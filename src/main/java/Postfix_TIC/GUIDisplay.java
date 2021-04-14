package Postfix_TIC;

/**
* File: GUIDisplay.java
* Author: Kolger Hajati
* Date: February 6, 2019
* Purpose: holds GUI details for user menu.
*/

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIDisplay extends JFrame {

	// Variables
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField inputText;
	private static JTextField resultText;
	private JButton inputButton;
	private JLabel inputLabel;
	private JLabel resultLabel;

	//Main and runs GUI
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				GUIDisplay frame = new GUIDisplay();
				frame.setVisible(true);
				}
		});
	}

	private GUIDisplay() {

		//Content Bounds
		setTitle("Postfix to Infix Expression Conversion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 180);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//Construct Tree Button
		inputButton = new JButton("Construct Tree");
		inputButton.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 12));
		inputButton.setBounds(155, 60, 160, 30);
		contentPane.add(inputButton);

		//Postfix Expression label
		inputLabel = new JLabel("Enter Postfix Expression");
		inputLabel.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 12));
		inputLabel.setBounds(25, 10, 180, 20);
		contentPane.add(inputLabel);

		//Infix label
		resultLabel = new JLabel("Infix Expression");
		resultLabel.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 12));
		resultLabel.setBounds(30, 110, 110, 20);
		contentPane.add(resultLabel);

		//Input text field
		inputText = new JTextField();
		inputText.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		inputText.setBounds(200, 10, 230, 20);
		contentPane.add(inputText);
		inputText.setColumns(10);

		//Result text field
		resultText = new JTextField();
		resultText.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		resultText.setBounds(150, 110, 260, 20);
		contentPane.add(resultText);
		resultText.setColumns(10);
		resultText.setEditable(false);

		//Action listener sent to MethodActions
		inputButton.addActionListener(new ActionInput());
		inputText.addActionListener(new ActionInput());
		resultText.addActionListener(new ActionInput());
	}

	/**
	*Action listener that handles input and output of GUI values
	*Also handles error check for invalid token and empty input
	*/
	private class ActionInput implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (GUIDisplay.inputText.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please enter value!", "Error", JOptionPane.ERROR_MESSAGE);
			} 
			else {
				try {
					String textInput = GUIDisplay.inputText.getText();
					String stringTrim = textInput.replaceAll(" ", "");

					EvaluationTree binT = new EvaluationTree();
					binT.create(stringTrim);
					GUIDisplay.resultText.setText("" + binT.infix());
				} 
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Invalid Token: " + EvaluationTree.getError(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}