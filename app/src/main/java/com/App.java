package com;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.app.annotation.aspect.TimeLog;
import com.base.util.SpUtil;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by baixiaokang on 16/4/23.
 */
public class App extends Application {
    private static App mApp;
    public Stack<Activity> store;
    public HashMap<String, Object> mCurActivityExtra;

    @Override
    @TimeLog
    public void onCreate() {
        super.onCreate();
        mApp = this;
        SpUtil.init(this);
        store = new Stack<>();
        registerActivityLifecycleCallbacks(new SwitchBackgroundCallbacks());
    }

    public static App getAppContext() {
        return mApp;
    }


    private class SwitchBackgroundCallbacks implements Application.ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            store.add(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            store.remove(activity);
        }
    }

    /**
     * 获取当前的Activity
     *
     * @return
     */
    public Activity getCurActivity() {
        return store.lastElement();
    }
}
