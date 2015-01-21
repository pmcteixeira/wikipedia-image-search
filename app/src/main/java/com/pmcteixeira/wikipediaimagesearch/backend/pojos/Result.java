
package com.pmcteixeira.wikipediaimagesearch.backend.pojos;

import com.google.gson.annotations.Expose;

public class Result {

    @Expose
    private Pages pages;

    /**
     * 
     * @return
     *     The pages
     */
    public Pages getPages() {
        return pages;
    }

    /**
     * 
     * @param pages
     *     The pages
     */
    public void setPages(Pages pages) {
        this.pages = pages;
    }

}
