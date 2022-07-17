package br.com.compass.avaliacao.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "Partidos")
public class PartidosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nome")
    private String nome;

    @Column(name = "sigla")
    private String sigla;

    @Column(name = "ideologia")
    private String ideologia;

    @Column(name = "data_fundacao")
    private LocalDate dataFundacao;

}
