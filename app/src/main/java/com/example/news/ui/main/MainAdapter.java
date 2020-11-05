package com.example.news.ui.main;


import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;
import com.example.news.databinding.HeadlineItemBinding;
import com.example.news.model.NewsArticle;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    List<? extends NewsArticle> mHeadlineList;

    @Nullable
    private final HeadlineClickCallback headlineClickCallback;

    public MainAdapter(@Nullable HeadlineClickCallback clickCallback) {
        headlineClickCallback = clickCallback;
    }

    public void setHeadlinesList(final List<? extends NewsArticle> headlineList) {
        mHeadlineList = headlineList;
    }

    @Override
    @NonNull
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HeadlineItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.headline_item, parent, false);
        binding.setCallback(headlineClickCallback);

        return new MainViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.binding.setHeadline(mHeadlineList.get(position));
        holder.binding.executePendingBindings();
    }


    @Override
    public int getItemCount() {
        return mHeadlineList == null ? 0 : mHeadlineList.size();
    }






    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.get()
                .load(imageUrl)
                .into(view);
    }






    static class MainViewHolder extends RecyclerView.ViewHolder {

        final HeadlineItemBinding binding;

        public MainViewHolder(HeadlineItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
