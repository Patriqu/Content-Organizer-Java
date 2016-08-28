package com.patryk;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/* Author: Patryk Siuda */
public class MainController {
	@FXML protected TreeView<String> listsTreeView;
	@FXML protected TreeView<String> notesTreeView;
	@FXML protected TabPane tabPane;
	@FXML protected SplitPane splitPane;
	
	@FXML protected TableView listsTableView;
	
	@FXML protected Tab listManagerTab;
	@FXML protected Tab notebookTab;
	
	protected TreeItem<String> currentRoot;
	
	protected TreeItem<String> listsRoot;
	protected TreeItem<String> notesRoot;
	
	private Database database = new Database();
	// FOR TESTS PURPOSES: ///////////////////
	@FXML private TextArea noteTextArea;
	
	private final String DATABASE_NAME = "organizer";
	private final String[] TABLES_NAMES = new String[] { "lists", "notes" };
	private final String[] TABLE_COLUMNS = new String[] { "id integer primary key autoincrement", "name string", "text string" };
	private final List<TreeMap<String, String>> records = new LinkedList<>();
	//////////////////////////////////////////
	
	
	private void testOperations() {
		try {
			database.createDatabase(DATABASE_NAME);
			
			database.createTable(TABLES_NAMES[0], TABLE_COLUMNS);
			database.createTable(TABLES_NAMES[1], TABLE_COLUMNS);
			
			for (String name : TABLES_NAMES) {
				database.insertExampleRecords(name);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/////////////////////////////////////////
	
	public void initialize() {
		listsRoot = new TreeItem<String>("Lists");
		notesRoot = new TreeItem<String>("Notes");
		
	    listsTreeView.setRoot(listsRoot);
	    notesTreeView.setRoot(notesRoot);
	    
	    currentRoot = listsRoot;
	    
	    // listener for when the user change current tab in split pane
	    tabPane.getSelectionModel().selectedItemProperty().addListener(
    	    new ChangeListener<Tab>() {
    	        @Override
    	        public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
    	        	String id = t1.getId();
    	        	if (id.equals("listManagerTab")) {
    	        		currentRoot = listsRoot;
    	        	}
    	        	else if (id.equals("notebookTab")) {
    	        		currentRoot = notesRoot;
    	        	}
    	        	System.out.println("Opened tab: " + id);
    	        }
    	    }
	    );
	    
	    // listener for when the user click item from the list on the left
	    listsTreeView.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<Object>() {
	        @Override
	        public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
	            try {
					loadList();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
	        }

	    });
	    // listener for when the user click item from the list on the left
	    notesTreeView.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<Object>() {
	        @Override
	        public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
	            try {
					loadNote();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
	        }

	    });
	    
	    testOperations();
	}
	
	// Event handlers
	@FXML protected void handleListManagerMenuItemAction(ActionEvent event) {
		openListManager();
	}
	@FXML protected void handleNotebookMenuItemAction(ActionEvent event) {
		openNotebook();
	}
	
	@FXML protected void handleCreateListAction(ActionEvent event) throws ClassNotFoundException {
		createList();
	}
	@FXML protected void handleCreateNoteAction(ActionEvent event) throws ClassNotFoundException {
		createNote();
	}
	
	@FXML protected void handleRenameListAction(ActionEvent event) {
		rename();
	}
	@FXML protected void handleRenameNoteAction(ActionEvent event) {
		rename();
	}
	
	@FXML protected void handleSaveListAction(ActionEvent event) throws ClassNotFoundException {
		saveList();
	}
	@FXML protected void handleSaveNoteAction(ActionEvent event) throws ClassNotFoundException {
		saveNote();
	}
	
	@FXML protected void handleLoadListAction(ActionEvent event) throws ClassNotFoundException {
		loadList();
	}
	@FXML protected void handleLoadNoteAction(ActionEvent event) throws ClassNotFoundException {
		loadNote();
	}
	
	@FXML protected void handleDeleteListAction(ActionEvent event) {
		deleteList();
	}
	@FXML protected void handleDeleteNoteAction(ActionEvent event) {
		deleteNote();
	}
	
	// methods
	private void openListManager() {
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(listManagerTab);
		currentRoot = listsRoot;
		
		System.out.println("List Manager opened");
	}
	private void openNotebook() {
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(notebookTab);
		currentRoot = notesRoot;
		
		System.out.println("Notebook opened");
	}
	
	private void createList() throws ClassNotFoundException {
		currentRoot.setExpanded(true);
		currentRoot.getChildren().add(new TreeItem<String>("New List"));
		
		database.createList("New List");
	}
	private void createNote() throws ClassNotFoundException {
		currentRoot.setExpanded(true);
		currentRoot.getChildren().add(new TreeItem<String>("New Note"));
		
		database.createNote("New Note");
	}
	
	private void saveList() throws ClassNotFoundException {
		database.saveNote(getSelectedListName());	
	}
	private void saveNote() throws ClassNotFoundException {
		database.saveNote(getSelectedNoteName());
	}
	
	private void loadList() throws ClassNotFoundException {
		ResultSet rs = database.loadList(getSelectedListName());
		// TODO: display table of this list
		//noteTextArea.setText(rs.getString(0));
	}
	private void loadNote() throws ClassNotFoundException {
		database.loadNote(getSelectedNoteName());
		// TODO: display note in text field
	}
	
	private void deleteList() {
		database.deleteList(getSelectedListName());
		getSelectedList().getParent().getChildren().remove(getSelectedList());
	}
	private void deleteNote() {
		database.deleteNote(getSelectedNoteName());
		getSelectedNote().getParent().getChildren().remove(getSelectedNote());
	}
	
	// inner helper methods
	private TreeItem<String> getSelectedList() {
		return listsTreeView.getSelectionModel().getSelectedItem();
	}
	private TreeItem<String> getSelectedNote() {
		return notesTreeView.getSelectionModel().getSelectedItem();
	}
	private String getSelectedListName() {
		return getSelectedList().getValue();
	}
	private String getSelectedNoteName() {
		return getSelectedNote().getValue();
	}
	private void rename() {
		TreeItem<String> treeItem = null;
		if (currentRoot.getValue().equals("list")) {
			treeItem = listsTreeView.getSelectionModel().getSelectedItem();
		}
		else if (currentRoot.getValue().equals("note")) {
			treeItem = notesTreeView.getSelectionModel().getSelectedItem();
		}
		
		// TODO: display Dialog where you can choose the new name
		treeItem.setValue("changed name");
	}
}
