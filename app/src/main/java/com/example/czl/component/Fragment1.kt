package com.example.czl.component

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.czl.R

class Fragment1 : Fragment() {

    //onAttach-onCreate-onCreateView-onViewCreated-onStart-onResume-onPause-onStop-onDestroyView-onDestroy-onDetach
    //onStart-onStartA-onResumeA-onResume-onPause-onPauseA-onStop-onStopA-onDestroyView-onDestroy-onDetach-onDestroyA
    //总结：fragment的生命周期方法，除了onResume方法fragment执行晚于activity，其它方法都是fragment执行早于activity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("Fragment1", "onAttach: ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("Fragment1", "onCreate: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("Fragment1", "onCreateView: ")
        return inflater.inflate(R.layout.fragment1_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("Fragment1", "onViewCreated: ")
    }

    override fun onStart() {
        super.onStart()
        Log.e("Fragment1", "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.e("Fragment1", "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.e("Fragment1", "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.e("Fragment1", "onStop: ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("Fragment1", "onDestroyView: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("Fragment1", "onDestroy: ")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("Fragment1", "onDetach: ")
    }
}