package com.linkapedia.search.search;

import android.app.SearchManager;
import android.content.Context;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnSuggestionListener {

    private static final String TAG = "junk";
    private SearchView searchView;
    private SearchFeedResultsAdaptor mSearchViewAdapter;
    public static String[] columns = new String[]{"_id", "FEED_URL"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchActionBarItem = menu.findItem(R.id.searchActionBarItem);
        searchView = (SearchView) MenuItemCompat.getActionView(searchActionBarItem);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String arg0) {
                System.out.println("buscar: " + arg0);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                if (text.length() > 2) {
                    System.out.println("cumplio iniciando busqueda de sugeridos");
                    loadData(text);
                }
                return true;
            }
        });

        searchView.setOnSuggestionListener(this);
        mSearchViewAdapter = new SearchFeedResultsAdaptor(searchView.getContext(), R.layout.search_suggestions, null, columns,null, -1000);
        searchView.setSuggestionsAdapter(mSearchViewAdapter);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @Override
    public boolean onSuggestionSelect(int position) {
        return false;
    }

    @Override
    public boolean onSuggestionClick(int position) {
        return false;
    }

    private void loadData(String searchText) {
        List<String> results = new ArrayList<>(Arrays.asList("wine", "coffee", "buddhism"));
        MatrixCursor matrixCursor = convertToCursor(results);
        mSearchViewAdapter.changeCursor(matrixCursor);
    }

    private MatrixCursor convertToCursor(List<String> feedlyResults) {
        MatrixCursor cursor = new MatrixCursor(columns);
        int i = 0;
        for (String feedlyResult : feedlyResults) {
            String[] temp = new String[2];
            temp[0] = Integer.toString(++i);
            temp[1] = feedlyResult;

            cursor.addRow(temp);
        }
        return cursor;
    }
}
