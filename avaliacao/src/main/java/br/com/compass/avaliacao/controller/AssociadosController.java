package br.com.compass.avaliacao.controller;

import br.com.compass.avaliacao.dto.requisicao.RequisicaoAssociadosDto;
import br.com.compass.avaliacao.dto.requisicao.VinculaAssociadoEPartido;
import br.com.compass.avaliacao.dto.resposta.RespostaAssociadosDto;
import br.com.compass.avaliacao.service.AssociadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/associados")
public class AssociadosController {

    @Autowired
    private AssociadosService associadosService;

    @GetMapping("/{id}")
    public ResponseEntity<RespostaAssociadosDto> pegaPorId(@PathVariable Long id) {
        RespostaAssociadosDto respostaAssociadosDto = associadosService.pegaAssociadoPorId(id);
        return ResponseEntity.ok(respostaAssociadosDto);
    }

    @GetMapping
    public ResponseEntity<List<RespostaAssociadosDto>> pegaTodos(@RequestParam(required = false) String cargoPolitico,
                                                                 @RequestParam(required = false,
                                                                         defaultValue = "id") String ordenaPor) {
        List<RespostaAssociadosDto> respostaAssociadosDto= associadosService.pegaTodosAssociados(cargoPolitico, ordenaPor);
        return ResponseEntity.ok(respostaAssociadosDto);
    }



    @PostMapping
    public ResponseEntity<RespostaAssociadosDto> cadastra(@RequestBody @Valid RequisicaoAssociadosDto requisicao){
        RespostaAssociadosDto resposta = associadosService.salvaAssociados(requisicao);
        return ResponseEntity.ok(resposta);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualiza(@RequestBody @Valid RequisicaoAssociadosDto requisicao, @PathVariable Long id){
        associadosService.atualizaAssociadosPorId(requisicao, id);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleta(@PathVariable Long id) {
        associadosService.deletaAssociadosPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/partidos")
    public ResponseEntity<Void> vincula(@RequestBody @Valid VinculaAssociadoEPartido requisicao){
        associadosService.vinculaAssociadoAoPartido(requisicao);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/partidos/{idPartidos}")
    public ResponseEntity<RespostaAssociadosDto> desvincula(@PathVariable Long id, @PathVariable Long idPartidos){
        RespostaAssociadosDto respostaAssociadosDto = associadosService.desvinculaAssociadoDoPartido(id, idPartidos);
        return ResponseEntity.ok(respostaAssociadosDto);
    }

}
