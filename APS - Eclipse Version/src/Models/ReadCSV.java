package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors


public class ReadCSV {
    
    private List<Object[]> dataframe = new ArrayList<Object[]>();
    private String path = System.getProperty("user.dir");
    private String[] header;
    
    /**
     * Construtor da classe
     * @param nome Nome do arquivo com a extens�o 
     */
    public ReadCSV(String nome) {
        
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
	        }    
        }
    }
    
    /**
     * Recebe uma List de vetores de Objetos e retorna ela em um Array Bidimensional de Objetos
     * @param con List que ser� convertida
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
     * Retorna o cabe�alho do CSV, das colunas especificadas
     * @param index o index da colunas a serem devolvidos 
     * @return Array de Objetos contendo o cabe�alho do csv
     */   
    public String[] getHeader(int[] index){
        String[] res = new String[index.length];
        
        int count = -1;
        for(int i : index){
            count++;
            res[count] = this.header[i];
        }
        return res;
    }

    /**
     * Substitui o cabe�alho antigo por um novo
     * @param header novo cabe�alho que substituir� o antigo 
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
    	for(Object[] ln : dataframe) {
    		this.dataframe.add(ln);
    	}
    	
    }
    
    /**
     * Altera um unico dado de uma posi��o espec�fica para um valor recebido
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
			System.out.println(this.dataframe.size());
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
			this.header = new String[1];
			this.header[0] = "Nada";
			Object[] i = new Object[1];
			i = this.header;
			this.dataframe.add(i);
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