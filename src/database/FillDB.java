package database;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import models.Book;
import models.Customer;
import models.Library;

public class FillDB {

	public static void fillLoan(ConectionJDBC test, ResultSet rs) {
		ArrayList<Customer> alc = GetDB.getCustomer(test, rs);
		ArrayList<Book> alb = GetDB.getBook(test, rs);
		ArrayList<Library> all = GetDB.getLibrary(test, rs);

		int aux = int_aleatorio(100, 500);
		for (int i = 0; i < aux; i++) {
			Calendar cal =aleDate();
			Date date=(Date) cal.getTime();
			cal.add(Calendar.DAY_OF_YEAR, + int_aleatorio(1, 30));
			Date date2=(Date) cal.getTime();
			int day=int_aleatorio(1, 31);
			int month = int_aleatorio(1, 12);
			int year = int_aleatorio(1999, 2018);
			String fecha = year + "-" + month + "-" + day;
			int day2 = day+int_aleatorio(7, 25);
			int month2 = (month+int_aleatorio(0, 2));
			int year2=year;
			if (day2>31) {
				day2=(int_aleatorio(0, 30));
				month2++;
				if (month2>12) {
					month2=1;
					year2=1;
				}
			}
			String fecha2 = year2 + "-" + month2 + "-" + day2;

			
			rs = test.execSQL("insert into Loan(Loan_ID, ISBN, Customer_ID, Beginin, Endind, Library_ID ) \n"
					+ "values (" + i + ", '" 
					+ alb.get(int_aleatorio(0, alb.size())).getISBN() + "', "
					+ alc.get(int_aleatorio(0, alc.size())).getCustomerID() + ", '"
					+ fecha +"', '"
					+ fecha2 +"', "
					+ all.get(int_aleatorio(0, all.size())).getLibraryId() + ");");
		}

	}

	public static Calendar aleDate() {
		Calendar date;

		int number = 0;
		Random aleatorio;
		aleatorio = new Random();

		date = Calendar.getInstance();
		date.set(aleatorio.nextInt(10) + 2014, aleatorio.nextInt(12) + 1, aleatorio.nextInt(30) + 1);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy");
		System.out.println("La fecha vale " + sdf.format(date.getTime()));

		return date;
	}

	public static void fillCustomer(ConectionJDBC test, ResultSet rs) {
		ArrayList<Library> all = GetDB.getLibrary(test, rs);
		int aux = int_aleatorio(100, 500);
		for (int i = 0; i < aux; i++) {
			rs = test.execSQL("insert into Customer(Customer_ID, Customer_Name, Library_ID) \n" + "values (" + i + ", '"
					+ user_aleatorio() + "'," + all.get(int_aleatorio(0, all.size())).getLibraryId() + ");");
		}
	}

	public static void fillLibrary(ConectionJDBC test, ResultSet rs) {
		int aux = int_aleatorio(1, 5);
		for (int i = 0; i < aux; i++) {
			rs = test.execSQL("insert into Library(Library_ID, Library_Name, Library_Location) \n" + "values (" + i
					+ ", '" + user_aleatorio() + "', '" + user_aleatorio() + "');");
		}
	}

	public static void fillBook(ConectionJDBC test, ResultSet rs) {
		int aux = int_aleatorio(100, 500);
		for (int i = 0; i < aux; i++) {
			rs = test.execSQL("insert into Book(ISBN, Book_Name, Genre) \n" + "values ('" + user_aleatorio() + "', '"
					+ user_aleatorio() + "', '" + user_aleatorio() + "');");
		}
	}

	public static void fillCatalog(ConectionJDBC test, ResultSet rs) {

		ArrayList<Book> alb = GetDB.getBook(test, rs);
		ArrayList<Library> all = GetDB.getLibrary(test, rs);

		System.out.println(all.size());

		for (int i = 0; i < all.size(); i++) {
			System.out.println("Catalogo");
			for (int j = 0; j < alb.size(); j++) {
				if (int_aleatorio(0, 5) > 2) {
					rs = test.execSQL("insert into Catalog(Library_ID, ISBN, Cuantity) \n" + "values ("
							+ all.get(i).getLibraryId() + ", '" + alb.get(j).getISBN() + "', " + int_aleatorio(1, 50)
							+ ");");
					System.out.println("if Catalogo");
				}
			}
		}
	}

	public static String user_aleatorio() {

		char[] elementos = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q',
				'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

		char[] conjunto = new char[8];

		for (int i = 0; i < 8; i++) {
			int el = (int) (Math.random() * 26);
			conjunto[i] = (char) elementos[el];
		}
		return new String(conjunto);
	}

	public static int int_aleatorio(int N, int M) {
		int valorEntero = (int) Math.floor(Math.random() * (N - M + 1) + M);

		return valorEntero;
	}
}
