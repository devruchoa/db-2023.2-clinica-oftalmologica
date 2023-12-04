package org.example.model;

import java.time.LocalDate;

public class ConsultaMedica extends GenericModel {

    private String assinatura;
    private LocalDate dataConsulta;
    private Integer pacienteId;
    private Integer medicoId;

    public ConsultaMedica(Integer id, String assinatura, LocalDate dataConsulta, Integer pacienteId, Integer medicoId) {
        super(id);
        this.assinatura = assinatura;
        this.dataConsulta = dataConsulta;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
    }

    public String getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(String assinatura) {
        this.assinatura = assinatura;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Integer getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Integer pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Integer getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Integer medicoId) {
        this.medicoId = medicoId;
    }

    @Override
    public String toString() {
        return String.format("ConsultaMedica {assinatura='%s', dataConsulta='%s', pacienteId='%s', medicoId='%s'}",
                assinatura, dataConsulta, pacienteId, medicoId);
    }
}
