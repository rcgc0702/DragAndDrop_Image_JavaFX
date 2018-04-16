package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.io.File;


public class Controller {

    @FXML
    Button btn_chooseFile;
    @FXML
    ImageView imageview;
    @FXML
    GridPane gridpane2;
    private Image image01;
    @FXML
    ImageView imageview2;
    File selected = null;

    public void initialize() {

        FileChooser fc = new FileChooser();
        fc.setTitle("Choose an image...");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images","*.png","*.gif","*.jpg"));

        btn_chooseFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selected = fc.showOpenDialog(new TempWindow());
                image01 = new Image(selected.toURI().toString());
                imageview.setPreserveRatio(true);
                imageview.setImage(image01);

            }
        });


        imageview.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                System.out.println("I'm being dragged!");

                Dragboard dragboard = imageview.startDragAndDrop(TransferMode.MOVE);
                dragboard.setDragView(image01);
                ClipboardContent clipboardContent = new ClipboardContent();
                clipboardContent.putString(selected.toURI().toString());
                dragboard.setContent(clipboardContent);
                event.consume();
            }
        });


        gridpane2.setOnDragEntered(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {

                Dragboard db = event.getDragboard();
                Image anotherImage;

                if (db.hasString()) {

                    anotherImage = new Image(db.getString());
                    System.out.println(db.getString());
                    imageview2.setPreserveRatio(true);
                    imageview2.setImage(anotherImage);
                    imageview.setImage(null);
                }


            }
        });



    }


}
