package br.com.psmcompany.exame.domain.services;

import br.com.psmcompany.exame.domain.services.exceptions.ResourceNotFoundException;
import br.com.psmcompany.exame.infrastructure.model.ProdutoModel;
import br.com.psmcompany.exame.infrastructure.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoModel findById(String codProduto) {
        Optional<ProdutoModel> produtoModel = produtoRepository.findById(codProduto);
        return produtoModel.orElseThrow(() -> new ResourceNotFoundException(codProduto));
    }
}
