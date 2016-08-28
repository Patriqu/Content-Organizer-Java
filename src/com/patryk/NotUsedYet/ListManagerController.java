package com.patryk.NotUsedYet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class ListManagerController extends MainController {
	@FXML final protected void handleCreateListAction(ActionEvent event) {
		createList();
	}
	
	@FXML final protected void handleDeleteListAction(ActionEvent event) {
		deleteList();
	}
	
	private void createList() {
		treeRoot.setExpanded(true);
		treeRoot.getChildren().add(new TreeItem<String>("New List"));
        
		System.out.println("New list created");
	}
	
	private void deleteList() {
		TreeItem<String> treeItem = treeView.getSelectionModel().getSelectedItem();
		treeItem.getParent().getChildren().remove(treeItem);
		
		System.out.println("List deleted");
	}
}
