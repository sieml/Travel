package com.travel.personaltravel.activity;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


import com.travel.personaltravel.R;
import com.travel.personaltravel.cache.ACache;
import com.travel.personaltravel.widget.AvatarPickerView;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created with Android Studio
 * Email: sielee@163.com
 * Date: 2016/1/13
 * Author: SieLee
 * CopyRight: MilesLife
 *
 * @Description: TODO 截取头像的页面
 */
public class AvatarPickerActivity extends AppCompatActivity implements View.OnClickListener {

    private AvatarPickerView sourceIv;
    private ProgressDialog progressDialog;
    private ACache aCache;
    private String avatarImgCacheDir = "avatar-cache";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avatar_picker_activity);
        sourceIv = (AvatarPickerView) findViewById(R.id.source);
        ImageView cancelIv = (ImageView) findViewById(R.id.take_photo_bar_cancel_iv);
        cancelIv.setOnClickListener(this);
        setResult(RESULT_CANCELED);
        aCache = ACache.get(this, avatarImgCacheDir);
    }


    public void showProgressDialog(String title, String message) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(AvatarPickerActivity.this, title,
                    message, true, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle(title);
            progressDialog.setMessage(message);
        }
        progressDialog.show();
    }

    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private void openImageIntent() {

        //相机拍摄之后的输出路径
        final File filename = new File(Environment.getExternalStorageDirectory(), "avatar_capture.jpg");
        Uri outputFileUri = Uri.fromFile(filename);

        //所有符合条件的结果集合
        final List<Intent> retList = new ArrayList<>();
        //TODO ACTION_IMAGE_CAPTURE调用系统相机
        final Intent capIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = getPackageManager();
        //查找相机（前后，双置等）
        final List<ResolveInfo> camList = packageManager.queryIntentActivities(capIntent, 0);
        //遍历所有的相机
        for (ResolveInfo res : camList) {
            final String packageName = res.activityInfo.packageName;
            final Intent intent = new Intent(capIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(packageName);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            retList.add(intent);
        }

        final Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        //TODO 从gallery选取图片的galleryIntent
        final Intent chooserIntent = Intent.createChooser(galleryIntent, "截取头像");
        //TODO 查到符合ACTION_IMAGE_CAPTURE的retList
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, retList.toArray(new Parcelable[retList.size()]));

        startActivityForResult(chooserIntent, PICK_IMAGE);
    }

    final static int PICK_IMAGE = 0;

    @Override
    public void onActivityResult(int req, int res, Intent intent) {
        if (res == RESULT_OK) {
            boolean isCamera = intent == null || intent.getAction() != null && intent.getAction().equals(MediaStore.ACTION_IMAGE_CAPTURE);
            getIntent().setData(isCamera ? Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "avatar_capture.jpg")) : intent.getData());
        } else {
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getIntent().getData() == null)
            openImageIntent();
        else {
            sourceIv.setVisibility(View.VISIBLE);
            //如果需要处理旋转90度,需要添加另外的代码,此时不适用
            sourceIv.setImageURI(getIntent().getData());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void clickUpdate(View view) {
        showProgressDialog("", "请稍后...");
        if (sourceIv.getDrawable() != null) {
            Bitmap bitmap = sourceIv.getClippedBitmap(512, 512);
            aCache.put("cAvatar", bitmap);
            //File file = aCache.file("cAvatar");
            hideProgressDialog();
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.take_photo_bar_cancel_iv:
                finish();
                break;
        }
    }
}
