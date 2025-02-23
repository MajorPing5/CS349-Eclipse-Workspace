package app;

import javax.swing.JOptionPane;

import module.*;

public class app {
	
	public static void main(String[] args) {
        // TODO Auto-generated method stub
        /**
        Manual entry of a book. Must be an easier way to do this.
        
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
        /**
         * Could easily create a do...while loop for allowing the user the option to pick
		 * which operation should be executed.
		 * 
		 * Would start with:
		 * int option = 0;
		 * do {
		 * 		option =
		 * }
		 * while(option != 3) {
		 * 		option = JOptionPane.showInputDialog("What would like to do with your library?\n"
		 * 				+"1. Update a Book\n"
		 * 				+"2. Remove a Book\n"
		 * 				+"3. Quit")
		 * }
         */
        //update books
        updateBook(library);
        
        // remove books
        removeBook(library);
        
        System.exit(0);
        
    }
    
    /**
     * create a library and add books
     * 
     * could modify to extrapolate book addition into
	 * its own separate function to allow adding books at any time (either after library creation or after other operations)
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
		String yesNo;
		
		do {
			int isbn = Integer.parseInt(JOptionPane.showInputDialog("Enter the ISBN to update an entry"));
			
			Book bookToUpdate = null;
			
			for(Book book: library.getBooks()) {
				if(book.getISBN() == isbn) {
					bookToUpdate = book;
					break;
				}
			}
			if(bookToUpdate != null) {
				String title = JOptionPane.showInputDialog("Enter new book's title");
				String author = JOptionPane.showInputDialog("Enter new author's name");
				
				bookToUpdate.setTitle(title);
				bookToUpdate.setAuthor(author);
				
				library.displayBooks();
			} else {
				JOptionPane.showMessageDialog(null, "Book information doesn't exist in the library");
			}
			
			yesNo = JOptionPane.showInputDialog("Do you have more books to update?\n"
					+"Enter y for yes or press any key to quit");
		}
		while(yesNo.toLowerCase().equals("y"));
		
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
				if(book.getISBN() == Integer.parseInt(isbn)) {
					bookToRemove = book;
					break;
				}
			}
			if(bookToRemove != null) {
				library.removeBook(bookToRemove);
				library.displayBooks();
			} else {
				JOptionPane.showMessageDialog(null,  "Book information doesn't exist in the library");
			}
			
			yesNo = JOptionPane.showInputDialog("Do you want to remove another book?\n"
					+"Enter y to continue or press any key to quit");
			}
		while(yesNo.toLowerCase().equals("y"));
	}
}
