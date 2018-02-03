package models;

public class Book {
	private String ISBN;
	private String Name;
	private String Genre;
	
	
	public Book(String iSBN, String name, String genre) {
		super();
		ISBN = iSBN;
		Name = name;
		Genre = genre;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getGenre() {
		return Genre;
	}
	public void setGenre(String genre) {
		Genre = genre;
	}
	@Override
	public String toString() {
		return "Book [ISBN=" + ISBN + ", Name=" + Name + ", Genre=" + Genre + "]";
	}

}
