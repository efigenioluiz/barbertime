package sample.model;

import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Atendimento {
    private  int id_atendimento;
    private SimpleStringProperty dataAtendimento;
    private SimpleStringProperty horaAtendimento;
    private Cliente cliente;
    private Servico servico;
    private SimpleStringProperty estadoString;


    public String getEstadoString() {
        return estadoString.get();
    }

    public SimpleStringProperty estadoStringProperty() {
        return estadoString;
    }

    public void setEstadoString(String estadoString) {
        this.estadoString.set(estadoString);
    }

    public Atendimento(int id_atendimento, String dataAtendimento, Cliente cliente, String horaAtendimento, Servico servico, boolean estado) {
        this.id_atendimento = id_atendimento;
        this.dataAtendimento = new SimpleStringProperty(dataAtendimento);
        this.horaAtendimento= new SimpleStringProperty(horaAtendimento);
        this.cliente = cliente;
        this.servico=servico;
        this.estadoString= new SimpleStringProperty(verificaEstado(estado));
    }

    public Atendimento(String dataAtendimento, Cliente cliente, String horaAtendimento, Servico servico, Boolean estado) {

        this.dataAtendimento = new SimpleStringProperty(dataAtendimento);
        this.horaAtendimento= new SimpleStringProperty(horaAtendimento);
        this.cliente = cliente;
        this.servico=servico;
        this.estadoString= new SimpleStringProperty(verificaEstado(estado));
    }

    public String getDataAtendimento() {
        return dataAtendimento.get();
    }

    public SimpleStringProperty dataAtendimentoProperty() {
        return dataAtendimento;
    }

    public void setDataAtendimento(String dataAtendimento) {
        this.dataAtendimento.set(dataAtendimento);
    }

    public String getHoraAtendimento() {
        return horaAtendimento.get();
    }

    public SimpleStringProperty horaAtendimentoProperty() {
        return horaAtendimento;
    }

    public void setHoraAtendimento(String horaAtendimento) {
        this.horaAtendimento.set(horaAtendimento);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }


    public int getId_atendimento() {
        return id_atendimento;
    }

    public void setId_atendimento(int id_atendimento) {
        this.id_atendimento = id_atendimento;
    }



    public String verificaEstado(Boolean estado){
        if(estado == true){
                return "Concluído";
        }else {
            return "Pendente";
        }
    }

    @Override
    public String toString() {
        return "Atendimento{" +
                "id_atendimento=" + id_atendimento +
                ", cliente=" + cliente +
                ", servico=" + servico +
                ", estadoString=" + estadoString +
                '}';
    }

}
