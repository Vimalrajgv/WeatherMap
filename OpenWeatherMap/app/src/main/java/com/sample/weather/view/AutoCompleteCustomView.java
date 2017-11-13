package com.sample.weather.view;

import android.content.Context;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.util.AttributeSet;

/**
 * Custom view for auto suggest.
 * Created by VimalRaj on 11/13/2017.
 */

public class AutoCompleteCustomView extends AppCompatAutoCompleteTextView {

    public AutoCompleteCustomView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public AutoCompleteCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public AutoCompleteCustomView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean enoughToFilter() {
        return true;
    }

    @Override
    protected void performFiltering(final CharSequence text, final int keyCode) {
        String filterText = "";
        super.performFiltering(filterText, 0);
    }

   /* @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);

        if (focused && getAdapter() != null) {

            performFiltering("", 0);
        }
    }*/
}
