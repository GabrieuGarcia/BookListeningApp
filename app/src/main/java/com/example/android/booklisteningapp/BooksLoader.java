package com.example.android.booklisteningapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * This class sets the loader of the app.
 *
 * Created by Gabriel on 14/03/2018.
 */

public class BooksLoader extends AsyncTaskLoader<List<Book>>{

    private String mUrl;

    public BooksLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        List<Book> booksList = QueryUtils.fetchBookData(mUrl);
        return booksList;
    }
}
