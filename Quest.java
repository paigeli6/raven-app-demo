import java.io.*;
import java.util.ArrayList;

/**====================================================================================================
 * Quest class
 * Andy Liu, Paige Li
 * January 17th, 2023
 * Java, Eclipse 4.21
 * ====================================================================================================
 * <Class>
 * This class creates a template for a Quest object.
 * ====================================================================================================
 * <List of Identifiers>
 * questList - arraylist used to store Quest objects <type ArrayList<Quest>>
 * questName - name of the quest <type String>
 * difficulty - difficulty level of the quest <type String>
 * deadline - deadline for the quest <type String>
 * 
 * (other identifiers are detailed in individual method headers)
====================================================================================================*/
class Quest {

	private static ArrayList<Quest> questList = new ArrayList<Quest>(); //stores questInfo arrays
	private String questName, difficulty, deadline;

	/** Quest default constructor
	 * Creates a default Quest object.
	 * 
	 * <List of Local Variables>
	 * none
	 * 
	 * @param none
	 */
	Quest() {
		questName = "untitled quest";
		difficulty = RavenAppDemo.TRIVIAL_DIFFICULTY;
		deadline = "0";
	} //end of Quest() default constructor method
	
	/** addToQuestList procedural method
	 * Adds a quest to questList. Called after creating a NEW quest. 
	 * 
	 * <List of Local Variables>
	 * none
	 * 
	 * @param newQuest - new quest <type Quest)
	 * @see RavenAppDemo
	 */
	static void addToQuestList(Quest newQuest) {
		questList.add(newQuest);
	}

	/** getQuestName functional getter method
	 * Gets the name of the quest.
	 * 
	 * <List of Local Variables>
	 * none
	 * 
	 * @param none
	 * @return questName - the name of the quest <type String>
	 */
	String getQuestName() {
		return questName;
	} //end of getQuestName() functional getter method

	/** setQuestName procedural setter method
	 * Sets the name of a quest to another String value.
	 * 
	 * <List of Local Variables>
	 * none
	 * 
	 * @param questName - the new quest name <type String>
	 * @return void
	 */
	void setQuestName(String questName) {
		this.questName = questName;
	} //end of setQuestName(String questName) functional getter method

	/** getDifficulty functional getter method
	 * Gets the difficulty level of the quest.
	 * 
	 * <List of Local Variables>
	 * none
	 * 
	 * @param none
	 * @return difficulty - the difficulty of the quest <type String>
	 */
	String getDifficulty() {
		return difficulty;
	} //end of getDifficulty() functional getter method

	/** setDifficulty procedural setter method
	 * Sets the difficulty of a quest to another String value ("0" "1" or "2")
	 * 
	 * <List of Local Variables>
	 * none
	 * 
	 * @param difficulty - the new difficulty <type String>
	 * @return void
	 */
	void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	} //end of setDifficulty(String difficulty) functional getter method

	/** getDeadline functional getter method
	 * Gets the deadline of the quest.
	 * 
	 * <List of Local Variables>
	 * none
	 * 
	 * @param none
	 * @return deadline - the deadline of the quest <type String>
	 */
	String getDeadline() {
		return deadline;
	} //end of getDeadline() functional getter method

	/** setDeadline procedural setter method
	 * Sets the deadline of a quest to another String value in the DDMMYYYY format.
	 * 
	 * <List of Local Variables>
	 * none
	 * 
	 * @param date - the new date <type String>
	 * @return void
	 */
	void setDeadline(String deadline) {
		this.deadline = deadline;
	} //end of setDeadline(String deadline) functional getter method

	/** removeQuest procedural method
	 * Removes a Quest object from the questList arraylist.
	 * 
	 * <List of Local Variables>
	 * none
	 * 
	 * @param questNum - the index of questList where an element (questInfo array) will be removed <int questNum>
	 * @return void
	 */
	static void removeQuest(int questNum) {
		questList.remove(questNum);
	} //end of removeQuest(int questNum) procedural method

	/** getQuestList functional getter method
	 * Gets the VALUES of the questList arrayList. 
	 * 
	 * <List of Local Variables>
	 * none
	 * 
	 * @param none
	 * @return questList - list of all questInfo arrays <type Quest>
	 */
	static ArrayList<Quest> getQuestList() {
		return questList;
	} //end of getQuestList() functional getter method

	/** fillExistingQuests procedural method
	 * Fills the questList arraylist with pre-existing quests from a user quest .txt file. 
	 * 
	 * <List of Local Variables>
	 * fr - object used to read the user's quest file <type FileReader>
	 * br - object used for reading lines of a file <type BufferedReader>
	 * line - a specific line of a the user's quest file <type String>
	 * tempArray - array used to store quest info from lines, it will be copied into tempQuest <type String[]>
	 * fileRow - row of the file, used to track which index of tempArray values should go into <type int>
	 * tempQuest - Quest object added into questList once info is copied into it from tempArray <type Quest>
	 * 
	 * @param username - the user's username, used to identify their quest file <type String>
	 * @return void
	 */
	static void fillExistingQuests(String username) {
		try {
			FileReader fr = new FileReader(username + "Quests.txt"); //usernameQuests.txt is an existing file in the project folder
			BufferedReader br = new BufferedReader(fr);
			String line;
			String[] tempArray = new String[3]; 
			int fileRow = 0; 

			while((line = br.readLine())!= null) { //reads lines, will loop subsequent code if it is not null
				tempArray[fileRow] = line.trim(); //puts the read line into the [fileRow] index of tempArray, trims excess whitespace on either side
				if (fileRow == 2) { //this is the last index of tempArray
					Quest tempQuest = new Quest();
					tempQuest.setQuestName(tempArray[0]);
					tempQuest.setDifficulty(tempArray[1]);
					tempQuest.setDeadline(tempArray[2]);
					questList.add(tempQuest); //adds the new Quest object to questList
					fileRow=-1; //resets fileRow to reset the indexes, the following line increments so it must be set to -1 here (to get index[0])
				} //end of if statement
				fileRow++;
			} //end of while loop
			fr.close();
		} catch (Exception e) {
			System.out.println(e);
		} //end of try/catch
	} //end of fillExistingQuests(String username) procedural method

	/** updateFile procedural method
	 * Updates the user's quest file with new quest info. 
	 * 
	 * <List of Local Variables>
	 * w - object that lets the program write in the user's .txt quest file <type Writer>
	 * x - counter that loops for the number of elements in questList <type int>
	 * 
	 * @param username - the user's username, used to identify their quest file <type String>
	 * @return void
	 */
	static void updateQuestFile (String username) {
		try {
			Writer w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(username + "Quests.txt"), "utf-8"));
			for (int x = 0; x < questList.size(); x++) {
				w.write(questList.get(x).getQuestName() +"\n");
				w.write(questList.get(x).getDifficulty() +"\n");
				w.write(questList.get(x).getDeadline() +"\n");
			}
			w.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println(fnfe);
		} catch (IOException ioe) {
			System.out.println(ioe);
		} //end of try/catch
	} //end of updateFile(String username) procedural method

} //end of Quest class
