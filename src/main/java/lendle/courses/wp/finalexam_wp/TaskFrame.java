/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lendle.courses.wp.finalexam_wp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author lendle
 */
public class TaskFrame extends JInternalFrame {

    private JTextField textTitle = null;
    private JTextArea textContent = null;
    private boolean modified = false;

    public TaskFrame() {
        //this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //JDesktopPane jDesktopPane=new JDesktopPane();
        //this.setContentPane(jDesktopPane);
        this.setSize(500, 300);
        //Q4: layout 出如圖所示的樣子，
        //記得 JTextArea 要放在捲軸裡面 (30%)
        
        ////////////////////////////
        this.setClosable(true);
        this.setResizable(true);
        this.setVisible(true);
        
        JLabel jLabel=new JLabel("title:");
        JTextField jTextField=new JTextField(title);
        this.add(jLabel);
        this.add(jTextField);
        
        JTextArea jTextArea=new JTextArea();
        JPanel southPanel = new JPanel();
        this.add(southPanel, "South");
        southPanel.add(jTextArea);
                
        JButton saveButton = new JButton("Save");
        southPanel.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaskDB.save(getNoteTitle(), getNoteContent());
                modified = false;
                setTitle("");
            }
        });

        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                modified = true;
                setTitle("*");
            }

        };
        textContent.addKeyListener(keyListener);
        this.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                if (modified) {
                    //Q5: 發現變更，顯示 confirm dialog 詢問是否要儲存 (20%)
                    //JDesktopPane desktop=new JDesktopPane();
                    //this.setContenPan(desktop);
                    
                    int ret =JOptionPane.showConfirmDialog(this, "是否要儲存?", "test", JOptionPane.YES_NO_OPTION);
                    /////////////////////////////////////////////
                    if (ret == JOptionPane.YES_OPTION) {
                        TaskDB.save(getNoteTitle(), getNoteContent());
                        modified = false;
                        setTitle("");
                    } 
                    
                }
            }

            private void setContenPan(JDesktopPane desktop) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
    }

    public String getNoteTitle() {
        return textTitle.getText();
    }

    public void setNoteTitle(String title) {
        this.textTitle.setText(title);
    }

    public String getNoteContent() {
        return textContent.getText();
    }

    public void setNoteContent(String content) {
        this.textContent.setText(content);
    }
}
