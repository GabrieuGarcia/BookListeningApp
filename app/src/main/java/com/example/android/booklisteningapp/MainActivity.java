package com.example.android.booklisteningapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the main class where the search stars and return the results.
 *
 * Created by Gabriel on 12/03/2018.
 */
public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    private static final String LOG_TAG = MainActivity.class.getName();
    private static Button btnBuscar;
    private static EditText edtBusca;
    private String textQuery;
    private static final String BOOKS_REQUEST_URL ="https://www.googleapis.com/books/v1/volumes?q=";
    private String queryURL;
    private static final int BOOKS_LOADER_ID = 1;
    private BooksAdapter mAdapter;
    private TextView mEmptyStateTextView;
    private View loadingIndicator;
    private ListView booksListView;

    private ArrayList<Book> booksArrayList = new ArrayList<Book>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingIndicator = findViewById(R.id.loading_indicator);
        edtBusca = (EditText) findViewById(R.id.et_search);
        btnBuscar = (Button) findViewById(R.id.btn_search);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        booksListView = (ListView) findViewById(R.id.list);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkConnectivity();

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(btnBuscar.getWindowToken(), 0);
            }
        });

        mAdapter = new BooksAdapter(this, booksArrayList);

        booksListView.setEmptyView(mEmptyStateTextView);
        booksListView.setAdapter(mAdapter);

    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new BooksLoader(this, queryURL);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        loadingIndicator.setVisibility(View.GONE);

        if (data.size() > 0) {
            BooksAdapter adapterForSearchResults = new BooksAdapter(this, data);

            booksListView.setAdapter(adapterForSearchResults);
            booksArrayList = (ArrayList<Book>) data;

            booksListView.setEmptyView(mEmptyStateTextView);
            mEmptyStateTextView.setText(R.string.no_books);
        } else {
            booksListView.setEmptyView(mEmptyStateTextView);
            mEmptyStateTextView.setText(R.string.no_books);
        }
    }

    public LoaderManager checkConnectivity(){

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            textQuery = edtBusca.getText().toString();
            queryURL = BOOKS_REQUEST_URL + textQuery;
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.restartLoader(BOOKS_LOADER_ID,null,this);
            return loaderManager;

        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
            return null;
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        mAdapter.clear();
    }
}
