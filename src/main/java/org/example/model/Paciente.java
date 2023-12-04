package org.example.model;

import java.time.LocalDate;

public class Paciente extends GenericModel {

    private String nome;
    private String cpf;
    private LocalDate dataNascimento;

    public Paciente(Integer id, String nome, String cpf, LocalDate dataNascimento) {
        super(id);
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return String.format("Paciente {id='%s', nome='%s', cpf='%s', dataNascimento='%s'}",
                this.getId(), nome, cpf, dataNascimento);
    }
}