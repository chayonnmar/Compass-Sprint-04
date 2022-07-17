package br.com.compass.avaliacao.dto.requisicao;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class VinculaAssociadoEPartido {
    @Positive
    private Long idAssociado;
    @Positive
    private Long idPartido;

}
