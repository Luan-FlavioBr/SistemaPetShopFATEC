/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.compeasy.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author sobra
 */
public class Conexao {
    
    public Connection conectar() {
        Connection conn = null;

        try {
            // Configuração da URL de conexão com o banco de dados
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/petshop?useTimezone=true&serverTimezone=America/Sao_Paulo"
                    , "root", "root");
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (SQLException e) {
            // Trata erros de conexão
            JOptionPane.showMessageDialog(null, "Erro de Conexão: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return conn;
    }

    public void desconectar(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexão fechada com sucesso!");
            }
        } catch (SQLException e) {
            // Trata erros ao fechar a conexão
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
