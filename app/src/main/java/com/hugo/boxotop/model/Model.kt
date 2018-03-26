package com.hugo.boxotop.model

/**
 * Created by hpatural on 25/03/2018.
 */
object Model {
    data class SearchResult(var Search: ArrayList<Movie>?,
                                  var totalResults: String?,
                                  var Response: String)
}