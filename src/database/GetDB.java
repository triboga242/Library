package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import models.Book;
import models.Customer;
import models.Library;
import models.Loan;

public class GetDB {

	public static ArrayList<Book> getBook(ConectionJDBC test, ResultSet rs) {
		ArrayList<Book> al = new ArrayList<>();

		rs = test.execSQL("select * from Book");
		try {
			while (rs.next()) {
				al.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		for (Book c : al) {
//			System.out.println(c.toString());
//		}
		return al;
	}

	public static ArrayList<Customer> getCustomer(ConectionJDBC test, ResultSet rs) {
		ArrayList<Customer> al = new ArrayList<>();

		rs = test.execSQL("select * from Customer");
		try {
			while (rs.next()) {
				if (!al.contains(new Customer(rs.getInt(1), rs.getString(2))))
					al.add(new Customer(rs.getInt(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		for (Customer c : al) {
//			System.out.println(c.toString());
//		}

		return al;
	}

	public static HashMap<Book, Integer> getCatalog(ConectionJDBC test, ResultSet rs) {
		HashMap<Book, Integer> catalog = new HashMap<>();
		ArrayList<Book> al = getBook(test, rs);

		rs = test.execSQL("select * from Catalog");
		try {
			while (rs.next()) {
				for (Book b : al) {
					if (b.getISBN() == rs.getString(2)) {
						catalog.put(b, rs.getInt(3));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return catalog;
	}

	public static ArrayList<Library> getLibrary(ConectionJDBC test, ResultSet rs) {
		ArrayList<Library> al = new ArrayList<>();

		rs = test.execSQL("select * from Library");
		try {
			while (rs.next()) {
				al.add(new Library(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}

	public static ArrayList<Library> getLibraryFull(ConectionJDBC test, ResultSet rs) {
		ArrayList<Library> al = new ArrayList<>();
		ArrayList<Book> books = getBook(test, rs);
		ArrayList<Customer> customersLibrary = new ArrayList<>();
		ArrayList<Customer> customers = getCustomer(test, rs);
		ArrayList<Loan> loans = getLoan(test, rs);
		ArrayList<Loan> loansLibrary = new ArrayList<>();

		HashMap<Book, Integer> catalog = new HashMap<>();

		rs = test.execSQL("select * from Library");
		try {
			while (rs.next()) {
				al.add(new Library(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		rs = test.execSQL("select * from Catalog");
		try {
			while (rs.next()) {
				for (Library l : al) {
					if (rs.getInt(1) == l.getLibraryId()) {
						for (Book b : books) {
							if (b.getISBN() == rs.getString(2)) {
								catalog.put(b, rs.getInt(3));
							}
						}
						l.setCatalog(catalog);
						catalog = new HashMap<>();
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		rs = test.execSQL("select * from Customer");
		try {
			while (rs.next()) {
				for (Library l : al) {
					if (rs.getInt(3) == l.getLibraryId()) {
						for (Customer cus : customers) {
							if (cus.getCustomerID() == rs.getInt(1))
								customersLibrary.add(new Customer(rs.getInt(1), rs.getString(2)));
						}
					}
					l.setCustomers(customers);
					customersLibrary = new ArrayList<>();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		rs = test.execSQL("select * from Loan");
		try {
			while (rs.next()) {
				for (Library l : al) {
					if (rs.getInt(6)==l.getLibraryId()) {
						for (Loan lo :loans) {
							if (l.getLibraryId()==lo.getLibraryID()) {
								loansLibrary.add(lo);
							}
						}
					}
					l.setLoans(loansLibrary);
					loansLibrary= new ArrayList<>();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (Library library:al) {
			System.out.println(library.toStringFull());
		}
		
		return al;
	}

	public static ArrayList<Loan> getLoan(ConectionJDBC test, ResultSet rs) {
		ArrayList<Loan> al = new ArrayList<>();
		ArrayList<Customer> alc = getCustomer(test, rs);
		Customer customer = null;
		rs = test.execSQL("select * from Loan");
		try {
			while (rs.next()) {
				for (Customer c : alc) {
					if (c.getCustomerID() == rs.getInt(3)) {
						customer = c;
					}
				}
				al.add(new Loan(rs.getInt(1), rs.getString(2), customer, rs.getDate(4), rs.getDate(5), rs.getInt(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		for (Loan c : al) {
//			System.out.println(c.toString());
//		}

		return al;
	}
}
