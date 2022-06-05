package br.com.psmcompany.exame.infrastructure.repository;

import br.com.psmcompany.exame.infrastructure.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, String> {
}
