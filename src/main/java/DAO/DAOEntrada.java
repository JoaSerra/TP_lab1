package DAO;

import model.Administrador;
import model.Entrada;
import service.ServiceExeption;

import java.sql.*;
import java.util.ArrayList;

public class DAOEntrada extends DAOAbstract<Entrada> {
    String valores = null;
    Entrada elemento = null;

    @Override
    public String getTabla(){
        return "ENTRADA";
    }

    @Override
    public String getFinder(){
        return "NRO_ENTRADA";
    }

    @Override
    public String valoresInsert(){
        valores = "(" +elemento.getNroEntrada()+
                "," + elemento.getEspectaculo().getIdEspectaculo()+
                "," + elemento.getPrecio()+
                "," + elemento.getUbicacion().getIdUbi()+
                ")";

        return valores;
    }

    @Override
    public String valoresUpdate(){
        valores = "ID_ESPECTACULO="+ elemento.getEspectaculo().getIdEspectaculo() +
                ",PRECIO="+ elemento.getPrecio() +
                ",UBICACION="+ elemento.getUbicacion().getIdUbi();

        return valores;
    }

    @Override
    public Entrada cargarObjeto(ResultSet rs) throws SQLException, ServiceExeption {
        elemento = new Entrada();

        elemento = new Entrada();
        elemento.setNroEntrada(rs.getInt("NRO_ENTRADA"));
        elemento.setEspectaculoId(rs.getInt("ID_ESPECTACULO"));
        elemento.setPrecio(rs.getDouble("PRECIO"));
        elemento.setUbicacionId(rs.getInt("UBICACION"));

        return elemento;
    }

    @Override
    public ArrayList<Entrada> cargarLista(ResultSet rs) throws SQLException, ServiceExeption {
        ArrayList<Entrada> lista = new ArrayList<>();

        while (rs.next()){
            elemento = new Entrada();
            elemento.setNroEntrada(rs.getInt("NRO_ENTRADA"));
            elemento.setEspectaculoId(rs.getInt("ID_ESPECTACULO"));
            elemento.setPrecio(rs.getDouble("PRECIO"));
            elemento.setUbicacionId(rs.getInt("UBICACION"));
            lista.add(elemento);
        }
        return lista;
    }


}
