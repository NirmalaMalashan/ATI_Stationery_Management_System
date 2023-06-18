package ati.ati;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainController {
    @FXML
    private AnchorPane MainPanel;

    public void Null(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("attendance.fxml"));
        MainPanel.getChildren().setAll(pane);
    }

    public void Issued(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Issued.fxml"));
        MainPanel.getChildren().setAll(pane);
    }

    public void Supplies(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Supply.fxml"));
        MainPanel.getChildren().setAll(pane);
    }

    public void Reports(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Report.fxml"));
        MainPanel.getChildren().setAll(pane);
    }

    public void Manage(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Manage Item.fxml"));
        MainPanel.getChildren().setAll(pane);
    }
}
