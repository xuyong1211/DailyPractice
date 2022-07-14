package com.xy.dailypractice.videoplay

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.xy.dailypractice.databinding.ActivityVideoPlayBinding
import xyz.doikki.videoplayer.exo.ExoMediaPlayerFactory

class VideoPlayActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityVideoPlayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityVideoPlayBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        playVideo("https://dobestapp.oss-cn-hangzhou.aliyuncs.com/dbt/apptools/ios/HDWallpaper/DynamicWallpaper/100001.mp4")
    }
    private fun playVideo(filePath: String) {
//        val mediaController = MediaController(this)
//        viewBinding.vvView.setMediaController(mediaController)
//        viewBinding.vvView.setVideoURI(Uri.parse(filePath))
//        viewBinding.vvView.start()
        viewBinding.vvView.setUrl(filePath)
        viewBinding.vvView.setLooping(true)
        viewBinding.vvView.setVolume(-1.0f,-1.0f)
        viewBinding.vvView.setPlayerFactory(ExoMediaPlayerFactory.create())
//        viewBinding.vvView.setVolume(0.1f,0.1f)
        viewBinding.vvView.start()

    }

    override fun onPause() {
        super.onPause()
        viewBinding.vvView.pause()
    }

    override fun onResume() {
        super.onResume()
        viewBinding.vvView.resume()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding.vvView.release()
    }


    override fun onBackPressed() {
        if (!viewBinding.vvView.onBackPressed()) {
            super.onBackPressed()
        }
    }

}