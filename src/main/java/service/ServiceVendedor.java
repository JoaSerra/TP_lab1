package service;

import DAO.DAOException;
import DAO.DAOVendedor;
import model.Vendedor;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;

import java.util.ArrayList;

public class ServiceVendedor {
    private final DAOVendedor daoVendedor;

    public ServiceVendedor(){
        daoVendedor = new DAOVendedor();
    }

    public void guardar(Vendedor vendedor) throws ServiceExeption {
        try {
            daoVendedor.guardar(vendedor);
            System.out.println("Vendedor registrado");
        } catch(DAOException e) {
            throw new ServiceExeption(e.getMessage());
        }
    }

    public void modificar(Vendedor vendedor) throws ServiceExeption {
        try{
            daoVendedor.modificar(vendedor);
        } catch(DAOException e) {
            throw new ServiceExeption(e.getMessage());
        }
    }

    public Vendedor buscarVendedor(int dni) throws ServiceExeption{
        Vendedor vendedor;
        try{
            vendedor = daoVendedor.buscar(dni);
            return vendedor;
        } catch(DAOException e){
            throw new ServiceExeption(e.getMessage());
        }
    }

    public void eliminar(int dni) throws ServiceExeption, JdbcSQLIntegrityConstraintViolationException{
        try{
            daoVendedor.eliminar(dni);
            System.out.println("Vendedor eliminado");
        }catch (DAOException e){
            throw new ServiceExeption(e.getMessage());
        }
    }

    public ArrayList<Vendedor> todosLosVendedores() throws ServiceExeption {
        ArrayList<Vendedor> vendedors;
        try {
            vendedors = daoVendedor.buscarTodos();
            return vendedors;
        }catch (DAOException e){
            throw new ServiceExeption(e.getMessage());
        }
    }
}
