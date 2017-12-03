package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import uk.me.desiderio.jokedisplay.JokeDisplayActivity;


public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.EndpointTaskListener {
    
    private static final String TAG = MainActivity.class.getSimpleName();
    

    private ProgressBar progressBar;
    private View buttonContainerView;
    private String joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.joke_progress_bar);
        buttonContainerView = findViewById(R.id.joke_request_button_container);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showJokeButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        new EndpointsAsyncTask(this).execute();
        showProgressBar();
        showInterstitialAd();

    }

    @Override
    public void onJokeApiResponse(String joke) {
        this.joke = joke;
        if(!isInterstitialAdShowing()) {
            showJoke();
        }
    }

    public void showJoke() {
            Intent intent = new Intent(this, JokeDisplayActivity.class);
            intent.putExtra(JokeDisplayActivity.EXTRA_JOKE, joke);
            startActivity(intent);

            joke = null;

    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        buttonContainerView.setVisibility(View.GONE);
    }

    private void showJokeButton() {
        progressBar.setVisibility(View.GONE);
        buttonContainerView.setVisibility(View.VISIBLE);
    }

    private void showInterstitialAd() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.jokes_fragment);
        if(fragment instanceof InterstitialAdProvided) {
            ((InterstitialAdProvided) fragment).showInterstitialAd();
        }
    }

    private boolean isInterstitialAdShowing() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.jokes_fragment);
        if(fragment instanceof InterstitialAdProvided) {
             return ((InterstitialAdProvided) fragment).isAdShowing();
        }
        return false;
    }
}
