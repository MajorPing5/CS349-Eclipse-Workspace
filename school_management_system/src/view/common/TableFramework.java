package view.common;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.function.Function;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableFramework {
	private JTable catalog;
	private DefaultTableModel tableModel;
	
	/**
	 * Empty constructor that initializes the entire class for method use
	 */
	public TableFramework() {}

	public void buildInitialTable(JPanel tablePanel, String borderTitle, String[] columnNames) {
	    tablePanel.setBorder(BorderFactory.createTitledBorder(borderTitle));
	    
	    // Create table and scroll pane
	    tableModel = new DefaultTableModel(columnNames, 0);
	    
	    catalog = new JTable(tableModel);
	    JScrollPane scrollPane = new JScrollPane(catalog);
	    
	    // Add table to the panel
	    tablePanel.add(scrollPane, BorderLayout.CENTER);
	}
	
	/**
	 * Generates a new table using a generic data extraction approach
	 * @param items List of items of any type
	 * @param rowMapper Function to convert items to table row data
	 */
	public <T> void newTable(ArrayList<T> items, Function<T, Object[]> rowMapper) {
	    // Clear existing table data
	    if (tableModel.getRowCount() > 0) {
	        tableModel.setRowCount(0);
	    }
	    
	    // Populate table using the mapper function
	    for (T item : items) {
	        tableModel.addRow(rowMapper.apply(item));
	    }
	}
}