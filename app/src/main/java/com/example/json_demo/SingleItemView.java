package com.example.json_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleItemView extends Activity {
	// Declare Variables
	String image;
	String description;
	String title;
	ImageLoader imageLoader = new ImageLoader(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.singleitemview);

		Intent i = getIntent();
		title = i.getStringExtra("title");
		description = i.getStringExtra("description");
		image = i.getStringExtra("image");

		TextView txttitle = (TextView) findViewById(R.id.title1);
		TextView txdescription = (TextView) findViewById(R.id.description1);
		ImageView imgflag = (ImageView) findViewById(R.id.image1);

		// Set results to the TextViews
		txttitle.setText(title);
		txdescription.setText(description);

		//load the image
		imageLoader.DisplayImage(image, imgflag);
	}
}