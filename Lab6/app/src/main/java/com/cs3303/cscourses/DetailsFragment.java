package com.cs3303.cscourses;
/*
  an instance of this class will create a fragment to display the detailed information
  about a selected item from the ArrayAdapter
 */

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailsFragment extends Fragment {

    public static DetailsFragment newInstance(int index){
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        detailsFragment.setArguments(args);
        return detailsFragment;
    }

    public int getShowIndex(){
        return getArguments().getInt("index",0);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        if(container == null)
            return null;
        ScrollView scrollView = new ScrollView(getActivity() );
        TextView textView = new TextView(getActivity());
        textView.setTextSize(20);
        textView.setBackgroundColor(Color.BLUE);
        textView.setTextColor(Color.WHITE);
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,
                getActivity().getResources().getDisplayMetrics());
        textView.setPadding(padding,padding,padding,padding);
        scrollView.addView(textView);
        textView.setText(CourseInfo.COURSE_INFO[getShowIndex()]);
        return scrollView;
    }
}
