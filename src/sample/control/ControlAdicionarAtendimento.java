package sample.control;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import sample.model.Atendimento;
import sample.model.Cliente;
import sample.model.Ferramenta;
import sample.model.JDBC.JDBCAtendimento;
import sample.model.JDBC.JDBCCliente;
import sample.model.JDBC.JDBCServico;
import sample.model.Servico;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ControlAdicionarAtendimento {

    public JFXTimePicker horaAtendimento;
    public JFXDatePicker dataAtendimento;
    public JFXComboBox<Servico> cbServico;
    public TableView<Cliente> cliente;
    public TableColumn<Cliente,String> tcCpf;
    public TableColumn<Cliente,String> tcNome;
    public BorderPane TELA;

    public void initialize(){
        horaAtendimento.setIs24HourView(true);
        cbServico.setItems(JDBCServico.getInstance().list());
        atualizaTV();
    }
    public void voltar() {
        Ferramenta.getInstance().mudaJanela(TELA, "/sample/view/JanelaManterAtendimento.fxml");
    }

    public void confirmar() throws SQLException, ParseException {

        if(horaAtendimento.getValue() == null|| dataAtendimento.getValue() ==null || cbServico==null||cliente == null ){
            Ferramenta.getInstance().mensagem(Alert.AlertType.ERROR,"Campos preenchidos incorretamente!");
        }else {
            Cliente c = cliente.getSelectionModel().getSelectedItem();



            Atendimento atendimento = new Atendimento(dataAtendimento.getValue().toString(), c,horaAtendimento.getValue().toString(), cbServico.getValue(), false);

            JDBCAtendimento.getInstance().create(atendimento, c, cbServico.getValue());
            Ferramenta.getInstance().mensagem(Alert.AlertType.CONFIRMATION, "Atendimento Cadastrado com Sucesso!");
            Ferramenta.getInstance().mudaJanela(TELA, "/sample/view/JanelaManterAtendimento.fxml");

        }
    }
    public void atualizaTV() {
        cliente.getItems().clear();
        tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        cliente.setItems(JDBCCliente.getInstance().list());
    }
}
