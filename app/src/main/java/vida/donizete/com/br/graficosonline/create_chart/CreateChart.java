package vida.donizete.com.br.graficosonline.create_chart;

import android.graphics.Color;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import vida.donizete.com.br.graficosonline.util.ConvertData;

public class CreateChart {

    private ConvertData convertData;

    public CreateChart() {
        convertData = ConvertData.getInstance();
    }

    public void createPieChart(HashMap<String, Object> dados, String titulo, String[] categoriasArray, String[] valoresArray, PieChart chart, OnChartValueSelectedListener onChartValueSelectedListener) {

        chart.setUsePercentValues(true);

        chart.getDescription().setEnabled(true);

        Description description = new Description();
        description.setText(titulo);
        description.setTextSize(15f);
        description.setPosition(600f, 180f);

        chart.setDescription(description);

        chart.setExtraOffsets(5, 10, 5, 5);

        chart.setDragDecelerationFrictionCoef(10f);

        chart.setDrawHoleEnabled(false);
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

        chart.setEntryLabelColor(Color.BLACK); // texto da categoria
        chart.setEntryLabelTextSize(10f);

        ArrayList<PieEntry> entries = new ArrayList<>();

        List<String> categorias = convertData.getListString(categoriasArray, dados);
        List<Float> dadosFloat = convertData.getListFloat(valoresArray, dados);

        for (int i = 0; i < categorias.size(); i++) {
            entries.add(new PieEntry(dadosFloat.get(i), categorias.get(i)));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Categorias");

        dataSet.setValueFormatter(new PercentFormatter());
        dataSet.setDrawIcons(true);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<>();

        int random = new Random().nextInt(6);


        switch (random) {
            case 0:
                for (int c : ColorTemplate.VORDIPLOM_COLORS)
                    colors.add(c);
                break;
            case 1:
                for (int c : ColorTemplate.JOYFUL_COLORS)
                    colors.add(c);
                break;
            case 2:
                for (int c : ColorTemplate.COLORFUL_COLORS)
                    colors.add(c);
                break;
            case 3:
                for (int c : ColorTemplate.LIBERTY_COLORS)
                    colors.add(c);
                break;
            case 4:
                for (int c : ColorTemplate.PASTEL_COLORS)
                    colors.add(c);
                break;
            case 5:
                for (int c : ColorTemplate.MATERIAL_COLORS)
                    colors.add(c);
                break;

            case 6:
                colors.add(ColorTemplate.getHoloBlue());
                break;
        }


        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(20f);
        data.setValueTextColor(Color.BLACK);//texto do centro
        chart.setData(data);

        // undo all highlights
        chart.highlightValues(null);
        chart.invalidate();

    }
}
