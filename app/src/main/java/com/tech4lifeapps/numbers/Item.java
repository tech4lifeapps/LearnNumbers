package com.tech4lifeapps.numbers;

import android.graphics.Bitmap;

/**
 * 
 * The class behind the scenes
 *
 */

public class Item {
	Bitmap image;
	String number;
	String title;
	
	public Item(Bitmap image, String number, String title) {
		super();
		this.image = image;
		this.number = number;
		this.title = title;
	}
	public Bitmap getImage() {
		return image;
	}
	public void setImage(Bitmap image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

}
