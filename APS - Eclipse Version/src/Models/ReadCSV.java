package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors


public class ReadCSV {
	
	private String fileName;
	private List<Object[]> dataframe = new ArrayList<Object[]>();
    private String path = System.getProperty("user.dir");
    private String[] header;
    
    /**
     * Construtor da classe
     * @param nome Nome do arquivo com a extens�o 
     */
    public ReadCSV(String nome) {
        
    	this.fileName = nome;
        this.path += "\\" + nome; // caminho do arquivo a partir da raiz
        
        if(!this.createNewCSV()) {        
	        try{
	            File file = new File(this.path);
	            Scanner scanner = new Scanner(file);
	            
	            this.header = scanner.nextLine().split(",");
	            while(scanner.hasNextLine()){                
	                String[] line = scanner.nextLine().split(",");
	                Object[] linha = new Object[line.length];
	                for(int i = 0; i < line.length; i++){
	                    try{
	                        Integer d = Integer.parseInt(line[i]);
	                        linha[i] = d;
	                    } catch(java.lang.NumberFormatException e){
	                        try{
	                            Double d = Double.parseDouble(line[i]);
	                            linha[i] = d;
	                        } catch(java.lang.NumberFormatException e1){
	                            linha[i] = line[i];
	                        }
	                    }
	                }
	                this.dataframe.add(linha);
	            }  
	            scanner.close();
	        } catch(java.io.FileNotFoundException e2) {
	            e2.printStackTrace();        
	        } catch(java.util.NoSuchElementException e2) {
	        	if(this.fileName == "Alunos.csv") {
	        		this.header = new String[] {"ID","Nome"};
	        	} else if (this.fileName == "Cursos.csv") {
	        		this.header = new String[] {"Nome","Tipo","Ano"};
	        	} else {
	        		this.header = new String[] {"RA","NP1","NP2","Sub","Exame"};
	        	}
	        }
        }
    }
    
    /**
     * Recebe uma List de vetores de Objetos e retorna ela em um Array Bidimensional de Objetos
     * @param con List que será convertida
     * @return a Array Bidimensional de Objetos que foi convertida da List
     */
    public static Object[][] toObject(List<Object[]> con){
    	try {
	    	int x = con.size();
	    	int y = con.get(0).length;
	    	Object[][] res = new Object[x][y];
	    	for(int i = 0; i < x; i++) {
	    		for(int k = 0; k < y; k++) {
	    			res[i][k] = con.get(i)[k];
	    		}
	    	}
	    	return res;
    	} catch(java.lang.IndexOutOfBoundsException e) {
    		Object[][] res = null;
        	return res;
    	}
    }
    
    /**
     * Recebe um objeto e retorna ele em forma de Integer, ou retorna null caso impossível
     * @param con objeto que será convertida
     * @return a Integer ou null
     */
    public static Integer toInt(Object con) {
    	try {
    		Integer res = Integer.valueOf(con.toString());
        	return res;
    	} catch(java.lang.NumberFormatException e) {
    		return null;
    	}
    }
    
    /**
     * Recebe uma string e retorna ela de volta, porem sem acentos ou cedilhas.
     * @param text texto que será refinado
     * @return a entrada porem sem a maioria dos caracteres especiais
     */
    public static String refineString(String text) {
    	String res;
    	
    	res = text;
    	res = res.toLowerCase();
    	res = res.replace("ç", "c");
    	res = res.replace("ã", "a");
    	res = res.replace("á", "a");
    	res = res.replace("à", "a");
    	res = res.replace("â", "a");
    	res = res.replace("ú", "u");
    	res = res.replace("ô", "o");
    	res = res.replace("ó", "o");
    	res = res.replace("é", "e");
    	res = res.replace("è", "e");
    	res = res.replace("ê", "e");
    	res = res.replace("í", "i");
    	res = res.replace("ì", "i");
    	
    	return res;
    }
    
    /**
     * Retorna o cabeçalho do CSV, das colunas especificadas
     * @param index o index da colunas a serem devolvidos 
     * @return Array de Objetos contendo o cabe�alho do csv
     */   
    public String[] getHeader(Integer[] index){
    	if (index != null) {
	        String[] res = new String[index.length];
	        
	        int count = -1;
	        for(int i : index){
	            count++;
	            res[count] = this.header[i];
	        }
	        return res;
    	} else {
    		return this.header;
    	}
    }

    /**
     * Substitui o cabeçalho antigo por um novo
     * @param header novo cabeçalho que substituir� o antigo 
     */   
    public void setHeader(String[] header){
        this.header = header;
    }
    
    /**
     * Retorna as colunas especificadas da tabela de dados
     * @param columnIDs array de integer com os index das colunas a serem retornadas (insira null para retornar todos)
     * @return um objecto bidimencional com as colunas e seus itens
     */
    public Object[][] getDataframe(Integer[] columnIDs){
    	if (columnIDs != null) {
    		Object[][] res = new Object[this.dataframe.size()][columnIDs.length];
	        
	        for(int i = 0; i < this.dataframe.size(); i++) {
	            int count = -1;
	            for(int k : columnIDs) {
	                count ++;
	                res[i][count] = this.dataframe.get(i)[k];
	            }
	        }
    	} else {
	        return ReadCSV.toObject(this.dataframe);
    	}
    	return null;
    }

    /**
     * Susbtitui a tabela de dados antiga por uma nova
     * @param dataframe a nova tabela de dados que substituir� a antiga
     * @return um objecto bidimencional com as colunas e seus itens
     */
    public void setDataframe(Object[][] dataframe){
    	this.dataframe.clear();
    	if(dataframe != null) {
	    	for(Object[] ln : dataframe) {
	    		this.dataframe.add(ln);
	    	}
    	}
    }
    
    /**
     * Altera um unico dado de uma posição específica para um valor recebido
     * @param column coluna em que o dado deve ser inserido
     * @param line linha em que o dado deve ser inserido
     * @param data dado que dever ser colocado no dataframe
     */
    public void setData(int column, int line, Object data){
    	Object[] ln = this.dataframe.get(line);
    	ln[column] = data;
        this.dataframe.set(line, ln);
    }
    
    /**
     * Salva e fecha o arquivo CSV
     */
    public void save() {
    	FileWriter table;
		try {
			table = new FileWriter(this.path);
        	String line = "";
        	for(Object k : this.header) {
        		line += k;
        		line += ",";
        	}
        	line = line.substring(0,line.length()-1) + "\n";
	        for(Object[] i : this.dataframe) {
	        	for(Object k : i) {
	        		line += k;
	        		line += ",";
	        	}
	        	line = line.substring(0,line.length()-1) + "\n";
	        }
	        line = line.substring(0,line.length()-1);
        	table.write(line);
	        table.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (java.lang.NullPointerException e) {
			if(this.fileName == "Alunos.csv") {
        		this.header = new String[] {"ID","Nome"};
        	} else if (this.fileName == "Cursos.csv") {
        		this.header = new String[] {"Nome","Tipo","Ano"};
        	} else {
        		this.header = new String[] {"RA","NP1","NP2","Sub","Exame"};
        	}
			this.save();
		}
    	
    }
    
    /**
     * Cria um novo arquivo .csv
     * @return True se o arquivo foi criado com sucesso e False se houve um erro ou o arquivo ja existe 
     */
    public boolean createNewCSV() {
    	boolean res = false;
    	try {
    		File file = new File(this.path);
    		if (file.createNewFile()) {
    			res = true;
    		}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return res;
    }
    
    
}