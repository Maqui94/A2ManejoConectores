package DAO;

import database.DBConnection;
import database.SchemaDB;
import model.Coche;
import model.Pasajero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PasajeroDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public PasajeroDAO() {
        connection = new DBConnection().getConnection();
    }
    public void añadirPasajero(Pasajero pasajero) throws SQLException {
        String query = String.format("INSERT into %s (%s,%s,%s) VALUES (?,?,?)",
                SchemaDB.TAB_PAS, SchemaDB.COL_PAS_NAME, SchemaDB.COL_PAS_AGE, SchemaDB.COL_PAS_PESO);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, pasajero.getNombre());
        preparedStatement.setInt(2, pasajero.getEdad());
        preparedStatement.setDouble(3, pasajero.getPeso());
        preparedStatement.execute();

    }

    public void borrarPasajero(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + SchemaDB.TAB_PAS + " WHERE id=?");
            preparedStatement.setInt(1,id);
            int row = preparedStatement.executeUpdate();
            System.out.println("El numero de registro borrados es de " + row);
        } catch (SQLException e) {
            System.out.println("Error en la creacion query");
        }
    }
    public Pasajero consultarPasajero(int id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + SchemaDB.TAB_PAS + " WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return new Pasajero(
                        resultSet.getInt(SchemaDB.COL_PAS_ID),
                        resultSet.getString(SchemaDB.COL_PAS_NAME),
                        resultSet.getInt(SchemaDB.COL_PAS_AGE),
                        resultSet.getDouble(SchemaDB.COL_PAS_PESO)
                );
            }
        } catch (SQLException e) {
            System.out.println("Error en la creacion query");
        }
        return null;
    }
    public ArrayList<Pasajero> listaPasajeros() {
        ArrayList<Pasajero> pasajeros = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement("SELECT * FROM "+ SchemaDB.TAB_PAS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Pasajero pasajero = new Pasajero(
                        resultSet.getInt(SchemaDB.COL_PAS_ID),
                        resultSet.getString(SchemaDB.COL_PAS_NAME),
                        resultSet.getInt(SchemaDB.COL_PAS_AGE),
                        resultSet.getDouble(SchemaDB.COL_PAS_PESO)
                );
                pasajeros.add(pasajero);
            }
        } catch (SQLException e) {
            System.out.println("Error query");;
        }
        return pasajeros;
    }

}

