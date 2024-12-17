package dao;

import factory.ConnectionFactory;
import model.Aluno;
import java.sql.*;

public class AlunoDAO {
    private Connection connection;

    public AlunoDAO(){
        this.connection = new ConnectionFactory().getConnection();}

    // Inserir Aluno
    public void inserirAluno(Aluno aluno) {
        String sql = "INSERT INTO alunos (cpf, nome, data_nascimento, peso, altura) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, aluno.getCpf());
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getDataNascimento());
            stmt.setFloat(4, aluno.getPeso());
            stmt.setFloat(5, aluno.getAltura());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Excluir aluno pelo CPF
    public void excluirAluno(String cpf) {
        String sql = "DELETE FROM alunos WHERE cpf = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Atualizar aluno
    public void atualizarAluno(Aluno aluno) {
        String sql = "UPDATE alunos SET nome = ?, data_nascimento = ?, peso = ?, altura = ? WHERE cpf = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getDataNascimento());
            stmt.setFloat(3, aluno.getPeso());
            stmt.setFloat(4, aluno.getAltura());
            stmt.setString(5, aluno.getCpf());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Consultar aluno pelo CPF
    public Aluno consultarAluno(String cpf) {
        String sql = "SELECT * FROM alunos WHERE cpf = ?";
        Aluno aluno = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                aluno = new Aluno(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("data_nascimento"),
                        rs.getFloat("peso"),
                        rs.getFloat("altura")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return aluno;
    }
}
