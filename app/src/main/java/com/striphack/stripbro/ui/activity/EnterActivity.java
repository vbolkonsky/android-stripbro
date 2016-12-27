package com.striphack.stripbro.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.striphack.stripbro.R;
import com.striphack.stripbro.application.StripbroApplication;
import com.striphack.stripbro.mvp.presenter.EnterPresenter;
import com.striphack.stripbro.mvp.router.EnterRouter;
import com.striphack.stripbro.mvp.view.EnterView;
import com.striphack.stripbro.ui.activity.base.ActivityNavigator;
import com.striphack.stripbro.ui.activity.base.BaseActivity;
import com.striphack.stripbro.ui.widget.WidgetTextUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Valentin S. Bolkonsky on 10.12.2016.
 */

public class EnterActivity extends BaseActivity implements EnterRouter, EnterView{

    @BindView(R.id.et_promo)
    protected EditText textCode;
    @BindView(R.id.btn_scan)
    protected Button btnApply;

    @CodeRegime
    private int codeRegime = SCAN;

    @Inject
    protected EnterPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        ButterKnife.bind(this);
        StripbroApplication.getApplication(this).getComponent().inject(this);
        WidgetTextUtils.validationTextView(textCode, null, 1)
                .subscribe(aBoolean -> {
                   btnApply.setText(getString(aBoolean ? R.string.button_apply : R.string.button_scan_card));
                    codeRegime = aBoolean ? MANUAL : SCAN;
                });
        presenter.attachRouter(this);
    }

    @OnClick(R.id.btn_scan)
    public void onScan(){
        switch (codeRegime) {
            case SCAN:
                new IntentIntegrator(this).initiateScan();
                break;
            case MANUAL:
            default:
                presenter.onEnterPromoCode(String.valueOf(textCode.getText()).trim());
                break;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachRouter();
    }

    @Override
    public void onGoToMain() {
        ActivityNavigator.startGenericActivity(this, MainActivity.class, null, true, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(null != result.getContents()) {
                textCode.setText(result.getContents());
                //presenter.onEnterPromoCode(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    @IntDef(value = {SCAN, MANUAL})
    public @interface CodeRegime{ }

    public static final int SCAN = 0;
    public static final int MANUAL = 1;
}
