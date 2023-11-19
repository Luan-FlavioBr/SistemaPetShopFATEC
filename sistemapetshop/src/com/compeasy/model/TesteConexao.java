import com.compeasy.model.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class TesteConexao {

    public static void main(String[] args) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        System.out.println(conn);
        if (conn != null) {
            
            try {
                // Exemplo: Inserir um novo paciente
                String insertQuery = "INSERT INTO Paciente (nome, dataNascimento, sexo) VALUES (?, ?, ?)";
                
                // Substitua os valores abaixo pelos dados reais do paciente
                String nome = "Cabral";
                Date dataNascimento = new Date(); // Substitua pela data real
                String sexo = "M"; // Substitua pelo sexo real

                PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
                insertStatement.setString(1, nome);
                insertStatement.setDate(2, new java.sql.Date(dataNascimento.getTime()));
                insertStatement.setString(3, sexo);

                // Executa o insert
                int rowsAffected = insertStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Inserção realizada com sucesso!");
                } else {
                    System.out.println("Falha ao inserir o paciente.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Fecha a conexão
                conexao.desconectar(conn);
            }
        }
    }
}