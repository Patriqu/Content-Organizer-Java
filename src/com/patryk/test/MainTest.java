package com.patryk.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.patryk.Database;

public class MainTest {
	Database tests = new Database();
	
	private final String DATABASE_NAME = "organizer";
	private final String[] TABLES_NAMES = new String[] { "lists", "notes" };
	private final String[] TABLE_COLUMNS = new String[] { "id integer", "name string", "text string" };
	private final List<TreeMap<String, String>> records = new LinkedList<>();
	
	@Before
	public void setUp() {
	}
	@After
	public void tearDown() {
	}
	
	//@Test
	public void testLoadList() {
		// GIVEN
		String name = "list";
		
		// WHEN
		try {
			tests.loadList(name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// THEN
		
	}
	//@Test
	public void testLoadNote() {
		String name = "note";
		
		try {
			tests.loadNote(name);				
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	//@Test
	public void testSaveList() {
		String name = "list";
		
		try {
			tests.saveList(name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testSaveNote() {
		String name = "note";
		
		try {
			tests.saveNote(name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDisplay() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

	    Connection connection = null;
	    try
	    {
	      connection = DriverManager.getConnection("jdbc:sqlite:db/organizer.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);
	
	      ResultSet rs = statement.executeQuery("select * from lists");
	      tests.displaySqlDataInTable(rs);
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
}
