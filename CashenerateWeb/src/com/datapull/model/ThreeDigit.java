package com.datapull.model;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ThreeDigit {
	
	public int number;
	public String date;
    public String dayOrNight;
    public String language;
    public int winnersNo;
    public int totalPayout;
	
    
    
	public String getDate() {
		return date;
	}



	public void setDate(String date2) {
		this.date = date2;
	}



	public int getNumber() {
		return number;
	}



	public void setNumber(int number) {
		this.number = number;
	}



	public String getDayOrNight() {
		return dayOrNight;
	}



	public void setDayOrNight(String dayOrNight) {
		this.dayOrNight = dayOrNight;
	}



	public String getLanguage() {
		return language;
	}



	public void setLanguage(String language) {
		this.language = language;
	}



	public int getWinnersNo() {
		return winnersNo;
	}



	public void setWinnersNo(int winnersNo) {
		this.winnersNo = winnersNo;
	}



	public int getTotalPayout() {
		return totalPayout;
	}



	public void setTotalPayout(int totalPayout) {
		this.totalPayout = totalPayout;
	}



	public ThreeDigit() {
		super();
		// TODO Auto-generated constructor stub
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	


}
