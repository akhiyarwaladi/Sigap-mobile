package project.arrik.com.sipadat.adapter;

/**
 * Created by ALDI on 23/07/2016.
 */
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import project.arrik.com.sipadat.R;
import project.arrik.com.sipadat.model.AllData;

public class AllLogAdapter extends RecyclerView.Adapter<AllLogAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<AllData> allDataArrayList;
    private static String today;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView status, waktu, ph, doo, suhu;
        public RelativeLayout rl;

        public ViewHolder(View view) {
            super(view);
            status = (TextView) view.findViewById(R.id.status);
            waktu = (TextView) view.findViewById(R.id.waktu);
            ph = (TextView) view.findViewById(R.id.tv_ph);
            doo = (TextView) view.findViewById(R.id.tv_do);
            suhu = (TextView) view.findViewById(R.id.tv_suhu);
            rl = (RelativeLayout) view.findViewById(R.id.rl);
        }
    }

    public AllLogAdapter(Context mContext, ArrayList<AllData> allDataArrayList) {
        this.mContext = mContext;
        this.allDataArrayList = allDataArrayList;

        Calendar calendar = Calendar.getInstance();
        today = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_log_all_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AllData allData = allDataArrayList.get(position);
        holder.status.setText("Data ke-"+String.valueOf(position));
        holder.ph.setText(allData.getPh());
        holder.suhu.setText(allData.getSuhu());
        holder.doo.setText(allData.getDoo());
        holder.waktu.setText(getTimeStamp(allData.getWaktu()));
    }



    public static String getTimeStamp(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = "";

        today = today.length() < 2 ? "0" + today : today;

        try {
            Date date = format.parse(dateStr);
            SimpleDateFormat todayFormat = new SimpleDateFormat("dd");
            String dateToday = todayFormat.format(date);
            format = dateToday.equals(today) ? new SimpleDateFormat("hh:mm a") : new SimpleDateFormat("dd LLL, hh:mm a");
            String date1 = format.format(date);
            timestamp = date1.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timestamp;
    }

    @Override
    public int getItemCount() {
        return allDataArrayList.size();
    }
}
