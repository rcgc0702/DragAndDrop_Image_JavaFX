package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.File;


public class Controller {

    @FXML
    Button btn_chooseFile;
    @FXML
    HBox horizontal;
    @FXML
    HBox horiz_2;
    @FXML
    BorderPane bpane;

    private File selected = null;

    public void initialize() {

        FileChooser fc = new FileChooser();
        fc.setTitle("Choose an image...");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images","*.png","*.gif","*.jpg"));



        EventHandler<MouseEvent> anEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("I got detected");

                ImageView bb = (ImageView) event.getSource();
                Image img = bb.getImage();
                Dragboard dragboard = bb.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent cbc = new ClipboardContent();
                cbc.putImage(img);
                dragboard.setDragView(img);
                dragboard.setContent(cbc);
                event.consume();
            }
        };

        btn_chooseFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                selected = fc.showOpenDialog(new TempWindow());

                if (selected == null) return;

                Image image01 = new Image(selected.toURI().toString());
                ImageView animageview = new ImageView();
                animageview.setImage(image01);
                animageview.setPreserveRatio(true);
                animageview.setImage(image01);
                animageview.setFitWidth(200);
                horizontal.getChildren().add(animageview);
                animageview.setOnDragDetected(anEvent);
                event.consume();
            }
        });

        bpane.setOnDragEntered(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                event.acceptTransferModes(TransferMode.MOVE);
                Dragboard db = event.getDragboard();
                Image img = new Image(db.getUrl());

                ImageView newImgView = new ImageView();
                newImgView.setImage(img);
                newImgView.setPreserveRatio(true);
                newImgView.setFitWidth(200);

                horiz_2.getChildren().add(newImgView);
                event.consume();
            }
        });

    }

}
