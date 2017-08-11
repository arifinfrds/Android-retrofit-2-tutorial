package com.latihan.retrofit2github;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.latihan.retrofit2github.dummy.DummyContent;
import com.latihan.retrofit2github.model.GithubRepository;

import java.util.List;

/**
 * Created by arifinfirdaus on 8/11/17.
 */
public class GithubRepositoryAdapter
        extends RecyclerView.Adapter<GithubRepositoryAdapter.ViewHolder> {

    private List<GithubRepository> mGithubRepositories;


    public GithubRepositoryAdapter(List<GithubRepository> mGitHubRepositories) {
        this.mGithubRepositories = mGitHubRepositories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.mIdView.setText(mGithubRepositories.get(position).getName());
        holder.mContentView.setText(mGithubRepositories.get(position).getFullName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Context context = v.getContext();
//                Intent intent = new Intent(context, ItemDetailActivity.class);
//                intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, holder.mItem.id);
//                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGithubRepositories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyContent.DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}