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

    private static Jokes myApiService = null;
    private EndpointTaskListener listener;

    public interface EndpointTaskListener {
        void onJokeApiResponse(String joke);
    }

    public EndpointsAsyncTask(EndpointTaskListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        Log.d(TAG, "doInBackground: entering ");
        if(myApiService == null) {

            Jokes.Builder builder = new Jokes.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setApplicationName("desJokesApi")
                    .setRootUrl(BuildConfig.ENDPOINT_SERVER_URL_STRING)
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
            Log.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d(TAG, "onPostExecute: " + result);
        listener.onJokeApiResponse(result);
    }
}
