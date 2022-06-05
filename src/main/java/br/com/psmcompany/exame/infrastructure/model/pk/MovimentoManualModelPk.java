package br.com.psmcompany.exame.infrastructure.model.pk;

import br.com.psmcompany.exame.infrastructure.model.ProdutoCosifModel;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class MovimentoManualModelPk implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "COD_COSIF"), @JoinColumn(name = "COD_PRODUTO")})
    private ProdutoCosifModel produtoCosifModel;

    @Column(name="DAT_MES", precision=2, scale=0)
    @Type(type = "big_decimal")
    private BigDecimal datMes;

    @Column(name="DAT_ANO", precision=4, scale=0)
    @Type(type = "big_decimal")
    private BigDecimal datAno;

    @Column(name="NUM_LANCAMENTO", precision=18, scale=0)
    @Type(type = "big_decimal")
    private BigDecimal numLancamento;

    public MovimentoManualModelPk() {
    }

    public MovimentoManualModelPk(ProdutoCosifModel produtoCosifModel, BigDecimal datMes, BigDecimal datAno, BigDecimal numLancamento) {
        this.produtoCosifModel = produtoCosifModel;
        this.datMes = datMes;
        this.datAno = datAno;
        this.numLancamento = numLancamento;
    }

    public ProdutoCosifModel getProdutoCosifModel() {
        return produtoCosifModel;
    }

    public void setProdutoCosifModel(ProdutoCosifModel produtoCosifModel) {
        this.produtoCosifModel = produtoCosifModel;
    }

    public BigDecimal getDatMes() {
        return datMes;
    }

    public void setDatMes(BigDecimal datMes) {
        this.datMes = datMes;
    }

    public BigDecimal getDatAno() {
        return datAno;
    }

    public void setDatAno(BigDecimal datAno) {
        this.datAno = datAno;
    }

    public BigDecimal getNumLancamento() {
        return numLancamento;
    }

    public void setNumLancamento(BigDecimal numLancamento) {
        this.numLancamento = numLancamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovimentoManualModelPk that = (MovimentoManualModelPk) o;
        return Objects.equals(produtoCosifModel, that.produtoCosifModel) && Objects.equals(datMes, that.datMes) && Objects.equals(datAno, that.datAno) && Objects.equals(numLancamento, that.numLancamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produtoCosifModel, datMes, datAno, numLancamento);
    }
}
