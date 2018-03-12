package uk.me.desiderio.jokedisplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

/** displays the joke string that receives as an extra of its intent.
 * The extra has to named using the value JokeDisplayActivity.EXTRA_JOKE
 */
public class JokeDisplayActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "joke_display_activity_extra_joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        String joke = getIntent().getStringExtra(EXTRA_JOKE);

        TextView jokeTextView = findViewById(R.id.jokeTextView);

        if (joke != null && !joke.isEmpty()) {
            jokeTextView.setText(joke);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
