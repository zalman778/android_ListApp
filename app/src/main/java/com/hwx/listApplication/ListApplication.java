package com.hwx.listApplication;

import android.app.Application;
import android.content.Context;


public class ListApplication extends Application {

    // Resource Provider
    private ResourceProvider mResourceProvider;

    public ResourceProvider getResourceProvider() {
        if (mResourceProvider == null)
            mResourceProvider = new ResourceProvider(this);

        return mResourceProvider;
    }

    public static ListApplication get(Context context) {
        return (ListApplication) context.getApplicationContext();
    }

}
