package com.pmcteixeira.wikipediaimagesearch.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pmcteixeira.wikipediaimagesearch.R;
import com.pmcteixeira.wikipediaimagesearch.backend.pojos.Page;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with Android Studio.
 * User: pedroteixeira pmcteixeira@gmail.com
 * Date: 21/01/15
 * Time: 03:09
 */
public class ImageGridViewAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private Map<Integer,Page> mPagesMap = new HashMap<>();
	private int count;

	public ImageGridViewAdapter(Context c) {
		mContext = c;
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void updateList( List<Page> pagesList) {
		count = 0;
		mPagesMap = new HashMap<>();
		for(int i=0;i<pagesList.size();i++) {
			mPagesMap.put(i, pagesList.get(i));
			count++;

			// Warm up cache
			if(mPagesMap.get(i).getThumbnail() !=  null) {
				String url = mPagesMap.get(i).getThumbnail().getSource();
				Picasso.with(mContext).load(url).fetch();
			}
		}
	}

	public int getCount() {
		return count;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		View gridItemView;

		if (convertView == null) {

			gridItemView = mInflater.inflate(R.layout.grid_item, null);

			ViewHolder viewHolder = new ViewHolder();
			viewHolder.caption = (TextView) gridItemView.findViewById(R.id.caption);
			viewHolder.thumbnail = (ImageView) gridItemView.findViewById(R.id.thumbnail);

			gridItemView.setTag(viewHolder);
		} else {
			gridItemView = convertView;
		}

		ViewHolder viewHolder = (ViewHolder) gridItemView.getTag();
		if(viewHolder != null) {
			if(mPagesMap != null) {
				Page page = mPagesMap.get(position);

				viewHolder.caption.setText(page.getTitle());

				if(page.getThumbnail() != null) {

					Picasso.with(mContext)
							.load(page.getThumbnail().getSource())
							.placeholder(R.drawable.placeholder)
							.fit()
							.noFade()
							.into(viewHolder.thumbnail);
				} else {
					// Image place holder
					viewHolder.thumbnail.setImageResource(R.drawable.placeholder);
				}
			}
		}

		return gridItemView;
	}

	protected class ViewHolder {
		public ImageView thumbnail;
		public TextView caption;
	}
}
