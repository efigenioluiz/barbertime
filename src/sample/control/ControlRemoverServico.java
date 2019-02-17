package sample.control;

import com.jfoenix.controls.JFXComboBox;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.model.Ferramenta;
import sample.model.JDBC.JDBCServico;
import sample.model.Servico;

import java.io.IOException;

public class ControlRemoverServico {
    public JFXComboBox cbServico;
    public BorderPane TELA;

    public void initialize(){
        cbServico.getItems().addAll(JDBCServico.getInstance().list());
    }

    public void Confirmar() throws Exception {
        JDBCServico.getInstance().delete((Servico) cbServico.getValue());
        Ferramenta.getInstance().mudaJanela(TELA,"/sample/view/JanelaManterServicos.fxml");
    }

    public void Voltar() {
        Ferramenta.getInstance().mudaJanela(TELA,"/sample/view/JanelaManterServicos.fxml");
    }


}
