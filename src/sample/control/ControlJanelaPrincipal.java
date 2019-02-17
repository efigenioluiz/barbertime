package sample.control;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.model.Cliente;
import sample.model.Ferramenta;
import sample.model.JDBC.JDBCCliente;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.util.Optional;

public class ControlJanelaPrincipal {
    @FXML
    public BorderPane TELA;


    public void initialize () throws Exception {

    }


    public void cadastrarCliente() {
        Ferramenta.getInstance().mudaJanela(TELA,"/sample/view/JanelaCadastrarCliente.fxml");
    }
    protected void mensagem(Alert.AlertType tipo, String mensagem){
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Alerta!!");
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    public void manterServico() {
        Ferramenta.getInstance().mudaJanela(TELA,"/sample/view/JanelaManterServicos.fxml");
    }

    public void manterAtendimento() {

        Ferramenta.getInstance().mudaJanela(TELA,"/sample/view/JanelaManterAtendimento.fxml");
    }

    public void gerarRelatorio() {
        Dialog<ButtonType> dialog = new Dialog();
        dialog.setTitle("Gerar Relatorio!");

        try{

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sample/view/JanelaGerarRelatorio.fxml"));
            dialog.getDialogPane().setContent(fxmlLoader.load());

            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

            Optional<ButtonType> result = dialog.showAndWait();

            if(result.isPresent() && result.get()== ButtonType.OK){
                ControlGerarRelatorio controlGerarRelatorio= fxmlLoader.getController();
                controlGerarRelatorio.processResult();
                Ferramenta.getInstance().mensagem(Alert.AlertType.CONFIRMATION,"Relatório Gerado com Sucesso!\nVerificar no Diretorio ");
            }
        }catch (IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

}
