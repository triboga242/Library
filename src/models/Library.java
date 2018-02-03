package models;

import java.util.ArrayList;
import java.util.HashMap;

public class Library {
	private int libraryId;
	private String libraryName;
	private String libraryLocation;
	private HashMap<Book, Integer> catalog;
	private ArrayList<Loan> loans;
	private ArrayList<Customer> customers;

	public int getLibraryId() {
		return libraryId;
	}

	public void setLibraryId(int libraryId) {
		this.libraryId = libraryId;
	}

	public String getLibraryName() {
		return libraryName;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	public String getLibraryLocation() {
		return libraryLocation;
	}

	public void setLibraryLocation(String libraryLocation) {
		this.libraryLocation = libraryLocation;
	}

	public HashMap<Book, Integer> getCatalog() {
		return catalog;
	}

	public void setCatalog(HashMap<Book, Integer> catalog) {
		this.catalog = catalog;
	}

	public ArrayList<Loan> getLoans() {
		return loans;
	}

	public void setLoans(ArrayList<Loan> loans) {
		this.loans = loans;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	public Library(int libraryId, String libraryName, String libraryLocation, HashMap<Book, Integer> catalog,
			ArrayList<Loan> loans, ArrayList<Customer> customers) {
		super();
		this.libraryId = libraryId;
		this.libraryName = libraryName;
		this.libraryLocation = libraryLocation;
		this.catalog = catalog;
		this.loans = loans;
		this.customers = customers;
	}

	public Library(int int1, String string, String string2) {
		this.libraryId = int1;
		this.libraryName = string;
		this.libraryLocation = string2;
	}

	
	@Override
	public String toString() {
		return "Library [libraryId=" + libraryId + ", libraryName=" + libraryName + ", libraryLocation="
				+ libraryLocation + "]";
	}

	public String toStringFull() {
		return "Library [libraryId=" + libraryId +
				", libraryName=" + libraryName + 
				", libraryLocation="
				+ libraryLocation + 
				", catalog=" + catalog + 
				", loans=" + loans + 
				", customers=" + customers + "]";
	}



}
