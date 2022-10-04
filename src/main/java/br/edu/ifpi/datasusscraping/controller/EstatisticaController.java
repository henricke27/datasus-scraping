package br.edu.ifpi.datasusscraping.controller;

import br.edu.ifpi.datasusscraping.client.DataSUS;
import br.edu.ifpi.datasusscraping.service.EstatisticaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/estatistica")
@RequiredArgsConstructor
public class EstatisticaController {

    private final EstatisticaService estatisticaService;
    private final DataSUS dataSUS;

    @GetMapping(path = "/sync")
    public ResponseEntity<Void> sincronizarPorMunicipio(@RequestParam String municipio) {
        dataSUS.sincronizarPorMunicipio(municipio);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
