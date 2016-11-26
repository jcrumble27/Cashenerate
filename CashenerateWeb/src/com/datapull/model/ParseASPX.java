package com.datapull.model;
import java.io.*;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;


public class ParseASPX {
	StringBuilder appender = new StringBuilder();
	String inner = "<form action='/QuickRunnerWeb/Presentation' method='post'><select name='item'>";
    String outer = "</select><input type='submit' value='Select SERVER from dropdown'></form>";
	
	public String populate() throws IOException {
		
		//Reference most recent WAS Summary sheet
		Document doc = Jsoup.connect("http://wassupport.delta.com/was/summary.aspx").get();

		//Access the static table 
        //Set the extracted data in appropriate data structures and use them for further processing
        Element table = doc.getElementById("grvWas");
        Elements tds = table.getElementsByTag("td");

        //You can check for nesting of tds if such structure exists
        for (Element td : tds) {
            //System.out.println("\n"+td.text());
        	if (td.text().contains("/admin"))
        	{
        	System.out.println("\n"+td.text());
        	appender.append("<option value='" + td.text() +"'" +"> "+td.text().substring(0, td.text().length() - 22) + "</option>");
        	}
        }
        
        //REturn the syscodes after building appropriate tags around
        return inner+appender.toString()+outer;
	      }
		
	

}
