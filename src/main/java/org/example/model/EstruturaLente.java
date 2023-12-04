package org.example.model;

public class EstruturaLente extends GenericModel {

    private String tipoCorrecao;
    private Integer distanciaPupilar;
    private Integer receitaOculosId;

    public EstruturaLente(Integer id, String tipoCorrecao, Integer distanciaPupilar, Integer receitaOculosId) {
        super(id);
        this.tipoCorrecao = tipoCorrecao;
        this.distanciaPupilar = distanciaPupilar;
        this.receitaOculosId = receitaOculosId;
    }

    public String getTipoCorrecao() {
        return tipoCorrecao;
    }

    public void setTipoCorrecao(String tipoCorrecao) {
        this.tipoCorrecao = tipoCorrecao;
    }

    public Integer getDistanciaPupilar() {
        return distanciaPupilar;
    }

    public void setDistanciaPupilar(Integer distanciaPupilar) {
        this.distanciaPupilar = distanciaPupilar;
    }

    public Integer getReceitaOculosId() {
        return receitaOculosId;
    }

    public void setReceitaOculosId(Integer receitaOculosId) {
        this.receitaOculosId = receitaOculosId;
    }

    @Override
    public String toString() {
        return String.format("EstruturaLente{tipoCorrecao='%s', distanciaPupilar=%d, receitaOculosId=%d}", tipoCorrecao, distanciaPupilar, receitaOculosId);
    }
}
