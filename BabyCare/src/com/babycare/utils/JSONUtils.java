package com.babycare.utils;

import generalplus.com.GPComAir5Lib.Person;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtils {
	
	public static String readLocalJson(InputStream inputStream){ 
	        String resultString=""; 
	        try { 
	            byte[] buffer=new byte[inputStream.available()]; 
	            inputStream.read(buffer); 
	            resultString=new String(buffer,"UTF-8"); 
	            inputStream.close();
	        } catch (Exception e) { 
	            // TODO: handle exception
	        	try {
					inputStream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	        } 
	        
	        return resultString; 
	}

	public static List<Person> JSONParse(InputStream inputStream, int index)
	{
		Person person = null; 
		List<Person>  persons = new ArrayList<Person>();
		String json = readLocalJson(inputStream);
		try {
			JSONArray jsonArray = new JSONArray(json);
			JSONObject obj = jsonArray.getJSONObject(index);
			jsonArray = obj.getJSONArray("data");
			
			for(int i=0; i<jsonArray.length(); i++)
			{
				obj = jsonArray.getJSONObject(i);
				person = new Person();
				person.setId(i);
				person.setOnenumber(obj.getInt("onenumber"));
				person.setTwonumber(obj.getInt("twonumber"));
				person.setName(obj.getString("name"));
				person.setMold(obj.getString("mold"));
				
				persons.add(person);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return persons;
	}
	
	 
	 

}
