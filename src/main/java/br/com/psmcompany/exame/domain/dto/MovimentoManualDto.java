package br.com.psmcompany.exame.domain.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class MovimentoManualDto {
    @NotBlank
    @Size(max = 2)
    private String datMes;

    @NotBlank
    @Size(max = 4)
    private String datAno;

    @NotBlank
    @Size(max = 4)
    private String codProduto;

    @NotBlank
    @Size(max = 11)
    private String codCosif;

    @Size(max = 18)
    private String valValor;

    @Size(max = 50)
    private String desDescricao;

    @Size(max = 15)
    private String codUsuario = "TESTE";

    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getDatMes() {
        return datMes;
    }

    public void setDatMes(String datMes) {
        this.datMes = datMes;
    }

    public String getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(String codProduto) {
        this.codProduto = codProduto;
    }

    public String getCodCosif() {
        return codCosif;
    }

    public void setCodCosif(String codCosif) {
        this.codCosif = codCosif;
    }

    public String getValValor() {
        return valValor;
    }

    public void setValValor(String valValor) {
        this.valValor = valValor;
    }
    public String getDatAno() {
        return datAno;
    }

    public void setDatAno(String datAno) {
        this.datAno = datAno;
    }

    public String getDesDescricao() {
        return desDescricao;
    }

    public void setDesDescricao(String desDescricao) {
        this.desDescricao = desDescricao;
    }
}
