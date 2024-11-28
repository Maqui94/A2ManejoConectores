package DAO;

import database.DBConnection;
import database.SchemaDB;
import model.Coche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
            preparedStatement.setInt(1,id);
            int row = preparedStatement.executeUpdate();
            System.out.println("El numero de registro borrados es de " + row);
        } catch (SQLException e) {
            System.out.println("Error en la creacion query");
        }
    }
    public Coche consultarCoche(int id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + SchemaDB.TAB_CH + " WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return new Coche(
                        resultSet.getInt(SchemaDB.COL_CH_ID),
                        resultSet.getString(SchemaDB.COL_CH_MAT),
                        resultSet.getString(SchemaDB.COL_CH_MAR),
                        resultSet.getString(SchemaDB.COL_CH_MOD),
                        resultSet.getString(SchemaDB.COL_CH_COL)
                );
            }
        } catch (SQLException e) {
            System.out.println("Error en la creacion query");
        }
        return null;
    }
    public void modificarCoche(Coche coche){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + SchemaDB.TAB_CH + " SET matricula = ?, marca = ?, modelo = ?, color = ? WHERE id = ?");
            preparedStatement.setString(1, coche.getMatricula());
            preparedStatement.setString(2, coche.getMarca());
            preparedStatement.setString(3, coche.getModelo());
            preparedStatement.setString(4, coche.getColor());
            preparedStatement.setInt(5, coche.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error en la creacion query");
        }

    }
    public ArrayList<Coche> flota() {
        ArrayList<Coche> coches = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement("SELECT * FROM "+ SchemaDB.TAB_CH);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Coche coche = new Coche(
                        resultSet.getInt(SchemaDB.COL_CH_ID),
                        resultSet.getString(SchemaDB.COL_CH_MAT),
                        resultSet.getString(SchemaDB.COL_CH_MAR),
                        resultSet.getString(SchemaDB.COL_CH_MOD),
                        resultSet.getString(SchemaDB.COL_CH_COL)
                );
                coches.add(coche);
            }
        } catch (SQLException e) {
            System.out.println("Error query");;
        }
        return coches;
    }
}
