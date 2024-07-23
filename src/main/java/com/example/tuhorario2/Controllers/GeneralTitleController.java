package com.example.tuhorario2.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.plaf.metal.MetalInternalFrameUI;
import java.net.URL;
import java.util.ResourceBundle;

public class GeneralTitleController implements Initializable {
    public Button MiniButton;
    public Button CLoseButton;
    public ImageView TileImage;
    public Label TitleLabel;

    private Stage st;


    //TODO add implementation draggable to be able to drag all windows

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MiniButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                st.setIconified(true);
            }
        });

        CLoseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                st.close();
            }
        });
    }

    public void setStage(Stage st){
        this.st = st;
    }


}
