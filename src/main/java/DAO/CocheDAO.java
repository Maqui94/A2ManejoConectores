package DAO;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CocheDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    public CocheDAO(){
        connection=new DBConnection().getConnection();
    }
}
