package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.common.TableFramework;
import view.common.admin.AdminManageButtons;

public class AdminView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	@SuppressWarnings("unused")
	private AdminManageButtons sysManageButtons;
	private TableFramework tableFramework;
	
	/**
	 * Create the Admin specific frame.
	 */
	public AdminView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		sysManageButtons = new AdminManageButtons();
		tableFramework = new TableFramework();
		
		add(sysManageButtons, BorderLayout.CENTER);
		
		// Pack the contents of the window and display it.
		pack();
		setVisible(true);
	}
}