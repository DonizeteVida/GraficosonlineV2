package vida.donizete.com.br.graficosonline.util;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;


import java.text.DecimalFormat;

public class PercentFormater implements IAxisValueFormatter {

    public DecimalFormat mFormat;

    public PercentFormater() {
        mFormat = new DecimalFormat("###,###,##0.0");
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mFormat.format(value) + "%";
    }
}
