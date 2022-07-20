package com.xy.dailypractice.filesys

import android.Manifest
import android.app.WallpaperManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.xy.dailypractice.databinding.ActivityFileOperatorBinding
import com.xy.dailypractice.livepaper.LivePaperHelper

class FileOperatorActivity : AppCompatActivity() {

    val downloadViewModel : FileViewModel by lazy {
        ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(FileViewModel::class.java)
    }
    val urls = arrayOf(
        "https://dobestapp.oss-cn-hangzhou.aliyuncs.com/dbt/apptools/ios/HDWallpaper/DynamicWallpaper/1.1/resource/140011.mp4",
        "https://dobestapp.oss-cn-hangzhou.aliyuncs.com/dbt/apptools/ios/HDWallpaper/DynamicWallpaper/1.1/resource/140032.mp4",
        "https://dobestapp.oss-cn-hangzhou.aliyuncs.com/dbt/apptools/ios/HDWallpaper/DynamicWallpaper/1.1/resource/120068.mp4",
    "https://dobestapp.oss-cn-hangzhou.aliyuncs.com/dbt/apptools/ios/HDWallpaper/DynamicWallpaper/1.1/resource/130001.mp4",
        "https://dobestapp.oss-cn-hangzhou.aliyuncs.com/dbt/apptools/ios/HDWallpaper/DynamicWallpaper/1.1/resource/120015.mp4",
        "https://dobestapp.oss-cn-hangzhou.aliyuncs.com/dbt/apptools/ios/HDWallpaper/DynamicWallpaper/1.1/resource/130072.mp4"
        )
    val names = arrayOf(
        "120068.mp4",
        "130001.mp4",
        "120015.mp4",
        "130072.mp4"
    )
    val videos = arrayOf(
        "https://dobestapp.oss-cn-hangzhou.aliyuncs.com/dbt/apptools/ios/HDWallpaper/DynamicWallpaper/1.1/resource/130005.mp4",
        "https://dobestapp.oss-cn-hangzhou.aliyuncs.com/dbt/apptools/ios/HDWallpaper/DynamicWallpaper/1.1/resource/100004.mp4",
        "https://dobestapp.oss-cn-hangzhou.aliyuncs.com/dbt/apptools/ios/HDWallpaper/DynamicWallpaper/1.1/resource/130008.mp4",
        "https://dobestapp.oss-cn-hangzhou.aliyuncs.com/dbt/apptools/ios/HDWallpaper/DynamicWallpaper/1.1/resource/130030.mp4",
        "https://dobestapp.oss-cn-hangzhou.aliyuncs.com/dbt/apptools/ios/HDWallpaper/DynamicWallpaper/1.1/resource/100008.mp4"
    )

    var currentIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivityFileOperatorBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.N){
            requestPermissions(arrayOf(Manifest
                .permission.WRITE_EXTERNAL_STORAGE , Manifest.permission.READ_EXTERNAL_STORAGE),10)
        }
//  /storage/self/primary/Pictures/WeChat/wx_camera_1606916859637.mp4/**/
        /*
        * /storage/self/primary/Pictures/WeChat/wx_camera_1600527652489.mp4
        *
        *
        *
        *
        *
        * */
        viewBinding.run {
            bt1.setOnClickListener {
                currentIndex = 0
                downloadViewModel.downloadFile(urls[0],names[0], MediaStore.Video.Media.EXTERNAL_CONTENT_URI )
            }

            bt2.setOnClickListener {
                currentIndex = 1
                downloadViewModel.downloadFile(urls[1],names[1], MediaStore.Video.Media.EXTERNAL_CONTENT_URI )

            }

            bt3.setOnClickListener {
                currentIndex = 2
                downloadViewModel.downloadFile(urls[2],names[2], MediaStore.Video.Media.EXTERNAL_CONTENT_URI )

            }

            bt4.setOnClickListener {
                currentIndex = 3
                downloadViewModel.downloadFile(urls[3],names[3], MediaStore.Video.Media.EXTERNAL_CONTENT_URI )

            }
//
//            bt2.setOnClickListener {
//                downloadViewModel.downloadFile("https://dobestapp.oss-cn-hangzhou.aliyuncs.com/dbt/apptools/ios/HDWallpaper/wallpaper/wallpaper/010058.png",
//                    "010058.png")
//            }
        }
        initData()
    }

    private fun initData() {
        downloadViewModel.downloadLiveData.observe(this){
            if(it.isHasDone){
                if(currentIndex == 0){
                    Toast.makeText(this@FileOperatorActivity, "下载完成", Toast.LENGTH_SHORT).show()
                    LivePaperHelper.startNewWallpaper(this@FileOperatorActivity)
                }
                LivePaperHelper.updateWallpaperVideo(this@FileOperatorActivity,names[currentIndex])
            }
        }
    }



}