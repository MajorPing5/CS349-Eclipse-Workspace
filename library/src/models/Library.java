package models;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Library {
	private String name;
	private ArrayList<Book> books = new ArrayList<>();
	
	public Library() {}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Book> getBooks() {
		return books;
	}
	
	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}
	
	// custom methods
	/**
	 * method to add a book to the books collection
	 */
	public void addBook(Book book) {
		// books.add(book); // stale data
		getBooks().add(book);
	}
	
	/**
	 * method to remove a book from the book collection
	 */
	public void removeBook(Book book) {
		// get all books available
		ArrayList<Book> currentBooks = getBooks();
		
		// loop through to remove the book from the list
		if (book.isAvailable()) {
			currentBooks.remove(book);
			JOptionPane.showMessageDialog(null,  "Book with title "+book.getTitle()+" removed successfully");
			
			// update the book's availability from True to False
			book.setAvailable(false);
		}
		else {
			JOptionPane.showMessageDialog(null,  "Book not found");
		}
	}
		
	/**
	 * method to display books
	 */
		public void displayBooks() {
			
			StringBuilder booksToDisplay = new StringBuilder("");
			
			ArrayList<Book> books = new ArrayList<>();
			for(Book book: getBooks()) {
				booksToDisplay.append(book.display()+"\n");
			}
			
			JOptionPane.showMessageDialog(null, booksToDisplay);
	}
}
