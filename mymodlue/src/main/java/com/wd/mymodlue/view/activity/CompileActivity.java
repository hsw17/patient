package com.wd.mymodlue.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.mvplibrary.base.BaseActivity;
import com.bwie.mvplibrary.utils.CustomClickListener;
import com.bwie.mvplibrary.utils.SPUtils;
import com.bwie.mvplibrary.utils.ToastUtils;
import com.wd.mymodlue.R;
import com.wd.mymodlue.R2;
import com.wd.mymodlue.modle.bean.UpdateArchivesBean;
import com.wd.mymodlue.persenter.Persenter;
import com.wd.mymodlue.view.contract.IViewContract;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CompileActivity extends BaseActivity<Persenter> implements IViewContract.IView {


    @BindView(R2.id.head_details_back)
    ImageView headDetailsBack;
    @BindView(R2.id.vvv)
    View vvv;
    private ImageView head_details_back;
    private EditText diseaseMain;
    private LinearLayout line1;
    private EditText diseaseNow;
    private LinearLayout lin2;
    private EditText diseaseBefore;
    private LinearLayout lin3;
    private EditText treatmentHospitalRecent;
    private LinearLayout lin4;
    private TextView start;
    private TextView edit_starttime;
    private ImageView img_startTime;
    private RelativeLayout startTime;
    private LinearLayout lin5;
    private TextView end;
    private TextView edit_endtime;
    private ImageView img_endTime;
    private RelativeLayout endTime;
    private LinearLayout lin6;
    private EditText treatmentProcess;
    private LinearLayout lin7;
    private RecyclerView bo_image_list;
    private ImageView add_image;
    private Button addFiles;
    private LinearLayout lin8;
    private SPUtils login;
    private Map<String, Object> map;
    Calendar calendar = Calendar.getInstance(Locale.CHINA);
    private int id1;

    @Override
    protected int bindLayout() {
        return R.layout.activity_compile;
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
        head_details_back = findViewById(R.id.head_details_back);
        diseaseMain = findViewById(R.id.diseaseMain);
        line1 = findViewById(R.id.line1);
        diseaseNow = findViewById(R.id.diseaseNow);
        lin2 = findViewById(R.id.lin2);
        diseaseBefore = findViewById(R.id.diseaseBefore);
        lin3 = findViewById(R.id.lin3);
        treatmentHospitalRecent = findViewById(R.id.treatmentHospitalRecent);
        lin4 = findViewById(R.id.lin4);
        start = findViewById(R.id.start);
        edit_starttime = findViewById(R.id.edit_starttime);
        img_startTime = findViewById(R.id.img_startTime);
        startTime = findViewById(R.id.startTime);
        lin5 = findViewById(R.id.lin5);
        end = findViewById(R.id.end);
        edit_endtime = findViewById(R.id.edit_endtime);
        img_endTime = findViewById(R.id.img_endTime);
        endTime = findViewById(R.id.endTime);
        lin6 = findViewById(R.id.lin6);
        treatmentProcess = findViewById(R.id.treatmentProcess);
        lin7 = findViewById(R.id.lin7);
        bo_image_list = findViewById(R.id.bo_image_list);
        add_image = findViewById(R.id.add_image);
        addFiles = findViewById(R.id.addFiles);
        lin8 = findViewById(R.id.lin8);
        ToastUtils.init(this);
//        获取存储内容
        login = new SPUtils(this, "login");
        int id = (int) login.getSharedPreference("id", 0);
        String sessionId = (String) login.getSharedPreference("sessionId", "");
        map = new HashMap<>();
        map.put("userId", id);
        map.put("sessionId", sessionId);
        Intent intent = getIntent();
        id1 = intent.getIntExtra("id", 0);
//      返回
        head_details_back.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                finish();
            }

            @Override
            protected void onFastClick() {

            }
        });
        //点击保存

        addFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框的内容
                //主要病症
                String main = diseaseMain.getText().toString().trim();
                //现病史
                String now = diseaseNow.getText().toString().trim();
                //既往史
                String before = diseaseBefore.getText().toString().trim();
                //最近治疗医院
                String recent = treatmentHospitalRecent.getText().toString().trim();
                //治疗过程
                String process = treatmentProcess.getText().toString().trim();
                String startte = edit_starttime.getText().toString().trim();
                String endte = end.getText().toString().trim();
                Map<String, Object> oap = new HashMap<>();
                oap.put("diseaseMain", main);
                oap.put("diseaseNow", now);
                oap.put("diseaseBefore", before);
                oap.put("treatmentHospitalRecent", recent);
                oap.put("treatmentProcess", process);
                oap.put("treatmentStartTime", startte);
                oap.put("treatmentEndTime", endte);
                presenter.onGetupdateUserArchives(map,oap);
            }
        });

        //设置在activity启动的时候输入法默认是不开启的
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //开始时间
        img_startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(CompileActivity.this);
                final View view = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_date, null);
                final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
                //设置日期简略显示 否则详细显示 包括:星期\周
                datePicker.setCalendarViewShown(false);
                //初始化当前日期
                calendar.setTimeInMillis(System.currentTimeMillis());
                datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH), null);
                //设置date布局
                builder.setView(view);

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //日期格式
                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d",
                                datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));
                        edit_starttime.setText(sb);
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
            }
        });

        //结束时间
        img_endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(CompileActivity.this);
                final View view = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_date, null);
                final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
                //设置日期简略显示 否则详细显示 包括:星期\周
                datePicker.setCalendarViewShown(false);
                //初始化当前日期
                calendar.setTimeInMillis(System.currentTimeMillis());
                datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH), null);
                //设置date布局
                builder.setView(view);

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //日期格式
                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d",
                                datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));
                        end.setText(sb);
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
            }
        });
    }

    @Override
    public void onSuccess(Object obj) {
        UpdateArchivesBean updateArchivesBean= (UpdateArchivesBean) obj;
        if ("0000".equals(updateArchivesBean.getStatus())) {
            ToastUtils.show(updateArchivesBean.getMessage());
            Intent intent=new Intent("com.hl.SuccessActivity");
            intent.putExtra("idl",id1);
            sendBroadcast(intent);
        }else {
            ToastUtils.show(updateArchivesBean.getMessage());
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
