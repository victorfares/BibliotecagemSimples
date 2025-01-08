/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Victor
 */
public class LivroDAO {
    /*
        public Livro buscarLivroPorId(int id) {
        Livro livro = null;
        String sql = "SELECT * FROM livros WHERE id = ?";

        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
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
}
