package br.com.compass.avaliacao.service;

import br.com.compass.avaliacao.controller.PartidosController;
import br.com.compass.avaliacao.dto.requisicao.RequisicaoPartidosDto;
import br.com.compass.avaliacao.dto.resposta.RespostaAssociadosDto;
import br.com.compass.avaliacao.dto.resposta.RespostaPartidosDto;
import br.com.compass.avaliacao.entity.AssociadosEntity;
import br.com.compass.avaliacao.entity.PartidosEntity;
import br.com.compass.avaliacao.entity.enums.Ideologia;
import br.com.compass.avaliacao.exceptions.AssociadosExcecaoNaoEncontrada;
import br.com.compass.avaliacao.exceptions.PartidosExcecaoNaoEncontrada;
import br.com.compass.avaliacao.repository.AssociadosRepository;
import br.com.compass.avaliacao.repository.PartidosRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartidosService {

    private Logger log = LoggerFactory.getLogger(PartidosController.class);

    @Autowired
    private PartidosRepository partidosRepository;

    @Autowired
    private AssociadosRepository associadosRepository;

    @Autowired
    private ModelMapper modelMapper;

    public RespostaPartidosDto pegaPartidoPorId(Long id){

        log.info("pegaPartidosPorId - INICIOU - Listando os partidos por id.");

        PartidosEntity partidosEntity = partidosRepository.findById(id).orElseThrow(PartidosExcecaoNaoEncontrada::new);
        return modelMapper.map(partidosEntity, RespostaPartidosDto.class);
    }

    public List<RespostaPartidosDto> pegaTodosPartidos(String ideologia){

        log.info("pegaPartido - INICIOU - Listando todos os partidos.");

        List<PartidosEntity> partidosEntities;
        if(ideologia == null){
            partidosEntities = partidosRepository.findAll();
        }else{
            partidosEntities = partidosRepository.pegaComFiltroDeIdeologia(ideologia);
        }
        return partidosEntities.stream().map(partidosEntity -> modelMapper.map(partidosEntity,
                RespostaPartidosDto.class)).collect(Collectors.toList());
    }

    public RespostaPartidosDto salvaPartidos(RequisicaoPartidosDto requisicao){

        log.info("salvaPartidos - INICIOU - Salvando os partidos.");

        requisicao.setIdeologia(Ideologia.valueOf(requisicao.getIdeologia().toUpperCase()).pegaIdeologia());
        PartidosEntity entity = modelMapper.map(requisicao, PartidosEntity.class);
        PartidosEntity savedEntity = partidosRepository.save(entity);
        return modelMapper.map(savedEntity, RespostaPartidosDto.class);
    }

    public void deletaPartidoPorId(Long id){
        log.info("deletaPartidoPorId - INICIOU - Deletando os partidos por id.");

        partidosRepository.findById(id).orElseThrow(PartidosExcecaoNaoEncontrada::new);
        partidosRepository.deleteById(id);
    }

    public void atualizaPartidoPorId(RequisicaoPartidosDto requisicao, Long id){

        log.info("atualizaPartidoPorId - INICIOU - Atualizando partidos por id.");

        PartidosEntity politicalEntity = partidosRepository.findById(id).
                orElseThrow(PartidosExcecaoNaoEncontrada::new);
        modelMapper.map(requisicao, politicalEntity);
        partidosRepository.save(politicalEntity);
    }

    public RespostaPartidosDto listaPartido(Long id) {
        PartidosEntity partido = partidosRepository.findById(id).orElseThrow(PartidosExcecaoNaoEncontrada::new);
        return modelMapper.map(partido, RespostaPartidosDto.class);
    }

    public List<RespostaAssociadosDto> listaOsAssociados(Long id){

        log.info("listaOsAssociados - INICIOU - Listando todos os associados do partido.");

        List<AssociadosEntity> associadosEntities = associadosRepository.findByPartidoId_id(id);
        return associadosEntities.stream().map(associadosEntity -> modelMapper.
                map(associadosEntity, RespostaAssociadosDto.class)).collect(Collectors.toList());

    }
}
