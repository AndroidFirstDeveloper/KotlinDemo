package com.example.czl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.czl.component.Activity1
import com.example.czl.component.Fragment1Activity
import com.example.czl.component.Service1Activity
import com.example.czl.component.Service2Activity
import com.example.czl.privacy.PrivacyActivity
import com.example.czl.test.*
import kotlinx.coroutines.*
import java.io.File
import java.io.InputStream
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection

class MainActivity : AppCompatActivity() {
    /**非空属性必须在定义的时候初始化,kotlin提供了一种可以延迟初始化的方案,使用 lateinit*/
    private lateinit var detailContainer: LinearLayout
    private lateinit var activity_main_iv: ImageView
    private lateinit var activity_main_iv1: ImageView
    private lateinit var activity_main_iv2: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        detailContainer = findViewById<LinearLayout>(R.id.activity_main_detail_container)
        activity_main_iv = findViewById<ImageView>(R.id.activity_main_iv)
        activity_main_iv1 = findViewById<ImageView>(R.id.activity_main_iv1)
        activity_main_iv2 = findViewById<ImageView>(R.id.activity_main_iv2)
        var activity_main_btn = findViewById<Button>(R.id.activity_main_btn)
        activity_main_btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                println("测试对象表达式创建匿名内部类1")
            }
        })

        var activity_main_add_detail = findViewById<LinearLayout>(R.id.activity_main_add_detail)
        activity_main_add_detail.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                println("测试对象表达式创建匿名内部类2")
                //as运算符用于执行引用类型的显示类型转换，如果类型与指定类型相符，就会转换成功，如果类型不兼容，使用as?就会返回null
                TestRealUse.addDetailView(p0?.context, detailContainer)
            }
        })
    }

    fun testPrivacy(view: View) {
        val intent = Intent(MainActivity@ this, PrivacyActivity::class.java)
        startActivity(intent)
    }

    fun testAndroidQ() {
        val file = externalCacheDir
        val file2 = cacheDir
        var path = ""
        var path2 = ""
        if (file != null) {
            path = file.absolutePath
            println("应用外部专属缓存目录：" + path)
        }
        if (file2 != null) {
            path2 = file2.absolutePath
            println("应用内部专属缓存目录：" + path2)
        }
        //尝试在应用专属目录下创建一个日志文件夹
        if (path.isNotEmpty()) {
            val directory = path + File.separator + "log"
            val directoryFile = File(directory)
            if (directoryFile.exists()) {
                println("目录已存在：" + directoryFile.path)
                val txtFile = File(directoryFile, "today.txt")
                if (!txtFile.exists()) {
                    println("创建today.txt文件")
                    txtFile.createNewFile()
                } else {
                    println("today.txt文件已存在")
                }
            } else {
                println("创建目录：" + directoryFile.path)
                directoryFile.mkdirs()
            }
        }
        /*if (path2.isNotEmpty()) {
            val file = File(path2, "log")
            if (!file.exists()) {
                file.mkdirs()
            }
        }*/
    }

    fun start1(view: View) {
        testAndroidQ()
        val intent = Intent(MainActivity@ this, Service1Activity::class.java)
        startActivity(intent)
    }

    fun bind1(view: View) {
        val intent = Intent(MainActivity@ this, Service2Activity::class.java)
        startActivity(intent)
    }

    fun openFragment1(view: View) {
        val intent = Intent(MainActivity@ this, Fragment1Activity::class.java)
        startActivity(intent)
    }

    fun openActivity1(view: View) {
        val intent = Intent(MainActivity@ this, Activity1::class.java)
        startActivity(intent)
    }

    fun useCoroutines(view: View) {
        val testCoroutines = TestCoroutines()
//        testCoroutines.test1(activity_main_iv)
//        testCoroutines.test2()
//        testCoroutines.test3()
//        testCoroutines.test4(activity_main_iv1,activity_main_iv2)
//        testCoroutines.test5()
//        testCoroutines.test6()
//        testCoroutines.test7()
//        testCoroutines.test8()
//        testCoroutines.test9()
//        testCoroutines.test10()
//        testCoroutines.test11()
//        testCoroutines.test12()
//        testCoroutines.test13()
//        testCoroutines.test14()
//        testCoroutines.test15()
//        testCoroutines.test16()
//        testCoroutines.test17()
//        testCoroutines.test17_1()
//        testCoroutines.test18()
//        testCoroutines.test19()
//        testCoroutines.test20()
//        testCoroutines.test21()
//        testCoroutines.test22()
//        testCoroutines.test23()

        //流
        val testFlow = TestFlow()
//        testFlow.test1()
//        testFlow.test2()
//        testFlow.test3()
//        testFlow.test4()
//        testFlow.test5()
//        testFlow.test6()
//        testFlow.test7()
//        testFlow.test8()
//        testFlow.test9()
//        testFlow.test10()
//        testFlow.test11()
//        testFlow.test12()
//        testFlow.test13()
//        testFlow.test14()
//        testFlow.test15()
//        testFlow.test16()

//        val testGson=TestGson()
//        testGson.test1()

        val testAlgorithm = TestAlgorithm()
        /*testAlgorithm.test1()
        testAlgorithm.test2()
        testAlgorithm.test3()
        testAlgorithm.test4()
        testAlgorithm.test5()
        testAlgorithm.test6()
        testAlgorithm.test7()
        testAlgorithm.test8()
        testAlgorithm.test9()
        testAlgorithm.test10()
        testAlgorithm.test11()
        testAlgorithm.test12()
        testAlgorithm.test13()
        testAlgorithm.test14()
        testAlgorithm.test15()
        testAlgorithm.test15_1()
        testAlgorithm.test15_2()
        testAlgorithm.test16()
        testAlgorithm.test17()
        testAlgorithm.test18()
        testAlgorithm.test19()
        testAlgorithm.test20()
        testAlgorithm.test21()
        testAlgorithm.test22()*/
//        testAlgorithm.test23()
//        testAlgorithm.test24()
//        testAlgorithm.test25()
//        testAlgorithm.test26()
        testAlgorithm.test27()

//        val testTree=TestTree()
//        testTree.test()
    }

    fun openRVActivity(view: View) {
        val intent = Intent(MainActivity@ this, RVActivity::class.java)
        startActivity(intent)
    }

    public fun test(view: View) {
        testGrammer()
    }

    public fun typeTest(view: View) {
        val testParameterType = TestParameterType()
        testParameterType.test()
    }

    /**测试序列化对象传值*/
    public fun startSecond(view: View) {
        val child = Child(10, "CZL", 170, 8)
        val young = Young(20, "Zhou", "15222633502")
        val girl = Girl("Lili", 18, 98)
//        val intent = Intent(this, SecondActivity::class.java)//显示跳转页面方式1
        val intent = Intent(MainActivity@ this, SecondActivity::class.java)//显示跳转页面方式2
        intent.putExtra("data", child)
        intent.putExtra("data2", young)
        intent.putExtra("data3", girl)
        startActivity(intent)
    }

    fun testGrammer() {
        /*val baseGrammer = BaseGrammer()
        val result1 = baseGrammer.sum1(1, 2)
        println("sum1返回结果：$result1")
        val result2 = baseGrammer.sum2(1, 2)
        println("sum2返回结果：$result2")
        val result3 = baseGrammer.sum3(1, 2)
        println("sum3返回结果：$result3")
        baseGrammer.sum4(1, 2)
        baseGrammer.sum5(1, 2)
        baseGrammer.dynamicArgsMethod(1, 2, 3, 4, 5, 6)
        baseGrammer.lambdaMethod()
        baseGrammer.dynamicAndStaticVariable()
        baseGrammer.notNullDeal()
        val result4 = baseGrammer.judgeArgsType(1)
        println("类型Int返回结果：$result4")
        val result5 = baseGrammer.judgeArgsType("hello")
        println("类型String返回结果：$result5")
        val result6 = baseGrammer.judgeArgsType(this)
        println("类型Activity返回结果：$result6")
        baseGrammer.cycleSpaceUse()*/

        /*val numberType = NumberType()
        numberType.useNumber()
        numberType.compareNumber()
        numberType.numberChangeType()
        numberType.logicCalculate()
        numberType.arrayUse()
        numberType.stringUse()*/

        /*val conditionExpress = ConditionExpress()
        conditionExpress.ifMethod()
        conditionExpress.sectionMethod()
        conditionExpress.whenMethod()
        val result = conditionExpress.whenMethod2(3)
        println("result=$result")*/

        /*val cycleControl = CycleControl()
        cycleControl.forMethod()
        cycleControl.whileMethod()
        cycleControl.forContinueAndBreak()
        cycleControl.forCycleLabel()
        cycleControl.labelReturn1()
        cycleControl.labelReturn2()
        cycleControl.labelReturn3()
        cycleControl.labelReturn4()*/

        /*val classAndObject = ClassAndObject("Tom", 89, "北京市朝阳区解放路45号", "15222633503")
        println(classAndObject.name)
        println(classAndObject.score)
        println(classAndObject.address)
        println(classAndObject.phoneNumber)
        classAndObject.score = 80//只有在外部设置属性的值，才会调用设置器setter
        println(classAndObject.score)

        val innerClass = ClassAndObject.InnerClass(2.50f, "2021-01-08")
        println(innerClass.price)
        println(innerClass.date)*/

        val innerClass2 = ClassAndObject.InnerClass2("张三", 10);
        val innerClass3 = ClassAndObject.InnerClass3()
        //val innerClass4=ClassAndObject.InnerClass4()

        /*val expandTest = ExpandTest()
        expandTest.test1()
        expandTest.test2()
        expandTest.test4()
        expandTest.test5()
        expandTest.test6()
        expandTest.test7()

        val toast = Toast(this)
        toast.toast(this, "hello world", Toast.LENGTH_SHORT)
        recycleSource()*/

        /*val dataTest = DataTest()
        dataTest.test1()*/

        /*val arrayTest = ArrayTest()
        arrayTest.baseTypeArray()
        arrayTest.baseTypeFixSizeArray()
        arrayTest.allTypeArray()
        arrayTest.allTypeFixSizeArray()
        arrayTest.allTypeEmptyArray()
        arrayTest.traversalArrayElement()
        arrayTest.modifyArrayElement()
        arrayTest.traversalTwoArray()
        arrayTest.testMultiTypeArray()
        arrayTest.elementAdd()
        arrayTest.elementFill()*/
        /*val listTest=ListTest()
        listTest.testCreateIMMutableList();
        listTest.testCreateMutableList();
        listTest.testIMMutableAddElement();
        listTest.testMutableAddElement();
        listTest.testArrayAndListChange();
        listTest.testRealUse();
        listTest.testListSort()*/

        /*val listTest = ListTest();
        listTest.testListConductElement()
        listTest.testFilterListElements()
        listTest.testListMapConduct()
        listTest.testListFindElement()*/

        /*val enumTest=EnumTest()
        enumTest.testEnumUse()*/

        val testObjectExpress = TestObjectExpress();
        testObjectExpress.myTest()
    }

    fun load(view: View) {
//        loadServerData()
        val testRetrofit = TestRetrofit()
//        testRetrofit.test1()
//        testRetrofit.test2()
//        testRetrofit.test3()
//        testRetrofit.test4()
        testRetrofit.test5()
    }

    private fun loadServerData() {
        CoroutineScope(Dispatchers.Main).launch {
            val job = launch(Dispatchers.IO) {
//                while (isActive) {
                println("开始加载资源")
                val url = URL("http://219.141.190.225/api/configuration/all")
                val stringBuilder = StringBuilder()
                lateinit var inputStream: InputStream
                lateinit var connection: URLConnection

                try {
                    delay(200)
                    connection = url.openConnection()
                    connection.connectTimeout = 100000
                    connection.readTimeout = 100000
                    connection.connect()
                    inputStream = connection.inputStream
                    while (inputStream.read() != -1) {
                        val byteArray = ByteArray(2048)
                        inputStream.read(byteArray)
                        val string = String(byteArray)
                        stringBuilder.append(string)
                    }
                } catch (e: Exception) {
                    println("Caught $e")
                } finally {
                    println("关闭连接，释放资源")
                    inputStream?.close()
                }
                println(stringBuilder.toString())
//                }
            }
            delay(1000)
            println("main i am tired")
//            job.cancelAndJoin()
            job.cancel()
            println("main i am quit")
        }
    }
}