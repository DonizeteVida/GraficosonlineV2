package vida.donizete.com.br.graficosonline.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.List;

import vida.donizete.com.br.graficosonline.R;
import vida.donizete.com.br.graficosonline._interface.PieChartAdapterInterface;
import vida.donizete.com.br.graficosonline.create_chart.CreateChart;
import vida.donizete.com.br.graficosonline.entities.DataPieChart;

public class PieChartAdapter extends RecyclerView.Adapter<PieChartAdapter.PieChartHolder> {

    private Context context;
    private List<DataPieChart> dataPieCharts;
    private CreateChart createChart;
    private OnChartValueSelectedListener onChartValueSelectedListener;

    public PieChartAdapter(Context context, List<DataPieChart> dataPieCharts, OnChartValueSelectedListener onChartValueSelectedListener) {
        this.context = context;
        this.dataPieCharts = dataPieCharts;
        this.onChartValueSelectedListener = onChartValueSelectedListener;
        createChart = new CreateChart();
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
        PieChart pieChart = viewHolder.pieChart;

        createChart.createPieChart(dataPieChart.getData(), dataPieChart.getTitulo(), dataPieChart.getCategoriasArray(), dataPieChart.getValoresArray(), pieChart, onChartValueSelectedListener, dataPieChart.getOptions());
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


}
