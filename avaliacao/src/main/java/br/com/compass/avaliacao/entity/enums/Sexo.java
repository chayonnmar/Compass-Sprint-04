package br.com.compass.avaliacao.entity.enums;

public enum Sexo {

    FEMININO("Feminino"),
    MASCULINO("Masculino");

    private String sexo;

    Sexo(String sexo){

        this.sexo = sexo;
    }

    public String pegaSexo(){

        return sexo;
    }
}
