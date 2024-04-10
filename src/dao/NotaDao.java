package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import factory.ConnectionFactory;
import models.Nota;

public class NotaDao {
  public void create(Nota nota) {
    String sql = "INSERT INTO nota(nota) VALUES (?)";

    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = ConnectionFactory.openConnection();
      statement = (PreparedStatement) connection.prepareStatement(sql);

      statement.setInt(1, nota.getNota());

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

  public ArrayList<Nota> read() {
    String sql = "SELECT * FROM nota";
    ArrayList<Nota> notas = new ArrayList<Nota>();

    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet results = null;

    try {
      connection = ConnectionFactory.openConnection();
      statement = (PreparedStatement) connection.prepareStatement(sql);

      results = statement.executeQuery();

      while (results.next() == true) {
        Nota nota = new Nota();

        nota.setId(results.getInt("id_nota"));
        nota.setNota(results.getInt("nota"));

        notas.add(nota);
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

    return notas;

  }

  public void update(Nota nota) {
    String sql = "UPDATE nota SET nota = ? WHERE id_nota = ?";

    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = ConnectionFactory.openConnection();
      statement = (PreparedStatement) connection.prepareStatement(sql);

      statement.setInt(1, nota.getNota());
      statement.setInt(2, nota.getId());

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

  public void delete(Nota nota) {
    String sql = "DELETE FROM nota WHERE id_nota = ?";

    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = ConnectionFactory.openConnection();
      statement = (PreparedStatement) connection.prepareStatement(sql);

      statement.setInt(1, nota.getId());

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
