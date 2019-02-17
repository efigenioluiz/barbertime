package sample.model.interfaces;

import javafx.collections.ObservableList;
import sample.model.Atendimento;
import sample.model.Cliente;
import sample.model.Servico;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface AtendimentoDAO {

    void create(Atendimento a, Cliente cliente, Servico servico) throws SQLException;
    public List<Atendimento> list();
    public Atendimento search(int id) throws Exception;
    public void update(Atendimento a) throws Exception;
    public void delete(Atendimento a) throws Exception;
    ObservableList<Atendimento> listDoDia();
}
