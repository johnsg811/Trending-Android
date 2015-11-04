package msd.com.trending;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import msd.com.trending.NewsFeed;

/**
 * Created by Johns on 03/11/2015.
 */
public class UserDb extends SQLiteOpenHelper{

    private static final String DatabaseName = "NEWSFEEDDB";
    private static final int DatabaseVersion = 1;
    private static final String CREATE =
            "CREATE TABLE "+ NewsFeed.NewsFeedList.TableName+" ("+ NewsFeed.NewsFeedList.FeedName+" TEXT,"+ NewsFeed.NewsFeedList.FeedURL+" TEXT);";

    public UserDb(Context context)
    {
        super(context,DatabaseName,null,DatabaseVersion);   //when I create a object of this class, with a context argument, then it will create a database if the database is not exsisting and if the database exit , it simply opens it and if there is a change in the database version , android will then recreate the database
        Log.e("Database Operations", "Database created / opened...");
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        //create table
        db.execSQL(CREATE);
        Log.e("Database Operations","Table created...");

    }

    public void addWebsites(String webName, String url, SQLiteDatabase db)
    {

        //INSERT
        ContentValues contentValues = new ContentValues();
        contentValues.put(NewsFeed.NewsFeedList.FeedName, webName);
        contentValues.put(NewsFeed.NewsFeedList.FeedURL, url);
        // INSERT data into database
        db.insert(NewsFeed.NewsFeedList.TableName, null, contentValues);      //with 'null' vaalue, android will not insert a new row if the content value is empty
        Log.e("Database Operations", "One row inserted...");

    }

    public Cursor viewList(SQLiteDatabase db)
    {
        Cursor cursor;
        String[] projections = {NewsFeed.NewsFeedList.FeedName, NewsFeed.NewsFeedList.FeedURL};
        cursor = db.query(NewsFeed.NewsFeedList.TableName,projections,null,null,null,null,null);
        return cursor;
    }

    public Cursor getWebsiteInfo(String FeedName, SQLiteDatabase sqLiteDatabase)
    {
        String[] projections = {NewsFeed.NewsFeedList.FeedName, NewsFeed.NewsFeedList.FeedURL};
        String selection = NewsFeed.NewsFeedList.FeedName+" LIKE ?";
        String[] selection_arg = {FeedName};
        Cursor cursor = sqLiteDatabase.query(NewsFeed.NewsFeedList.TableName,projections,selection,selection_arg,null,null,null);
        return cursor;
    }

    public void deleteRow(String FeedName, SQLiteDatabase sqLiteDatabase)
    {
        String selection = NewsFeed.NewsFeedList.FeedName+" LIKE ?";
        String[] selection_arg = {FeedName};
        sqLiteDatabase.delete(NewsFeed.NewsFeedList.TableName,selection,selection_arg);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}
