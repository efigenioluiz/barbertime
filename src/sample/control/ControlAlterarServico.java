package sample.control;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import sample.model.Ferramenta;
import sample.model.JDBC.JDBCServico;
import sample.model.NumberTextField;
import sample.model.Servico;

public class ControlAlterarServico {
    public JFXComboBox<Servico> cbServico;
    public BorderPane TELA;
    public TextField valor;

    public void initialize(){
        cbServico.setItems(JDBCServico.getInstance().list());
    }

    public void Alterar() throws Exception {
        if(valor.getText() == null || cbServico.getValue() ==null ){
            Ferramenta.getInstance().mensagem(Alert.AlertType.ERROR,"Campos incorretos, tente novamente!");
            Ferramenta.getInstance().mudaJanela(TELA,"/sample/view/JanelaAlterarServico.fxml");
        }else{
            JDBCServico.getInstance().update(cbServico.getValue(),Double.parseDouble(valor.getText()));
            Ferramenta.getInstance().mensagem(Alert.AlertType.CONFIRMATION,"Servico Alterado com Sucesso!");
            Ferramenta.getInstance().mudaJanela(TELA,"/sample/view/JanelaManterServicos.fxml");
        }
    }

    public void Voltar() {
        Ferramenta.getInstance().mudaJanela(TELA,"/sample/view/JanelaManterServicos.fxml");
    }
}
