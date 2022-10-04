package br.edu.ifpi.datasusscraping.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstatisticaScraping {
    private Long codigo;
    private String nome;
    private Integer existentes;
    private Integer emUso;
    private Integer existentesSUS;
    private Integer emUsoSus;
}
