package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mylibrary.JokeActivity;

/**
 * Created by Moetaz on 12/12/2017.
 */

public class MainActivityFragment extends Fragment implements EndpointAsyncTask.Callback{

    public String jokeContent = null;
    public boolean inTestMode = false;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button button = (Button) root.findViewById(R.id.joke_btn);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getJoke();
            }
        });

      return root;
    }
    public void getJoke(){
        new EndpointAsyncTask(this).execute();
    }


    @Override
    public void LoadJoke(String s) {
        jokeContent = s;

        if (!inTestMode) {
            Context context = getActivity();
            Intent intent = new Intent(context, JokeActivity.class);
            intent.putExtra("joke_pass", s);
            context.startActivity(intent);
        }


    }
}
