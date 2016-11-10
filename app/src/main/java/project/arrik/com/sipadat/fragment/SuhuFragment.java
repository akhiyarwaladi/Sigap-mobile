package project.arrik.com.sipadat.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
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
 * Activities that contain this fragment must implement the
 * {@link SuhuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SuhuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SuhuFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static SensorAdapter mAdapter;
    private RecyclerView recyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View v;
    LineChart chartSuhu, chartPH, chartDO;

    private OnFragmentInteractionListener mListener;

    public SuhuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SuhuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SuhuFragment newInstance(String param1, String param2) {
        SuhuFragment fragment = new SuhuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_suhu, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_suhu);
        mAdapter = new SensorAdapter(DataHistoryActivity.SUHU, DataHistoryActivity.allDatas);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        setupChart();
        return v;
        // Inflate the layout for this fragment
    }

    private void setupChart(){
        // Setup chart suhu
        chartSuhu = (LineChart) v.findViewById(R.id.chart_suhu);
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
            float dooo = Float.parseFloat(dat.getDoo());
            entrySuhu.add(new Entry(dooo,i));
            labelSuhu.add(String.valueOf(i+1));
        }
//
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

        LineDataSet dataSetSuhu = new LineDataSet(entrySuhu, "1 <= x <= -1");
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
