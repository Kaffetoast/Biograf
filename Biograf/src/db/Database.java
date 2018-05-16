package db;

import java.sql.*;

public class Database {

    private Connection connection;

    public Database() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println (e);
            System.exit (-1);
        }
    }
    public ResultSet query(String queryString) {

        connect();
        // execute query
        Statement statement = null;
        try {
            statement = connection.createStatement();
            return statement.executeQuery(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
          return null;
    }

    public void insert(String queryString) {

        connect();
        // execute query
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

    }

    public boolean connect() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "abc");

        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
