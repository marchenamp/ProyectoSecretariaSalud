/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author march
 */
public class Conexion {

    private String USERNAME;
    private String PASSWORD;
    private String HOST;
    private String PORT;
    private String DATABASE;
    private String CLASSNAME = "com.mysql.cj.jdbc.Driver";
    private String URL;
    private Connection con;

    public Conexion() {
        // Obtener las variables de entorno
        USERNAME = System.getenv("DATABASE_USER");
        PASSWORD = System.getenv("DATABASE_PASSWORD");
        HOST = System.getenv("DATABASE_HOST");
        PORT = System.getenv("DATABASE_PORT");
        DATABASE = System.getenv("DATABASE_NAME");

        // Construir la URL de conexión
        URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;

        try {
            Class.forName(CLASSNAME);
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Error en: " + e);
        } catch (SQLException e) {
            System.err.println("Error en: " + e);
        }
    }

    public Connection getConexion() {
        return con;
    }
}
