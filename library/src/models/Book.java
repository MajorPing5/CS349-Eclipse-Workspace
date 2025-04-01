package models;

public class Book {
	// fields - id, title, author, numberPages, genre
	private int id, numberPages;
	private String title, author, genre;
	private boolean available;
	
	// constructor - empty constructor
	public Book() {
		
	}
	
	public Book(int id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.available = true;
	}
	
	/**
	 * @return id
	 */
	public int getID() {
		return id;
	}
	
	
	public void setID(int id) {
		this.id = id;
	}
	
	
	public int getNumberPages() {
		return numberPages;
	}
	
	
	public void setNumberPages(int numberPages) {
		this.numberPages = numberPages;
	}
	
	
	public String getTitle() {
		return title;
	}
	
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getAuthor() {
		return author;
	}
	
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	public String getGenre() {
		return genre;
	}
	
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
	public boolean isAvailable() {
		return available;
	}
	
	
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public String display() {
		return getID()+getTitle()+getAuthor();
	}
	
}
