package br.edu.ifpi.datasusscraping.controller;

import br.edu.ifpi.datasusscraping.entity.Equipamento;
import br.edu.ifpi.datasusscraping.service.EquipamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EquipamentoController {

    private final EquipamentoService equipamentoService;

    @GetMapping(path = "/equipamentos/{municipio}")
    public ResponseEntity<List<Equipamento>> listAll(@PathVariable String municipio){
        return new ResponseEntity<>(equipamentoService.scrape(municipio), HttpStatus.OK);
    }
}
