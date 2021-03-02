package com.example.czl.test;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


/**
 * 测试java中的泛型
 */
public class TestJavaGeneric {

    private final String TAG = "TestJavaGeneric";

    /**
     * 结论：java中所有使用通配符?修饰泛型类型元素的集合List都无法直接add元素(编译报错)，只能get元素.
     * 准确的说，上界通配符无法添加元素，获取的元素只能是上界类型的对象；下界通配符只能添加下界类型的对象，获取的元素只能是Object类型的对象.(总结：能get明确类型的就不能add，能add明确类型的就不能get)
     * 但是通配符的最大作用是协变(Covariance)、逆变(Contravariance)，也就是集合中元素类型是泛型的上界/下界的子类或父类时，可以直接进行引用赋值
     * ag List<? extends TextView> list=new ArrayList<Button>();
     * ag List<? super Button> list=new ArrayList<TextView>();
     */
    public void test1(Context context) {
        //1测试上界通配符
        List<Button> list = new ArrayList<>();
        list.add(new Button(context));
        //List<TextView> list2=list;//编译报错，java中的List是不支持协变(Covariance)的.
        List<? extends TextView> list3 = list;//编译通过，使用？(通配符)可以支持协变(Covariance).
        //list3.add(new Button(context));//编译报错，java中? extends 组合使用的集合只能get不能add，被称作生产者(Producer)。
        //list3.add(new TextView(context));//编译报错
        TextView textView = list3.get(0);//编译通过，因为list3中泛型类型的元素上限是TextView，所以获取的元素都可以这样写
        //2测试下界通配符
        List<? super Button> list4 = new ArrayList<>();
        list4.add(new Button(context));//能添下界类型的数据
        //list4.add(new TextView(context));//编译报错
        //list4.add(new View(context));//编译报错
        Object button = list4.get(0);
        //3
        Producer1<Integer> producer1 = new Producer1<>(1);
        Integer element = producer1.getElement();
        Log.e("TAG", "" + element);
        Consumer1<Integer> consumer1 = new Consumer1<>();
        consumer1.setElement(1);
        //4
        /**? 通配符表示泛型的类型是未知类型，extends 限制了未知类型的父类(类，接口)，所以右边类型只能是TextView的子类
         * producer6 一行编译报错，说明java中使用泛型类型的对象之间没有继承关系，为了解决这种写法java提供了?通配符，使得producer5
         * 这个对象能合法
         * */
        Producer2<? extends TextView> producer2 = new Producer2<>(new Button(context));
        Producer2<? extends TextView> producer3 = new Producer2<Button>(new Button(context));
        Producer2<? extends TextView> producer4 = new Producer2<EditText>(new EditText(context));
        Producer2<? extends TextView> producer5 = new Producer2<RadioButton>(new RadioButton(context));
        //Producer2<TextView> producer6 = new Producer2<Button>(new Button(context));
        //5
        /**consumer7这一行编译报错，相比producer6更好理解，第一这首先不符合多态，第二java中泛型类型的对象间没有继承关系*/
        Consumer2<? super Button> consumer2 = new Consumer2<TextView>();
        Consumer2<? super Button> consumer3 = new Consumer2<ViewTreeObserver.OnPreDrawListener>();
        Consumer2<? super Button> consumer4 = new Consumer2<View>();
        Consumer2<? super Button> consumer5 = new Consumer2<Drawable.Callback>();
        Consumer2<? super Button> consumer6 = new Consumer2<KeyEvent.Callback>();
        //Consumer2<Button> consumer7 = new Consumer2<TextView>();
    }


    static class Producer1<T> {
        private final T t;

        public Producer1(T t) {
            this.t = t;
        }

        public T getElement() {
            return this.t;
        }
    }

    static class Consumer1<T> {
        private T t;

        public Consumer1() {

        }

        public void setElement(T t) {
            this.t = t;
        }
    }

    static class Producer2<T> {
        private final T t;

        public Producer2(T t) {
            this.t = t;
        }

        public T getElement() {
            return this.t;
        }
    }

    static class Consumer2<T> {
        private T t;

        public Consumer2() {

        }

        public void setElement(T t) {
            this.t = t;
        }
    }

    static class AutoGeneric<T> {

        private Context context;

        public void test1(List<T> dest, List<T> src) {
            dest.addAll(src);
        }

        /**
         * 结论：java中一切关于泛型的对象之间直接进行引用赋值的操作都是不合法的，必须在泛型参数前使用通配符?才可以
         */
        public void test2(List<? extends Number> dest, List<? extends Number> src) {
            //dest.addAll(src);//编译报错，因为？表示泛型类型是未知类型，两个集合中的元素虽然都是Number的子类，但是不一定是相同类型的对象
            //List<Number> list = src;//编译报错，因为java的泛型类型不可变性(invariance). 虽然List<Number>跟List<? extends Number>两个对象中元素具有继承关系，但是不能吧两个集合看成有继承关系
            List<? extends Number> list2 = src;//协变(Covariance)
            //Producer1<TextView> producer1=new Producer1<Button>(new Button(context));//编译报错
        }
    }


    public void test2() {
        //1类型检查
        //通过list1和list2对比，可以确定泛型类型的检查是通过引用检查的，而不是引用指向的对象
        List<String> list1 = new ArrayList<>();
        list1.add("hello");
        list1.add("world");
        //list1.add(1);//编译报错
        List list2 = new ArrayList<String>();
        list2.add("hello");
        list2.add("world");
        list2.add(1);//编译通过
        //2通过反射调用list的add方法添加一个非指定泛型类型的元素，可以验证泛型的类型擦除确实存在（编译后生成的字节码文件确实也是这样的）
        List<Integer> list3 = new ArrayList<>();
        list3.add(1);
        //list3.add("abc");//编译报错
        try {
            list3.getClass().getMethod("add", Object.class).invoke(list3, "abc");//编译正常
        } catch (NoSuchMethodException e) {
            Log.e(TAG, "test2: ", e);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "test2: ", e);
        } catch (InvocationTargetException e) {
            Log.e(TAG, "test2: ", e);
        }
        //3泛型的类型擦除机制会将编译后的泛型类型全部替换为Object类型，但是我们获取数据的时候为什么可以直接获取到泛型类型的对象，而不是Object类型的对象呢？
        //这是因为在返回数据的时候代码会自动进行强制转换，而被转换的类型就是泛型类型，但是泛型不是已经被替换为Object了吗？确实也是这样的，但是编译的时候会将泛型
        //变量放到字节码中
        List<String> list4 = new ArrayList<>();
        list4.add("hello");
        String element1 = list4.get(0);//不需要显示的进行类型转换
    }
}
