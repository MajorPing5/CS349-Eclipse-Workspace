

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SecondFrame2 extends JFrame {

	private static String text = "default from second frame";
	private JTextField textField;
	private JLabel messageLabel; // To display a message
	private JButton button;
	private JPanel panel;

	public SecondFrame2(String t) {
		
		text = t;
		
		// Set the title bar text.
		setTitle("Second frame");

		// Set the size of the window.
		setSize(200, 155);

		// Specify an action for the close button.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLocationRelativeTo(null);

		// Create a label.
		messageLabel = new JLabel("This is the second frame");

		button = new JButton("go to first frame");
		button.addActionListener(new ButtonListener());

		textField = new JTextField(10);
		textField.setEditable(false);
		textField.setText(text);

		panel = new JPanel();
		panel.add(textField);
		panel.add(messageLabel);
		panel.add(button);
		add(panel);

		setVisible(true);
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new FirstFrame2().setVisible(true);
			dispose();
		}
	}

	public static void main(String[] args) {
		new SecondFrame2(text);
	}

}
