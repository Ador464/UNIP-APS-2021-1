package Models;

public enum EnumTipoCurso {

    GRADUACAO(1), POS_GRADUACAO(2);

    private int valor;
        EnumTipoCurso(int valorOpcao){
        valor = valorOpcao;
}
    public int getValor(){
        return valor;
    }
}

