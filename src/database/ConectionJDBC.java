package database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class ConectionJDBC implements Serializable{
	
	// init database constants
		private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
		private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/library";
		private static final String USERNAME = "root";
		private static final String PASSWORD = "1234";
		private static final String MAX_POOL = "250"; // set your own limit

		private static final long serialVersionUID = 4971075361003058584L;

		// init connection object
		private Connection connection;
		// init properties object
		private Properties properties;

		// create properties
		private Properties getProperties() {
			if (properties == null) {
				properties = new Properties();
				properties.setProperty("user", USERNAME);
				properties.setProperty("password", PASSWORD);
				properties.setProperty("MaxPooledStatements", MAX_POOL);
				properties.setProperty("database_name", "library");
			}
			return properties;
		}

		// Conecta a la base de datos
		public Connection connect() {
			if (connection == null) {
				try {
					Class.forName(DATABASE_DRIVER);
					connection = DriverManager.getConnection(DATABASE_URL, getProperties());
				} catch (ClassNotFoundException | SQLException e) {
					// Java 7+
					e.printStackTrace();
				}
			}
			return connection;
		}

		// "Desconecta" de la bd
		public void disconnect() {
			if (connection != null) {
				try {
					connection.close();
					connection = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		public ResultSet execSQL(String sql) {

			connect();
			ResultSet rs = null;

			try {

				PreparedStatement statement = connection.prepareStatement(sql);
				statement.execute();

				rs = statement.getResultSet();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// disconnect();
			}

			return rs;

		}
}
