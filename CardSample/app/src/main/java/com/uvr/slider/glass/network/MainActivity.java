/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uvr.slider.glass.network;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.request.RequestOptions;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.animations.DescriptionAnimation;
import com.glide.slider.library.slidertypes.BaseSliderView;
import com.glide.slider.library.slidertypes.TextSliderView;
import com.glide.slider.library.tricks.ViewPagerEx;
import com.uvr.slider.glass.network.fragments.BaseFragment;
import com.uvr.slider.glass.network.fragments.ColumnLayoutFragment;
import com.uvr.slider.glass.network.fragments.MainLayoutFragment;
import com.uvr.glass.ui.GlassGestureDetector.Gesture;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Main activity of the application. It provides viewPager to move between fragments.
 */
public class MainActivity extends BaseActivity implements  BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener {

    private List<BaseFragment> fragments = new ArrayList<>();
 //   private ViewPager viewPager;
    private SliderLayout mSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_slide);
        mSlider = findViewById(R.id.slider);

//        ArrayList<String> listUrl = new ArrayList<>();
//        ArrayList<String> listName = new ArrayList<>();
//
//        listUrl.add("https://www.revive-adserver.com/media/GitHub.jpg");
//        listName.add("JPG - Github");
//
//        listUrl.add("https://tctechcrunch2011.files.wordpress.com/2017/02/android-studio-logo.png");
//        listName.add("PNG - Android Studio");
//
//        listUrl.add("http://static.tumblr.com/7650edd3fb8f7f2287d79a67b5fec211/3mg2skq/3bdn278j2/tumblr_static_idk_what.gif");
//        listName.add("GIF - Disney");
//
//        listUrl.add("http://www.gstatic.com/webp/gallery/1.webp");
//        listName.add("WEBP - Mountain");

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("bb",R.drawable.pulse01);
        file_maps.put("ba",R.drawable.pulse02);
        file_maps.put("bc",R.drawable.pulse03);
        file_maps.put("bd",R.raw.heart);
        file_maps.put("be",R.drawable.pulse04);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();
        //.diskCacheStrategy(DiskCacheStrategy.NONE)
        //.placeholder(R.drawable.placeholder)
        //.error(R.drawable.placeholder);

        for(String name : file_maps.keySet()){
            TextSliderView sliderView = new TextSliderView(this);
            // if you want show image only / without description text use DefaultSliderView instead

            // initialize SliderLayout
            sliderView
                    .image(file_maps.get(name))
                    //.description(name)
                    .setRequestOption(requestOptions)
                    .setProgressBarVisible(true)
                    .setOnSliderClickListener(this);

            //add your extra information
            sliderView.bundle(new Bundle());
            sliderView.getBundle().putString("extra", name);
            mSlider.addSlider(sliderView);
        }

        // set Slider Transition Animation
        mSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        // mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);

        mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSlider.setCustomAnimation(new DescriptionAnimation());
        mSlider.setDuration(40000);
        mSlider.addOnPageChangeListener(this);
        mSlider.stopCyclingWhenTouch(false);

//        final ScreenSlidePagerAdapter screenSlidePagerAdapter = new ScreenSlidePagerAdapter(
//            getSupportFragmentManager());
//        viewPager = findViewById(R.id.viewPager);
//        viewPager.setAdapter(screenSlidePagerAdapter);
//
//        fragments.add(MainLayoutFragment
//            .newInstance(getString(R.string.text_sample), getString(R.string.footnote_sample),
//                getString(R.string.timestamp_sample), null));
//        fragments.add(MainLayoutFragment
//            .newInstance(getString(R.string.different_options), getString(R.string.empty_string),
//                getString(R.string.empty_string), R.menu.main_menu));
//        fragments.add(ColumnLayoutFragment
//            .newInstance(R.drawable.ic_style, getString(R.string.columns_sample),
//                getString(R.string.footnote_sample), getString(R.string.timestamp_sample)));
//        fragments.add(MainLayoutFragment
//            .newInstance(getString(R.string.like_this_sample), getString(R.string.empty_string),
//                getString(R.string.empty_string), null));
//
//        screenSlidePagerAdapter.notifyDataSetChanged();
//
//        final TabLayout tabLayout = findViewById(R.id.page_indicator);
//        tabLayout.setupWithViewPager(viewPager, true);
    }

//    @Override
//    public boolean onGesture(Gesture gesture) {
//        switch (gesture) {
//            case TAP:
//              //  fragments.get(viewPager.getCurrentItem()).onSingleTapUp();
//              //  return true;
//            default:
//              //  return super.onGesture(gesture);
//        }
//    }

    @Override
    protected void onStop() {
        mSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this, slider.getBundle().getString("extra") + "", Toast.LENGTH_SHORT).show();
        Log.i("ylee", "Current Position " + mSlider.getCurrentPosition());

        mSlider.setCurrentPosition(1,false);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

//    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
//
//        ScreenSlidePagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return fragments.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return fragments.size();
//        }
//    }
}
