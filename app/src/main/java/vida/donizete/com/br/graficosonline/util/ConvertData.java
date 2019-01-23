package vida.donizete.com.br.graficosonline.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConvertData {

    private static ConvertData convertData;

    public static ConvertData getInstance() {
        if (convertData == null) {
            convertData = new ConvertData();
        }

        return convertData;
    }

    private ConvertData() {

    }

    private String convertToString(Object object) {
        return object.toString();
    }

    private Float convertToFloat(Object object) {
        return Float.parseFloat(object.toString());
    }

    public List<String> getListString(String[] array, HashMap<String, Object> dados) {
        List<String> lista = new ArrayList<>();

        for (String s : array) {
            lista.add(convertToString(dados.get(s)));
        }

        return lista;
    }

    public List<Float> getListFloat(String[] array, HashMap<String, Object> dados) {
        List<Float> lista = new ArrayList<>();

        for (String s : array) {
            lista.add(convertToFloat(dados.get(s)));
        }

        return lista;
    }

}
