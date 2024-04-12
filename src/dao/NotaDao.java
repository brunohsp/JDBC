package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import factory.ConnectionFactory;
import models.Aluno;
import models.Estatistica;
import models.Nota;

public class NotaDao {

  public static void create(Nota nota) {
    String sql = "INSERT INTO nota(nota, id_aluno) VALUES (?, ?)";

    try (
        Connection connection = ConnectionFactory.openConnection();
        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);) {
      statement.setInt(1, nota.getNota());
      statement.setInt(2, nota.getAluno().getIdAluno());

      statement.executeUpdate();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static ArrayList<Nota> get() {
    String sql = "SELECT * FROM nota";

    try (
        Connection connection = ConnectionFactory.openConnection();
        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
        ResultSet results = statement.executeQuery();) {

      ArrayList<Nota> notas = new ArrayList<Nota>();
      while (results.next() == true) {
        Nota nota = new Nota();

        nota.setIdNota(results.getInt("id_nota"));
        nota.setNota(results.getInt("nota"));
        nota.setAluno(AlunoDao.get(results.getInt("id_aluno")));

        notas.add(nota);
      }
      return notas;
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static ArrayList<Nota> get(Aluno aluno) {
    String sql = "SELECT * FROM nota WHERE id_aluno = ?";

    try (
        Connection connection = ConnectionFactory.openConnection();
        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);) {

      statement.setInt(1, aluno.getIdAluno());
      try (ResultSet results = statement.executeQuery();) {

        ArrayList<Nota> notas = new ArrayList<Nota>();
        while (results.next() == true) {
          Nota nota = new Nota();

          nota.setIdNota(results.getInt("id_nota"));
          nota.setNota(results.getInt("nota"));
          nota.setAluno(aluno);

          notas.add(nota);
        }
        return notas;
      }
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static Estatistica getEstatistica(Aluno aluno) {
    String sql = "SELECT " +
        "AVG(nota) AS media, " +
        "MAX(nota) AS maximo, " +
        "MIN(nota) AS minimo, " +
        "VARIANCE(nota) AS variancia " +
        "FROM nota WHERE id_aluno = ? " +
        "GROUP BY id_aluno;";

    try (
        Connection connection = ConnectionFactory.openConnection();
        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);) {

      statement.setInt(1, aluno.getIdAluno());
      try (ResultSet results = statement.executeQuery();) {

        Estatistica estatistica = new Estatistica();

        if(!results.next()) return null;

        estatistica.setMedia(results.getFloat("media"));
        estatistica.setMax(results.getFloat("maximo"));
        estatistica.setMin(results.getFloat("minimo"));
        estatistica.setVar(results.getFloat("variancia"));

        return estatistica;
      }
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static void update(Nota nota) {
    String sql = "UPDATE nota SET nota = ? WHERE id_nota = ?";

    try (
        Connection connection = ConnectionFactory.openConnection();
        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);) {
      statement.setInt(1, nota.getNota());
      statement.setInt(2, nota.getIdNota());

      statement.executeUpdate();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void delete(Nota nota) {
    String sql = "DELETE FROM nota WHERE id_nota = ?";

    try (
        Connection connection = ConnectionFactory.openConnection();
        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);) {
      statement.setInt(1, nota.getIdNota());

      statement.executeUpdate();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void deleteAllFromAluno(Aluno aluno) {
    String sql = "DELETE FROM nota WHERE id_aluno = ?";

    try (
        Connection connection = ConnectionFactory.openConnection();
        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);) {
      statement.setInt(1, aluno.getIdAluno());

      statement.executeUpdate();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
