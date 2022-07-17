package br.com.compass.avaliacao.repository;

import br.com.compass.avaliacao.entity.AssociadosEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociadosRepository extends JpaRepository<AssociadosEntity, Long> {

    @Query("SELECT associado FROM AssociadosEntity associado WHERE(:cargoPolitico IS NULL OR :cargoPolitico = associado.cargoPolitico)")
    List<AssociadosEntity> pegaComFiltro(@Param("cargoPolitico") String cargoPolitico, Sort sort);

    List<AssociadosEntity> findByPartidoId_id(Long id);
}
