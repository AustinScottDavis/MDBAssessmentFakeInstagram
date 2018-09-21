package com.example.davis.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    public static ArrayList<Post> allPosts = new ArrayList<>();
    private RecyclerView postRecyclerView;
    private RecyclerView.Adapter postAdapter;
    private RecyclerView.LayoutManager postLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //allPosts = new ArrayList<>();

        postRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        postRecyclerView.setHasFixedSize(true);
        postLayoutManager = new LinearLayoutManager(this);
        postRecyclerView.setLayoutManager(postLayoutManager);
        postAdapter = new PostAdapter(getApplicationContext(), allPosts);
        postRecyclerView.setAdapter(postAdapter);

        select();
    }

    public void select() {
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListActivity.this, SelectActivity.class);
                ListActivity.this.startActivityForResult(i, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            postAdapter.notifyDataSetChanged();
            postRecyclerView.setLayoutManager(postLayoutManager);
            postAdapter = new PostAdapter(getApplicationContext(), allPosts);
            postRecyclerView.setAdapter(postAdapter);

            select();
        }
    }
}
