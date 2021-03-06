package hr.fer.zemris.project.geometry.dash.model.settings.character;

import hr.fer.zemris.project.geometry.dash.model.Utils;
import hr.fer.zemris.project.geometry.dash.model.settings.GameConstants;
import javafx.scene.image.Image;


/**
 * Represents character in game. Every character in game has icon and can be locked or unlocked from the beginning of the game
 * @author Andi Skrgat
 */
public class CharacterObject {
	
	/**
	 * {@linkplain ImageIcon}
	 */
	private Image icon;
	
	/**
	 * Uri to icon photo
	 */
	private String uri;
	
	/**
	 * Not all characters are available from the beginning
	 */
	private boolean locked;
	
	/**
	 * 
	 * @param uri Uri for loading icon
	 * @param locked Not all characters are unlocked from the start of the game
	 */
	public CharacterObject(String uri, boolean locked) {
		this.icon = loadIcon(uri);
		this.locked = locked;
		this.uri = uri;
	}
	
	/**
	 * Loads icon with the help of {@linkplain Utils} class
	 * @param uri path to the icon
	 * @return loaded icon
	 */
	private Image loadIcon(String uri) {

		//return Utils.loadIcon(GameConstants.pathToIcons + uri);

		return Utils.loadIcon(GameConstants.pathToIcons + uri, GameConstants.iconWidth, GameConstants.iconHeight);
	}
	
	/**
	 * @return  locked
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * @param locked the locked to set
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * @return the icon
	 */
	public Image getIcon() {
		return icon;
	}
	
	/**
	 * @return the icon uri
	 */
	public String getUri() {
		return uri;
	}
	

}
