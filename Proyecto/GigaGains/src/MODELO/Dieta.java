package MODELO;

public class Dieta {
    private int id;
    private String tipo;
    private int proteinas;
    private int carbohidratos;
    private int calorias;

    public Dieta() {
    }

    public Dieta(int id, String tipo, int proteinas, int carbohidratos, int calorias) {
        this.id = id;
        this.tipo = tipo;
        this.proteinas = proteinas;
        this.carbohidratos = carbohidratos;
        this.calorias = calorias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getProteinas() {
        return proteinas;
    }

    public void setProteinas(int proteinas) {
        this.proteinas = proteinas;
    }

    public int getCarbohidratos() {
        return carbohidratos;
    }

    public void setCarbohidratos(int carbohidratos) {
        this.carbohidratos = carbohidratos;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }
    
}
