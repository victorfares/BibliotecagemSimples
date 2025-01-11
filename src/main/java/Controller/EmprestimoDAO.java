/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.Livro;

import Model.Usuario;
import Model.Emprestimo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
public class EmprestimoDAO {
    static Connection conn = null;
    static String url = "jdbc:mysql://localhost:3306/mydb";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = "root";
    static String senha = "2004Gu$tavo";
    
    public boolean checarAlugado(int id){
        PreparedStatement ps = null;
        
        String sql0 = "SELECT Liv_alugado FROM livro WHERE Liv_id = ?";
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,senha);
            ps = conn.prepareStatement(sql0);
            
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                return rs.getBoolean("Liv_alugado");

            }
            ps.close();
            rs.close();
            conn.close();
        }catch(Exception ex){
            System.out.println(ex);
        }
        return false;
    }
    
    public void inserirE(Emprestimo emp){
        Livro livro = new Livro();
        Usuario usuario = new Usuario();
        PreparedStatement ps = null;
        
        
        
        boolean ver = checarAlugado(emp.getLivro().getId());
        if(ver == false) {
            String sql1 = "INSERT INTO emprestimo (Emp_id, Liv_id, Us_rg, Emp_dia, Emp_mes, Emp_ano)" + "VALUES(?, ?, ?, ?, ?, ?)";
        
            try{
                Class.forName(driver);
                conn = DriverManager.getConnection(url,user,senha);
                System.out.println("Inserindo dados...");
            
                ps = conn.prepareStatement(sql1);
                ps.setInt(1, emp.getId());
                ps.setInt(2, emp.getLivro().getId());
                ps.setInt(3, emp.getUsuario().getRg());
                ps.setInt(4, emp.getDia());
                ps.setInt(5, emp.getMes());
                ps.setInt(6, emp.getAno());

                ps.executeUpdate();
                System.out.println("Dados inseridos com sucesso!");
                ps.close();
                conn.close();
            }catch(Exception ex){
                System.out.println(ex);
            }
        
            String sql3 = "UPDATE livro SET Liv_alugado = TRUE WHERE Liv_id = ?";
            try{
                Class.forName(driver);
                conn = DriverManager.getConnection(url,user,senha);

                ps = conn.prepareStatement(sql3);
                ps.setInt(1, emp.getLivro().getId());

                ps.executeUpdate();
                System.out.println("Dados inseridos com sucesso!");
                ps.close();
                conn.close();
                JOptionPane.showMessageDialog(
                    null, "Empréstimo cadastrado", "Cadastro de Empréstimos", JOptionPane.INFORMATION_MESSAGE);
            }catch(Exception ex){
                System.out.println(ex);
            }
        }else {
            JOptionPane.showMessageDialog(null, "Livro ja esta alugado, nao é possivle fazer empretstimo", "Cadastro de Emprestimos", 1);
        }
        
    }
    
    
    public void excluirE(int id){
        PreparedStatement ps = null;
        
        String sql2 = "DELETE FROM emprestimo WHERE Emp_id = ?";
        
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,senha);
            
            ps = conn.prepareStatement(sql2);
            ps.setInt(1, id);
            
            ps.executeUpdate();
            System.out.println("Dados excluidos");
            ps.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Usuario Excluido com sucesso", "Exclusao de usuarios", 1);
            
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        
    }
    
    public void atualizarE(Emprestimo emp){
        PreparedStatement ps = null;
        
        String sql3 = "UPDATE livro SET Liv_alugado = FALSE WHERE Liv_id = ?";
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,senha);
            
            ps = conn.prepareStatement(sql3);
            ps.setInt(1, emp.getLivro().getId());
            
            ps.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");
            ps.close();
            conn.close();
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
}
