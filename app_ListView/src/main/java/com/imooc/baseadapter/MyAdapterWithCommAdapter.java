/*
 * @Title MyAdapterWithCommViewHolder.java
 * @Copyright Copyright 2010-2015 Yann Software Co,.Ltd All Rights Reserved.
 * @Description��
 * @author Yann
 * @date 2015-8-5 ����10:03:45
 * @version 1.0
 */
package com.imooc.baseadapter;

import java.util.List;

import com.imooc.baseadapter.utils.CommonAdapter;
import com.imooc.baseadapter.utils.ViewHolder;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

/**
 * 类注释
 *
 * @author Yann
 * @date 2015-8-5 下午10:03:45
 */
public class MyAdapterWithCommAdapter extends CommonAdapter<Bean> {
    public MyAdapterWithCommAdapter(Context context, List<Bean> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    /**
     * 实现public abstract View getView(int position, View convertView, ViewGroup parent);
     * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
     */
/*    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent, R.layout.item, position);
        Bean bean = mDatas.get(position);
        
        ((TextView)holder.getView(R.id.tv_title)).setText(bean.getTitle());
        ((TextView)holder.getView(R.id.tv_desc)).setText(bean.getDesc());
        ((TextView)holder.getView(R.id.tv_time)).setText(bean.getTime());
        ((TextView)holder.getView(R.id.tv_phone)).setText(bean.getPhone());
        
        return holder.getConvertView();
    }
*/

    /**
     * 实现public abstract void convert(ViewHolder holder, T t);
     *
     * @see com.imooc.baseadapter.utils.CommonAdapter#convert(com.imooc.baseadapter.utils
     * .ViewHolder, java.lang.Object)
     */
    @Override
    public void convert(ViewHolder holder, final Bean bean) {
/*
        ((TextView)holder.getView(R.id.tv_title)).setText(bean.getTitle());
        ((TextView)holder.getView(R.id.tv_desc)).setText(bean.getDesc());
        ((TextView)holder.getView(R.id.tv_time)).setText(bean.getTime());
        ((TextView)holder.getView(R.id.tv_phone)).setText(bean.getPhone());
*/
        holder.setText(R.id.tv_title, bean.getTitle())
                .setText(R.id.tv_desc, bean.getDesc())
                .setText(R.id.tv_time, bean.getTime())
                .setText(R.id.tv_phone, bean.getPhone());

        final CheckBox cBox = (CheckBox) (holder.getView(R.id.cb));
        if (cBox != null) {
            cBox.setChecked(bean.isChecked());

            cBox.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    bean.setChecked(cBox.isChecked());
                }
            });
        }
    }
}
