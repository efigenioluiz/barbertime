package sample.control;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import sample.model.Atendimento;
import sample.model.Cliente;
import sample.model.Ferramenta;
import sample.model.JDBC.JDBCAtendimento;
import sample.model.JDBC.JDBCServico;
import sample.model.Servico;
import sun.security.pkcs11.P11Util;

import java.time.LocalDate;
import java.time.LocalTime;

public class ControlAlterarAtendimento {
    public BorderPane TELA;

    //LISTAR PARA ALTERAR
    public TableView<Atendimento> tbAtendimento;
    public TableColumn<Atendimento, String> tcHora;
    public TableColumn<Atendimento, String> tcData;
    public TableColumn<Atendimento, Servico> tcServico;
    public TableColumn<Atendimento, Cliente> tcNome;

    // ALTER
    public JFXDatePicker dataAtendimento;
    public JFXTimePicker horaAtendimento;
    public JFXComboBox<Servico> cbServico;
    public JFXDatePicker dataDiaChoose;


    public void initialize(){
        dataAtendimento.setDisable(true);
        horaAtendimento.setDisable(true);
        cbServico.setDisable(true);
        cbServico.setItems(JDBCServico.getInstance().list());
        horaAtendimento.setIs24HourView(true);

    }

    public void confirmar() throws Exception {
        if(dataAtendimento != null  && horaAtendimento != null && cbServico.getValue() != null){
            Atendimento atendimento= tbAtendimento.getSelectionModel().getSelectedItem();
            atendimento.setDataAtendimento(dataAtendimento.getValue().toString());
            atendimento.setHoraAtendimento(horaAtendimento.getValue().toString());
            atendimento.setServico(cbServico.getValue());

            JDBCAtendimento.getInstance().update(atendimento);
            Ferramenta.getInstance().mensagem(Alert.AlertType.CONFIRMATION,"Atendimento Alterado com Sucesso !");
            Ferramenta.getInstance().mudaJanela(TELA,"/sample/view/JanelaManterAtendimento.fxml");
        }else{
            Ferramenta.getInstance().mensagem(Alert.AlertType.ERROR,"Preencha os campos corretamente");
            Ferramenta.getInstance().mudaJanela(TELA,"/sample/view/JanelaAlterarAtendimento.fxml");
        }

    }

    public void voltar() {
        Ferramenta.getInstance().mudaJanela(TELA,"/sample/view/JanelaManterAtendimento.fxml");
    }
    public void atualizaTV(LocalDate date){
        tbAtendimento.getItems().clear();
        tcHora.setCellValueFactory(new PropertyValueFactory<>("horaAtendimento"));
        tcData.setCellValueFactory(new PropertyValueFactory<>("dataAtendimento"));
        tcNome.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        tcServico.setCellValueFactory(new PropertyValueFactory<>("servico"));
        tbAtendimento.setItems(JDBCAtendimento.getInstance().listDoDiaSearch(date));
    }

    public void click(MouseEvent mouseEvent) {
        if(mouseEvent.getButton().equals(MouseButton.PRIMARY) && tbAtendimento.getSelectionModel().getSelectedItem() != null){

            dataAtendimento.setDisable(false);
            horaAtendimento.setDisable(false);
            cbServico.setDisable(false);

            dataAtendimento.setValue(LocalDate.parse(tbAtendimento.getSelectionModel().getSelectedItem().getDataAtendimento()));
            horaAtendimento.setValue(LocalTime.parse(tbAtendimento.getSelectionModel().getSelectedItem().getHoraAtendimento()));
        }
    }

    public void chooseDate() {
        atualizaTV(dataDiaChoose.getValue());
    }
}
