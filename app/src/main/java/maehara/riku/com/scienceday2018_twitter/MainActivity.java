package maehara.riku.com.scienceday2018_twitter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
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

    private static final Boolean IS_TUTORIAL = true;
    private static final String HASH_TAG = " #OneDayScience2018 #rhLab";

    private Button button;
    private EditText editText;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!IS_TUTORIAL && TwitterCore.getInstance().getSessionManager().getActiveSession() == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        // ここに書く



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
                                        showToast("ツイートしました。");
                                    }
                                });
                            }

                            public void failure(TwitterException exception) {
                                handler.post(new Runnable() {
                                    public void run() {
                                        showToast("ツイートに失敗しました");
                                    }
                                });
                            }
                        });
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
