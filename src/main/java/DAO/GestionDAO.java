package DAO;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class GestionDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    public GestionDAO(){
        connection=new DBConnection().getConnection();
    }
}
