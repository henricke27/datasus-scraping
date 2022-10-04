package br.edu.ifpi.datasusscraping.repository;

import br.edu.ifpi.datasusscraping.domain.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
    List<Municipio> findByNome(String nome);

}
