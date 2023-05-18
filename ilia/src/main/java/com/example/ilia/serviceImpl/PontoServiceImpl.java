package com.example.ilia.serviceImpl;


import com.example.ilia.controller.dto.RegistroDTO;
import com.example.ilia.controller.dto.Relatorio;
import com.example.ilia.controller.exceptions.BusinessException;
import com.example.ilia.controller.exceptions.ConflictException;
import com.example.ilia.controller.exceptions.InputException;
import com.example.ilia.entity.Registro;
import com.example.ilia.repository.RegistroRepository;
import com.example.ilia.service.PontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PontoServiceImpl implements PontoService {

    @Autowired
    private RegistroRepository registroRepository;

    @Override
    public RegistroDTO insereBatida(String momento) throws Exception {
        if (momento == null) {
            throw new InputException("Campo obrigatório não informado");
        }
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        try{

        LocalDateTime dataHora = LocalDateTime.parse(momento,inputFormatter);

        if (dataHora.getDayOfWeek() == DayOfWeek.SATURDAY || dataHora.getDayOfWeek() == DayOfWeek.SUNDAY) {
            throw new BusinessException("Sábado e domingo não são permitidos como dia de trabalho");
        }

        String dia = dataHora.toLocalDate().toString();
        Registro item = registroRepository.findByDia(dia).orElse(new Registro(dia));
        if (item.getHorarioLivre() < 0) {
            throw new BusinessException("Apenas 4 horários podem ser registrados por dia");
        }
        LocalTime horario = dataHora.toLocalTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        if(item.getHorarioLivre() > 0 && Arrays.stream(item.getHorarios())
                .anyMatch(a->a.equals(horario.format(formatter)))){
            throw new ConflictException("Horário já registrado");
        }


        String[] registros = item.getHorarios();
        registros[item.getHorarioLivre()] = horario.format(formatter);
        if (item.getPausa() != null && item.getRetorno() == null) {
            LocalTime ultimoHorario = LocalTime.parse(item.getPausa());
            if (Duration.between(ultimoHorario, horario).toMinutes() < 60) {
                throw new BusinessException("Deve haver no mínimo 1 hora de almoço");
            }
        }

        item.setHorarios(registros);
        return new RegistroDTO(registroRepository.save(item));
        }catch (DateTimeParseException e){
            throw new InputException("Data e hora em formato inválido");
        }

    }

    public Relatorio gerarRelatorioMensal(String mes) throws NoSuchElementException{
        List<Registro> registros = registroRepository.findByDiaStartsWith(mes).orElseThrow();
        Duration tempoTrabalhado = registros.stream().filter(item->(item.getHorarioLivre() < 0)).map(item ->
                Duration.between(LocalTime.parse(item.getEntrada()),LocalTime.parse(item.getPausa()))
                        .plus(Duration.between(LocalTime.parse(item.getRetorno()),
                                LocalTime.parse(item.getSaida())))).reduce(Duration.ZERO, Duration::plus);
        Relatorio novoRelatorio = new Relatorio();
        novoRelatorio.setHorasTrabalhadas(tempoTrabalhado.toString());
        var yearMonth = YearMonth.parse(mes);
        Predicate<LocalDate> fimDeSemana = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY;

        long diasDoMes = Stream.iterate(yearMonth.atDay(1), date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(yearMonth.atDay(1), yearMonth.atEndOfMonth()))
                .filter(fimDeSemana.negate())
                .count();
        var horasNoMes = Duration.of(diasDoMes*9,ChronoUnit.HOURS);
        var diff = tempoTrabalhado.minus(horasNoMes);
        if(diff.isNegative()){
            novoRelatorio.setHorasDevidas(horasNoMes.abs().toString());
            novoRelatorio.setHorasExcedentes(Duration.ZERO.toString());
        }else{
            novoRelatorio.setHorasDevidas(Duration.ZERO.toString());
            novoRelatorio.setHorasExcedentes(diff.toString());
        }

        novoRelatorio.setRegistros(registros.stream()
                .map(item->new RegistroDTO(item.getDia(),item.getHorarios()))
                .collect(Collectors.toList()));
        novoRelatorio.setMes(mes);
        return novoRelatorio;

    }
}
