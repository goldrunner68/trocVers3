package com.example.myapplicationtroc.Article;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.example.myapplicationtroc.R;
import com.example.myapplicationtroc.User.DbHelperUser;

/**
 * activity to get input from user and insert into SQLite database
 * @author ketan(Visit my <a
 *         href="http://androidsolution4u.blogspot.in/">blog</a>)
 */
public class AddActivityArticle extends Activity implements OnClickListener {
private Button btn_save;
private EditText edit_first,edit_last;
private DbHelperUser mHelper;
private SQLiteDatabase dataBase;
private String id, titre, article;
private boolean isUpdate;
// todo changer les variables pour article
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity_article);
        
        btn_save=(Button)findViewById(R.id.save_btn);
        edit_first=(EditText)findViewById(R.id.frst_editTxt);
        edit_last=(EditText)findViewById(R.id.last_editTxt);
         
       isUpdate=getIntent().getExtras().getBoolean("update");
        if(isUpdate)
        {
        	id=getIntent().getExtras().getString("ID");
        	titre =getIntent().getExtras().getString("Titre");
        	article =getIntent().getExtras().getString("Article");
        	edit_first.setText(titre);
        	edit_last.setText(article);
        	
        }
         
         btn_save.setOnClickListener(this);
         
         mHelper=new DbHelperUser(this);
        
    }

    // saveButton click event 
	public void onClick(View v) {
		titre =edit_first.getText().toString().trim();
		article =edit_last.getText().toString().trim();
		if(titre.length()>0 && article.length()>0)
		{
			saveData();
		}
		else
		{
			AlertDialog.Builder alertBuilder=new AlertDialog.Builder(AddActivityArticle.this);
			alertBuilder.setTitle("Invalid Data");
			alertBuilder.setMessage("Please, Enter valid data");
			alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
					
				}
			});
			alertBuilder.create().show();
		}
		
	}

	/**
	 * save data into SQLite
	 */
	private void saveData(){
		dataBase=mHelper.getWritableDatabase();
		ContentValues values=new ContentValues();
		
		values.put(DbHelperUser.KEY_FNAME, titre);
		values.put(DbHelperUser.KEY_LNAME, article);
		
		System.out.println("");
		if(isUpdate)
		{    
			//update database with new data 
			dataBase.update(DbHelperUser.TABLE_NAME, values, DbHelperUser.KEY_ID+"="+id, null);
		}
		else
		{
			//insert data into database
			dataBase.insert(DbHelperUser.TABLE_NAME, null, values);
		}
		//close database
		dataBase.close();
		finish();
		
		
	}

}
