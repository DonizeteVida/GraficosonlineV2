package vida.donizete.com.br.graficosonline.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import vida.donizete.com.br.graficosonline.R;
import vida.donizete.com.br.graficosonline.entities.DataPieChart;

public class PieChartAdapter extends RecyclerView.Adapter<PieChartAdapter.PieChartHolder> {

    private Context context;
    private List<DataPieChart> dataPieCharts;
    private OnChartValueSelectedListener onChartValueSelectedListener;

    public PieChartAdapter(Context context, List<DataPieChart> dataPieCharts, OnChartValueSelectedListener onChartValueSelectedListener) {
        this.context = context;
        this.dataPieCharts = dataPieCharts;
        this.onChartValueSelectedListener = onChartValueSelectedListener;
    }

    @NonNull
    @Override
    public PieChartAdapter.PieChartHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.pie_chart_adapter, viewGroup, false);
        return new PieChartHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PieChartAdapter.PieChartHolder viewHolder, int i) {
        DataPieChart dataPieChart = dataPieCharts.get(i);

        createChart(dataPieChart.getData(), dataPieChart.getTitulo(), dataPieChart.getCategoriasArray(), dataPieChart.getValoresArray(), viewHolder.pieChart);
    }

    @Override
    public int getItemCount() {
        return dataPieCharts != null ? dataPieCharts.size() : 0;
    }

    class PieChartHolder extends RecyclerView.ViewHolder {

        PieChart pieChart;

        public PieChartHolder(@NonNull View v) {
            super(v);

            pieChart = v.findViewById(R.id.pieChart);
        }
    }

    private void createChart(HashMap<String, Object> dados, String titulo, String[] categoriasArray, String[] valoresArray, PieChart chart) {

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
        chart.setOnChartValueSelectedListener(onChartValueSelectedListener);

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

        chart.setEntryLabelColor(Color.GRAY); // texto da categoria
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

        for (int c : ColorTemplate.MATERIAL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);//texto do centro
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
}
