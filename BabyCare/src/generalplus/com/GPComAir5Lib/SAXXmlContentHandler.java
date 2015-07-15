package generalplus.com.GPComAir5Lib;


import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class SAXXmlContentHandler extends DefaultHandler {  
  
	    private ArrayList<Person> persons;  
	    private Person person;  
	    private String tagName; //��ǰ���  

	    public ArrayList<Person> getBooks() {  
	    	return persons;  
	        
	    }   
	    
	    @Override  
	    public void startDocument() throws SAXException {  
	        this.persons = new ArrayList<Person>();  
	        Log.e("SAXXmlContentHandler", "��ʼ����XML�ļ�");  
	    }  
	  
	    @Override  
	    public void startElement(String uri, String localName, String qName,  
	            Attributes attributes) throws SAXException {  
//	        Log.e("SAXXmlContentHandler", "��ȡ��ǩ");  
	        this.tagName = localName;
//	        Log.e("000", "000");
	        if(this.tagName.equals("person")){  
	            person = new Person(); 
//	            Log.e("111", "000");
	            person.setId(Integer.parseInt(attributes.getValue(0))); 
//	            person.setId(Integer.parseInt(attributes.getValue("", "id"))); 
	        } 
	        tagName = localName; 
	        
	    }  
	    
	    @Override  
	    public void characters(char[] ch, int start, int length)throws SAXException {  
//	        Log.e("SAXXmlContentHandler", "����tagName��ȡ��ǩ������");  
	        if (this.tagName != null) {  
	            String data = new String(ch, start, length);  
	            if (this.tagName.equals("name")) {	            	
	                this.person.setName(data);  
	            } 
	            if (this.tagName.equals("onenumber")) {  
	                this.person.setOnenumber(Integer.parseInt(data));  
	            }
	            if (this.tagName.equals("twonumber")) {  
	                this.person.setTwonumber(Integer.parseInt(data));  
	            }
	            if (this.tagName.equals("mold")) {  
	                this.person.setMold(data);  
	            }
	            if (this.tagName.equals("key")) {  
	                this.person.setKey(data);  
	            }
	        }  
	    } 
	    
	    @Override  
	    public void endElement(String uri, String localName, String qName)throws SAXException {  
	        if(localName.equals("person")){  
	            this.persons.add(person);
//	            System.out.println("����XML�ļ�һ���������");
	        }  
	        this.tagName = null;  
	    }  
	}  


