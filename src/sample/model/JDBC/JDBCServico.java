package sample.model.JDBC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Cliente;
import sample.model.FabricaConexao;
import sample.model.Servico;
import sample.model.interfaces.ServicoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCServico  implements ServicoDAO {

    private static JDBCServico instance;
    private ObservableList<Servico> lista;

    private JDBCServico(){
        lista = FXCollections.observableArrayList();
    }

    public static JDBCServico getInstance(){
        if(instance==null){
            instance = new JDBCServico();
        }
        return instance;
    }


    private Servico montaServico(ResultSet rs)throws SQLException {
        int id_servico = rs.getInt("id_servico");
        String nome = rs.getString("nome");
        Double valor = rs.getDouble("valor");
        Servico u = new Servico (id_servico,nome,valor);

        return u;
    }
    @Override
    public void create(Servico s) throws Exception {
        String sql = "insert into servico(nome,valor) values (?,?)";

        Connection c = FabricaConexao.getConnection();
        PreparedStatement pstm = c.prepareStatement(sql);
        pstm.setString(1,s.getNome());
        pstm.setDouble(2,s.getValor());
        pstm.execute();
        pstm.close();
        c.close();
    }

    @Override
    public ObservableList<Servico> list() {
        lista.clear();
        try {
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm
                    .executeQuery("select *from servico;");

            while (rs.next()){
                lista.add(montaServico(rs));
            }

            rs.close();
            stm.close();
            c.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Servico search(int id) throws Exception {
        Connection c = FabricaConexao.getConnection();
        String sql = "select * from servico where id_servico=?";

        PreparedStatement pstm = c.prepareStatement(sql);
        pstm.setInt(1,id);
        ResultSet rs = pstm.executeQuery();
        Servico ca = null;
        while(rs.next()){
            ca = montaServico(rs);
        }
        rs.close();
        pstm.close();
        c.close();
        return ca;
    }

    @Override
    public void update(Servico s, Double valor) throws Exception {
        try {
            String sql = "update servico set valor=?  where id_servico =? ;";

            Connection c = FabricaConexao.getConnection();
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setDouble(1,valor);
            pstm.setInt(2,s.getId_Servico());

            pstm.execute();
            pstm.close();
            c.close();
        }catch (SQLException e){
            e.getMessage();
        }
    }

    @Override
    public void delete(Servico s) {

        try {
            String sql = "delete from servico where id_servico =?;";

            Connection c = FabricaConexao.getConnection();
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setInt(1,s.getId_Servico());

            pstm.execute();
            pstm.close();
            c.close();
        }catch (SQLException e){
            e.getMessage();
        }

    }
}
