package com.android.test.custom;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.android.test.R;
import com.squareup.picasso.Picasso;

import java.util.Timer;
import java.util.TimerTask;

public class AutoSlideLayout extends FrameLayout {
    private final static String TAG = AutoSlideLayout.class.getSimpleName();
    private AutoSlideAdapter adapter;
    private ViewPager autoSlideViewpager;
    private int position;
    public AutoSlideLayout(Context context) {
        super(context);
        init(context);
    }

    public AutoSlideLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AutoSlideLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context pContext) {
        View view = LayoutInflater.from(pContext).inflate(R.layout.auto_slide_layout, null, false);
        autoSlideViewpager = (ViewPager)view.findViewById(R.id.auto_slide_viewpager);
        adapter = new AutoSlideAdapter(pContext);
        autoSlideViewpager.setAdapter(adapter);
        autoSlideStart();
        addView(view);
    }
    private void autoSlideStart(){
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                position = autoSlideViewpager.getCurrentItem();
                if(position == 4){
                    position = 0;
                }else{
                    position++;
                }
                autoSlideViewpager.setCurrentItem(position,true);
            }
        };
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        },5000,5000);
    }


    class AutoSlideAdapter extends PagerAdapter {
        private LayoutInflater mLayoutInflater;
        private Context mContext;
        private ImageView autoSlideImage;
        public AutoSlideAdapter(Context pContext) {
            mContext = pContext;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.auto_slide_item,null);
            autoSlideImage = (ImageView)itemView.findViewById(R.id.auto_slide_image);
            Picasso.with(mContext).load("https://www.google.co.kr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png").fit().into(autoSlideImage);
            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }
}
