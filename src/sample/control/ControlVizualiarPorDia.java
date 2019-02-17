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

public class ControlVizualiarPorDia {
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
