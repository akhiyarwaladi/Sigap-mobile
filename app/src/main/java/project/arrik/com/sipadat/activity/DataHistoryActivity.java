package project.arrik.com.sipadat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import project.arrik.com.sipadat.R;
import project.arrik.com.sipadat.app.Config;
import project.arrik.com.sipadat.app.EndPoint;
import project.arrik.com.sipadat.app.MyApplication;
import project.arrik.com.sipadat.fragment.DoFragment;
import project.arrik.com.sipadat.fragment.PhFragment;
import project.arrik.com.sipadat.fragment.SuhuFragment;
import project.arrik.com.sipadat.model.AllData;

public class DataHistoryActivity extends BaseActivity {
    private String TAG = DashboardActivity.class.getSimpleName();
    public static int PH = 1;
    public static int SUHU = 2;
    public static int DOO = 3;

    @Bind(R.id.tl_buddy_add)
    TabLayout tabLayout;
    @Bind(R.id.vp_buddy_add)
    ViewPager viewPager;

    private ViewPagerAdapter adapter;
    public static List<AllData> allDatas;
    String id_alat;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_history);

        toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        id_alat = intent.getStringExtra("id_alat");

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//        setupViewPager(viewPager);
//        tabLayout.setupWithViewPager(viewPager);

        allDatas = new ArrayList<AllData>();
        getData();

//        allDatas.add(new AllData("2", "50", "22", "normal", "12 january 2017","87"));
//        allDatas.add(new AllData("2", "50", "22", "normal", "12 january 2017","87"));
//        allDatas.add(new AllData("2", "50", "22", "normal", "12 january 2017","87"));
//        allDatas.add(new AllData("2", "50", "22", "normal", "12 january 2017","87"));
//        allDatas.add(new AllData("2", "50", "22", "normal", "12 january 2017","87"));
//        allDatas.add(new AllData("2", "50", "22", "normal", "12 january 2017","87"));
//        allDatas.add(new AllData("2", "50", "22", "normal", "12 january 2017","87"));
//        allDatas.add(new AllData("2", "50", "22", "normal", "12 january 2017","87"));
//        allDatas.add(new AllData("2", "50", "22", "normal", "12 january 2017","87"));
//        allDatas.add(new AllData("2", "50", "22", "normal", "12 january 2017","87"));
//        allDatas.add(new AllData("2", "50", "22", "normal", "12 january 2017","87"));
//        allDatas.add(new AllData("2", "50", "22", "normal", "12 january 2017","87"));
    }
    public void getData(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                EndPoint.URL_DATA+"/"+id_alat, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                try {
                    JSONObject obj = new JSONObject(response);

                    if (obj.getBoolean("error") == false) {
//                        Toast.makeText(DataHistoryActivity.this, "Data dapat"+response, Toast.LENGTH_SHORT).show();
                        JSONArray data = obj.getJSONArray("tasks");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject dataObj = (JSONObject) data.get(i);
                            Log.i("dataDapat",""+dataObj);
                            String status = dataObj.getString("status");
                            String suhu = dataObj.getString("suhu");
                            String ph = dataObj.getString("ph");
                            String doo = dataObj.getString("do");
                            String hasil = dataObj.getString("hasil");
                            String createdAt = dataObj.getString("create_at");
                            String createdAt2 = getTimeStampOnWithoutTime(createdAt);
                            AllData dataa = new AllData(ph,suhu,doo,status,createdAt2,hasil);
                            allDatas.add(dataa);
                            setupViewPager(viewPager);
                            tabLayout.setupWithViewPager(viewPager);

                            tabLayout.getTabAt(0).setIcon(R.drawable.thermometer);
                            tabLayout.getTabAt(1).setIcon(R.drawable.thermometer);
                            tabLayout.getTabAt(2).setIcon(R.drawable.thermometer);
                        }
                    } else {
                        // error in fetching data
                        Toast.makeText(DataHistoryActivity.this, "" + obj.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "json parsing error: " + e.getMessage());
                    Toast.makeText(DataHistoryActivity.this, "Json parse error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                Log.e(TAG, "Volley error: " + error.getMessage() + ", code: " + networkResponse);
                Toast.makeText(DataHistoryActivity.this, "Volley errror: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map headers = new HashMap();
                headers.put("Authorization", Config.USER_AUTHORIZATION);

                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
        //Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(stringRequest);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DoFragment(), "HPc");
        adapter.addFragment(new SuhuFragment(), "Uk");
        adapter.addFragment(new PhFragment(), "OpTime");
        viewPager.setAdapter(adapter);
    }

    public static String getTimeStampOnWithoutTime(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = "";
        try {
            Date date = format.parse(dateStr);

            format = new SimpleDateFormat("dd LLL, yyyy | HH:mm");
            String date1 = format.format(date);

            timestamp = date1.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }
        else if(id == R.id.action_logout){
            FirebaseAuth.getInstance().signOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
