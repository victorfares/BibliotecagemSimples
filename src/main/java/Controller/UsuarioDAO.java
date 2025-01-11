/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class UsuarioDAO {
    static Connection conn = null;
    static String url = "jdbc:mysql://localhost:3306/mydb";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = "root";
    static String senha = "2004Gu$tavo";
    
    public void inserirU(Usuario us){
        PreparedStatement ps = null;
        
        String sql1 = "INSERT INTO usuario (Us_rg, Us_nome)" + "VALUES(?, ?)";
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,senha);
            System.out.println("Inserindo dados...");
            
            ps = conn.prepareStatement(sql1);
            ps.setInt(1, us.getRg());
            ps.setString(2, us.getNome());
            
            ps.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");
            ps.close();
            conn.close();
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    public void ExcluirU(int rg){
        PreparedStatement ps = null;
        
        String sql2 = "DELETE FROM usuario WHERE Us_rg = ?";
        
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,senha);
            
            ps = conn.prepareStatement(sql2);
            ps.setInt(1, rg);
            
            ps.executeUpdate();
            System.out.println("Dados excluidos");
            ps.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Usuario Excluido com sucesso", "Exclusao de usuarios", 1);
            
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    public boolean buscarU(int rg) {
        Usuario us = null;
        String sql3 = "SELECT * FROM usuario WHERE Us_rg = ?";

        try{
            conn = DriverManager.getConnection(url, user, senha);
            PreparedStatement ps = conn.prepareStatement(sql3);

            ps.setInt(1, rg);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Nome do usuario ="+rs.getString(2));
                ps.close();
                rs.close();
                conn.close();
                return true;
                
            }else{
                System.out.println("Usuario nao encontrado");
                ps.close();
                rs.close();
                conn.close();            
            }
        }catch (Exception ex) {
            System.out.println(ex);
        }

        return false;
    }
    
}
