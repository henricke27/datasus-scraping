package br.edu.ifpi.datasusscraping.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Estatistica {
    @Id
    private Long id;
    @ManyToOne
    private Equipamento equipamento;
    @ManyToOne
    private Municipio municipio;
    private Integer existentes;
    private Integer emUso;
    private Integer existentesSUS;
    private Integer emUsoSus;
}
