package br.edu.ifpi.datasusscraping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstatisticaDto {
    private Long codigo;
    private String nome;
    private Integer existentes;
    private Integer emUso;
    private Integer existentesSUS;
    private Integer emUsoSus;
}
