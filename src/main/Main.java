package main;

import java.sql.ResultSet;
import java.util.ArrayList;

import database.ConectionJDBC;
import database.FillDB;
import database.GetDB;
import models.Book;

public class Main {

	public static void main(String[] args) {

		ConectionJDBC test = new ConectionJDBC();
		test.connect();
		ResultSet rs = null;
		
//		FillDB.fillBook(test, rs);
//		FillDB.fillCatalog(test, rs);
//		FillDB.fillLibrary(test, rs);
//		FillDB.fillCustomer(test, rs);
//		FillDB.fillLoan(test, rs);
		
		GetDB.getLibraryFull(test, rs);
		
	}
}
