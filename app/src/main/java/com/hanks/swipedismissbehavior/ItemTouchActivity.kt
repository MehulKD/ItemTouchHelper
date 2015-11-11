package com.hanks.swipedismissbehavior

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.*

/**
 * Created by hanks on 15-11-11.
 */
class ItemTouchActivity : AppCompatActivity() {

    public var dataList: ArrayList<String> = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContentView(R.layout.activity_itemtouch)




    }


}

