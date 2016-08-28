package com.patryk.NotUsedYet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public abstract class MainController {
	@FXML protected TreeView<String> treeView;
	@FXML protected TabPane tabPane;
	@FXML protected SplitPane splitPane;
	
	@FXML protected Tab listManagerTab;
	@FXML protected Tab notebookTab;
	
	protected TreeItem<String> treeRoot;
	
	public void initialize() {
		treeRoot = new TreeItem<String>("List");
	    treeView.setRoot(treeRoot);
	}
	
	@FXML protected void handleListManagerMenuItemAction(ActionEvent event) {
		openListManager();
	}
	
	@FXML protected void handleNotebookMenuItemAction(ActionEvent event) {
		openNotebook();
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
}
