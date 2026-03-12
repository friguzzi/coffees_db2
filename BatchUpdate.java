/*
 * Copyright 2003 Sun Microsystems, Inc.  ALL RIGHTS RESERVED.
 * Use of this software is authorized pursuant to the terms of the license found at
 * http://developer.java.sun.com/berkeley_license.html.
 */ 
import java.sql.*;

public class BatchUpdate {

	public static void main(String args[]) throws SQLException {

		ResultSet rs = null;
		PreparedStatement ps = null;

		String url = "<jdbc-url>";

		Connection con;
		Statement stmt;


		try {

			con = DriverManager.getConnection(url,"<username>","<password>");
			con.setAutoCommit(false);

			stmt = con.createStatement();  

			stmt.addBatch("INSERT INTO COFFEES " + 
				 "VALUES('Amaretto', 49, 9.99, 0, 0)");
			stmt.addBatch("INSERT INTO COFFEES " +
				"VALUES('Hazelnut', 49, 9.99, 0, 0)");
			stmt.addBatch("INSERT INTO COFFEES " +
				"VALUES('Amaretto_decaf', 49, 10.99, 0, 0)");
			stmt.addBatch("INSERT INTO COFFEES " +
				"VALUES('Hazelnut_decaf', 49, 10.99, 0, 0)");

			int [] updateCounts = stmt.executeBatch();
			con.commit();
			con.setAutoCommit(true);

			ResultSet uprs = stmt.executeQuery("SELECT * FROM COFFEES");

			System.out.println("Table COFFEES after insertion:");
			while (uprs.next()) {
				String name = uprs.getString("COF_NAME");
				int id = uprs.getInt("SUP_ID");
				float price = uprs.getFloat("PRICE");
				int sales = uprs.getInt("SALES");
				int total = uprs.getInt("TOTAL");
				System.out.print(name + "   " + id + "   " + price);
				System.out.println("   " + sales + "   " + total);
			}

			uprs.close();
			stmt.close();
			con.close();

		} catch(BatchUpdateException b) {
			System.err.println("-----BatchUpdateException-----");
			System.err.println("SQLState:  " + b.getSQLState());
			System.err.println("Message:  " + b.getMessage());
			System.err.println("Vendor:  " + b.getErrorCode());
			System.err.print("Update counts:  ");
			int [] updateCounts = b.getUpdateCounts();
			for (int i = 0; i < updateCounts.length; i++) {
				System.err.print(updateCounts[i] + "   ");
			}
			System.err.println("");

		} catch(SQLException ex) {
			System.err.println("-----SQLException-----");
			System.err.println("SQLState:  " + ex.getSQLState());
			System.err.println("Message:  " + ex.getMessage());
			System.err.println("Vendor:  " + ex.getErrorCode());
		}
	}
}

