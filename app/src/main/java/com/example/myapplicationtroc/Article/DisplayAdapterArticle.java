package com.example.myapplicationtroc.Article;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.myapplicationtroc.R;

import java.util.ArrayList;

public class DisplayAdapterArticle extends BaseAdapter {
	private Context mContext;
	private ArrayList<String> id;
	private ArrayList<String> titre;
	private ArrayList<String> article;


	public DisplayAdapterArticle( Context c, ArrayList<String> id, ArrayList<String> fname, ArrayList<String> lname) {
		this.mContext = c;

		this.id = id;
		this.titre = fname;
		this.article = lname;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return id.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int pos, View child, ViewGroup parent) {
		Holder mHolder;
		LayoutInflater layoutInflater;
		if (child == null) {
			layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			child = layoutInflater.inflate(R.layout.listcell, null);
			mHolder = new Holder();
			mHolder.txt_id = (TextView) child.findViewById(R.id.txt_id);
			mHolder.txt_titre = (TextView) child.findViewById(R.id.txt_fName);
			mHolder.txt_article = (TextView) child.findViewById(R.id.txt_lName);
			child.setTag(mHolder);
		} else {
			mHolder = (Holder) child.getTag();
		}
		mHolder.txt_id.setText(id.get(pos));
		mHolder.txt_titre.setText(titre.get(pos));
		mHolder.txt_article.setText(article.get(pos));

		return child;
	}

	public class Holder {
		TextView txt_id;
		TextView txt_titre;
		TextView txt_article;
	}

}
