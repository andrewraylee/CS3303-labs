package com.cs3303.cscourses;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

public class CourseFragment extends ListFragment {
    boolean mDualPane;
    int mCurrPosition = 0;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurrPosition);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position);
    }
    public void showDetails(int index){
        mCurrPosition = index;
        if(mDualPane) {
            getListView().setItemChecked(index, true);
            DetailsFragment details = (DetailsFragment)
                    getFragmentManager().findFragmentById(R.id.details);
            if (details == null || details.getShowIndex() != index) {
                details = DetailsFragment.newInstance(index);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.details, details);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        }
        else{
            Intent intent = new Intent();
            intent.setClass(getActivity(),DetailsActivity.class);
            intent.putExtra("index",index);
            startActivity(intent);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<String> connectAdapterToListView = new ArrayAdapter<String>(getActivity(),
                R.layout.activity_main, R.id.label,CourseInfo.COURSES);
        setListAdapter(connectAdapterToListView);
        View detailFrame = getActivity().findViewById(R.id.details);
        mDualPane = (detailFrame != null && detailFrame.getVisibility() == View.VISIBLE);
        if(savedInstanceState != null)
            mCurrPosition = savedInstanceState.getInt("curChoice",0);
        if(mDualPane){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showDetails(mCurrPosition);
        }
        else {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            getListView().setItemChecked(mCurrPosition,true);
        }
    }
}
