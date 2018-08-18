package maehara.riku.com.scienceday2018_twitter;

import android.app.Application;
import android.util.Log;

import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TwitterConfig twitterConfig = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(BuildConfig.CONSUMER_KEY, BuildConfig.CONSUMER_SECRET))
                .debug(true)
                .build();
        Twitter.initialize(twitterConfig);
    }
}
