package com.pmcteixeira.wikipediaimagesearch.backend.pojos;

import com.google.gson.annotations.Expose;

/**
 * Created with Android Studio.
 * User: pedroteixeira pmcteixeira@gmail.com
 * Date: 21/01/15
 * Time: 11:55
 */
public class Page {

	@Expose
	private String title;
	@Expose
	private Integer index;
	@Expose
	private Thumbnail thumbnail;

	/**
	 *
	 * @return
	 * The title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 *
	 * @return
	 * The index
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 *
	 * @return
	 * The thumbnail
	 */
	public Thumbnail getThumbnail() {
		return thumbnail;
	}
}


