/*
 * Copyright 2003 Sun Microsystems, Inc.  ALL RIGHTS RESERVED.
 * Use of this software is authorized pursuant to the terms of the license found at
 * http://developer.java.sun.com/berkeley_license.html.
 */ 

import java.sql.*;

public class InsertRows {

	public static void main(String args[]) {

		String url = "jdbc:db2://<ip>:<port>/<db>";
		Connection con;
		Statement stmt;
		

		try {

			con = DriverManager.getConnection(url,"<username>","<password>");

			stmt = con.createStatement(
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet uprs = stmt.executeQuery("SELECT * FROM COFFEES");

			uprs.moveToInsertRow();

			uprs.updateString("COF_NAME", "Kona");
			uprs.updateInt("SUP_ID", 150);
			uprs.updateFloat("PRICE", 10.99f);
			uprs.updateInt("SALES", 0);
			uprs.updateInt("TOTAL", 0);

			uprs.insertRow();

			uprs.updateString("COF_NAME", "Kona_Decaf");
			uprs.updateInt("SUP_ID", 150);
			uprs.updateFloat("PRICE", 11.99f);
			uprs.updateInt("SALES", 0);
			uprs.updateInt("TOTAL", 0);

			uprs.insertRow();

			uprs.beforeFirst();

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

		} catch(SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	}
}

