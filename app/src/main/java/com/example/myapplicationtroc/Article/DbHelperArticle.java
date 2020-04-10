package com.example.myapplicationtroc.Article;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelperArticle extends SQLiteOpenHelper {
	static String DATABASE_NAME="articledata";
	public static final String TABLE_NAME="article";
	public static final String KEY_TITRE ="titre";
	public static final String KEY_DECRIPTION="description";

	public static final String KEY_ID="id";
	public DbHelperArticle( Context context) {
		super(context, DATABASE_NAME, null, 1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+KEY_ID+" INTEGER PRIMARY KEY, "+ KEY_TITRE +" TEXT, "+KEY_DECRIPTION+" TEXT)";
		db.execSQL(CREATE_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
		onCreate(db);

	}

}
