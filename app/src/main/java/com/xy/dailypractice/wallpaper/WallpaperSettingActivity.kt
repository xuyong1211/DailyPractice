package com.xy.dailypractice.wallpaper

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.xy.dailypractice.R
import com.xy.dailypractice.databinding.ActivitySecondBinding
import java.io.IOException


class WallpaperSettingActivity  : AppCompatActivity(){
    private lateinit var viewBinding: ActivitySecondBinding

    val handler = Handler(Looper.getMainLooper())

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySecondBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)


        viewBinding.button.setOnClickListener {
            SetHomePaper()
        }
        viewBinding.button1.setOnClickListener {
            SetLockPaper()
        }


    }


    // 此方法 高于N的版本 会直接把主屏幕和 锁屏的壁纸都换掉
    fun SetWallPaper() {
        val mWallManager = WallpaperManager.getInstance(this)
        try {
            val bitmap = BitmapFactory.decodeResource(resources,R.drawable.ic_head_pic)
            mWallManager.setBitmap(bitmap)
            Toast.makeText(this@WallpaperSettingActivity, "壁纸设置成功", Toast.LENGTH_SHORT)
                .show()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun SetHomePaper() {
        val mWallManager = WallpaperManager.getInstance(this)
        try {
            val bitmap = BitmapFactory.decodeResource(resources,R.drawable.ic_head_pic)
            mWallManager.setBitmap(bitmap,null,false,WallpaperManager.FLAG_SYSTEM )
            Toast.makeText(this@WallpaperSettingActivity, "壁纸设置成功", Toast.LENGTH_SHORT)
                .show()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun SetLockPaper() {
        val mWallManager = WallpaperManager.getInstance(this)
        try {
            val bitmap = BitmapFactory.decodeResource(resources,R.drawable.ic_head_pic)
            mWallManager.setBitmap(bitmap,null,true,WallpaperManager.FLAG_LOCK)
            Toast.makeText(this@WallpaperSettingActivity, "壁纸设置成功", Toast.LENGTH_SHORT)
                .show()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    private fun SetLockWallPaper() {

        //TODO 低版本验证此方法是不是设置锁屏的
        // TODO Auto-generated method stub
        try {
            val mWallManager = WallpaperManager.getInstance(this);
            val class1 = mWallManager.javaClass//获取类名
            val setWallPaperMethod = class1.getMethod("setBitmapToLockWallpaper", Bitmap::class.java)//获取设置锁屏壁纸的函数
            setWallPaperMethod.invoke(mWallManager, BitmapFactory.decodeResource(resources,R.drawable.iv_screen))//调用锁屏壁纸的函数，并指定壁纸的路径imageFilesPath
            Toast.makeText(this@WallpaperSettingActivity, "锁屏壁纸设置成功", Toast.LENGTH_SHORT).show()
        } catch (e : Throwable ) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

    }
}