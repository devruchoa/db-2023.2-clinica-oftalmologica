package org.example.model;

public class EspecificacaoLente extends GenericModel {

    private Float valor;
    private Integer estruturaLenteId;
    private Integer atributoEstruturaLenteId;

    public EspecificacaoLente(Integer id, Float valor, Integer estruturaLenteId, Integer atributoEstruturaLenteId) {
        super(id);
        this.valor = valor;
        this.estruturaLenteId = estruturaLenteId;
        this.atributoEstruturaLenteId = atributoEstruturaLenteId;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Integer getEstruturaLenteId() {
        return estruturaLenteId;
    }

    public void setEstruturaLenteId(Integer estruturaLenteId) {
        this.estruturaLenteId = estruturaLenteId;
    }

    public Integer getAtributoEstruturaLenteId() {
        return atributoEstruturaLenteId;
    }

    public void setAtributoEstruturaLenteId(Integer atributoEstruturaLenteId) {
        this.atributoEstruturaLenteId = atributoEstruturaLenteId;
    }

    @Override
    public String toString() {
        return "EspecificacaoLente{" +
                "valor=" + valor +
                ", estruturaLenteId=" + estruturaLenteId +
                ", atributoEstruturaLenteId=" + atributoEstruturaLenteId +
                '}';
    }
}
