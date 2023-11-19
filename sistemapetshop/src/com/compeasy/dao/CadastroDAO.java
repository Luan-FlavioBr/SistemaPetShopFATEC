package com.compeasy.dao;

import com.compeasy.model.Cadastro;
import com.compeasy.model.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Carlos Eduardo
 */
public class CadastroDAO {

    // Método para inserir um novo cadastro no banco de dados
    public boolean inserirCadastro(Cadastro cadastro) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();  
        

        if (conn != null) {
            try {
                // Inserir paciente
                String insertPacienteQuery = "INSERT INTO Paciente (nome, dataNascimento, sexo) VALUES (?, ?, ?)";
                PreparedStatement insertPacienteStatement = conn.prepareStatement(insertPacienteQuery);
                insertPacienteStatement.setString(1, cadastro.getPaciente().getNome());
                insertPacienteStatement.setString(2, cadastro.getPaciente().getDataNascimento());
                insertPacienteStatement.setString(3, cadastro.getPaciente().getSexo());
                insertPacienteStatement.executeUpdate();

                // Obter o ID do paciente inserido
                // Inserir endereço
                String insertEnderecoQuery = "INSERT INTO Endereco (logradouro, numero, bairro, localidade, cep) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertEnderecoStatement = conn.prepareStatement(insertEnderecoQuery);
                insertEnderecoStatement.setString(1, cadastro.getEndereco().getLogradouro());
                insertEnderecoStatement.setInt(2, cadastro.getEndereco().getNumero());
                insertEnderecoStatement.setString(3, cadastro.getEndereco().getBairro());
                insertEnderecoStatement.setString(4, cadastro.getEndereco().getLocalidade());
                insertEnderecoStatement.setString(5, cadastro.getEndereco().getCep());
                insertEnderecoStatement.executeUpdate();

                // Obter o ID do endereço inserido
                // Inserir cadastro
                String insertCadastroQuery = "INSERT INTO Cadastro (paciente_id, endereco_id, telefone, email, observacao) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertCadastroStatement = conn.prepareStatement(insertCadastroQuery);
                insertCadastroStatement.setString(3, cadastro.getTelefone());
                insertCadastroStatement.setString(4, cadastro.getEmail());
                insertCadastroStatement.setString(5, cadastro.getObservacao());
                int rowsAffected = insertCadastroStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Cadastro inserido com sucesso!");
                    return true;
                } else {
                    System.out.println("Falha ao inserir o cadastro.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Fecha a conexão
                conexao.desconectar(conn);
            }
        }
        return false;
    }

}
