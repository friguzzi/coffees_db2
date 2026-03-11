/*
 * Copyright 2003 Sun Microsystems, Inc.  ALL RIGHTS RESERVED.
 * Use of this software is authorized pursuant to the terms of the license found at
 * http://developer.java.sun.com/berkeley_license.html.
 */ 

import java.sql.*;

public class SetSavepoint {

    public static void main(String args[]) {
    
		String url = "jdbc:db2://<ip>:<port>/<db>";


	try {

		Connection	con = DriverManager.getConnection(url,"<username>","<password>");
		con.setAutoCommit(false);

		String query = "SELECT COF_NAME, PRICE FROM COFFEES " +
										"WHERE TOTAL > ?";
		String update = "UPDATE COFFEES SET PRICE = ? " +
										"WHERE COF_NAME = ?";

		PreparedStatement getPrice = con.prepareStatement(query);
		PreparedStatement updatePrice = con.prepareStatement(
														update);

		getPrice.setInt(1, 7000);
		ResultSet rs = getPrice.executeQuery();

		Savepoint save1 = con.setSavepoint();

		while (rs.next())  {
			String cof = rs.getString("COF_NAME");
			float oldPrice = rs.getFloat("PRICE");
			float newPrice = oldPrice + (oldPrice * .05f);
			updatePrice.setFloat(1, newPrice);
			updatePrice.setString(2, cof);
			updatePrice.executeUpdate();
			System.out.println("New price of " + cof + " is " +
													newPrice);
			if (newPrice > 11.99) {
				con.rollback(save1);
			}

		}

		getPrice = con.prepareStatement(query);
		updatePrice = con.prepareStatement(update);

		getPrice.setInt(1, 8000);

		rs = getPrice.executeQuery();
		System.out.println();

		Savepoint save2 = con.setSavepoint();

		while (rs.next())  {
			String cof = rs.getString("COF_NAME");
			float oldPrice = rs.getFloat("PRICE");
			float newPrice = oldPrice + (oldPrice * .05f);
			updatePrice.setFloat(1, newPrice);
			updatePrice.setString(2, cof);
			updatePrice.executeUpdate();
			System.out.println("New price of " + cof + " is " + 
													newPrice);
			if (newPrice > 11.99) {
				con.rollback(save2);
			}
		}

		con.commit();

		Statement stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT COF_NAME, " +
				"PRICE FROM COFFEES");

		System.out.println();
		while (rs.next()) {
			String name = rs.getString("COF_NAME");
			float price = rs.getFloat("PRICE");
			System.out.println("Current price of " + name + 
									" is " + price);
		}
		con.commit();

	    con.close();

	} catch (Exception e) {
	    e.printStackTrace();
		}

    }

}
