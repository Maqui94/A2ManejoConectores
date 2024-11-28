package DAO;

import database.DBConnection;
import database.SchemaDB;
import model.Gestion;
import model.Pasajero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GestionDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    public GestionDAO(){
        connection=new DBConnection().getConnection();
    }
    public void añadirPasajeroACoche(Gestion gestion)
            throws SQLException {
        String query = String.format("INSERT into %s (%s,%s) VALUES (?,?)",
                SchemaDB.TAB_GES, SchemaDB.COL_GES_IDC, SchemaDB.COL_GES_IDP);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, gestion.getId_coche());
        preparedStatement.setInt(2, gestion.getId_pasajero());
        preparedStatement.execute();
    }
    public void quitarPasajeroDeCoche(int id_coche,int id_pasajero) {
        String query = "DELETE FROM "+SchemaDB.TAB_GES+ " WHERE id_coche=? AND id_pasajero=?";
        try {
             PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_coche);
            preparedStatement.setInt(2, id_pasajero);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int PasajerosEnCoche(int idCoche) {
        ArrayList<Pasajero> pasajerosCoche = new ArrayList<>();
        String query = " SELECT p.* FROM pasajeros p JOIN "+SchemaDB.TAB_GES+" cp ON p.id = cp.id_pasajero WHERE cp.id_coche = ?";
        try {
             PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idCoche);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                pasajerosCoche.add(new Pasajero(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getInt("edad"),
                        resultSet.getFloat("peso")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pasajerosCoche.size();
    }
}
