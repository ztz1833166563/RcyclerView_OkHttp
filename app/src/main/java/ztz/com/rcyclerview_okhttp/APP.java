package ztz.com.rcyclerview_okhttp;

import android.app.Application;

import ztz.com.rcyclerview_okhttp.util.ImageLoaderUtil;

/**
 * Created by TR on 2017/11/11.
 */

public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderUtil.initConfig(this);
    }
}
