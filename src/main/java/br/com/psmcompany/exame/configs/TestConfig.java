package br.com.psmcompany.exame.configs;

import br.com.psmcompany.exame.infrastructure.model.*;
import br.com.psmcompany.exame.infrastructure.model.pk.MovimentoManualModelPk;
import br.com.psmcompany.exame.infrastructure.model.pk.ProdutoCosifPk;
import br.com.psmcompany.exame.infrastructure.repository.MovimentoManualRepository;
import br.com.psmcompany.exame.infrastructure.repository.ProdutoCosiRepository;
import br.com.psmcompany.exame.infrastructure.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    ProdutoCosiRepository produtoCosiRepository;

    @Autowired
    MovimentoManualRepository movimentoManualRepository;

    @Override
    public void run(String... args) throws Exception {
        ProdutoModel produto = new ProdutoModel("001", "DESC 001", "A");
        produtoRepository.save(produto);
        ProdutoCosifPk produtoCosifPk = new ProdutoCosifPk(produto, "codcosif1");
        ProdutoCosifModel produtoCosifModel = new ProdutoCosifModel(produtoCosifPk, "cla1", "A");
        produtoCosiRepository.save(produtoCosifModel);
        MovimentoManualModelPk movimentoManualModelPk = new MovimentoManualModelPk(produtoCosifModel, BigDecimal.valueOf(2), BigDecimal.valueOf(2019), BigDecimal.valueOf(1));
        MovimentoManualModel movimentoManualModel = new MovimentoManualModel(movimentoManualModelPk, "TESTE DESC", LocalDateTime.now(ZoneId.of("UTC")), "TESTE", BigDecimal.valueOf(150));
        movimentoManualRepository.save(movimentoManualModel);
    }
}
