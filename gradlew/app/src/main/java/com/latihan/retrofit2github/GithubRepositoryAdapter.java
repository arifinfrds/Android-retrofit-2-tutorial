package com.latihan.retrofit2github;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.latihan.retrofit2github.dummy.DummyContent;
import com.latihan.retrofit2github.model.GithubRepository;
import com.latihan.retrofit2github.model.Owner;
import com.latihan.retrofit2github.util.GSONConverter;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by arifinfirdaus on 8/11/17.
 */
public class GithubRepositoryAdapter
        extends RecyclerView.Adapter<GithubRepositoryAdapter.ViewHolder> {

    private List<GithubRepository> mGithubRepositories;
    private Context mContext;


    public GithubRepositoryAdapter(List<GithubRepository> mGitHubRepositories, Context context) {
        this.mGithubRepositories = mGitHubRepositories;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final GithubRepository githubRepository = mGithubRepositories.get(position);
        final Owner owner = githubRepository.getOwner();

        Picasso.with(mContext)
                .load(owner.getAvatarUrl())
                .into(holder.mIvCardThumbnail);

        holder.mTitle.setText(githubRepository.getName());
        holder.mDescription.setText(githubRepository.getDescription());
        holder.mCreatedAt.setText(githubRepository.getCreatedAt());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ItemDetailActivity.class);

                Class githubRepositoryClass = githubRepository.getClass();
                Class ownerClass = owner.getClass();

                intent.putExtra(
                        ItemDetailFragment.ARG_GITHUB_REPOSITORY_ID,
                        githubRepositoryClass.toString()
                );
                intent.putExtra(
                        ItemDetailFragment.ARG_OWNER_ID,
                        owner.toString()
                );
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGithubRepositories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mIvCardThumbnail;
        public final TextView mTitle;
        public final TextView mDescription;
        public final TextView mCreatedAt;
        public DummyContent.DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIvCardThumbnail = (ImageView) view.findViewById(R.id.iv_card_thumbnail);
            mTitle = (TextView) view.findViewById(R.id.tv_card_title);
            mDescription = (TextView) view.findViewById(R.id.tv_card_description);
            mCreatedAt = (TextView) view.findViewById(R.id.tv_card_created_at);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDescription.getText() + "'";
        }
    }
}