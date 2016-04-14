package org.core.database.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadAccessTable {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		try {
			// Load the database driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection conn = DriverManager
					.getConnection("jdbc:odbc:avenues;password=pw2765836");
			//Connection newUblConn = DriverManager
				//	.getConnection("jdbc:odbc:NEW_UBL;");
			testAvenues(conn);
			//copyBank(ublConn, newUblConn);
			//copyCurrency(ublConn, newUblConn);
			//copyPartyDts(ublConn, newUblConn);
			//ublConn.close();
			//newUblConn.close();
		} catch (SQLException se) {
			System.out.println("SQL Exception:");
			se.printStackTrace();
			while (se != null) {
				System.out.println("State  : " + se.getSQLState());
				System.out.println("Message: " + se.getMessage());
				System.out.println("Error  : " + se.getErrorCode());

				se = se.getNextException();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	private static void testAvenues(Connection conn)
			throws Exception {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM tbl_users");
		while (rs.next()) {
			String name = rs.getString("user_fname");
			System.out.println(name);
		}
		rs.close();
		stmt.close();
		conn.commit();
	}
	private static void copyBank(Connection ubl, Connection newUbl)
			throws Exception {
		Statement stmt = ubl.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT Bank_ID,Bank_Name FROM BankDtls");
		PreparedStatement ps = newUbl
				.prepareStatement("INSERT INTO BANKS(Bank_ID,Bank_Name,Employee_No) VALUES(?,?,?)");
		String name = "";
		while (rs.next()) {
			String id = rs.getString("Bank_ID");
			name = rs.getString("Bank_Name");
			System.out.println(id+"   "+name);
			ps.setInt(1, Integer.parseInt(id));
			ps.setString(2, name);
			ps.setInt(3, 1);
			ps.execute();

		}
		rs.close();
		stmt.close();
		ps.close();
		newUbl.commit();
	}
	private static void copyCurrency(Connection ubl, Connection newUbl)
			throws Exception {
		Statement stmt = ubl.createStatement();
		System.out.println("========== LOADING Currency===========");
		ResultSet rs = stmt.executeQuery("SELECT * From CurrencyTBL");
		PreparedStatement ps = newUbl
				.prepareStatement("INSERT INTO CurrencyTBL(Curr_ID,Curr_Type,Employee_No) VALUES(?,?,?)");
		String name = "";
		while (rs.next()) {
			String id = rs.getString("Curr_ID");
			name = rs.getString("Curr_Type");
			System.out.println(id+"   "+name);
			ps.setInt(1, Integer.parseInt(id));
			ps.setString(2, name);
			ps.setInt(3, 1);
			ps.execute();

		}
		rs.close();
		stmt.close();
		ps.close();
		newUbl.commit();
	}
	private static void copyPartyDts(Connection ubl, Connection newUbl)
			throws Exception {
		Statement stmt = ubl.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT Bank_ID,Party_ID,Party_Name,Party_Acct_Ccy,Party_Acct_No,Party_Acct_Type FROM PartyDtls");
		PreparedStatement ps = newUbl
				.prepareStatement("INSERT INTO PartyDtls(Bank_ID,Party_Name,Party_Acct_Ccy,Party_Acct_No,Party_Acc_Type,Employee_No) VALUES(?,?,?,?,?,?)");
		String name = "";
		while (rs.next()) {
			String bankId = rs.getString("Bank_ID");
			String partyId = rs.getString("Party_ID");
			System.out.println(bankId+"    "+partyId);
			//System.out.println("  "+bankId+"   "+rs.getString("Party_ID")+"  "+rs.getString("Party_Name")+"  "+rs.getString("Party_Acct_Ccy")+ "   "+rs.getString("Party_Acct_No") +"   "+rs.getString("Party_Acct_Type"));
			ps.setInt(1, Integer.parseInt(bankId));
			ps.setString(2, rs.getString("Party_Name"));
			ps.setString(3, rs.getString("Party_Acct_Ccy"));
			ps.setString(4, rs.getString("Party_Acct_No"));
			ps.setString(5, rs.getString("Party_Acct_Type"));
			ps.setInt(6, 1);
			ps.execute();
		}
		rs.close();
		stmt.close();
		ps.close();
		newUbl.commit();
	}
	private static void copyCustomerForexDeal(Connection ubl, Connection newUbl)
			throws Exception {
		Statement stmt = ubl.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT Deal_No,Deal_Dt,Bank_Name,Ccy_Bt,Deal_rt,Amt_bt,Ccy_sld,Amt_bt_dt,Amt_sld_dt,Amt_sld,Local_Eq,Status,Db_acct,Cr_acct FROM Customer");
		PreparedStatement ps = newUbl
				.prepareStatement("INSERT INTO ForexDeal(Deal_No,Deal_Date,Deal_Dt,Ccy_bt,Ccy_cr,Amt_bt,Amt_sld,Rate,Value_date,Local_eq,Entry_date_1,Entry_date_2,Serial_db_no,Serial_cr_no,Serial_db_desc) VALUES(?,?,?,?,?,?)");
		String name = "";
		while (rs.next()) {
			String bankId = rs.getString("Bank_ID");
			String partyId = rs.getString("Party_ID");
			System.out.println(bankId+"    "+partyId);
			//System.out.println("  "+bankId+"   "+rs.getString("Party_ID")+"  "+rs.getString("Party_Name")+"  "+rs.getString("Party_Acct_Ccy")+ "   "+rs.getString("Party_Acct_No") +"   "+rs.getString("Party_Acct_Type"));
			ps.setInt(1, Integer.parseInt(bankId));
			ps.setString(2, rs.getString("Party_Name"));
			ps.setString(3, rs.getString("Party_Acct_Ccy"));
			ps.setString(4, rs.getString("Party_Acct_No"));
			ps.setString(5, rs.getString("Party_Acct_Type"));
			ps.setInt(6, 1);
			ps.execute();
		}
		rs.close();
		stmt.close();
		ps.close();
		newUbl.commit();
	}
}
