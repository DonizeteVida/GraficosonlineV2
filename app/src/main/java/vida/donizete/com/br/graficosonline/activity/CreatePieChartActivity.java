package vida.donizete.com.br.graficosonline.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import vida.donizete.com.br.graficosonline.R;
import vida.donizete.com.br.graficosonline.adapter.PieChartAdapter;
import vida.donizete.com.br.graficosonline.entities.DataPieChart;
import vida.donizete.com.br.graficosonline.util.Alert;

public class CreatePieChartActivity extends BaseActivity implements OnChartValueSelectedListener {

    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private Boolean[] options;
    private List<DataPieChart> dataPieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pie_chart);
        dataPieChart = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemViewCacheSize(0);
        options = new Boolean[]{true};
        setUpToolbar();
        getValues();
    }

    protected void getValues() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                HashMap<String, Object> d = new HashMap<>();

                for (DataSnapshot snapOld : dataSnapshot.getChildren()) {
                    d.put(snapOld.getKey(), snapOld.getValue());
                }

                dataPieChart.clear();

                dataPieChart.add(new DataPieChart(d, "Acumulado mês R$", new String[]{"C4", "C5", "C6", "C7", "C8"}, new String[]{"G4", "G5", "G6", "G7", "G8"}, options));
                dataPieChart.add(new DataPieChart(d, "Acumulado mês anterior R$", new String[]{"C15", "C16", "C17", "C18", "C19"}, new String[]{"E15", "E16", "E17", "E18", "E19"}, options));
                dataPieChart.add(new DataPieChart(d, "Acumulado total do ano R$", new String[]{"C26", "C27", "C28", "C29", "C30"}, new String[]{"E26", "E27", "E28", "E29", "E30"}, options));

                dataPieChart.add(new DataPieChart(d, "Acumulado mês QTY", new String[]{"C4", "C5", "C6", "C7", "C8"}, new String[]{"F4", "F5", "F6", "F7", "F8"}, options));
                dataPieChart.add(new DataPieChart(d, "Acumulado mês anterior QTY", new String[]{"C15", "C16", "C17", "C18", "C19"}, new String[]{"D15", "D16", "D17", "D18", "D19"}, options));
                dataPieChart.add(new DataPieChart(d, "Acumulado total do ano QTY", new String[]{"C26", "C27", "C28", "C29", "C30"}, new String[]{"D26", "D27", "D28", "D29", "D30"}, options));

                populatePieChart(dataPieChart);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null)
            return;
        DecimalFormat df = new DecimalFormat("#,##0.##");
        new Alert(this).longToast("Valor: " + df.format(e.getY()));
    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    protected void setUpToolbar() {
        super.setUpToolbar();

        ActionBar ab = getSupportActionBar();

        if (ab != null) {
            ab.setTitle("Pie chart");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pie_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.change_values:
                options[0] = !options[0];
                populatePieChart(dataPieChart);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void populatePieChart(List<DataPieChart> dataPieChart) {
        recyclerView.setAdapter(new PieChartAdapter(CreatePieChartActivity.this, dataPieChart, CreatePieChartActivity.this));
    }
}
