package org.example.model;

public class ObservacaoLaudo extends GenericModel {

    private String descricao;
    private Integer receitaOculosId;

    public ObservacaoLaudo(Integer id, String descricao, Integer receitaOculosId) {
        super(id);
        this.descricao = descricao;
        this.receitaOculosId = receitaOculosId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getReceitaOculosId() {
        return receitaOculosId;
    }

    public void setReceitaOculosId(Integer receitaOculosId) {
        this.receitaOculosId = receitaOculosId;
    }

    @Override
    public String toString() {
        return String.format("ObservacaoLaudo{ id=%d, descricao='%s', receitaOculosId=%d }", getId(), descricao, receitaOculosId);
    }
}
