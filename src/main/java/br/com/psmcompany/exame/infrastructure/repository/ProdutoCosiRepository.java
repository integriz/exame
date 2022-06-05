package br.com.psmcompany.exame.infrastructure.repository;

import br.com.psmcompany.exame.infrastructure.model.ProdutoCosifModel;
import br.com.psmcompany.exame.infrastructure.model.pk.ProdutoCosifPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoCosiRepository extends JpaRepository<ProdutoCosifModel, ProdutoCosifPk> {
    Boolean existsByIdProdutoModelCodProdutoAndIdCodCosif(String codProduto, String codCosif);
}
