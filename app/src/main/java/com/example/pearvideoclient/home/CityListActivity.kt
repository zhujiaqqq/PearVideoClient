package com.example.pearvideoclient.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pearvideoclient.R
import com.example.pearvideoclient.entity.CityEntity
import com.example.pearvideoclient.entity.CityListBean
import com.example.pearvideoclient.home.HomeFragment.Companion.RESULT_CODE
import kotlinx.android.synthetic.main.activity_city_list.*
import java.util.*


/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-23 22:36
 * @ClassName: CityListActivity
 */
class CityListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_list)
        initData()
    }

    private fun initData() {
        try {
            val bundle = intent.extras
            val cityList = bundle!!.getSerializable("cityList") as CityListBean
            val currentCity = bundle.getString("currentCity")
            val autoLocalChannelInfo = cityList.autoLocalChannelInfo
            val hotChannelList = cityList.hotChannelList
            val channelList = cityList.channelList

            val entities = ArrayList<CityEntity>()
            addAbleLocalCity(autoLocalChannelInfo, entities)
            addHotChannels(hotChannelList, entities)
            addChannels(channelList, entities)


            rv_city_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            val cityListAdapter = CityListAdapter(entities)
            cityListAdapter.setOnItemChildClickListener { _, view, position ->
                if (view.id == R.id.rl_parent) {
                    val entity = entities[position]
                    val channelBean = entity.channelBean
                    val intent = Intent()
                    intent.putExtra("city", channelBean)
                    setResult(RESULT_CODE, intent)
                    finish()
                }
            }
            rv_city_list.adapter = cityListAdapter

            tv_choose_city.text = getString(R.string.tv_current_city, currentCity)

            iv_back.setOnClickListener { finish() }

        } catch (e: Exception) {
            Log.e(TAG, "initData: " + e.message)
        }

    }

    private fun addChannels(channelList: List<CityListBean.ChannelBean>, entities: MutableList<CityEntity>) {
        var currentIndex: String? = null
        for (channel in channelList) {
            val temp = channel.nameSpell.substring(0, 1)
            if (temp != currentIndex) {
                val entity = CityEntity()
                entity.itemType = 1
                entity.index = temp
                entities.add(entity)
                currentIndex = temp
            }
            val cityEntity = CityEntity()
            cityEntity.itemType = 0
            cityEntity.channelBean = channel
            entities.add(cityEntity)
        }
    }

    private fun addHotChannels(hotChannelList: List<CityListBean.ChannelBean>, entities: MutableList<CityEntity>) {
        val indexEntity = CityEntity()
        indexEntity.itemType = 1
        indexEntity.index = "热门城市"
        entities.add(indexEntity)
        for (channelBean in hotChannelList) {
            val entity = CityEntity()
            entity.channelBean = channelBean
            entity.itemType = 0
            entities.add(entity)
        }
    }

    private fun addAbleLocalCity(autoLocalChannelInfo: CityListBean.ChannelBean?, entities: MutableList<CityEntity>) {
        if (autoLocalChannelInfo == null) {
            return
        }
        val indexEntity = CityEntity()
        indexEntity.itemType = 1
        indexEntity.index = "您当前可能在"
        entities.add(indexEntity)
        val cityEntity = CityEntity()
        cityEntity.itemType = 0
        cityEntity.channelBean = autoLocalChannelInfo
        entities.add(cityEntity)
    }

    companion object {
        private const val TAG = "CityListActivity"
    }
}
