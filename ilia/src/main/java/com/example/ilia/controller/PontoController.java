package com.example.ilia.controller;

import com.example.ilia.controller.dto.Momento;
import com.example.ilia.controller.dto.RegistroDTO;
import com.example.ilia.controller.dto.Relatorio;
import com.example.ilia.service.PontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/v1")
public class PontoController {

    @Autowired
    private PontoService pontoService;

    @PostMapping("/batidas")
    public ResponseEntity<RegistroDTO> insereBatida(@RequestBody Momento momento) throws Exception  {

        RegistroDTO registro = pontoService.insereBatida(momento.dataHora);
            return ResponseEntity.status(HttpStatus.CREATED).body(registro);

    }

    @GetMapping("/folhas-de-ponto/{mes}")
    public ResponseEntity<Relatorio> geraRelatorioMensal(@PathVariable("mes") String mes) throws NoSuchElementException {

            Relatorio relatorio = pontoService.gerarRelatorioMensal(mes);
            return ResponseEntity.status(HttpStatus.OK).body(relatorio);

    }

}
