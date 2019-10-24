package com.example.pearvideoclient.channel

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.pearvideoclient.R
import com.example.pearvideoclient.channel.adapter.CategoryListAdapter
import com.example.pearvideoclient.entity.CategoryBean
import kotlinx.android.synthetic.main.activity_resort_category.*

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-19 09:57
 * @ClassName: ReSortCategoryActivity
 */
class ReSortCategoryActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resort_category)
        initData()
    }

    private fun initData() {
        val categoryList = intent.getParcelableArrayListExtra<CategoryBean.CategoryListBean>("categoryList")
        rv_category_list.layoutManager = GridLayoutManager(this, 5)
        val categoryListAdapter = CategoryListAdapter(this, categoryList)
        rv_category_list.adapter = categoryListAdapter

        val itemTouchHelper = ItemTouchHelper(IHCallback(categoryListAdapter))
        itemTouchHelper.attachToRecyclerView(rv_category_list)
        iv_close.setOnClickListener {
            val intent = Intent()
            intent.putParcelableArrayListExtra("categoryList", categoryList)
            setResult(0, intent)
            finish()
        }
    }
}
