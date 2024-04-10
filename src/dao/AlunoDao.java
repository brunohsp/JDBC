package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import factory.ConnectionFactory;
import models.Aluno;

public class AlunoDao {
  public void create(Aluno aluno) {
    String sql = "INSERT INTO aluno(nome) VALUES (?)";

    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = ConnectionFactory.openConnection();
      statement = (PreparedStatement) connection.prepareStatement(sql);

      statement.setString(1, aluno.getNome());

      statement.executeUpdate();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    } finally {
      try {
        if (connection != null)
          connection.close();
        if (statement != null)
          statement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public ArrayList<Aluno> read() {
    String sql = "SELECT * FROM aluno";
    ArrayList<Aluno> alunos = new ArrayList<Aluno>();

    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet results = null;

    try {
      connection = ConnectionFactory.openConnection();
      statement = (PreparedStatement) connection.prepareStatement(sql);

      results = statement.executeQuery();

      while (results.next() == true) {
        Aluno aluno = new Aluno();

        aluno.setId(results.getInt("id_aluno"));
        aluno.setNome(results.getString("nome"));

        alunos.add(aluno);
      }
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    } finally {
      try {
        if (connection != null)
          connection.close();
        if (statement != null)
          statement.close();
        if (results != null)
          results.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return alunos;

  }

  public void update(Aluno aluno) {
    String sql = "UPDATE aluno SET nome = ? WHERE id_aluno = ?";

    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = ConnectionFactory.openConnection();
      statement = (PreparedStatement) connection.prepareStatement(sql);

      statement.setString(1, aluno.getNome());
      statement.setInt(2, aluno.getId());

      statement.executeUpdate();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    } finally {
      try {
        if (connection != null)
          connection.close();
        if (statement != null)
          statement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public void delete(Aluno aluno) {
    String sql = "DELETE FROM aluno WHERE id_aluno = ?";

    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = ConnectionFactory.openConnection();
      statement = (PreparedStatement) connection.prepareStatement(sql);

      statement.setInt(1, aluno.getId());

      statement.executeUpdate();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    } finally {
      try {
        if (connection != null)
          connection.close();
        if (statement != null)
          statement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

}
