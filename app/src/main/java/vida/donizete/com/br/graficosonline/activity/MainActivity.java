package vida.donizete.com.br.graficosonline.activity;

import android.os.Bundle;

import vida.donizete.com.br.graficosonline.R;

public class MainActivity extends BaseActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpToolbar();

        setUpMenuIcon();

    }


}
