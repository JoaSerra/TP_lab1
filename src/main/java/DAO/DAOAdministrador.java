package DAO;

import model.Administrador;
import java.sql.*;
import java.util.ArrayList;

public class DAOAdministrador extends DAOAbstract<Administrador>{
    String valores = null;
    Administrador elemento = null;

    @Override
    public String getTabla(){
        return "ADMINISTRADOR";
    }

    @Override
    public String getFinder(){
        return "DNI";
    }

    @Override
    public String valoresInsert(){
        valores = "(" +elemento.getDni()+
                "," + elemento.getNombre()+
                "," + elemento.getApellido()+
                "," + elemento.getTelefono()+
                ")";

        return valores;
    }

    @Override
    public String valoresUpdate(){
        valores = "NOMBRE="+ elemento.getNombre() +
                ",APELLIDO="+ elemento.getApellido() +
                ",TELEFONO="+ elemento.getTelefono();

        return valores;
    }

    @Override
    public Administrador cargarObjeto(ResultSet rs) throws SQLException {
        elemento = new Administrador();

        elemento.setDni(rs.getInt("DNI"));
        elemento.setNombre(rs.getString("NOMBRE"));
        elemento.setApellido(rs.getString("APELLIDO"));
        elemento.setTelefono(rs.getString("TELEFONO"));

        return elemento;
    }

    @Override
    public ArrayList<Administrador> cargarLista(ResultSet rs) throws SQLException {
        ArrayList<Administrador> lista = new ArrayList<>();

        while (rs.next()){
            elemento = new Administrador();
            elemento.setDni(rs.getInt("DNI"));
            elemento.setNombre(rs.getString("NOMBRE"));
            elemento.setApellido(rs.getString("APELLIDO"));
            elemento.setTelefono(rs.getString("TELEFONO"));
            lista.add(elemento);
        }
        return lista;
    }
}
