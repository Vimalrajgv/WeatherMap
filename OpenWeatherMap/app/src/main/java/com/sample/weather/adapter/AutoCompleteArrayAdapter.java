package com.sample.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sample.weather.activity.WeatherActivity;


/**
 * Created by VimalRaj on 11/12/2017.
 */
public class AutoCompleteArrayAdapter extends ArrayAdapter<String> {

    private static final String TAG = AutoCompleteArrayAdapter.class.getSimpleName();

    private Context mContext;
    private int layoutResourceId;
    private String data[] = null;

    public AutoCompleteArrayAdapter(Context context, int layoutResourceId, String[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        try {
            if (convertView == null) {
                // inflate the layout
                LayoutInflater inflater = ((WeatherActivity) mContext).getLayoutInflater();
                convertView = inflater.inflate(layoutResourceId, parent, false);
            }
            // object item based on the position
            String searchItem = data[position];
            // get the TextView and then set the text
            TextView textItem = (TextView) convertView.findViewById(com.sample.weather.R.id.txtItem);
            textItem.setText(searchItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }
}
