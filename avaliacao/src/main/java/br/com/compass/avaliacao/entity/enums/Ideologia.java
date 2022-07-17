package br.com.compass.avaliacao.entity.enums;

public enum Ideologia {

    DIREITA("Direita"),
    CENTRO("Centro"),
    ESQUERDA("Esquerda");

    private String ideologia;

    Ideologia (String ideologia){

        this.ideologia = ideologia;
    }

    public String pegaIdeologia(){

        return ideologia ;
    }
}
