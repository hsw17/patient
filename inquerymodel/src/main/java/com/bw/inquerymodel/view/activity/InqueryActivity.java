package com.bw.inquerymodel.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.inquerymodel.R;
import com.bw.inquerymodel.bean.DoctroinfoBean;
import com.bw.inquerymodel.contract.DoctroInfoContract;
import com.bw.inquerymodel.presenter.DoctroInfoPresenter;
import com.bw.inquerymodel.view.adapter.DoctroEvalutaeAdapter;
import com.bwie.mvplibrary.base.BaseActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import cn.jpush.im.android.api.JMessageClient;

public class InqueryActivity extends BaseActivity<DoctroInfoPresenter> implements DoctroInfoContract.IMainView {

    private String TAG = "InqueryActivity";
    private ImageView backimage;
    private SimpleDraweeView doctro_image;
    private TextView docnames;
    private TextView zhiweis;
    private ImageView guanzhu;
    private TextView addresse;
    private RelativeLayout relat1;
    private TextView haopings;
    private TextView huanmbers;
    private SimpleDraweeView xianhua;
    private TextView xianhuanum;
    private SimpleDraweeView jiangzhang;
    private TextView jiangzhangnum;
    private SimpleDraweeView jiangbei;
    private TextView jiangbeinum;
    private TextView jianjie;
    private TextView lingyu;
    private Button btn_reviews;
    private Button btn_seat;
    private DoctroinfoBean.ResultBean doctroinfoBeanResult;
    private List<DoctroinfoBean.ResultBean.CommentListBean> commentList;
    private List<DoctroinfoBean.ResultBean.DoctorReceiveGiftListBean> doctorReceiveGiftList;
    private int doctorIds;
    private RecyclerView pinglun;
    private String doctorName;
    private String doctorId;
    private String doctorUserName;

    @Override
    protected int bindLayout() {
        return R.layout.activity_inquery;
    }

    @Override
    protected DoctroInfoPresenter setPresenter() {
        return new DoctroInfoPresenter ();
    }

    @Override
    protected void initData() {
        super.initData ();

        Intent intent = getIntent ();
        doctorId = intent.getStringExtra ( "doctorId" );
        doctorUserName = intent.getStringExtra ( "doctorUserName" );
        presenter.doctorinfo ( Integer.parseInt ( doctorId ) );
    }

    @Override
    protected void initView() {
        super.initView ();
        backimage = findViewById ( R.id.backimage );
        doctro_image = findViewById ( R.id.doctro_image );
        zhiweis = findViewById ( R.id.docnames );
        docnames = findViewById ( R.id.zhiweis );
        guanzhu = findViewById ( R.id.guanzhu );
        addresse = findViewById ( R.id.addresse );
        haopings = findViewById ( R.id.haopings );
        huanmbers = findViewById ( R.id.huanmbers );
        xianhua = findViewById ( R.id.xianhua );
        xianhuanum = findViewById ( R.id.xianhuanum );
        jiangbei = findViewById ( R.id.jiangbei );
        jiangbeinum = findViewById ( R.id.jiangbeinum );
        jiangzhang = findViewById ( R.id.jiangzhang );
        jiangzhangnum = findViewById ( R.id.jiangzhangnum );
        lingyu = findViewById ( R.id.lingyu  );
        jianjie = findViewById ( R.id. jianjie);
        btn_reviews = findViewById ( R.id.btn_reviews );
        btn_seat = findViewById ( R.id.btn_seat );
        pinglun = findViewById ( R.id.pinglun );

        backimage.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        } );

        btn_seat.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent ( InqueryActivity.this, LiaoTActivity.class );
                intent.putExtra ( "userName", doctorName);
                intent.putExtra ( "doctorId",doctorId );
                startActivity ( intent );

            }
        } );
    }

    @Override
    public void success(DoctroinfoBean doctroinfoBean) {
        doctroinfoBeanResult = doctroinfoBean.getResult ();
        String imagePic = doctroinfoBeanResult.getImagePic ();
        doctorName = doctroinfoBeanResult.getDoctorName ();
        doctorIds = doctroinfoBeanResult.getDoctorId ();
        String jobTitle = doctroinfoBeanResult.getJobTitle ();
        String inauguralHospital = doctroinfoBeanResult.getInauguralHospital ();
        String praise = doctroinfoBeanResult.getPraise ();
        int serverNum = doctroinfoBeanResult.getServerNum ();
        int servicePrice = doctroinfoBeanResult.getServicePrice ();
        int followFlag = doctroinfoBeanResult.getFollowFlag ();
        String personalProfile = doctroinfoBeanResult.getPersonalProfile ();
        String goodField = doctroinfoBeanResult.getGoodField ();

        doctro_image.setImageURI ( imagePic );
        docnames.setText ( doctorName );
        zhiweis.setText ( jobTitle );
        addresse.setText ( inauguralHospital );

        haopings.setText ("好评率 "+ praise );
        huanmbers.setText ( "服务人数 "+ serverNum );
        btn_reviews.setText ( servicePrice+"H币/1次" );

        jianjie.setText ( personalProfile );
        lingyu.setText ( goodField );


        //评论
        commentList = doctroinfoBeanResult.getCommentList ();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager ( this, LinearLayoutManager.VERTICAL, false );
        pinglun.setLayoutManager ( linearLayoutManager );
        DoctroEvalutaeAdapter doctroEvalutaeAdapter = new DoctroEvalutaeAdapter ( this, commentList );
        pinglun.setAdapter ( doctroEvalutaeAdapter );

        //礼物
        doctorReceiveGiftList = doctroinfoBeanResult.getDoctorReceiveGiftList ();
        if (doctorReceiveGiftList.size ()==0){
            Log.e ( TAG,doctorReceiveGiftList.size ()+"" );
            return;
        }else {
            String giftPic0 = doctorReceiveGiftList.get ( 0 ).getGiftPic ();
            int receiveNum0 = doctorReceiveGiftList.get ( 0 ).getReceiveNum ();

            String giftPic1 = doctorReceiveGiftList.get ( 1 ).getGiftPic ();
            int receiveNum1 = doctorReceiveGiftList.get ( 1 ).getReceiveNum ();

            String giftPic2 = doctorReceiveGiftList.get ( 2 ).getGiftPic ();
            int receiveNum2 = doctorReceiveGiftList.get ( 2 ).getReceiveNum ();

            xianhua.setImageURI ( giftPic0 );
            xianhuanum.setText ( String.valueOf ( receiveNum0 ) );

            jiangzhang.setImageURI ( giftPic1 );
            jiangzhangnum.setText ( String.valueOf ( receiveNum1 ) );

            jiangbei.setImageURI ( giftPic2 );
            jiangbeinum.setText ( String.valueOf ( receiveNum2 ) );
        }


    }

    @Override
    public void fuilerror(String e) {

    }

}
