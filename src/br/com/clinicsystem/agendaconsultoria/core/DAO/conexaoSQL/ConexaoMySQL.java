package br.com.clinicsystem.agendaconsultoria.core.DAO.conexaoSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {

    private static final String url = "jdbc:mysql://localhost:3306/clinicSystem";
    private static final String user = "root";
    private static final String password = "root";

    private static Connection connection;

    public static Connection getConnection(){

        try {

            if (connection == null){
                connection = DriverManager.getConnection(url, user, password);
                return connection;
            } else {
                return connection;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

}
