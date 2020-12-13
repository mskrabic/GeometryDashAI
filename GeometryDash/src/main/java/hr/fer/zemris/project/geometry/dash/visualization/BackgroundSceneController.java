package hr.fer.zemris.project.geometry.dash.visualization;

import java.io.IOException;

import hr.fer.zemris.project.geometry.dash.model.GameEngine;
import hr.fer.zemris.project.geometry.dash.model.Utils;
import hr.fer.zemris.project.geometry.dash.model.listeners.LoggedInListener;
import hr.fer.zemris.project.geometry.dash.model.settings.GameConstants;
import hr.fer.zemris.project.geometry.dash.visualization.ai.AIOptionsController;
import hr.fer.zemris.project.geometry.dash.visualization.level.LevelEditorSceneController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class BackgroundSceneController {

	@FXML
	private ImageView background1;

	@FXML
	private ImageView background2;

	@FXML
	private ImageView background3;

	@FXML
	private Rectangle overlay;

	@FXML
	private ImageView playButton;

	@FXML
	private ImageView charactersButton;
	
	@FXML
	private ImageView levelEditorButton;

	@FXML
	private ImageView achievementsButton;

	@FXML
	private ImageView settingsButton;

	@FXML
	private ImageView statsButton;

	@FXML
	private StackPane rootPane;

	@FXML
	private Button logout;
	
	@FXML
	private AnchorPane logoutOverlay;

	@FXML
	private ImageView startBackgroundMusic;

	@FXML
	private ImageView stopBackgroundMusic;

	@FXML
	private ImageView nextBackgroundMusic;
	
	@FXML
    private ImageView play_ai;

	/**
	 * Reference to the game engine
	 */
	private GameEngine gameEngine;
	
	private LoggedInListener listener;


	@FXML
	private void settingsButtonClicked(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource(GameConstants.pathToVisualization + "SettingsScene.fxml"));
		loader.load();
		SettingsSceneController controller = loader.getController();
		controller.setPreviousSceneRoot(rootPane);
		controller.setGameEngine(gameEngine);
		controller.setListener(listener);
	}

	@FXML
	private void achievementsButtonClicked(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource(GameConstants.pathToVisualization + "AchievementsScene.fxml"));
		loader.load();
		AchievementsSceneController controller = loader.getController();
		controller.setPreviousSceneRoot(rootPane);
		controller.setGameEngine(gameEngine);
		controller.init();
	}

	@FXML
	private void statsButtonClicked(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource(GameConstants.pathToVisualization + "StatsScene.fxml"));
		loader.load();
		StatsSceneController controller = loader.getController();
		controller.setPreviousSceneRoot(rootPane);
		controller.setGameEngine(gameEngine);
		controller.init();
	}

	@FXML
	private void characterSelectButtonClicked(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource(GameConstants.pathToVisualization + "CharacterSelectScene.fxml"));
		loader.load();
		CharacterSelectController controller = loader.getController();
		controller.setPreviousSceneRoot(rootPane);
		controller.setGameEngine(gameEngine);
		controller.init();
	}

	@FXML
	private void playButtonClicked(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource(GameConstants.pathToVisualization + "ChoosePlayerScene.fxml"));
		loader.load();
		ChoosePlayerController controller = loader.getController();
		controller.setPreviousSceneRoot(rootPane);
		//gameEngine.getSettings().getBackgroundMusicPlayer().stop();
	}

	@FXML
	private void levelEditorButtonClicked(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource(GameConstants.pathToVisualization + "level/LevelEditorScene.fxml"));
		loader.load();
		LevelEditorSceneController controller = loader.getController();
		controller.setPreviousSceneRoot(rootPane);
		controller.setListeners();
		controller.setGameEngine(gameEngine);
		gameEngine.getGameStateListener().levelEditorModeEntered(controller.getGraphicsContext());
//		gameEngine.start();
	}
	
	@FXML
	private void logoutButtonClicked(MouseEvent event) throws IOException {
		gameEngine.getUserListener().logout();
		logout.setVisible(false);
		logout.setMouseTransparent(true);	
		logoutOverlay.setVisible(true);
	}
	
    @FXML
    void aiClicked(MouseEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(GameConstants.pathToVisualization + "AI/AIOptionsScene.fxml"));
    	loader.load();
    	AIOptionsController controller = loader.getController();
    	controller.setPreviousSceneRoot(rootPane);
    }
	
	@FXML
	private void removeOverlay(MouseEvent event) throws IOException {
		logoutOverlay.setVisible(false);
	}
	
	@FXML
	public void initialize() {
		Utils.animateBackground(overlay, background1, background2, background3);
	}
	
	public void init() {
		logout.setVisible(gameEngine.getSession() != null);
		logout.setMouseTransparent(gameEngine.getSession() == null);
		
		listener = () -> {
			logout.setVisible(true);
			logout.setMouseTransparent(false);
		};
	}

	/**
	 * Sets game engine
	 * @param gameEngine
	 */
	public void setGameEngine(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
	}

	@FXML
	void nextBackgroundMusicClicked(MouseEvent event) {
		gameEngine.getSettings().getBackgroundMusicPlayer().next();
	}

	@FXML
	void startBackgroundMusicClicked(MouseEvent event) {
		gameEngine.getSettings().getBackgroundMusicPlayer().start();
	}

	@FXML
	void stopBackgroundMusicClicked(MouseEvent event) {
		gameEngine.getSettings().getBackgroundMusicPlayer().stop();
	}
}
