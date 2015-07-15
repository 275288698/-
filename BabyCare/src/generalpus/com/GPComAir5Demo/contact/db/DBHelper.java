package generalpus.com.GPComAir5Demo.contact.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;

import generalplus.com.GPComAir5Lib.User;

public class DBHelper {
	
	public static final String DB_DBNAME="contact";
	
	public static final String DB_TABLENAME="user";
	
	public static final int VERSION = 4;
	
	public static SQLiteDatabase dbInstance; 
	
	private MyDBHelper myDBHelper;
	
	private StringBuffer tableCreate;
	
	private Context context;
	
	public DBHelper(Context context) {
		this.context = context;
	}
	
	public void openDatabase() {
		if(dbInstance == null) {
			myDBHelper = new MyDBHelper(context,DB_DBNAME,VERSION);
			dbInstance = myDBHelper.getWritableDatabase();
		}
	}
	/**
	 * �����ݿ������user������һ�����ݣ���ʧ�ܷ���-1
	 * @param user
	 * @return   ʧ�ܷ���-1
	 */
	public long insert(User user) {
		ContentValues values = new ContentValues();
		values.put("name", user.username);
		values.put("onenumber", user.onenumber);
		values.put("twonumber", user.twonumber);
		values.put("mold", user.mold);
		values.put("love", user.love);
		values.put("privacy", user.privacy);
		return dbInstance.insert(DB_TABLENAME, null, values);
	}
	
	/**
	 * ������ݿ������е��û�����ÿһ���û��ŵ�һ��map��ȥ��Ȼ���ٽ�map�ŵ�list����ȥ����
	 * @param privacy 
	 * @return list
	 */
	
	public ArrayList getAllUser(boolean privacy) {
		ArrayList list = new ArrayList();
		Cursor cursor = null;
		if(privacy) {
			cursor = dbInstance.query(DB_TABLENAME, 
					new String[]{"_id","name","mobilephone","officephone","familyphone","address","othercontact","email","position","company","zipcode","remark","imageid"}, 
					"privacy=1", 
					null, 
					null, 
					null, 
					null);
		} else {
			cursor = dbInstance.query(DB_TABLENAME, 
					new String[]{"_id","name","mobilephone","officephone","familyphone","address","othercontact","email","position","company","zipcode","remark","imageid"}, 
					"privacy=0",
					null, 
					null, 
					null, 
					null);
		}
		 
		
		while(cursor.moveToNext()) {
			HashMap item = new HashMap();
			item.put("_id", cursor.getInt(cursor.getColumnIndex("_id")));
			item.put("name", cursor.getString(cursor.getColumnIndex("name")));
			item.put("onenumber", cursor.getString(cursor.getColumnIndex("onenumber")));
			item.put("twonumber", cursor.getString(cursor.getColumnIndex("twonumber")));
			item.put("mold", cursor.getString(cursor.getColumnIndex("mold")));
			item.put("love", cursor.getString(cursor.getColumnIndex("love")));
			list.add(item);
		}
		
		return list;
	}

	
	public void modify(User user) {
		ContentValues values = new ContentValues();
		values.put("name", user.username);
		values.put("mobilephone", user.onenumber);
		values.put("officephone", user.twonumber);
		values.put("familyphone", user.mold);
		values.put("address", user.love);
	
		dbInstance.update(DB_TABLENAME, values, "_id=?", new String[]{String.valueOf(user._id)});
	}
	
	public void delete(int _id) {
		dbInstance.delete(DB_TABLENAME, "_id=?", new String[]{String.valueOf(_id)});
	}
	public void deleteAll(int privacy) {
		dbInstance.delete(DB_TABLENAME, "privacy=?", new String[]{String.valueOf(privacy)});
	}
	
	public int getTotalCount() {
		Cursor cursor = dbInstance.query(DB_TABLENAME, new String[]{"count(*)"}, null, null, null, null, null);
		cursor.moveToNext();
		return cursor.getInt(0);
	}
	
	
	public ArrayList getUsers(String condition, boolean privacy) {
		ArrayList list = new ArrayList();
		String strSelection = "";
		if(privacy) {
			strSelection = "and privacy = 1";
		} else {
			strSelection = "and privacy = 0";
		}
		String sql = "select * from " + DB_TABLENAME + " where 1=1 and (name like '%" + condition + "%' " +
				"or onenumber like '%" + condition + "%' or twonumber like '%" + condition + "%' " +
						"or mold like '%" + condition + "%')" + strSelection;
		Cursor cursor = dbInstance.rawQuery(sql, null);
		while(cursor.moveToNext()) {
			HashMap item = new HashMap();
			item.put("_id", cursor.getInt(cursor.getColumnIndex("_id")));
			item.put("name", cursor.getString(cursor.getColumnIndex("name")));
			item.put("onenumber", cursor.getString(cursor.getColumnIndex("onenumber")));
			item.put("twonumber", cursor.getString(cursor.getColumnIndex("twonumber")));
			item.put("mold", cursor.getString(cursor.getColumnIndex("mold")));
			item.put("love", cursor.getString(cursor.getColumnIndex("love")));
			list.add(item);
		}
		return list;
	}
	
	public void deleteMarked(ArrayList<Integer> deleteId) {
		StringBuffer  strDeleteId = new StringBuffer();
		strDeleteId.append("_id=");
		for(int i=0;i<deleteId.size();i++) {
			if(i!=deleteId.size()-1) {
				strDeleteId.append(deleteId.get(i) + " or _id=");
			} else {
				strDeleteId.append(deleteId.get(i));
			}
			
			
		}
		dbInstance.delete(DB_TABLENAME, strDeleteId.toString(), null);
		System.out.println(strDeleteId.toString());
		
	}
	
	public void backupData(boolean privacy) {
		StringBuffer sqlBackup = new StringBuffer();
		Cursor cursor = null;
		if(privacy) {
			cursor = dbInstance.query(DB_TABLENAME, 
					new String[]{"_id","name","onenumber","twonumber","mold","live","privacy"}, 
					"privacy=1", 
					null, 
					null, 
					null, 
					null);
		} else {
			cursor = dbInstance.query(DB_TABLENAME, 
					new String[]{"_id","name","onenumber","twonumber","mold","live","privacy"}, 
					"privacy=0",
					null, 
					null, 
					null, 
					null);
		}
		 
		
		while(cursor.moveToNext()) {
			sqlBackup.append("insert into " + DB_TABLENAME + "(name,onenumber,twonumber,mold,love,privacy)")
			.append(" values ('")
			.append(cursor.getString(cursor.getColumnIndex("name"))).append("','")
			.append(cursor.getString(cursor.getColumnIndex("onenumber"))).append("','")
			.append(cursor.getString(cursor.getColumnIndex("twonumber"))).append("','")
			.append(cursor.getString(cursor.getColumnIndex("mold"))).append("','")
			.append(cursor.getString(cursor.getColumnIndex("love"))).append("','")
			.append(cursor.getInt(cursor.getColumnIndex("privacy")))
			.append(");").append("\n");
		}
		saveDataToFile(sqlBackup.toString(),privacy);
		
	}

	
	private void saveDataToFile(String strData,boolean privacy) {
		String fileName = "";
		if(privacy) {
			fileName = "priv_data.bk";
		} else {
			fileName = "comm_data.bk";
		}
		try {
		String SDPATH = Environment.getExternalStorageDirectory() + "/";
		File fileParentPath = new File(SDPATH + "zpContactData/");
		fileParentPath.mkdirs();
		File file = new File(SDPATH + "zpContactData/" + fileName);
		System.out.println("the file previous path = " + file.getAbsolutePath());
		
		file.createNewFile();
		System.out.println("the file next path = " + file.getAbsolutePath());
		FileOutputStream fos = new FileOutputStream(file);
		
		fos.write(strData.getBytes());
		fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void restoreData(String fileName) {
		try {
		String SDPATH = Environment.getExternalStorageDirectory() + "/";
		File file = null;
		if(fileName.endsWith(".bk")) {
			file = new File(SDPATH + "zpContactData/"+ fileName);
		} else {
			file = new File(SDPATH + "zpContactData/"+ fileName + ".bk");
		}
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str = "";
		while((str=br.readLine())!=null) {
			System.out.println(str);
			dbInstance.execSQL(str);
		}
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean findFile(String fileName) {
		String SDPATH = Environment.getExternalStorageDirectory() + "/";
		File file = null;
		if(fileName.endsWith(".bk")) {
			file = new File(SDPATH + "zpContact/"+fileName);
		} else {
			file = new File(SDPATH + "zpContact/"+fileName + ".bk");
		}
		
		if(file.exists()) {
			return true;
		} else {
			return false;
		}
		
		
	}


	class MyDBHelper extends SQLiteOpenHelper {

		public MyDBHelper(Context context, String name,
				int version) {
			super(context, name, null, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			tableCreate = new StringBuffer();
			tableCreate.append("create table ")
					   .append(DB_TABLENAME)
					   .append(" (")
					   .append("_id integer primary key autoincrement,")
					   .append("name text,")
					   .append("onenumber text,")
					   .append("twonumber text,")
					   .append("mold text,")
					   .append("love text,")
					   .append("privacy int ")
					   .append(")");
			System.out.println(tableCreate.toString());
			db.execSQL(tableCreate.toString());
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			String sql = "drop table if exists " + DB_TABLENAME;
			db.execSQL(sql);
			myDBHelper.onCreate(db);
		}
		
	}


	


	
	
}