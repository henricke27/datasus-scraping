package br.edu.ifpi.datasusscraping.repository;

import br.edu.ifpi.datasusscraping.domain.Estatistica;
import br.edu.ifpi.datasusscraping.domain.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstatisticaRepository extends JpaRepository<Estatistica, Void> {
    void deleteByMunicipio(Municipio municipio);
}
