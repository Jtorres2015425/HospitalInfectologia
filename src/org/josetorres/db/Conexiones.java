/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josetorres.db;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.jdbc.Driver;

        
        
/**
 *
 * @author programacion
 */
public class Conexiones {
    private Connection conexion;
    private static Conexiones instancia;
    
    private String driver;
    private String url;
    private String usuario;
    private String pasword;
    private String bdname;



public Conexiones(){
String dbname = "HospitalInfectologiaIN5BM2015425";
String url = "jdbc:mysql://localhost:3306/"+dbname+"?useSSL=false&zeroDateTimeBehavior=convertToNull";
String driver = "com.mysql.jdbc.Driver";
String usuario = "root";
String pasword = "admin";

try{
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    conexion = DriverManager.getConnection(url,usuario,pasword);
    }catch (ClassNotFoundException e){
        e.printStackTrace();
        
    }catch (InstantiationException e){
        e.printStackTrace();
    }catch (SQLException e){
        e.printStackTrace();
    }catch (IllegalAccessException e){
        e.printStackTrace();
}
}

public static Conexiones getInstancia(){
    if(instancia == null){
        instancia = new Conexiones();
    }
    return instancia;
}

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }


}