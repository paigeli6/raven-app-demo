/**====================================================================================================
 * Raven App Demo Program
 * Andy Liu, Paige Li
 * January 21st, 2023
 * Java, Eclipse 4.21
 * ====================================================================================================
 * Problem Definition - Create an RPG-style productivity app. 
 * Input - Mouse, Console, .txt Files 
 * Output - GUI, Console
 * Process - Create a GUI and add features as necessary. 
====================================================================================================*/

import java.io.*;
import java.net.*;
import java.text.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

/**====================================================================================================
 * RavenAppDemo class
 * Andy Liu, Paige Li
 * January 21st, 2023
 * Java, Eclipse 4.21
 * ====================================================================================================
 * <Class>
 * This class creates an RPG-style productivity app.
 * ====================================================================================================
 * <List of Identifiers>
 * HEADER - constant that separates output to make it easier to read <type String>
 * TRIVIAL_DIFFICULTY - numerical identifier for the difficulty "Trivial" <type String>
 * EASY_DIFFICULTY - numerical identifier for the difficulty "Easy" <type String>
 * MEDIUM_DIFFICULTY - numerical identifier for the difficulty "Medium" <type String>
 * HARD_DIFFICULTY - numerical identifier for the difficulty "Hard" <type String>
 * TRIVIAL_DAMAGE - amount of damage the player will take for neglecting a quest with the difficulty "Trivial" <type int>
 * EASY_DAMAGE - amount of damage the player will take for neglecting a quest with the difficulty "Easy" <type int>
 * MEDIUM_DAMAGE - amount of damage the player will take for neglecting a quest with the difficulty "Medium" <type int>
 * HARD_DAMAGE - amount of damage the player will take for neglecting a quest with the difficulty "Hard" <type int>
 * TRIVIAL_XP - amount of XP the player will gain for completing a quest with the difficulty "Trivial" <type int>
 * EASY_XP - amount of XP the player will gain for completing a quest with the difficulty "Easy" <type int>
 * MEDIUM_XP - amount of XP the player will gain for completing a quest with the difficulty "Medium" <type int>
 * HARD_XP - amount of XP the player will gain for completing a quest with the difficulty "Hard" <type int>
 * TRIVIAL_COINS - amount of coins the player will gain for completing a quest with the difficulty "Trivial" <type int>
 * EASY_COINS - amount of coins the player will gain for completing a quest with the difficulty "Easy" <type int>
 * MEDIUM_COINS - amount of coins the player will gain for completing a quest with the difficulty "Medium" <type int>
 * HARD_COINS - amount of coins the player will gain for completing a quest with the difficulty "Hard" <type int>
 * LEVEL_UP_INCREASE - amount that needed XP to level up increases per level gained <type int>
 * 
 * BR - object used for reading lines of a file <type BufferedReader>
 * FRAMESIZE - constant that represents the preferred size of a frame <type Dimension>
 * BUTTONSIZE - constant that represents the preferred size of a button in the menu bar <type Dimension>
 * RAVENPURPLE - constant that represents a custom color for the Raven app <type Color>
 * DATEFORMAT - object used for formatting strings into DayMonthYear <type DateFormat>
 * 
 * label - GUI component used for images and some text <type JLabel>
 * questList - array to allow methods access to user quests <type ArrayList<Quest>>
 * loggedIn - 
 * 
 * (other identifiers are detailed in individual method headers)
====================================================================================================*/
@SuppressWarnings("serial") //we have no intention of serializing our classes, so we just suppressed the warning
class RavenAppDemo extends JFrame {

	//****************************************************************************************************
	//STATIC CONSTANTS
	final static String HEADER = "\n****************************************************************************************************";
	final static String TRIVIAL_DIFFICULTY = "0";
	final static String EASY_DIFFICULTY = "1";
	final static String MEDIUM_DIFFICULTY = "2";
	final static String HARD_DIFFICULTY = "3";
	final static int TRIVIAL_DAMAGE = 20;
	final static int EASY_DAMAGE = 15;
	final static int MEDIUM_DAMAGE = 10;
	final static int HARD_DAMAGE = 5;
	final static int TRIVIAL_XP = 5;
	final static int EASY_XP = 10;
	final static int MEDIUM_XP = 15;
	final static int HARD_XP = 20;
	final static int TRIVIAL_COINS = 2;
	final static int EASY_COINS = 4;
	final static int MEDIUM_COINS = 8;
	final static int HARD_COINS = 16;
	final static int LEVEL_UP_INCREASE = 25;

	final static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	final static Dimension FRAMESIZE = new Dimension (800,800); //preferred size of the frame
	final static Dimension BUTTONSIZE = new Dimension (150,30); //preferred size of the buttons in the menu bar
	final static Color RAVENPURPLE = new Color(42, 1, 112); //creates a purple color
	final static DateFormat DATEFORMAT = new SimpleDateFormat("ddMMyyyy"); //used to format dates

	//****************************************************************************************************
	//STATIC VARIABLES
	private static JLabel label = new JLabel("", SwingConstants.CENTER); //creates a JLabel, used for images (through icons) and text
	private static ArrayList<Quest> questList = new ArrayList<Quest>(); //static array to allow methods access to user quests
	private static boolean loggedIn = false; //boolean variable that tracks if the user is logged in
	private static String username; //user's username

	//****************************************************************************************************

	static void loginAttempt(JTextField usernameInput, JTextField passwordInput) {
		try {
			FileReader fr = new FileReader("login.txt"); //login.txt is an existing file in the project folder, it holds valid user login info
			BufferedReader br = new BufferedReader(fr); 
			String line;
			String password; 

			username = usernameInput.getText().toString(); //gets the text in the usernameInput text field and converts it to String
			password = passwordInput.getText().toString(); //gets the text in the passwordInput text field and converts it to String

			while ((line = br.readLine())!= null) { 
				if (line.equals(username + "\t" + password)) { //checks if any lines have the matching username and password
					loggedIn = true; //sets loggedIn to true
					break;
				} //end of if statement
			} //end of while loop
			fr.close();
		} catch (Exception e) {
			System.out.println(e);
		} //end of try/catch
	}

	//****************************************************************************************************
	//MAIN METHOD!!!
	public static void main(String[] args) throws NullPointerException {

		//****************************************************************************************************
		//CREATING FRAME (WINDOW)
		JFrame frame = new JFrame("RavenAppDemo"); //creates a JFrame
		frame.setPreferredSize(FRAMESIZE); //sets the preferred size of the frame
		frame.pack(); //sizes the frame to fit all contents at or above their preferred size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //makes the program terminate when the "x" on the frame's window is pressed (assuming no console operations are running)
		frame.setVisible(true); //makes the frame visible
		//frame.setResizable(false);

		//CREATING LOGIN
		JLabel iconLabel = new JLabel(""); //creates a label used for pictures
		ImageIcon ravenIcon = new ImageIcon("Raven.png"); //existing image in the java project folder
		iconLabel.setIcon(ravenIcon);

		JLabel logPrompt = new JLabel ("RavenApp Login"); //creates a label for text
		logPrompt.setFont(new Font("Times New Roman", Font.BOLD,30)); //formats text
		logPrompt.setForeground(RAVENPURPLE); //colors text

		JTextField usernameInput = new JTextField(); //creates a text field 
		JTextField passwordInput = new JPasswordField(); //creates a special text field (user will not be able to see what they type)
		JButton loginButton = new JButton("Log In"); //creates a "log in" button
		JLabel loginMessage = new JLabel (""); //creates another label, no text yet

		//sets the location for the components above
		iconLabel.setBounds(273, 150, 255, 200);
		logPrompt.setBounds(295, 350, 250, 50);
		usernameInput.setBounds(325,400,150,30);
		passwordInput.setBounds(325,430,150,30);
		loginButton.setBounds(350,460,100,30);
		loginMessage.setBounds(275, 495, 250, 90);

		//adds the components above to a new JPanel
		JPanel loginPanel = new JPanel(); 
		loginPanel.add(iconLabel);
		loginPanel.add(logPrompt); 
		loginPanel.add(loginMessage);
		loginPanel.add(usernameInput);
		loginPanel.add(passwordInput);
		loginPanel.add(loginButton);
		loginPanel.setLayout(null); //configures the layout of the panel to follow the locations set above

		frame.add(loginPanel, BorderLayout.CENTER); //adds the panel above to the frame

		SwingUtilities.updateComponentTreeUI(frame); //refreshes the frame to show changes made
		usernameInput.requestFocus(); //starts the mouse where the text field for username input is

		loginButton.addActionListener(new ActionListener() { //runs the subsequent code in the event that the "log in" button is pressed
			public void actionPerformed (ActionEvent ae) {
				loginAttempt(usernameInput, passwordInput); //attempts to log in

				if (loggedIn) { 
					frame.remove(loginPanel); //removes the login menu after the user logs in

					Quest.fillExistingQuests(username); //fills the questList in class Quest with the existing quests from a user's Quest.txt file
					questList = Quest.getQuestList(); //fills the questList in class RavenAppDemo with the questList from class Quest

					//****************************************************************************************************
					//CREATING MENU TABS
					JToolBar toolBar = new JToolBar(); //creates a JToolbar, a panel will be added to it
					frame.getContentPane().add(toolBar, BorderLayout.NORTH); //aligns the toolbar along the top of the frame
					JPanel tabPanel = new JPanel(); //creates a JPanel, RESIZED buttons (can't resize buttons directly added to the toolbar) will be added to it

					//****************************************************************************************************
					JPopupMenu profileFeatures = new JPopupMenu(); //create a pop-up menu for program features under "Profile"

					//CREATING "PROFILE" FEATURE 1, HELP
					profileFeatures.add(new JMenuItem(new AbstractAction("Help") { //adds Simulate Day Pass option to the profileFeatures pop-up menu
						public void actionPerformed(ActionEvent ae) {
							try {
					            Desktop.getDesktop().browse(new URI("https://docs.google.com/document/d/e/2PACX-1vRMttLguCY6uidbxTgWBS7_AWSlWC_9Zv31oq894W57t1U7KuPK_78-U8151DMlEFvFeW1XnrLMrwpm/pub")); //opens up the Raven App user manual google doc
					        } catch (IOException ioe) {
					            System.out.println(ioe);
					        } catch (URISyntaxException urise) {
					            System.out.println(urise);
					        } //end of try/catch
							label.setText("Opened Help Document Webpage");
							label.setIcon(null);
							SwingUtilities.updateComponentTreeUI(frame);
						} //end of Simulate Day Pass action event
					})); //end of Simulate Day Pass feature
					
					//CREATING "PROFILE" FEATURE 2, LOG OUT
					profileFeatures.add(new JMenuItem(new AbstractAction("Log Out") { //adds Simulate Day Pass option to the profileFeatures pop-up menu
						public void actionPerformed(ActionEvent ae) {
							loggedIn = false;
							frame.dispose(); //gets rid of the existing frame (and everything in it)
							main(args); //calls the main method again
						} //end of Simulate Day Pass action event
					})); //end of Simulate Day Pass feature

					//CREATING "PROFILE" FEATURE 3, SIMULATE DAY PASS
					profileFeatures.add(new JMenuItem(new AbstractAction("Simulate Day Pass") { //adds Simulate Day Pass option to the profileFeatures pop-up menu
						public void actionPerformed(ActionEvent ae) {
							int simDays; //amount of days the user will want to simulate

							//GETTING NUMBER OF DAYS THE USER WANTS TO SIMULATE
							while (true) {
								System.out.println(HEADER);
								System.out.println("How many days would you like to simulate? \nYou will take damage if you pass any deadlines.");
								try {
									simDays = Integer.parseInt(BR.readLine()); //takes in user input
									if (simDays < 0) //throws an exception if the user inputs a non-positive integer amount of days
										throw new InvalidInputException();
									break;
								} catch (InvalidInputException iee) {
									System.out.println("\nInvalid input. Please enter a postive integer.");
								} catch (NumberFormatException nfe) { //catches the error if the user enters a non-numerical value
									System.out.println("\nInvalid input. Please enter a postive integer (numerical value).");
								} catch (IOException ioe) {
									System.out.println(ioe);
								} //end of try/catch
							} //end of while loop for days to simulate

							Date currentDate = new Date(); //creates a Date object
							long dateInMS = currentDate.getTime()+86400000*simDays; //gets the current date in MS and adds x amount of days to it

							try {
								for (int x = 0; x < simDays; x++) { //loops for x amount of simulated days
									long deadlineInMS; 
									Player p = new Player(username); //creates an instance of the player so that its data can be accessed

									for (int y = 0; y < questList.size(); y++) { //loops for y amount of quests in the questList
										Quest testQuest = questList.get(y); //creates a testQuest (for y amount of quests) and assigns it the value of y quest in questList
										Date deadline = DATEFORMAT.parse(testQuest.getDeadline()); //parses the String deadline into a Date deadline
										deadlineInMS = deadline.getTime(); //gets the deadline in terms of MS

										if (dateInMS > deadlineInMS) { //checks if the deadline has passed (more MS in the tested date)
											if(testQuest.getDifficulty().equals(TRIVIAL_DIFFICULTY)) { //checks to see how much damage should the user take based on the difficulty of the quest
												p.setCurrentHealth(p.getCurrentHealth()-TRIVIAL_DAMAGE);
												System.out.println("\nYou took " + TRIVIAL_DAMAGE + " damage!");
											} else if(testQuest.getDifficulty().equals(EASY_DIFFICULTY)) {
												p.setCurrentHealth(p.getCurrentHealth()-EASY_DAMAGE);		
												System.out.println("\nYou took " + EASY_DAMAGE + " damage!");
											} else if(testQuest.getDifficulty().equals(MEDIUM_DIFFICULTY)){
												p.setCurrentHealth(p.getCurrentHealth()-MEDIUM_DAMAGE);
												System.out.println("\nYou took " + MEDIUM_DAMAGE + " damage!");
											} else if(testQuest.getDifficulty().equals(HARD_DIFFICULTY)){
												p.setCurrentHealth(p.getCurrentHealth()-HARD_DAMAGE);
												System.out.println("\nYou took " + HARD_DAMAGE + " damage!");
											} //end of inner if/else if statement
											Player.updatePlayerFile(username, p); //updates the player's damage
											label.setText("<html><b>" + username + "</b><br>Level: <font color=blue>" + p.getLevel() + "</font>   -->   <font color=green>"+ p.getExperiencePoints() + "/" + p.getLevelUp() + " EXP"+ "</font><br>Health: <font color=red>" + p.getCurrentHealth() + "/" + p.getTotalHealth() + "</font><br>Coins: <font color=orange>" + p.getCoins() + "</font></html>");
											SwingUtilities.updateComponentTreeUI(frame); //refreshes the frame to show damage taken
										} //end of outer if statement
									} //end of inner for loop with counter y
								} //end of outer for loop with counter x
							} catch (ParseException e) {
								System.out.println("\nInvalid date. The date must be in DDMMYYYY format.");
							} //end of try/catch
							System.out.println(HEADER);
							System.out.println(simDays + " days simulated.");
						} //end of Simulate Day Pass action event
					})); //end of Simulate Day Pass feature

					//CREATING TOOLBAR OPTION #1, PROFILE
					JButton profileButton = new JButton("Profile"); //creates a JButton
					profileButton.setPreferredSize(BUTTONSIZE); //sets the preferred size of the button
					profileButton.addMouseListener(new MouseAdapter() { //lets the computer track mouse events
						public void mousePressed(MouseEvent e) { //runs the subsequent code when the mouse is clicked (pressed and released)
							Player p = new Player(username); //loads the data of the user
							label.setText("<html><b>" + username + "</b><br>Level: <font color=blue>" + p.getLevel() + "</font>   -->   <font color=green>"+ p.getExperiencePoints() + "/" + p.getLevelUp() + " EXP"+ "</font><br>Health: <font color=red>" + p.getCurrentHealth() + "/" + p.getTotalHealth() + "</font><br>Coins: <font color=orange>" + p.getCoins() + "</font></html>");
							ImageIcon profileIcon = new ImageIcon(username + "Char.png"); //usernameChar.png is a file in the java project folder
							label.setIcon(profileIcon); //sets icon as the corresponding visual for label
							SwingUtilities.updateComponentTreeUI(frame);
							profileFeatures.show(e.getComponent(), e.getX(), e.getY()+20);
						} //end of Profile mouse pressed event
					}); //end of Profile button
					tabPanel.add(profileButton); //adds the Profile button to the tabPanel (the panel will be added to a toolbar)

					//****************************************************************************************************
					//CREATING TOOLBAR OPTION #2, COMMISSIONS
					JButton commissionsButton = new JButton("Commissions");
					commissionsButton.setPreferredSize(BUTTONSIZE);
					commissionsButton.addMouseListener(new MouseAdapter() { 
						public void mousePressed(MouseEvent e) { 
							label.setText(""); //clears label text
							ImageIcon commissionIcon = new ImageIcon("Comms.png");
							label.setIcon(commissionIcon);
							SwingUtilities.updateComponentTreeUI(frame);
						}
					});
					tabPanel.add(commissionsButton);

					//****************************************************************************************************
					JPopupMenu questFeatures = new JPopupMenu(); 

					//CREATING "QUESTS" FEATURE 1, SEE QUESTS
					questFeatures.add(new JMenuItem(new AbstractAction("See Quests") {
						public void actionPerformed(ActionEvent ae) {
							Quest displayQuest = new Quest(); //creates an instance of Quest to hold and display values
							questList = Quest.getQuestList(); //updates questList in RavenAppDemo

							System.out.println(HEADER);
							System.out.println("Displaying Quests...");

							if (questList.size() == 0)  //run the code below if there are no quests
								System.out.println("\nNO ACTIVE QUESTS...\n\nGo create one!");

							for (int x = 0; x < questList.size(); x++) { //loops for x amount of quests in questList
								displayQuest = questList.get(x); //assigns an existing Quest object into the temporary displayQuest

								System.out.println("\nQuest: " + displayQuest.getQuestName());

								switch (displayQuest.getDifficulty()) {
								case TRIVIAL_DIFFICULTY:
									System.out.println("Difficulty: Trivial");
									break;
								case EASY_DIFFICULTY:
									System.out.println("Difficulty: Easy");
									break;
								case MEDIUM_DIFFICULTY:
									System.out.println("Difficulty: Medium");
									break;
								case HARD_DIFFICULTY:
									System.out.println("Difficulty: Hard");
									break;
								default: 
									System.out.println("Invalid Difficulty. Please \"complete\" and re-add this quest.");
									break;
								} //end of switch/case
								System.out.println("Deadline: " + displayQuest.getDeadline());
							} //end of for loop with counter x
						} //end of See Quests action event
					})); //end of See Quests feature

					//CREATING "QUESTS" FEATURE 2, ADD QUEST
					questFeatures.add(new JMenuItem(new AbstractAction("Add Quest") {
						public void actionPerformed(ActionEvent ae) {
							Quest newQuest = new Quest(); //this will be a new quest
							Quest.addToQuestList(newQuest); //adds the new quest into the questList
							String difficulty, deadline;

							//GETTING INPUT FOR QUEST DETAILS
							while (true) {
								try {
									//GETTING QUEST NAME
									System.out.println(HEADER);
									System.out.println("Please enter a name for your quest.");
									newQuest.setQuestName(BR.readLine());

									//GETTING QUEST DIFFICULTY
									while (true) {
										try {
											System.out.println(HEADER);
											System.out.println("Please choose a difficulty for your quest.");
											System.out.println("\n<" + TRIVIAL_DIFFICULTY + "> - Trivial");
											System.out.println("<" + EASY_DIFFICULTY + " > - Easy");
											System.out.println("<" + MEDIUM_DIFFICULTY + "> - Medium");
											System.out.println("<" + HARD_DIFFICULTY + "> - Hard");
											difficulty = BR.readLine();

											//checks if difficulty is valid
											if (!(difficulty.equals(TRIVIAL_DIFFICULTY)) && !(difficulty.equals(EASY_DIFFICULTY)) && !(difficulty.equals(MEDIUM_DIFFICULTY)) && !(difficulty.equals(HARD_DIFFICULTY)))
												throw new InvalidInputException();

											newQuest.setDifficulty(difficulty);
											break;
										} catch (InvalidInputException iie) {
											System.out.println("\nInvalid choice. Please enter a VALID difficulty option.");
										} catch (NumberFormatException nfe) {
											System.out.println("\nInvalid input. Please enter a numerical value.");
										} //end of inner try/catch for difficulty input
									} //end of while loop for difficulty input

									//GETTING QUEST DEADLINE
									while (true) {
										try {
											System.out.println(HEADER);
											System.out.println("Please enter a deadline in DDMMYYYY format, or <0> if you do not want to have a deadline.");
											deadline = BR.readLine();

											if (Integer.parseInt(deadline) == 0) { //runs if the user does NOT want a deadline
												newQuest.setDeadline(deadline);
												break;
											}
											//checks if days, months are valid
											if (Integer.parseInt(deadline.substring(0,2)) == 0 || Integer.parseInt(deadline.substring(0,2)) > 31)
												throw new InvalidInputException();
											if (Integer.parseInt(deadline.substring(2,4)) == 0 || Integer.parseInt(deadline.substring(2,4)) > 12)
												throw new InvalidInputException();

											newQuest.setDeadline(deadline);
											break;
										} catch (NumberFormatException nfe) {
											System.out.println("\nInvalid input. Please enter a COMPLETELY NUMERICAL (no slashes) value in DDMMYYYY format, or <0> if you do not want a deadline.");
										} catch (StringIndexOutOfBoundsException sioobe) {
											System.out.println("\nInvalid input. Please enter a value in DDMMYYYY format (8 numerical characters), or <0> if you do not want a deadline.");
										}catch (InvalidInputException iie) {
											System.out.println("\nInvalid date. Please enter a VALID DATE in DDMMYYYY format, or <0> if you do not want a deadline.");
										} //end of inner try/catch
									} //end of while loop for deadline input

									System.out.println(HEADER);
									System.out.println("Quest added successfully. It is now visible under <See Quests>.");
									Quest.updateQuestFile(username); //updates the .txt questFile
									break;

								} catch (IOException ioe) {
									System.out.println(ioe);
								} //end of outer try/catch
							} //end of while loop for quest details
						} //end of Add Quest action event
					})); //end of Add Quest feature

					//CREATING "QUESTS" FEATURE #3, COMPLETE QUEST
					questFeatures.add(new JMenuItem(new AbstractAction("Complete Quest") {
						public void actionPerformed(ActionEvent ae) {
							Player p = new Player(username);
							Quest accessQuest = new Quest(); //instance of Quest that will point to an existing Quest object to access its data
							int removeQuestNum; //the index of the questList element that will be removed
							questList = Quest.getQuestList();

							System.out.println(HEADER);
							System.out.println("Displaying Quests...");

							if (questList.size() == 0)
								System.out.println("\nNO ACTIVE QUESTS...\n\nGo create one!");

							//GETTING REMOVE QUEST CHOICE
							while (true) {
								try {
									for (int x = 0; x < questList.size(); x++) {
										accessQuest = questList.get(x);	
										System.out.println("\n" + (x+1) + ". " + accessQuest.getQuestName()); //x starts at 0, counting up starts at 1, so x+1

									}
									System.out.println(HEADER);
									System.out.println("Please enter the number of the quest you have completed.");
									removeQuestNum = Integer.parseInt(BR.readLine())-1; //index number of task is 1 less than the displayed number
									break;
								} catch (NumberFormatException nfe) {
									System.out.println("\nInvalid input. Please enter a NUMERICAL value from the given options.");
								} catch (StringIndexOutOfBoundsException sioobe) {
									System.out.println("\nInvalid input. Please enter a VALID option.");
								} catch (IOException ioe) {
									System.out.println(ioe);
								} //end of try/catch
							} //end of while loop for removing quest choice

							accessQuest = questList.get(removeQuestNum); //gets details of the completed quest
							System.out.println(HEADER);
							System.out.println("\nGreat job, " + username + "! You completed a quest: <" + accessQuest.getQuestName());

							//UPDATE PLAYER VALUES
							System.out.println(HEADER);
							if(accessQuest.getDifficulty().equals(TRIVIAL_DIFFICULTY)) {
								p.setExperiencePoints(p.getExperiencePoints() + TRIVIAL_XP);
								p.setCoins(p.getCoins() + TRIVIAL_COINS);
								System.out.println("Rewards! \n+ " + TRIVIAL_XP + " experience points\n+ " + TRIVIAL_COINS + " coins");
							} else if(accessQuest.getDifficulty().equals(EASY_DIFFICULTY)) {
								p.setExperiencePoints(p.getExperiencePoints() + EASY_XP);
								p.setCoins(p.getCoins() + EASY_COINS);
								System.out.println("Rewards! \n+ " + EASY_XP + " experience points\n+ " + EASY_COINS + " coins");								
							} else if(accessQuest.getDifficulty().equals(MEDIUM_DIFFICULTY)){
								p.setExperiencePoints(p.getExperiencePoints() + MEDIUM_XP);
								p.setCoins(p.getCoins() + MEDIUM_COINS);
								System.out.println("Rewards! \n+ " + MEDIUM_XP + " experience points\n+ " + MEDIUM_COINS + " coins");	
							} else if(accessQuest.getDifficulty().equals(HARD_DIFFICULTY)){
								p.setExperiencePoints(p.getExperiencePoints() + HARD_XP);
								p.setCoins(p.getCoins() + HARD_COINS);
								System.out.println("Rewards! \n+ " + HARD_XP + " experience points\n+ " + HARD_COINS + " coins");	
							} //end of if/else if statements

							//LEVEL UP CHECK
							if (p.getExperiencePoints() >= p.getLevelUp()) { //runs if the player has earned equal or more xp points than needed to level up
								p.setLevel(p.getLevel()+1); //increases the player's level
								p.setExperiencePoints(p.getExperiencePoints()-p.getLevelUp()); //resets experience points (keeps carry over)
								p.setLevelUp(p.getLevelUp()+LEVEL_UP_INCREASE); //increases the amount of XP needed to level up per level

								if (p.getTotalHealth() > 100) //runs the following code if the player has more than 100 health total
									p.setTotalHealth(p.getTotalHealth()-10); //decreases the amount of total health the player will have each level
							} //end of if statement

							Player.updatePlayerFile(username, p);
							Quest.removeQuest(removeQuestNum); //removes the completed quest from questList
							Quest.updateQuestFile(username); 
						} //end of Complete Quest action event
					})); //end of Complete Quest feature

					//CREATING TOOLBAR OPTION #3, QUESTS
					JButton questButton = new JButton("Quests");
					questButton.setPreferredSize(BUTTONSIZE);
					questButton.addMouseListener(new MouseAdapter() { 
						public void mousePressed(MouseEvent e) { 
							label.setText("");
							ImageIcon questIcon = new ImageIcon("Quests.jpg");
							label.setIcon(questIcon);
							SwingUtilities.updateComponentTreeUI(frame);
							questFeatures.show(e.getComponent(), e.getX(), e.getY()+20);
						}
					});
					tabPanel.add(questButton);

					//****************************************************************************************************
					//CREATING TOOLBAR OPTION #4, TAVERN
					JButton tavernButton = new JButton("Tavern");
					tavernButton.setPreferredSize(BUTTONSIZE);
					tavernButton.addMouseListener(new MouseAdapter() { 
						public void mousePressed(MouseEvent e) { 
							label.setText("");
							ImageIcon tavernIcon = new ImageIcon("Tavern.png");
							label.setIcon(tavernIcon);
							SwingUtilities.updateComponentTreeUI(frame);
						}
					});
					tabPanel.add(tavernButton);

					//****************************************************************************************************
					//CREATING TOOLBAR OPTION #5, MERCHANT
					JButton merchantButton = new JButton("Merchant");
					merchantButton.setPreferredSize(BUTTONSIZE);
					merchantButton.addMouseListener(new MouseAdapter() { 
						public void mousePressed(MouseEvent e) { 
							label.setText("<html><font color=green>shop is closed... come back... another day... <br>hehehe</font></html>");
							ImageIcon merchantIcon = new ImageIcon("Merchant.png");
							label.setIcon(merchantIcon);
							SwingUtilities.updateComponentTreeUI(frame);
						}
					});
					tabPanel.add(merchantButton);

					toolBar.add(tabPanel); //adds the panel of resized buttons to the toolbar
					frame.getContentPane().add(label,SwingConstants.CENTER); //adds the static label to the frame
					label.setText("<html><font color=purple><b>Welcome to Raven!</b></font>"
							+ "<br><br>There are five menu options on this app: Profile, Commissions, Quest, Tavern, Merchant"
							+ "<br>In &lt;Profile&gt; you can view your character and stats."
							+ "<br>In &lt;Commissions&gt; you will be able to create recurring tasks.<b>*</b>"
							+ "<br>In &lt;Quest&gt; you can create long-term goals/projects, and set difficulty of the task."
							+ "<br>In &lt;Tavern&gt; you will able to interact with other players.<b>*</b>"
							+ "<br>In &lt;Merchant&gt; you will be able to use your coins to buy accessories.<b>*</b>"
							+ "<br><br><b>*These features are unavailble in this prototype. Sorry for the inconvience.</b>"
							+ "<br><br><font color = purple>For more detailed instructions, please visit the &lt;Help&gt; feature under &lt;Profile&gt;</font></html>"); //introductory prompt
					
					ImageIcon userIcon = new ImageIcon(username+"Char.png");
					label.setIcon(userIcon);
					SwingUtilities.updateComponentTreeUI(frame);

				} //end of while loop with condition loggedIn
				else
					loginMessage.setText("<html>LOGIN FAILED: <br>Incorrect Username or Password. <br>Try again.</html>");
			} //end of main action event
		}); //end of main GUI
	} //end of main method
} //end of RavenAppDemo class


/**====================================================================================================
 * InvalidInputException class
 * Andy Liu, Paige Li
 * January 21st, 2023
 * Java, Eclipse 4.21
 * ====================================================================================================
 * <Class>
 * This class creates an InvalidInputException checked exception which extends the Exception class.
 * ====================================================================================================
 * <List of Identifiers>
 * none
 * 
 * (other identifiers are detailed in individual method headers)
====================================================================================================*/

@SuppressWarnings("serial")
class InvalidInputException extends Exception {
	
	/** InvalidInputException default constructor
	 * 	Creates a custom InvalidInputException checked exception that can be thrown in other classes. 
	 * 
	 * <List of Local Variables>
	 * none
	 * 
	 * @param none
	 * @see Exception class 
	 */
	InvalidInputException() {
	} //end of InvalidInputException() default constructor method
	
} //end of InvalidInputException class

