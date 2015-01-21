
package com.pmcteixeira.wikipediaimagesearch.backend.pojos;

import com.google.gson.annotations.Expose;

public class WikiImgsResponse {

    @Expose
    private Result query;

    /**
     * 
     * @return
     *     The query
     */
    public Result getQuery() {
        return query;
    }

    /**
     * 
     * @param query
     *     The query
     */
    public void setQuery(Result query) {
        this.query = query;
    }

}
