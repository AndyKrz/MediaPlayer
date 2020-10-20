package sample;

import java.io.File;
import java.net.MalformedURLException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {

    FileChooser mediaChooser;

    Player mediaPlayer;

    MenuBar menu;
    Menu fileMenu;
    MenuItem openItem;

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Media Player");
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 600, 400);
        mediaChooser = new FileChooser();

        primaryStage.setScene(scene);
        menu = new MenuBar();
        fileMenu = new Menu("File");
        openItem = new MenuItem("Open");
        fileMenu.getItems().add(openItem);
        menu.getMenus().add(fileMenu);

        openItem.setOnAction((e) -> {
            try {
                File mediaFile = mediaChooser.showOpenDialog(primaryStage);
                if(mediaPlayer != null) {
                    mediaPlayer.player.dispose();
                }
                System.out.println(mediaFile.getAbsolutePath() + " : " + mediaFile.toURI().toURL().toExternalForm());
                mediaPlayer = new Player(mediaFile.toURI().toURL().toExternalForm());
                mediaPlayer.view.setFitWidth(scene.getWidth());
                root.setCenter(mediaPlayer);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        });

        root.setTop(menu);

        primaryStage.widthProperty().addListener((obs,oldVal,newVal) -> {
                    if(mediaPlayer != null)
                mediaPlayer.view.setFitWidth(scene.getWidth());

            });
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
