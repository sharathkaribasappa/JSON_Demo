package com.example.json_demo;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

	Context mContext;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> mData;
	ImageLoader imageLoader;
	HashMap<String, String> resultp = new HashMap<String, String>();

	public ListViewAdapter(Context context,ArrayList<HashMap<String, String>> arraylist) {
		mContext = context;
		mData = arraylist;
		imageLoader = new ImageLoader(context);
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// Declare Variables
		TextView description;
		TextView title;
		ImageView image;

		inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView = inflater.inflate(R.layout.list_view_item, parent, false);
		// Get the position
		resultp = mData.get(position);

		// Locate the TextViews in listview_item.xml
		description = (TextView) itemView.findViewById(R.id.description);
		title = (TextView) itemView.findViewById(R.id.title);

		// Locate the ImageView in listview_item.xml
		image = (ImageView) itemView.findViewById(R.id.image);

		// Capture position and set results to the TextViews
		description.setText(resultp.get(MainActivity.DESCRIPTION));
		title.setText(resultp.get(MainActivity.TITLE));
		// Capture position and set results to the ImageView
		// Passes flag images URL into ImageLoader.class
		imageLoader.DisplayImage(resultp.get(MainActivity.IMAGE), image);
		// Capture ListView item click
		itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Get the position
				resultp = mData.get(position);
				Intent intent = new Intent(mContext, SingleItemView.class);

				intent.putExtra("title", resultp.get(MainActivity.TITLE));
				intent.putExtra("description", resultp.get(MainActivity.DESCRIPTION));
				intent.putExtra("image", resultp.get(MainActivity.IMAGE));

				mContext.startActivity(intent);

			}
		});
		return itemView;
	}
}
