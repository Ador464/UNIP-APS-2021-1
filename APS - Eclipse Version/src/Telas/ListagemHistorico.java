/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import Models.DataCSV;
import Models.ReadCSV;

/**
 *
 * @author camil
 */
@SuppressWarnings("serial")
public class ListagemHistorico extends javax.swing.JFrame {

	private DataCSV csv = new DataCSV();
		
    /**
     * Creates new form ListagemHistorico
     */
    public ListagemHistorico(){
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BtnSair = new javax.swing.JButton();
        BtnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Selecione o aluno:");
        
        
        String[] alunos = csv.getAlunos();
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(alunos));

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 36)); // NOI18N
        jLabel3.setText("Histórico");

        reloadTable();
        
        jScrollPane1.setViewportView(jTable1);

        BtnSair.setText("Voltar");
        BtnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	BtnSairActionPerformed(evt);
            }
        });
        
        BtnDelete.setText("Excluir Rendimento");
        BtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	BtnDeleteActionPerformed(evt, getFinalTable(csv.getModalTable(), jComboBox1.getSelectedIndex())[jTable1.getSelectedRow()][0].toString(), jComboBox1.getSelectedIndex());
            }
        });
        
        reloadTable();


    	jComboBox1.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		SelectionBoxChanged(evt);
        		reloadTable();
        	}
        });
    	
        
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            	.addGap(5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                    	.addComponent(BtnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(168, 168, 168)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup()
                	.addComponent(BtnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                	.addComponent(BtnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSairActionPerformed
        dispose();
    }//GEN-LAST:event_BtnSairActionPerformed
    
    private void BtnDeleteActionPerformed(java.awt.event.ActionEvent evt, String crs, int idx) {//GEN-FIRST:event_BtnSairActionPerformed
    	try {
    		csv.deleteHistorico(crs, idx);
    		reloadTable();
    	} catch (java.lang.ArrayIndexOutOfBoundsException e) {
    		JOptionPane.showMessageDialog(null, "selecione um Histórico para deletá-lo");
    	}
    }//GEN-LAST:event_BtnSairActionPerformed
    
    private void SelectionBoxChanged(java.awt.event.ActionEvent evt) {
    }
    
    private void reloadTable() {
    	int selected_index = jComboBox1.getSelectedIndex();
		Object[][] finalTable = getFinalTable(csv.getModalTable(), selected_index);
		jTable1.setModel(new javax.swing.table.DefaultTableModel(
			finalTable,
            new String [] {
            		"Curso", "NP1", "NP2", "Sub", "Exame", "Média", "Condição"
            }
        ));
    }
    
    private Object[][] getFinalTable(Object[][][] modal,int index_aluno) {
    	//"Curso", "NP1", "NP2", "Sub", "Exame", "Média", "Condição"
    	if(modal != null) {
    		List<Object[]> pre_res = new LinkedList<Object[]>();
    		int aluno_ra = Integer.valueOf(csv.getAlunosTable()[index_aluno][0].toString());
    		for(int i = 0; i < modal.length; i++) {
    			if(modal[i] != null) {
	    			for(int k = 0; k < modal[i].length; k++) {
	    				if(ReadCSV.toInt(modal[i][k][0]) == aluno_ra) {
	    					Object[] adds = new Object[7];
	    					adds[0] = csv.getCursosTable()[i][0].toString();
	    		    		adds[1] = modal[i][k][1];
	    		    		adds[2] = modal[i][k][2];
	    		    		adds[3] = modal[i][k][3];
	    		    		adds[4] = modal[i][k][4];
	    		    		
	    		    		double np1 = Double.valueOf(modal[i][k][1].toString());
	    		    		double np2 = Double.valueOf(modal[i][k][2].toString());
	    		    		double sub = Double.valueOf(modal[i][k][3].toString());
	    		    		double exame = Double.valueOf(modal[i][k][4].toString());
	    		    		double med = 0;
	    		    		
	    		    		if(np1 < sub) {
	    		    			med = (sub + np1) / 2;
	    		    		} else if(np2 < sub) {
	    		    			med = (sub + np2) / 2;
	    		    		} else {
	    		    			med = (np1 + np2) / 2;
	    		    		}
	    		    		
	    		    		adds[5] = med;
	    		    		
	    		    		int med_c = 0;
	    		    		int curso_s = csv.getCursosTable()[i][1].toString().indexOf("-");
	    		    		if(curso_s < 0) {
	    		    			med_c = 7;
	    		    		} else {
	    		    			med_c = 5;
	    		    		}
	    		    		
	    		    		if(med >= med_c) {
	    		    			adds[6] = "Aprovado";
	    	    			} else {
	    	    				med = (med + exame) / 2;
	    	    				if(med >= 5) {
	    	    					adds[6] = "Aprovado";
	    	    				} else {
	    	    					adds[6] = "Reprovado";
	    	    				}
	    	    			}
	    		    		pre_res.add(adds);
	    				}
	    			}
    			}
    		}
	    	Object[][] res = ReadCSV.toObject(pre_res);
	    	return res;
    	} else {
    		Object[][] res = new Object[][] {};
        	return res;
    	}
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListagemHistorico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListagemHistorico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListagemHistorico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListagemHistorico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListagemHistorico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnSair;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton BtnDelete;
    // End of variables declaration//GEN-END:variables
}
