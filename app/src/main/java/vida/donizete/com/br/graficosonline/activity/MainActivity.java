package vida.donizete.com.br.graficosonline.activity;

import android.os.Bundle;
import android.widget.Toast;

import vida.donizete.com.br.graficosonline.R;
import vida.donizete.com.br.graficosonline.entities.DataPieChart;

public class MainActivity extends BaseActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpToolbar();
        setUpMenuIcon();
    }


}
