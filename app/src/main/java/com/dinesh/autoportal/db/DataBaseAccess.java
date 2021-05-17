package com.dinesh.autoportal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DataBaseAccess instance;
    Cursor c=null;

    private DataBaseAccess(Context context){
       this.openHelper=new DBManager(context);
    }

    public static DataBaseAccess getInstance(Context context){

        if(instance==null){
            instance = new DataBaseAccess(context);
        }
        return instance;
    }

    public void opendb(){
        this.db=openHelper.getWritableDatabase();
    }

    public void close(){
        if(db!=null){
            this.db.close();
        }
    }

    public ArrayList<String> get_Users(){
        c=db.rawQuery("select f_name,m_name,l_name,address,phone,email_id from user",new String[]{});
        StringBuffer data_buffer=new StringBuffer();
        ArrayList<String> data_list=new ArrayList<String>();

        while(c.moveToNext()){

            String data="Name : "+c.getString(0)+" "+c.getString(1)+" "+c.getString(2);
//            String data1="\nAddress : "+c.getString(3)+"\n";
//            data_buffer.append(data1);
//            data_buffer.append("\n");
//            data_buffer.append(data);
            data_list.add(data);

//            data.append(""+name+"\n\n\n\n");

        }
        return data_list;
    }

    public Cursor populate_data(){

        c=db.rawQuery("select f_name,m_name,l_name,email_id,dob from user",new String[]{});
        return c;


    }

    public void reg_user(String name,String mname,String lname,String Address,String email,String pass,String phone,String dob,String regdate,Integer c_id,Context context){

        SQLiteDatabase sqLiteDatabase=this.db;
        ContentValues c=new ContentValues();

        c.put("f_name",name);
        c.put("m_name",mname);
        c.put("l_name",lname);

        c.put("Address",Address);
        c.put("city_id",c_id);
        c.put("phone",Integer.valueOf(phone));

        c.put("email_id",email);
        c.put("password",pass);
        c.put("dob",dob);

        c.put("reg_date",regdate);

        long res=sqLiteDatabase.insert("user",null,c);

        if(res == -1){
            Toast.makeText(context,"Not Registered Successfully",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context,"Registered Successfully",Toast.LENGTH_LONG).show();
        }




    }
}
