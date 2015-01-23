package com.pmcteixeira.wikipediaimagesearch.drawables;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.SystemClock;

/**
 * Created with Android Studio.
 * User: pedroteixeira pmcteixeira@gmail.com
 * Date: 23/01/15
 * Time: 14:08
 */
public class FadeInDrawable extends BitmapDrawable {

	private static final float FADE_DURATION = 400f; //ms
	private long startTimeMillis;
	int alpha = 0xFF;

	/**
	 * Create drawable from a bitmap, setting initial target density based on
	 * the display metrics of the resources.
	 *
	 * @param res
	 * @param bitmap
	 */
	public FadeInDrawable(Resources res, Bitmap bitmap) {
		super(res, bitmap);
		startTimeMillis = SystemClock.uptimeMillis();
	}

	@Override
	public void draw(Canvas canvas) {

		float normalized = (SystemClock.uptimeMillis() - startTimeMillis) / FADE_DURATION;
		if (normalized >= 1f) {
			super.draw(canvas);
		} else {

			int partialAlpha = (int) (alpha * normalized);
			super.setAlpha(partialAlpha);
			super.draw(canvas);
			super.setAlpha(alpha);
			if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1) {
				invalidateSelf();
			}
		}
	}
}
