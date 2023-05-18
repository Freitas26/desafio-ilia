package com.example.ilia.controller.dto;

import com.example.ilia.entity.Registro;
import com.fasterxml.jackson.annotation.JsonProperty;
public class RegistroDTO {
    @JsonProperty("dia")
    private String dia;
    @JsonProperty("horarios")
    private String[] horarios;

    public String[] getHorarios() {
        return horarios;
    }

    public RegistroDTO(String dia, String[] horarios) {
        this.dia = dia;
        this.horarios = horarios;
    }

    public RegistroDTO(Registro registro) {
        this.dia = registro.getDia();
        this.horarios = registro.getHorarios();
    }


}
