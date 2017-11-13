package com.sample.weather.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.sample.weather.R;
import com.sample.weather.adapter.AutoCompleteArrayAdapter;
import com.sample.weather.handler.DataStoreHandler;
import com.sample.weather.model.WeatherSearchResponse;
import com.sample.weather.service.ServiceManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Search fragment view.
 * Created by VimalRaj on 11/12/2017.
 */

public class SearchWeatherFragment extends Fragment {


    /**
     * Interface to communicate the search result to activity.
     */
    public interface OnSearchListener {
        /**
         * Callback when response is fetched from service.
         *
         * @param query        - To save for auto suggest
         * @param searchResult - Displays the search results in UI.
         */
        void onSearchResult(String query, WeatherSearchResponse searchResult);
    }

    private static final String TAG = SearchWeatherFragment.class.getSimpleName();
    private AutoCompleteTextView autoCompleteTextView;
    private Button searchButton;
    private String searchText;
    private OnSearchListener listener;
    private AutoCompleteArrayAdapter adapter;
    private List<String> queryList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_weather, container, false);
        autoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.txtAutoInput);
        searchButton = (Button) view.findViewById(R.id.btnSearch);
        listener = (OnSearchListener) getActivity();
        Set<String> querySet = DataStoreHandler.retrieveSearchHistory(getContext());
        if (querySet != null) {
            //Set the adapter for auto load view.
            queryList = new ArrayList<>(querySet);
            adapter = new AutoCompleteArrayAdapter(getContext(), R.layout.list_item_view, queryList);
            autoCompleteTextView.setAdapter(adapter);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        autoCompleteTextView.setText("");
        //Listener to enable or disable search btn based on the user input.
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchText = s.toString();
                if (!searchText.isEmpty()) {
                    searchButton.setEnabled(true);
                } else {
                    searchButton.setEnabled(false);
                }
            }
        });

        //Listener gets called when auto suggest item is selected.
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new WeatherServiceTask().execute(queryList.get(position));
            }
        });

        //Listener for search button. Executes the service call from user input data.
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getActivity().getCurrentFocus();
                if (view != null) {
                    InputMethodManager im = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                    im.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                new WeatherServiceTask().execute(searchText);
            }
        });
    }

    /**
     * Connects the service api in background and publish the response to UI.
     */
    private class WeatherServiceTask extends AsyncTask<String, Void, WeatherSearchResponse> {

        @Override
        protected WeatherSearchResponse doInBackground(String... params) {
            Map<String, String> requestParams = new HashMap<>();
            requestParams.put("q", params[0]);
            requestParams.put("units", getString(R.string.units));
            requestParams.put("APPID", getString(R.string.app_key));
            return ServiceManager.retrieveWeather(requestParams);
        }

        @Override
        protected void onPostExecute(WeatherSearchResponse response) {
            super.onPostExecute(response);
            listener.onSearchResult(searchText, response);
        }
    }

}
