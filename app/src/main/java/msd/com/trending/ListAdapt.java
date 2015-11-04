package msd.com.trending;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johns on 04/11/2015.
 */
public class ListAdapt extends ArrayAdapter {
    List list = new ArrayList();

    public ListAdapt(Context context, int resource){
        super(context, resource);
    }

    static class LayoutHandler
    {
        TextView webNames;
    }

    @Override
    public void add(Object object)
    {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutHandler layoutHandler;
        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.list_layout,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.webNames = (TextView)row.findViewById(R.id.webName);
            row.setTag(layoutHandler);
        }
        else
        {
            layoutHandler = (LayoutHandler)row.getTag();
        }
        Data data = (Data)this.getItem(position);
        layoutHandler.webNames.setText(data.getWebsite());
        return row;
    }
}
