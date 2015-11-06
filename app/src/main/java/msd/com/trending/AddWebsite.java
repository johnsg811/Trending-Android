package msd.com.trending;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class AddWebsite extends AppCompatActivity {
    EditText WebsiteName, WebsiteURL;

    Context context = this;
    UserDb userDb;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_website);
        WebsiteName = (EditText) findViewById(R.id.webName);
        WebsiteURL = (EditText) findViewById(R.id.webUrl);

    }

    public void onClick(View v)
    {
        finish();
        overridePendingTransition(R.anim.leftopp_animation, R.anim.rightopp_animation);
    }


    public void addNewWebsite(View view)
    {
        String WebName = WebsiteName.getText().toString();
        String WebUrl = WebsiteURL.getText().toString();
        userDb = new UserDb(context);    //initalize userDb
        sqLiteDatabase = userDb.getWritableDatabase();  //initalize sqlitedatabase
        userDb.addWebsites(WebName, WebUrl, sqLiteDatabase);  //add one row of data to database
        Toast.makeText(getBaseContext(), "Data Svaed", Toast.LENGTH_LONG).show();
        userDb.close(); //close database
        Intent intent = new Intent(this, MainActivity.class);
        overridePendingTransition(R.anim.right_animation, R.anim.left_animation);
        this.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_website, menu);
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
