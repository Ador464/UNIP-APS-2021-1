package Models;

public class RendimentoModel {

    private AlunoModel aluno;
    private CursoModel curso;
    private NotasModel notas;
    private boolean aprovado;
    private NotaModel media;
    
    public RendimentoModel(AlunoModel aAluno, CursoModel aCurso, NotasModel aNotas){
        this.aluno = aAluno;
        this.curso = aCurso;
        this.notas = aNotas;
        atualizarMedia();
    } 

    public void setNotas(NotasModel aNota) {
        this.notas = aNota;
        atualizarMedia();
    }

    private double getNotaCorte() {
        if (getCurso().getNivel() == EnumTipoCurso.GRADUACAO) {
            return 7.0;
        } else {
            return 5.0;
        }
    }

    private void atualizarMedia() {
        double np1 = getNotas().getNp1().getValor();
        double np2 = getNotas().getNp2().getValor();
        double sub = getNotas().getSub().getValor();
        double exame = getNotas().getExame().getValor();
        if (sub > np1 || sub > np2) {
            if (np1 > np2) {
                np2 = sub;
            } else {
                np1 = sub;
            }
        }

        double mediaInicial = (np1 + np2) / 2.0;
        if (mediaInicial >= getNotaCorte()) {
            this.setMedia(new NotaModel(mediaInicial));
            setAprovado(true);
        } else {
            double mediaFinal = (mediaInicial + exame) / 2.0;
            if (mediaFinal >= 5.0) {
                this.setMedia(new NotaModel(mediaFinal));
                setAprovado(true);
            } else {
                this.setMedia(new NotaModel(mediaFinal));
                setAprovado(false);
            }
        }
    }

    public AlunoModel getAluno() {
        return aluno;
    }

    public void setAluno(AlunoModel aluno) {
        this.aluno = aluno;
        atualizarMedia();
    }

    public CursoModel getCurso() {
        return curso;
    }

    public void setCurso(CursoModel curso) {
        this.curso = curso;
        atualizarMedia();
    }

    public NotasModel getNotas() {
        return notas;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public NotaModel getMedia() {
        return media;
    }

    public void setMedia(NotaModel media) {
        this.media = media;
    }
}
