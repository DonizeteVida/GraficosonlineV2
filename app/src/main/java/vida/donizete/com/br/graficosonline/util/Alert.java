package vida.donizete.com.br.graficosonline.util;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class Alert extends AppCompatActivity {

    private Context context;

    public Alert(Context context){
        this.context = context;
    }

    public void longToast(String mensagem){
        Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show();
    }

    public void shortToast(String mensagem){
        Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show();
    }
}
