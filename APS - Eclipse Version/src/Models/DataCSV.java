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
	
	/**
	 * Constructor da Classe
	 */
	public DataCSV(){
    	loadCSV();
    }
	    
    /**
     * Carrega todos os Arquivos CSV do Projeto e os armazena nas listas privadas desde objeto
	 */
	private void loadCSV(){
		if(csv_alunos.getDataframe(null)!=null) {
	    	for(Object[] i : csv_alunos.getDataframe(null)) {
	    		this.AlunosTable.add(i);
	    	}
		}
		if(csv_cursos.getDataframe(null)!=null) {
	    	for(Object[] i : csv_cursos.getDataframe(null)) {
	    		this.CursosTable.add(i);
	    	}
	    	for(Object[] i : CursosTable) {
	    		String nta = i[0].toString() + "_" + i[1].toString() + "_" + i[2].toString().replace(" ", ""); // nta = nome_tipo_ano
	    		nta = ReadCSV.refineString(nta);
	    	    this.csv_modal.add(new ReadCSV(nta + ".csv"));
	    	}
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
	
	/**
	 * Le novamente todos os dados do Arquivo CSV, e atualiza as Listas privadas com eles
	 */
	public void reload() {
		this.csv_alunos = new ReadCSV("Alunos.csv");
		this.csv_cursos = new ReadCSV("Cursos.csv");
		this.csv_modal = new ArrayList<ReadCSV>();
		
		this.AlunosTable = new ArrayList<Object[]>();
		this.CursosTable = new ArrayList<Object[]>();
		this.ModalTable = new ArrayList<List<Object[]>>();
		
		loadCSV();
    }
    
	/**
	 * Recebe todos os dados das listas privadas e Escreve elas em um arquvo .csv
	 */
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
    
    /**
     * Getter da lista de Rendimentos
     * @return Todos os cursos e alunos cadastrados neles ou um erro se não houver nenhum
     */
    public Object[][][] getModalTable(){
    	Object[][][] res = new Object[this.ModalTable.size()][this.ModalTable.get(0).size()][5];
    	int i = 0;
    	for(List<Object[]> o : this.ModalTable) {
    		Object[][] r = ReadCSV.toObject(o);
    		res[i++] = r;
    	}
    	return res;
    	
    }
    
    /**
     * Getter da lista de Alunos
     * @return Todos os alunos e Ids castrados ou null se não houver nenhum
     */
    public Object[][] getAlunosTable(){
    	Object[][] res = ReadCSV.toObject(this.AlunosTable);
    	return res;
    }
    
    /**
     * Getter da lista de Cursos
     * @return Todos os Cursos, Tipos e Anos castrados ou null se não houver nenhum
     */
    public Object[][] getCursosTable(){
    	Object[][] res = ReadCSV.toObject(this.CursosTable);
    	return res;
    }

    /**
     * Getter do rendimento de um curso específico
     * @param index o index da base de dados que o curso possui
     * @return Rendimento de todos os alunos do curso dado pelo index ou null se não houver nenhum
     */
    public Object[][] getModalByIndex(Integer index){
    	Object [][] res = ReadCSV.toObject(this.ModalTable.get(index));
    	return res;
    }
    
    /**
     * Getter do nome dos cursos
     * @return Nome de todos os cursos cadastrados
     */
    public String[] getCursos() {
    	String[] res = new String[this.CursosTable.size()];
    	for(int i = 0; i < this.CursosTable.size(); i++) {
    		res[i] = this.CursosTable.get(i)[0].toString();
    	}
    	return res;
    }
    
    /**
     * Getter do nome dos alunos
     * @return Nome de todos os alunos cadastrados
     */
    public String[] getAlunos() {
    	String[] res = new String[this.AlunosTable.size()];
    	for(int i = 0; i < this.AlunosTable.size(); i++) {
    		res[i] = this.AlunosTable.get(i)[1].toString();
    	}
    	return res;
    }
    
    /**
     * Getter do ID dos alunos
     * @return Todos os IDs cadastrados
     */
    public String[] getIdAlunos() {
    	String[] res = new String[this.AlunosTable.size()];
    	for(int i = 0; i < this.AlunosTable.size(); i++) {
    		res[i] = this.AlunosTable.get(i)[0].toString();
    	}
    	return res;
    }
    
    /**
     * Getter de um aluno específico por ID
     * @param ra o ID do aluno que será retornado
     * @return nome do aluno
     */
    public String getAlunoByRA(Integer ra) {
    	String res = null;
    	for(int i = 0; i < this.AlunosTable.size(); i++) {
    		if(this.AlunosTable.get(i)[0] == ra) {
    			res = this.AlunosTable.get(i)[1].toString();
    		}
    	}
    	return res;
    }
    
    /**
     * Verifica se existe algum curso cadastrado
     * @return true se existe, caso o contrário false
     */
    public boolean modalExists() {
    	if(this.ModalTable.size() < 1) {
    		return false;
    	} else {
    		return true;
    	}
    }
    
    /**
     * Adiciona um novo curso a lista privada ou gera um erro caso impossível
     * @param name Nome do curso
     * @param tipo Tipo do curso
     * @param ano Ano do curso
     * @throws Exception Caso exista um curso com esse Nome,Tipo,Ano cadastrado
     */
    public void addCurso(Object name, Object tipo, Object ano) throws Exception {
    	Object[][] cursosExistentes = this.getCursosTable();
    	Object[] novocurso = new Object[] {name, tipo, ano};
    	
    	if(cursosExistentes!=null) {
	    	for (Object[] curso: cursosExistentes) { 
	    		if(curso[0].toString().equals(novocurso[0]) &&
				   curso[1].toString().equals(novocurso[1]) &&
				   curso[2].toString().equals(novocurso[2]) ) {
					throw new Exception("Já existe um curso cadastrado com essas características!");
				}
			}
    	}
    	
    	this.CursosTable.add(novocurso);
    }
    
    /**
     * Adiciona um novo Aluno a lista privada ou gera um erro caso impossível
     * @param id ID do novo aluno
     * @param nome Nome do novo Aluno
     * @throws Exception Caso o ID cadastrado já exista
     */
    public void addAluno(Object id, Object nome) throws Exception {
    	
    	String[] idsExistentes = this.getIdAlunos();
    	if(idsExistentes!=null) {
	    	for (String idAluno : idsExistentes) {    		
	    		
				if(idAluno.contains(id.toString())) {
					throw new Exception("Este ID ja foi cadastrado!");
				}
			}
    	}
    	
    	Object[] novoaluno = new Object[] {id, nome};
    	this.AlunosTable.add(novoaluno);
    }

    /**
     * Adiciona um novo Rendimento a lista privada ou gera um erro caso impossível
     * @param aluno_index Index do aluno na lista de alunos cadastrados
     * @param curso_index Index do curso na lista de cursos cadastrados
     * @param np1 Nota da NP1 do aluno
     * @param np2 Nota da NP2 do aluno
     * @param sub Nota da Substitutiva do aluno
     * @param exame Nota do Exame do Aluno
     * @throws Exception Caso o rendimento já tenha sido cadastrado para esse aluno nesse curso específico
     */
    public void addRendimento(int aluno_index, int curso_index, Object np1, Object np2, Object sub, Object exame) throws Exception {
    	
    	Integer ra = Integer.valueOf(this.AlunosTable.get(aluno_index)[0].toString());
    	
    	if(this.getModalByIndex(curso_index)!=null) {
	    	for (Object[] itemColunaRa : this.getModalByIndex(curso_index)) {
	    		
	    		if(ra.compareTo((Integer) itemColunaRa[0]) == 0) {
	    			throw new Exception("Este aluno ja possui rendimento cadastrado!");
	    		}
			}
    	}
    	
    	this.ModalTable.get(curso_index).add(new Object[] {ra, np1, np2, sub, exame});
    }

    /**
     * Deleta um rendimento da base de dados
     * @param curso_index index index do curso do rendimento
     * @param list_index index do aluno do rendimento
     */
    public void deleteRendimento(int curso_index, int list_index) {
    	this.ModalTable.get(curso_index).remove(list_index);
    	this.saveAll();
    }
    
    public void deleteHistorico(String nome_curso, int aluno_index) {
    	int ra = Integer.valueOf(this.AlunosTable.get(aluno_index)[0].toString());
    	for(int i = 0; i < this.getCursos().length; i++) {
    		if(this.getCursos()[i] == nome_curso) {
    			for(int k = 0; k < this.ModalTable.get(i).size(); k++) {
    				int ra2 = Integer.valueOf(this.ModalTable.get(i).get(k)[0].toString());
    				if(ra == ra2) {
    					this.ModalTable.get(i).remove(k);
    				}
    			}
    		}
    	}
    	this.saveAll();
    }
  
    /**
     * Deleta um curso e todos os rendimentos associados o ele da base de dados
     * @param curso_index o index do curso na base de dados a ser deletado
     * @return nome do curso deletado
     */
    public String deleteCurso(int curso_index) {
    	final String name = this.getCursos()[curso_index];
    	this.csv_modal.get(curso_index).deleteCSV();
    	this.csv_modal.remove(curso_index);
    	this.CursosTable.remove(curso_index);
    	this.ModalTable.remove(curso_index);
    	this.saveAll();
    	return name;
    }

    /**
     * Deleta um aluno e todas os rendimentos associados a ele na base de dados
     * @param aluno_index index no aluno a ser removido
     * @return nome do aluno
     */
    public String deleteAluno(int aluno_index) {
    	String nome = this.getAlunos()[aluno_index];
    	int ra = Integer.valueOf(this.getAlunosTable()[aluno_index][0].toString());
    	if(this.modalExists()) {
	    	for(int i = 0; i < this.ModalTable.size(); i++) {
	    		for(int k = 0; k < this.ModalTable.get(i).size(); k++) {
	    			int ra2 = Integer.valueOf(this.ModalTable.get(i).get(k)[0].toString());
	    			if(ra2 == ra) {
	    				this.ModalTable.get(i).remove(k);
	    			}
	    		}
	    	}
    	}
    	this.AlunosTable.remove(aluno_index);
    	this.saveAll();
    	return nome;
    }
}
