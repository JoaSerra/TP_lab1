package DAO;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;

import java.util.ArrayList;

interface IDAO <T>{

    String DB_JDBC_DRIVER = "org.h2.Driver";
    String DB_JDBC_URL = "jdbc:h2:./BD_tp;AUTO_SERVER=TRUE;CLOSE_DELAY=1;DB_CLOSE_ON_EXIT=FALSE";
    String DB_JDBC_USER = "sa";
    String DB_JDBC_PASS = "";

    void guardar(T elemento) throws DAOExeption;
    void modificar(T elemento) throws DAOExeption;
    void eliminar(int dni) throws DAOExeption, JdbcSQLIntegrityConstraintViolationException;
    T buscar(int dni) throws DAOExeption;

    ArrayList<T> buscarTodos() throws DAOExeption;
}