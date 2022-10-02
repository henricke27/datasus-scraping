package br.edu.ifpi.datasusscraping.client;

public enum Municipio {
    AGUA_BRANCA(220020),
    ALTO_LONGA(220030),
    ALTOS(220040),
    BOA_HORA(220177),
    CAMPO_MAIOR(220220),
    CAJAZEIRAS(220207),
    CAPITAO_DE_CAMPOS(220240),
    CASTELO_DO_PIAUI(220260),
    ESPERANTINA(220370),
    FLORIANO(220390),
    PARNAIBA(220770),
    PICOS(220800),
    PIRIPIRI(220840),
    MIGUEL_ALVES(220620),
    OEIRAS(220700),
    SAO_JOAO_DA_SERRA(220990),
    TERESINA(221100),
    UNIAO(221110);

    private final int valor;
    Municipio(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

}
