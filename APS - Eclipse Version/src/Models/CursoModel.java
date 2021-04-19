package Models;

public class CursoModel {
    
    private String nomeCurso;
    private int anoCurso;
    private EnumTipoCurso nivel;
   
    public CursoModel(String aNomeCurso, int aAnoCurso, EnumTipoCurso aNivel)
    {
        this.nomeCurso = aNomeCurso;
        this.anoCurso = aAnoCurso;
        this.nivel = aNivel;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String aNomeCurso) {
        this.nomeCurso = aNomeCurso;
    }

    public int getAnoCurso() {
        return anoCurso;
    }

    public void setAnoCurso(int aAnoCurso) {
        this.anoCurso = aAnoCurso;
    }

    public EnumTipoCurso getNivel() {
        return nivel;
    }

    public void setNivel(EnumTipoCurso nivel) {
        this.nivel = nivel;
    }  
}
