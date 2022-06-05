package br.com.psmcompany.exame.domain.services;

import br.com.psmcompany.exame.domain.dto.MovimentoManualDto;
import br.com.psmcompany.exame.domain.services.exceptions.ResourceNotFoundException;
import br.com.psmcompany.exame.infrastructure.model.MovimentoManualModel;
import br.com.psmcompany.exame.infrastructure.model.ProdutoModel;
import br.com.psmcompany.exame.infrastructure.model.pk.MovimentoManualModelPk;
import br.com.psmcompany.exame.infrastructure.model.pk.ProdutoCosifPk;
import br.com.psmcompany.exame.infrastructure.repository.MovimentoManualRepository;
import br.com.psmcompany.exame.infrastructure.repository.ProdutoCosiRepository;
import br.com.psmcompany.exame.infrastructure.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class MovimentoManualService {
    @Autowired
    private MovimentoManualRepository movimentoManualRepository;
    @Autowired
    private ProdutoCosiRepository produtoCosiRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<MovimentoManualModel> findByIdDatAnoAndIdDatMes(BigDecimal datAno, BigDecimal datMes) {
        Optional<List<MovimentoManualModel>> movimentoManualModels = movimentoManualRepository.findByIdDatAnoAndIdDatMes(datAno, datMes);
        return movimentoManualModels.orElseThrow(() -> new ResourceNotFoundException(movimentoManualModels));
    }

    @Transactional
    public MovimentoManualModel save(MovimentoManualDto movimentoManualDto) {
        Optional<ProdutoModel> optionalProdutoModel = produtoRepository.findById(movimentoManualDto.getCodProduto());

        ProdutoModel produtoModel = optionalProdutoModel.orElseThrow(() -> new ResourceNotFoundException(optionalProdutoModel));
        ProdutoCosifPk produtoCosifPk = new ProdutoCosifPk(produtoModel, movimentoManualDto.getCodCosif());
        MovimentoManualModelPk movimentoManualModelPk = new MovimentoManualModelPk(
                produtoCosifPk.getProdutoModel().getProdutoCosifModel().get(0),
                BigDecimal.valueOf(Double.valueOf(movimentoManualDto.getDatMes())),
                BigDecimal.valueOf(Double.valueOf(movimentoManualDto.getDatAno())),
                geraLancamento(movimentoManualDto.getDatAno(), movimentoManualDto.getDatMes()));
        MovimentoManualModel movimentoManualModel = new MovimentoManualModel(movimentoManualModelPk,
                movimentoManualDto.getDesDescricao(),
                LocalDateTime.now(ZoneId.of("UTC")),  movimentoManualDto.getCodUsuario(),
                BigDecimal.valueOf(Double.valueOf(movimentoManualDto.getValValor())));

        return movimentoManualRepository.save(movimentoManualModel);
    }

    private BigDecimal geraLancamento(String ano, String mes) {
        Long quantidadeLancamento = movimentoManualRepository.countByIdDatAnoAndIdDatMes(
                BigDecimal.valueOf(Integer.valueOf(ano)),
                BigDecimal.valueOf(Integer.valueOf(mes)));

        return BigDecimal.valueOf(quantidadeLancamento + 1);
    }
}