package br.com.compass.avaliacao.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "associados")
public class AssociadosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cargo_politico")
    private String cargoPolitico;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "sexo")
    private String sexo;

    @ManyToOne(optional = true, cascade = CascadeType.REFRESH)
    private PartidosEntity partidoId;

}
