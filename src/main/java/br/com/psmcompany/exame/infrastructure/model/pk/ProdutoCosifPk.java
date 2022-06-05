package br.com.psmcompany.exame.infrastructure.model.pk;

import br.com.psmcompany.exame.infrastructure.model.ProdutoModel;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProdutoCosifPk implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "COD_PRODUTO")
    private ProdutoModel produtoModel;

    @Column(name="COD_COSIF", length=11)
    private String codCosif;

    public ProdutoCosifPk(){
    }

    public ProdutoCosifPk(ProdutoModel produtoModel, String codCosif) {
        this.produtoModel = produtoModel;
        this.codCosif = codCosif;
    }

    public ProdutoModel getProdutoModel() {
        return produtoModel;
    }

    public void setProdutoModel(ProdutoModel produtoModel) {
        this.produtoModel = produtoModel;
    }

    public String getCodCosif() {
        return codCosif;
    }

    public void setCodCosif(String codCosif) {
        this.codCosif = codCosif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoCosifPk that = (ProdutoCosifPk) o;
        return Objects.equals(produtoModel, that.produtoModel) && Objects.equals(codCosif, that.codCosif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produtoModel, codCosif);
    }
}
