package br.com.psmcompany.exame;

import br.com.psmcompany.exame.domain.services.MovimentoManualService;
import br.com.psmcompany.exame.domain.services.ProdutoCosifService;
import br.com.psmcompany.exame.domain.services.ProdutoService;
import br.com.psmcompany.exame.infrastructure.model.*;
import br.com.psmcompany.exame.infrastructure.model.pk.MovimentoManualModelPk;
import br.com.psmcompany.exame.infrastructure.model.pk.ProdutoCosifPk;
import br.com.psmcompany.exame.infrastructure.repository.MovimentoManualRepository;
import br.com.psmcompany.exame.infrastructure.repository.ProdutoCosiRepository;
import br.com.psmcompany.exame.infrastructure.repository.ProdutoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class ExameApplicationTest {
    @TestConfiguration
    static class ProdutoServiceTestConfiguration {
        @Bean
        public ProdutoService produtoService() {
            return new ProdutoService();
        }

        @Bean
        public ProdutoCosifService produtoCosifService() {
            return new ProdutoCosifService();
        }

        @Bean
        public MovimentoManualService movimentoManualService() {
            return new MovimentoManualService();
        }
    }

    @Autowired
    ProdutoService produtoService;

    @Autowired
    ProdutoCosifService produtoCosiService;

    @Autowired
    MovimentoManualService movimentoManualService;

    @MockBean
    ProdutoRepository produtoRepository;

    @MockBean
    ProdutoCosiRepository produtoCosiRepository;

    @MockBean
    MovimentoManualRepository movimentoManualRepository;

    @Before
    public void setup() {
        ProdutoModel produto = new ProdutoModel("001", "DESC 001", "A");
        Mockito.when(produtoRepository.findById("001")).thenReturn(Optional.of(produto));

        Mockito.when(produtoCosiRepository.existsByIdProdutoModelCodProdutoAndIdCodCosif(produto.getCodProduto(), "codcosif1" )).thenReturn(Boolean.TRUE);

        ProdutoCosifPk produtoCosifPk = new ProdutoCosifPk(produto, "codcosif1");
        ProdutoCosifModel produtoCosifModel = new ProdutoCosifModel(produtoCosifPk, "cla1", "A");
        MovimentoManualModelPk movimentoManualModelPk = new MovimentoManualModelPk(produtoCosifModel, BigDecimal.valueOf(2), BigDecimal.valueOf(2019), BigDecimal.valueOf(1));
        MovimentoManualModel movimentoManualModel = new MovimentoManualModel(movimentoManualModelPk, "TESTE DESC", LocalDateTime.now(ZoneId.of("UTC")), "TESTE", BigDecimal.valueOf(150));
        List <MovimentoManualModel> movimentoManualModels = Arrays.asList(movimentoManualModel);
        Optional<List<MovimentoManualModel>> optionalMovimentoManualModels = Optional.of(movimentoManualModels);
        Mockito.when(movimentoManualRepository.findByIdDatAnoAndIdDatMes(BigDecimal.valueOf(2019), BigDecimal.valueOf(2))).thenReturn(optionalMovimentoManualModels);
    }

    @Test
    public void deveTratarCasoQuandoProcurarRegistroNaoExistente() {
        String codProduto = "000";

        try {
            ProdutoModel produtoModel = produtoService.findById(codProduto);
        } catch (Throwable e) {
            String message = e.getMessage();
            Assertions.assertEquals( "Resource not found. Id 000", message);
        }
    }

    @Test
    public void deveRetornarORegistroProdurado() {
        String codProduto = "001";

        ProdutoModel produtoModel = produtoService.findById(codProduto);

        Assertions.assertEquals(produtoModel.getStaStatus(),"A");
    }

    @Test
    public void deveRetornarFalseAoProcurarRegistroNaoExistente() {
        Boolean exists = produtoCosiService.existsByIdCodProdutoAndIdCodCosif("000", "codcosif1");

        Assertions.assertFalse(exists);
    }

    @Test
    public void deveRetornarTrueAoProcurarRegistroExistente() {
        Boolean exists = produtoCosiService.existsByIdCodProdutoAndIdCodCosif("001", "codcosif1");

        Assertions.assertTrue(exists);
    }

    @Test
    public void deveTratarQuandoNaoEncontrarRegistroPesquisado() {
        BigDecimal datAno = new BigDecimal(2011);
        BigDecimal datMes = new BigDecimal(2);

        try {
            List<MovimentoManualModel> movimentoManualModels = movimentoManualService.findByIdDatAnoAndIdDatMes(datAno, datMes);
        } catch (Throwable e) {
            String message = e.getMessage();
            Assertions.assertEquals( "Resource not found. Id Optional.empty", message);
        }
    }

    @Test
    public void deveEncontrarRegistroPesquisado() {
        BigDecimal datAno = new BigDecimal(2019);
        BigDecimal datMes = new BigDecimal(2);

        List<MovimentoManualModel> movimentoManualModels = movimentoManualService.findByIdDatAnoAndIdDatMes(datAno, datMes);

        Assertions.assertEquals(movimentoManualModels.get(0).getId().getDatAno(), datAno);
    }
}
