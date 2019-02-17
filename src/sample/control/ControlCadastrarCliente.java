package sample.control;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.model.Cliente;
import sample.model.Ferramenta;
import sample.model.JDBC.JDBCCliente;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class ControlCadastrarCliente {
    @FXML
    public BorderPane TELA;

    //inserindo cliente na tabela
    @FXML
    public TextField nome;
    public TextField telefone;
    public TextField cpf;
    public ComboBox<String> sexo;
    public DatePicker dataNascimento;

    public void initialize(){
        sexo.getItems().add("M");
        sexo.getItems().add("F");
    }

    public void voltar() {
        Ferramenta.getInstance().mudaJanela(TELA,"/sample/view/JanelaPrincipal.fxml");
    }

    public void confirmar() throws Exception {
        if(nome.getText() == null || cpf.getText() == null || telefone.getText() == null || sexo.getSelectionModel().getSelectedItem() == null ){
            Ferramenta.getInstance().mensagem(Alert.AlertType.ERROR, "Preencha todos os campos corretamente!");
            Ferramenta.getInstance().mudaJanela(TELA,"../view/JanelaCadastrarCliente.fxml");
        }else {
            Cliente cliente = new Cliente(nome.getText(), cpf.getText(), telefone.getText(), sexo.getSelectionModel().getSelectedItem(), dataNascimento.getValue().toString());
            System.out.println(cliente.getDataNascimento());
            JDBCCliente.getInstance().create(cliente);
            Ferramenta.getInstance().mensagem(Alert.AlertType.CONFIRMATION, "Casdastrado com sucesso!");
            Ferramenta.getInstance().mudaJanela(TELA,"../view/JanelaPrincipal.fxml");
        }
    }
}
