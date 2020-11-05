package com.example.news.ui.details;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.news.MainActivity;
import com.example.news.R;
import com.example.news.databinding.FragmentDetailsBinding;
import com.example.news.model.NewsArticle;

public class DetailsFragment extends Fragment {

    private NewsArticle mArticle;

    private FragmentDetailsBinding mBinding;

    public DetailsFragment() {
    }

    public static DetailsFragment newInstance(NewsArticle article) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable("article", article);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mArticle = (NewsArticle) getArguments().getSerializable("article");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);

        if(mArticle != null) {
            mBinding.setHeadline(mArticle);
        }

        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);


        return mBinding.getRoot();
    }




    @Override
    public void onDestroyView() {
        mBinding = null;
        super.onDestroyView();
    }
}