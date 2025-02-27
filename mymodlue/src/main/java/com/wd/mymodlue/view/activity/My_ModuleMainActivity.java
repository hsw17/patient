package com.wd.mymodlue.view.activity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bwie.mvplibrary.base.BaseActivity;
import com.bwie.mvplibrary.utils.CustomClickListener;
import com.bwie.mvplibrary.utils.SPUtils;
import com.bwie.mvplibrary.utils.ToastUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.mymodlue.R;

import com.wd.mymodlue.R2;
import com.wd.mymodlue.modle.bean.UserBean;
import com.wd.mymodlue.persenter.Persenter;
import com.wd.mymodlue.view.contract.IViewContract;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
@Route(path = "/mymodlue/activity")
public class My_ModuleMainActivity extends BaseActivity<Persenter> implements IViewContract.IView {


    @BindView(R2.id.head_details_back)
    ImageView headDetailsBack;
    @BindView(R2.id.my_image_simple)
    SimpleDraweeView myImageSimple;
    @BindView(R2.id.my_text_login)
    TextView myTextLogin;
    @BindView(R2.id.my_button_sing_in)
    Button myButtonSingIn;
    @BindView(R2.id.linear_lay)
    LinearLayout linearLay;
    @BindView(R2.id.my_button_inquiry)
    RelativeLayout myButtonInquiry;
    @BindView(R2.id.my_button_history)
    RelativeLayout myButtonHistory;
    @BindView(R2.id.re_latiview)
    RelativeLayout reLatiview;
    @BindView(R2.id.my_button_record)
    LinearLayout myButtonRecord;
    @BindView(R2.id.my_button_wallet)
    LinearLayout myButtonWallet;
    @BindView(R2.id.my_button_collect)
    LinearLayout myButtonCollect;
    @BindView(R2.id.my_button_suggest)
    LinearLayout myButtonSuggest;
    @BindView(R2.id.my_button_video)
    LinearLayout myButtonVideo;
    @BindView(R2.id.my_button_patients_circle)
    LinearLayout myButtonPatientsCircle;
    @BindView(R2.id.my_button_attention)
    LinearLayout myButtonAttention;
    @BindView(R2.id.my_button_task)
    LinearLayout myButtonTask;
    @BindView(R2.id.my_button_set)
    LinearLayout myButtonSet;
    @BindView(R2.id.linear_my)
    LinearLayout linearMy;
    @BindView(R2.id.my_text_title)
    TextView myTextTitle;

    private Map<String, Object> map;

    @Override
    protected int bindLayout() {
        return R.layout.activity_mymodlue_main;
    }

    @Override
    protected Persenter setPresenter() {
        return new Persenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        ToastUtils.init(this);
//        获取存储内容
        SPUtils login = new SPUtils(this, "login");
        int id = (int) login.getSharedPreference("id", 0);
        String sessionId = (String) login.getSharedPreference("sessionId", "");
        String nickName = (String) login.getSharedPreference("nickName", "");
        String headPic = (String) login.getSharedPreference("headPic", "");

//        设置头像名字
        if (id!=0){
            myImageSimple.setImageURI(headPic);
            myTextLogin.setText(nickName);
        }
        myTextLogin.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                ARouter.getInstance().build("/mymainmodule/activity").navigation();
            }

            @Override
            protected void onFastClick() {

            }
        });
        map = new HashMap<>();
        map.put("userId",id);
        map.put("sessionId",sessionId);
//       底部文字
        myTextTitle.setText("@八维移动通讯学院毕业作品");
//        返回
        headDetailsBack.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                finish();
            }

            @Override
            protected void onFastClick() {

            }
        });
//        签到
        myButtonSingIn.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                presenter.doAddSign(map);
            }

            @Override
            protected void onFastClick() {

            }
        });
//        我的档案
        myButtonRecord.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                Intent intent=new Intent("com.hl.RecordActivity");
                startActivity(intent);
            }

            @Override
            protected void onFastClick() {

            }
        });
//        我的关注
        myButtonAttention.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                Intent intent=new Intent("com.hl.AttentionActivity");
                startActivity(intent);
            }

            @Override
            protected void onFastClick() {

            }
        });
//        购买过的视频
        myButtonVideo.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                Intent intent=new Intent("com.hl.VideoActivity");
                startActivity(intent);
            }

            @Override
            protected void onFastClick() {

            }
        });
//   我的任务
        myButtonTask.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                Intent intent=new Intent("com.hl.TaskActivity");
                startActivity(intent);
            }

            @Override
            protected void onFastClick() {

            }
        });
//        我的钱包
        myButtonWallet.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                Intent intent=new Intent("com.hl.WalletActivity");
                startActivity(intent);
            }

            @Override
            protected void onFastClick() {

            }
        });
//   被采纳的建议
        myButtonSuggest.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                Intent intent=new Intent("com.hl.SuggestActivity");
                startActivity(intent);
            }

            @Override
            protected void onFastClick() {

            }
        });
//      设置
        myButtonSet.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                Intent intent=new Intent("com.hl.SettingActivity");
                startActivity(intent);
            }

            @Override
            protected void onFastClick() {

            }
        });
//        当前问诊
        myButtonInquiry.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                Intent intent=new Intent("com.hl.InquiryActivity");
                startActivity(intent);
            }

            @Override
            protected void onFastClick() {

            }
        });
//        我的收藏
        myButtonCollect.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                Intent intent=new Intent("com.hl.CollectActivity");
                startActivity(intent);
            }

            @Override
            protected void onFastClick() {

            }
        });
//        我的视频
        myButtonVideo.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                Intent intent=new Intent("com.hl.VideoActivity");
                startActivity(intent);
            }

            @Override
            protected void onFastClick() {

            }
        });
//        我的病友圈
        myButtonPatientsCircle.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                Intent intent=new Intent("com.hl.PatientsCircleActivity");
                startActivity(intent);
            }

            @Override
            protected void onFastClick() {

            }
        });
        //        历史问诊
        myButtonHistory.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                Intent intent=new Intent("com.hl.HistoryActivity");
                startActivity(intent);
            }

            @Override
            protected void onFastClick() {

            }
        });
    }
    @Override
    public void onSuccess(Object obj) {
        UserBean userBean= (UserBean) obj;
        if ("0000".equals(userBean.status)) {
            ToastUtils.show(userBean.message);
        }else {
            ToastUtils.show(userBean.message);
        }
    }

    @Override
    public void onSuccessOne(Object one) {

    }

    @Override
    public void onSuccessTwo(Object two) {

    }

    @Override
    public void onSuccessThree(Object three) {

    }

    @Override
    public void onSuccessFour(Object four) {

    }

    @Override
    public void onFail(String str) {

    }

}
