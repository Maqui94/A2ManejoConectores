package DAO;

import database.DBConnection;
import database.SchemaDB;
import model.Coche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CocheDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public CocheDAO() {
        connection = new DBConnection().getConnection();
    }

    public void añadirCoche(Coche coche) throws SQLException {
        String query = String.format("INSERT into %s (%s,%s,%s,%s) VALUES (?,?,?,?)",
                SchemaDB.TAB_CH, SchemaDB.COL_CH_MAT, SchemaDB.COL_CH_MAR, SchemaDB.COL_CH_MOD, SchemaDB.COL_CH_COL);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, coche.getMatricula());
        preparedStatement.setString(2, coche.getMarca());
        preparedStatement.setString(3, coche.getModelo());
        preparedStatement.setString(4, coche.getColor());
        preparedStatement.execute();

    }

    public void borrarCoche(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + SchemaDB.TAB_CH + " WHERE id=?");
            preparedStatement.setInt(1, 1);
            // Podria ser return preparadStatement.executeUpdate();
            int row = preparedStatement.executeUpdate();
            System.out.println("El numero de registro borrados es de " + row);
        } catch (SQLException e) {
            System.out.println("Error en la creacion query");
        }
    }
}
