
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Stopwatch implements ActionListener{


	private JLabel lb1;
	private JTextField tx1;
	public Object jf;
	
	//JFrame frame = new JFrame(); //NEW FRAME IS ADDED ***** i want to add all of this stop watch stuff to the panel "jf" from the other class-tester
	JButton startButton = new JButton ("START");
	JButton resetButton = new JButton ("RESET");
	JLabel timeLabel = new JLabel();
	int elapsedTime = 0;
	int seconds = 0;
	int minutes = 0;
	int hours = 0;
	boolean started = false;
	String seconds_string = String.format("%02d", seconds);
	String minutes_string = String.format("%02d", minutes);
	String hours_string = String.format("%02d", hours);

	Timer timer = new Timer(1000, new ActionListener() { //everything timer does every 1000 miliseconds

		public void actionPerformed(ActionEvent e) {

			elapsedTime+=1000;
			hours = (elapsedTime / 3600000); //miliseconds in an hour
			minutes = (elapsedTime / 60000) % 60; //miliseconds in a minute
			seconds = (elapsedTime/1000) % 60;//miliseconds in a second
			seconds_string = String.format("%02d", seconds); 
			minutes_string = String.format("%02d", minutes);
			hours_string = String.format("%02d", hours);
			timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string); //displaying timer that changes every 1000 miliseconds

		}

	});



	Stopwatch(){

		tester jf = new tester();
		jf.setLocationRelativeTo(null);
		jf.setTitle("TIME TRACKER");
		jf.setSize(200, 200);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string); //displaying initial timer
		timeLabel.setBounds(100,100,200,100);
		//timeLabel.setFont(new Font("Verdana", Font.PLAIN, 35)); //setting font 
		timeLabel.setBorder(BorderFactory.createBevelBorder(1));
		timeLabel.setOpaque(true);
		timeLabel.setHorizontalAlignment(JTextField.CENTER);

		startButton.setBounds(100,200,100,50);	//startButton
		//startButton.setFont(new Font("Verdana", Font.PLAIN, 35));
		startButton.setFocusable(false);
		startButton.addActionListener(this); //start button has to do something after pressed, therefore add "ActionListener"

		resetButton.setBounds(200,200,100,50);
		//resetButton.setFont(new Font("Verdana", Font.PLAIN, 35));
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);//reset button has to do something after pressed, therefore add "ActionListener"



		jf.add(startButton);  //add buttons and time to the frame
		jf.add(resetButton);
		jf.add(timeLabel);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(420,420);
		jf.setLayout(null);
		jf.setVisible(true);


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startButton) { //if start button pressed
			start(); //timer starts
			if(started == false) {
				started = true;
				startButton.setText("STOP"); //if timer is started, the start button switches to "stop"
				start(); //timer is started
			}else {
				started = false;
				startButton.setText("START"); //if timer is stopped, start button changes back to "start"
				stop(); //timer is stopped
			}

		}
		if(e.getSource() == resetButton) { //if reset button pressed
			started = false;
			startButton.setText("START");  //start button is set to "Start"
			reset();
		}

	}

	void start() {
		timer.start();
	}

	void stop() {
		timer.stop();
	}

	void reset() {
		timer.stop();
		elapsedTime = 0;
		seconds = 0;
		minutes = 0;
		hours = 0;
		boolean started = false;
		seconds_string = String.format("%02d", seconds); //seconds, minutes, hours are all reset to 0
		minutes_string = String.format("%02d", minutes);
		hours_string = String.format("%02d", hours);
		timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
	}


}
