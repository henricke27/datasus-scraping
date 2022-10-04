package br.edu.ifpi.datasusscraping.controller;

import br.edu.ifpi.datasusscraping.entity.EstatisticaScraping;
import br.edu.ifpi.datasusscraping.service.EstatisticaScrapingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estatistica/scraping")
@RequiredArgsConstructor
public class EstatisticaScrapingController {

    private final EstatisticaScrapingService estatisticaScrapingService;

    @GetMapping(path = "/{municipio}")
    public ResponseEntity<List<EstatisticaScraping>> listAll(@PathVariable String municipio){
        return new ResponseEntity<>(estatisticaScrapingService.scrape(municipio), HttpStatus.OK);
    }
}
