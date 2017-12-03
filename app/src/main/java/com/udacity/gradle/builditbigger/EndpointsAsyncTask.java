package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.support.annotation.StringDef;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import uk.me.desiderio.builditbigger.backend.jokes.Jokes;


/**
 * AsyncTask to retrieve jokes from the GC endpoint server
 */

class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

    private static final String TAG = EndpointTaskListener.class.getSimpleName();

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({SERVER_TYPE_TEST, SERVER_TYPE_LIVE})
    @interface ServerType{};
    private static final String SERVER_TYPE_LIVE = "live_server";
    private static final String SERVER_TYPE_TEST = "test_server";

    private static Jokes myApiService = null;
    private EndpointTaskListener listener;

    public interface EndpointTaskListener {
        void onJokeApiResponse(String joke);
    }

    public EndpointsAsyncTask(EndpointTaskListener listener) {
        this.listener = listener;
    }

    private String getRootUrlStringByType(@ServerType String serverType) {
        String urlString = null;

        switch (serverType) {
            case SERVER_TYPE_LIVE:
                urlString = "https://desjokesapi.appspot.com/_ah/api/";
                break;
            case SERVER_TYPE_TEST:
                /*
                    options for running against local devappserver
                    10.0.2.2 is localhost's IP address in Android emulator
                    turn off compression when running against local devappserver
                 */
                urlString = "http://10.0.2.2:8080/_ah/api/";
                break;
        }
        return urlString;
    }

    @Override
    protected String doInBackground(Void... voids) {
        Log.d(TAG, "doInBackground: entering ");
        if(myApiService == null) {  // Only do this once
            String rootUrl = getRootUrlStringByType(SERVER_TYPE_LIVE);

            Jokes.Builder builder = new Jokes.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setApplicationName("desJokesApi")
                    .setRootUrl(rootUrl)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                @Override
                public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                    abstractGoogleClientRequest.setDisableGZipContent(true);
                }
            });
            myApiService = builder.build();
        }

        try {
            return myApiService.tellJoke().execute().getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d(TAG, "onPostExecute: " + result);
        listener.onJokeApiResponse(result);
    }
}
