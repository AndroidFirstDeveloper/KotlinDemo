package com.example.czl.privacy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.czl.R
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class PrivacyActivity : AppCompatActivity() {
    var ownDirectory=""
    val ownFile = "today.txt"
    val otherDirectory = ""
    val otherFile = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy)
        ownDirectory= externalCacheDir.toString() + File.separator + "log"
        Log.e("PrivacyActivity", "onCreate: " + ownDirectory)
    }

    fun createOwnFile(view: View) {
        val directoryFile = File(ownDirectory)
        if (!directoryFile.exists() && !directoryFile.isDirectory()) {
            val result = directoryFile.mkdirs()
            if (result) {
                Log.e("PrivacyActivity", "createOwnFile: 创建目录成功")
            } else {
                Log.e("PrivacyActivity", "createOwnFile: 创建目录失败")
                return
            }
        }
        val file = File(directoryFile, ownFile)
        if (!file.exists()) {
            var outputStream: FileOutputStream? = null
            try {
                val result = file.createNewFile()
                if (result) {
                    val content = "hello world !!!!"
                    outputStream = FileOutputStream(file)
                    outputStream.write(content.toByteArray())
                    Log.e("PrivacyActivity", "createOwnFile: 创建文件成功")
                }
            } catch (e: IOException) {
                Log.e("PrivacyActivity", "createOwnFile: 创建文件失败")
                Log.e("PrivacyActivity", "createOwnFile: " + e.message)
            } finally {
                try {
                    if (outputStream != null)
                        outputStream.close()
                } catch (e: IOException) {
                    Log.e("PrivacyActivity", "createOwnFile: 关闭数据流失败")
                }
            }
        } else {
            Log.e("PrivacyActivity", "createOwnFile: 文件已经创建完毕不再重复创建")
        }
    }

    fun accessOtherAppFile(view: View) {
        val directory = File(otherDirectory)
        if (directory.exists()) {
            val file = File(directory, otherFile)
            if (file.exists()) {
                var inputStream: FileInputStream? = null
                try {
                    val sb = StringBuilder()
                    inputStream = FileInputStream(file)
                    val result=ByteArray(1024)
                    while (inputStream.read() != -1) {
                        inputStream.read(result)
                        sb.append(String(result))
                    }
                    Log.e("PrivacyActivity", "accessOtherAppFile: 访问其它应用专属文件结果：" + sb.toString())
                } catch (e: IOException) {
                    Log.e("PrivacyActivity", "accessOtherAppFile: ", e)
                } finally {
                    try {
                        if (inputStream != null)
                            inputStream.close()
                    } catch (e: IOException) {
                        Log.e("PrivacyActivity", "accessOtherAppFile: 关闭数据流失败")
                    }
                }
            }
        }
    }
}