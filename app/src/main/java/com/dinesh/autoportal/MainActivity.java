package com.dinesh.autoportal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.dinesh.autoportal.db.DBManager;
import com.dinesh.autoportal.db.DataBaseAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static android.util.Log.*;

public class MainActivity extends AppCompatActivity {


//
//    private Spinner city;
//    private EditText FirstName,MiddleName,LastName,Address,phone,email,pass,dob,Regdate;

    private ArrayList<String> name,emails,date_of_birth;
    private DataBaseAccess dataBaseAccess;
    Map<String,Integer> cities=new HashMap<String, Integer>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyler;

        cities.put("Ahmedabad",1);
        cities.put("Surat",2);
        cities.put("Vapi",3);
        cities.put("Rajkot",4);
        cities.put("Jamnagar",5);

//        city=(Spinner)findViewById(R.id.city);
        ArrayAdapter<String> city_list=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,new ArrayList<String>(cities.keySet()));
//        city.setAdapter(city_list);


        dataBaseAccess=DataBaseAccess.getInstance(getApplicationContext());
        dataBaseAccess.opendb();
        ListView data=(ListView)findViewById(R.id.data);

        List<String> users=dataBaseAccess.get_Users();

//        ArrayAdapter<String> user=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,city_list);


//        city.setAdapter(city_list);

        name=new ArrayList<String>();
        emails=new ArrayList<String>();
        date_of_birth=new ArrayList<String>();

        populate_list();
        Log.i("Names => ",name.toString());
        Log.i("Names => ",emails.toString());
        Log.i("Names => ",date_of_birth.toString());
        CustomAdapter customAdapter=new CustomAdapter(MainActivity.this,this,name,emails,date_of_birth);

        recyler= findViewById(R.id.recyler);
        recyler.setAdapter(customAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyler.setLayoutManager(llm);

        dataBaseAccess.close();

    }

    private void populate_list() {


        Cursor c=dataBaseAccess.populate_data();
        while(c.moveToNext()){

            String data="Name : "+c.getString(0)+" "+c.getString(1)+" "+c.getString(2);
            String e="Email-Id : "+c.getString(3);
            String d="DOB : "+c.getString(4);
            Log.i("Name",data);
            Log.i("Email",e);
            Log.i("DOB",d);
            this.name.add(data);
            this.emails.add(e);
            this.date_of_birth.add(d);

        }


    }

    /*public void register(View view){

        FirstName=(EditText)findViewById(R.id.FirstName);
        MiddleName=(EditText)findViewById(R.id.MiddleName);
        LastName=(EditText)findViewById(R.id.LastName);
        Address=(EditText)findViewById(R.id.Address);
        email=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.pass);
        phone=(EditText)findViewById(R.id.phone);
        dob=(EditText)findViewById(R.id.dob);
        Regdate=(EditText)findViewById(R.id.Regdate);

        DataBaseAccess dataBaseAccess=DataBaseAccess.getInstance(getApplicationContext());
        dataBaseAccess.opendb();

        String name=FirstName.getText().toString();
        String mname=MiddleName.getText().toString();
        String lname=LastName.getText().toString();
        String address=Address.getText().toString();
        String e=email.getText().toString();
        String password=pass.getText().toString();
        String ph=phone.getText().toString();
        String d=dob.getText().toString();
        String regd=Regdate.getText().toString();
        city=(Spinner)findViewById(R.id.city);

        Integer city_id=cities.get(city.getSelectedItem().toString());
        dataBaseAccess.reg_user(name,mname,lname,address,e,password,ph,d,regd,city_id,this);
        dataBaseAccess.close();





    }*/



}