package com.example.news.ui.main;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.news.MainActivity;
import com.example.news.R;
import com.example.news.databinding.MainFragmentBinding;
import com.example.news.model.NewsArticle;

import java.util.List;

public class MainFragment extends Fragment {

    public static MainFragment newInstance() {
        return new MainFragment();
    }


    private MainAdapter mMainAdapter;

    private MainFragmentBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);

        mMainAdapter = new MainAdapter(mHeadlineClickCallback);
        mBinding.newsList.setAdapter(mMainAdapter);

        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);


        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.init();
        mBinding.progressBar.setVisibility(View.VISIBLE);
        viewModel.getNewsRepository().observe(getViewLifecycleOwner(), newsResponse -> {


            if(newsResponse != null) {
                newsResponse.getStatus();
                List<NewsArticle> newsArticles = newsResponse.getArticles();


                mBinding.progressBar.setVisibility(View.GONE);
                if (newsArticles != null) {
                    mMainAdapter.setHeadlinesList(newsArticles);
                } else {
                    // nao ha headlines - tente novamente
                }

                
                mBinding.executePendingBindings();

                mMainAdapter.notifyDataSetChanged();
            }
        });

    }


    @Override
    public void onDestroyView() {
        mBinding = null;
        mMainAdapter = null;
        super.onDestroyView();
    }


    private final HeadlineClickCallback mHeadlineClickCallback = headline -> {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((MainActivity) requireActivity()).show(headline);
        }
    };
}
