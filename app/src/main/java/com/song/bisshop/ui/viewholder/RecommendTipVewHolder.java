package com.song.bisshop.ui.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.song.bisshop.R;
import com.song.bisshop.config.Config;
import com.song.bisshop.model.bean.RecommendContentModel;
import com.song.bisshop.ui.activity.MainActivity;

/**
 * author：Anumbrella
 * Date：16/5/25 下午9:38
 */
public class RecommendTipVewHolder extends BaseViewHolder<RecommendContentModel> implements View.OnClickListener {

    private TextView tip;

    private Button btn;

    private RecommendContentModel data;


    public RecommendTipVewHolder(ViewGroup parent) {
        super(parent, R.layout.itemview_recommend_tip);
        tip = $(R.id.recommend_tip);
        btn = $(R.id.recommend_btn);
    }


    @Override
    public void setData(RecommendContentModel data) {
        super.setData(data);
        this.data = data;
        tip.setText(data.getTip());
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < Config.recommdendTips.length; i++) {
            if (data.getTip().equals(Config.recommdendTips[i])) {
                MainActivity.staticViewPager.setCurrentItem(i + 1);
                break;
            }
        }
    }
}

