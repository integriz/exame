package br.com.psmcompany.exame.infrastructure.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "PRODUTO")
public class ProdutoModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="COD_PRODUTO",length=4)
    private String codProduto;

    @Column(name="DES_PRODUTO",length=30)
    private String desProduto;

    @Column(name="STA_STATUS",length=1)
    private String staStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "id.produtoModel")
    private List<ProdutoCosifModel> produtoCosifModel;

    public ProdutoModel(){
    }

    public ProdutoModel(String codProduto, String desProduto, String staStatus) {
        this.codProduto = codProduto;
        this.desProduto = desProduto;
        this.staStatus = staStatus;
    }

    public String getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(String codProduto) {
        this.codProduto = codProduto;
    }

    public String getDesProduto() {
        return desProduto;
    }

    public void setDesProduto(String desProduto) {
        this.desProduto = desProduto;
    }

    public String getStaStatus() {
        return staStatus;
    }

    public void setStaStatus(String staStatus) {
        this.staStatus = staStatus;
    }

    public List<ProdutoCosifModel> getProdutoCosifModel() {
        return produtoCosifModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoModel that = (ProdutoModel) o;
        return Objects.equals(codProduto, that.codProduto) && Objects.equals(desProduto, that.desProduto) && Objects.equals(staStatus, that.staStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codProduto);
    }
}
