package com.compeasy.model;
//
public class Endereco {
    private String logradouro;
    private int numero;
    private String bairro;
    private String localidade;
    private String cep;

    public Endereco(String rua, int numero, String bairro, String cidade, String cep) {
        this.logradouro = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.localidade = cidade;
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "logradouro='" + logradouro + '\'' +
                ", numero=" + numero +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + localidade + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }    
}
