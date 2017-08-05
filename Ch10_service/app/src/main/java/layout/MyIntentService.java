package layout;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyIntentService extends IntentService {
    public MyIntentService() {
        super("MyIntentService");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i("myIntentService:", "currentThread:" + Thread.currentThread());
    }

    @Override
    public void onDestroy() {
        Log.i("myIntentService:", "destroy");
        super.onDestroy();
    }
}
