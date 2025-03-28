
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

/**
 * This class demonstrates how to use an ImageIcon and a JLabel to display an
 * image.
 */

public class MyCatImage2 extends JFrame {
	private JPanel imagePanel; // To hold the label
	private JPanel buttonPanel; // To hold a button
	private JLabel imageLabel; // To show an image
	private JButton button; // To get an image

	/**
	 * Constructor
	 */

	public MyCatImage2() {
		// Set the title.
		setTitle("My Cat");

		// Specify an action for the close button.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create a BorderLayout manager.
		setLayout(new BorderLayout());

		// Build the panels.
		buildImagePanel();
		buildButtonPanel();

		// Add the panels to the content pane.
		add(imagePanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		// Pack and display the window.
		pack();
		setVisible(true);
	}

	/**
	 * The buildImagePanel method adds a label to a panel.
	 */

	private void buildImagePanel() {
		// Create a panel.
		imagePanel = new JPanel();

		// Create a label.
		imageLabel = new JLabel("Click the button to " + "see an image of my cat.");

		// Add the label to the panel.
		imagePanel.add(imageLabel);
	}

	/**
	 * The buildButtonPanel method adds a button to a panel.
	 */

	private void buildButtonPanel() {
		ImageIcon smileyImage;

		// Create a panel.
		buttonPanel = new JPanel();

		// Get the smiley face image.
		smileyImage = new ImageIcon("/Users/aabnxq/eclipse-workspace/SamplePrograms/src/c12FirstLookAtGui/Smiley.gif");

		// Create a button.
		button = new JButton("Get Image");
		button.setMnemonic(KeyEvent.VK_A);
		button.setIcon(smileyImage);

		// Register an action listener with the button.
		button.addActionListener(new ButtonListener());

		// Add the button to the panel.
		buttonPanel.add(button);
	}

	/**
	 * Private inner class that handles the event when the user clicks the button.
	 */

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			JFileChooser fileChooser = new JFileChooser(); // open file chooser dialog
			int status = fileChooser.showOpenDialog(null); // returns the dialog box status
			
			System.out.println(status);
			
			if (status == JFileChooser.APPROVE_OPTION) {
				
				File selectedFile = fileChooser.getSelectedFile();
				
				String filename = selectedFile.getPath(); // get path
				// Read the image file into an ImageIcon object.
				ImageIcon catImage = new ImageIcon(filename);

				//JOptionPane.showMessageDialog(null, "You selected " + filename);
				// Display the image in the label.
				imageLabel.setIcon(catImage);

				// Remove the text from the label.
				imageLabel.setText(null);
			}
			// Pack the frame again to accommodate the
			// new size of the label.
			pack();
		}
	}

	/**
	 * The main method creates an instance of the MyCatImage class which causes it
	 * to display its window.
	 */
	public static void main(String[] args) {
		new MyCatImage2();
	}
}
