package com.example.jetmentor;

import android.view.View;
import android.widget.ProgressBar;

import androidx.test.espresso.IdlingResource;

/**
 * Monitor Activity idle status by watching ProgressBar.
 */
public class BaseActivityIdlingResource implements IdlingResource {

    private BaseActivity mActivity;
    private ResourceCallback mCallback;

//    public BaseActivityIdlingResource(AnonymousAuthActivity activity) {
//        mActivity = activity;
//    }

    public BaseActivityIdlingResource(loginActivity activity) {
        mActivity = activity;
    }

    @Override
    public String getName() {
        return "BaseActivityIdlingResource:" + mActivity.getLocalClassName();
    }

    @Override
    public boolean isIdleNow() {
        ProgressBar progressBar = mActivity.mProgressBar;
        boolean idle = (progressBar == null || progressBar.getVisibility() == View.INVISIBLE);

        if (mCallback != null && idle) {
            mCallback.onTransitionToIdle();
        }

        return idle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mCallback = callback;
    }
}
