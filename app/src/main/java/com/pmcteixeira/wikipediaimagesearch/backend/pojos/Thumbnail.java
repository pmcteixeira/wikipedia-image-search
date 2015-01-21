package com.pmcteixeira.wikipediaimagesearch.backend.pojos;

import com.google.gson.annotations.Expose;

/**
 * Created with Android Studio.
 * User: pedroteixeira pmcteixeira@gmail.com
 * Date: 21/01/15
 * Time: 13:52
 */
public class Thumbnail {

	@Expose
	private String source;
	@Expose
	private Integer width;
	@Expose
	private Integer height;

	/**
	 *
	 * @return
	 * The source
	 */
	public String getSource() {
		return source;
	}

	/**
	 *
	 * @param source
	 * The source
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 *
	 * @return
	 * The width
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 *
	 * @param width
	 * The width
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 *
	 * @return
	 * The height
	 */
	public Integer getHeight() {
		return height;
	}

	/**
	 *
	 * @param height
	 * The height
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}

}
