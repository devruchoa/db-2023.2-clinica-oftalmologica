package org.example.model;

public class Especialidade extends GenericModel {

    private String descricao;
    private String conselho;

    public Especialidade(Integer id, String descricao, String conselho) {
        super(id);
        this.descricao = descricao;
        this.conselho = conselho;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getConselho() {
        return conselho;
    }

    public void setConselho(String conselho) {
        this.conselho = conselho;
    }

    @Override
    public String toString() {
        return "Especialidade {" +
                "id='" + this.getId() + "'" +
                "descricao='" + descricao + "'" +
                "conselho='" + conselho + "'" +
                '}';
    }
}
