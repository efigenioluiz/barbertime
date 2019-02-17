package sample.control;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.model.Ferramenta;
import sample.model.JDBC.JDBCServico;
import sample.model.NumberTextField;
import sample.model.Servico;

import java.io.IOException;

public class ControlAdicionarServico {
    public BorderPane TELA;

    public TextField nomeServico;
    public NumberTextField valor;

    public void confirmar() throws Exception {
        if(nomeServico.getText() == null || valor.getText() == null){
            Ferramenta.getInstance().mensagem(Alert.AlertType.ERROR,"Campos incorretos, tente novamente!");
            Ferramenta.getInstance().mudaJanela(TELA,"/sample/view/JanelaAdicionarServico.fxml");

        }else {
            Servico servico = new Servico(nomeServico.getText(), Double.parseDouble(valor.getText()));
            JDBCServico.getInstance().create(servico);
            Ferramenta.getInstance().mensagem(Alert.AlertType.CONFIRMATION, "Serviço Cadastrado com sucesso !");
            Ferramenta.getInstance().mudaJanela(TELA,"/sample/view/JanelaManterServicos.fxml");
        }

    }

    public void voltar() {
        Ferramenta.getInstance().mudaJanela(TELA,"/sample/view/JanelaManterServicos.fxml");
    }

}
