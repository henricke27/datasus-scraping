package br.edu.ifpi.datasusscraping.service;

import br.edu.ifpi.datasusscraping.domain.Municipio;
import br.edu.ifpi.datasusscraping.repository.MunicipioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MunicipioService {

    private final MunicipioRepository municipioRepository;

    public Municipio findyByNome(String nome){
        return municipioRepository.findByNome(nome).stream()
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Municipio save(Municipio municipio) {
        Optional.ofNullable(findyByNome(municipio.getNome()))
                .ifPresent((m) -> {throw new ResponseStatusException(HttpStatus.CONFLICT);});

        return municipioRepository.save(municipio);
    }
}
