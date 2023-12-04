package com.example.homeartifact;

import javafx.scene.image.Image;
import java.sql.Date;


public class TaskModel {
    private String taskName;
    private String taskStatus;
    private Image icon;

    private Date deadline;
    private Date created;

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }


    public TaskModel(String taskName, String taskStatus, Image icon) {
        this.taskName = taskName;
        this.taskStatus = taskStatus;
        this.icon = icon;
    }

    public TaskModel(String taskName, boolean completed) {
        this.taskName = taskName;
        if (completed) {
            this.taskStatus = "Completed";
            this.icon = new Image(
                    HelloApplication.class.getResourceAsStream("icons/yes.png")
            );
        } else {
            this.taskStatus = "In Progress";
            this.icon = new Image(
                    HelloApplication.class.getResourceAsStream("icons/check-circle.png")
            );
        }
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }
}
