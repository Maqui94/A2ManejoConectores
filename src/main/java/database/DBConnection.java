package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    public Connection getConnection(){
        if(connection==null){
            newConnection();
        }
        return connection;
    }

    private void newConnection() {
        String url="jdbc:mysql://localhost:3306/"+SchemaDB.DB_NAME;
        try {
            connection= DriverManager.getConnection(url,"root","");
        } catch (SQLException e) {
            System.out.println("Conexion incorrecta");
        }
        System.out.println("Conexion creada");
    }
    public void closeConnection(){
        try{
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexion");
        } finally {
            connection=null;
        }
    }
}

