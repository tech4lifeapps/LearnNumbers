package com.tech4lifeapps.numbers;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomGridViewAdapter extends ArrayAdapter<Item> {
	Context context;
	int layoutResourceId;
	
	ArrayList<Item> data = new ArrayList<Item>();

	public CustomGridViewAdapter(Context context, int layoutResourceId,
			ArrayList<Item> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		RecordHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new RecordHolder();
			holder.txtTitle = (TextView) row.findViewById(R.id.item_text);
			holder.txtNumber = (TextView) row.findViewById(R.id.item_number);
//			holder.imageItem = (ImageView) row.findViewById(R.id.item_image);
			row.setTag(holder);

		} else {
			holder = (RecordHolder) row.getTag();
		}

		// find the Item to work with 
		Item item = data.get(position);
		
		holder.txtNumber.setText(item.getNumber());
		holder.txtTitle.setText(item.getTitle());
//		holder.imageItem.setImageBitmap(item.getImage());
		
		return row;
	}

	static class RecordHolder {
		TextView txtNumber;
		TextView txtTitle;
		ImageView imageItem;
	}
}