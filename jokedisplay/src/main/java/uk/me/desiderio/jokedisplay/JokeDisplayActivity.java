package uk.me.desiderio.jokedisplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "joke_display_activity_extra_joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        String joke = getIntent().getStringExtra(EXTRA_JOKE);

        TextView jokeTextView = findViewById(R.id.jokeTextView);

        jokeTextView.setText(joke);

    }
}
