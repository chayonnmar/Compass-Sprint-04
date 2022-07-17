package br.com.compass.avaliacao.entity.enums;

public enum CargoPolitico {

    VEREADOR("Vereador"),
    PREFEITO("Prefeito"),
    DEPUTADO_ESTADUAL("Deputado Estadual"),
    DEPUTADO_FEDERAL("Deputado Federal"),
    SENADOR("Senador"),
    GOVERNADOR("Governador"),
    PRESIDENTE("Presidente"),
    NENHUM("Nenhum");

    private String cargoPolitico;

    CargoPolitico(String cargoPolitico){

        this.cargoPolitico = cargoPolitico;
    }

    public String pegaCargoPolitico(){
        return cargoPolitico;
    }
}
