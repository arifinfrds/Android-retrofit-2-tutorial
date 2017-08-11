package com.latihan.retrofit2github.Service;

import com.latihan.retrofit2github.model.GithubRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by arifinfirdaus on 8/11/17.
 */

public interface GitHubService {

    @GET("users/{user}/repos")
    Call<List<GithubRepository>> listRepos(@Path("user") String user);

}
