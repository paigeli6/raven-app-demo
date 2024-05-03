import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI implements ActionListener{
	
	int count = 0;
	private JLabel label;
	private JFrame frame;
	private JPanel panel;

	
	public GUI() {
		
		frame = new JFrame();
		
		//JButton button = new JButton("Click me");
		label = new JLabel("Number of Clicks: 0");
		
		//button.addActionListener(this);
		
		panel = new JPanel();
		//panel.setBorder(BorderFactory.createEmptyBorder(500, 500, 50, 30)); //top bottom left right
		//panel.setLayout(new GridLayout(0,1)); // enter rows and columns
		//panel.add(button);
		panel.add(label);
		
        frame.setPreferredSize(new Dimension(500, 500)); //the window size
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Our GUI");
		frame.pack(); //causes components to fit to size of the window
		frame.setVisible(true); 
		
	}

	public JButton createButton() {
		JButton button = new JButton();
		
		return button;
		
	}
	public static void main(String[] args) {
		new GUI();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		count++;
		label.setText("Number of Clicks:" + count);
		
	}

}
