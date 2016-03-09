package com.travel.personaltravel.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.travel.personaltravel.MainActivity;
import com.travel.personaltravel.R;
import com.travel.personaltravel.application.AiLYApplication;
import com.travel.personaltravel.cache.ACache;
import com.travel.personaltravel.widget.CircleImageView;
import com.travel.personaltravel.widget.CustomDialog;


public class PersonalCenterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView uNameTv;
    private CircleImageView avatarIv;
    private String avatarImgCacheDir = "avatar-cache";
    private EditText edit_name;
    private TextView myName;
    private TextView mySex;
    private TextView myTM;
    private ACache aCache;
    private EditText edit_sex;
    private EditText edit_tm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_center_activity);
        aCache = ACache.get(this, avatarImgCacheDir);

        ImageView backIv = (ImageView) findViewById(R.id.personal_center_title_bar_back_iv);
        avatarIv = (CircleImageView) findViewById(R.id.personal_center_avatar_iv);
        ImageView setIv = (ImageView) findViewById(R.id.personal_center_setting_iv);
        uNameTv = (TextView) findViewById(R.id.personal_center_title_bar_uname_tv);
        myName = (TextView) findViewById(R.id.pc_my_name_tv);
        String pName = aCache.getAsString("pName");
        if (pName == null) pName = "点击修改名字";
        String pSex = aCache.getAsString("pSex");
        if (pSex == null) pSex = "男";
        String pTm = aCache.getAsString("pTm");
        if (pTm == null) pTm = "点击修改宣言";
        myName.setText(pName);
        mySex = (TextView) findViewById(R.id.pc_my_sex_tv);
        mySex.setText(pSex);
        myTM = (TextView) findViewById(R.id.pc_my_tm_tv);
        myTM.setText(pTm);
        TextView exitTv = (TextView) findViewById(R.id.personal_settin_login_out_btn);
        exitTv.setOnClickListener(this);
        LinearLayout balanceLl = (LinearLayout) findViewById(R.id.personal_center_my_tm_ll);
        balanceLl.setOnClickListener(this);
        LinearLayout uOrderLl = (LinearLayout) findViewById(R.id.personal_center_my_name_ll);
        LinearLayout uCollectLl = (LinearLayout) findViewById(R.id.personal_center_my_sex_ll);
        setIv.setOnClickListener(this);
        backIv.setOnClickListener(this);
        avatarIv.setOnClickListener(this);
        uOrderLl.setOnClickListener(this);
        uCollectLl.setOnClickListener(this);

        Bitmap cAvatar = aCache.getAsBitmap("cAvatar");
        String mname = aCache.getAsString("uname");
        if (mname != null) {
            uNameTv.setText(mname);
        }
        if (cAvatar != null) {
            avatarIv.setImageBitmap(cAvatar);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Bitmap cAvatar = aCache.getAsBitmap("cAvatar");
        if (cAvatar != null) {
            avatarIv.setImageBitmap(cAvatar);
        }
    }

    private void isUpdateAvatar() {
        startActivity(new Intent(this, AvatarPickerActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal_center_title_bar_back_iv:
                finish();
                break;
            case R.id.personal_center_avatar_iv:
                //TODO 选择或拍照完成之后 上传头像,取消则不上传
                isUpdateAvatar();
                break;
            case R.id.personal_center_my_name_ll:
                //TODO 编辑姓名
                View view = getLayoutInflater().inflate(R.layout.dialog_name_layout, null);
                edit_name = (EditText) view.findViewById(R.id.edit_name);
                AlertDialog nameDialog = new AlertDialog.Builder(this)
                        .setTitle("编辑姓名")
                        .setIcon(android.R.drawable.ic_menu_add)
                        .setView(view)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String name = edit_name.getText().toString();
                                myName.setText(name);
                                aCache.put("pName", name);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .setCancelable(true)
                        .create();
                nameDialog.show();
                break;
            case R.id.personal_center_my_sex_ll:
                //TODO 编辑性别
                View view2 = getLayoutInflater().inflate(R.layout.dialog_sex_layout, null);
                edit_sex = (EditText) view2.findViewById(R.id.edit_sex);
                AlertDialog sexDialog = new AlertDialog.Builder(this)
                        .setTitle("编辑性别")
                        .setIcon(android.R.drawable.ic_menu_add)
                        .setView(view2)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String sex = edit_sex.getText().toString();
                                mySex.setText(sex);
                                aCache.put("pSex", sex);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .setCancelable(true)
                        .create();
                sexDialog.show();
                break;
            case R.id.personal_center_my_tm_ll:
                //TODO 编辑旅游宣言
                View view3 = getLayoutInflater().inflate(R.layout.dialog_tm_layout, null);
                edit_tm = (EditText) view3.findViewById(R.id.edit_tm);
                AlertDialog tmDialog = new AlertDialog.Builder(this)
                        .setTitle("编辑旅游宣言")
                        .setIcon(android.R.drawable.ic_menu_add)
                        .setView(view3)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String tm = edit_tm.getText().toString();
                                myTM.setText(tm);
                                aCache.put("pTm", tm);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .setCancelable(true)
                        .create();
                tmDialog.show();
                break;
            case R.id.personal_center_setting_iv:
                startActivity(new Intent(PersonalCenterActivity.this, PersonalSettingActivity.class));
                break;
            case R.id.personal_settin_login_out_btn:
                if (AiLYApplication.isLogged) {
                    //TODO 提示用户慎重操作
                    final CustomDialog dialog = new CustomDialog(PersonalCenterActivity.this, R.style.mystyle,
                            R.layout.custom_exit_dialog);
                    dialog.setnCallback(new CustomDialog.negativeCallback() {
                        @Override
                        public void clickCancel() {
                            //取消对话框
                            dialog.dismiss();
                        }
                    });
                    dialog.setpCallback(new CustomDialog.positiveCallback() {
                        @Override
                        public void clickConfirm() {
                            //提示用户退出
                            Toast.makeText(PersonalCenterActivity.this, "已退出登录", Toast.LENGTH_SHORT).show();
                            AiLYApplication.isLogged = false;
                            aCache.put("isLogin", "false");
                            Intent intent = new Intent(PersonalCenterActivity.this, MainActivity.class);
                            startActivity(intent);
                            PersonalCenterActivity.this.finish();
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                } else {
                    Toast.makeText(PersonalCenterActivity.this, "您已经处于退出状态了", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
