package msd.com.trending;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    UserDb userDb;
    Cursor cursor;
    ListAdapt listAdapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        registerForContextMenu(listView);
        listAdapt = new ListAdapt(getApplicationContext(), R.layout.list_layout);
        listView.setAdapter(listAdapt);
        userDb = new UserDb(getApplicationContext());
        sqLiteDatabase = userDb.getReadableDatabase();
        cursor = userDb.viewList(sqLiteDatabase);
        
        if(cursor.moveToFirst())
        {
            do {

                String website, websiteUrl;
                website = cursor.getString(0);
                websiteUrl = cursor.getString(1);
                Data data = new Data(website, websiteUrl);
                listAdapt.add(data);


            }while(cursor.moveToNext());
        }


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_floatinig_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        switch (item.getItemId())
        {
            case R.id.id_remove:
                userDb = new UserDb(getApplicationContext());
                sqLiteDatabase = userDb.getReadableDatabase();
                userDb.deleteRow(NewsFeed.NewsFeedList.FeedName, sqLiteDatabase);
                Toast.makeText(getBaseContext(),"Website Removed ",Toast.LENGTH_LONG).show();
                /*listAdapt.remove(info.position);
                listAdapt.notifyDataSetChanged();
                return true;*/
            default:
                return super.onContextItemSelected(item);
        }

    }

    public void onClick(View v)
    {
        startActivity(new Intent(MainActivity.this, AddWebsite.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
