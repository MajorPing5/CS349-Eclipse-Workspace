package view.common;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The {@code PanelOperations} class represents a collection of
 * shared Panel actions and behaviors. All Panel Operations that are
 * applicable across multiple views, such as panel swapping, are
 * implemented in this class and are to be used in place of per-class
 * implementations.
 */
@SuppressWarnings("serial")
public class PanelOperations extends JFrame {
	// Constructors
	/**
	 * Empty Constructor for Class 
	 */
	public PanelOperations() {
	};
	
	// Custom Methods
	/**
	 * Enables the ability to visibly and functionally toggle text
	 * fields on and off at will.
	 * @param fields An ArrayList of fields to enable/disable
	 * @param status Boolean True/False for Enable/Disable
	 */
	public void toggleFieldsEdit(ArrayList<JTextField> fields, boolean status) {
		fields.forEach(field -> {
			field.setEditable(status);
			
			if (status) {
				// Standard Black Text on White Background
				field.setForeground(Color.BLACK);
				field.setBackground(Color.WHITE);
			} else {
				// Lightly grayed out Text Box with darker gray Text
				field.setForeground(Color.GRAY);
				field.setBackground(Color.LIGHT_GRAY);
			}
		});
	}
	
	/**
	 * Enables exchanging two declared JPanels with each other
	 * @param main The Parent JPanel of a given container
	 * @param target The desired JPanel that is to be displayed within {@code main}
	 */
	public void panelSwap(JPanel main, JPanel target) {
		main.removeAll();
		main.add(target);
		main.revalidate();
		main.repaint();
	}
}