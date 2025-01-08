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




public class LivroDAO {
    static Connection conn = null;
    static String url = "jdbc:mysql://localhost:3306/mydb";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = "root";
    static String senha = "2004Gu$tavo";
    /*
        public Livro buscarLivroPorId(int id) {
        Livro livro = null;
        String sql = "SELECT * FROM livros WHERE id = ?";

        try (Connection conexao = DriverManager.getConnection(url, user, senha);
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int livroId = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");

                livro = new Livro(livroId, titulo, autor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return livro;
    }
    */
    
    

    public void inserir(Livro livro){
        Statement st = null;
        PreparedStatement ps = null;
        
       
        String sql = "INSERT INTO Livro (id, nome, anoLancamento, genero, autor, edicao, editora, alugado) "
           + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,senha);
            System.out.println("Inserindo dados...");
            
            ps = conn.prepareStatement(sql);
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
}
