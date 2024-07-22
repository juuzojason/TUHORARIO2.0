package com.example.tuhorario2.Views;

import com.example.tuhorario2.Controllers.GeneralTitleController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.border.Border;
import java.io.IOException;
import java.util.Objects;

public class ViewFactory {
    //Is the class that controls all the views that should be shown whenever a button is pressed
    Stage currStage;
    //DashboardView

    public ViewFactory(){

    }

    /*
    public ___ getDashboardView(){
        if (dashboard == null){
            try {
                Load DashBoardView
            } catch {
                get the exception
            }
        }
    }
    */

    public void UserView(){
        createStage("/Fxml/Admin/User/User.fxml");
    }

    public void LoginView(){
        createStage("/Fxml/Admin/Sections/LOGIN.fxml");
    }

    public void RegisterView(){
        createStage("/Fxml/Admin/Sections/Registro.fxml");
    }

    private void createStage(String path){
        if (currStage != null) currStage.close();
        Scene scn = null;
        BorderPane bd;
        try{
            FXMLLoader fxml = new FXMLLoader(getClass().getResource(path));
            bd = fxml.load();
            scn = new Scene(bd);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stg = new Stage(StageStyle.UNDECORATED);
        stg.setScene(scn);
        stg.show();
        currStage = stg;
        setTitleBar(bd,stg);
    }

    private void setTitleBar(BorderPane window, Stage st){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/GeneralTitleBar.fxml"));
            AnchorPane tb = fxmlLoader.load();
            GeneralTitleController c = fxmlLoader.getController();
            c.setStage(st);
            window.setTop(tb);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
