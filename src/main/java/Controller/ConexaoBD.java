/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConexaoBD {
    static Connection conn = null;
    static String url = "jdbc:mysql://localhost:3306/mydb";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = "root";
    static String senha = "2004Gu$tavo";

    public static void main(String[] args){
        try{
            System.out.println("Carregando o driver...");
            Class.forName(driver);
            System.out.println("Driver carregado com sucesso!");
        }catch(Exception e){
            System.out.println("Falha no carregamento!");
    }

        try{
            System.out.println("Tentando conectar o BD...");
            conn = DriverManager.getConnection(url,user,senha);
            System.out.println("BD conectado com sucesso!");
        }catch(Exception e){
            System.out.println("Falha no carregamento!");
        }
    }
}

