package com.reed.wechatsharedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private WeChatShareUtil weChatShareUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weChatShareUtil = WeChatShareUtil.getInstance(this);
    }

    @Override
    public void onClick(View v) {
        boolean result = true;
        switch (v.getId()) {
            case R.id.btn_friends_text:
                String sessionText = "分享的文字：hello";
                result = weChatShareUtil.shareText(sessionText, SendMessageToWX.Req.WXSceneSession);
                break;
            case R.id.btn_friends_pic:
                Bitmap sessionBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                result = weChatShareUtil.sharePic(sessionBmp, SendMessageToWX.Req.WXSceneSession);
                break;
            case R.id.btn_friends_page:
                String sessionTitle = "百度";
                String sessionDescription = "百度的链接";
                String sessionUrl = "http://www.baidu.com";
                Bitmap sessionThumb = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                result = weChatShareUtil.shareUrl(sessionUrl, sessionTitle, sessionThumb, sessionDescription, SendMessageToWX.Req.WXSceneSession);
                break;
            case R.id.btn_timeline_text:
                if (weChatShareUtil.isSupportWX()) {
                    String text = "分享的文字：hello";
                    result = weChatShareUtil.shareText(text, SendMessageToWX.Req.WXSceneTimeline);
                } else {
                    Toast.makeText(MainActivity.this, "手机上微信版本不支持分享到朋友圈", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_timeline_pic:
                if (weChatShareUtil.isSupportWX()) {
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                    result = weChatShareUtil.sharePic(bmp, SendMessageToWX.Req.WXSceneTimeline);
                } else {
                    Toast.makeText(MainActivity.this, "手机上微信版本不支持分享到朋友圈", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_timeline_page:
                if (weChatShareUtil.isSupportWX()) {
                    String title = "百度";
                    String description = "百度的链接";
                    String url = "http://www.baidu.com";
                    Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                    result = weChatShareUtil.shareUrl(url, title, thumb, description, SendMessageToWX.Req.WXSceneTimeline);
                } else {
                    Toast.makeText(MainActivity.this, "手机上微信版本不支持分享到朋友圈", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        if (!result) {
            Toast.makeText(MainActivity.this, "没有检测到微信", Toast.LENGTH_SHORT).show();
        }
    }
}
