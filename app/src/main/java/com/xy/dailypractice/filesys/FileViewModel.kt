package com.xy.dailypractice.filesys

import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xy.dailypractice.IOTransformer
import com.xy.dailypractice.MyApplication

class FileViewModel: ViewModel() {

    val downloadLiveData = MutableLiveData<DownloadProgressInfo>()
    fun downloadFile(url : String, fileName :String,fileType : Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI){
        DownloadRepository.download(url,MyApplication.Instant.applicationContext.filesDir.absolutePath,fileName,fileType)
            .compose(IOTransformer())
            .subscribe({
                downloadLiveData.postValue(it)
                Log.d("FileViewModel","${it.progress}")
            },{
                Log.d("FileViewModel","${it.message}")
            })
    }
}