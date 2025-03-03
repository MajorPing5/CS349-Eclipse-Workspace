

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*; // Needed for Swing classes

public class FirstFrame2 extends JFrame {

	private JTextField textField;
	private JLabel messageLabel; // To display a message
	private JButton button;
	private JPanel panel;

	public FirstFrame2() {
		// Set the title bar text.
		setTitle("First frame");

		// Set the size of the window.
		setSize(200, 155);

		// Specify an action for the close button.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLocationRelativeTo(null);

		// Create a label.
		messageLabel = new JLabel("This is the first frame");

		button = new JButton("go to second frame");
		button.addActionListener(new ButtonListener());

		textField = new JTextField(10);

		panel = new JPanel();
		panel.add(textField);
		panel.add(messageLabel);
		panel.add(button);
		add(panel);

		setVisible(true);
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String text = textField.getText();
			new SecondFrame2(text);
			dispose();

//			SecondFrame2 secondFrame = new SecondFrame2(text);
//			secondFrame.textField.setText(text);
//			secondFrame.setVisible(true);
//			dispose();

		}
	}

	public static void main(String[] args) {
		new FirstFrame2();
	}

}
