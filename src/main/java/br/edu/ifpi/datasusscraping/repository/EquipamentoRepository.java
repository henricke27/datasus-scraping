package br.edu.ifpi.datasusscraping.repository;

import br.edu.ifpi.datasusscraping.domain.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {

}
