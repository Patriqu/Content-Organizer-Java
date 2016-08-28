package com.patryk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/* Author: Patryk Siuda */
public class Database {
	@FXML private TreeView<String> treeView;
	
	@FXML private TableView<?> listsTableView;
	@FXML private TextArea noteTextArea;
	
	private final String DATABASE_FILE_PATH = "jdbc:sqlite:db/organizer.db";
	
	private String[] listsExampleRecords = new String[] { "null, \"shopping\", \"eggs, milk, bread, sausage\" " };
	private String[] notesExampleRecords = new String[] { "null, \"programming\", \"C++, Java, C#\" " };
	
	
	public void insertExampleRecords(String tableName) {
	    try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

	    Connection connection = null;
	    try
	    {
	    	connection = DriverManager.getConnection(DATABASE_FILE_PATH);
	    	Statement statement = connection.createStatement();
	    	statement.setQueryTimeout(30);
	      
	    	String[] iteratedTable = null;
	    	switch(tableName) {
	    		case "lists":
	    			iteratedTable = listsExampleRecords;
	    			break;
	    			
	    		case "notes":
	    			iteratedTable = notesExampleRecords;
	    			break;
	    	}
	    	
	    	for (String record : iteratedTable) {
	    		statement.executeUpdate("insert into " + tableName + " values (" + record + ")");
	    	}
	    }
	    catch(SQLException e)
	    {
	      System.err.println(e.getMessage());
	    }
	    finally
	    {
	      try
	      {
	        if(connection != null)
	          connection.close();
	        
	        System.out.println("New records inserted to the \"" + tableName + "\" table!");
	      }
	      catch(SQLException e)
	      {
	        System.err.println(e);
	      }
	    }
	}
	
	public void createDatabase(String databaseName) throws ClassNotFoundException {
	    Class.forName("org.sqlite.JDBC");

	    Connection connection = null;
	    try
	    {
	    	connection = DriverManager.getConnection(DATABASE_FILE_PATH);
	    	Statement statement = connection.createStatement();
	    	statement.setQueryTimeout(30);
	      
	    	statement.executeUpdate("create database " + databaseName);
	    }
	    catch(SQLException e)
	    {
	      System.err.println(e.getMessage());
	    }
	    finally
	    {
	      try
	      {
	        if(connection != null)
	          connection.close();
	        
	        System.out.println("Database created");
	      }
	      catch(SQLException e)
	      {
	        // connection close failed.
	        System.err.println(e);
	      }
	    }
	}
	public void createTable(String tableName, String[] attributes) throws ClassNotFoundException {
	    Class.forName("org.sqlite.JDBC");

	    Connection connection = null;
	    try
	    {
	      connection = DriverManager.getConnection(DATABASE_FILE_PATH);
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);
	      
	      String attsTxt = "";
	      for (int i = 0; i < attributes.length; i++) {
	    	  attsTxt += attributes[i];
	    	  if (i < attributes.length-1) {
	    		  attsTxt += ", ";
	    	  }
	      }
	      //statement.executeUpdate("drop table if exists " + databaseName);
	      
	      System.out.println(attsTxt);
	      statement.executeUpdate("create table " + tableName + " (" + attsTxt + ")");
	    }
	    catch(SQLException e)
	    {
	      System.err.println(e.getMessage());
	    }
	    finally
	    {
	      try
	      {
	        if(connection != null)
	          connection.close();
	        
	        System.out.println("Table " + tableName + " created");
	      }
	      catch(SQLException e)
	      {
	        System.err.println(e);
	      }
	    }
	}
	
	public void createList(String name) throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
	    
	    Connection connection = null;
	    try
	    {
	      connection = DriverManager.getConnection(DATABASE_FILE_PATH);
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);
	
	      statement.executeUpdate("insert into lists values(null, \"" + name + "\", \"text\")");
	    }
	    catch(SQLException e)
	    {
	      System.err.println(e.getMessage());
	    }
	    finally
	    {
	      try
	      {
	        if(connection != null)
	          connection.close();
	        
	        System.out.println("New List created");
	      }
	      catch(SQLException e)
	      {
	        System.err.println(e);
	      }
	    }
	}
	public void createNote(String name) throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
	    
	    Connection connection = null;
	    try
	    {
	      connection = DriverManager.getConnection(DATABASE_FILE_PATH);
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);
	
	      statement.executeUpdate("insert into notes values(null, \"" + name + "\", \"text\")");
	    }
	    catch(SQLException e)
	    {
	      System.err.println(e.getMessage());
	    }
	    finally
	    {
	      try
	      {
	        if(connection != null)
	          connection.close();
	        
	        System.out.println("New note created");
	      }
	      catch(SQLException e)
	      {
	        System.err.println(e);
	      }
	    }
	}
	
	public void saveList(String name) throws ClassNotFoundException {
		
	}
	public void saveNote(String name) throws ClassNotFoundException {
		String txt = noteTextArea.getText();
		
	    Class.forName("org.sqlite.JDBC");
	    
	    Connection connection = null;
	    try
	    {
	      connection = DriverManager.getConnection(DATABASE_FILE_PATH);
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);
	
	      statement.executeUpdate("insert into notes values(1, " + txt + ")");
	    }
	    catch(SQLException e)
	    {
	      System.err.println(e.getMessage());
	    }
	    finally
	    {
	      try
	      {
	        if(connection != null)
	          connection.close();
	        
	        System.out.println("Note saved");
	      }
	      catch(SQLException e)
	      {
	        System.err.println(e);
	      }
	    }
	}
	
	public ResultSet loadList(String name) throws ClassNotFoundException {		
		Class.forName("org.sqlite.JDBC");

	    Connection connection = null;
	    ResultSet rs = null;
	    try
	    {
	      connection = DriverManager.getConnection(DATABASE_FILE_PATH);
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);
	
	      rs = statement.executeQuery("SELECT * FROM lists WHERE name=\"" + name + "\"");
	      
	      printObtainedSqlData(rs);
	    }
	    catch(SQLException e)
	    {
	      System.err.println(e.getMessage());
	    }
	    finally
	    {
	      try
	      {
	        if(connection != null)
	          connection.close();
	        
	        System.out.println("List loaded");
	      }
	      catch(SQLException e)
	      {
	        System.err.println(e);
	      }
	    }
	    return rs;
	}
	public void loadNote(String name) throws ClassNotFoundException {
	    Class.forName("org.sqlite.JDBC");

	    Connection connection = null;
	    try
	    {
	      connection = DriverManager.getConnection(DATABASE_FILE_PATH);
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);
	
	      ResultSet rs = statement.executeQuery("select * from notes");
	      
	      printObtainedSqlData(rs);
	      displaySqlDataInTable(rs);
	    }
	    catch(SQLException e)
	    {
	      System.err.println(e.getMessage());
	    }
	    finally
	    {
	      try
	      {
	        if(connection != null)
	          connection.close();
	        
	        System.out.println("Note loaded");
	      }
	      catch(SQLException e)
	      {
	        System.err.println(e);
	      }
	    }
	}
	
	public void deleteList(String name) {
		System.out.println("List deleted");
	}
	public void deleteNote(String name) {
		System.out.println("Note deleted");
	}
	
	
	public void loadAllLists() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

	    Connection connection = null;
	    try
	    {
	      connection = DriverManager.getConnection(DATABASE_FILE_PATH);
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);
	
	      ResultSet rs = statement.executeQuery("select * from lists");
	      
	      displaySqlDataInTable(rs);
	    }
	    catch(SQLException e)
	    {
	      System.err.println(e.getMessage());
	    }
	    finally
	    {
	      try
	      {
	        if(connection != null)
	          connection.close();
	        
	        System.out.println("List loaded");
	      }
	      catch(SQLException e)
	      {
	        System.err.println(e);
	      }
	    }
	}
	
	private void printObtainedSqlData(ResultSet rs) {
		try {
			while(rs.next()) {
				System.out.println("id = " + rs.getInt("id"));
				System.out.println("name = " + rs.getString("name"));
				System.out.println("text = " + rs.getString("text"));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void displaySqlDataInTable(ResultSet rs) throws SQLException {
		System.out.println("displaySqlDataInTable");
		//listsTableView.getColumns();
		
		ObservableList<?> ol = listsTableView.getColumns();
		
		/*
		while(rs.next()) {
			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				ol.add(rs.getObject(i));
			}
		}
		*/
	}
}
