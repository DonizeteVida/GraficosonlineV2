package vida.donizete.com.br.graficosonline.activity;

import android.os.Bundle;


import vida.donizete.com.br.graficosonline.R;
import vida.donizete.com.br.graficosonline.util.CreateChart;

public class MainActivity extends CreateChart {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getValues();
    }
}
