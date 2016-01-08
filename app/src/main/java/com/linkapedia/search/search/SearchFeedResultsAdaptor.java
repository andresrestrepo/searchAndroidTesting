package com.linkapedia.search.search;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.TextView;

public class SearchFeedResultsAdaptor extends SimpleCursorAdapter {
    private static final String tag=SearchFeedResultsAdaptor.class.getName();
    private Context context=null;
    public SearchFeedResultsAdaptor(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        this.context=context;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textView=(TextView)view.findViewById(R.id.suggested_title);
        textView.setText(cursor.getString(1));
    }
}
