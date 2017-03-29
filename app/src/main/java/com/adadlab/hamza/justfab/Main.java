package com.adadlab.hamza.justfab;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.adadlab.hamza.justfab.adapters.SliderAdapter;
import com.adadlab.hamza.justfab.models.Page;
import com.adadlab.hamza.justfab.models.Pages;
import com.adadlab.hamza.justfab.models.Section;
import com.squareup.picasso.Picasso;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App
                .getApiInterface()
                .getPages().
                subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Pages>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(getApplication().getApplicationContext(), "complited", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("HAMZA", e.getMessage());
                    }

                    @Override
                    public void onNext(Pages pages) {
                        for(Page page : pages.getPage()){
                            if(page.getName().equalsIgnoreCase("index")){
                                for(Section section : page.getSections()){
                                    if(section.getType().equalsIgnoreCase("slider")){
                                        makeSlider(section);
                                    }
                                    if(section.getType().equalsIgnoreCase("image")){
                                        appendToScroll(section);
                                    }

                                }

                            }


                        }
                        //Toast.makeText(getApplication().getApplicationContext(), "complited :" + pages.getPage().size(), Toast.LENGTH_LONG).show();
                    }
                });

    }

    public void makeSlider(Section section){
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPageAndroid);
        mViewPager.setVisibility(View.VISIBLE);
        SliderAdapter adapterView = new SliderAdapter(this, section.getData().getImages());
        mViewPager.setAdapter(adapterView);
        Toast.makeText(getApplication().getApplicationContext(), "complited :" + section.getData().getImages().size(), Toast.LENGTH_LONG).show();
    }

    public void appendToScroll(Section section){
        LinearLayout scrollview = (LinearLayout) findViewById(R.id.scollMain);
        ImageView mImageView = new ImageView(this);
        Picasso.with(this)
                .load(section.getData().getImages().get(0))
                .error()
                .fit()
                .into(mImageView);
        scrollview.addView(mImageView);
    }
}
