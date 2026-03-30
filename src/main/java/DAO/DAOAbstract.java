package DAO;
import service.ServiceExeption;

import java.sql.*;
import java.util.ArrayList;

public abstract class DAOAbstract<T> {
	String DB_JDBC_DRIVER = "org.h2.Driver";
	String DB_JDBC_URL = "jdbc:h2:./BD_tp;AUTO_SERVER=TRUE;CLOSE_DELAY=1;DB_CLOSE_ON_EXIT=FALSE";
	String DB_JDBC_USER = "sa";
	String DB_JDBC_PASS = "";

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	String tabla = getTabla();
	String finder = getFinder(); //Finder es la columna por la que se busca un elemento (dni , nro_entrada)

	public void guardar() throws DAOException {
		try {
			Class.forName(DB_JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_JDBC_URL, DB_JDBC_USER, DB_JDBC_PASS);

			String valores = valoresInsert();
			preparedStatement = connection.prepareStatement("INSERT INTO " +tabla+ " VALUES" +valores); //

			preparedStatement.executeUpdate();

			connection.close();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Error al acceder a la base de datos");
		} catch (ClassNotFoundException e) {
			throw new DAOException();
		}
	}

	public void modificar(int id) throws DAOException {
		try {
			Class.forName(DB_JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_JDBC_URL, DB_JDBC_USER, DB_JDBC_PASS);

			String valores = valoresUpdate();
			preparedStatement = connection.prepareStatement("UPDATE " +tabla+ " SET " +valores+ "WHERE " + finder + " = " + id);

			preparedStatement.executeUpdate();

			connection.close();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Error al acceder a la base de datos");
		} catch (ClassNotFoundException e) {
			throw new DAOException();
		}
	}

	public void eliminar(int id) throws DAOException {
		try {
			Class.forName(DB_JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_JDBC_URL, DB_JDBC_USER, DB_JDBC_PASS);

			preparedStatement = connection.prepareStatement("DELETE FROM" +tabla + "WHERE " + finder + "=" + id);

			preparedStatement.executeUpdate();

			connection.close();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Error al acceder a la base de datos");
		} catch (ClassNotFoundException e) {
			throw new DAOException();
		}
	}

	public T buscar(int id) throws DAOException {
		T resultado = null;

		try {
			Class.forName(DB_JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_JDBC_URL,DB_JDBC_USER,DB_JDBC_PASS);

			preparedStatement = connection.prepareStatement("SELECT * FROM " + tabla + "WHERE " + finder + " = " + id);

			ResultSet rs = preparedStatement.executeQuery();
			
			resultado = cargarObjeto(rs);
			
			connection.close();
			
			return resultado;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Error al acceder a la base de datos");
		} catch (ClassNotFoundException | ServiceExeption e) {
			throw new DAOException();
		}
	}

	public ArrayList<T> buscarTodos() throws DAOException {
		ArrayList<T> lista = new ArrayList<>();
		try {
			Class.forName(DB_JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_JDBC_URL,DB_JDBC_USER,DB_JDBC_PASS);

			preparedStatement = connection.prepareStatement("SELECT * FROM " + tabla);

			ResultSet rs = preparedStatement.executeQuery();

			lista = cargarLista(rs);

			connection.close();

			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Error al acceder a la base de datos");
		} catch (ClassNotFoundException | ServiceExeption e) {
			throw new DAOException();
		}
	}



	public abstract String getTabla();
	public abstract String getFinder();
	public abstract String valoresInsert();
	public abstract String valoresUpdate();
	public abstract T cargarObjeto(ResultSet rs) throws SQLException, ServiceExeption;
	public abstract ArrayList<T> cargarLista(ResultSet rs) throws SQLException, ServiceExeption;
	
}
