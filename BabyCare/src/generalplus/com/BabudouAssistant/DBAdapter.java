package generalplus.com.BabudouAssistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	static final String KEY_ROWID = "_id";  
    static final String KEY_NAME = "name";  
    static final String KEY_EMAIL = "email";  
    static final String TAG = "DBAdapter";  
      
    static final String DATABASE_NAME = "MyDB1";  
    static final String DATABASE_TABLE = "contacts";  
    static final int DATABASE_VERSION = 1;  
      
    static final String DATABASE_CREATE =   
            "create table contacts( _id integer primary key autoincrement, " +   
            "name text not null, email text not null);";  
    final Context context;  
      
    DatabaseHelper DBHelper1;  
    SQLiteDatabase db2;  
      
    public DBAdapter(Context cxt)  
    {  
        this.context = cxt;  
        DBHelper1 = new DatabaseHelper(context);  
    }  
    
      
    private static class DatabaseHelper extends SQLiteOpenHelper  
    {  
  
        DatabaseHelper(Context context)  
        {  
            super(context, DATABASE_NAME, null, DATABASE_VERSION);  
        }  
        @Override  
        public void onCreate(SQLiteDatabase db1) {  
            // TODO Auto-generated method stub  
            try  
            {  
                db1.execSQL(DATABASE_CREATE);  
            }  
            catch(SQLException e)  
            {  
                e.printStackTrace();  
            }  
        }  
  
        @Override  
        public void onUpgrade(SQLiteDatabase db1, int oldVersion, int newVersion) {  
            // TODO Auto-generated method stub  
            Log.wtf(TAG, "Upgrading database from version "+ oldVersion + "to "+  
             newVersion + ", which will destroy all old data");  
            db1.execSQL("DROP TABLE IF EXISTS contacts");  
            onCreate(db1);  
        }  
    }  
      
    //open the database  
    public DBAdapter open() throws SQLException  
    {  
        db2 = DBHelper1.getWritableDatabase();  
        return this;  
    }  
    //close the database  
    public void close()  
    {  
        DBHelper1.close();  
    }  
      
    //insert a contact into the database  
    public long insertContact(String name, int email)  
    {  
        ContentValues initialValues = new ContentValues();  
        initialValues.put(KEY_NAME, name);  
        initialValues.put(KEY_EMAIL, email);  
        return db2.insert(DATABASE_TABLE, null, initialValues);  
    }  
    //delete a particular contact  
    public boolean deleteContact(long rowId)  
    {  
        return db2.delete(DATABASE_TABLE, KEY_ROWID + "=" +rowId, null) > 0;  
    }  
    //retreves all the contacts  
    public Cursor getAllContacts()  
    {  
        return db2.query(DATABASE_TABLE, new String[]{ KEY_ROWID,KEY_NAME,KEY_EMAIL}, null, null, null, null, null);  
    }  
    //retreves a particular contact  
    public Cursor getContact(long rowId) throws SQLException  
    {  
        Cursor mCursor =   
                db2.query(true, DATABASE_TABLE, new String[]{ KEY_ROWID,  
                         KEY_NAME, KEY_EMAIL}, KEY_ROWID + "=" + rowId, null, null, null, null, null);  
        if (mCursor != null)  
            mCursor.moveToFirst();  
        return mCursor;  
    }  
    //updates a contact  
    public boolean updateContact(long rowId, String name, int email)  
    {  
        ContentValues args = new ContentValues();  
        args.put(KEY_NAME, name);  
        args.put(KEY_EMAIL, email);  
        return db2.update(DATABASE_TABLE, args, KEY_ROWID + "=" +rowId, null) > 0;  
    }  
}  
