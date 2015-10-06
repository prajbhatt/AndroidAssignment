package gitassignment.com.mygitassignment;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends FragmentActivity {

    private ListView listView;
   private ArrayList<ResponseBean> responseBeans ;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         dialog = ProgressDialog.show(MainActivity.this, "",
                "Loading. Please wait...", true);


        listView = (ListView) findViewById(R.id.list);
        String url = "https://api.github.com/repos/tenderlove/rails/commits";

        StringRequest postRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                System.out.println("response:" + response);

                 responseBeans=getListFromResponse(response);

             if( responseBeans!=null && responseBeans.size()>0)
             {
                 ListAdapter listAdapter=new ListAdapter(MainActivity.this,responseBeans);
                 listView.setAdapter(listAdapter);
             }

             if(dialog!=null && dialog.isShowing())
             {
                 dialog.dismiss();
             }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ) {

        };


        Volley.newRequestQueue(this).add(postRequest);

    }

    ArrayList<ResponseBean> getListFromResponse(String response) {
        responseBeans = new ArrayList<ResponseBean>();
        ResponseBean responseBean;
        String name, date, message;

        try {
            JSONArray jsonArray = new JSONArray(response);


            if (jsonArray != null)
                for (int i = 0; i < jsonArray.length(); i++) {
                    responseBean = new ResponseBean();

                    if (jsonArray.get(i) != null) {
                        name =((JSONObject) ((JSONObject) ((JSONObject) jsonArray.get(i)).get("commit")).get("committer")).getString("name");
                        date = ((JSONObject) ((JSONObject) ((JSONObject) jsonArray.get(i)).get("commit")).get("committer")).getString("date");
                        message = ((JSONObject) ((JSONObject) jsonArray.get(i)).get("commit")).getString("message");

                        responseBean.setCommitterNAme(name);
                        responseBean.setDate(date);
                        responseBean.setMessage(message);
                    }
                    responseBeans.add(responseBean);
                }


        } catch (Exception e) {
            e.printStackTrace();

        }


        return responseBeans;
    }

    private void viewList() {


    }


}
