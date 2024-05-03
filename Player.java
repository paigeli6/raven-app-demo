import java.io.*;

/**====================================================================================================
 * Player class
 * Andy Liu, Paige Li
 * January 19th, 2023
 * Java, Eclipse 4.21
 * ====================================================================================================
 * <Class>
 * This class creates a template for a Player object.
 * ====================================================================================================
 * <List of Identifiers>
 * level - player level <type int>
 * experiencePoints - experience points the player has <type int>
 * levelUp - experience points needed to level up <type int>
 * currentHealth - player's current health <type int>
 * totalHealth - player's totalHealth <type int>
 * coins - player's coin balance <type int>
 * DATAPOINTS - constant number of data points related to each player
 * 
 * (other identifiers are detailed in individual method headers)
====================================================================================================*/
class Player {
	
	private int level, experiencePoints, levelUp, currentHealth, totalHealth, coins;
	final static private int DATAPOINTS = 6;

	/** Player custom constructor
	 * Creates a Player object from an existing .txt file (in the project folder) and a corresponding username.
	 * 
	 * <List of Local Variables>
	 * fr - object used to read the player file <type FileReader>
	 * br - object used for reading lines of a file <type BufferedReader>
	 * line - a specific line of a the player file <type String>
	 * tempData - array used to store player info from lines (converted to type int), it will be copied into their respective instance variables <type int[]>
	 * fileRow - row of the file, used to track which index of tempData values should go into <type int>
	 * 
	 * @param username - the user's username, used to identify their player file <type String>
	 * @return void
	 */
	Player(String username) {
		try {
			FileReader fr = new FileReader(username + ".txt"); //username.txt is an existing file in the project folder
			BufferedReader br = new BufferedReader(fr);
			String line;
			int tempData[] = new int[DATAPOINTS];
			int fileRow = 0;

			while ((line = br.readLine())!= null) {
				tempData[fileRow] = Integer.parseInt(line.trim()); //puts the read line into the [fileRow] index of tempData, trims excess whitespace on either side
				fileRow++;
			}	
			fr.close();

			level = tempData[0];
			experiencePoints = tempData[1];
			levelUp = tempData[2];
			currentHealth = tempData[3];
			totalHealth = tempData[4];
			coins = tempData[5];
		}catch(Exception e) {
			System.out.println(e);
		} //end of try/catch
	} //end of Player() custom constructor
	
	/** updatePlayerFile procedural method
	 * Updates the user's player file with new info. 
	 * 
	 * <List of Local Variables>
	 * w - object that lets the program write in the user's .txt player file <type Writer>
	 * 
	 * @param username - the user's username, used to identify their player file <type String>
	 * @param p - Player object from which data is received <type Player>
	 * @return void
	 */
	static void updatePlayerFile (String username, Player p) {
		try {
			Writer w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(username + ".txt"), "utf-8"));
			w.write(p.getLevel() + "\n");
			w.write(p.getExperiencePoints() + "\n");
			w.write(p.getLevelUp() + "\n");
			w.write(p.getCurrentHealth() + "\n");
			w.write(p.getTotalHealth() + "\n");
			w.write(p.getCoins() + "\n");
			w.close();
		} catch (Exception e) {
			System.out.println(e);
		} //end of try/catch
	} //end of updateFile(String username) procedural method

	int getLevel() {
		return level;
	}

	void setLevel(int level) {
		this.level = level;
	}

	int getExperiencePoints() {
		return experiencePoints;
	}

	void setExperiencePoints(int experiencePoints) {
		this.experiencePoints = experiencePoints;
	}

	int getLevelUp() {
		return levelUp;
	}

	void setLevelUp(int levelUp) {
		this.levelUp = levelUp;
	}

	int getCurrentHealth() {
		return currentHealth;
	}

	void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	int getTotalHealth() {
		return totalHealth;
	}

	void setTotalHealth(int totalHealth) {
		this.totalHealth = totalHealth;
	}

	int getCoins() {
		return coins;
	}

	void setCoins(int coins) {
		this.coins = coins;
	}
} //end of Player class
