package com.compeasy.dao;

import com.compeasy.model.Cadastro;
import com.compeasy.model.Conexao;
import com.compeasy.model.Endereco;
import com.compeasy.model.Paciente;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
                int pacienteId = obterUltimoIdInserido(conn);

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
                int enderecoId = obterUltimoIdInserido(conn);

                // Inserir cadastro
                String insertCadastroQuery = "INSERT INTO Cadastro (paciente_id, endereco_id, telefone, email, observacao) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertCadastroStatement = conn.prepareStatement(insertCadastroQuery);
                insertCadastroStatement.setInt(1, pacienteId);
                insertCadastroStatement.setInt(2, enderecoId);
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

    // Método para obter todos os registros de cadastro do banco de dados
    public List<Cadastro> obterTodosCadastros() {
        List<Cadastro> listaCadastros = new ArrayList<>();
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        if (conn != null) {
            try {
                String query = "SELECT * FROM Cadastro " +
                               "JOIN Paciente ON Cadastro.paciente_id = Paciente.id " +
                               "JOIN Endereco ON Cadastro.endereco_id = Endereco.id";

                try (PreparedStatement statement = conn.prepareStatement(query);
                     ResultSet resultSet = statement.executeQuery()) {

                    while (resultSet.next()) {
                        // Mapeia os resultados para criar objetos Cadastro, Paciente e Endereco
                        Paciente paciente = new Paciente(
                                resultSet.getString("nome"),
                                resultSet.getString("dataNascimento"),
                                resultSet.getString("sexo")
                        );

                        Endereco endereco = new Endereco(
                                resultSet.getString("logradouro"),
                                resultSet.getInt("numero"),
                                resultSet.getString("bairro"),
                                resultSet.getString("localidade"),
                                resultSet.getString("cep")
                        );

                        Cadastro cadastro = new Cadastro(
                                paciente,
                                endereco,
                                resultSet.getString("telefone"),
                                resultSet.getString("email"),
                                resultSet.getString("observacao")
                        );

                        listaCadastros.add(cadastro);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Fecha a conexão
                conexao.desconectar(conn);
            }
        }

        return listaCadastros;
    }

    // Método para obter o último ID inserido em uma tabela
    private int obterUltimoIdInserido(Connection conn) throws SQLException {
        String query = "SELECT LAST_INSERT_ID() as last_id";
        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("last_id");
            }
        }
        return -1;
    }
}
