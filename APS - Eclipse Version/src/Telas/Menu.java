package Telas;

import Models.*;

@SuppressWarnings("serial")
public class Menu extends javax.swing.JFrame {
	
	private DataCSV csv = new DataCSV();
	
    public Menu() {
    	initComponents();
    }
    
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        CadastroAluno = new javax.swing.JMenuItem();
        CadastroCurso = new javax.swing.JMenuItem();
        CadastroRendimento = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        Fechar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jMenu1.setText("Cadastro");

        CadastroAluno.setText("Aluno");
        CadastroAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadastroAlunoActionPerformed(evt);
                
            }
        });
        jMenu1.add(CadastroAluno);

        CadastroCurso.setText("Curso");
        CadastroCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadastroCursoActionPerformed(evt);
            }
        });
        jMenu1.add(CadastroCurso);

        CadastroRendimento.setText("Rendimento");
        CadastroRendimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadastroRendimentoActionPerformed(evt);
            }
        });
        jMenu1.add(CadastroRendimento);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Listagem");

        jMenuItem4.setText("Cursos");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Alunos");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setText("Histórico");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setText("Rendimento");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Sair");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        Fechar.setText("Fechar");
        Fechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FecharActionPerformed(evt);
            }
        });
        jMenu3.add(Fechar);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void CadastroAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadastroAlunoActionPerformed
    	CadastroAluno ca = new CadastroAluno();
        this.dispose();
    	ca.setVisible(true);
        
    	ca.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
          	csv.reload();
                Menu.this.setVisible(true);
            }
        
        });
        
                
    }//GEN-LAST:event_CadastroAlunoActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
    	
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void CadastroCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadastroCursoActionPerformed
          CadastroCurso cd = new CadastroCurso();
          this.dispose();
          cd.setVisible(true);
          
          
          cd.addWindowListener(new java.awt.event.WindowAdapter() {
              @Override
              public void windowClosed(java.awt.event.WindowEvent windowEvent) {
            	  csv.reload();
                  Menu.this.setVisible(true);
              }
          });
          

          
    }//GEN-LAST:event_CadastroCursoActionPerformed
    
    private void CadastroRendimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadastroRendimentoActionPerformed
        this.dispose();
        CadastroRendimento cr = new CadastroRendimento();
        cr.setVisible(true);
        cr.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {  
                Menu.this.setVisible(true);
            }
        });        
        
    }//GEN-LAST:event_CadastroRendimentoActionPerformed

    private void FecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FecharActionPerformed
    	this.csv.reload();
    	this.csv.saveAll();
    	System.exit(0);
    }//GEN-LAST:event_FecharActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    	ListagemCursos lc = new ListagemCursos(csv.getCursosTable());
        lc.setVisible(true);
        
        
        lc.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                Menu.this.setVisible(true);
            }
        });               
        
    }                                          

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
    	this.dispose();
        ListagemAlunos la = new ListagemAlunos(csv.getAlunosTable());
        la.setVisible(true);
        
        la.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                Menu.this.setVisible(true);
            }
        });    
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        this.dispose();
        ListagemHistorico lh = new ListagemHistorico();
        lh.setVisible(true);
        
        lh.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                Menu.this.setVisible(true);
            }
        });   
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        this.dispose();
        ListagemRendimento lr = new ListagemRendimento();
        lr.setVisible(true);
        lr.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                Menu.this.setVisible(true);
            }
        });          
    }//GEN-LAST:event_jMenuItem7ActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	Menu menu = new Menu();
                menu.setVisible(true);
            }
        });
    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem CadastroAluno;
    private javax.swing.JMenuItem CadastroCurso;
    private javax.swing.JMenuItem CadastroRendimento;
    private javax.swing.JMenuItem Fechar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    // End of variables declaration//GEN-END:variables
}
