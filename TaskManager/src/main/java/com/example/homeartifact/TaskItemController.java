package com.example.homeartifact;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class TaskItemController implements Initializable {
    @FXML
    private Button btnInfo;
    @FXML
    private ImageView iconSelect;
    @FXML
    private Label lblTaskName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setTask(TaskModel model) {
        lblTaskName.setText(model.getTaskName());
        btnInfo.setText(model.getTaskStatus());
        iconSelect.setImage(model.getIcon());


        ContextMenu menu = new ContextMenu();
        menu.getItems().add(new MenuItem("Set Task Complete"));

        if (model.getTaskStatus().equalsIgnoreCase("In Progress")) {
            menu.getItems().add(new MenuItem("Set Task Complete"));
        } else {
            menu.getItems().add(new MenuItem("Set Task In Progress"));
        }
        lblTaskName.setContextMenu(menu);
    }
}

