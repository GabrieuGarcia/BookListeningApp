package com.example.android.booklisteningapp;

import org.json.JSONArray;

/**
 * This class is the Book object.
 *
 * Created by Gabriel on 12/03/2018.
 */

public class Book {

    private String booksTitle;
    private JSONArray booksAuthor;
    private String mUrl;

    public Book(String booksTitle, JSONArray booksAuthor, String url) {
        this.booksTitle = booksTitle;
        this.booksAuthor = booksAuthor;
        mUrl = url;
    }

    public String getBooksTitle() {
        return booksTitle;
    }


    public JSONArray getBooksAuthor() {
        return booksAuthor;
    }


    public String getUrl() {
        return mUrl;
    }
}
