package com.pmcteixeira.wikipediaimagesearch.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.pmcteixeira.wikipediaimagesearch.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created with Android Studio.
 * User: pedroteixeira pmcteixeira@gmail.com
 * Date: 23/01/15
 * Time: 01:58
 */
public class SquareImageView extends ImageView implements Target {

	public SquareImageView(Context context) {
		super(context);
	}

	public SquareImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	/**
	 * Callback when an image has been successfully loaded.
	 * <p/>
	 * <strong>Note:</strong> You must not recycle the bitmap.
	 *
	 * @param bitmap
	 * @param from
	 */
	@Override
	public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
		setImageBitmap(bitmap);
	}

	/**
	 * Callback indicating the image could not be successfully loaded.
	 * @param errorDrawable
	 */
	@Override
	public void onBitmapFailed(Drawable errorDrawable) {
		this.setImageResource(R.drawable.placeholder);
	}

	/**
	 * Callback invoked right before your request is submitted.
	 *
	 * @param placeHolderDrawable
	 */
	@Override
	public void onPrepareLoad(Drawable placeHolderDrawable) {
		this.setImageResource(R.drawable.placeholder);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth()); //Snap to width
	}
}
