package com.xy.dailypractice.livepaper;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class LivePaperHelper {
    public static final String VIDEO_PARAMS_CONTROL_ACTION = "me.wally.arch.livewallpaper.VideoLiveWallpaper";

    public static final String KEY_ACTION = "action";
    public static final String FILE_NAME = "fileName";
    public static final int ACTION_UPDATE_VIDEO_FILE_PATH = 112;

    /**
     * 跳转到系统的动态壁纸设置界面
     */
    public static void startNewWallpaper(Context context) {
        Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
        intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(context, MyLivePaperService.class));
        context.startActivity(intent);
    }

    public static void updateWallpaperVideo(Context context,String fileName) {
        //发送广播更新
        Intent intent = new Intent(LivePaperHelper.VIDEO_PARAMS_CONTROL_ACTION);
        intent.putExtra(LivePaperHelper.KEY_ACTION, LivePaperHelper.ACTION_UPDATE_VIDEO_FILE_PATH);
        intent.putExtra(LivePaperHelper.FILE_NAME, fileName);
        context.sendBroadcast(intent);
    }
}
