package br.com.psmcompany.exame.infrastructure.model;

import br.com.psmcompany.exame.infrastructure.model.pk.MovimentoManualModelPk;
import org.hibernate.annotations.Type;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "MOVIMENTO_MANUAL")
public class MovimentoManualModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private MovimentoManualModelPk id;

    @Column(name="DES_DESCRICAO",length=50)
    private String desDescricao;

    @Column(name="DAT_MOVIMENTO")
    private LocalDateTime datMovimento;

    @Column(name="COD_USUARIO",length=15)
    private String codUsuario;

    @Column(name="VAL_VALOR", precision=18, scale=2)
    @Type(type = "big_decimal")
    private BigDecimal valValor;

    public MovimentoManualModel(){
    }

    public MovimentoManualModel(MovimentoManualModelPk id, String desDescricao, LocalDateTime datMovimento, String codUsuario, BigDecimal valValor) {
        this.id = id;
        this.desDescricao = desDescricao;
        this.datMovimento = datMovimento;
        this.codUsuario = codUsuario;
        this.valValor = valValor;
    }

    public MovimentoManualModelPk getId() {
        return id;
    }

    public void setId(MovimentoManualModelPk id) {
        this.id = id;
    }

    public String getDesDescricao() {
        return desDescricao;
    }

    public void setDesDescricao(String desDescricao) {
        this.desDescricao = desDescricao;
    }

    public LocalDateTime getDatMovimento() {
        return datMovimento;
    }

    public void setDatMovimento(LocalDateTime datMovimento) {
        this.datMovimento = datMovimento;
    }

    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    public BigDecimal getValValor() {
        return valValor;
    }

    public void setValValor(BigDecimal valValor) {
        this.valValor = valValor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovimentoManualModel that = (MovimentoManualModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
