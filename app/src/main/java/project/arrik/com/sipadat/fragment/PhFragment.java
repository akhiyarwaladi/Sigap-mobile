package project.arrik.com.sipadat.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import project.arrik.com.sipadat.R;
import project.arrik.com.sipadat.activity.DataHistoryActivity;
import project.arrik.com.sipadat.adapter.SensorAdapter;
import project.arrik.com.sipadat.model.AllData;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhFragment extends Fragment {

    public static SensorAdapter mAdapter;
    private RecyclerView recyclerView;
    View view;
    LineChart chartSuhu, chartPH, chartDO;


    public PhFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ph, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_ph);
        mAdapter = new SensorAdapter(DataHistoryActivity.PH, DataHistoryActivity.allDatas);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        setupChart();

        return view;
        // Inflate the layout for this fragment
    }

    private void setupChart(){
        // Setup chart suhu
        chartSuhu = (LineChart) view.findViewById(R.id.chart_ph);
//        chartPH = (LineChart) view.findViewById(R.id.chart_ph);
//        chartDO = (LineChart) view.findViewById(R.id.chart_do);
        chartSuhu.setDescription("");
//        chartPH.setDescription("");
//        chartDO.setDescription("");

        updateChart();

    }

    private void updateChart(){

        ArrayList<Entry> entrySuhu = new ArrayList<>();
        ArrayList<String> labelSuhu = new ArrayList<>();

        entrySuhu.clear();
        labelSuhu.clear();

        // Date formater
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z");
        Date parsed = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        dateFormat.setTimeZone(TimeZone.getDefault());

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        timeFormat.setTimeZone(TimeZone.getDefault());
        // end date formater

        for(int i = 0; i<DataHistoryActivity.allDatas.size();i++){
            AllData dat = DataHistoryActivity.allDatas.get(i);
            float dooo = Float.parseFloat(dat.getOutput());
            entrySuhu.add(new Entry(dooo,i));
            labelSuhu.add(String.valueOf(i+1));
        }
//        entrySuhu.add(new Entry(60,0));
//        entrySuhu.add(new Entry(15,1));
//        entrySuhu.add(new Entry(33,2));
//        entrySuhu.add(new Entry(90,3));
//        entrySuhu.add(new Entry(10,4));
//        entrySuhu.add(new Entry(70,5));
//        entrySuhu.add(new Entry(90,6));
//        entrySuhu.add(new Entry(10,7));
//        entrySuhu.add(new Entry(70,8));
//        labelSuhu.add("11:40");
//        labelSuhu.add("11:40");
//        labelSuhu.add("11:40");
//        labelSuhu.add("11:40");
//        labelSuhu.add("11:40");
//        labelSuhu.add("11:40");
//        labelSuhu.add("11:40");
//        labelSuhu.add("11:40");
//        labelSuhu.add("11:40");

        LineDataSet dataSetSuhu = new LineDataSet(entrySuhu, "Derajat keasaman");
        dataSetSuhu.setColor(Color.parseColor("#009688"));
        dataSetSuhu.setCircleColor(Color.parseColor("#ffcdd2"));
        dataSetSuhu.setCircleColorHole(Color.parseColor("#f44336"));

        LineData dataSuhu = new LineData(labelSuhu, dataSetSuhu);

        chartSuhu.setData(dataSuhu);

        // Update data
        chartSuhu.notifyDataSetChanged();

        // Animate
        chartSuhu.animateY(1000);

    }
}
