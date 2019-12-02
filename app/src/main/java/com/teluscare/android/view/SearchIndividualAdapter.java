package com.teluscare.android.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.teluscare.android.R;
import com.teluscare.android.model.IndividualListResponseDataBean;

import java.util.ArrayList;
import java.util.List;

public class SearchIndividualAdapter extends BaseAdapter implements Filterable {

    public Context context;
    public List<IndividualListResponseDataBean> IndividualListResponseDataBeanArrayList;
    public List<IndividualListResponseDataBean> orig;

    public SearchIndividualAdapter(Context context, List<IndividualListResponseDataBean> IndividualListResponseDataBeanArrayList) {
        super();
        this.context = context;
        this.IndividualListResponseDataBeanArrayList = IndividualListResponseDataBeanArrayList;
    }


    public class EmployeeHolder
    {
        TextView name;
        TextView age;
    }

    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final List<IndividualListResponseDataBean> results = new ArrayList<IndividualListResponseDataBean>();
                if (orig == null)
                    orig = IndividualListResponseDataBeanArrayList;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final IndividualListResponseDataBean g : orig) {
                            if (g.getUsername().toLowerCase()
                                    .contains(constraint.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                IndividualListResponseDataBeanArrayList = (ArrayList<IndividualListResponseDataBean>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return IndividualListResponseDataBeanArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return IndividualListResponseDataBeanArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EmployeeHolder holder;
        if(convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(R.layout.listview, parent, false);
            holder=new EmployeeHolder();
            holder.name=(TextView) convertView.findViewById(R.id.txtName);
            holder.age=(TextView) convertView.findViewById(R.id.txtAge);
            convertView.setTag(holder);
        }
        else
        {
            holder=(EmployeeHolder) convertView.getTag();
        }

        holder.name.setText(IndividualListResponseDataBeanArrayList.get(position).getUsername());
        holder.age.setText(String.valueOf(IndividualListResponseDataBeanArrayList.get(position).getJobid()));

        return convertView;

    }

}
