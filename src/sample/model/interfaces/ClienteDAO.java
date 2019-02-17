package sample.model.interfaces;

import sample.model.Cliente;

import java.util.List;

public interface ClienteDAO {
    public void create(Cliente c) throws Exception;
    public List<Cliente> list();
    public Cliente search(int id) throws Exception;
    public void update(Cliente c) throws Exception;
    public void delete(Cliente c) throws Exception;
}
