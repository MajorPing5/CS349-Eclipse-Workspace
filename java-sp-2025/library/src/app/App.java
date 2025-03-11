package app;

import javax.swing.JOptionPane;

import models.*;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		
		Book book1 = new Book();
		book1.setTitle("1984");
		book1.setAuthor("George");
		book1.setID(1);
		book1.setGenre("Dystopia");
		book1.setAvailable(true);
		
		// add the book to the library
		library.addBook(book1); */
		
		Library library = createLibrary();
		
		// display the book
		library.displayBooks();
		
		// update books
		updateBook(library);
		
		// remove books
		removeBook(library);
	}
	
	/**
	 * create a library and add books
	 */
	private static Library createLibrary() {
		// create a library
		Library library = new Library();
		library.setName(JOptionPane.showInputDialog("Enter library name"));
		
		int id = 1;
		
		String yesNo;
		
		// Could write for(int id = 1, String yesNo = "y"; yesNo.toLowerCase().equals("y"); id++)
		do {
			String title = JOptionPane.showInputDialog("Enter book title");
			String author = JOptionPane.showInputDialog("Enter author name");
			
			Book newBook = new Book(id, title, author);
			library.addBook(newBook);
			
			id++;
			
			yesNo = JOptionPane.showInputDialog("Do you want to add another book?\n"
					+ "Enter y to continue or press any key to quit");
		}
		while(yesNo.toLowerCase().equals("y"));
		
		return library;
	}
	
	/**
	 * @param library
	 */
	private static void updateBook(Library library) {
		int isbn = Integer.parseInt(JOptionPane.showInputDialog("Enter the ISBN to update a book"));
		
		Book bookToUpdate = null;
		
		for(Book book: library.getBook()) {
			if(book.getID() == )
		}
		
	}
	
	/**
	 * 
	 * @param library
	 */
	private static void removeBook(Library library) {
		String yesNo;
		do {
			// get isbn of the book to be removed
			String isbn = JOptionPane.showInputDialog(null, "Enter the ISBN");
			
			// book object to be removed
			Book bookToRemove = null;
			
			// loop through the books
			for(Book book: library.getBooks()) {
				if(book.getID() == Integer.parseInt(isbn)) {
					bookToRemove = book;
					break;
				}
			}
			if(bookToRemove != null) {
				library.removeBook(bookToRemove);
				library.displayBooks();
			} else {
				JOptionPane.showMessageDialog(null,  "Book not found");
			}
			
			yesNo = JOptionPane.showInputDialog("Do you want to remove another book?\n"
					+"Enter y to continue or press any key to quit");
			}
		while(yesNo.toLowerCase().equals("y"));
	}
}
