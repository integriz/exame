package br.com.psmcompany.exame.domain.services;

import br.com.psmcompany.exame.infrastructure.repository.ProdutoCosiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoCosifService {
    @Autowired
    private ProdutoCosiRepository produtoCosiRepository;

    public Boolean existsByIdCodProdutoAndIdCodCosif(String codProduto, String codCosif) {
        return produtoCosiRepository.existsByIdProdutoModelCodProdutoAndIdCodCosif(codProduto, codCosif);
    }
}
