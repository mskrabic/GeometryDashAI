package hr.fer.zemris.project.geometry.dash.model.drawables.environment;

import hr.fer.zemris.project.geometry.dash.model.math.Vector2D;
import hr.fer.zemris.project.geometry.dash.model.GameObject;
import hr.fer.zemris.project.geometry.dash.model.Utils;
import hr.fer.zemris.project.geometry.dash.model.drawables.player.Player;
import hr.fer.zemris.project.geometry.dash.model.settings.GameConstants;
import javafx.scene.canvas.GraphicsContext;

public class LeftSpike extends Obstacle {
    @Override
    public Vector2D getCenterPosition() {
        return getCurrentPosition().translated(new Vector2D(getWidth()/2.0, getHeight()/2.0));
    }

    //note: position here is lower left corner!
    public LeftSpike(Vector2D position, String uriIcon) {
    	setInitialPosition(position.copy());;
        setCurrentPosition(position);
        this.setWidth(GameConstants.iconWidth); //visina trokuta
        this.setHeight(GameConstants.iconHeight); // baza trokuta
        setIconPath(uriIcon);
        setIcon(Utils.loadIcon(uriIcon, GameConstants.iconWidth, GameConstants.iconHeight));
        setName("LeftSpike");
    }

    /**
     * Constructor that accepts all {@linkplain GameObject}'s parameters
     * @param name object name
     * @param currentPosition current position
     * @param height height
     * @param width width
     * @param iconPath path to icon
     */
    public LeftSpike(String name, Vector2D currentPosition, int height, int width, String iconPath) {
        setName(name);
        setInitialPosition(currentPosition.copy());;
        setCurrentPosition(currentPosition);
        setHeight(height);
        setWidth(width);
        setIconPath(iconPath);
        setIcon(Utils.loadIcon(iconPath, GameConstants.iconWidth, GameConstants.iconHeight));
    }

    @Override
    public boolean checkCollisions(Player player) {
        Vector2D centerDiff = this.getCenterPosition().translated(player.getCenterPosition().reversed());
        double xDiff = centerDiff.getX();   // ako je xDiff pozitivan, player se nalazi ~lijevo od blocka
        double yDiff = centerDiff.getY();   // ako je yDiff pozitivan, player se nalazi ~iznad blocka

        return  (((xDiff + (2 * Math.abs(yDiff))) <= getHeight()) && Math.abs(xDiff) <= getHeight()
                && Math.abs(yDiff) <= getHeight() * 0.9);
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(this.getIcon(), this.getCurrentPosition().getX(), this.getCurrentPosition().getY());
    }

    @Override
    public GameObject copy() {
        return new Spike(getCurrentPosition().copy(), getIconPath());
    }

}
