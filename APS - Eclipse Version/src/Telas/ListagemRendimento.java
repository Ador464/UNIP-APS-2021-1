/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Models.DataCSV;


@SuppressWarnings("serial")
public class ListagemRendimento extends javax.swing.JFrame {

	private DataCSV csv = new DataCSV();
	

    public ListagemRendimento() {
        initComponents();
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        BtnSair = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 36)); // NOI18N
        jLabel3.setText("Rendimento");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Selecione o curso:");

        
        String[] cursos = csv.getCursos();
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(cursos));

        BtnSair.setText("Voltar");
        BtnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSairActionPerformed(evt);
            }
        });
        
        
        
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		SelectionBoxChanged(evt);
        		int selected_index = jComboBox1.getSelectedIndex();
        		Object[][] finalTable = getFinalTable(csv.getModalByIndex(selected_index), selected_index);
        		jTable1.setModel(new javax.swing.table.DefaultTableModel(
        			finalTable,
                    new String [] {
                    		"Aluno", "NP1", "NP2", "Sub", "Exame", "Média", "Condição"
                    }
                ));
        	}
        });

        int selected_index = jComboBox1.getSelectedIndex();
		Object[][] finalTable = getFinalTable(csv.getModalByIndex(selected_index), selected_index);
		jTable1.setModel(new javax.swing.table.DefaultTableModel(
			finalTable,
            new String [] {
            		"Aluno", "NP1", "NP2", "Sub", "Exame", "Média", "Condição"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(109, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(BtnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 225, Short.MAX_VALUE)
                .addComponent(BtnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(119, 119, 119)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(57, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSairActionPerformed
        dispose();
    }//GEN-LAST:event_BtnSairActionPerformed
    
    private void SelectionBoxChanged(java.awt.event.ActionEvent evt) {
    }
    
    private Object[][] getFinalTable(Object[][] input,int curso) {
    	//"Aluno", "NP1", "NP2", "Sub", "Exame", "Média", "Condição"
    	if(input != null) {
	    	Object[][] res = new Object[input.length][7];
	    	for(int i = 0; i < input.length;i++) {
	    		res[i][0]= csv.getAlunoByRA(Integer.valueOf(input[i][0].toString()));
	    		res[i][1]= input[i][1];
	    		res[i][2]= input[i][2];
	    		res[i][3]= input[i][3];
	    		res[i][4]= input[i][4];
	
	    		double np1 = Double.valueOf(input[i][1].toString());
	    		double np2 = Double.valueOf(input[i][2].toString());
	    		double sub = Double.valueOf(input[i][3].toString());
	    		double exame = Double.valueOf(input[i][4].toString());
	    		
	    		double med = 0;
	    		
	    		if(np1 < sub) {
	    			med = (sub + np1) / 2;
	    		} else if(np2 < sub) {
	    			med = (sub + np2) / 2;
	    		} else {
	    			med = (np1 + np2) / 2;
	    		}
	    		
	    		res[i][5] = med;
	    		
	    		int med_c = 0;
	    		int curso_s = csv.getCursosTable()[curso][1].toString().indexOf("-");
	    		if(curso_s < 0) {
	    			med_c = 7;
	    		} else {
	    			med_c = 5;
	    		}
	    		
	    		if(med >= med_c) {
    				res[i][6] = "Aprovado";
    			} else {
    				med = (med + exame) / 2;
    				if(med >= 5) {
    					res[i][6] = "Aprovado";
    				} else {
    					res[i][6] = "Reprovado";
    				}
    			}
	    	}
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
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListagemRendimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListagemRendimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListagemRendimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListagemRendimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListagemRendimento().setVisible(true);
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
    // End of variables declaration//GEN-END:variables
}
