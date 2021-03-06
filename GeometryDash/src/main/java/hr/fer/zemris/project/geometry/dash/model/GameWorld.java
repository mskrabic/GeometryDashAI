package hr.fer.zemris.project.geometry.dash.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import hr.fer.zemris.project.geometry.dash.model.drawables.environment.*;
import hr.fer.zemris.project.geometry.dash.model.io.ZipUtil;
import hr.fer.zemris.project.geometry.dash.model.listeners.PlayerListener;
import hr.fer.zemris.project.geometry.dash.model.math.Vector2D;
import hr.fer.zemris.project.geometry.dash.model.drawables.player.Player;
import hr.fer.zemris.project.geometry.dash.model.level.Level;
import hr.fer.zemris.project.geometry.dash.model.level.LevelManager;
import hr.fer.zemris.project.geometry.dash.model.serialization.GameObjectDeserializer;
import hr.fer.zemris.project.geometry.dash.model.settings.GameConstants;
import hr.fer.zemris.project.geometry.dash.model.settings.Options;
import hr.fer.zemris.project.geometry.dash.model.settings.character.CharactersSelector;
import hr.fer.zemris.project.geometry.dash.visualization.PlayerDeathSceneController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

/**
 * Manages all current objects on the scene.
 *
 * @author Andi Škrgat
 */
public class GameWorld {

    /**
     * Reference to the {@linkplain WorldPlayerListener}
     */
    private PlayerListener playerListener;

    /**
     * Reference to the {@linkplain LevelManager}
     */
    private LevelManager levelManager;

    /**
     * {@linkplain CharactersSelector}
     */
    private CharactersSelector charactersSelector;

    /**
     * Graphics context TODO documentation
     */
    private GraphicsContext graphics;

    /**
     * We need to keep reference on the player for camera moving
     */
    private GameObject player;

    /**
     * Reference on the floor
     */
    private GameObject floor;

    /**
     * Renderer
     */
    private Renderer renderer;

    /**
     * @return the graphics
     */
    public GraphicsContext getGraphics() {
        return graphics;
    }

    /**
     * @param graphics the graphics to set
     */
    public void setGraphics(GraphicsContext graphics) {
        this.graphics = graphics;
    }

    /**
     * @return level manager
     */
    public LevelManager getLevelManager() {
        return levelManager;
    }

    /**
     * @return characters selector
     */
    public CharactersSelector getCharactersSelector() {
        return charactersSelector;
    }

    /**
     * @return get floor
     */
    public GameObject getFloor() {
        return floor;
    }

    /**
     * @return renderer
     */
    public Renderer getRenderer() {
        return renderer;
    }

    /**
     * @return player
     */
    public GameObject getPlayer() {
        return player;
    }

    /**
     * @return the playerListener
     */
    public PlayerListener getPlayerListener() {
        return playerListener;
    }

    /**
     * Initializes characters selector and creates scene for playing. Temporary for testing collisions and jumping on platforms
     */
    public GameWorld() {
        charactersSelector = new CharactersSelector();
        levelManager = new LevelManager();
        playerListener = new WorldPlayerListener();
        createScene();
    }

    public void reset() {
        createScene();
    }

    /**
     * Creates temporary scene
     */
    private void createScene() {
        player = new Player(new Vector2D(0, GameConstants.floorPosition_Y - GameConstants.iconHeight - 5), new Vector2D(GameConstants.playerSpeed_X, GameConstants.playerSpeed_Y));
        floor = new Floor(new Vector2D(0, GameConstants.floorPosition_Y + GameConstants.levelToWorldOffset));
        //Set<GameObject> levelObjects = new GameObjectDeserializer(GameConstants.levelToWorldOffset).deserialize(ZipUtil.openZipFile(GameConstants.pathToLevelsFolder, "Level1"));
        //when we create choose level scene then we will change these lines, maybe create scene will be public and will receive levelName
        // and level manager will have from start predefines levels, you can call levelManeger.startLevelWithName(levelName);
        // but for testing it's okay
        Set<GameObject> levelObjects = new HashSet<>();
        levelObjects.add(new RightSpike(new Vector2D(GameConstants.playerPosition_X + 5*GameConstants.iconHeight, -3 * GameConstants.iconHeight + GameConstants.floorPosition_Y + 3), GameConstants.rightSpikeImage));
        levelObjects.add(new RightSpike(new Vector2D(GameConstants.playerPosition_X + 15*GameConstants.iconHeight, -3 * GameConstants.iconHeight +GameConstants.floorPosition_Y + 3), GameConstants.rightSpikeImage));
        levelObjects.add(new RightSpike(new Vector2D(GameConstants.playerPosition_X + 30*GameConstants.iconHeight,  GameConstants.floorPosition_Y + 3), GameConstants.rightSpikeImage));
        levelObjects.add(new Block(new Vector2D(GameConstants.playerPosition_X + 29 * GameConstants.iconHeight,  GameConstants.floorPosition_Y + 3), GameConstants.blockImage));
        levelObjects.add(new RightSpike(new Vector2D(GameConstants.playerPosition_X + 35 * GameConstants.iconHeight, GameConstants.floorPosition_Y + 3), GameConstants.rightSpikeImage));
        levelObjects.add(new RightSpike(new Vector2D(GameConstants.playerPosition_X + 49 * GameConstants.iconHeight, GameConstants.floorPosition_Y + 3), GameConstants.rightSpikeImage));
        levelObjects.add(new RightSpike(new Vector2D(GameConstants.playerPosition_X + 55 * GameConstants.iconHeight, GameConstants.floorPosition_Y + 3), GameConstants.rightSpikeImage));
        levelObjects.add(new RightSpike(new Vector2D(GameConstants.playerPosition_X + 60 * GameConstants.iconHeight, GameConstants.floorPosition_Y + 3), GameConstants.rightSpikeImage));
        levelObjects.add(new Block(new Vector2D(GameConstants.playerPosition_X + 35 * GameConstants.iconHeight, GameConstants.floorPosition_Y + 3), GameConstants.blockImage));
        levelObjects.add(new Spike(new Vector2D(GameConstants.playerPosition_X + 63 * GameConstants.iconHeight, GameConstants.floorPosition_Y + 3 - 2 * GameConstants.iconHeight), GameConstants.spikeImage));
        levelObjects.add(new Platform(new Vector2D(GameConstants.playerPosition_X + 40 * GameConstants.iconHeight, GameConstants.floorPosition_Y + 3 - GameConstants.iconHeight), GameConstants.iconWidth, GameConstants.platformImage));
        levelObjects.add(new Platform(new Vector2D(GameConstants.playerPosition_X + 41 * GameConstants.iconHeight, GameConstants.floorPosition_Y + 3 - GameConstants.iconHeight), GameConstants.iconWidth, GameConstants.platformImage));
        levelObjects.add(new Platform(new Vector2D(GameConstants.playerPosition_X + 42 * GameConstants.iconHeight, GameConstants.floorPosition_Y + 3 - GameConstants.iconHeight), GameConstants.iconWidth, GameConstants.platformImage));
        levelObjects.add(new Platform(new Vector2D(GameConstants.playerPosition_X + 43 * GameConstants.iconHeight, GameConstants.floorPosition_Y + 3 - GameConstants.iconHeight), GameConstants.iconWidth, GameConstants.platformImage));
        levelObjects.add(new Platform(new Vector2D(GameConstants.playerPosition_X + 44 * GameConstants.iconHeight, GameConstants.floorPosition_Y + 3 - GameConstants.iconHeight), GameConstants.iconWidth, GameConstants.platformImage));
        levelObjects.add(new Block(new Vector2D(GameConstants.playerPosition_X + 30 * GameConstants.iconHeight, GameConstants.floorPosition_Y + 3), GameConstants.blockImage));
        levelObjects.add(new Block(new Vector2D(GameConstants.playerPosition_X + 31 * GameConstants.iconHeight, GameConstants.floorPosition_Y + 3), GameConstants.blockImage));
        levelObjects.add(new Block(new Vector2D(GameConstants.playerPosition_X + 34 * GameConstants.iconHeight, GameConstants.floorPosition_Y + 3), GameConstants.blockImage));
        levelObjects.add(new Block(new Vector2D(GameConstants.playerPosition_X + 35 * GameConstants.iconHeight, GameConstants.floorPosition_Y + 3), GameConstants.blockImage));
        levelObjects.add(new Spike(new Vector2D(GameConstants.playerPosition_X + 33 * GameConstants.iconHeight, GameConstants.floorPosition_Y + 3), GameConstants.spikeImage));
        levelObjects.add(new Spike(new Vector2D(GameConstants.playerPosition_X + 30 * GameConstants.iconHeight, GameConstants.floorPosition_Y + 3), GameConstants.spikeImage));
        levelObjects.add(new Spike(new Vector2D(GameConstants.playerPosition_X + 25 * GameConstants.iconHeight, GameConstants.floorPosition_Y + 3), GameConstants.spikeImage));
        levelObjects.add(new Spike(new Vector2D(GameConstants.playerPosition_X + 20 * GameConstants.iconHeight, GameConstants.floorPosition_Y + 3 - GameConstants.iconHeight), GameConstants.spikeImage));

        levelObjects.add(player);
        levelObjects.add(floor);

        levelManager.addLevel("Level1", levelObjects);
        levelManager.startLevelWithName("Level1");
        renderer = new Renderer(levelObjects);
        ((Floor) floor).setCamera(renderer.getCamera());
    }

    /**
     * Checks for relations between camera, player and ground
     */
    public boolean update() {
//    	System.out.println("Početna x: " + (player.getCurrentPosition().getX() - GameConstants.playerPosition_X));
//    	System.out.println("Završna x: " + (player.getCurrentPosition().getX() - GameConstants.playerPosition_X + GameConstants.WIDTH));
        checkPlayerGround();
        checkPlayerCamera_X();
        checkPlayerCamera_Y();
        checkCameraGround_Y();
        if (checkCollision()) {
            return false;
        }
        renderer.render();
        if (player.initialPosition.getX() != 0) {
        	Scanner sc = new Scanner(System.in);
        	sc.next();
        	sc.close();
        };
        return true;
    }

    private boolean checkCollision() {
        for (GameObject gameObject : levelManager.getCurrentLevel().getLevelData()) {
            if (gameObject instanceof Obstacle) {
                if (((Obstacle) gameObject).checkCollisions((Player) player)) {
                	return true;
                }
            }
        }
        return false;
    }

    /**
     * Camera's final y position. Tweak values!!
     */
    private void checkCameraGround_Y() {
        double cameraPos_Y = renderer.getCamera().getPosition().getY();
        if (cameraPos_Y > GameConstants.cameraGroundOffset_Y) {
            renderer.getCamera().getPosition().setY(GameConstants.cameraGroundOffset_Y);
        }
    }

    /**
     * Distance player to camera - y coordinate
     */
    private void checkPlayerCamera_Y() {
        double playerPos_Y = player.getCurrentPosition().getY();
        double cameraPos_Y = renderer.getCamera().getPosition().getY();
        if (playerPos_Y - cameraPos_Y > GameConstants.playerPosition_Y) {
            renderer.getCamera().getPosition().setY(playerPos_Y - GameConstants.playerPosition_Y);
        }
    }

    /**
     * Distance player to camera - x coordinate
     */
    private void checkPlayerCamera_X() {
        double playerPos_X = player.getCurrentPosition().getX();
        double cameraPos_X = renderer.getCamera().getPosition().getX();
        if (playerPos_X - cameraPos_X > GameConstants.playerPosition_X) {
            renderer.getCamera().getPosition().setX(playerPos_X - GameConstants.playerPosition_X);
        }
    }

    /**
     * Checks relation between player and ground
     */
    private void checkPlayerGround() {
        double playerPos_Y = player.getCurrentPosition().getY();
        double floorPos_Y = floor.getCurrentPosition().getY();
        if (playerPos_Y + GameConstants.playerGroundOffset_Y >= floorPos_Y) {
            ((Player) player).touchesGround();
            player.getCurrentPosition().setY(floorPos_Y - GameConstants.playerGroundOffset_Y);
            ((Player) player).setTouchingGround(true);
        } else {
            ((Player) player).setTouchingGround(false);
        }

        //prolazi sve gameObjects na levelu i ako je neki od njih blok ili platforma te ako je player na njemu kaze
        //playeru da smije skociti
        for (GameObject gameObject : levelManager.getCurrentLevel().getLevelData()) {
            if (gameObject instanceof Block) {
                if (((Block) gameObject).playerIsOn((Player) player)) {
                    ((Player) player).touchesGround();
                    player.getCurrentPosition().setY(gameObject.getCurrentPosition().getY() - GameConstants.iconHeight);
                }
            } else if (gameObject instanceof Platform) {
                if (((Platform) gameObject).playerIsOn((Player) player)) {
                    ((Player) player).touchesGround();
                    player.getCurrentPosition().setY(gameObject.getCurrentPosition().getY() - GameConstants.iconHeight);
                }
            }
        }
    }

    /**
     * A {@link PlayerListener} implementation
     */
    class WorldPlayerListener implements PlayerListener {

        /**
         * Player is in the air
         */
        @Override
        public void playerIsInAir() {
            // TODO otkriti kako ovo iskoristiti
        }

        /**
         * Player is on the platform
         */
        @Override
        public void playerIsOnPlatform() {
            // TODO otkriti kako ovo iskoristiti
        }

        /**
         * Player is on the floor
         */
        @Override
        public void playerIsOnFloor() {
            // TODO otkriti kako ovo iskoristiti
        }

        /**
         * Player is dead
         * @throws IOException 
         */
        @Override
        public void playerIsDead(Options options, GameEngine gameEngine, double time) throws IOException {
            if (options.isAutoRetry()) { // ako je auto retry onda sve kreni ispocetka
            	gameEngine.reset();
            } else { //ako ne otvori scenu u kojoj će moć izabrat da li želi restart ili u game menu
            	FXMLLoader loader = new FXMLLoader(getClass().getResource(GameConstants.pathToVisualization + "PlayerDeathScene.fxml"));
            	loader.load();
            	PlayerDeathSceneController controller = loader.<PlayerDeathSceneController>getController();
            	controller.setGameEngine(gameEngine);
            	Stage stage = (Stage)Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
            	Pane rootPane = stage == null ? null : (Pane)stage.getScene().getRoot();
            	controller.setPreviousSceneRoot(rootPane);
            	controller.showInformation(
            			levelManager.getCurrentLevel().getLevelName(),
            			Long.toString(levelManager.getCurrentLevel().getTotalAttempts()),
            			levelManager.getCurrentLevel().getLevelPercentagePassNormalMode(),
            			Long.toString(levelManager.getCurrentLevel().getTotalJumps()),
            			time
            			);
            }
        }

        @Override
        public void playerJumped() {
            ((Player) player).jump();
        }

    }

}
