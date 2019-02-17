package sample.model.interfaces;
import sample.model.JDBC.JDBCServico;
import sample.model.Servico;

import java.util.List;

public interface ServicoDAO {
    public void create(Servico s) throws Exception;
    public List<Servico> list();
    public Servico search(int id) throws Exception;
    public void update(Servico s, Double valor) throws Exception;
    public void delete(Servico s) throws Exception;
}
