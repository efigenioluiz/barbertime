package sample.control;

import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import sample.model.Atendimento;
import sample.model.Ferramenta;
import sample.model.JDBC.JDBCAtendimento;

import java.time.LocalDate;

public class ControlRemoverAtendimento {
    public TableView <Atendimento> tbAtendimento;
    public TableColumn tcHora;
    public TableColumn tcData;
    public TableColumn tcNome;
    public TableColumn tcServico;
    
    public BorderPane TELA;
    public JFXDatePicker dataDiaChoose;


    public void click(MouseEvent mouseEvent) {

    }

    public void Voltar(ActionEvent actionEvent) {
        Ferramenta.getInstance().mudaJanela(TELA,"/sample/view/JanelaManterAtendimento.fxml");
    }

    public void Confirmar(ActionEvent actionEvent) throws Exception {
        if(tbAtendimento.getSelectionModel().getSelectedItem() != null){
            JDBCAtendimento.getInstance().delete(tbAtendimento.getSelectionModel().getSelectedItem());
            Ferramenta.getInstance().mensagem(Alert.AlertType.CONFIRMATION,"Atendimento Removido com sucesso!");
            Ferramenta.getInstance().mudaJanela(TELA,"/sample/view/JanelaManterAtendimento.fxml");
        }else{
            Ferramenta.getInstance().mensagem(Alert.AlertType.ERROR,"Selecione atendimento para ser removido!");
        }

    }

    public void chooseDate(ActionEvent actionEvent) {
        atualizaTV(dataDiaChoose.getValue());
    }
    public void atualizaTV(LocalDate date){
        tbAtendimento.getItems().clear();
        tcHora.setCellValueFactory(new PropertyValueFactory<>("horaAtendimento"));
        tcData.setCellValueFactory(new PropertyValueFactory<>("dataAtendimento"));
        tcNome.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        tcServico.setCellValueFactory(new PropertyValueFactory<>("servico"));
        tbAtendimento.setItems(JDBCAtendimento.getInstance().listDoDiaSearch(date));
    }
}
