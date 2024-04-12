package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import factory.ConnectionFactory;
import models.Aluno;

public class AlunoDao {
  public static void create(Aluno aluno) {
    String sql = "INSERT INTO aluno(nome) VALUES (?)";

    try (
        Connection connection = ConnectionFactory.openConnection();
        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);) {

      statement.setString(1, aluno.getNome());

      statement.executeUpdate();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static ArrayList<Aluno> get() {
    String sql = "SELECT * FROM aluno";

    try (
        Connection connection = ConnectionFactory.openConnection();
        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
        ResultSet results = statement.executeQuery();) {

      ArrayList<Aluno> alunos = new ArrayList<Aluno>();

      while (results.next() == true) {
        Aluno aluno = new Aluno();

        aluno.setIdAluno(results.getInt("id_aluno"));
        aluno.setNome(results.getString("nome"));

        alunos.add(aluno);
      }

      return alunos;
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return null;

  }

  public static Aluno get(Integer idAluno) {
    String sql = "SELECT * FROM aluno WHERE id_aluno = ?";

    try (
        Connection connection = ConnectionFactory.openConnection();
        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);) {

      statement.setInt(1, idAluno);
      try (ResultSet results = statement.executeQuery();) {
        Aluno aluno = new Aluno();

        results.next();
        aluno.setIdAluno(results.getInt("id_aluno"));
        aluno.setNome(results.getString("nome"));

        return aluno;
      }
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void update(Aluno aluno) {
    String sql = "UPDATE aluno SET nome = ? WHERE id_aluno = ?";

    try (
        Connection connection = ConnectionFactory.openConnection();
        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);) {

      statement.setString(1, aluno.getNome());
      statement.setInt(2, aluno.getIdAluno());

      statement.executeUpdate();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void delete(Aluno aluno) {
    String sql = "DELETE FROM aluno WHERE id_aluno = ?";

    try (
        Connection connection = ConnectionFactory.openConnection();
        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);) {
      statement.setInt(1, aluno.getIdAluno());

      statement.executeUpdate();
      NotaDao.deleteAllFromAluno(aluno);
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

}
