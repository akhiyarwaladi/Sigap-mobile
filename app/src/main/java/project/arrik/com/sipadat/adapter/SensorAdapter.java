package project.arrik.com.sipadat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import project.arrik.com.sipadat.MainActivity;
import project.arrik.com.sipadat.R;
import project.arrik.com.sipadat.model.AllData;

/**
 * Created by ALDI on 23/07/2016.
 */
public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.ViewHolder> {
    private List<AllData> allDataArrayList;
    private static String today;
    private int stat;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nilai, waktu, no;
        public ViewHolder(View view) {
            super(view);
            no = (TextView) view.findViewById(R.id.no);
            nilai = (TextView) view.findViewById(R.id.nilai);
            waktu = (TextView) view.findViewById(R.id.waktuu);
        }
    }

    public SensorAdapter(int stat, List<AllData> moviesList) {
        this.allDataArrayList = moviesList;
        this.stat = stat;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sensor_data_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AllData allData = allDataArrayList.get(position);
        holder.no.setText(String.valueOf(position+1));
        if(stat == MainActivity.PH) holder.nilai.setText(allData.getOutput());
        else if (stat == MainActivity.SUHU) holder.nilai.setText(allData.getDoo());
        else if (stat == MainActivity.DOO) holder.nilai.setText(allData.getPh());
        else if (stat == MainActivity.HASIL) holder.nilai.setText(allData.getOutput());
        else holder.nilai.setText("");
        holder.waktu.setText(allData.getWaktu());
    }

    @Override
    public int getItemCount() {
        return allDataArrayList.size();
    }
}