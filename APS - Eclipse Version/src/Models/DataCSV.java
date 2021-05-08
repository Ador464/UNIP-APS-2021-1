package Models;

import java.util.ArrayList;
import java.util.List;

public class DataCSV {
	
	private ReadCSV csv_alunos = new ReadCSV("Alunos.csv");
	private ReadCSV csv_cursos = new ReadCSV("Cursos.csv");
	private List<ReadCSV> csv_modal = new ArrayList<ReadCSV>();

	private List<Object[]> AlunosTable = new ArrayList<Object[]>();
	private List<Object[]> CursosTable = new ArrayList<Object[]>();
	private List<List<Object[]>> ModalTable = new ArrayList<List<Object[]>>();
	
	public DataCSV(){
    	loadCSV();
    }
	
	public void loadCSV(){
    	for(Object[] i : csv_alunos.getDataframe(null)) {
    		this.AlunosTable.add(i);
    	}
    	for(Object[] i : csv_cursos.getDataframe(null)) {
    		this.CursosTable.add(i);
    	}
    	for(Object[] i : CursosTable) {
    		String nta = i[0].toString() + "_" + i[1].toString() + "_" + i[2].toString(); // nta = nome_tipo_ano
    		nta = nta.replace(" ", "");
    	    nta = ReadCSV.refineString(nta);
    	    this.csv_modal.add(new ReadCSV(nta + ".csv"));
    	}
    	for(ReadCSV csv : csv_modal) {
    		List<Object[]> tmp = new ArrayList<Object[]>();
    		if(csv.getDataframe(null) != null) {
	    		for(Object[] i : csv.getDataframe(null)) {
	    			tmp.add(i);
	    		}
    		}
    		this.ModalTable.add(tmp);
    	}
    }
	
	public void reload() {
		this.csv_alunos = new ReadCSV("Alunos.csv");
		this.csv_cursos = new ReadCSV("Cursos.csv");
		this.csv_modal = new ArrayList<ReadCSV>();
		
		this.AlunosTable = new ArrayList<Object[]>();
		this.CursosTable = new ArrayList<Object[]>();
		this.ModalTable = new ArrayList<List<Object[]>>();
		
		loadCSV();
    }
    
    public void saveAll() {
    	this.csv_alunos.setDataframe(ReadCSV.toObject(AlunosTable));
    	this.csv_cursos.setDataframe(ReadCSV.toObject(CursosTable));
    	for(int i = 0; i < this.ModalTable.size(); i++) {
    		List<Object[]> tmp = this.ModalTable.get(i);
    		this.csv_modal.get(i).setDataframe(ReadCSV.toObject(tmp));
    	}
    	this.csv_alunos.save();
    	this.csv_cursos.save();
    	for(ReadCSV csv : this.csv_modal) {
    		csv.save();
    	}
    }
    
    public Object[][][] getModalTable(){
    	Object[][][] res = new Object[this.ModalTable.size()][this.ModalTable.get(0).size()][5];
    	int i = 0;
    	for(List<Object[]> o : this.ModalTable) {
    		Object[][] r = ReadCSV.toObject(o);
    		res[i++] = r;
    	}
    	return res;
    }
    
    public Object[][] getAlunosTable(){
    	Object[][] res = ReadCSV.toObject(this.AlunosTable);
    	return res;
    }
    
    public Object[][] getCursosTable(){
    	Object[][] res = ReadCSV.toObject(this.CursosTable);
    	return res;
    }

    public Object[][] getModalByIndex(Integer index){
    	Object [][] res = ReadCSV.toObject(this.ModalTable.get(index));
    	return res;
    }
    
    public String[] getCursos() {
    	String[] res = new String[this.CursosTable.size()];
    	for(int i = 0; i < this.CursosTable.size(); i++) {
    		res[i] = this.CursosTable.get(i)[0].toString();
    	}
    	return res;
    }
    
    public String[] getAlunos() {
    	String[] res = new String[this.AlunosTable.size()];
    	for(int i = 0; i < this.AlunosTable.size(); i++) {
    		res[i] = this.AlunosTable.get(i)[1].toString();
    	}
    	return res;
    }
    
    public String getAlunoByRA(Integer ra) {
    	String res = null;
    	for(int i = 0; i < this.AlunosTable.size(); i++) {
    		if(this.AlunosTable.get(i)[0] == ra) {
    			res = this.AlunosTable.get(i)[1].toString();
    		}
    	}
    	return res;
    }
    
    public void addCurso(Object name, Object tipo, Object ano) {
    	Object[] novocurso = new Object[] {name, tipo, ano};
    	this.CursosTable.add(novocurso);
    }
    
    public void addAluno(Object id, Object nome) {
    	Object[] novoaluno = new Object[] {id, nome};
    	this.AlunosTable.add(novoaluno);
    }

    public void addRendimento(int aluno_index, int curso_index, Object np1, Object np2, Object sub, Object exame) {
    	Integer ra = Integer.valueOf(this.AlunosTable.get(aluno_index)[0].toString());
    	this.ModalTable.get(curso_index).add(new Object[] {ra, np1, np2, sub, exame});
    }
}
