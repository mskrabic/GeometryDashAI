package hr.fer.zemris.project.geometry.dash.model;

import com.google.gson.annotations.Expose;

import hr.fer.zemris.project.geometry.dash.model.listeners.UserListener;
import hr.fer.zemris.project.geometry.dash.model.settings.Account;
import hr.fer.zemris.project.geometry.dash.model.stats.Stats;

/**
 * Stores session informations about user, his statistic and whether or not is some user logged.
 * @author Andi �krgat
 *
 */
public class Session {

	/**
	 * Information about current user
	 */
	@Expose
	private Account account;
	
	/**
	 * User's statistic
	 */
	@Expose
	private Stats stats;
	
	
	/**
	 * Create new session
	 * @param firstName first name
	 * @param lastName last name
	 * @param username username
	 * @param password password
	 */
	public Session(String firstName, String lastName, String username, String password) {
		this.account = new Account(firstName, lastName, username, password);		
		stats = new Stats();
	}
	
	public Session(Account account, Stats stats) {
		super();
		this.account = account;
		this.stats = stats;
	}



	/**
	 *  @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * @return the stats
	 */
	public Stats getStats() {
		return stats;
	}
	
	
	
}
