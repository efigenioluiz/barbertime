package sample.model;

public class Servico {
    private int id_Servico;
    private String nome;
    private double valor;


    @Override
    public String toString() {
        return nome+" - "+valor;
    }

    public Servico(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public Servico(int id_Servico, String nome, double valor) {
        this.id_Servico = id_Servico;
        this.nome = nome;
        this.valor = valor;
    }

    public Servico() {
        this.nome = nome;
        this.valor = valor;
    }

    public int getId_Servico() {
        return id_Servico;
    }

    public void setId_Servico(int id_Servico) {
        this.id_Servico = id_Servico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
