package com.huawaii.customview;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link PlaceholderFragment_1#newInstance} factory method to
 * create an instance of this fragment.
 */

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment_1 extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public PlaceholderFragment_1() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment_1 newInstance(int sectionNumber) {
        PlaceholderFragment_1 fragment = new PlaceholderFragment_1();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_1, container, false);
        //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        ImageView imageView1 = (ImageView) rootView.findViewById(R.id.mz_big_picture);
        imageView1.setImageBitmap(BitmapFactory.decodeResource(rootView.getResources(), R.mipmap.panda1));

        ImageView imageView2 = (ImageView) rootView.findViewById(R.id.big_picture);
        imageView2.setImageBitmap(BitmapFactory.decodeResource(rootView.getResources(), R.mipmap.panda1));

        return rootView;
    }

}