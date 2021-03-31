package com.example.czl2.privacy

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.czl2.R
import org.greenrobot.eventbus.EventBus
import java.io.*
import java.nio.charset.StandardCharsets
import kotlin.math.log


class PrivacyActivity : AppCompatActivity() {
    var ownDirectory = ""
    val ownFile = "today.txt"
    val otherDirectory = "/storage/emulated/0/Android/data/com.example.czl/cache/log"
    val otherFile = "today.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy)
        ownDirectory = externalCacheDir.toString() + File.separator + "log"
        Log.e("PrivacyActivity", "onCreate: " + ownDirectory)
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    fun openFileManager(view: View) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(intent, 1001)
    }

    fun readPublicFile2(view: View) {
        val path = "/storage/emulated/0/Download/log/today.txt"
        val contentResolver = contentResolver
        val parcelFileDescriptor = contentResolver.openFileDescriptor(Uri.parse(path), "r")
        var inputStream: InputStream? = null
        if (parcelFileDescriptor != null && parcelFileDescriptor.fileDescriptor != null) {
            try {
                inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
                val byteArray = ByteArray(128)
                while (true) {
                    val length = inputStream.read(byteArray)
                    if (length == -1) {
                        break
                    }
                    val content = String(byteArray, 0, length, StandardCharsets.UTF_8)
                    Log.e("PrivacyActivity", "readPublicFile2: " + content)
                }
            } catch (e: Exception) {
                Log.e("PrivacyActivity", "readPublicFile2: ", e)
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close()
                    } catch (e: Exception) {
                        Log.e("PrivacyActivity", "readPublicFile2: ", e)
                    }
                }
            }
        }
    }

    fun readPublicFile(view: View) {
        val path = "/storage/emulated/0/Download/log/today.txt"
        val file = File(path)
        if (!file.exists()) {
            Log.e("PrivacyActivity", "readPublicFile: 文件不存在")
            return
        }
        var inputStream: InputStream? = null
        try {
            inputStream = FileInputStream(path)
            val byteArray = ByteArray(128)
            while (true) {
                val length = inputStream.read(byteArray)
                if (length == -1) {
                    break
                }
                val result = String(byteArray, StandardCharsets.UTF_8)
                Log.e("PrivacyActivity", "readPublicFile: " + result)
            }
        } catch (e: Exception) {
            Log.e("PrivacyActivity", "readPublicFile: ", e)
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close()
                } catch (e: Exception) {
                    Log.e("PrivacyActivity", "readPublicFile: ", e)
                }
            }
        }
    }

    /**在公共目录下创建文件，并写入内容*/
    fun createPublicFile(view: View) {
        val contentResolver = contentResolver
        val contentValues = ContentValues()
        contentValues.put(MediaStore.Downloads.DISPLAY_NAME, "today")
        contentValues.put(MediaStore.Downloads.MIME_TYPE, "text/plain")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            contentValues.put(MediaStore.Downloads.RELATIVE_PATH, "Download/log")
        }
        val external: Uri = MediaStore.Downloads.EXTERNAL_CONTENT_URI
        val insertUri = contentResolver.insert(external, contentValues)
        var outputStream: OutputStream? = null
        val content = "2-hello world !!!! 这是一个有趣的测试，come on."
        try {
            if (insertUri != null) {
                outputStream = contentResolver.openOutputStream(insertUri)
                if (outputStream != null) {
                    outputStream.write(content.toByteArray(StandardCharsets.UTF_8))
                }
            }
        } catch (e: Exception) {
            Log.e("PrivacyActivity", "createPublicFile: ", e)
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close()
                } catch (e: Exception) {
                    Log.e("PrivacyActivity", "createPublicFile: ", e)
                }
            }
        }
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
        if (file.exists()) {
            Log.e("PrivacyActivity", "createOwnFile: 文件已经创建，删除重新创建")
            val result = file.delete()
            if (result) {
                createFile()
            }
        } else {
            createFile()
        }
    }

    private fun createFile() {
        val file = File(ownDirectory, ownFile)
        var outputStream: FileOutputStream? = null
        try {
            val result = file.createNewFile()
            if (result) {
                val content = "2-hello world !!!! 这是一个有趣的测试，come on."
                outputStream = FileOutputStream(file)
                for (i in 0..50) {
                    outputStream.write(content.toByteArray(StandardCharsets.UTF_8))
                }
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
                    val result = ByteArray(128)
                    var length = 0
                    while (true) {
                        length = inputStream.read(result);
                        if (length == -1)
                            break
                        sb.append(String(result, 0, length, StandardCharsets.UTF_8))
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e("PrivacyActivity", "onActivityResult: ")
    }
}