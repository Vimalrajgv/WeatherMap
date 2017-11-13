package com.sample.weather.handler;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;
import java.util.TreeSet;

/**
 * Data store handler, maintains the search result.
 * Created by VimalRaj on 11/13/2017.
 */

public class DataStoreHandler {

    private static final String SHARED_PREFS_FILE = "applicationProfile";

    private static final String SEARCH_QUERY = "searchQuery";

    /**
     * Retrieves the search history
     *
     * @param context - Context
     * @return - result
     */
    public static Set<String> retrieveSearchHistory(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        return prefs.getStringSet(SEARCH_QUERY, null);
    }

    /**
     * Saves the searched query.
     *
     * @param context - Context
     * @param query   - query to save
     */
    public static void saveSearchQuery(Context context, String query) {
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        Set<String> querySet = prefs.getStringSet(SEARCH_QUERY, null);
        if (querySet != null) {
            querySet.add(query);
        } else {
            querySet = new TreeSet<>();
            querySet.add(query);
        }
        SharedPreferences.Editor e = prefs.edit();
        e.clear();
        e.putStringSet(SEARCH_QUERY, querySet);
        e.apply();
    }
}
