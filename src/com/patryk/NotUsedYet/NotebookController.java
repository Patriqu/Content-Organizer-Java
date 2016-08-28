package com.patryk.NotUsedYet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

public class NotebookController extends MainController {
	@FXML final protected void handleCreateNoteAction(ActionEvent event) {
		createNote();
	}
	
	@FXML final protected void handleDeleteNoteAction(ActionEvent event) {
		deleteNote();
	}
	
	
	private void createNote() {
		System.out.println("New note created");
	}
	
	private void deleteNote() {
		System.out.println("Note deleted");
	}
}
