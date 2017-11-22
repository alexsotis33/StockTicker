package stockticker;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class StockPricer {


	
	public StockPricer(){

	}// End constructor
	
	
	public String getPrice(String ticker) throws IOException{
            // offline mode
            //return "300.00";
            
		URL url = new URL("https://finance.google.com/finance?q=NASDAQ:"+ticker);
		URLConnection urlConn = url.openConnection();
		InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
		BufferedReader buff = new BufferedReader(inStream);
		String price = "not found";
		String line = buff.readLine();

		boolean keepGoing = true;
		while(line != null  && keepGoing){

			if(line.contains("[\""+ticker+"\",")){

				int target = line.indexOf("[\""+ticker+"\",");
				
				int deci = line.indexOf("\"", target);
				deci = line.indexOf("\"", deci+1);
				deci = line.indexOf("\"", deci+1);
				deci = line.indexOf("\"", deci+1);
				deci = line.indexOf(".", deci+1);

				
				int start = deci;
				while(line.charAt(start) != '\"'){
					start--;
				}
				price = line.substring(start + 1, deci +3);
				keepGoing = false;
				
			}
			
			line = buff.readLine();
			
		}

		price = price.replace(",","");
		
		return  price;
		
	}// End getPrice
	
	
	private static void print(Object message){		
		System.out.println(message);
	}// End print
	

	
}// End Stock
