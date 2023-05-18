package com.example.ilia.entity;

import com.fasterxml.jackson.annotation.JsonProperty;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Entity
@Table(name="registros")
public class Registro {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(name="dia")
    private String dia;
    @Column(name="entrada")
    private String entrada = null;
    @Column(name="pausa")
    private String pausa= null;
    @Column(name="retorno")
    private String retorno= null;
    @Column(name="saida")
    private String saida= null;
    public Registro(String dia){
        this.dia = dia;
    }
    public Registro(){}

    public String getDia() {
        return dia;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getPausa() {
        return pausa;
    }

    public void setPausa(String pausa) {
        this.pausa = pausa;
    }

    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

    public String[] getHorarios() {
        return new String[]{this.entrada,this.pausa,this.retorno,this.saida};
    }

    public void setHorarios(String[] horarios){
        this.entrada = horarios[0];
        this.pausa=horarios[1];
        this.retorno=horarios[2];
        this.saida=horarios[3];
    }

    public int getHorarioLivre(){
        var list = this.getHorarios();
        int i=0;
        while (i<=3){
            if(list[i]==null||list[i].isBlank()){
            return i;
            }
            i++;
        }
        return -1;
    }

}
