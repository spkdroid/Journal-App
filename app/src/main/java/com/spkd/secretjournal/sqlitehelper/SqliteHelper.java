package com.spkd.secretjournal.sqlitehelper;

import android.database.sqlite.SQLiteDatabase;

public class SqliteHelper {
	

	public static final String TABLE_LOG="log";
	public static final String LOG_ID="_id";
	public static final String LOG_TITLE="title";
	public static final String LOG_DATA="summary";
	public static final String LOG_DATE="date";
	
	private static final String DATABASE_CREATE="create table "
			+ TABLE_LOG
			+ "("
			+ LOG_ID + " integer primary key autoincrement,"
			+ LOG_TITLE + " text not null, "
			+ LOG_DATA +" text not null,"
			+ LOG_DATE +" DATETIME DEFAULT CURRENT_TIMESTAMP"
		    + ");";
	
	public static void onCreate(SQLiteDatabase database)
	{
		database.execSQL(DATABASE_CREATE);
	}
	
	public static void onUpgrade(SQLiteDatabase database,int oldversion,int newversion)
	{
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_LOG);
	    onCreate(database);	
	}

}
