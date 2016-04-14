package org.core.fileread;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SkypeCSVFile {

	public static void main(String[] args) throws IOException {
		FileReader fileReader = null;
		BufferedReader reader = null;
		Map<String, Integer> total =new HashMap<String, Integer>();
		try {
			URL url = SkypeCSVFile.class.getResource("SkypeCallJuly.csv");
			fileReader = new FileReader(url.getFile());
			reader = new BufferedReader(fileReader);
			String data = null;
			 
			int i=0;
			do{
				data = reader.readLine();
//				System.out.println(data);
				String[] values = data.split(",");
				//System.out.println(i+" "+values[3]+" "+values[7]);
				Integer mins= total.get(values[3]);
				if(!total.containsKey(values[3])){
					mins = Integer.valueOf(0);
					total.put(values[3], mins);
				}
				if(values[7] !=null){
					String[] s=values[7].split(":");
					//System.out.println(s[0]+"   "+s[1]+"  "+s[2]+"  ==> "+(Integer.valueOf(s[0])*60+ Integer.valueOf(s[1])));
					mins = mins + Integer.valueOf(s[0])*60+ Integer.valueOf(s[1]);
					if(Integer.valueOf(s[2])>0)
						mins++;
					total.put(values[3], mins);
					//System.out.println("------>"+mins);
				}
				i++;
			}while(data !=null);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (Exception e) {
			}
		}
		Set<String> keys = total.keySet();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()){
			String key = it.next();
			System.out.println(key +"\t"+total.get(key));
		}
	}
}
