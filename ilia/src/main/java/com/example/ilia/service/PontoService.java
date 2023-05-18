package com.example.ilia.service;

import com.example.ilia.controller.dto.RegistroDTO;
import com.example.ilia.controller.dto.Relatorio;
import com.example.ilia.entity.Registro;

import java.util.NoSuchElementException;

public interface PontoService {
    RegistroDTO insereBatida(String momento) throws Exception;
    Relatorio gerarRelatorioMensal(String mes) throws NoSuchElementException;

}
