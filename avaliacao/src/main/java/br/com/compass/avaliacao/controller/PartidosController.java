package br.com.compass.avaliacao.controller;

import br.com.compass.avaliacao.dto.requisicao.RequisicaoPartidosDto;
import br.com.compass.avaliacao.dto.resposta.RespostaAssociadosDto;
import br.com.compass.avaliacao.dto.resposta.RespostaPartidosDto;
import br.com.compass.avaliacao.service.PartidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/partidos")
public class PartidosController {

    @Autowired
    private PartidosService partidosService;

    @GetMapping("/{id}")
    public ResponseEntity<RespostaPartidosDto> pegaPorId(@PathVariable Long id) {
        RespostaPartidosDto respostaPartidosDto = partidosService.pegaPartidoPorId(id);
        return ResponseEntity.ok(respostaPartidosDto);
    }

    @GetMapping
    public ResponseEntity<List<RespostaPartidosDto>> pegaTodos(@RequestParam(required = false) String ideologia) {
        List<RespostaPartidosDto> responsePartidosDtos = partidosService.pegaTodosPartidos(ideologia);
        return ResponseEntity.ok(responsePartidosDtos);
    }


    @PostMapping
    public ResponseEntity<RespostaPartidosDto> cadastra(@RequestBody @Valid RequisicaoPartidosDto requisicao){
        RespostaPartidosDto resposta = partidosService.salvaPartidos(requisicao);
        return ResponseEntity.ok(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualiza(@RequestBody @Valid RequisicaoPartidosDto requisicao, @PathVariable Long id){
        partidosService.atualizaPartidoPorId(requisicao, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleta(@PathVariable Long id) {
        partidosService.deletaPartidoPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/associados")
    public ResponseEntity<List<RespostaAssociadosDto>> listaOsAssociados(@PathVariable Long id){
        List<RespostaAssociadosDto> respostaAssociadosDtos = partidosService.listaOsAssociados(id);
        return ResponseEntity.ok(respostaAssociadosDtos);

    }

}
