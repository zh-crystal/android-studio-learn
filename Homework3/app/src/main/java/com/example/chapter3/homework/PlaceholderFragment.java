package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

public class PlaceholderFragment extends Fragment {

    private ArrayAdapter<Item> adapterItems;
    private LottieAnimationView lottieAnimationView;
    private ListView listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Item> items = Item.getItems();
        adapterItems = new ArrayAdapter<>(getActivity(),
                R.layout.list_item, items);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        View view = inflater.inflate(R.layout.fragment_placeholder, container, false);
        lottieAnimationView = (LottieAnimationView)view.findViewById(R.id.animation_view);
        listView = (ListView)view.findViewById(R.id.list_view);
        listView.setAdapter(adapterItems);
        listView.setAlpha(0.0f);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                AlphaAnimation mHideAnimation1 = new AlphaAnimation(1.0f, 0.0f);
                mHideAnimation1.setDuration(1000);
                mHideAnimation1.setFillAfter(true);
                lottieAnimationView.startAnimation(mHideAnimation1);
                AlphaAnimation mHideAnimation2 = new AlphaAnimation(0.0f, 1.0f);
                mHideAnimation2.setDuration(1000);
                mHideAnimation2.setFillAfter(true);
                listView.startAnimation(mHideAnimation2);
                listView.setAlpha(1.0f);
            }
        }, 5000);
    }
}
