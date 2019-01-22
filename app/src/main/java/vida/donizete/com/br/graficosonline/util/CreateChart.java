package vida.donizete.com.br.graficosonline.util;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.Toast;


import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import vida.donizete.com.br.graficosonline.R;
import vida.donizete.com.br.graficosonline.adapter.PieChartAdapter;
import vida.donizete.com.br.graficosonline.entities.DataPieChart;


public class CreateChart extends AppCompatActivity implements OnChartValueSelectedListener {

    DatabaseReference databaseReference;

    public CreateChart() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    protected void getValues() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                HashMap<String, Object> d = new HashMap<>();

                for (DataSnapshot snapOld : dataSnapshot.getChildren()) {
                    d.put(snapOld.getKey(), snapOld.getValue());
                }

                List<DataPieChart> dataPieChart = new ArrayList<>();
                dataPieChart.add(new DataPieChart(d, "Acumulado mês", new String[]{"C4", "C5", "C6", "C7", "C8"}, new String[]{"G4", "G5", "G6", "G7", "G8"}));
                dataPieChart.add(new DataPieChart(d, "Acumulado mês anterior", new String[]{"C15", "C16", "C17", "C18", "C19"}, new String[]{"E15", "E16", "E17", "E18", "E19"}));


                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(CreateChart.this));
                recyclerView.setAdapter(new PieChartAdapter(CreateChart.this, dataPieChart, CreateChart.this));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void createChart(HashMap<String, Object> dados, int id, String titulo, String[] categoriasArray, String[] valoresArray) {
        PieChart chart = findViewById(id);

        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5, 10, 5, 5);

        chart.setDragDecelerationFrictionCoef(0.95f);

        chart.setCenterText(titulo);

        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.WHITE);

        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);

        chart.setHoleRadius(58f);
        chart.setTransparentCircleRadius(61f);

        chart.setDrawCenterText(true);
        chart.setOnChartValueSelectedListener(this);

        chart.setRotationAngle(0);
        chart.setRotationEnabled(true);
        chart.setHighlightPerTapEnabled(true);

        chart.animateY(1400, Easing.EaseInOutQuad);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        chart.setEntryLabelColor(Color.WHITE);
        chart.setEntryLabelTextSize(12f);

        ArrayList<PieEntry> entries = new ArrayList<>();

        List<String> categorias = getArrayString(categoriasArray, dados);
        List<Float> dadosFloat = getArrayFloat(valoresArray, dados);

        for (int i = 0; i < categorias.size(); i++) {
            entries.add(new PieEntry(dadosFloat.get(i), categorias.get(i)));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Categorias");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        chart.setData(data);

        // undo all highlights
        chart.highlightValues(null);

        chart.invalidate();
    }

    private String gs(Object a) {
        return a.toString();
    }

    private Float gf(Object a) {
        return Float.parseFloat(a.toString());
    }

    private List<String> getArrayString(String[] array, HashMap<String, Object> dados) {
        List<String> lista = new ArrayList<>();

        for (String s : array) {
            lista.add(gs(dados.get(s)));
        }

        return lista;
    }

    private List<Float> getArrayFloat(String[] array, HashMap<String, Object> dados) {
        List<Float> lista = new ArrayList<>();

        for (String s : array) {
            lista.add(gf(dados.get(s)));
        }

        return lista;
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null)
            return;
        alert("R$ " + e.getY());
    }

    @Override
    public void onNothingSelected() {

    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}
