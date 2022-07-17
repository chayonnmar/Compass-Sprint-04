package br.com.compass.avaliacao.repository;

import br.com.compass.avaliacao.entity.AssociadosEntity;
import br.com.compass.avaliacao.entity.PartidosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidosRepository extends JpaRepository<PartidosEntity, Long> {

    @Query("SELECT partido FROM PartidosEntity partido WHERE(:ideologia IS NULL OR :ideologia = partido.ideologia)")
    List<PartidosEntity> pegaComFiltroDeIdeologia(String ideologia);
}
