package vida.donizete.com.br.graficosonline.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class Alert extends AppCompatActivity {

    private Context context;

    public Alert(Context context) {
        this.context = context;
    }

    public void longToast(String mensagem) {
        toast(mensagem, Toast.LENGTH_LONG);
    }

    public void shortToast(String mensagem) {
        toast(mensagem, Toast.LENGTH_SHORT);
    }

    public void longSnack(String mensagem) {
        snack(mensagem, Snackbar.LENGTH_LONG);
    }

    public void shortSnack(String mensagem) {
        snack(mensagem, Snackbar.LENGTH_SHORT);
    }

    public void indefiniteSnack(String mensagem) {
        snack(mensagem, Snackbar.LENGTH_INDEFINITE);
    }

    private void toast(String mensagem, int lenght) {
        Toast.makeText(context, mensagem, lenght).show();
    }

    private void snack(String mensagem, int lenght) {
        Snackbar.make(findViewById(android.R.id.content), mensagem, lenght).show();
    }
}
