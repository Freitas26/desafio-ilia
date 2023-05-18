package com.example.ilia.controller.dto;
import java.util.List;

import com.example.ilia.entity.Registro;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Relatorio {
    @JsonProperty("mes")
    private String mes;

    @JsonProperty("horasTrabalhadas")
    private String horasTrabalhadas;

    @JsonProperty("horasExcedentes")
    private String horasExcedentes;

    @JsonProperty("horasDevidas")
    private String horasDevidas;

    @JsonProperty("registros")
    private List<RegistroDTO>  registros;

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(String horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public String getHorasExcedentes() {
        return horasExcedentes;
    }

    public void setHorasExcedentes(String horasExcedentes) {
        this.horasExcedentes = horasExcedentes;
    }

    public String getHorasDevidas() {
        return horasDevidas;
    }

    public void setHorasDevidas(String horasDevidas) {
        this.horasDevidas = horasDevidas;
    }

    public List<RegistroDTO>  getRegistros() {
        return registros;
    }

    public void setRegistros(List<RegistroDTO> registros) {
        this.registros = registros;
    }
}