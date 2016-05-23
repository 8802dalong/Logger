package com.stan.logger;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    private List<String> mStrList=new ArrayList<String>();
    private List<Integer> mIntList=new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addListData(R.string.showlog);
        addListData(R.string.writelog);
        ListAdapter listAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,mStrList);
        setListAdapter(listAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        int clickid=mIntList.get(position);
        switch(clickid){
            case R.string.showlog:
                break;
            case R.string.writelog:
                break;
        }
    }

    private void addListData(int strId){
        mStrList.add(getString(strId));
        mIntList.add(strId);
    }
}
