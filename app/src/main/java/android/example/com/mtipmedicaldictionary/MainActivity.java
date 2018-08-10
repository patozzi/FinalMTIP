package android.example.com.mtipmedicaldictionary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.TermAdapterOnClickHandler {
    @Override
    public void onTermClick(ListItem item) {
        Context context = this;
        Intent intent = new Intent(context, ParentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("term", item.getTerm());
        bundle.putString("definition", item.getDefinition());
        bundle.putString("symptoms", item.getSymptoms());
        bundle.putString("treatment", item.getTreatment());
        bundle.putString("musicalTechniques", item.getMusical_techniques());
        intent.putExtra("termBundle", bundle);
//        intent.putExtra(Intent.EXTRA_TEXT, item);
        startActivity(intent);
    }

    private static final String URL_DATA = "https://raw.githubusercontent.com/patozzi/MTIPMedicalDictionary/master/Ryan%20MTIP%20JSON.json";

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private TextView textViewTerm;
    private TextView textViewDefinition;
    private TextView textViewSymptoms;
    private TextView textViewTreatment;
    private TextView textViewMusicalTechniques;

    private List<ListItem> listItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTerm = (TextView) findViewById(R.id.textViewTerm);
        textViewDefinition = (TextView) findViewById(R.id.textViewDefinition);
        textViewSymptoms = (TextView) findViewById(R.id.textViewSymptoms);
        textViewTreatment = (TextView) findViewById(R.id.textViewTreatment);
        textViewMusicalTechniques = (TextView) findViewById(R.id.textViewMusicalTechniques);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new MyAdapter(this);
        recyclerView.setAdapter(myAdapter);

        listItems = new ArrayList<>();

        loadRecyclerViewData();
    }

    public void onItemClick(AdapterView<?> parent, final View view,
                            int position, long id) {
        final ListItem item = (ListItem) parent.getItemAtPosition(position);
        view.animate().setDuration(2000).alpha(0)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
    }



    private void loadRecyclerViewData(){


        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("Medical Terminology");

                            for(int i = 0; i<array.length(); i++){
                                JSONObject o = array.getJSONObject(i);
                                ListItem item = new ListItem(
                                        o.getString("name"),
                                        o.getString("definition"),
                                        o.getString("symptoms"),
                                        o.getString("treatment"),
                                        o.getString("musical_techniques")
                                );
                                listItems.add(item);
                            }

                            myAdapter.setListItems(listItems);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
