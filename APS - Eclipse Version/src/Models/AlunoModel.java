package Models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/*import jdk.nashorn.internal.objects.NativeArray;*/

/**
 *
 * @author Alves
 */
public class AlunoModel {
    private String id;
    private String nome;
    
    public AlunoModel(String id, String nome){
    	this.id = id;
    	this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
    // COISAS DA AMANDA
    public String salvar() {
        
        try {
        FileWriter fw = new FileWriter("C:\\Users\\Alves\\OneDrive\\�rea de Trabalho\\01\\aluno.csv",true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println("ID: " + this.id + " , " + "Nome: " + this.nome);
        pw.flush();
        pw.close();
        fw.close();
        
        } catch (IOException ex){
            Logger.getLogger(AlunoModel.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                
        return "Cadastrado com Sucesso";
    }
    
    public String carregar(){
        String dadosAlunos = ""; 
        
        try{
          Scanner in = new Scanner(new FileReader("C:\\Users\\Alves\\OneDrive\\�rea de Trabalho\\01\\aluno.csv"));
            while (in.hasNextLine()) {
                dadosAlunos += in.nextLine()+"\n";
            }
        }catch (IOException ex){
            Logger.getLogger(AlunoModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dadosAlunos;
    }

    void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}


