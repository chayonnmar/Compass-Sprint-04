package br.com.compass.avaliacao.dto.requisicao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class RequisicaoAssociadosDto {

    @NotBlank
    private String nome;
    @NotBlank
    private String cargoPolitico;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    @NotBlank
    private String sexo;

}
