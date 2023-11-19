package com.compeasy.model;

public class Cadastro {
    private Paciente paciente;
    private Endereco endereco;
    private String telefone;
    private String email;
    private String observacao;

    public Cadastro(Paciente paciente, Endereco endereco, String telefone,String email, String observacao) {
        this.paciente = paciente;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.observacao = observacao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
  
}
