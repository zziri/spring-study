package com.zziri.study;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Hello world!!");

        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.h2.Driver");
            String url = "jdbc:h2:mem:test;MODE=MySQL;";
            connection = DriverManager.getConnection(url, "sa", "");
            statement = connection.createStatement();

            connection.setAutoCommit(false);

            statement.execute("create table member(id int auto_increment, username varchar(255) not null, password varchar(255) not null, primary key(id))");

            statement.executeUpdate("insert into member(username, password) values('jihoon', '1234')");

            ResultSet resultSet = statement.executeQuery("select id, username, password from member");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                logger.info(String.format("id: %d, username : %s, password : %s", id, username, password));
            }

            connection.commit();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}