package com.example.jizhang1;

public class dataBaseRecord {

	//消费种类
	public final static int SHOPPING = 1;
	public final static int TRAFFIC = 2;
	public final static int DIET = 3;
	public final static int LIFE = 4;
	public final static int RELATION = 5;
	public final static int OTHER = 6;
	//年月日、种类、金钱及备注
	private int Year,Month,Day,Type;
	private double Money;
	private String Comment;
	
	public dataBaseRecord() {
		this.Year = 0;
		this.Month = 0;
		this.Day = 0;
		this.Type = 0;
		this.Money = 0;
		this.Comment = null;
	}
	
	public dataBaseRecord(int year, int month, int day, int type, int money){
		this.Year = year;
		this.Month = month;
		this.Day = day;
		this.Type = type;
		this.Money = money;
		this.Comment = null;
	}
	public dataBaseRecord(int year, int month, int day, int type, int money, String comment){
		this.Year = year;
		this.Month = month;
		this.Day = day;
		this.Type = type;
		this.Money = money;
		this.Comment = comment;
	}
	
	public int getYear(){
		return this.Year;
	}
	
	public int getMonth(){
		return this.Month;
	}
	
	public int getDay()
	{
		return this.Day;
	}
	
	public double getMoney(){
		return this.Money;
	}
	
	public int getType(){
		return this.Type;
	}
	
	public String getComment(){
		return this.Comment;
	}
	
	public void setYear(int year){
		this.Year = year;
	}
	
	public void setMonth(int month){
		this.Month = month;
	}
	
	public void setDay(int day){
		this.Day = day;
	}
	
	public void setMoney(double money){
		this.Money = money;
	}
	
	public void setType(int type){
		this.Type = type;
	}
	
	public void setComment(String comment){
		this.Comment = comment;
	}
}
