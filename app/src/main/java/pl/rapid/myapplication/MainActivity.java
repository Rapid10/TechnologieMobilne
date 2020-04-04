package pl.rapid.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

import pl.rapid.R;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> target;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        target = new ArrayList<>();
        String[] values = new String[]{
                "Pies", "Kot", "Koń", "Gołąb", "Kruk", "Dzik", "Karp", "Osioł", "Chomik", "Mysz",
                "Jeż", "Karaluch"
        };

        target.addAll(Arrays.asList(values));
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.target);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(this.adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            String newEntry = (String) extras.get("entry");
            target.add(newEntry);
            adapter.notifyDataSetChanged();
        }
    }

    public void onNewEntry(MenuItem mi) {
        Intent intention = new Intent(this, NewEntry.class);
        startActivityForResult(intention, 1);
    }

}
