
package com.pmcteixeira.wikipediaimagesearch.backend.pojos;


import java.util.List;

/**
 * Created with Android Studio.
 * User: pedroteixeira pmcteixeira@gmail.com
 * Date: 21/01/15
 * Time: 11:55
 */
public class Pages {


	public List<Page> mPages;

	/**
	 *
	 * @return
	 *     The Pages
	 */
	public List<Page> getPageList() {
		return mPages;
	}

	public void setPages(List<Page> pages) {
		this.mPages = pages;
	}
}
