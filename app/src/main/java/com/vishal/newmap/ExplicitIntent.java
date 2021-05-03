package com.vishal.newmap;

import android.content.Context;
import android.content.Intent;

public class ExplicitIntent extends Intent {


    public ExplicitIntent(Context context, int actionId) {
        super(context.getString(actionId));
        setPackage(context.getPackageName());
    }

    public ExplicitIntent(Context context, String actionString) {
        super(actionString);
        setPackage(context.getPackageName());
    }

}
