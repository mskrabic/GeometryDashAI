package hr.fer.zemris.project.geometry.dash;
import java.io.IOException;

import hr.fer.zemris.project.geometry.dash.model.GameEngine;
import hr.fer.zemris.project.geometry.dash.model.GameWorld;
import hr.fer.zemris.project.geometry.dash.model.Utils;
import hr.fer.zemris.project.geometry.dash.model.settings.GameConstants;
import hr.fer.zemris.project.geometry.dash.model.settings.music.BackgroundMusicPlayer;
import hr.fer.zemris.project.geometry.dash.view.components.GeometryDashTitle;
import hr.fer.zemris.project.geometry.dash.visualization.GameSceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GeometryDash extends Application {

	private GameEngine gameEngine = new GameEngine(100, "Geometry Dash", GameConstants.WIDTH, GameConstants.HEIGHT);
	
    @Override
    public void start(Stage primaryStage) throws IOException {

    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(GameConstants.pathToVisualization + "Settings.fxml"));
    	Parent root1 = fxmlLoader.load();
    	GameSceneController controller = fxmlLoader.<GameSceneController>getController();
    	controller.setGameEngine(gameEngine);
    	Scene scene = new Scene(root1);
        gameEngine.createStageFromData(primaryStage);
        gameEngine.start();
        
//    	tester
//		Media media = new Media(getClass().getResource(GameConstants.pathToSongs + "BlahBlahBlah.mp3").toExternalForm());
//		MediaPlayer mediaPlayer = new MediaPlayer(media);
//		mediaPlayer.setVolume(1);
//		mediaPlayer.play();
//        Utils.createMediaPlayer("BlahBlahBlah.mp3").play();
//        System.out.println("Printaj odmah poslije");
        
		primaryStage.setTitle("Geometry Dash");
		primaryStage.setScene(scene);
	    primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
