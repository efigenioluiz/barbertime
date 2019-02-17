package sample.model.JDBC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Atendimento;
import sample.model.Cliente;
import sample.model.FabricaConexao;
import sample.model.Servico;
import sample.model.interfaces.AtendimentoDAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.Collections;

public class JDBCAtendimento implements AtendimentoDAO {
    private static JDBCAtendimento instance;
    private ObservableList<Atendimento> lista;

    private JDBCAtendimento(){
        lista = FXCollections.observableArrayList();
    }

    public static JDBCAtendimento getInstance(){
        if(instance==null){
            instance = new JDBCAtendimento();
        }
        return instance;
    }

    private Atendimento montaAtendimento(ResultSet rs) throws Exception {
        int id_atendimento = rs.getInt("id_atendimento");
        String data_atendimento= rs.getString("data_atendimento");
        int fk_cliente= rs.getInt("fk_cliente");
        int fk_servico= rs.getInt("fk_servico");
        String hora_atendimento= rs.getString("hora_atendimento");
        Boolean estado= rs.getBoolean("estado");


        Cliente cliente= JDBCCliente.getInstance().search(fk_cliente);
        Servico servico= JDBCServico.getInstance().search(fk_servico);

        Atendimento atendimento= new Atendimento(id_atendimento,data_atendimento,cliente,hora_atendimento,servico,estado);

        return atendimento;
    }


    @Override
    public void create(Atendimento a, Cliente cliente, Servico servico) throws SQLException {
    String sql = "insert into atendimento(data_atendimento,fk_cliente,hora_atendimento,fk_servico,estado) values (?,?,?,?,?)";

        Connection c = FabricaConexao.getConnection();
        PreparedStatement pstm = c.prepareStatement(sql);
        pstm.setString(1,a.getDataAtendimento());
        pstm.setInt(2,cliente.getId());
        pstm.setString(3,a.getHoraAtendimento());
        pstm.setInt(4,servico.getId_Servico());
        pstm.setBoolean(5,false);
        pstm.execute();
        pstm.close();
        c.close();

    }

    @Override
    public ObservableList<Atendimento> list() {
        lista.clear();
        try {
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm
                    .executeQuery("select *from atendimento;");

            while (rs.next()){
                lista.add(montaAtendimento(rs));
            }

            rs.close();
            stm.close();
            c.close();
        }catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Atendimento search(int id) throws Exception {
        Connection c = FabricaConexao.getConnection();
        String sql = "select * from atendimento where id_atendimento=?";

        PreparedStatement pstm = c.prepareStatement(sql);
        pstm.setInt(1,id);
        ResultSet rs = pstm.executeQuery();
        Atendimento ca = null;
        while(rs.next()){
            ca = montaAtendimento(rs);
        }
        rs.close();
        pstm.close();
        c.close();
        return ca;
    }

    @Override
    public void update(Atendimento a) throws Exception {
        try {
            String sql = "UPDATE `atendimento` SET data_atendimento=?,fk_cliente=? ,fk_servico=?, hora_atendimento=? WHERE id_atendimento=?";

            Connection c = FabricaConexao.getConnection();
            PreparedStatement pstm = c.prepareStatement(sql);

            pstm.setString(1,a.getDataAtendimento());
            pstm.setInt(2,a.getCliente().getId());
            pstm.setInt(3,a.getServico().getId_Servico());
            pstm.setString(4,a.getHoraAtendimento());
            pstm.setInt(5,a.getId_atendimento());

            pstm.execute();
            pstm.close();
            c.close();
        }catch (SQLException e){
            e.getMessage();
        }
    }

    @Override
    public void delete(Atendimento a) throws Exception {

        try {
            String sql = "DELETE FROM atendimento WHERE id_atendimento =? ";

            Connection c = FabricaConexao.getConnection();
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setInt(1,a.getId_atendimento());
            pstm.execute();
            pstm.close();
            c.close();
        }catch (SQLException e){
            e.getMessage();
        }
    }

    @Override
    public ObservableList<Atendimento> listDoDia() {
        lista.clear();
        try {
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm
                    .executeQuery("select *from atendimento where data_atendimento='"+LocalDate.now()+"' and estado=false ORDER BY `atendimento`.`hora_atendimento` ASC");

            while (rs.next()){
                lista.add(montaAtendimento(rs));
            }

            rs.close();
            stm.close();
            c.close();
        }catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ObservableList<Atendimento> listDoDiaSearch(LocalDate data) {
        lista.clear();
        try {
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm
                    .executeQuery("select *from atendimento where data_atendimento='"+data+"' ORDER BY `atendimento`.`hora_atendimento` ASC");

            while (rs.next()){
                lista.add(montaAtendimento(rs));
            }

            rs.close();
            stm.close();
            c.close();
        }catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    public void updateEstado(Atendimento a){
        try {
            String sql = "UPDATE `atendimento` SET estado=? WHERE id_atendimento=?";

            Connection c = FabricaConexao.getConnection();
            PreparedStatement pstm = c.prepareStatement(sql);

            pstm.setBoolean(1,true);
            pstm.setInt(2,a.getId_atendimento());

            pstm.execute();
            pstm.close();
            c.close();
        }catch (SQLException e){
            e.getMessage();
        }
    }

    public ObservableList<Atendimento> listDoDiaEstado() {
        ObservableList<Atendimento> listinha= FXCollections.observableArrayList();
        listinha.clear();
        try {
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm
                    .executeQuery("select *from atendimento where data_atendimento='"+LocalDate.now()+"' && estado="+true+" ORDER BY `atendimento`.`hora_atendimento` ASC");
            while (rs.next()){
                listinha.add(montaAtendimento(rs));
            }

            rs.close();
            stm.close();
            c.close();
        }catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listinha;
    }
}
