package br.com.psmcompany.exame.domain.resources;

import br.com.psmcompany.exame.domain.dto.MovimentoManualDto;
import br.com.psmcompany.exame.domain.services.MovimentoManualService;
import br.com.psmcompany.exame.domain.services.ProdutoCosifService;
import br.com.psmcompany.exame.infrastructure.model.MovimentoManualModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/movimentos")
public class MovimentoManualResource {
    @Autowired
    private MovimentoManualService movimentoManualService;
    @Autowired
    private ProdutoCosifService produtoCosifService;

    @GetMapping(value = "/{datAno}/{datMes}")
    public ResponseEntity<List<MovimentoManualModel>> findByIdDatAnoAndIdDatMes(@PathVariable BigDecimal datAno, @PathVariable BigDecimal datMes ) {
        List<MovimentoManualModel> movimentoManualModel = movimentoManualService.findByIdDatAnoAndIdDatMes(datAno, datMes);
        return ResponseEntity.ok().body(movimentoManualModel);
    }

    @PostMapping
    public ResponseEntity<Object> saveMovimentoManual(@RequestBody @Valid MovimentoManualDto movimentoManualDto){
        if(produtoCosifService.existsByIdCodProdutoAndIdCodCosif(movimentoManualDto.getCodProduto(), movimentoManualDto.getCodCosif())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: CodProduto and CodCosif not found!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(movimentoManualService.save(movimentoManualDto));
    }
}
