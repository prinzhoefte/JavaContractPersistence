

import Kaufvertrag.Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.Kaufvertrag.dataLayer.businessObjects.Adresse;
import Kaufvertrag.Kaufvertrag.dataLayer.dataAccessObjects.IDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdresseDaoSqlite implements IDao<IAdresse, Long>
{
  @Override
  public IAdresse create()
  {
    //TODO: Possehl fragen, ob wir hier mit scanner bzw generell user input den kram bekommen sollen
    Adresse objectToInsert = new Adresse("", "", "", "");
    try
    {
      Connection connection = new ConnectionManager().getNewConnection();
      String query = "INSERT into adresse (strasse, hausnummer, postleitzahl, ort) values(?, ?, ?, ?)";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, objectToInsert.getStrasse());
      statement.setString(2, objectToInsert.getHausNr());
      statement.setString(3, objectToInsert.getPlz());
      statement.setString(4, objectToInsert.getOrt());
      statement.executeUpdate();
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in AdresseDaoSqlite#create(IAdresse objectToInsert).");
    }
    return objectToInsert;
  }

  @Override
  public void create(IAdresse objectToInsert)
  {
    try
    {
      Connection connection = new ConnectionManager().getNewConnection();
      String query = "INSERT into adresse (strasse, hausnummer, postleitzahl, ort) values(?, ?, ?, ?)";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, objectToInsert.getStrasse());
      statement.setString(2, objectToInsert.getHausNr());
      statement.setString(3, objectToInsert.getPlz());
      statement.setString(4, objectToInsert.getOrt());
      statement.executeUpdate();
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in AdresseDaoSqlite#create(IAdresse objectToInsert).");
    }
  }

  @Override
  public IAdresse read(Long id)
  {
    try
    {
      Connection connection = new ConnectionManager().getNewConnection();
      String query = "SELECT strasse, hausnummer, postleitzahl, ort from adresse WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setLong(1, id);
      ResultSet result = statement.executeQuery();
      String strasse = result.getString("strasse");
      String hausnummer = result.getString("hausnummer");
      String plz = result.getString("postleitzahl");
      String ort = result.getString("ort");
      return new Adresse(strasse, hausnummer, plz, ort);
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in AdresseDaoSqlite#read(Long id).");
    }
    return null;
  }

  @Override
  public List<IAdresse> readAll()
  {
    try
    {
      Connection connection = new ConnectionManager().getNewConnection();
      String query = "SELECT strasse, hausnummer, postleitzahl, ort from adresse";
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet result = statement.executeQuery();
      ArrayList<IAdresse> list = new ArrayList<>();
      while(result.next())
      {
        String strasse = result.getString("strasse");
        String hausnummer = result.getString("hausnummer");
        String plz = result.getString("postleitzahl");
        String ort = result.getString("ort");
        list.add(new Adresse(strasse, hausnummer, plz, ort));
      }
      return list;
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in AdresseDaoSqlite#readAll().");
    }
    return null;
  }

  @Override
  public void update(IAdresse objectToUpdate)
  {
    try
    {
      Connection connection = new ConnectionManager().getNewConnection();
      String query = "UPDATE adresse SET strasse = ?, hausnummer = ?, postleitzahl = ?, ort = ? WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, objectToUpdate.getStrasse());
      statement.setString(2, objectToUpdate.getHausNr());
      statement.setString(3, objectToUpdate.getPlz());
      statement.setString(4, objectToUpdate.getOrt());
      //TODO: Possehl fragen, ob wir hier mit scanner bzw generell user input den kram bekommen sollen
      statement.setInt(5, 0/*whatthefuckshalliputastheidhere?*/);
      statement.executeUpdate();
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in AdresseDaoSqlite#update(IAdresse objectToUpdate).");
    }
  }

  @Override
  public void delete(Long id)
  {
    try
    {
      Connection connection = new ConnectionManager().getNewConnection();
      String query = "DELETE from adresse WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setLong(1, id);
      ResultSet result = statement.executeQuery();
    }
    catch (Exception ex)
    {
      System.out.println("There was an unexpected Exception in AdresseDaoSqlite#delete(Long id).");
    }
  }
}
