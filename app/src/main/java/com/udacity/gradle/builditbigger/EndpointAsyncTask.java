package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

import com.example.moetaz.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by Moetaz on 12/4/2017.
 */

public class EndpointAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    private Callback mCallback;

    public EndpointAsyncTask(Callback mCallback) {
        this.mCallback = mCallback;
    }

    @SafeVarargs
    @Override
    protected final String doInBackground(Pair<Context, String>... params) {


        if(myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://jokebackend-188912.appspot.com/_ah/api/");

            myApiService = builder.build();
        }


        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mCallback.LoadJoke(result);

    }
    interface Callback{
        void LoadJoke(String s);
    }
}