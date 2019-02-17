package sample.control;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import sample.model.*;
import sample.model.JDBC.JDBCAtendimento;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class JanelaManterAtendimento {
    public BorderPane TELA;
    public Text data;

    public TableView<Atendimento> tbAtendimento;
    public TableColumn<Atendimento, String> tcHora;
    public TableColumn<Atendimento, String> tcData;
    public TableColumn<Atendimento, Servico> tcServico;
    public TableColumn<Atendimento, Cliente> tcNome;
    public TableColumn<Atendimento, String> tcEstado;


    //--------------------- segunda tableview --------------------
    public TableView tbAtendimentoEstado;
    public TableColumn tcHora2;
    public TableColumn tcData2;
    public TableColumn tcNome2;
    public TableColumn tcServico2;
    public TableColumn tcEstado2;

    public void initialize(){
        data.setText("Data: "+getPegaDataAtual());
        atualizaTV();
    }
    public void adicionarAtendimento() {
        Ferramenta.getInstance().mudaJanela(TELA,"/sample/view/JanelaAdicionarAtendimento.fxml");
    }

    public void removerAtendimento() {
        Ferramenta.getInstance().mudaJanela(TELA,"/sample/view/JanelaRemoverAtendimento.fxml");
    }

    public void voltar() {
        Ferramenta.getInstance().mudaJanela(TELA,"/sample/view/JanelaPrincipal.fxml");
    }
    public String getPegaDataAtual() {
        Date dataa = new Date(System.currentTimeMillis());
        SimpleDateFormat formatarDate = new SimpleDateFormat("dd/MM/yyy");
        return formatarDate.format(dataa);
    }

    public void atualizaTV() {
        tbAtendimento.getItems().clear();
        tcHora.setCellValueFactory(new PropertyValueFactory<>("horaAtendimento"));
        tcData.setCellValueFactory(new PropertyValueFactory<>("dataAtendimento"));
        tcEstado.setCellValueFactory(new PropertyValueFactory<>("estadoString"));
        tcNome.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        tcServico.setCellValueFactory(new PropertyValueFactory<>("servico"));
        tbAtendimento.setItems(JDBCAtendimento.getInstance().listDoDia());

        tbAtendimentoEstado.getItems().clear();
        tcHora2.setCellValueFactory(new PropertyValueFactory<>("horaAtendimento"));
        tcData2.setCellValueFactory(new PropertyValueFactory<>("dataAtendimento"));
        tcEstado2.setCellValueFactory(new PropertyValueFactory<>("estadoString"));
        tcNome2.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        tcServico2.setCellValueFactory(new PropertyValueFactory<>("servico"));
        tbAtendimentoEstado.setItems(JDBCAtendimento.getInstance().listDoDiaEstado());
    }

    public void visualizarDia() {
        Ferramenta.getInstance().mudaJanela(TELA,"/sample/view/JanelaVisualizarPorDia.fxml");
    }

    public void alterarAtendimento() {
        Ferramenta.getInstance().mudaJanela(TELA,"/sample/view/JanelaAlterarAtendimento.fxml");
    }

    public void darBaixa(MouseEvent mouseEvent) {
        if(tbAtendimento.getSelectionModel().getSelectedItem() != null){
            JDBCAtendimento.getInstance().updateEstado(tbAtendimento.getSelectionModel().getSelectedItem());
            atualizaTV();
        }
    }
}
