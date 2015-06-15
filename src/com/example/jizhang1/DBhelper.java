package com.example.jizhang1;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class DBhelper {

	SQLiteDatabase db;
	public DBhelper(SQLiteDatabase database)
	{
		this.db = database;
	}
	
	
	public boolean CreatTable()
	{
		db.execSQL("CREATE TABLE IF NOT EXISTS "
				+ "info " + "("
				+ "YEAR " + "INTEGER,"
				+ "MONTH " + "INTEGER,"
				+ "DAY " + "INTEGER,"
				+ "TYPE " + "INTEGER,"
				+ "MONEY " + "DOUBLE,"
				+ "COMMENT " + "VARCHAR(50)" 
				+ ")"
				);
		return true;
	}
	
	
	public boolean dbInsert(dataBaseRecord dbrecord) 
	{
		ContentValues cValues = new ContentValues();
		cValues.put("YEAR", dbrecord.getYear());
		cValues.put("MONTH", dbrecord.getMonth());
		cValues.put("DAY", dbrecord.getDay());
		cValues.put("TYPE", dbrecord.getType());
		cValues.put("MONEY", dbrecord.getMoney());
		cValues.put("COMMENT", dbrecord.getComment());
		db.insert("info", null, cValues);
		return true;
	}
	
	public boolean dbFindData(SQLiteDatabase db,dataBaseRecord dbrecord){
		
		return true;
	}
}
