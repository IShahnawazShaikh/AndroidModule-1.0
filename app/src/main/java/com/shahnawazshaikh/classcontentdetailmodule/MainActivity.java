package com.shahnawazshaikh.classcontentdetailmodule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.shahnawazshaikh.classcontentdetailmodule.adapter.CourseAdapter;
import com.shahnawazshaikh.classcontentdetailmodule.bean.Course;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    String classtype;
    List<Course> list;
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    private static String CLASS_URL="http://www.digitalcatnyx.store/api/class_detail.php";
    //private static String TEST_URL="https://amygdaloid-cleat.000webhostapp.com/digitalcatnyx/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner=findViewById(R.id.ClassType);
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(MainActivity.this,R.array.classtype,R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        list =new ArrayList<Course>();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                classtype=spinner.getSelectedItem().toString();
                if (!classtype.equals("Select Your Class")) {
                    classtype=spinner.getSelectedItem().toString();
                   // Toast.makeText(MainActivity.this, "" + classtype, Toast.LENGTH_SHORT).show();
                    fetchClassDetails();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void fetchClassDetails() {
        Toast.makeText(MainActivity.this, "method" , Toast.LENGTH_SHORT).show();

        StringRequest request = new StringRequest(Request.Method.POST,CLASS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            //JSONObject jsonObject = new JSONObject(response);

                            //Toast.makeText(getApplicationContext(), "" + jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            // progressBar.setVisibility(View.GONE);
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject detail = array.getJSONObject(i);
                                Toast.makeText(getApplicationContext(),""+detail.getString("vid"),Toast.LENGTH_LONG).show();
                                System.out.println("id="+detail.getString("vid"));
                                System.out.println("Name Of the Course="+detail.getString("vname"));
                                System.out.println("class="+detail.getString("vofclass"));
                                System.out.println("Description="+detail.getString("vdesp"));
                                System.out.println("Date="+detail.getString("vdate"));
                                System.out.println("Video Path="+detail.getString("vpath"));
                                System.out.println("Subject="+detail.getString("vsub"));
                                System.out.println("");

                                list.add(new Course(detail.getString("vid"),detail.getString("vname"),detail.getString("vofclass")
                                       ,detail.getString("vdesp"),detail.getString("vdate"),detail.getString("vsub"),detail.getString("vpath")));
                             }
                            CourseAdapter adapter1 = new CourseAdapter(MainActivity.this,list);
                            recyclerView.setAdapter(adapter1);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),"Login Error"+e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Login Error"+error.toString(),Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("classtype", classtype);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
      }
    }

