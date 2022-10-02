package br.edu.ifpi.datasusscraping.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Equipamento {
    private Long id;
    private String nome;
    private Integer existentes;
    private Integer emUso;
    private Integer existentesSUS;
    private Integer emUsoSus;
}
