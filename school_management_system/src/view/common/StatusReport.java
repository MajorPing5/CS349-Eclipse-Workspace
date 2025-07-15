package view.common;

import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * All methods responsible for reporting success or failure messages
 */
public class StatusReport {
	/**
	 * Reports a successful login attempt during runtime.
	 * Precon: ALL tested field(s) were proven to be valid;
	 * Postcon: A Login Success Message is displayed to the user, over the existing window
	 * @returns Login Success Message
	 */
	public void loginSuccess() {
		JOptionPane.showMessageDialog(null,
				"Login Attempt Successful",
				"Success", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Reports a successful entry during runtime.
	 * Precon: ALL tested field(s) were proven to be valid;
	 * Postcon: A Success Message is displayed to the user, over the existing window
	 * @returns Success Message
	 */
	public void entrySuccess() {
		JOptionPane.showMessageDialog(null,
				"Your information has been updated",
				"Success", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Reports the Error discovered during runtime.
	 * Precon: A tested field(s) was proven to be invalid;
	 * Postcon: An error message is displayed to the user, over the existing window	
	 * @param failType The string name of the error type
	 * @param testedFields A list of strings for all tested field(s)
	 * @returns Error Message
	 */
	public void failedEntry(String failType, ArrayList<String> testedFields) {
		switch (failType) {
		
			// By far the most-likely and widely applicable error that can take place
		case "Empty Field": 
			JOptionPane.showMessageDialog(null,
					"Empty Field Detected: " + String.join(", ", testedFields),
					"Error", JOptionPane.ERROR_MESSAGE);
			break;
		
			// The following case is to be reported in the event the login information does not match any user in the database
		case "Incorrect Login":
			JOptionPane.showMessageDialog(null,
					"Your login information was incorrect. Check your email and password before trying again.",
					"Error", JOptionPane.ERROR_MESSAGE);

			// The following cases are for User ID and Course ID fields-specific errors
		case "Not An Integer":
			JOptionPane.showMessageDialog(null,
					"Invalid Integer Entry: " + testedFields,
					"Error", JOptionPane.ERROR_MESSAGE);
			break;
		case "Integer Domain Violation":
			JOptionPane.showMessageDialog(null,
					"Missing: " + String.join(", ", testedFields),
					"Error", JOptionPane.ERROR_MESSAGE);
			break;

			// This case is specific to an ID search error - assuming that the value was a valid int within proper domain
		case "Integer DNE":
			JOptionPane.showMessageDialog(null,
					"Your ID search does not exist. Check if you searched for the right ID value.",
					"Error", JOptionPane.ERROR_MESSAGE);
			break;
			
			// Under NO circumstances should this EVER be displayed, but in the rare chance something critical happens...
		default:
			JOptionPane.showMessageDialog(null,
					"Something just went HORRIBLY wrong for you to see this. Important stuff: " + failType +
					" with field(s) " + testedFields,
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
