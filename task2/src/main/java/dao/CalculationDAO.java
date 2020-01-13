package dao;

import libs.DbConnection;
import libs.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CalculationDAO implements DAO<Result>{
    private List<Result> database;
    private Connection connection;

    public CalculationDAO() {
        connection = DbConnection.getConnection();
        read();
    }

    @Override
    public List<Result> getDatabase() {
        read();
        return database;
    }

    @Override
    public void read() {
        try {
            database = new LinkedList<>();

            final String SQLQ = "SELECT * FROM calculations";
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQ);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                database.add(new Result(
                        resultSet.getString("firstNumber"),
                        resultSet.getString("secondNumber"),
                        resultSet.getString("operation"),
                        resultSet.getString("result")
                ));
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void add(Result value) {
        try {
            final String SQLQ = "INSERT INTO calculations(firstnumber, secondnumber, operation, result) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQ);
            preparedStatement.setString(1,value.getFirstNumber());
            preparedStatement.setString(2,value.getSecondNumber());
            preparedStatement.setString(3,value.getOperation());
            preparedStatement.setString(4,value.getResult());
            preparedStatement.execute();
            database.add(value);
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
