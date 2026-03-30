package service;

import DAO.DAOEspectaculo;
import DAO.DAOException;
import model.Espectaculo;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;

import java.util.ArrayList;

public class ServiceEspectaculo {
    private final DAOEspectaculo daoEspectaculo;

    public ServiceEspectaculo(){
        daoEspectaculo = new DAOEspectaculo();
    }

    public void guardar(Espectaculo espectaculo) throws ServiceExeption {
        try {
            daoEspectaculo.guardar(espectaculo);
            System.out.println("Espectaculo guardado exitosamente");
        } catch(DAOException e) {
            throw new ServiceExeption(e.getMessage());
        }
    }

    public void modificar(Espectaculo espectaculo) throws ServiceExeption {
        try{
            daoEspectaculo.modificar(espectaculo);
        } catch(DAOException e) {
            throw new ServiceExeption(e.getMessage());
        }
    }

    public Espectaculo buscarEspectaculo(int id) throws ServiceExeption{
        Espectaculo espectaculo;
        try{
            espectaculo = daoEspectaculo.buscar(id);
            return espectaculo;
        } catch(DAOException e){
            throw new ServiceExeption(e.getMessage());
        }
    }

    public void eliminar(int id) throws ServiceExeption, JdbcSQLIntegrityConstraintViolationException {
        try{
            daoEspectaculo.eliminar(id);
            System.out.println("Espectaculo eliminado exitosamente");
        }catch (DAOException e){
            throw new ServiceExeption(e.getMessage());
        }
    }

    public ArrayList<Espectaculo> todosLosEspectaculos() throws ServiceExeption {
        ArrayList<Espectaculo> espectaculos;
        try {
            espectaculos = daoEspectaculo.buscarTodos();
            return espectaculos;
        }catch (DAOException e){
            throw new ServiceExeption(e.getMessage());
        }
    }
}
