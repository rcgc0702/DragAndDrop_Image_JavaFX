package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.File;


public class Controller {

    @FXML
    Button btn_chooseFile;
    @FXML
    HBox horizontal;
    @FXML
    GridPane gridpane2;
    private Image image01;
    @FXML
    ImageView imageview2;
    private File selected = null;
    Group agroup = new Group();
    @FXML
    BorderPane bpane;

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

                dragboard.setDragView(img);
                ClipboardContent clipboardContent = new ClipboardContent();
                clipboardContent.putImage(img);
                dragboard.setContent(clipboardContent);
                event.consume();
            }
        };

        btn_chooseFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                selected = fc.showOpenDialog(new TempWindow());
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

        agroup.getChildren().add(horizontal);
        bpane.getChildren().add(agroup);


        gridpane2.setOnDragEntered(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {

                System.out.println("entereD?");

                Dragboard db = event.getDragboard();
                Image anotherImage;

                if (db.hasUrl()) {

                    anotherImage = new Image(db.getUrl());
                    imageview2.setPreserveRatio(true);
                    imageview2.setImage(anotherImage);
                }
                event.consume();
            }
        });
    }
}
