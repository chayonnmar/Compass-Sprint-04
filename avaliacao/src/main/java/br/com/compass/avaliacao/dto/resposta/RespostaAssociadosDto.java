package br.com.compass.avaliacao.dto.resposta;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RespostaAssociadosDto {

    private String nome;

    private String cargoPolitico;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private String sexo;

}
