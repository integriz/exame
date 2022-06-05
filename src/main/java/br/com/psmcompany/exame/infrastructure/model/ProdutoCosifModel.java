package br.com.psmcompany.exame.infrastructure.model;

import br.com.psmcompany.exame.infrastructure.model.pk.ProdutoCosifPk;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PRODUTO_COSIF")
public class ProdutoCosifModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ProdutoCosifPk id;

    @Column(name="COD_CLASSIFICACAO",length=6)
    private String codClassificacao;

    @Column(name="STA_STATUS",length=1)
    private String staStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "id.produtoCosifModel")
    private Set<MovimentoManualModel> produtoCosifModel = new HashSet<>();

    public ProdutoCosifModel() {
    }

    public ProdutoCosifModel(ProdutoCosifPk id, String codClassificacao, String staStatus) {
        this.id = id;
        this.codClassificacao = codClassificacao;
        this.staStatus = staStatus;
    }

    public ProdutoCosifPk getId() {
        return id;
    }

    public void setId(ProdutoCosifPk id) {
        this.id = id;
    }

    public String getCodClassificacao() {
        return codClassificacao;
    }

    public void setCodClassificacao(String codClassificacao) {
        this.codClassificacao = codClassificacao;
    }

    public String getStaStatus() {
        return staStatus;
    }

    public void setStaStatus(String staStatus) {
        this.staStatus = staStatus;
    }

    public Set<MovimentoManualModel> getProdutoCosifModel() {
        return produtoCosifModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoCosifModel that = (ProdutoCosifModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
