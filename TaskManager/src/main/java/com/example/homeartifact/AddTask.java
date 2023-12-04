package com.example.homeartifact;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;


import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddTask implements Initializable {

    @FXML
    private Button btnCancel;
    @FXML
    private Button btnOk;
    @FXML
    private DatePicker dpDeadline;
    @FXML
    private TextField tfDescription;
    private Date deadline;
    private String taskDescription;

    @FXML
    private void getDeadlineDate(ActionEvent event) {
        this.deadline = Date.valueOf(dpDeadline.getValue());
    }

    @FXML
    private void getTaskDescription(ActionEvent event) {
        this.taskDescription = tfDescription.getText();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DataBaseHandler dbHandler = new DataBaseHandler();

        btnOk.setOnAction(event -> {
            dbHandler.addTask(taskDescription, deadline, Date.valueOf(LocalDate.now()));
            btnOk.getScene().getWindow().hide();
        });

        btnCancel.setOnAction(event -> {
            btnCancel.getScene().getWindow().hide();
        });

    }
}
