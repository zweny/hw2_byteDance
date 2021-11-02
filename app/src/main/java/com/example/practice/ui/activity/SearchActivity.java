package com.example.practice.ui.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.R;
import com.example.practice.ui.adapter.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private EditText mEditView;
    private SearchAdapter mSearchAdapter = new SearchAdapter();

    private void initial_items(List<String> items){
        items.clear();
        for (int i = 1; i < 101; i++){
            items.add("这是第" + String.valueOf(i) + "行");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mRecyclerView = findViewById(R.id.rv);
        mEditView = findViewById(R.id.search_edittext);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mSearchAdapter);
        List<String> items = new ArrayList<>();
        initial_items(items);
        mSearchAdapter.notifyItems(items);
        mEditView.addTextChangedListener(new TextWatcher() {
            private List<String> items = new ArrayList<>();

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("Search_Text","changed from" + s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                initial_items(items);
                if(s.length()!=0){
                    List<String> tempItems = new ArrayList<>();
                    for (int i = 0; i < items.size(); ++i) {
                        if ((items.get(i)).contains(s.toString()))
                            tempItems.add(items.get(i));
                    }
                    items = tempItems;
                }
                mSearchAdapter.notifyItems(items);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("Search_Text","change to" + s);
            }
        });
    }
}