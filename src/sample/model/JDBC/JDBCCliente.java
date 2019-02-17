package sample.model.JDBC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Cliente;
import sample.model.FabricaConexao;
import sample.model.interfaces.ClienteDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCCliente implements ClienteDAO {

    private static JDBCCliente instance;
    private ObservableList<Cliente> lista;

    private JDBCCliente(){
        lista = FXCollections.observableArrayList();
    }

    public static JDBCCliente getInstance(){
        if(instance==null){
            instance = new JDBCCliente();
        }
        return instance;
    }

    private Cliente montaCliente(ResultSet rs)throws SQLException {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String telefone = rs.getString("telefone");
        String sexo = rs.getString("sexo");
        String cpf = rs.getString("cpf");
        String data_nascimento = rs.getString("data_nascimento");

        Cliente u = new Cliente(id,nome,cpf,telefone,sexo,data_nascimento);

        return u;
    }

    @Override
    public void create(Cliente cl) throws Exception {
        String sql = "insert into cliente(nome,telefone,sexo,cpf,data_nascimento) values ('"+cl.getNome()+"','"+cl.getTelefone()+"','"+cl.getSexo()+"','"+cl.getCpf()+"','"+cl.getDataNascimento()+"' )";

        Connection c = FabricaConexao.getConnection();
        PreparedStatement pstm = c.prepareStatement(sql);

        pstm.execute();
        pstm.close();
        c.close();

    }

    @Override
    public ObservableList<Cliente> list() {
        lista.clear();
        try {
            Connection c = FabricaConexao.getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm
                    .executeQuery("select *from cliente;");

            while (rs.next()){
                lista.add(montaCliente(rs));
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
    public Cliente search(int id) throws Exception {
        Connection c = FabricaConexao.getConnection();
        String sql = "select * from cliente where id=?";

        PreparedStatement pstm = c.prepareStatement(sql);
        pstm.setInt(1,id);
        ResultSet rs = pstm.executeQuery();
        Cliente ca = null;
        while(rs.next()){
            ca = montaCliente(rs);
        }
        rs.close();
        pstm.close();
        c.close();
        return ca;
    }

    @Override
    public void update(Cliente c) throws Exception {/*
        Connection c = FabricaConexao.getConnection();
        Statement stm = c.createStatement();
        ResultSet rs = stm
                .executeQuery("update despesas set status=true  where id_despesa="+d.getId()+" and fk_usuario='"+u.getId()+"';");
        rs.close();
        stm.close();
        c.close();*/

    }

    @Override
    public void delete(Cliente cl) throws Exception {


        try {
            String sql = "DELETE FROM cliente WHERE id =? ";

            Connection c = FabricaConexao.getConnection();
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setInt(1,cl.getId());
            pstm.execute();
            pstm.close();
            c.close();
        }catch (SQLException e){
            e.getMessage();
        }
    }
}
