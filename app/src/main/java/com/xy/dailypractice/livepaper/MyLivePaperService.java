package com.xy.dailypractice.livepaper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.Toast;

import com.xy.dailypractice.MyApplication;

import java.io.File;
import java.io.IOException;

public class MyLivePaperService  extends WallpaperService {
    @Override
    public Engine onCreateEngine() {
        Log.d("MyLivePaperService","VideoEngine  onCreateEngine"+ this.toString());

        return new VideoEngine();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyLivePaperService","MyLivePaperService  onCreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyLivePaperService","MyLivePaperService  onStartCommand"+ this.toString());

        return super.onStartCommand(intent, flags, startId);
    }

    class VideoEngine extends Engine {
        private MediaPlayer mMediaPlayer;
        private BroadcastReceiver mVideoParamsControlReceiver;
        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            Log.d("MyLivePaperService","VideoEngine  onCreate"+ this.toString());

            IntentFilter intentFilter = new IntentFilter(LivePaperHelper.VIDEO_PARAMS_CONTROL_ACTION);
            registerReceiver(mVideoParamsControlReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Log.d("MyLivePaperService","onReceive");
                    int action = intent.getIntExtra(LivePaperHelper.KEY_ACTION, -1);
                    String fileName = intent.getStringExtra(LivePaperHelper.FILE_NAME);
                    switch (action) {
                        case LivePaperHelper.ACTION_UPDATE_VIDEO_FILE_PATH:
                            //更新视频播放源
//                            String videoFilePath = getVideoPath();
                            try {
//                                mMediaPlayer.setDataSource(getApplicationContext(), Uri.parse(videoFilePath));
                                File file = new File(MyApplication.Instant.getApplicationContext().getFilesDir().getAbsolutePath(),fileName);
                                Log.d("MyLivePaperService","onReceive==="+file.getAbsolutePath());
                                mMediaPlayer.stop();
                                mMediaPlayer.seekTo(0);
//                                mMediaPlayer.setDataSource(MyApplication.Instant,Uri.parse());
                                mMediaPlayer.setDataSource(file.getAbsolutePath());
                                mMediaPlayer.prepareAsync();
                                Toast.makeText(context, "成功", Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        default:
                            break;
                    }
                }
            }, intentFilter);
        }

        @Override
        public void onDestroy() {
            unregisterReceiver(mVideoParamsControlReceiver);
            Log.d("MyLivePaperService","VideoEngine  onDestroy"+ this.toString());

            super.onDestroy();
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            Log.d("MyLivePaperService","VideoEngine  onVisibilityChanged"+ this.toString());

            if (visible) {
                Log.d("MyLivePaperService","VideoEngine  onVisibilityChanged  visible"+ this.toString());
                mMediaPlayer.start();
            } else {
                Log.d("MyLivePaperService","VideoEngine  onVisibilityChanged  invisible"+ this.toString());

                mMediaPlayer.pause();
            }
        }

        @Override
        public void onSurfaceCreated(SurfaceHolder holder) {
            super.onSurfaceCreated(holder);
            Log.d("MyLivePaperService","VideoEngine  onSurfaceCreated"+ this.toString());

            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setSurface(holder.getSurface());
            try {
                File file = new File(MyApplication.Instant.getApplicationContext().getFilesDir().getAbsolutePath(),"120068.mp4");
                mMediaPlayer.setDataSource(file.getAbsolutePath());
                mMediaPlayer.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
                mMediaPlayer.setLooping(true);
                mMediaPlayer.setVolume(0, 0);
                mMediaPlayer.prepare();
                mMediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
            Log.d("MyLivePaperService","VideoEngine  onSurfaceDestroyed"+ this.toString());

            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
