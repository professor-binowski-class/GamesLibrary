package com.example.mockuppage;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity implements ExampleAdapter.OnNoteListener {
    private static final String TAG = "GameActivityExample";
    private ExampleAdapter adapter;
    private List<ExampleItem> exampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_and_filter);
        fillExampleList();
        setUpRecyclerView();
        TextView filterDialogTv = findViewById(R.id.filterDialogTv);
        filterDialogTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewDialog alert = new ViewDialog(Search.this);
                alert.show();
            }
        });
    }

    private void fillExampleList() {
        exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.ic_android, "Doom", "First-Person-Shooter",0));
        exampleList.add(new ExampleItem(R.drawable.ic_assessment, "Donkey Kong", "Action/Adventure", 1));
        exampleList.add(new ExampleItem(R.drawable.ic_cloud_upload, "Mortal Kombat", "Fighting", 2));
        exampleList.add(new ExampleItem(R.drawable.ic_android, "Resident Evil", "Survival", 3));
        exampleList.add(new ExampleItem(R.drawable.ic_assessment, "Super Mario", "Adventure", 4));
        exampleList.add(new ExampleItem(R.drawable.ic_cloud_upload, "League of Legends", "MOBA",5));
        exampleList.add(new ExampleItem(R.drawable.ic_android, "Rogue Legacy", "Roguelike", 6));
        exampleList.add(new ExampleItem(R.drawable.ic_assessment, "Garry's Mod", "Sandbox", 7));
        exampleList.add(new ExampleItem(R.drawable.ic_cloud_upload, "Age of Empires", "RTS",8));
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new ExampleAdapter(exampleList, this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public void onNoteClick(int position) { //onClick triggered, method runs
        Log.d(TAG, "onClick: clicked." + position);
        Intent intent = new Intent(Search.this, GameActivityExample.class);
        startActivity(intent);
    }

    /**@Override
    public void onClick(int position) { //onClick triggered, method runs
    Log.d(TAG, "onClick: clicked." + position);

    Intent intent = new Intent(Search.this, GameActivityExample.class);
    //intent.putExtra("game_object", (Parcelable) ExampleList.get(position));

    startActivity(intent);
    }**/
}
