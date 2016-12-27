package com.striphack.stripbro.ui.activity;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.striphack.stripbro.R;
import com.striphack.stripbro.application.StripbroApplication;
import com.striphack.stripbro.mvp.presenter.MainPresenter;
import com.striphack.stripbro.mvp.router.MainRouter;
import com.striphack.stripbro.mvp.view.MainView;
import com.striphack.stripbro.repository.bro.BarneyReaction;
import com.striphack.stripbro.ui.activity.base.ActivityNavigator;
import com.striphack.stripbro.ui.activity.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Valentin S. Bolkonsky on 10.12.2016.
 */
@Slf4j
public class MainActivity extends BaseActivity implements MainView, MainRouter, View.OnClickListener {

    public static final String KEY_NEED_PUSH = "KEY_NEED_PUSH";

    @Inject
    protected MainPresenter presenter;

    @BindView(R.id.text_reaction)
    protected TextView textReaction;
    @BindView(R.id.tools_reaction)
    protected ViewGroup toolsReaction;
    @BindView(R.id.controls_container_horizontal)
    protected LinearLayout controlsContainerHorizontal;
    @BindView(R.id.controls_container_vertical)
    protected LinearLayout controlsContainerVertical;
    @BindView(R.id.icBarney)
    protected ImageView icBarney;

    private boolean isShowNotification;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        StripbroApplication.getApplication(this).getComponent().inject(this);
        presenter.attachRouter(this);
        final Bundle args = getIntent().getBundleExtra(ActivityNavigator.ARGS_BUNDLE);
        if(args != null) {
            isShowNotification = args.getBoolean(KEY_NEED_PUSH, false);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);
        presenter.onCreate(isShowNotification);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView();
        controlsContainerHorizontal.removeAllViews();
        controlsContainerVertical.removeAllViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //if(isShowNotification) {
        //    presenter.onShowNotification();
        //}
        presenter.detachRouter();
    }

    @Override
    public void showFirstBarneyReaction(BarneyReaction reaction) {
        log.debug("first barney reaction {}", reaction);
        fillReaction(reaction);
    }

    @Override
    public void showBarneyReaction(BarneyReaction reaction) {
        log.debug("barney reaction {}", reaction);
        fillReaction(reaction);
    }

    @Override
    public void afterUserAnswer(String message, int gravity) {
        textReaction.setGravity(Gravity.CENTER);
        textReaction.setText(message);
        controlsContainerHorizontal.setVisibility(View.GONE);
        controlsContainerVertical.setVisibility(View.GONE);

    }

    @Override
    public void changeBarnyImage(@DrawableRes int id) {
        icBarney.setImageResource(id);
    }

    @Override
    public void onClick(View view) {
        presenter.userAnswer(((BarneyReaction.Variant) view.getTag()).getVariantId());
    }

    @Override
    public void onGoToLogo() {
        ActivityNavigator.startGenericActivity(this, LogoActivity.class, null, true, 0);
    }

    private void fillReaction(BarneyReaction reaction) {
        afterUserAnswer(reaction.getBarneySpeech(),Gravity.FILL_HORIZONTAL );
        switch (reaction.getType()) {
            case BarneyReaction.HARD:
                for (BarneyReaction.Variant variant : reaction.getVariants()) {
                    final Button actionBtn;
                    if (BarneyReaction.CANCEL.equals(variant.getVariantId())) {
                        actionBtn = (Button) LayoutInflater.from(this).inflate(R.layout.btn_packet_cancel, null);
                    } else {
                        actionBtn = (Button) LayoutInflater.from(this).inflate(R.layout.btn_packet, null);
                    }
                    actionBtn.setTag(variant);
                    actionBtn.setOnClickListener(this);
                    actionBtn.setText(variant.getVariantText());
                    controlsContainerVertical.addView(actionBtn);
                }
                controlsContainerVertical.setVisibility(View.VISIBLE);
                break;
            case BarneyReaction.SIMPLE:
            default:
                for (BarneyReaction.Variant variant : reaction.getVariants()) {
                    final Button actionBtn;
                    if (BarneyReaction.CANCEL.equals(variant.getVariantId())) {
                        actionBtn = (Button) LayoutInflater.from(this).inflate(R.layout.btn_cancel, null);
                    } else {
                        actionBtn = (Button) LayoutInflater.from(this).inflate(R.layout.btn_apply, null);
                    }
                    actionBtn.setTag(variant);
                    actionBtn.setOnClickListener(this);
                    actionBtn.setText(variant.getVariantText());
                    controlsContainerHorizontal.addView(actionBtn);
                }
                controlsContainerHorizontal.setVisibility(View.VISIBLE);
                break;

        }
    }
}
