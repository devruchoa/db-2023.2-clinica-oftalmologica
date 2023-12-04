package org.example.model;

import java.time.LocalDate;

public class EspecialidadeMedica extends GenericModel {

    private String observacao;
    private LocalDate dtConclusao;
    private Integer especialidadeID;
    private Integer medicoID;

    public EspecialidadeMedica(Integer id, String observacao, LocalDate dtConclusao, Integer especialidadeID, Integer medicoID) {
        super(id);
        this.observacao = observacao;
        this.dtConclusao = dtConclusao;
        this.especialidadeID = especialidadeID;
        this.medicoID = medicoID;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public LocalDate getDtConclusao() {
        return dtConclusao;
    }

    public void setDtConclusao(LocalDate dtConclusao) {
        this.dtConclusao = dtConclusao;
    }

    public Integer getEspecialidadeID() {
        return especialidadeID;
    }

    public void setEspecialidadeID(Integer especialidadeID) {
        this.especialidadeID = especialidadeID;
    }

    public Integer getMedicoID() {
        return medicoID;
    }

    public void setMedicoID(Integer medicoID) {
        this.medicoID = medicoID;
    }

    @Override
    public String toString() {
        return String.format("EspecialidadeMedica{ID=%d, observacao='%s', dtConclusao=%s, especialidadeID=%d, medicoID=%d}", getId(), observacao, dtConclusao, especialidadeID, medicoID);
    }
}
