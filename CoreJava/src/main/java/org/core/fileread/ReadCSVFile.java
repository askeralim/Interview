package org.core.fileread;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class ReadCSVFile {

	public static void main(String[] args) throws IOException {
		FileReader fileReader = null;
		BufferedReader reader = null;
		try {
			URL url = ReadCSVFile.class.getResource("Koduvally_Data.csv");
			fileReader = new FileReader(url.getFile());
			reader = new BufferedReader(fileReader);
			String data = null;
			do{
				data = reader.readLine();
//				System.out.println(data);
				String[] values = data.split(",");
//				System.out.println(values[0]+"\t"+values[1]+"\t"+values[2]+"\t"+values[3]);
//				String query ="INSERT INTO member(`ID`,`Name`,`Mobile`,`joiningDate`,`balance`,`panchayathId`) VALUES(";
//				query = query +values[0]+",";
//				query = query +"'"+values[1]+"',";
//				query = query +"'"+values[2]+"',";
//				query = query +"'2012-12-26',";
//				query = query +"'"+values[3]+"',";
//				query = query +values[4];
//				query = query +");";
				
				String query ="INSERT INTO memberpayment(memberid,expenseId,amount) VALUES(";
				query = query +values[0]+",";
				query = query +"3,";
				query = query +values[4];
				query = query +");";
				System.out.println(query);

			}while(data !=null);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (Exception e) {
			}
		}
	}
}
