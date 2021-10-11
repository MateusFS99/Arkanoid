package arkanoid;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Arkanoid extends Application{
    
    @Override
    public void start(Stage primaryStage){
        
        Game root = new Game();
        Scene scene = new Scene(root, 800, 680);
        root.Menu();
        
        primaryStage.setTitle("Arkanoid v3.5");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        
        launch(args);
    }
    
}
