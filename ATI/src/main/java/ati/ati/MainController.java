package ati.ati;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.SQLException;

public class MainController {
    public Button btnsupply;
    public Button btnissied;
    public Button btnnull;
    public Button btnreport;
    public Button btnmanage;
    @FXML
    private AnchorPane MainPanel;

    public void initialize() throws SQLException, IOException {
        // TODO Auto-generated method stub
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Null.fxml"));
        MainPanel.getChildren().setAll(pane);
        btnnull.setStyle("-fx-background-radius:30; -fx-background-color: #a3a3c2;");
        btnissied.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");
        btnsupply.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");
        btnsupply.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");
        btnreport.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");
        btnmanage.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");

    }
    public void Null(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Null.fxml"));
        MainPanel.getChildren().setAll(pane);
        btnnull.setStyle("-fx-background-radius:30; -fx-background-color: #a3a3c2;");
        btnissied.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");
        btnsupply.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");
        btnsupply.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");
        btnreport.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");
        btnmanage.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");


    }

    public void Issued(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Issued.fxml"));
        MainPanel.getChildren().setAll(pane);
        btnnull.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");
        btnissied.setStyle("-fx-background-radius:30; -fx-background-color: #a3a3c2;");
        btnsupply.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");
        btnsupply.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");
        btnreport.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");
        btnmanage.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");
    }

    public void Supplies(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Supply.fxml"));
        MainPanel.getChildren().setAll(pane);
        btnnull.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");
        btnissied.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");
        btnsupply.setStyle("-fx-background-radius:30; -fx-background-color: #a3a3c2;");
        btnreport.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");
        btnmanage.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");

    }

    public void Reports(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Report.fxml"));
        MainPanel.getChildren().setAll(pane);
        btnnull.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");
        btnissied.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");
        btnsupply.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");
        btnsupply.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");
        btnreport.setStyle("-fx-background-radius:30; -fx-background-color: #a3a3c2;");
        btnmanage.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");
    }

    public void Manage(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Manage Item.fxml"));
        MainPanel.getChildren().setAll(pane);
        btnnull.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");
        btnissied.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");
        btnsupply.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");
        btnsupply.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");
        btnreport.setStyle("-fx-background-radius:30; -fx-background-color: #52527a;");
        btnmanage.setStyle("-fx-background-radius:30; -fx-background-color: #a3a3c2;");
    }
}
