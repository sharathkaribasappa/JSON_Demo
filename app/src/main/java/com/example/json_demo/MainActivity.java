package com.example.json_demo;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class MainActivity extends Activity {

	JSONObject mJsonobject;
	JSONArray mJsonarray;
	ListView mListview;
	ListViewAdapter mAdapter;
	ProgressDialog mProgressDialog;
	ArrayList<HashMap<String, String>> mArraylist;

	public static String DESCRIPTION = "description";
	public static String TITLE = "title";
	public static String IMAGE = "image";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		new DownloadJson().execute();
	}
	
	private class DownloadJson extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressdialog
			mProgressDialog = new ProgressDialog(MainActivity.this);
			// Set progressdialog message
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			// Show progressdialog
			mProgressDialog.show();
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			mArraylist = new ArrayList<HashMap<String,String>>();
			
			mJsonarray = JSONfunctions
					.getJSONfromURL("https://gist.githubusercontent.com/maclir/f715d78b49c3b4b3b77f/raw/8854ab2fe4cbe2a5919cea97d71b714ae5a4838d/items.json");

			try {
				if(mJsonarray == null)
					return null;
				
				for (int i = 0; i < mJsonarray.length(); i++) {
					HashMap<String, String> map = new HashMap<String, String>();
					mJsonobject = mJsonarray.getJSONObject(i);
					// Retrieve JSON Objects
					map.put("image", mJsonobject.getString("image"));
					map.put("description", mJsonobject.getString("description"));
					map.put("title", mJsonobject.getString("title"));
					// Set the JSON Objects into the array
					mArraylist.add(map);
				}
				Log.e("sharath","*** mArraylist size:" + mArraylist.size());
			}catch (JSONException e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void args) {
			// Locate the listview in listview_main.xml
						mListview = (ListView) findViewById(R.id.list_view);
						// Pass the results into ListViewAdapter.java
						mAdapter = new ListViewAdapter(MainActivity.this, mArraylist);
						// Set the adapter to the ListView
						mListview.setAdapter(mAdapter);
						mProgressDialog.dismiss();
		}
	}
}