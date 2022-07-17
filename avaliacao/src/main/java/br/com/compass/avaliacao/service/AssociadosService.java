package br.com.compass.avaliacao.service;

import br.com.compass.avaliacao.controller.AssociadosController;
import br.com.compass.avaliacao.dto.requisicao.RequisicaoAssociadosDto;
import br.com.compass.avaliacao.dto.requisicao.VinculaAssociadoEPartido;
import br.com.compass.avaliacao.dto.resposta.RespostaAssociadosDto;
import br.com.compass.avaliacao.entity.AssociadosEntity;
import br.com.compass.avaliacao.entity.PartidosEntity;
import br.com.compass.avaliacao.entity.enums.CargoPolitico;
import br.com.compass.avaliacao.entity.enums.Sexo;
import br.com.compass.avaliacao.exceptions.AssociadosExcecaoNaoEncontrada;
import br.com.compass.avaliacao.exceptions.PartidosExcecaoNaoEncontrada;
import br.com.compass.avaliacao.repository.AssociadosRepository;
import br.com.compass.avaliacao.repository.PartidosRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssociadosService {

    private Logger log = LoggerFactory.getLogger(AssociadosController.class);

    @Autowired
    private AssociadosRepository associadosRepository;

    @Autowired
    private PartidosRepository partidosRepository;
    @Autowired
    private ModelMapper modelMapper;

    public RespostaAssociadosDto pegaAssociadoPorId(Long id){

        log.info("pegaAssociadosPorId - INICIOU - Listando os associados por id.");

        AssociadosEntity associadosEntity = associadosRepository.findById(id).orElseThrow(AssociadosExcecaoNaoEncontrada::new);
        return modelMapper.map(associadosEntity, RespostaAssociadosDto.class);
    }

    public List<RespostaAssociadosDto> pegaTodosAssociados(String cargoPolitico, String ordenaPor){

        log.info("pegaTodosAssociados - INICIOU - Listando todos os associados.");

        List<AssociadosEntity> todosAssociados = associadosRepository.pegaComFiltro(cargoPolitico,
                Sort.by(Sort.Direction.ASC, ordenaPor));
        List<RespostaAssociadosDto> dto = todosAssociados.stream().map(AssociadosEntity -> modelMapper.
                map(AssociadosEntity, RespostaAssociadosDto.class)).collect(Collectors.toList());
        return dto;

    }

    public RespostaAssociadosDto salvaAssociados(RequisicaoAssociadosDto requisicao){

        log.info("salvaAssociados - INICIOU - Salvando os associados.");

        requisicao.setCargoPolitico(CargoPolitico.valueOf(requisicao.getCargoPolitico().toUpperCase()).pegaCargoPolitico());
        requisicao.setSexo(Sexo.valueOf(requisicao.getSexo().toUpperCase()).pegaSexo());
        AssociadosEntity associadosEntity = modelMapper.map(requisicao, AssociadosEntity.class);
        AssociadosEntity associados= associadosRepository.save(associadosEntity);
        return modelMapper.map(associados, RespostaAssociadosDto.class);
    }

    public void deletaAssociadosPorId(Long id){

       log.info("deletaAssociadosPorId - INICIOU - Deletando os associados por id.");
       associadosRepository.findById(id).orElseThrow(AssociadosExcecaoNaoEncontrada::new);
       associadosRepository.deleteById(id);
    }

    public void atualizaAssociadosPorId(RequisicaoAssociadosDto requisicao, Long id){

        log.info("atualizaAssociadosPorId - INICIOU - Atualizando os associados por id.");

        AssociadosEntity associadosEntity = associadosRepository.findById(id).orElseThrow(AssociadosExcecaoNaoEncontrada::new);
        modelMapper.map(requisicao,associadosEntity);
        associadosRepository.save(associadosEntity);
    }

    public void vinculaAssociadoAoPartido(VinculaAssociadoEPartido requisicao){

        log.info("vinculaAssociadoAoPartido - INICIOU - Vinculando associado ao partido.");

        Long idAssociado = requisicao.getIdAssociado();
        Long idPartido = requisicao.getIdPartido();

        AssociadosEntity associadosEntity = associadosRepository.findById(idAssociado).
                orElseThrow(AssociadosExcecaoNaoEncontrada::new);
        PartidosEntity partidosEntity = partidosRepository.findById(idPartido).
                orElseThrow(PartidosExcecaoNaoEncontrada::new);
        associadosEntity.setPartidoId(partidosEntity);
        associadosRepository.save(associadosEntity);

    }


    public RespostaAssociadosDto desvinculaAssociadoDoPartido(Long idAssociado, Long idPartido){

        log.info("desvinculaAssociadoDoPartido - INICIOU - Desvinculando associado do partido.");

        partidosRepository.findById(idPartido).orElseThrow(PartidosExcecaoNaoEncontrada::new);
        AssociadosEntity associadosEntity = associadosRepository.findById(idAssociado).orElseThrow(AssociadosExcecaoNaoEncontrada::new);
        associadosEntity.setPartidoId(null);
        associadosRepository.save(associadosEntity);
        return modelMapper.map(associadosEntity, RespostaAssociadosDto.class);
    }

}
