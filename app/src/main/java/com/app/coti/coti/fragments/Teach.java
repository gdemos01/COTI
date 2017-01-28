package com.app.coti.coti.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.coti.coti.R;

/**
 * Created by User on 27/01/2017.
 */

public class Teach extends Fragment {

    private View view;

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public Teach() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Teach newInstance(int sectionNumber) {
        Teach fragment = new Teach();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view==null){
            view =  inflater.inflate(R.layout.teach_layout, container, false);
        }

        return view;
    }
}
