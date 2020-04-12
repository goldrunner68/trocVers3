package com.example.myapplicationtroc.Article;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplicationtroc.R;
import com.example.myapplicationtroc.User.AddActivityUser;
import com.example.myapplicationtroc.User.DbHelperUser;

import java.util.ArrayList;

public class DisplayActivityArticle extends AppCompatActivity {

	private DbHelperUser mHelper;
	private SQLiteDatabase dataBase;

	private ArrayList<String> arti_id = new ArrayList<String>();
	private ArrayList<String> arti_titre = new ArrayList<String>();
	private ArrayList<String> arti_article = new ArrayList<String>();

	private ListView articleList;
	private AlertDialog.Builder build;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_activity_article);

		articleList = (ListView) findViewById(R.id.List);

		mHelper = new DbHelperUser(this);
		
		//add new record
		findViewById(R.id.btnAddArticle).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Intent i = new Intent(getApplicationContext(),
						AddActivityArticle.class);
				i.putExtra("update", false);
				startActivity(i);

			}
		});
		
		//click to update data
		articleList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				Intent i = new Intent(getApplicationContext(),
						AddActivityUser.class);
				i.putExtra("Titre", arti_titre.get(arg2));
				i.putExtra("Article", arti_article.get(arg2));
				i.putExtra("ID", arti_id.get(arg2));
				i.putExtra("update", true);
				startActivity(i);

			}
		});
		
		//long click to delete data
		articleList.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {

				build = new AlertDialog.Builder(DisplayActivityArticle.this);
				build.setTitle("Delete " + arti_titre.get(arg2) + " "
						+ arti_article.get(arg2));
				build.setMessage("Do you want to delete ?");
				build.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {

							@SuppressLint("WrongConstant")
							public void onClick( DialogInterface dialog,
												 int which) {

								Toast.makeText(
										getApplicationContext(),
										arti_titre.get(arg2) + " "
												+ arti_article.get(arg2)
												+ " is deleted.", 3000).show();

								dataBase.delete(
										DbHelperUser.TABLE_NAME,
										DbHelperUser.KEY_ID + "="
												+ arti_id.get(arg2), null);
								displayData();
								dialog.cancel();
							}
						});

				build.setNegativeButton("No",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
							}
						});
				AlertDialog alert = build.create();
				alert.show();

				return true;
			}
		});
	}

	@Override
	protected void onResume() {
		displayData();
		super.onResume();
	}

	/**
	 * displays data from SQLite
	 */
	private void displayData() {
		dataBase = mHelper.getWritableDatabase();
		Cursor mCursor = dataBase.rawQuery("SELECT * FROM "
				+ DbHelperUser.TABLE_NAME, null);

		arti_id.clear();
		arti_titre.clear();
		arti_article.clear();
		if (mCursor.moveToFirst()) {
			do {
				arti_id.add(mCursor.getString(mCursor.getColumnIndex(DbHelperUser.KEY_ID)));
				arti_titre.add(mCursor.getString(mCursor.getColumnIndex(DbHelperUser.KEY_FNAME)));
				arti_article.add(mCursor.getString(mCursor.getColumnIndex(DbHelperUser.KEY_LNAME)));

			} while (mCursor.moveToNext());
		}
		DisplayAdapterArticle disadpt = new DisplayAdapterArticle(DisplayActivityArticle.this, arti_id , arti_titre , arti_article);
		articleList.setAdapter(disadpt);
		mCursor.close();
	}

	

}
