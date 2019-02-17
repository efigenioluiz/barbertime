package sample.model;

import javafx.beans.property.SimpleStringProperty;

public class Cliente {

    private int id;

    private SimpleStringProperty nome;
    private SimpleStringProperty telefone;
    private SimpleStringProperty sexo;

    private SimpleStringProperty cpf;

    private SimpleStringProperty dataNascimento;


    public SimpleStringProperty dataNascimentoProperty() {
        return dataNascimento;
    }

    public Cliente(int id,String nome, String cpf, String telefone, String sexo, String dataNascimento) {
        this.id=id;
        this.nome = new SimpleStringProperty(nome);
        this.cpf = new SimpleStringProperty(cpf);
        this.telefone = new SimpleStringProperty(telefone);
        this.sexo = new SimpleStringProperty(sexo);
        this.dataNascimento = new SimpleStringProperty(dataNascimento);
    }

    @Override
    public String toString() {
        return nome.get();
    }
    public Cliente(String nome, String cpf, String telefone, String sexo, String dataNascimento) {
        this.nome = new SimpleStringProperty(nome);
        this.cpf = new SimpleStringProperty(cpf);
        this.telefone = new SimpleStringProperty(telefone);
        this.sexo = new SimpleStringProperty(sexo);
        this.dataNascimento = new SimpleStringProperty(dataNascimento);
    }

    public String getNome() {
        return nome.get();
    }

    public SimpleStringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getCpf() {
        return cpf.get();
    }

    public SimpleStringProperty cpfProperty() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf.set(cpf);
    }

    public String getTelefone() {
        return telefone.get();
    }

    public SimpleStringProperty telefoneProperty() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone.set(telefone);
    }

    public String getSexo() {
        return sexo.get();
    }

    public SimpleStringProperty sexoProperty() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo.set(sexo);
    }

    public String getDataNascimento() {
        return dataNascimento.get();
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento.setValue(dataNascimento);
    }

    public void setId(int id) {
    }
    public int getId() {
        return id;
    }
}