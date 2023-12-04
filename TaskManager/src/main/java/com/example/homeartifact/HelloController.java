package com.example.homeartifact;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HelloController implements Initializable {
    Image imageEarth = new Image("https://cdn-icons-png.flaticon.com/128/814/814587.png");
    Image imageSnow = new Image("https://cdn-icons-png.flaticon.com/128/2465/2465979.png");

    Image imageClear = new Image("https://cdn-icons-png.flaticon.com/128/3222/3222794.png");

    Image imageCloudy = new Image("https://cdn-icons-png.flaticon.com/128/2930/2930014.png");

    Image imageOvercast = new Image("https://cdn-icons-png.flaticon.com/128/3920/3920590.png");

    Image imageSunny = new Image("https://cdn-icons-png.flaticon.com/128/3222/3222672.png");

    Image imageRain = new Image("https://cdn-icons-png.flaticon.com/128/3313/3313888.png");
    @FXML
    private Button btnAddTask;

    @FXML
    private Button btnExit;

    @FXML
    private Label lblGreeting;

    @FXML
    private Label lblGreeting1;

    @FXML
    private Label lblName;

    @FXML
    private Label lblName1;

    @FXML
    private Label lblName11;

    @FXML
    private Label lblName111;

    @FXML
    private Label lblProjectCount;

    @FXML
    private ScrollPane spTaskItems;

    @FXML
    private ScrollPane spTaskItems1;

    @FXML
    private TextField tfSearch;

    @FXML
    private VBox vTaskItems;

    @FXML
    private VBox vUpcomingTaskItems;
    @FXML
    private Text txtDegrees = new Text("Degrees");

    @FXML
    private ImageView Weather = new ImageView();

    @FXML
    private Button btnRefresh;

    @FXML
    private void closeWindow(ActionEvent event) {
        if (event.getSource() == btnExit)
            System.exit(0);
    }

    @FXML
    private void addNewTask(ActionEvent event) {
        btnAddTask.setOnAction(event1 -> {
            ((Stage)((Node)event1.getSource()).getScene().getWindow()).hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("AddTask.fxml"));

            try{
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

            ((Stage)((Node)event1.getSource()).getScene().getWindow()).show();
        });
    }

    @FXML
    private void Refresh(ActionEvent actionEvent) {
        try {
            WeatherConnection city = new WeatherConnection("tashkent");

            btnRefresh.setOnAction(event -> {

                changeIconWeather(city);
                txtDegrees.setText(city.getTemp_C_Api() + "°C");
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // load tasks from Data Base
        DataBaseHandler dbHandler = new DataBaseHandler();
        ArrayList<TaskModel> model = dbHandler.getTodayTask();

        vTaskItems.setSpacing(5);

        Node [] nodes = new Node[model.size()];

        try {
            for (int i = 0; i < nodes.length; ++i) {
                FXMLLoader loader = new FXMLLoader(
                        HelloApplication.class.getResource("TaskItem.fxml")
                );
                TaskItemController controller = new TaskItemController();
                loader.setController(controller);
                nodes[i] = loader.load();
                vTaskItems.getChildren().add(nodes[i]);
                controller.setTask(model.get(i));
            }
        } catch (IOException e) {
            System.err.println("Error Creating Tasks...");
            System.err.println(e.getMessage());
            System.exit(1);
        }


        // load Upcoming Tasks
        vUpcomingTaskItems.setSpacing(5);
        Node [] nodesUpcoming = new Node[10];
        try {
            for (int i = 0; i < nodesUpcoming.length; ++i) {
                nodesUpcoming[i] = FXMLLoader.load(
                        HelloApplication.class.getResource("TaskItem.fxml")
                );
                vUpcomingTaskItems.getChildren().add(nodesUpcoming[i]);
            }
        } catch (IOException e) {
            System.err.println("Error Creating Tasks...");
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private void changeIconWeather(WeatherConnection connection){
        if(connection.getText().toLowerCase().contains("snow")){
            Weather.setImage(imageSnow);
        }
        else if(connection.getText().toLowerCase().contains("rain")){
            Weather.setImage(imageRain);
        }
        else if(connection.getText().toLowerCase().contains("sunny")){
            Weather.setImage(imageSunny);
        }
        else if(connection.getText().toLowerCase().contains("cloudy")){
            Weather.setImage(imageCloudy);
        }
        else if(connection.getText().toLowerCase().contains("overcast")){
            Weather.setImage(imageOvercast);
        }
        else if(connection.getText().toLowerCase().contains("clear")){
            Weather.setImage(imageClear);
        }
        else {
            Weather.setImage(imageClear);
        }

    }
}