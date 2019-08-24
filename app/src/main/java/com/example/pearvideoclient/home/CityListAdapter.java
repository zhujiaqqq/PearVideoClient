package com.example.pearvideoclient.home;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.CityEntity;
import com.example.pearvideoclient.entity.CityListBean;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-24 22:52
 * @ClassName: CityListAdapter
 */
public class CityListAdapter extends BaseMultiItemQuickAdapter<CityEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public CityListAdapter(List<CityEntity> data) {
        super(data);
        addItemType(CityEntity.TYPE_CITY, R.layout.adapter_city_item);
        addItemType(CityEntity.TYPE_INDEX, R.layout.adapter_index_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, CityEntity item) {
        switch (helper.getItemViewType()) {
            case CityEntity.TYPE_CITY:
                cityTypeConvert(helper, item);
                break;
            case CityEntity.TYPE_INDEX:
                indexTypeConvert(helper, item);
                break;
            default:
                break;
        }
    }

    private void cityTypeConvert(BaseViewHolder helper, CityEntity item) {
        CityListBean.ChannelBean channelBean = item.getChannelBean();
        helper.setText(R.id.tv_city, channelBean.getName());
        helper.addOnClickListener(R.id.rl_parent);
    }

    private void indexTypeConvert(BaseViewHolder helper, CityEntity item) {
        String index = item.getIndex();
        helper.setText(R.id.tv_index, index);
    }

}
