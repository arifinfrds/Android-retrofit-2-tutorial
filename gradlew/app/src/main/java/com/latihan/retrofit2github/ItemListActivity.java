package com.latihan.retrofit2github;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Toast;


import com.latihan.retrofit2github.Service.GitHubService;
import com.latihan.retrofit2github.dummy.DummyContent;
import com.latihan.retrofit2github.model.GithubRepository;
import com.latihan.retrofit2github.util.ItemOffsetDecoration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ItemListActivity extends AppCompatActivity implements View.OnClickListener {


    private boolean mTwoPane;
    private FloatingActionButton mfFab;
    private List<GithubRepository> mGithubRepositories;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        mfFab = (FloatingActionButton) findViewById(R.id.fab);
        mfFab.setOnClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.item_list);
        mGithubRepositories = new ArrayList<>();

        fetchData();

        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
        }


    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.fab) {
            Toast.makeText(this, "fab clicked", Toast.LENGTH_SHORT).show();
        }

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        Toast.makeText(this, "mGithubRepositories.size : " + mGithubRepositories.size(), Toast.LENGTH_SHORT).show();
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this, R.dimen.item_offset);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(new GithubRepositoryAdapter(mGithubRepositories, this));

    }


    private void fetchData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<GithubRepository>> result = service.listRepos("arifinfrds");

        result.enqueue(new Callback<List<GithubRepository>>() {
            @Override
            public void onResponse(Call<List<GithubRepository>> call, Response<List<GithubRepository>> response) {
                mGithubRepositories = response.body();
                setupRecyclerView(mRecyclerView);
            }

            @Override
            public void onFailure(Call<List<GithubRepository>> call, Throwable t) {

            }
        });

    }


}
