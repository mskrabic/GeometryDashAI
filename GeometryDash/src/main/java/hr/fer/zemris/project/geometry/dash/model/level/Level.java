package hr.fer.zemris.project.geometry.dash.model.level;

import java.util.List;
import java.util.Set;

import hr.fer.zemris.project.geometry.dash.model.GameObject;
import hr.fer.zemris.project.geometry.dash.visualization.level.ObjectsOnGrid;

/**
 * Game level with all its data, stats and level name.
 * @author Andi �krgat
 *
 */
public class Level {
	
	/**
	 * Level name
	 */
	private String levelName;
	
	/**
	 * Total attempts on level
	 */
	private long totalAttempts;
	
	/**
	 * Total jumps on level
	 */
	private long totalJumps;
	
	/**
	 * Percentage pass when playing normal mode
	 */
	private short levelPercentagePassNormalMode;
	
	/**
	 * Percentage pass when playing practice mode
	 */
	private short levelPercentagePassPracticeMode;
	
	/**
	 * Initially normal mode is selected for playing
	 */
	private LEVEL_MODE levelMode = LEVEL_MODE.NORMAL_MODE;
	
	/**
	 * Level's data
	 */
	private Set<GameObject> gameObjects;
	
	/**
	 * Level is created with its data
	 * @param objectsOnGrid level's data
	 */
	public Level(String levelName, Set<GameObject> gameObjects) {
		this.levelName = levelName;
		this.gameObjects = gameObjects;
	}

	/**
	 * @param levelMode the levelMode to set
	 */
	public void setLevelMode(LEVEL_MODE levelMode) {
		this.levelMode = levelMode;
	}

	/**
	 * @return the levelMode
	 */
	public LEVEL_MODE getLevelMode() {
		return levelMode;
	}

	/**
	 * @return the totalAttempts
	 */
	public long getTotalAttempts() {
		return totalAttempts;
	}

	/**
	 * increments totalAttempts
	 */
	public void setTotalAttempts() {
		this.totalAttempts++;
	}

	/**
	 * @return the totalJumps
	 */
	public long getTotalJumps() {
		return totalJumps;
	}

	/**
	 * increments totalJumps
	 */
	public void setTotalJumps() {
		this.totalJumps++;
	}

	/**
	 * @return the levelPercentagePassNormalMode
	 */
	public short getLevelPercentagePassNormalMode() {
		return levelPercentagePassNormalMode;
	}

	/**
	 *  increments levelPercentagePassNormalMode
	 */
	public void setLevelPercentagePassNormalMode() {
		this.levelPercentagePassNormalMode++;
	}

	/**
	 * @return the levelPercentagePassPracticeMode
	 */
	public short getLevelPercentagePassPracticeMode() {
		return levelPercentagePassPracticeMode;
	}

	/**
	 * increments levelPercentagePassPracticeMode
	 */
	public void setLevelPercentagePassPracticeMode() {
		this.levelPercentagePassPracticeMode++;
	}

	/**
	 * @return the levelName
	 */
	public String getLevelName() {
		return levelName;
	}

	/**
	 * @param levelName the levelName to set
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	/**
	 * @return level data
	 */
	public Set<GameObject> getLevelData() {
		return gameObjects;
	}

	/**
	 * @param totalAttempts the totalAttempts to set
	 */
	public void setTotalAttempts(long totalAttempts) {
		this.totalAttempts = totalAttempts;
	}

	/**
	 * @param totalJumps the totalJumps to set
	 */
	public void setTotalJumps(long totalJumps) {
		this.totalJumps = totalJumps;
	}

	/**
	 * @param levelPercentagePassNormalMode the levelPercentagePassNormalMode to set
	 */
	public void setLevelPercentagePassNormalMode(short levelPercentagePassNormalMode) {
		this.levelPercentagePassNormalMode = levelPercentagePassNormalMode;
	}

	/**
	 * @param levelPercentagePassPracticeMode the levelPercentagePassPracticeMode to set
	 */
	public void setLevelPercentagePassPracticeMode(short levelPercentagePassPracticeMode) {
		this.levelPercentagePassPracticeMode = levelPercentagePassPracticeMode;
	}

}
