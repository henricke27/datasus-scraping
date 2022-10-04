package br.edu.ifpi.datasusscraping.service;

import br.edu.ifpi.datasusscraping.domain.Estatistica;
import br.edu.ifpi.datasusscraping.domain.Municipio;
import br.edu.ifpi.datasusscraping.repository.EstatisticaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EstatisticaService {

    private final EstatisticaRepository estatisticaRepository;

    public Estatistica save(Estatistica estatistica){
        return estatisticaRepository.save(estatistica);
    }

    public void deleteByMunicipio(Municipio municipio){
        estatisticaRepository.deleteByMunicipio(municipio);
    }
}
