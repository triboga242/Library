package models;

import java.util.Date;

public class Loan {
	private int loanID;
	private int libraryID;
	private String ISBN;
	private Customer customer;
	private Date beguining;
	private Date ending;

	public Loan(int loanID, String iSBN, Customer customerID, Date beguining, Date ending, int libraryID) {
		super();
		this.loanID = loanID;
		ISBN = iSBN;
		this.customer = customerID;
		this.beguining = beguining;
		this.ending = ending;
		this.libraryID = libraryID;
	}

	public int getLoanID() {
		return loanID;
	}

	public void setLoanID(int loanID) {
		this.loanID = loanID;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public Date getBeguining() {
		return beguining;
	}

	public void setBeguining(Date beguining) {
		this.beguining = beguining;
	}

	public Date getEnding() {
		return ending;
	}

	public void setEnding(Date ending) {
		this.ending = ending;
	}

	@Override
	public String toString() {
		return "Loan [loanID=" + loanID + ", ISBN=" + ISBN + ", customerID=" + customer + ", beguining=" + beguining
				+ ", ending=" + ending + ", " + libraryID + "]";
	}

	public int getLibraryID() {
		return libraryID;
	}

	public void setLibraryID(int libraryID) {
		this.libraryID = libraryID;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
