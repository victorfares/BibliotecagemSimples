/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Exception.LivroExisteException;
import Model.Livro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
                System.out.println("Nome do Livro = "+rs.getString(2));
                ps.close();
                rs.close();
                conn.close();
                return true;
                
            }else{
                JOptionPane.showMessageDialog(
                    null, "Livro não cadastrado", "Cadastro de Empréstimos", JOptionPane.INFORMATION_MESSAGE); 
                ps.close();
                rs.close();
                conn.close();            
            }
        }catch (Exception ex) {
            System.out.println(ex);
        }

        return false;
    }
 
    public void buscarIdLiv(int id) throws LivroExisteException{
        Livro livro = null;
        String sql3 = "SELECT COUNT(*) FROM livro WHERE Liv_id = ?";

        try{
            conn = DriverManager.getConnection(url, user, senha);
            PreparedStatement ps1 = conn.prepareStatement(sql3);

            ps1.setInt(1, id);
            ResultSet rs1 = ps1.executeQuery();
            

            if (rs1.next()) {
                int count = rs1.getInt(1);  
                if (count > 0) {
                    throw new LivroExisteException("Já existe livro com este ID cadastrado");
                } 
            }
            ps1.close();
            rs1.close();
            
        }catch (Exception ex) {
            System.out.println(ex);
        }
        
    }    
    
    

    public void inserirL(Livro livro){
        Statement st = null;
        PreparedStatement ps = null;
        
       
        String sql1 = "INSERT INTO livro (Liv_id, Liv_nome, Liv_anoLancamento, Liv_genero, Liv_autor, Liv_edicao, Liv_editora, Liv_alugado) "
           + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,senha);
            System.out.println("Inserindo dados...");
            buscarIdLiv(livro.getId());
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
            JOptionPane.showMessageDialog(
                null, "Livro cadastrado", "Cadastro de livros", JOptionPane.INFORMATION_MESSAGE);
            ps.close();
            conn.close();
        }catch(Exception ex){
            System.out.println(ex);
        }finally{
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
    
    public void excluirL(int id){
        Statement st = null;
        PreparedStatement ps = null;
        
        String sql2 = "DELETE FROM livro WHERE Liv_id = ?";
        
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
    
     public List<Object[]> buscarLivros(){
        List<Object[]> livros = new ArrayList<>();
        try{
            conn = DriverManager.getConnection(url,user,senha);
            String sql = "SELECT Liv_id, Liv_nome, Liv_genero, Liv_edicao, Liv_editora, Liv_anoLancamento, Liv_autor, Liv_alugado FROM livro";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("Liv_id");
                String nome = rs.getString("Liv_nome");        
                String gen = rs.getString("Liv_genero");
                int ed = rs.getInt("Liv_edicao");
                String edt = rs.getString("Liv_editora");
                int anoL = rs.getInt("Liv_anoLancamento");
                String aut = rs.getString("Liv_autor");
                boolean alu = rs.getBoolean("Liv_alugado");
                livros.add(new Object[]{id, nome, gen, ed, edt, anoL, aut, alu});
            }
            
            conn.close();
            ps.close();
            rs.close();
            
        }catch(Exception ex){
            System.out.println(ex);
        }
        return livros;
    }
     
     public List<Object[]> selecionarLivroAlugados(){
        List<Object[]> livros = new ArrayList<>();
        try{
            conn = DriverManager.getConnection(url,user,senha);
            String sql = "SELECT Liv_id, Liv_nome, Liv_genero, Liv_edicao, Liv_editora, Liv_anoLancamento, Liv_autor, Liv_alugado FROM livro WHERE Liv_alugado = TRUE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("Liv_id");
                String nome = rs.getString("Liv_nome");        
                String gen = rs.getString("Liv_genero");
                int ed = rs.getInt("Liv_edicao");
                String edt = rs.getString("Liv_editora");
                int anoL = rs.getInt("Liv_anoLancamento");
                String aut = rs.getString("Liv_autor");
                boolean alu = rs.getBoolean("Liv_alugado");
                livros.add(new Object[]{id, nome, gen, ed, edt, anoL, aut, alu});
            }
            
            conn.close();
            ps.close();
            rs.close();
            
        }catch(Exception ex){
            System.out.println(ex);
        }
        return livros;
     }
     
     public List<Object[]> selecionarLivDisp(){
        List<Object[]> livros = new ArrayList<>();
        try{
            conn = DriverManager.getConnection(url,user,senha);
            String sql = "SELECT Liv_id, Liv_nome, Liv_genero, Liv_edicao, Liv_editora, Liv_anoLancamento, Liv_autor, Liv_alugado FROM livro WHERE Liv_alugado = FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("Liv_id");
                String nome = rs.getString("Liv_nome");        
                String gen = rs.getString("Liv_genero");
                int ed = rs.getInt("Liv_edicao");
                String edt = rs.getString("Liv_editora");
                int anoL = rs.getInt("Liv_anoLancamento");
                String aut = rs.getString("Liv_autor");
                boolean alu = rs.getBoolean("Liv_alugado");
                livros.add(new Object[]{id, nome, gen, ed, edt, anoL, aut, alu});
            }
            
            conn.close();
            ps.close();
            rs.close();
            
        }catch(Exception ex){
            System.out.println(ex);
        }
        return livros;
     }
}
