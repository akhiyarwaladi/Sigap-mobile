package project.arrik.com.sipadat.fragment;

/**
 * Created by ALDI on 23/07/2016.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import project.arrik.com.sipadat.R;
import project.arrik.com.sipadat.adapter.AllLogAdapter;
import project.arrik.com.sipadat.app.EndPoint;
import project.arrik.com.sipadat.app.MyApplication;
import project.arrik.com.sipadat.helper.MyPreferenceManager;
import project.arrik.com.sipadat.model.AllData;

public class LogAllFragment extends Fragment {

    private String TAG = LogAllFragment.class.getSimpleName();
    private ListView lv;
    private AllLogAdapter mAdapter;
    private RecyclerView recyclerView;
    public LogAllFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_log_all, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        MyPreferenceManager.allDataArrayList = new ArrayList<>();
        mAdapter = new AllLogAdapter(getActivity(),MyPreferenceManager.allDataArrayList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        fetchChatRooms();

        return view;
    }

    private void fetchChatRooms() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                EndPoint.BASE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                try {
                    JSONObject obj = new JSONObject(response);

                    if (obj.getBoolean("error") == false) {
                        JSONArray data = obj.getJSONArray("tasks");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject dataObj = (JSONObject) data.get(i);
                            AllData ad = new AllData();
                            ad.setId(dataObj.getString("id"));
                            ad.setPh(dataObj.getString("sensor1"));
                            ad.setSuhu(dataObj.getString("sensor2"));
                            ad.setDoo(dataObj.getString("sensor3"));
                            ad.setOutput(dataObj.getString("output"));
                            ad.setWaktu(dataObj.getString("createdAt"));
                            ad.setStatus(dataObj.getString("status"));

                            if(dataObj.getString("status").equals("0")){
                                MyPreferenceManager.allDataArrayList.add(ad);
                            } else {
                                MyPreferenceManager.warnDataArrayList.add(ad);
                            }
                        }
                    } else {
                        // error in fetching data
                        Toast.makeText(getActivity(), "" + obj.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "json parsing error: " + e.getMessage());
                    Toast.makeText(getActivity(), "Json parse error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
                mAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                Log.e(TAG, "Volley error: " + error.getMessage() + ", code: " + networkResponse);
                Toast.makeText(getActivity(), "Volley errror: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map headers = new HashMap();
                headers.put("Authorization", "746be99d4ef39262e059f3afb7a9cb4f");

                return headers;
            }
        };
        //Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(stringRequest);
    }
}