package com.example.ilia.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Mensagem {
    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
