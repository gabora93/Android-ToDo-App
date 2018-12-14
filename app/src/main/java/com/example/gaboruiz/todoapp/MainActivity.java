package com.example.gaboruiz.todoapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.gaboruiz.todoapp.Class.item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    //private ArrayList<String> items;
   // private ArrayAdapter<String> itemsAdapter;
    //private ListView lvItems;

    ListView lstViewItems;
    Button btnAdd, btnEdit, btnDelete;
    EditText edtItem;
    item itemSelected=null;
    List<item> items = new ArrayList<item>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstViewItems = (ListView)findViewById(R.id.lstView);
        btnAdd = (Button)findViewById(R.id.btnAddItem);
        btnEdit = (Button)findViewById(R.id.btnEdit);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        edtItem = (EditText) findViewById(R.id.edtItem);

        //LOAD DATA WHEN APP OPENED
        new GetData().execute(Common.getAddressAPI());

        lstViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               itemSelected = items.get(position);
               //SET TEXT TO EDIT TEXT
                edtItem.setText(itemSelected.getItem());
            }
        });



        //ADD EVENT TO BUTTON

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new PostData(edtItem.getText().toString()).execute(Common.getAddressAPI());
            }
        });
    //BECAUSE THIS FUNCTION WE NEED PARAMETER ITEMSELECTED, SO WE NEED SET ITEMSELECTED
        //WHEN USER CLICK ON ITEM IN LISTVIEW
        btnEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new PutData(edtItem.getText().toString()).execute(Common.getAddressSingle(itemSelected));
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new DeleteData(itemSelected).execute(Common.getAddressSingle(itemSelected));

            }

        });



    }

    //FUNCTION PROCESS DATA
    class GetData extends AsyncTask<String, Void, String>{
        ProgressDialog pd = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            //Pre process
            pd.setTitle("Please wait...");
            pd.show();
        }

        @Override
        protected String doInBackground(String... params){
            // RUNNING PROCCESS..
            String stream = null;
            String urlString = params[0];

            HTTPDataHandler http = new HTTPDataHandler();
            stream = http.GetHTTPData(urlString);
            return stream;
        }

        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            //Done process
            //WE WILL USE GSON TO PARSE JSON TO CLASS
            Gson gson = new Gson();
            Type listType = new TypeToken<List<item>>(){}.getType();
            items=gson.fromJson(s,listType); // PARSE TO LIST
            CustomAdapter adapter = new CustomAdapter(getApplicationContext(),items); // CREATE ADAPTER
            lstViewItems.setAdapter(adapter); // SET ADAPTER TO LIST VIEW
            pd.dismiss();
        }
    }



    // FUNCTION TO ADD NEW ITEM
    class PostData extends AsyncTask<String,String,String> {
        ProgressDialog pd = new ProgressDialog(MainActivity.this);
        String item;

        public PostData(String item){
            this.item = item;
        }



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setTitle("Please wait...");
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String urlString = params[0];

            HTTPDataHandler hh = new HTTPDataHandler();
            String json = "{'item' :'" + item + "', 'id' : '"+ UUID.randomUUID().toString() +"'}";
            hh.PostHTTPData(urlString,json);
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            //REFRESH DATA
            new GetData().execute(Common.getAddressAPI());
            pd.dismiss();
        }
    }


    // FUNCTION TO EDIT ITEM
    class PutData extends AsyncTask<String,String,String> {
        ProgressDialog pd = new ProgressDialog(MainActivity.this);
        String item;

        public PutData(String item){
            this.item = item;
        }



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setTitle("Please wait...");
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String urlString = params[0];

            HTTPDataHandler hh = new HTTPDataHandler();
            String json = "{'item' :'" + item + "', 'id' : '"+ UUID.randomUUID().toString() +"'}";
            hh.PutHTTPData(urlString,json);
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            //REFRESH DATA
            new GetData().execute(Common.getAddressAPI());
            pd.dismiss();
        }
    }

    // FUNCTION TO DELETE ITEM
    class DeleteData extends AsyncTask<String,String,String> {
        ProgressDialog pd = new ProgressDialog(MainActivity.this);
        item item;

        public DeleteData(item item){
            this.item = item;
        }



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setTitle("Please wait...");
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String urlString = params[0];

            HTTPDataHandler hh = new HTTPDataHandler();
            String json="{\"item\":\""+item.getItem()+"\"}";
            hh.DeleteHTTPData(urlString,json);
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            //REFRESH DATA
            new GetData().execute(Common.getAddressAPI());
            pd.dismiss();
        }
    }

}
