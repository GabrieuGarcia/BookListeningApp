package com.example.android.booklisteningapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * This class adapts the layout of the app.
 *
 * Created by Gabriel on 12/03/2018.
 */
public class BooksAdapter extends ArrayAdapter<Book> {

    public BooksAdapter(Context context, List<Book> bookList) {
        super(context, 0, bookList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.books_item_list, parent, false);
        }

        Book currentBook = getItem(position);

        TextView titleView = (TextView) listItemView.findViewById(R.id.title_book);
        titleView.setText(currentBook.getBooksTitle());

        TextView sectionView = (TextView) listItemView.findViewById(R.id.author_book);
        sectionView.setText(currentBook.getBooksAuthor().toString());

        return listItemView;
    }
}