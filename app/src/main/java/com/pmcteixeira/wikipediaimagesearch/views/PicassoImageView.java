package com.pmcteixeira.wikipediaimagesearch.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.pmcteixeira.wikipediaimagesearch.R;
import com.pmcteixeira.wikipediaimagesearch.drawables.FadeInDrawable;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created with Android Studio.
 * User: pedroteixeira pmcteixeira@gmail.com
 * Date: 23/01/15
 * Time: 01:58
 */
public class PicassoImageView extends ImageView implements Target {

	private static final int FADE_DURATION = 300; //ms

	public PicassoImageView(Context context) {
		super(context);
	}

	public PicassoImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PicassoImageView(Context context, AttributeSet attrs, int defStyleAttr) {
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

		Drawable nextDrawabe = new BitmapDrawable(getResources(), bitmap);
		Drawable currentDrawable = getDrawable();

		if(currentDrawable != null) {
			Drawable[] layers = new Drawable[] {currentDrawable, nextDrawabe};
			TransitionDrawable crossFade = new TransitionDrawable(layers);
			setImageDrawable(crossFade);
			crossFade.startTransition(FADE_DURATION);
		} else {
			FadeInDrawable fadeInDrawable = new FadeInDrawable(getResources(), bitmap);
			setImageDrawable(fadeInDrawable);
		}
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
}
