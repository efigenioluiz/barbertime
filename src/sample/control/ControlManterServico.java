package sample.control;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.model.Ferramenta;
import sample.model.JDBC.JDBCServico;
import sample.model.Servico;

import java.io.IOException;
import java.time.temporal.TemporalAdjusters;

public class ControlManterServico {


    public BorderPane TELA;
    public TableView<Servico> tbServicos;
    public TableColumn tcValor;
    public TableColumn tcNome;

    public void initialize() {
        atualizaTV();
    }

    public void adicionarServico(ActionEvent actionEvent) {
        Ferramenta.getInstance().mudaJanela(TELA,"/sample/view/JanelaAdicionarServico.fxml");
    }


    public void voltar() {
        Ferramenta.getInstance().mudaJanela(TELA,"/sample/view/JanelaPrincipal.fxml");
    }

    public void atualizaTV() {
        tbServicos.getItems().clear();
        tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tbServicos.setItems(JDBCServico.getInstance().list());
    }

    public void removerServico() {
        Ferramenta.getInstance().mudaJanela(TELA,"/sample/view/JanelaRemoverServico.fxml");
    }

    public void alterarServico() {
        Ferramenta.getInstance().mudaJanela(TELA,"/sample/view/JanelaAlterarServico.fxml");
    }
}