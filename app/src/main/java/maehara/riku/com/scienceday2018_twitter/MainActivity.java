package maehara.riku.com.scienceday2018_twitter;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

public class MainActivity extends AppCompatActivity {

    private static final String HASH_TAG = " #OneDayScience2018 #rhLab";

    private Button button;
    private EditText editText;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        handler = new Handler();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                doTweet(text);
            }
        });
    }

    private void doTweet(String text) {
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        StatusesService statusesService = twitterApiClient.getStatusesService();
        statusesService.update(text + HASH_TAG, null, false, null, null, null, false, null, null)
                .enqueue(
                        new Callback<Tweet>() {
                            @Override
                            public void success(Result<Tweet> result) {
                                handler.post(new Runnable() {
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "ツイートしました。", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            public void failure(TwitterException exception) {
                                handler.post(new Runnable() {
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "ツイートに失敗しました。", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
    }
}
