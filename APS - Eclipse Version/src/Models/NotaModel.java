package Models;

public class NotaModel {
    private double valor; 
    
    public NotaModel(double aValor){
        this.valor = aValor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if(valor < 0.0 && valor > 10.0)
        {
            throw new IllegalArgumentException("Valor inválido");
        }
        else{
              this.valor = valor;
        }      
    }
}
