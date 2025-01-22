/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Exception.UsuarioExisteException;
import Model.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioDAO {
    static Connection conn = null;
    static String url = "jdbc:mysql://localhost:3306/biblioteca";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = "root";
    static String senha = "chips2002";
    
    
        public void buscarRgUser(int id) throws UsuarioExisteException{
        String sql3 = "SELECT COUNT(*) FROM usuario WHERE Us_rg = ?";

        try{
            conn = DriverManager.getConnection(url, user, senha);
            PreparedStatement ps = conn.prepareStatement(sql3);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            

        if (rs.next()) {
            int count = rs.getInt(1); 
            if (count > 0) {
                throw new UsuarioExisteException(); 
            }
        }
            ps.close();
            rs.close();
            
        }catch (Exception ex) {
            System.out.println(ex);
        }
        
    }    
    
    public void inserirU(Usuario us){
        PreparedStatement ps = null;
        
        String sql1 = "INSERT INTO usuario (Us_rg, Us_nome)" + "VALUES(?, ?)";
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,senha);
            buscarRgUser(us.getRg());
            
            ps = conn.prepareStatement(sql1);
            ps.setInt(1, us.getRg());
            ps.setString(2, us.getNome());
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(
                null, "Usuario Cadastrado", "Cadastro de Usuarios", JOptionPane.INFORMATION_MESSAGE);
            ps.close();
            conn.close();
        }catch(Exception ex){
            System.out.println(ex);
        } finally{
             try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
        } catch (Exception ex) {
            System.out.println("Erro ao fechar recursos: " + ex.getMessage());
        }
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
    
    public void alterarU(Usuario us){
        PreparedStatement ps = null;
        
        String sql = "UPDATE usuario SET Us_nome = ? WHERE Us_rg = ?";
        
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,senha);
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, us.getNome());
            ps.setInt(2, us.getRg());
            
            ps.executeUpdate();
            System.out.println("Dados alterados");
            ps.close();
            conn.close();
        }catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public List<Object[]> buscarUsuarios(){
        List<Object[]> usur = new ArrayList<>();
        try{
            conn = DriverManager.getConnection(url,user,senha);
            String sql = "SELECT Us_nome,Us_rg FROM usuario";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                String nome = rs.getString("Us_nome");        
                int id = rs.getInt("Us_rg");
                usur.add(new Object[]{nome, id});
            }
            
            conn.close();
            ps.close();
            rs.close();
            
        }catch(Exception ex){
            System.out.println(ex);
        }
        return usur;
    }
    
    public void selecionarU(){
        
    }
    
}
