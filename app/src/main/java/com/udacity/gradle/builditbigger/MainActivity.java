package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import uk.me.desiderio.javajokes.Joker;
import uk.me.desiderio.jokedisplay.JokeDisplayActivity;


public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.EndpointTaskListener {
    private ProgressBar progressBar;
    private View buttonContainerView;

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
    }


    @Override
    public void onJokeApiResponse(String joke) {
        Intent intent = new Intent(this, JokeDisplayActivity.class);
        intent.putExtra(JokeDisplayActivity.EXTRA_JOKE, joke);
        startActivity(intent);

    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        buttonContainerView.setVisibility(View.GONE);
    }


    private void showJokeButton() {
        progressBar.setVisibility(View.GONE);
        buttonContainerView.setVisibility(View.VISIBLE);
    }
}
