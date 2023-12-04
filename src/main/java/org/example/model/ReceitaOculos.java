package org.example.model;

import java.time.LocalDate;

public class ReceitaOculos extends GenericModel {

    private String detalhamento;
    private LocalDate dataConsulta;
    private Integer idConsultaMedica;

    public ReceitaOculos(Integer id, String detalhamento, LocalDate dataConsulta, Integer idConsultaMedica) {
        super(id);
        this.detalhamento = detalhamento;
        this.dataConsulta = dataConsulta;
        this.idConsultaMedica = idConsultaMedica;
    }

    public String getDetalhamento() {
        return detalhamento;
    }

    public void setDetalhamento(String detalhamento) {
        this.detalhamento = detalhamento;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Integer getIdConsultaMedica() {
        return idConsultaMedica;
    }

    public void setIdConsultaMedica(Integer idConsultaMedica) {
        this.idConsultaMedica = idConsultaMedica;
    }

    @Override
    public String toString() {
        return String.format("ReceitaOculos {detalhamento='%s', dataConsulta='%s', idConsultaMedica='%s'}",
                detalhamento, dataConsulta, idConsultaMedica);
    }
}
