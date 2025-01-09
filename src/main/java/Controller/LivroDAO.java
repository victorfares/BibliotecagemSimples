/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Model.Livro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;




public class LivroDAO {
    static Connection conn = null;
    static String url = "jdbc:mysql://localhost:3306/mydb";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = "root";
    static String senha = "2004Gu$tavo";
    
    public boolean buscarL(int id) {
        Livro livro = null;
        String sql3 = "SELECT * FROM livro WHERE Liv_id = ?";

        try{
            conn = DriverManager.getConnection(url, user, senha);
            PreparedStatement ps = conn.prepareStatement(sql3);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Nome do Livro ="+rs.getString(2));
                ps.close();
                rs.close();
                conn.close();
                return true;
                
            }else{
                System.out.println("Livro nao encontrado");
                ps.close();
                rs.close();
                conn.close();            
            }
        }catch (Exception ex) {
            System.out.println(ex);
        }

        return false;
    }
    
    
    

    public void inserirL(Livro livro){
        Statement st = null;
        PreparedStatement ps = null;
        
       
        String sql1 = "INSERT INTO Livro (Liv_id, Liv_nome, Liv_anoLancamento, Liv_genero, Liv_autor, Liv_edicao, Liv_editora, Liv_alugado) "
           + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,senha);
            System.out.println("Inserindo dados...");
            
            ps = conn.prepareStatement(sql1);
            ps.setInt(1, livro.getId());
            ps.setString(2, livro.getNome());
            ps.setInt(3, livro.getAnoLancamento());
            ps.setString(4, livro.getGenero());
            ps.setString(5, livro.getAutor());
            ps.setInt(6, livro.getEdicao());
            ps.setString(7, livro.getEditora());
            ps.setBoolean(8, livro.isAlugado());
            
           
            ps.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");
            ps.close();
            conn.close();
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    public void excluirL(int id){
        Statement st = null;
        PreparedStatement ps = null;
        
        String sql2 = "DELETE FROM livro WHERE id = ?";
        
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,senha);
            
            ps = conn.prepareStatement(sql2);
            ps.setInt(1, id);
             
            ps.executeUpdate();
            System.out.println("Dados excluidos");
            ps.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Livro Excluido com sucesso", "Exclusao de livros", 1);
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        
    }
    
    public void alterarL(Livro livro){
        
    }
}
