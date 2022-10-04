package br.edu.ifpi.datasusscraping.service;

import br.edu.ifpi.datasusscraping.domain.Equipamento;
import br.edu.ifpi.datasusscraping.repository.EquipamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;

    @Transactional(rollbackFor = Exception.class)
    public Equipamento save(Equipamento equipamento){
        return equipamentoRepository.save(equipamento);
    }

}
