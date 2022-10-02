package br.edu.ifpi.datasusscraping.service;

import br.edu.ifpi.datasusscraping.client.DataSUS;
import br.edu.ifpi.datasusscraping.client.Municipio;
import br.edu.ifpi.datasusscraping.entity.Equipamento;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EquipamentoService {

    private final DataSUS DataSUS;
    
    public List<Equipamento> scrape(String nomeMunicipio){
        Municipio municipio = Municipio.valueOf(nomeMunicipio.toUpperCase());
        return DataSUS.scrape(municipio.getValor());
    }

}
