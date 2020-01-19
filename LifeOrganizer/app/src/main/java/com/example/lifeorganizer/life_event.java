package com.example.lifeorganizer;
public class life_event{
	int date, month, year;
	String event_description;
	//img picture; //stretch goal
	life_event(int date, int month, int year, String event_description){

		this.date = date;
		this.month = month;
		this.year = year;
	    //this.time_start
		this.event_description = event_description;

		//this.picture; //stretch goal
	}
	
}