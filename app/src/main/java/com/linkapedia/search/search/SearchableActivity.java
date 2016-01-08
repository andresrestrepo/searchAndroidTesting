package com.linkapedia.search.search;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SearchableActivity extends AppCompatActivity {

    private static final String TAG = "junk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        Log.i(TAG, "in MySearchableActivity");
        Intent intent = getIntent();
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        // get the query out of the intent
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String searchQuery = intent.getStringExtra(SearchManager.QUERY);
            doSearchQuery(searchQuery);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.i(TAG, "in onNewIntent");
        setIntent(intent);
        handleIntent(intent);
    }

    private void doSearchQuery(String query) {
        Log.i(TAG, "in doSearchQuery, query string: " + query);
//        you would probably include all your search and display code here
    }

    public void doDialogSearchQuery(View view) {
        onSearchRequested();
    }
}
