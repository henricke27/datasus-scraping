package br.edu.ifpi.datasusscraping.service;

import br.edu.ifpi.datasusscraping.client.Municipio;
import br.edu.ifpi.datasusscraping.entity.EstatisticaScraping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EstatisticaScrapingService {

    private final br.edu.ifpi.datasusscraping.client.DataSUS DataSUS;

    public List<EstatisticaScraping> scrape(String nomeMunicipio){
        Municipio municipio = Municipio.valueOf(nomeMunicipio.toUpperCase());
        return DataSUS.scrape(municipio.getValor());
    }

}
