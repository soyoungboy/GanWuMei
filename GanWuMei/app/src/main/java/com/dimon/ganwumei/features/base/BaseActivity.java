package com.dimon.ganwumei.features.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dimon.ganwumei.MyApplication;
import com.dimon.ganwumei.internal.components.ActivityComponent;
import com.dimon.ganwumei.internal.components.AppComponent;
import com.dimon.ganwumei.internal.components.DaggerActivityComponent;
import com.dimon.ganwumei.internal.components.DaggerAppComponent;
import com.dimon.ganwumei.internal.modules.ActivityModule;
import com.dimon.ganwumei.internal.modules.AppModule;


/**
 * Created by Dimon on 2016/3/3.
 */

public class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BaseActivity",getClass().getSimpleName());
        getActivityComponent().inject(this);


    }

    protected ActivityComponent getActivityComponent(){
        return  DaggerActivityComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

    public AppComponent getAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule((MyApplication)getApplicationContext()))
                .build();
    }
}
