package com.sample.weather.fragment;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.sample.weather.R;
import com.sample.weather.model.WeatherSearchResponse;
import com.sample.weather.service.ServiceManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by VimalRaj on 11/12/2017.
 */

public class SearchWeatherFragment extends Fragment {


    /**
     * Interface to communicate the search result to activity.
     */
    public interface OnSearchListener {
        void onSearchResult(WeatherSearchResponse searchResult);
    }

    private static final String TAG = SearchWeatherFragment.class.getSimpleName();
    private AutoCompleteTextView autoCompleteTextView;
    private Button searchButton;
    private String searchText;
    private OnSearchListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_weather, container, false);
        autoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.txtAutoInput);
        searchButton = (Button) view.findViewById(R.id.btnSearch);
        listener = (OnSearchListener) getActivity();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //Logic to capture user entered data and to enable/disable search button based on user entry.
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
                if(!searchText.isEmpty()) {
                    searchButton.setEnabled(true);
                } else {
                    searchButton.setEnabled(false);
                }
            }
        });

        //Click listener for search button. Used to execute the service call with user input data.
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getActivity().getCurrentFocus();
                if (view != null) {
                    InputMethodManager im = (InputMethodManager)getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                    im.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                new WeatherServiceTask().execute(searchText);
            }
        });
    }

    private class WeatherServiceTask extends AsyncTask<String,Void,WeatherSearchResponse>{

        @Override
        protected WeatherSearchResponse doInBackground(String... params) {
            Map<String,String> requestParams = new HashMap<>();
            requestParams.put("q",params[0]);
            requestParams.put("units","imperial");
            requestParams.put("APPID","8fa716580744fefa5380aaae93949e93");
            return ServiceManager.retrieveWeather(requestParams);
        }

        @Override
        protected void onPostExecute(WeatherSearchResponse response) {
            super.onPostExecute(response);
            listener.onSearchResult(response);
        }
    }

}
