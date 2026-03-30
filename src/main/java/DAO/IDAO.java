package DAO;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;

import java.util.ArrayList;

interface IDAO <T>{
    String DB_JDBC_DRIVER = "org.h2.Driver";
    String DB_JDBC_URL = "jdbc:h2:./BD_tp;AUTO_SERVER=TRUE;CLOSE_DELAY=1;DB_CLOSE_ON_EXIT=FALSE";
    String DB_JDBC_USER = "sa";
    String DB_JDBC_PASS = "";

    void guardar(T elemento) throws DAOException;
    void modificar(T elemento) throws DAOException;
    void eliminar(int dni) throws DAOException, JdbcSQLIntegrityConstraintViolationException;
    T buscar(int dni) throws DAOException;

    ArrayList<T> buscarTodos() throws DAOException;
}