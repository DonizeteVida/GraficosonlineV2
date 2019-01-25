package vida.donizete.com.br.graficosonline.entities;

import java.util.HashMap;

public class DataPieChart {

    private HashMap<String, Object> data;
    private String titulo;
    private String[] categoriasArray;
    private String[] valoresArray;
    private Boolean[] options;

    public DataPieChart(HashMap<String, Object> data, String titulo, String[] categoriasArray, String[] valoresArray, Boolean[] options) {
        this.data = data;
        this.titulo = titulo;
        this.categoriasArray = categoriasArray;
        this.valoresArray = valoresArray;
        this.options = options;
    }

    public DataPieChart() {
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String[] getCategoriasArray() {
        return categoriasArray;
    }

    public void setCategoriasArray(String[] categoriasArray) {
        this.categoriasArray = categoriasArray;
    }

    public String[] getValoresArray() {
        return valoresArray;
    }

    public void setValoresArray(String[] valoresArray) {
        this.valoresArray = valoresArray;
    }

    public Boolean[] getOptions() {
        return options;
    }

    public void setOptions(Boolean[] options) {
        this.options = options;
    }
}
