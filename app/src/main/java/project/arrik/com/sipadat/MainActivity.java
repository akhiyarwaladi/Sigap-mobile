package project.arrik.com.sipadat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import project.arrik.com.sipadat.model.SensorModel;

public class MainActivity extends AppCompatActivity {
    public static int PH = 1;
    public static int SUHU = 2;
    public static int DOO = 3;
    public static int HASIL = 4;
    public static List<SensorModel> allDataArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //komentar hahah
    }
}
