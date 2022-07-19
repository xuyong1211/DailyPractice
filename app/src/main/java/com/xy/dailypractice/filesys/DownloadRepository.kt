package com.xy.dailypractice.filesys

import android.annotation.SuppressLint
import android.content.ContentValues
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import com.xy.dailypractice.MyApplication
import io.reactivex.Observable
import okhttp3.*
import java.io.*
import java.util.concurrent.TimeUnit

object DownloadRepository {
    val HTTP_TIME_CONNECT_OUT_TIME = 5L
    val HTTP_TIME_READ_OUT_TIME = 10L
    val HTTP_TIME_WRITE_OUT_TIME = 10L
    @SuppressLint("CheckResult")
//    MediaStore.Video.Media.EXTERNAL_CONTENT_URI
    fun download(url: String?, dir:String , filtName: String,fileType : Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI ): Observable<DownloadProgressInfo> {
        val request = Request.Builder().url(url).build()
        val saveDir = "download"
        return Observable.create<DownloadProgressInfo> { it ->
            val build = OkHttpClient.Builder() //设置连接超时时间
                .connectTimeout(HTTP_TIME_CONNECT_OUT_TIME, TimeUnit.SECONDS) //设置读取超时时间
                .readTimeout(HTTP_TIME_READ_OUT_TIME, TimeUnit.SECONDS) //设置写入超时时间
                .writeTimeout(HTTP_TIME_WRITE_OUT_TIME, TimeUnit.SECONDS) //默认重试一次
                .cache(null)
                .build()

            build.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.d("enqueue", Thread.currentThread().name)
                    var inputStream: InputStream? = null
                    val buf = ByteArray(2048)
                    var len: Int
                    var subLen = 0L
//                    var fos: FileOutputStream? = null
                    var fos: OutputStream? = null
                    // 储存下载文件的目录
//                    val savePath: String? = isExistDir(dirTye)
                    try {
                        inputStream = response.body()!!.byteStream()
                        val total = response.body()!!.contentLength()
                        val percent = total / 100
//                        val file = File(savePath, filtName)
//                        fos = FileOutputStream(file)

//                        //    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
////        MediaStore.Video.Media.EXTERNAL_CONTENT_URI
//                        val os = prepareOutPutStream(filtName,fileType)
//                        if(os !=null){
//                            fos = os
//                        }else{
//                            throw java.lang.Exception("outStream is null")
//                        }
                        if(dir == MyApplication.Instant.applicationContext.filesDir.absolutePath){
                            val file = File(dir, filtName)
                             fos = FileOutputStream(file)
                        }

                        var sum: Long = 0
                        while (inputStream.read(buf).also { len = it } != -1) {
                            Log.d("downloadProgress", "length::${len}")
                            fos?.write(buf, 0, len)
                            sum += len.toLong()

                            // 下载中
                            subLen += len.toLong()
                            if (subLen >= percent * 3) {
                                val progress = (sum * 1.0f / total * 100).toInt()
                                it.onNext(DownloadProgressInfo().apply {
                                    this.isHasDone = false
                                    this.progress = progress
                                })
                                subLen = 0L
                            }

                        }
                        fos?.flush()
                        // 下载完成
                        it.onNext(DownloadProgressInfo().apply {
                            this.isHasDone = true
                            this.progress = 100
                        })
                    } catch (e: Exception) {
                        it.onError(e)
                    } finally {
                        try {
                            inputStream?.close()
                        } catch (e: IOException) {
                        }
                        try {
                            fos?.close()
                        } catch (e: IOException) {
                        }
                    }
                }

            })

        }


    }

//    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//        MediaStore.Video.Media.EXTERNAL_CONTENT_URI
    fun prepareOutPutStream(fileName:String, fileType : Uri):OutputStream?{

        val contentValues =  ContentValues().apply {
            val split = fileName.split(".")
            when(fileType){
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI ->{
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        put(MediaStore.Images.ImageColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/dbtWallpaper/")
                    }
                    put(MediaStore.Images.Media.DISPLAY_NAME,fileName)
                    put(MediaStore.Images.Media.DESCRIPTION,fileName)
                    put(MediaStore.Images.Media.MIME_TYPE,"image/${split[split.size-1]}")
                }
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI ->{
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        put(MediaStore.Video.VideoColumns.RELATIVE_PATH, Environment.DIRECTORY_MOVIES + "/dbtWallpaper/")
                    }
                    put(MediaStore.Video.Media.DISPLAY_NAME,fileName)
                    put(MediaStore.Video.Media.DESCRIPTION,fileName)

                    put(MediaStore.Video.Media.MIME_TYPE,"video/${split[split.size-1]}")
                }
            }


        }
    val uri = MyApplication.Instant.contentResolver.insert(fileType, contentValues)
    return try {
        val openOutputStream = MyApplication.Instant.contentResolver.openOutputStream(uri!!)
        openOutputStream
    }catch (e:Exception){
        e.printStackTrace()
        null
    }

}
    /**
     * @param saveDir
     * @return
     * @throws IOException
     * 判断下载目录是否存在
     */
    @Throws(IOException::class)
    fun isExistDir(saveDir: String): String? {
        // 下载位置
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
//            val downloadFile = File(BaseApplication.getInstance().externalCacheDir, saveDir)
            val downloadFile = File(Environment.getExternalStoragePublicDirectory(saveDir).absolutePath)
            val savePath = downloadFile.absolutePath
            if (!downloadFile.exists()) {
                downloadFile.mkdirs()
            }
            return savePath
        }
        return null
    }
}