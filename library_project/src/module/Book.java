package module;

public class Book {
	
	// fields - ISBN, title, author, numberPages, genre
	int isbn, numberPages;
	String title, author, genre;
	boolean available;

	//constructor - empty constructor
	public Book() {
		
	}
	
	public Book(int isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = true;
    }

	// setters and getters
	/**
	 * @return the isbn
	 */
	public int getISBN() {
		return isbn;
	}

	/**
	 * @param isbn the isbn to set
	 */
	public void setISBN(int isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the numberPages
	 */
	public int getNumberPages() {
		return numberPages;
	}

	/**
	 * @param numberPages the numberPages to set
	 */
	public void setNumberPages(int numberPages) {
		this.numberPages = numberPages;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @return the available
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * @param available the available to set
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}	
	
	// custom methods
	/**
	 * Provides all book detail for the given book
	 * {@return isbn, title, author}
	 */
	public String display() {
		
		return getISBN()+" "+getTitle()+" "+getAuthor();
	}
}
