package com.patryk;

import javax.swing.JDialog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class OrganizerController {
	@FXML private TabPane tabPane;
	@FXML private Tab listManagerTab;
	@FXML private Tab notebookTab;
	
	
	@FXML protected void handleListManagerMenuItemAction(ActionEvent event) {
		openListManager();
	}
	
	@FXML protected void handleNotebookMenuItemAction(ActionEvent event) {
		openNotebook();
	}
	
	@FXML protected void handleCreateListAction(ActionEvent event) {
		createList();
	}
	
	@FXML protected void handleDeleteListAction(ActionEvent event) {
		deleteList();
	}
	
	@FXML protected void handleCreateNoteAction(ActionEvent event) {
		createNote();
	}
	
	@FXML protected void handleDeleteNoteAction(ActionEvent event) {
		deleteNote();
	}
	
	private void openListManager() {
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(listManagerTab);
		
		System.out.println("List Manager opened");
	}
	
	private void openNotebook() {
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(notebookTab);
		
		System.out.println("Notebook opened");
	}
	
	private void createList() {
		System.out.println("New list created");
	}
	
	private void deleteList() {
		System.out.println("List deleted");
	}
	
	private void createNote() {
		System.out.println("New note created");
	}
	
	private void deleteNote() {
		System.out.println("Note deleted");
	}
}
