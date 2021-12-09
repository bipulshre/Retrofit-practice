package com.example.retrofitpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.retrofitpractice.api.model.GitHubRepo;
import com.example.retrofitpractice.api.service.GitHubClient;
import com.example.retrofitpractice.api.service.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.pagination_list);

        Retrofit retrofit = RetrofitBuilder.getRetrofit();

       GitHubClient client = retrofit.create(GitHubClient.class);

       Call<List<GitHubRepo>> call =  client.repoForUser("bipulshre");

       call.enqueue(new Callback<List<GitHubRepo>>() {
           @Override
           public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
               List<GitHubRepo> repos = response.body();

               listView.setAdapter(new GitHubRepoAdapter(MainActivity.this,repos));
           }

           @Override
           public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
               Toast.makeText(MainActivity.this,"error occured",Toast.LENGTH_SHORT).show();
           }
       });



    }
}