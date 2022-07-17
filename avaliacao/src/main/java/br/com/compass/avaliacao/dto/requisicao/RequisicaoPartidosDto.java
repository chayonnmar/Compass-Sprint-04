package br.com.compass.avaliacao.dto.requisicao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
public class RequisicaoPartidosDto {

    @NotBlank
    private String nome;
    @NotBlank
    private String sigla;
    @NotBlank
    private String ideologia;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFundacao;

}
