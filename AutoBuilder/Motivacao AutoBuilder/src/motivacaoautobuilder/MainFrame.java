/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

////////////////////// shift + f6 para executar


package motivacaoautobuilder;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author leo_c
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() throws FileNotFoundException {
        initComponents();
        //SetBtnMatrix();
        SetRefMatrix();
        
        auxForPos = jPanel9;
        
        
        // Lists Selection Listeners
        compList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent arg0){ if(!arg0.getValueIsAdjusting()){ if(compList.getSelectedIndices().length == 1){
                Image dimg = general.GetImg(compList.getSelectedValue(), compPreviewPanel.getWidth(), compPreviewPanel.getHeight());
                compPreview.setIcon(new ImageIcon(dimg)); 
                System.out.println(compList.getSelectedIndices().length);
        }}}});
        backgroundList.addListSelectionListener(new ListSelectionListener(){ public void valueChanged(ListSelectionEvent arg0){ if (!arg0.getValueIsAdjusting()){ if (backgroundList.getSelectedIndices().length == 1){
            posTab.getAccessibleContext().setAccessibleName("bck_" + labelBackground.getSelectedValue());
            //JScrollPane auxBackgroundViewerPane = new JScrollPane();
            Image dimg2 = general.GetImg(backgroundList.getSelectedValue(), imgAux.getWidth(), imgAux.getHeight());
            imgAux.setIcon(new ImageIcon(dimg2));
            SetBoardMatrix(matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())));
            
        }}}});
        labelBackground.addListSelectionListener(new ListSelectionListener(){ public void valueChanged(ListSelectionEvent arg0){ if (!arg0.getValueIsAdjusting()){ if (labelBackground.getSelectedIndices().length == 1){
            background.remove(labelBackground.getSelectedValue().toString());
            textBackgroud = Integer.toString(background.size()) + " Imagem(s) de Fundo\n";
            for (String s : background){
                textBackgroud += "\t" + s + "\t\n";
            }
            textResumo = textBackgroud + "\n\n" + textElemento + "\n\n" + textArvore + "\n\n" + textLixo;
            labelResumo2.setText(textResumo);
            matrix.remove(ListCBackground.indexOf(labelBackground.getSelectedValue().toString()));
            ListCBackground.remove(ListCBackground.indexOf(labelBackground.getSelectedValue().toString()));
        }}}});
        labelElementos.addListSelectionListener(new ListSelectionListener(){ public void valueChanged(ListSelectionEvent arg0){ if (!arg0.getValueIsAdjusting()){ if (labelElementos.getSelectedIndices().length == 1){
            elemento.remove(labelElementos.getSelectedValue().toString());
            textElemento = Integer.toString(elemento.size()) + " Elemento(s)\n";
            for (String s : elemento){
                textElemento += "\t" + s + "\t\n";
            }
            textResumo = textBackgroud + "\n\n" + textElemento + "\n\n" + textArvore + "\n\n" + textLixo;
            labelResumo2.setText(textResumo);
            ListComponentsTemp.remove(ListComponentsTemp.indexOf(labelElementos.getSelectedValue().toString()));
            if(ListComponentsPrio.contains(labelElementos.getSelectedValue().toString()))
                ListComponentsPrio.remove(ListComponentsUsed.indexOf(labelElementos.getSelectedValue().toString()));
            if(ListComponentsUsed.contains(labelElementos.getSelectedValue().toString()))
                ListComponentsUsed.remove(ListComponentsUsed.indexOf(labelElementos.getSelectedValue().toString()));
            ListCElemento.remove(ListCElemento.indexOf(labelElementos.getSelectedValue().toString()));
        }}}});
        labelArvores.addListSelectionListener(new ListSelectionListener(){ public void valueChanged(ListSelectionEvent arg0){ if (!arg0.getValueIsAdjusting()){ if (labelArvores.getSelectedIndices().length == 1){
            arvore.remove(labelArvores.getSelectedValue().toString());
            textArvore = Integer.toString(arvore.size()) + " Árvore(s)\n";
            for (String s : arvore){
                textArvore += "\t" + s + "\t\n";
            }
            textResumo = textBackgroud + "\n\n" + textElemento + "\n\n" + textArvore + "\n\n" + textLixo;
            labelResumo2.setText(textResumo);
            ListComponentsTemp.remove(ListComponentsTemp.indexOf(labelArvores.getSelectedValue().toString()));
            if(ListComponentsPrio.contains(labelArvores.getSelectedValue().toString()))
                ListComponentsPrio.remove(ListComponentsUsed.indexOf(labelArvores.getSelectedValue().toString()));
            if(ListComponentsUsed.contains(labelArvores.getSelectedValue().toString()))
                ListComponentsUsed.remove(ListComponentsUsed.indexOf(labelArvores.getSelectedValue().toString()));
            ListCArvore.remove(ListCArvore.indexOf(labelArvores.getSelectedValue().toString()));
        }}}});
        labelLixo.addListSelectionListener(new ListSelectionListener(){ public void valueChanged(ListSelectionEvent arg0){ if (!arg0.getValueIsAdjusting()){ if (labelLixo.getSelectedIndices().length == 1){
            lixo.remove(labelLixo.getSelectedValue().toString());
            textLixo = Integer.toString(lixo.size()) + " Lixo(s)\n";
            for (String s : lixo){
                textLixo += "\t" + s + "\t\n";
            }
            textResumo = textBackgroud + "\n\n" + textElemento + "\n\n" + textArvore + "\n\n" + textLixo;
            labelResumo2.setText(textResumo);
            ListComponentsTemp.remove(ListComponentsTemp.indexOf(labelLixo.getSelectedValue().toString()));
            if(ListComponentsPrio.contains(labelLixo.getSelectedValue().toString()))
                ListComponentsPrio.remove(ListComponentsUsed.indexOf(labelLixo.getSelectedValue().toString()));
            if(ListComponentsUsed.contains(labelLixo.getSelectedValue().toString()))
                ListComponentsUsed.remove(ListComponentsUsed.indexOf(labelLixo.getSelectedValue().toString()));
            ListCLixo.remove(ListCLixo.indexOf(labelLixo.getSelectedValue().toString()));
        }}}});
        
        
        compList.setModel(ListComponentsTemp);
        backgroundList.setModel(ListCBackground);
        labelBackground.setModel(ListCBackground);
        labelElementos.setModel(ListCElemento);
        labelArvores.setModel(ListCArvore);
        labelLixo.setModel(ListCLixo);
        
        progressBuilding.setVisible(false);
        labelCommandLines.setVisible(false);
        textElemento = "";
        textArvore = "";
        textLixo = "";
        textBackgroud = "";
        Path currentRelativePath = Paths.get("");
        String refpath = currentRelativePath.toAbsolutePath().toString();
        ArrayList<String> data = general.StartSettings(refpath + "\\config-win.txt");
        //ArrayList<String> data = general.StartSettings(refpath + "/config-mac.txt");
        int i=0;
        for (String s : data){
            if (i==0)
                unitypath = s;
            if (i==1)
                plataform = s;
            
            i++;
        }
        System.out.println(unitypath);
        System.out.println(plataform);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        posTab = new javax.swing.JPanel();
        backgroundViewerPane = new javax.swing.JScrollPane();
        componentViewerPane = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanelTab = new javax.swing.JPanel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jScrollPane10 = new javax.swing.JScrollPane();
        imgAux1 = new javax.swing.JLabel();
        BtnPos_n2_n5 = new javax.swing.JToggleButton();
        BtnPos_n2_n6 = new javax.swing.JToggleButton();
        BtnPos_n2_n7 = new javax.swing.JToggleButton();
        BtnPos_n2_n8 = new javax.swing.JToggleButton();
        BtnPos_n2_5 = new javax.swing.JToggleButton();
        BtnPos_n2_6 = new javax.swing.JToggleButton();
        BtnPos_n2_7 = new javax.swing.JToggleButton();
        BtnPos_n2_8 = new javax.swing.JToggleButton();
        BtnPos_n2_9 = new javax.swing.JToggleButton();
        BtnPos_n1_n5 = new javax.swing.JToggleButton();
        BtnPos_n1_n6 = new javax.swing.JToggleButton();
        BtnPos_n1_n7 = new javax.swing.JToggleButton();
        BtnPos_n1_n8 = new javax.swing.JToggleButton();
        BtnPos_n1_5 = new javax.swing.JToggleButton();
        BtnPos_n1_6 = new javax.swing.JToggleButton();
        BtnPos_n1_7 = new javax.swing.JToggleButton();
        BtnPos_n1_8 = new javax.swing.JToggleButton();
        BtnPos_n1_9 = new javax.swing.JToggleButton();
        BtnPos_0_n5 = new javax.swing.JToggleButton();
        BtnPos_0_n6 = new javax.swing.JToggleButton();
        BtnPos_0_n7 = new javax.swing.JToggleButton();
        BtnPos_0_n8 = new javax.swing.JToggleButton();
        BtnPos_0_5 = new javax.swing.JToggleButton();
        BtnPos_0_6 = new javax.swing.JToggleButton();
        BtnPos_0_7 = new javax.swing.JToggleButton();
        BtnPos_0_8 = new javax.swing.JToggleButton();
        BtnPos_0_9 = new javax.swing.JToggleButton();
        BtnPos_1_n5 = new javax.swing.JToggleButton();
        BtnPos_1_n6 = new javax.swing.JToggleButton();
        BtnPos_1_n7 = new javax.swing.JToggleButton();
        BtnPos_1_n8 = new javax.swing.JToggleButton();
        BtnPos_1_5 = new javax.swing.JToggleButton();
        BtnPos_1_6 = new javax.swing.JToggleButton();
        BtnPos_1_7 = new javax.swing.JToggleButton();
        BtnPos_1_8 = new javax.swing.JToggleButton();
        BtnPos_1_9 = new javax.swing.JToggleButton();
        BtnPos_2_n5 = new javax.swing.JToggleButton();
        BtnPos_2_n6 = new javax.swing.JToggleButton();
        BtnPos_2_n7 = new javax.swing.JToggleButton();
        BtnPos_2_n8 = new javax.swing.JToggleButton();
        BtnPos_2_5 = new javax.swing.JToggleButton();
        BtnPos_2_6 = new javax.swing.JToggleButton();
        BtnPos_2_7 = new javax.swing.JToggleButton();
        BtnPos_2_8 = new javax.swing.JToggleButton();
        BtnPos_2_9 = new javax.swing.JToggleButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        compViewer1 = new javax.swing.JPanel();
        compList1 = new javax.swing.JList<>();
        compPreviewPanel1 = new javax.swing.JPanel();
        compPreview1 = new javax.swing.JLabel();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnIniciar = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnAddBackground = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        panelBackground = new javax.swing.JScrollPane();
        labelBackground = new javax.swing.JList<>();
        btnVoltar2 = new javax.swing.JButton();
        btnProx2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnAddElementos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        labelElementos = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        btnAddArvores = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        labelArvores = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        btnAddLixo = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        labelLixo = new javax.swing.JList<>();
        btnVoltar3 = new javax.swing.JButton();
        btnProx3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        posTabGroup = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        imgAux = new javax.swing.JLabel();
        BtnPos_n3_n13 = new javax.swing.JToggleButton();
        BtnPos_n3_n12 = new javax.swing.JToggleButton();
        BtnPos_n3_n11 = new javax.swing.JToggleButton();
        BtnPos_n3_n10 = new javax.swing.JToggleButton();
        BtnPos_n3_n9 = new javax.swing.JToggleButton();
        BtnPos_n3_n4 = new javax.swing.JToggleButton();
        BtnPos_n3_n3 = new javax.swing.JToggleButton();
        BtnPos_n3_n2 = new javax.swing.JToggleButton();
        BtnPos_n3_n1 = new javax.swing.JToggleButton();
        BtnPos_n3_0 = new javax.swing.JToggleButton();
        BtnPos_n3_1 = new javax.swing.JToggleButton();
        BtnPos_n3_2 = new javax.swing.JToggleButton();
        BtnPos_n3_3 = new javax.swing.JToggleButton();
        BtnPos_n3_4 = new javax.swing.JToggleButton();
        BtnPos_n3_10 = new javax.swing.JToggleButton();
        BtnPos_n3_11 = new javax.swing.JToggleButton();
        BtnPos_n3_12 = new javax.swing.JToggleButton();
        BtnPos_n3_13 = new javax.swing.JToggleButton();
        BtnPos_n3_14 = new javax.swing.JToggleButton();
        BtnPos_n2_n13 = new javax.swing.JToggleButton();
        BtnPos_n2_n12 = new javax.swing.JToggleButton();
        BtnPos_n2_n11 = new javax.swing.JToggleButton();
        BtnPos_n2_n10 = new javax.swing.JToggleButton();
        BtnPos_n2_n9 = new javax.swing.JToggleButton();
        BtnPos_n2_n4 = new javax.swing.JToggleButton();
        BtnPos_n2_n3 = new javax.swing.JToggleButton();
        BtnPos_n2_n2 = new javax.swing.JToggleButton();
        BtnPos_n2_n1 = new javax.swing.JToggleButton();
        BtnPos_n2_0 = new javax.swing.JToggleButton();
        BtnPos_n2_1 = new javax.swing.JToggleButton();
        BtnPos_n2_2 = new javax.swing.JToggleButton();
        BtnPos_n2_3 = new javax.swing.JToggleButton();
        BtnPos_n2_4 = new javax.swing.JToggleButton();
        BtnPos_n2_10 = new javax.swing.JToggleButton();
        BtnPos_n2_11 = new javax.swing.JToggleButton();
        BtnPos_n2_12 = new javax.swing.JToggleButton();
        BtnPos_n2_13 = new javax.swing.JToggleButton();
        BtnPos_n2_14 = new javax.swing.JToggleButton();
        BtnPos_n1_n13 = new javax.swing.JToggleButton();
        BtnPos_n1_n12 = new javax.swing.JToggleButton();
        BtnPos_n1_n11 = new javax.swing.JToggleButton();
        BtnPos_n1_n10 = new javax.swing.JToggleButton();
        BtnPos_n1_n9 = new javax.swing.JToggleButton();
        BtnPos_n1_n4 = new javax.swing.JToggleButton();
        BtnPos_n1_n3 = new javax.swing.JToggleButton();
        BtnPos_n1_n2 = new javax.swing.JToggleButton();
        BtnPos_n1_n1 = new javax.swing.JToggleButton();
        BtnPos_n1_0 = new javax.swing.JToggleButton();
        BtnPos_n1_1 = new javax.swing.JToggleButton();
        BtnPos_n1_2 = new javax.swing.JToggleButton();
        BtnPos_n1_3 = new javax.swing.JToggleButton();
        BtnPos_n1_4 = new javax.swing.JToggleButton();
        BtnPos_n1_10 = new javax.swing.JToggleButton();
        BtnPos_n1_11 = new javax.swing.JToggleButton();
        BtnPos_n1_12 = new javax.swing.JToggleButton();
        BtnPos_n1_13 = new javax.swing.JToggleButton();
        BtnPos_n1_14 = new javax.swing.JToggleButton();
        BtnPos_0_n13 = new javax.swing.JToggleButton();
        BtnPos_0_n12 = new javax.swing.JToggleButton();
        BtnPos_0_n11 = new javax.swing.JToggleButton();
        BtnPos_0_n10 = new javax.swing.JToggleButton();
        BtnPos_0_n9 = new javax.swing.JToggleButton();
        BtnPos_0_n4 = new javax.swing.JToggleButton();
        BtnPos_0_n3 = new javax.swing.JToggleButton();
        BtnPos_0_n2 = new javax.swing.JToggleButton();
        BtnPos_0_n1 = new javax.swing.JToggleButton();
        BtnPos_0_0 = new javax.swing.JToggleButton();
        BtnPos_0_1 = new javax.swing.JToggleButton();
        BtnPos_0_2 = new javax.swing.JToggleButton();
        BtnPos_0_3 = new javax.swing.JToggleButton();
        BtnPos_0_4 = new javax.swing.JToggleButton();
        BtnPos_0_10 = new javax.swing.JToggleButton();
        BtnPos_0_11 = new javax.swing.JToggleButton();
        BtnPos_0_12 = new javax.swing.JToggleButton();
        BtnPos_0_13 = new javax.swing.JToggleButton();
        BtnPos_0_14 = new javax.swing.JToggleButton();
        BtnPos_1_n13 = new javax.swing.JToggleButton();
        BtnPos_1_n12 = new javax.swing.JToggleButton();
        BtnPos_1_n11 = new javax.swing.JToggleButton();
        BtnPos_1_n10 = new javax.swing.JToggleButton();
        BtnPos_1_n9 = new javax.swing.JToggleButton();
        BtnPos_1_n4 = new javax.swing.JToggleButton();
        BtnPos_1_n3 = new javax.swing.JToggleButton();
        BtnPos_1_n2 = new javax.swing.JToggleButton();
        BtnPos_1_n1 = new javax.swing.JToggleButton();
        BtnPos_1_0 = new javax.swing.JToggleButton();
        BtnPos_1_1 = new javax.swing.JToggleButton();
        BtnPos_1_2 = new javax.swing.JToggleButton();
        BtnPos_1_3 = new javax.swing.JToggleButton();
        BtnPos_1_4 = new javax.swing.JToggleButton();
        BtnPos_1_10 = new javax.swing.JToggleButton();
        BtnPos_1_11 = new javax.swing.JToggleButton();
        BtnPos_1_12 = new javax.swing.JToggleButton();
        BtnPos_1_13 = new javax.swing.JToggleButton();
        BtnPos_1_14 = new javax.swing.JToggleButton();
        BtnPos_2_n13 = new javax.swing.JToggleButton();
        BtnPos_2_n12 = new javax.swing.JToggleButton();
        BtnPos_2_n11 = new javax.swing.JToggleButton();
        BtnPos_2_n10 = new javax.swing.JToggleButton();
        BtnPos_2_n9 = new javax.swing.JToggleButton();
        BtnPos_2_n4 = new javax.swing.JToggleButton();
        BtnPos_2_n3 = new javax.swing.JToggleButton();
        BtnPos_2_n2 = new javax.swing.JToggleButton();
        BtnPos_2_n1 = new javax.swing.JToggleButton();
        BtnPos_2_0 = new javax.swing.JToggleButton();
        BtnPos_2_1 = new javax.swing.JToggleButton();
        BtnPos_2_2 = new javax.swing.JToggleButton();
        BtnPos_2_3 = new javax.swing.JToggleButton();
        BtnPos_2_4 = new javax.swing.JToggleButton();
        BtnPos_2_10 = new javax.swing.JToggleButton();
        BtnPos_2_11 = new javax.swing.JToggleButton();
        BtnPos_2_12 = new javax.swing.JToggleButton();
        BtnPos_2_13 = new javax.swing.JToggleButton();
        BtnPos_2_14 = new javax.swing.JToggleButton();
        BtnPos_3_n13 = new javax.swing.JToggleButton();
        BtnPos_3_n12 = new javax.swing.JToggleButton();
        BtnPos_3_n11 = new javax.swing.JToggleButton();
        BtnPos_3_n10 = new javax.swing.JToggleButton();
        BtnPos_3_n9 = new javax.swing.JToggleButton();
        BtnPos_3_n4 = new javax.swing.JToggleButton();
        BtnPos_3_n3 = new javax.swing.JToggleButton();
        BtnPos_3_n2 = new javax.swing.JToggleButton();
        BtnPos_3_n1 = new javax.swing.JToggleButton();
        BtnPos_3_0 = new javax.swing.JToggleButton();
        BtnPos_3_1 = new javax.swing.JToggleButton();
        BtnPos_3_2 = new javax.swing.JToggleButton();
        BtnPos_3_3 = new javax.swing.JToggleButton();
        BtnPos_3_4 = new javax.swing.JToggleButton();
        BtnPos_3_10 = new javax.swing.JToggleButton();
        BtnPos_3_11 = new javax.swing.JToggleButton();
        BtnPos_3_12 = new javax.swing.JToggleButton();
        BtnPos_3_13 = new javax.swing.JToggleButton();
        BtnPos_3_14 = new javax.swing.JToggleButton();
        BtnPos_3_n14 = new javax.swing.JToggleButton();
        BtnPos_3_n15 = new javax.swing.JToggleButton();
        BtnPos_3_n16 = new javax.swing.JToggleButton();
        BtnPos_3_n17 = new javax.swing.JToggleButton();
        BtnPos_3_n18 = new javax.swing.JToggleButton();
        BtnPos_3_n5 = new javax.swing.JToggleButton();
        BtnPos_3_n6 = new javax.swing.JToggleButton();
        BtnPos_3_n7 = new javax.swing.JToggleButton();
        BtnPos_3_n8 = new javax.swing.JToggleButton();
        BtnPos_3_5 = new javax.swing.JToggleButton();
        BtnPos_3_6 = new javax.swing.JToggleButton();
        BtnPos_3_7 = new javax.swing.JToggleButton();
        BtnPos_3_8 = new javax.swing.JToggleButton();
        BtnPos_3_9 = new javax.swing.JToggleButton();
        BtnPos_3_15 = new javax.swing.JToggleButton();
        BtnPos_3_16 = new javax.swing.JToggleButton();
        BtnPos_3_17 = new javax.swing.JToggleButton();
        BtnPos_3_18 = new javax.swing.JToggleButton();
        BtnPos_3_19 = new javax.swing.JToggleButton();
        BtnPos_3_n19 = new javax.swing.JToggleButton();
        BtnPos_3_n20 = new javax.swing.JToggleButton();
        BtnPos_3_n21 = new javax.swing.JToggleButton();
        BtnPos_3_n22 = new javax.swing.JToggleButton();
        BtnPos_3_n23 = new javax.swing.JToggleButton();
        BtnPos_3_n24 = new javax.swing.JToggleButton();
        BtnPos_3_n25 = new javax.swing.JToggleButton();
        BtnPos_3_n26 = new javax.swing.JToggleButton();
        BtnPos_3_n27 = new javax.swing.JToggleButton();
        BtnPos_3_20 = new javax.swing.JToggleButton();
        BtnPos_3_21 = new javax.swing.JToggleButton();
        BtnPos_3_22 = new javax.swing.JToggleButton();
        BtnPos_3_23 = new javax.swing.JToggleButton();
        BtnPos_3_24 = new javax.swing.JToggleButton();
        BtnPos_3_25 = new javax.swing.JToggleButton();
        BtnPos_3_26 = new javax.swing.JToggleButton();
        BtnPos_3_27 = new javax.swing.JToggleButton();
        BtnPos_3_28 = new javax.swing.JToggleButton();
        BtnPos_3_29 = new javax.swing.JToggleButton();
        BtnPos_3_n28 = new javax.swing.JToggleButton();
        BtnPos_3_n29 = new javax.swing.JToggleButton();
        BtnPos_3_n30 = new javax.swing.JToggleButton();
        BtnPos_3_n31 = new javax.swing.JToggleButton();
        BtnPos_3_n32 = new javax.swing.JToggleButton();
        BtnPos_3_n33 = new javax.swing.JToggleButton();
        BtnPos_3_n34 = new javax.swing.JToggleButton();
        BtnPos_3_n35 = new javax.swing.JToggleButton();
        BtnPos_3_n36 = new javax.swing.JToggleButton();
        BtnPos_3_30 = new javax.swing.JToggleButton();
        BtnPos_3_31 = new javax.swing.JToggleButton();
        BtnPos_3_32 = new javax.swing.JToggleButton();
        BtnPos_3_33 = new javax.swing.JToggleButton();
        BtnPos_3_34 = new javax.swing.JToggleButton();
        BtnPos_3_35 = new javax.swing.JToggleButton();
        BtnPos_3_36 = new javax.swing.JToggleButton();
        BtnPos_3_37 = new javax.swing.JToggleButton();
        BtnPos_3_38 = new javax.swing.JToggleButton();
        BtnPos_3_39 = new javax.swing.JToggleButton();
        BtnPos_n3_n14 = new javax.swing.JToggleButton();
        BtnPos_n3_n15 = new javax.swing.JToggleButton();
        BtnPos_n3_n16 = new javax.swing.JToggleButton();
        BtnPos_n3_n17 = new javax.swing.JToggleButton();
        BtnPos_n3_n18 = new javax.swing.JToggleButton();
        BtnPos_n3_n5 = new javax.swing.JToggleButton();
        BtnPos_n3_n6 = new javax.swing.JToggleButton();
        BtnPos_n3_n7 = new javax.swing.JToggleButton();
        BtnPos_n3_n8 = new javax.swing.JToggleButton();
        BtnPos_n3_5 = new javax.swing.JToggleButton();
        BtnPos_n3_6 = new javax.swing.JToggleButton();
        BtnPos_n3_7 = new javax.swing.JToggleButton();
        BtnPos_n3_8 = new javax.swing.JToggleButton();
        BtnPos_n3_9 = new javax.swing.JToggleButton();
        BtnPos_n3_15 = new javax.swing.JToggleButton();
        BtnPos_n3_16 = new javax.swing.JToggleButton();
        BtnPos_n3_17 = new javax.swing.JToggleButton();
        BtnPos_n3_18 = new javax.swing.JToggleButton();
        BtnPos_n3_19 = new javax.swing.JToggleButton();
        BtnPos_n3_n19 = new javax.swing.JToggleButton();
        BtnPos_n3_n20 = new javax.swing.JToggleButton();
        BtnPos_n3_n21 = new javax.swing.JToggleButton();
        BtnPos_n3_n22 = new javax.swing.JToggleButton();
        BtnPos_n3_n23 = new javax.swing.JToggleButton();
        BtnPos_n3_n24 = new javax.swing.JToggleButton();
        BtnPos_n3_n25 = new javax.swing.JToggleButton();
        BtnPos_n3_n26 = new javax.swing.JToggleButton();
        BtnPos_n3_n27 = new javax.swing.JToggleButton();
        BtnPos_n3_20 = new javax.swing.JToggleButton();
        BtnPos_n3_21 = new javax.swing.JToggleButton();
        BtnPos_n3_22 = new javax.swing.JToggleButton();
        BtnPos_n3_23 = new javax.swing.JToggleButton();
        BtnPos_n3_24 = new javax.swing.JToggleButton();
        BtnPos_n3_25 = new javax.swing.JToggleButton();
        BtnPos_n3_26 = new javax.swing.JToggleButton();
        BtnPos_n3_27 = new javax.swing.JToggleButton();
        BtnPos_n3_28 = new javax.swing.JToggleButton();
        BtnPos_n3_29 = new javax.swing.JToggleButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        compViewer = new javax.swing.JPanel();
        compList = new javax.swing.JList<>();
        compPreviewPanel = new javax.swing.JPanel();
        compPreview = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        backgroundList = new javax.swing.JList<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        JScrollPane10 = new javax.swing.JScrollPane();
        labelResumo2 = new javax.swing.JTextPane();
        jLabel6 = new javax.swing.JLabel();
        textNomeJogo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        labelSaveAddress = new javax.swing.JTextField();
        btnOpenFileChooser = new javax.swing.JButton();
        checkboxAtalho = new javax.swing.JCheckBox();
        btnVoltar4 = new javax.swing.JButton();
        btnBuild = new javax.swing.JButton();
        progressBuilding = new javax.swing.JProgressBar();
        labelCommandLines = new javax.swing.JLabel();
        checkboxAtalho1 = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        NovoJogo = new javax.swing.JMenuItem();
        AbrirJogo = new javax.swing.JMenuItem();
        SalvarJogo = new javax.swing.JMenuItem();
        SalvarComoJogo = new javax.swing.JMenuItem();
        Fechar = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        ComoUsar = new javax.swing.JMenuItem();
        Sobre = new javax.swing.JMenuItem();

        javax.swing.GroupLayout posTabLayout = new javax.swing.GroupLayout(posTab);
        posTab.setLayout(posTabLayout);
        posTabLayout.setHorizontalGroup(
            posTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 753, Short.MAX_VALUE)
        );
        posTabLayout.setVerticalGroup(
            posTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 427, Short.MAX_VALUE)
        );

        jButton1.setText("jButton1");
        componentViewerPane.setViewportView(jButton1);

        jScrollPane10.setViewportView(imgAux1);

        BtnPos_n2_n5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_n5ActionPerformed(evt);
            }
        });

        BtnPos_n2_n6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_n6ActionPerformed(evt);
            }
        });

        BtnPos_n2_n7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_n7ActionPerformed(evt);
            }
        });

        BtnPos_n2_n8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_n8ActionPerformed(evt);
            }
        });

        BtnPos_n2_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_5ActionPerformed(evt);
            }
        });

        BtnPos_n2_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_6ActionPerformed(evt);
            }
        });

        BtnPos_n2_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_7ActionPerformed(evt);
            }
        });

        BtnPos_n2_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_8ActionPerformed(evt);
            }
        });

        BtnPos_n2_9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_9ActionPerformed(evt);
            }
        });

        BtnPos_n1_n5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_n5ActionPerformed(evt);
            }
        });

        BtnPos_n1_n6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_n6ActionPerformed(evt);
            }
        });

        BtnPos_n1_n7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_n7ActionPerformed(evt);
            }
        });

        BtnPos_n1_n8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_n8ActionPerformed(evt);
            }
        });

        BtnPos_n1_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_5ActionPerformed(evt);
            }
        });

        BtnPos_n1_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_6ActionPerformed(evt);
            }
        });

        BtnPos_n1_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_7ActionPerformed(evt);
            }
        });

        BtnPos_n1_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_8ActionPerformed(evt);
            }
        });

        BtnPos_n1_9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_9ActionPerformed(evt);
            }
        });

        BtnPos_0_n5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_n5ActionPerformed(evt);
            }
        });

        BtnPos_0_n6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_n6ActionPerformed(evt);
            }
        });

        BtnPos_0_n7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_n7ActionPerformed(evt);
            }
        });

        BtnPos_0_n8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_n8ActionPerformed(evt);
            }
        });

        BtnPos_0_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_5ActionPerformed(evt);
            }
        });

        BtnPos_0_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_6ActionPerformed(evt);
            }
        });

        BtnPos_0_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_7ActionPerformed(evt);
            }
        });

        BtnPos_0_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_8ActionPerformed(evt);
            }
        });

        BtnPos_0_9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_9ActionPerformed(evt);
            }
        });

        BtnPos_1_n5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_n5ActionPerformed(evt);
            }
        });

        BtnPos_1_n6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_n6ActionPerformed(evt);
            }
        });

        BtnPos_1_n7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_n7ActionPerformed(evt);
            }
        });

        BtnPos_1_n8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_n8ActionPerformed(evt);
            }
        });

        BtnPos_1_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_5ActionPerformed(evt);
            }
        });

        BtnPos_1_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_6ActionPerformed(evt);
            }
        });

        BtnPos_1_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_7ActionPerformed(evt);
            }
        });

        BtnPos_1_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_8ActionPerformed(evt);
            }
        });

        BtnPos_1_9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_9ActionPerformed(evt);
            }
        });

        BtnPos_2_n5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_n5ActionPerformed(evt);
            }
        });

        BtnPos_2_n6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_n6ActionPerformed(evt);
            }
        });

        BtnPos_2_n7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_n7ActionPerformed(evt);
            }
        });

        BtnPos_2_n8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_n8ActionPerformed(evt);
            }
        });

        BtnPos_2_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_5ActionPerformed(evt);
            }
        });

        BtnPos_2_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_6ActionPerformed(evt);
            }
        });

        BtnPos_2_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_7ActionPerformed(evt);
            }
        });

        BtnPos_2_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_8ActionPerformed(evt);
            }
        });

        BtnPos_2_9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_9ActionPerformed(evt);
            }
        });

        jLayeredPane2.setLayer(jScrollPane10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(BtnPos_n2_n5, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_n2_n6, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_n2_n7, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_n2_n8, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_n2_5, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_n2_6, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_n2_7, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_n2_8, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_n2_9, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_n1_n5, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_n1_n6, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_n1_n7, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_n1_n8, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_n1_5, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_n1_6, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_n1_7, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_n1_8, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_n1_9, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_0_n5, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_0_n6, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_0_n7, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_0_n8, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_0_5, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_0_6, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_0_7, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_0_8, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_0_9, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_1_n5, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_1_n6, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_1_n7, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_1_n8, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_1_5, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_1_6, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_1_7, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_1_8, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_1_9, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_2_n5, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_2_n6, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_2_n7, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_2_n8, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_2_5, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_2_6, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_2_7, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_2_8, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane2.setLayer(BtnPos_2_9, javax.swing.JLayeredPane.PALETTE_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(BtnPos_n2_n5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n2_n6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n2_n7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n2_n8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnPos_n2_5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n2_6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n2_7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n2_8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n2_9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(BtnPos_n1_n5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n1_n6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n1_n7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n1_n8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnPos_n1_5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n1_6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n1_7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n1_8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n1_9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(BtnPos_0_n5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_0_n6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_0_n7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_0_n8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnPos_0_5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_0_6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_0_7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_0_8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_0_9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(BtnPos_1_n5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_1_n6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_1_n7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_1_n8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnPos_1_5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_1_6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_1_7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_1_8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_1_9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(BtnPos_2_n5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_2_n6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_2_n7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_2_n8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnPos_2_5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_2_6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_2_7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_2_8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_2_9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnPos_n2_n5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n2_n6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n2_n7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n2_n8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n2_5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n2_6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n2_7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n2_8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n2_9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnPos_n1_n5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n1_n6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n1_n7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n1_n8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n1_5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n1_6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n1_7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n1_8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n1_9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnPos_0_n5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_0_n6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_0_n7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_0_n8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_0_5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_0_6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_0_7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_0_8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_0_9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnPos_1_n5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_1_n6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_1_n7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_1_n8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_1_5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_1_6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_1_7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_1_8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_1_9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnPos_2_n5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_2_n6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_2_n7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_2_n8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_2_5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_2_6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_2_7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_2_8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_2_9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout compViewer1Layout = new javax.swing.GroupLayout(compViewer1);
        compViewer1.setLayout(compViewer1Layout);
        compViewer1Layout.setHorizontalGroup(
            compViewer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(compViewer1Layout.createSequentialGroup()
                .addComponent(compList1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        compViewer1Layout.setVerticalGroup(
            compViewer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(compViewer1Layout.createSequentialGroup()
                .addComponent(compList1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 168, Short.MAX_VALUE))
        );

        jScrollPane11.setViewportView(compViewer1);

        javax.swing.GroupLayout compPreviewPanel1Layout = new javax.swing.GroupLayout(compPreviewPanel1);
        compPreviewPanel1.setLayout(compPreviewPanel1Layout);
        compPreviewPanel1Layout.setHorizontalGroup(
            compPreviewPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(compPreview1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        compPreviewPanel1Layout.setVerticalGroup(
            compPreviewPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(compPreview1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelTabLayout = new javax.swing.GroupLayout(jPanelTab);
        jPanelTab.setLayout(jPanelTabLayout);
        jPanelTabLayout.setHorizontalGroup(
            jPanelTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                    .addComponent(compPreviewPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelTabLayout.setVerticalGroup(
            jPanelTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane2)
                    .addGroup(jPanelTabLayout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(compPreviewPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Construtor Motivação");
        setFocusCycleRoot(false);
        setResizable(false);

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel9.setText("Ação");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel10.setText("Motiv");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Inicie se próprio jogo com o construtor Motivação.");

        btnIniciar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(298, 298, 298)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnIniciar)
                        .addGap(120, 120, 120))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addGap(38, 38, 38)))
                .addContainerGap(309, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(210, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnIniciar)
                .addGap(176, 176, 176))
        );

        jTabbedPane1.addTab("   Bem-Vindo   ", jPanel1);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Planos de Fundo");

        btnAddBackground.setBackground(new java.awt.Color(255, 255, 255));
        btnAddBackground.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnAddBackground.setText("Adicionar");
        btnAddBackground.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBackgroundActionPerformed(evt);
            }
        });

        panelBackground.setViewportView(labelBackground);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(panelBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 47, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(panel);

        btnVoltar2.setText("Voltar");
        btnVoltar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltar2ActionPerformed(evt);
            }
        });

        btnProx2.setText("Próximo");
        btnProx2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProx2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddBackground)
                        .addGap(29, 29, 29))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 896, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVoltar2)
                .addGap(18, 18, 18)
                .addComponent(btnProx2)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddBackground))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProx2)
                    .addComponent(btnVoltar2))
                .addContainerGap())
        );

        jTabbedPane1.addTab("   Plano de Fundo   ", jPanel7);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Elementos");

        btnAddElementos.setBackground(new java.awt.Color(255, 255, 255));
        btnAddElementos.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnAddElementos.setText("Adicionar");
        btnAddElementos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddElementosActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(labelElementos);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Árvores");

        btnAddArvores.setBackground(new java.awt.Color(255, 255, 255));
        btnAddArvores.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnAddArvores.setText("Adicionar");
        btnAddArvores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddArvoresActionPerformed(evt);
            }
        });

        jScrollPane6.setViewportView(labelArvores);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Lixo");

        btnAddLixo.setBackground(new java.awt.Color(255, 255, 255));
        btnAddLixo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnAddLixo.setText("Adicionar");
        btnAddLixo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddLixoActionPerformed(evt);
            }
        });

        jScrollPane9.setViewportView(labelLixo);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 857, Short.MAX_VALUE)
                    .addComponent(jScrollPane6)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddElementos))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddArvores))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddLixo))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddElementos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddArvores))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddLixo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane2.setViewportView(jPanel2);

        btnVoltar3.setText("Voltar");
        btnVoltar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltar3ActionPerformed(evt);
            }
        });

        btnProx3.setText("Próximo");
        btnProx3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProx3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(756, Short.MAX_VALUE)
                .addComponent(btnVoltar3)
                .addGap(18, 18, 18)
                .addComponent(btnProx3)
                .addContainerGap())
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 896, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(478, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProx3)
                    .addComponent(btnVoltar3))
                .addContainerGap())
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                    .addContainerGap(15, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(51, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("    Componentes   ", jPanel6);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Posicionamento dos Elementos");

        jScrollPane7.setViewportView(imgAux);

        BtnPos_n3_n13.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n13ActionPerformed(evt);
            }
        });

        BtnPos_n3_n12.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n12ActionPerformed(evt);
            }
        });

        BtnPos_n3_n11.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n11ActionPerformed(evt);
            }
        });

        BtnPos_n3_n10.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n10ActionPerformed(evt);
            }
        });

        BtnPos_n3_n9.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n9ActionPerformed(evt);
            }
        });

        BtnPos_n3_n4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n4ActionPerformed(evt);
            }
        });

        BtnPos_n3_n3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n3ActionPerformed(evt);
            }
        });

        BtnPos_n3_n2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n2ActionPerformed(evt);
            }
        });

        BtnPos_n3_n1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n1ActionPerformed(evt);
            }
        });

        BtnPos_n3_0.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_0ActionPerformed(evt);
            }
        });

        BtnPos_n3_1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_1ActionPerformed(evt);
            }
        });

        BtnPos_n3_2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_2ActionPerformed(evt);
            }
        });

        BtnPos_n3_3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_3ActionPerformed(evt);
            }
        });

        BtnPos_n3_4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_4ActionPerformed(evt);
            }
        });

        BtnPos_n3_10.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_10ActionPerformed(evt);
            }
        });

        BtnPos_n3_11.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_11ActionPerformed(evt);
            }
        });

        BtnPos_n3_12.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_12ActionPerformed(evt);
            }
        });

        BtnPos_n3_13.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_13ActionPerformed(evt);
            }
        });

        BtnPos_n3_14.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_14ActionPerformed(evt);
            }
        });

        BtnPos_n2_n13.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n2_n13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_n13ActionPerformed(evt);
            }
        });

        BtnPos_n2_n12.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n2_n12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_n12ActionPerformed(evt);
            }
        });

        BtnPos_n2_n11.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n2_n11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_n11ActionPerformed(evt);
            }
        });

        BtnPos_n2_n10.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n2_n10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_n10ActionPerformed(evt);
            }
        });

        BtnPos_n2_n9.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n2_n9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_n9ActionPerformed(evt);
            }
        });

        BtnPos_n2_n4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n2_n4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_n4ActionPerformed(evt);
            }
        });

        BtnPos_n2_n3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n2_n3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_n3ActionPerformed(evt);
            }
        });

        BtnPos_n2_n2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n2_n2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_n2ActionPerformed(evt);
            }
        });

        BtnPos_n2_n1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n2_n1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_n1ActionPerformed(evt);
            }
        });

        BtnPos_n2_0.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n2_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_0ActionPerformed(evt);
            }
        });

        BtnPos_n2_1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n2_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_1ActionPerformed(evt);
            }
        });

        BtnPos_n2_2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n2_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_2ActionPerformed(evt);
            }
        });

        BtnPos_n2_3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n2_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_3ActionPerformed(evt);
            }
        });

        BtnPos_n2_4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n2_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_4ActionPerformed(evt);
            }
        });

        BtnPos_n2_10.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n2_10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_10ActionPerformed(evt);
            }
        });

        BtnPos_n2_11.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n2_11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_11ActionPerformed(evt);
            }
        });

        BtnPos_n2_12.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n2_12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_12ActionPerformed(evt);
            }
        });

        BtnPos_n2_13.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n2_13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_13ActionPerformed(evt);
            }
        });

        BtnPos_n2_14.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n2_14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n2_14ActionPerformed(evt);
            }
        });

        BtnPos_n1_n13.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n1_n13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_n13ActionPerformed(evt);
            }
        });

        BtnPos_n1_n12.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n1_n12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_n12ActionPerformed(evt);
            }
        });

        BtnPos_n1_n11.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n1_n11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_n11ActionPerformed(evt);
            }
        });

        BtnPos_n1_n10.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n1_n10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_n10ActionPerformed(evt);
            }
        });

        BtnPos_n1_n9.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n1_n9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_n9ActionPerformed(evt);
            }
        });

        BtnPos_n1_n4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n1_n4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_n4ActionPerformed(evt);
            }
        });

        BtnPos_n1_n3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n1_n3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_n3ActionPerformed(evt);
            }
        });

        BtnPos_n1_n2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n1_n2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_n2ActionPerformed(evt);
            }
        });

        BtnPos_n1_n1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n1_n1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_n1ActionPerformed(evt);
            }
        });

        BtnPos_n1_0.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n1_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_0ActionPerformed(evt);
            }
        });

        BtnPos_n1_1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n1_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_1ActionPerformed(evt);
            }
        });

        BtnPos_n1_2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n1_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_2ActionPerformed(evt);
            }
        });

        BtnPos_n1_3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n1_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_3ActionPerformed(evt);
            }
        });

        BtnPos_n1_4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n1_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_4ActionPerformed(evt);
            }
        });

        BtnPos_n1_10.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n1_10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_10ActionPerformed(evt);
            }
        });

        BtnPos_n1_11.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n1_11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_11ActionPerformed(evt);
            }
        });

        BtnPos_n1_12.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n1_12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_12ActionPerformed(evt);
            }
        });

        BtnPos_n1_13.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n1_13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_13ActionPerformed(evt);
            }
        });

        BtnPos_n1_14.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n1_14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n1_14ActionPerformed(evt);
            }
        });

        BtnPos_0_n13.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_0_n13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_n13ActionPerformed(evt);
            }
        });

        BtnPos_0_n12.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_0_n12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_n12ActionPerformed(evt);
            }
        });

        BtnPos_0_n11.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_0_n11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_n11ActionPerformed(evt);
            }
        });

        BtnPos_0_n10.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_0_n10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_n10ActionPerformed(evt);
            }
        });

        BtnPos_0_n9.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_0_n9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_n9ActionPerformed(evt);
            }
        });

        BtnPos_0_n4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_0_n4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_n4ActionPerformed(evt);
            }
        });

        BtnPos_0_n3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_0_n3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_n3ActionPerformed(evt);
            }
        });

        BtnPos_0_n2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_0_n2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_n2ActionPerformed(evt);
            }
        });

        BtnPos_0_n1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_0_n1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_n1ActionPerformed(evt);
            }
        });

        BtnPos_0_0.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_0_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_0ActionPerformed(evt);
            }
        });

        BtnPos_0_1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_0_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_1ActionPerformed(evt);
            }
        });

        BtnPos_0_2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_0_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_2ActionPerformed(evt);
            }
        });

        BtnPos_0_3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_0_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_3ActionPerformed(evt);
            }
        });

        BtnPos_0_4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_0_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_4ActionPerformed(evt);
            }
        });

        BtnPos_0_10.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_0_10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_10ActionPerformed(evt);
            }
        });

        BtnPos_0_11.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_0_11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_11ActionPerformed(evt);
            }
        });

        BtnPos_0_12.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_0_12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_12ActionPerformed(evt);
            }
        });

        BtnPos_0_13.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_0_13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_13ActionPerformed(evt);
            }
        });

        BtnPos_0_14.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_0_14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_0_14ActionPerformed(evt);
            }
        });

        BtnPos_1_n13.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_1_n13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_n13ActionPerformed(evt);
            }
        });

        BtnPos_1_n12.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_1_n12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_n12ActionPerformed(evt);
            }
        });

        BtnPos_1_n11.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_1_n11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_n11ActionPerformed(evt);
            }
        });

        BtnPos_1_n10.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_1_n10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_n10ActionPerformed(evt);
            }
        });

        BtnPos_1_n9.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_1_n9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_n9ActionPerformed(evt);
            }
        });

        BtnPos_1_n4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_1_n4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_n4ActionPerformed(evt);
            }
        });

        BtnPos_1_n3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_1_n3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_n3ActionPerformed(evt);
            }
        });

        BtnPos_1_n2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_1_n2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_n2ActionPerformed(evt);
            }
        });

        BtnPos_1_n1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_1_n1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_n1ActionPerformed(evt);
            }
        });

        BtnPos_1_0.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_1_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_0ActionPerformed(evt);
            }
        });

        BtnPos_1_1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_1_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_1ActionPerformed(evt);
            }
        });

        BtnPos_1_2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_1_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_2ActionPerformed(evt);
            }
        });

        BtnPos_1_3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_1_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_3ActionPerformed(evt);
            }
        });

        BtnPos_1_4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_1_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_4ActionPerformed(evt);
            }
        });

        BtnPos_1_10.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_1_10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_10ActionPerformed(evt);
            }
        });

        BtnPos_1_11.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_1_11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_11ActionPerformed(evt);
            }
        });

        BtnPos_1_12.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_1_12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_12ActionPerformed(evt);
            }
        });

        BtnPos_1_13.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_1_13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_13ActionPerformed(evt);
            }
        });

        BtnPos_1_14.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_1_14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_1_14ActionPerformed(evt);
            }
        });

        BtnPos_2_n13.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_2_n13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_n13ActionPerformed(evt);
            }
        });

        BtnPos_2_n12.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_2_n12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_n12ActionPerformed(evt);
            }
        });

        BtnPos_2_n11.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_2_n11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_n11ActionPerformed(evt);
            }
        });

        BtnPos_2_n10.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_2_n10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_n10ActionPerformed(evt);
            }
        });

        BtnPos_2_n9.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_2_n9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_n9ActionPerformed(evt);
            }
        });

        BtnPos_2_n4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_2_n4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_n4ActionPerformed(evt);
            }
        });

        BtnPos_2_n3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_2_n3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_n3ActionPerformed(evt);
            }
        });

        BtnPos_2_n2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_2_n2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_n2ActionPerformed(evt);
            }
        });

        BtnPos_2_n1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_2_n1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_n1ActionPerformed(evt);
            }
        });

        BtnPos_2_0.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_2_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_0ActionPerformed(evt);
            }
        });

        BtnPos_2_1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_2_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_1ActionPerformed(evt);
            }
        });

        BtnPos_2_2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_2_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_2ActionPerformed(evt);
            }
        });

        BtnPos_2_3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_2_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_3ActionPerformed(evt);
            }
        });

        BtnPos_2_4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_2_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_4ActionPerformed(evt);
            }
        });

        BtnPos_2_10.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_2_10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_10ActionPerformed(evt);
            }
        });

        BtnPos_2_11.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_2_11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_11ActionPerformed(evt);
            }
        });

        BtnPos_2_12.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_2_12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_12ActionPerformed(evt);
            }
        });

        BtnPos_2_13.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_2_13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_13ActionPerformed(evt);
            }
        });

        BtnPos_2_14.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_2_14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_2_14ActionPerformed(evt);
            }
        });

        BtnPos_3_n13.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n13ActionPerformed(evt);
            }
        });

        BtnPos_3_n12.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n12ActionPerformed(evt);
            }
        });

        BtnPos_3_n11.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n11ActionPerformed(evt);
            }
        });

        BtnPos_3_n10.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n10ActionPerformed(evt);
            }
        });

        BtnPos_3_n9.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n9ActionPerformed(evt);
            }
        });

        BtnPos_3_n4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n4ActionPerformed(evt);
            }
        });

        BtnPos_3_n3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n3ActionPerformed(evt);
            }
        });

        BtnPos_3_n2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n2ActionPerformed(evt);
            }
        });

        BtnPos_3_n1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n1ActionPerformed(evt);
            }
        });

        BtnPos_3_0.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_0ActionPerformed(evt);
            }
        });

        BtnPos_3_1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_1ActionPerformed(evt);
            }
        });

        BtnPos_3_2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_2ActionPerformed(evt);
            }
        });

        BtnPos_3_3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_3ActionPerformed(evt);
            }
        });

        BtnPos_3_4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_4ActionPerformed(evt);
            }
        });

        BtnPos_3_10.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_10ActionPerformed(evt);
            }
        });

        BtnPos_3_11.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_11ActionPerformed(evt);
            }
        });

        BtnPos_3_12.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_12ActionPerformed(evt);
            }
        });

        BtnPos_3_13.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_13ActionPerformed(evt);
            }
        });

        BtnPos_3_14.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_14ActionPerformed(evt);
            }
        });

        BtnPos_3_n14.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n14ActionPerformed(evt);
            }
        });

        BtnPos_3_n15.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n15ActionPerformed(evt);
            }
        });

        BtnPos_3_n16.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n16ActionPerformed(evt);
            }
        });

        BtnPos_3_n17.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n17ActionPerformed(evt);
            }
        });

        BtnPos_3_n18.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n18ActionPerformed(evt);
            }
        });

        BtnPos_3_n5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n5ActionPerformed(evt);
            }
        });

        BtnPos_3_n6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n6ActionPerformed(evt);
            }
        });

        BtnPos_3_n7.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n7ActionPerformed(evt);
            }
        });

        BtnPos_3_n8.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n8ActionPerformed(evt);
            }
        });

        BtnPos_3_5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_5ActionPerformed(evt);
            }
        });

        BtnPos_3_6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_6ActionPerformed(evt);
            }
        });

        BtnPos_3_7.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_7ActionPerformed(evt);
            }
        });

        BtnPos_3_8.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_8ActionPerformed(evt);
            }
        });

        BtnPos_3_9.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_9ActionPerformed(evt);
            }
        });

        BtnPos_3_15.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_15ActionPerformed(evt);
            }
        });

        BtnPos_3_16.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_16ActionPerformed(evt);
            }
        });

        BtnPos_3_17.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_17ActionPerformed(evt);
            }
        });

        BtnPos_3_18.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_18ActionPerformed(evt);
            }
        });

        BtnPos_3_19.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_19ActionPerformed(evt);
            }
        });

        BtnPos_3_n19.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n19ActionPerformed(evt);
            }
        });

        BtnPos_3_n20.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n20ActionPerformed(evt);
            }
        });

        BtnPos_3_n21.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n21ActionPerformed(evt);
            }
        });

        BtnPos_3_n22.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n22ActionPerformed(evt);
            }
        });

        BtnPos_3_n23.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n23ActionPerformed(evt);
            }
        });

        BtnPos_3_n24.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n24ActionPerformed(evt);
            }
        });

        BtnPos_3_n25.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n25ActionPerformed(evt);
            }
        });

        BtnPos_3_n26.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n26ActionPerformed(evt);
            }
        });

        BtnPos_3_n27.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n27ActionPerformed(evt);
            }
        });

        BtnPos_3_20.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_20ActionPerformed(evt);
            }
        });

        BtnPos_3_21.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_21ActionPerformed(evt);
            }
        });

        BtnPos_3_22.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_22ActionPerformed(evt);
            }
        });

        BtnPos_3_23.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_23ActionPerformed(evt);
            }
        });

        BtnPos_3_24.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_24ActionPerformed(evt);
            }
        });

        BtnPos_3_25.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_25ActionPerformed(evt);
            }
        });

        BtnPos_3_26.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_26ActionPerformed(evt);
            }
        });

        BtnPos_3_27.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_27ActionPerformed(evt);
            }
        });

        BtnPos_3_28.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_28ActionPerformed(evt);
            }
        });

        BtnPos_3_29.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_29ActionPerformed(evt);
            }
        });

        BtnPos_3_n28.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n28ActionPerformed(evt);
            }
        });

        BtnPos_3_n29.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n29ActionPerformed(evt);
            }
        });

        BtnPos_3_n30.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n30ActionPerformed(evt);
            }
        });

        BtnPos_3_n31.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n31ActionPerformed(evt);
            }
        });

        BtnPos_3_n32.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n32ActionPerformed(evt);
            }
        });

        BtnPos_3_n33.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n33ActionPerformed(evt);
            }
        });

        BtnPos_3_n34.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n34ActionPerformed(evt);
            }
        });

        BtnPos_3_n35.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n35ActionPerformed(evt);
            }
        });

        BtnPos_3_n36.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_n36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_n36ActionPerformed(evt);
            }
        });

        BtnPos_3_30.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_30ActionPerformed(evt);
            }
        });

        BtnPos_3_31.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_31ActionPerformed(evt);
            }
        });

        BtnPos_3_32.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_32ActionPerformed(evt);
            }
        });

        BtnPos_3_33.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_33ActionPerformed(evt);
            }
        });

        BtnPos_3_34.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_34ActionPerformed(evt);
            }
        });

        BtnPos_3_35.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_35ActionPerformed(evt);
            }
        });

        BtnPos_3_36.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_36ActionPerformed(evt);
            }
        });

        BtnPos_3_37.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_37ActionPerformed(evt);
            }
        });

        BtnPos_3_38.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_38ActionPerformed(evt);
            }
        });

        BtnPos_3_39.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_3_39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_3_39ActionPerformed(evt);
            }
        });

        BtnPos_n3_n14.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n14ActionPerformed(evt);
            }
        });

        BtnPos_n3_n15.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n15ActionPerformed(evt);
            }
        });

        BtnPos_n3_n16.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n16ActionPerformed(evt);
            }
        });

        BtnPos_n3_n17.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n17ActionPerformed(evt);
            }
        });

        BtnPos_n3_n18.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n18ActionPerformed(evt);
            }
        });

        BtnPos_n3_n5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n5ActionPerformed(evt);
            }
        });

        BtnPos_n3_n6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n6ActionPerformed(evt);
            }
        });

        BtnPos_n3_n7.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n7ActionPerformed(evt);
            }
        });

        BtnPos_n3_n8.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n8ActionPerformed(evt);
            }
        });

        BtnPos_n3_5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_5ActionPerformed(evt);
            }
        });

        BtnPos_n3_6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_6ActionPerformed(evt);
            }
        });

        BtnPos_n3_7.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_7ActionPerformed(evt);
            }
        });

        BtnPos_n3_8.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_8ActionPerformed(evt);
            }
        });

        BtnPos_n3_9.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_9ActionPerformed(evt);
            }
        });

        BtnPos_n3_15.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_15ActionPerformed(evt);
            }
        });

        BtnPos_n3_16.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_16ActionPerformed(evt);
            }
        });

        BtnPos_n3_17.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_17ActionPerformed(evt);
            }
        });

        BtnPos_n3_18.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_18ActionPerformed(evt);
            }
        });

        BtnPos_n3_19.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_19ActionPerformed(evt);
            }
        });

        BtnPos_n3_n19.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n19ActionPerformed(evt);
            }
        });

        BtnPos_n3_n20.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n20ActionPerformed(evt);
            }
        });

        BtnPos_n3_n21.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n21ActionPerformed(evt);
            }
        });

        BtnPos_n3_n22.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n22ActionPerformed(evt);
            }
        });

        BtnPos_n3_n23.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n23ActionPerformed(evt);
            }
        });

        BtnPos_n3_n24.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n24ActionPerformed(evt);
            }
        });

        BtnPos_n3_n25.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n25ActionPerformed(evt);
            }
        });

        BtnPos_n3_n26.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n26ActionPerformed(evt);
            }
        });

        BtnPos_n3_n27.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_n27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_n27ActionPerformed(evt);
            }
        });

        BtnPos_n3_20.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_20ActionPerformed(evt);
            }
        });

        BtnPos_n3_21.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_21ActionPerformed(evt);
            }
        });

        BtnPos_n3_22.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_22ActionPerformed(evt);
            }
        });

        BtnPos_n3_23.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_23ActionPerformed(evt);
            }
        });

        BtnPos_n3_24.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_24ActionPerformed(evt);
            }
        });

        BtnPos_n3_25.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_25ActionPerformed(evt);
            }
        });

        BtnPos_n3_26.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_26ActionPerformed(evt);
            }
        });

        BtnPos_n3_27.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_27ActionPerformed(evt);
            }
        });

        BtnPos_n3_28.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_28ActionPerformed(evt);
            }
        });

        BtnPos_n3_29.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnPos_n3_29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPos_n3_29ActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(jScrollPane7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n13, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n12, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n11, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n10, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n9, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n4, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n3, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n2, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n1, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_0, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_1, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_2, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_3, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_4, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_10, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_11, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_12, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_13, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_14, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n2_n13, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n2_n12, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n2_n11, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n2_n10, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n2_n9, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n2_n4, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n2_n3, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n2_n2, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n2_n1, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n2_0, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n2_1, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n2_2, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n2_3, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n2_4, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n2_10, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n2_11, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n2_12, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n2_13, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n2_14, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n1_n13, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n1_n12, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n1_n11, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n1_n10, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n1_n9, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n1_n4, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n1_n3, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n1_n2, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n1_n1, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n1_0, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n1_1, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n1_2, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n1_3, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n1_4, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n1_10, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n1_11, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n1_12, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n1_13, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n1_14, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_0_n13, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_0_n12, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_0_n11, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_0_n10, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_0_n9, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_0_n4, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_0_n3, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_0_n2, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_0_n1, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_0_0, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_0_1, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_0_2, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_0_3, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_0_4, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_0_10, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_0_11, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_0_12, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_0_13, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_0_14, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_1_n13, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_1_n12, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_1_n11, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_1_n10, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_1_n9, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_1_n4, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_1_n3, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_1_n2, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_1_n1, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_1_0, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_1_1, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_1_2, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_1_3, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_1_4, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_1_10, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_1_11, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_1_12, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_1_13, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_1_14, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_2_n13, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_2_n12, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_2_n11, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_2_n10, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_2_n9, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_2_n4, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_2_n3, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_2_n2, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_2_n1, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_2_0, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_2_1, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_2_2, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_2_3, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_2_4, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_2_10, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_2_11, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_2_12, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_2_13, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_2_14, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n13, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n12, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n11, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n10, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n9, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n4, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n3, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n2, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n1, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_0, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_1, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_2, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_3, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_4, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_10, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_11, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_12, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_13, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_14, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n14, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n15, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n16, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n17, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n18, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n5, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n6, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n7, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n8, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_5, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_6, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_7, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_8, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_9, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_15, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_16, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_17, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_18, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_19, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n19, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n20, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n21, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n22, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n23, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n24, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n25, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n26, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n27, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_20, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_21, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_22, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_23, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_24, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_25, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_26, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_27, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_28, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_29, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n28, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n29, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n30, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n31, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n32, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n33, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n34, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n35, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_n36, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_30, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_31, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_32, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_33, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_34, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_35, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_36, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_37, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_38, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_3_39, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n14, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n15, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n16, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n17, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n18, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n5, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n6, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n7, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n8, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_5, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_6, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_7, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_8, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_9, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_15, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_16, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_17, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_18, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_19, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n19, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n20, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n21, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n22, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n23, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n24, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n25, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n26, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_n27, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_20, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_21, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_22, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_23, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_24, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_25, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_26, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_27, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_28, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(BtnPos_n3_29, javax.swing.JLayeredPane.PALETTE_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(BtnPos_n3_n19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n3_n20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n3_n21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n3_n22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n3_n23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n3_n24, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n3_n25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n3_n26, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n3_n27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n3_20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n3_21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n3_22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n3_23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n3_24, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n3_25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n3_26, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n3_27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n3_28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPos_n3_29, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jLayeredPane1Layout.createSequentialGroup()
                            .addComponent(BtnPos_n3_n14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BtnPos_n3_n15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BtnPos_n3_n16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BtnPos_n3_n17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BtnPos_n3_n18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BtnPos_n3_n5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BtnPos_n3_n6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BtnPos_n3_n7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BtnPos_n3_n8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BtnPos_n3_5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BtnPos_n3_6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BtnPos_n3_7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BtnPos_n3_8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BtnPos_n3_9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BtnPos_n3_15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BtnPos_n3_16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BtnPos_n3_17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BtnPos_n3_18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BtnPos_n3_19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(BtnPos_3_n28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_n29, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_n30, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_n31, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_n32, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_n33, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_n34, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_n35, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_n36, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_30, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_31, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_32, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_33, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_34, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_35, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_36, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_37, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_38, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_39, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(BtnPos_3_n19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_n20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_n21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_n22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_n23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_n24, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_n25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_n26, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_n27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_24, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_26, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPos_3_29, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                    .addComponent(BtnPos_3_n14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(BtnPos_3_n15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(BtnPos_3_n16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(BtnPos_3_n17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(BtnPos_3_n18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(BtnPos_3_n5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(BtnPos_3_n6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(BtnPos_3_n7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(BtnPos_3_n8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(BtnPos_3_5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(BtnPos_3_6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(BtnPos_3_7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(BtnPos_3_8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(BtnPos_3_9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(BtnPos_3_15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(BtnPos_3_16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(BtnPos_3_17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(BtnPos_3_18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(BtnPos_3_19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addComponent(BtnPos_3_n13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_3_n12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_3_n11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_3_n10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_3_n9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_3_n4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_3_n3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_3_n2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_3_n1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_3_0, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_3_1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_3_2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_3_3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_3_4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_3_10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_3_11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_3_12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_3_13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_3_14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addComponent(BtnPos_n3_n13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_n3_n12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_n3_n11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_n3_n10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_n3_n9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_n3_n4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_n3_n3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_n3_n2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_n3_n1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_n3_0, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_n3_1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_n3_2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_n3_3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_n3_4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_n3_10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_n3_11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_n3_12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_n3_13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_n3_14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addComponent(BtnPos_2_n13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_2_n12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_2_n11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_2_n10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_2_n9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_2_n4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_2_n3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_2_n2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_2_n1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_2_0, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_2_4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_2_10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_2_11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_2_12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_2_13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnPos_2_14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                .addComponent(BtnPos_n2_n13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_n2_n12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_n2_n11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_n2_n10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_n2_n9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                .addComponent(BtnPos_n1_n13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_n1_n12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_n1_n11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_n1_n10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_n1_n9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                .addComponent(BtnPos_0_n13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_0_n12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_0_n11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_0_n10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_0_n9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                .addComponent(BtnPos_1_n13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_1_n12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_1_n11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_1_n10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_1_n9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                        .addComponent(BtnPos_0_n4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(BtnPos_0_n3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(BtnPos_0_n2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(BtnPos_0_n1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(BtnPos_0_0, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(BtnPos_0_1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                                .addComponent(BtnPos_n2_n4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(BtnPos_n2_n3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(BtnPos_n2_n2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(BtnPos_n2_n1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                                .addComponent(BtnPos_n1_n4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(BtnPos_n1_n3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(BtnPos_n1_n2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(BtnPos_n1_n1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                                .addComponent(BtnPos_n2_0, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(BtnPos_n2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                                .addComponent(BtnPos_n1_0, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(BtnPos_n1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                        .addComponent(BtnPos_0_2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(BtnPos_0_3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(BtnPos_0_4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                                            .addComponent(BtnPos_n2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(BtnPos_n2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(BtnPos_n2_4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                                            .addComponent(BtnPos_n1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(BtnPos_n1_3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(BtnPos_n1_4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                        .addComponent(BtnPos_n2_10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(BtnPos_n2_11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(BtnPos_n2_12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(BtnPos_n2_13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(BtnPos_n2_14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                        .addComponent(BtnPos_n1_10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(BtnPos_n1_11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(BtnPos_n1_12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(BtnPos_n1_13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(BtnPos_n1_14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                        .addComponent(BtnPos_0_10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(BtnPos_0_11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(BtnPos_0_12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(BtnPos_0_13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(BtnPos_0_14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                .addComponent(BtnPos_1_n4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_1_n3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_1_n2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_1_n1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_1_0, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_1_3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_1_4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_1_10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_1_11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_1_12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_1_13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BtnPos_1_14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnPos_n3_22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_24, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_26, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_29, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n24, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n26, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnPos_n3_7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnPos_n3_2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_0, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n3_n13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnPos_n2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n2_4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n2_10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n2_11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n2_12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n2_13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n2_14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n2_n4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n2_n3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n2_n2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n2_n1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n2_0, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n2_n9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n2_n10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n2_n11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n2_n12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n2_n13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnPos_n1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n1_3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n1_4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n1_10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n1_11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n1_12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n1_13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n1_14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n1_n4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n1_n3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n1_n2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n1_n1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n1_0, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n1_n9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n1_n10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n1_n11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n1_n12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_n1_n13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnPos_0_14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_0_13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_0_12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_0_11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_0_2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_0_3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_0_4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_0_10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(BtnPos_0_n4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnPos_0_n3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnPos_0_n2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnPos_0_1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnPos_0_0, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnPos_0_n1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(BtnPos_0_n9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnPos_0_n10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnPos_0_n11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnPos_0_n12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnPos_0_n13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnPos_1_n4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_1_n3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_1_n2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_1_n1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_1_0, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_1_3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_1_4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_1_n9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_1_10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_1_11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_1_12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_1_13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_1_14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_1_n10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_1_n11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_1_n12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_1_n13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnPos_2_n13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_2_n12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_2_n11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_2_n10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_2_n4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_2_n3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_2_n2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_2_n1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_2_0, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_2_4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_2_n9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_2_10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_2_11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_2_12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_2_13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_2_14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnPos_3_2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_0, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnPos_3_7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnPos_3_22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_24, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_26, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_29, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n24, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n26, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnPos_3_32, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_33, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_34, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_35, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_36, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_37, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_38, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_39, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n33, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n34, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n35, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n36, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_30, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_31, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n32, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n31, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n30, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n29, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnPos_3_n28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout compViewerLayout = new javax.swing.GroupLayout(compViewer);
        compViewer.setLayout(compViewerLayout);
        compViewerLayout.setHorizontalGroup(
            compViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(compViewerLayout.createSequentialGroup()
                .addComponent(compList, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 107, Short.MAX_VALUE))
        );
        compViewerLayout.setVerticalGroup(
            compViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(compViewerLayout.createSequentialGroup()
                .addComponent(compList, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 162, Short.MAX_VALUE))
        );

        jScrollPane8.setViewportView(compViewer);

        javax.swing.GroupLayout compPreviewPanelLayout = new javax.swing.GroupLayout(compPreviewPanel);
        compPreviewPanel.setLayout(compPreviewPanelLayout);
        compPreviewPanelLayout.setHorizontalGroup(
            compPreviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(compPreview, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
        );
        compPreviewPanelLayout.setVerticalGroup(
            compPreviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(compPreview, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
        );

        jScrollPane3.setViewportView(backgroundList);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(compPreviewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(compPreviewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        posTabGroup.addTab("tab1", jPanel9);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(posTabGroup)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(posTabGroup))
        );

        jTabbedPane1.addTab("Posicionamento", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Resumo do Jogo");

        labelResumo2.setEditable(false);
        JScrollPane10.setViewportView(labelResumo2);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Nome do Jogo");

        textNomeJogo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        textNomeJogo.setText("Digite o nome do jogo");
        textNomeJogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNomeJogoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Salvar Onde");

        labelSaveAddress.setEditable(false);
        labelSaveAddress.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelSaveAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labelSaveAddressActionPerformed(evt);
            }
        });

        btnOpenFileChooser.setText("Escolher Arquivo");
        btnOpenFileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenFileChooserActionPerformed(evt);
            }
        });

        checkboxAtalho.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        checkboxAtalho.setSelected(true);
        checkboxAtalho.setText("Criar atalho na área de trabalho?");

        btnVoltar4.setText("Voltar");
        btnVoltar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltar4ActionPerformed(evt);
            }
        });

        btnBuild.setText("Construir");
        btnBuild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuildActionPerformed(evt);
            }
        });

        progressBuilding.setToolTipText("");

        labelCommandLines.setText("Command Lines");

        checkboxAtalho1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        checkboxAtalho1.setText("Para web");
        checkboxAtalho1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxAtalho1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textNomeJogo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(progressBuilding, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnVoltar4)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuild))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btnOpenFileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelSaveAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkboxAtalho)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(labelCommandLines)
                            .addComponent(checkboxAtalho1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(154, 154, 154)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textNomeJogo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnOpenFileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelSaveAddress))
                .addGap(18, 18, 18)
                .addComponent(checkboxAtalho)
                .addGap(18, 18, 18)
                .addComponent(checkboxAtalho1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(labelCommandLines)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressBuilding, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBuild)
                        .addComponent(btnVoltar4)))
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap(40, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(325, Short.MAX_VALUE)))
        );

        progressBuilding.getAccessibleContext().setAccessibleName("");

        jTabbedPane1.addTab("   Finalizar e Construir   ", jPanel4);

        jMenu1.setText("Arquivo");

        NovoJogo.setText("Novo");
        NovoJogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NovoJogoActionPerformed(evt);
            }
        });
        jMenu1.add(NovoJogo);

        AbrirJogo.setText("Abrir");
        AbrirJogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirJogoActionPerformed(evt);
            }
        });
        jMenu1.add(AbrirJogo);

        SalvarJogo.setText("Salvar");
        SalvarJogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvarJogoActionPerformed(evt);
            }
        });
        jMenu1.add(SalvarJogo);

        SalvarComoJogo.setText("Salvar Como");
        SalvarComoJogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvarComoJogoActionPerformed(evt);
            }
        });
        jMenu1.add(SalvarComoJogo);

        Fechar.setText("Fechar");
        Fechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FecharActionPerformed(evt);
            }
        });
        jMenu1.add(Fechar);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Ajuda");

        ComoUsar.setText("Como Usar?");
        jMenu3.add(ComoUsar);

        Sobre.setText("Sobre");
        jMenu3.add(Sobre);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddElementosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddElementosActionPerformed
        //JFrame parentFrame = new JFrame();
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Escolha o arquivo");
        int status = chooser.showOpenDialog(null);
        
        if(status == JFileChooser.APPROVE_OPTION){
            File f = chooser.getSelectedFile();
            elemento.add(f.getAbsolutePath());

            textElemento = Integer.toString(elemento.size()) + " Elemento(s)\n";
            for (String s : elemento){
                textElemento += "\t" + s + "\t\n";
            }

            textResumo = textBackgroud + "\n\n" + textElemento + "\n\n" + textArvore + "\n\n" + textLixo;
            labelResumo2.setText(textResumo);
            ListCElemento.addElement(f.getAbsolutePath());


            //Add element to the component viewer     
            ListComponents.addElement(f.getAbsolutePath());
            ListComponentsUsed.addElement(f.getAbsolutePath());
            ListComponentsTemp.addElement(f.getAbsolutePath());
        }
    }//GEN-LAST:event_btnAddElementosActionPerformed

    private void btnAddArvoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddArvoresActionPerformed
        //JFrame parentFrame = new JFrame();
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Escolha o arquivo");
        int status = chooser.showOpenDialog(null);
        
        if(status == JFileChooser.APPROVE_OPTION){
            File f = chooser.getSelectedFile();
            arvore.add(f.getAbsolutePath());

            textArvore = Integer.toString(arvore.size()) + " Arvore(s)\n";
            for (String s : arvore){
                textArvore += "\t" + s + "\t\n";
            }

            textResumo = textBackgroud + "\n\n" + textElemento + "\n\n" + textArvore + "\n\n" + textLixo;
            labelResumo2.setText(textResumo);
            ListCArvore.addElement(f.getAbsolutePath());

            //Add element to the component viewer     
            ListComponents.addElement(f.getAbsolutePath());
            ListComponentsUsed.addElement(f.getAbsolutePath());
            ListComponentsTemp.addElement(f.getAbsolutePath());
        }
    }//GEN-LAST:event_btnAddArvoresActionPerformed

    private void btnAddLixoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddLixoActionPerformed
        //JFrame parentFrame = new JFrame();
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Escolha o arquivo");
        int status = chooser.showOpenDialog(null);
        
        if(status == JFileChooser.APPROVE_OPTION){
            File f = chooser.getSelectedFile();
            lixo.add(f.getAbsolutePath());

            textLixo = Integer.toString(lixo.size()) + " Lixo(s)\n";
            for (String s : lixo){
                textLixo += "\t" + s + "\t\n";
            }

            textResumo = textBackgroud + "\n\n" + textElemento + "\n\n" + textArvore + "\n\n" + textLixo;
            labelResumo2.setText(textResumo);
            ListCLixo.addElement(f.getAbsolutePath());

            //Add element to the component viewer     
            ListComponents.addElement(f.getAbsolutePath());
            ListComponentsUsed.addElement(f.getAbsolutePath());
            ListComponentsTemp.addElement(f.getAbsolutePath());
        }
    }//GEN-LAST:event_btnAddLixoActionPerformed

    private void btnAddBackgroundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBackgroundActionPerformed
        //JFrame parentFrame = new JFrame();
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Escolha o arquivo");
        int status = chooser.showOpenDialog(null);
        
        if(status == JFileChooser.APPROVE_OPTION){
            File f = chooser.getSelectedFile();
            background.add(f.getAbsolutePath());
            textBackgroud = Integer.toString(background.size()) + " Imagem(s) de Fundo\n";
            for (String s : background){
                textBackgroud += "\t" + s + "\t\n";
            }

            textResumo = textBackgroud + "\n\n" + textElemento + "\n\n" + textArvore + "\n\n" + textLixo;
            labelResumo2.setText(textResumo);
            ListCBackground.addElement(f.getAbsolutePath());
            matrix.add(SetBtnMatrix());


            //Adding reference in "posicionamento" tab
            /*JPanel jp1=new JPanel();
            int i=jPanel9.getComponentCount()-1;
            for(;i>=0;i--)
            {
                 jp1.addComponent(
                      ((Component) // this casts the clone back to component. This is maybe superfluous.
                       ((Cloneable)jPanel9.getComponent(i) // You have to ensure that all components that are returned are in fact instances of Cloneable.
                       ).clone()
                      ));
            }
            //after this I am setting bounds of jp1.
            this.add(jp1);
            posTabGroup.addTab(f.getName(), null, auxPosTab, null);*/
            //auxPosTab.setLayout(null);
        }
    }//GEN-LAST:event_btnAddBackgroundActionPerformed

    private void btnVoltar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltar4ActionPerformed
        jTabbedPane1.setSelectedIndex(2);        // TODO add your handling code here
    }//GEN-LAST:event_btnVoltar4ActionPerformed

    private void btnOpenFileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenFileChooserActionPerformed
        //JFrame parentFrame = new JFrame();
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Escolha a pasta");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int status = chooser.showOpenDialog(null);
        
        if(status == JFileChooser.APPROVE_OPTION){
            File f = chooser.getSelectedFile();
            labelSaveAddress.setText(f.getAbsolutePath());
        }
    }//GEN-LAST:event_btnOpenFileChooserActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        jTabbedPane1.setSelectedIndex(1);       // TODO add your handling code here
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void NovoJogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NovoJogoActionPerformed
        int yesno = JOptionPane.showConfirmDialog(null, "Voce realmente quer criar um jogo novo?", "Novo Jogo", JOptionPane.YES_NO_OPTION);
        
        if(yesno == 0){
            ListCBackground = new DefaultListModel();
            ListCElemento = new DefaultListModel();
            ListCArvore = new DefaultListModel();
            ListCLixo = new DefaultListModel();
            ListCElemento = new DefaultListModel();
            ListComponents = new DefaultListModel();
            ListComponentsUsed = new DefaultListModel();
            ListComponentsPrio = new DefaultListModel();
            
            labelResumo2.setText("");
            textNomeJogo.setText("Digite o nome do jogo");
            labelSaveAddress.setText("");
            checkboxAtalho.setSelected(true);

            background = new ArrayList<String>();
            elemento = new ArrayList<String>();
            arvore = new ArrayList<String>();
            lixo = new ArrayList<String>();
            
            matrix = new ArrayList<ArrayList<JToggleButton>>();

            textElemento = "";
            textArvore = "";
            textLixo = "";
            textResumo = "";

            jTabbedPane1.setSelectedIndex(0);
        }
        else{}
    }//GEN-LAST:event_NovoJogoActionPerformed

    private void AbrirJogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbrirJogoActionPerformed
        //JFrame parentFrame = new JFrame();
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Abrir");
        int status = chooser.showOpenDialog(null);
        
        if(status == JFileChooser.APPROVE_OPTION){
            File f = chooser.getSelectedFile();

            ArrayList <ArrayList <String>> data;
            try {
                if(f.getAbsolutePath() != ""){
                    ListCBackground = new DefaultListModel();
                    ListCElemento = new DefaultListModel();
                    ListCArvore = new DefaultListModel();
                    ListCLixo = new DefaultListModel();
                    ListCElemento = new DefaultListModel();
                    ListComponents = new DefaultListModel();
                    ListComponentsUsed = new DefaultListModel();
                    ListComponentsPrio = new DefaultListModel();
                    matrix = new ArrayList<ArrayList<JToggleButton>>();

                    labelResumo2.setText("");
                    background = new ArrayList<String>();
                    elemento = new ArrayList<String>();
                    arvore = new ArrayList<String>();
                    lixo = new ArrayList<String>();

                    data = general.AbrirGame(f.getAbsolutePath());
                    int i=0;
                    int j=0;
                    for(ArrayList <String> lista : data){
                        for (String s : lista){
                            if(i==0){
                                if (j==0)
                                    textNomeJogo.setText(s);
                                else
                                    labelSaveAddress.setText(s);
                                j++;
                            }
                            if(i==1){
                                background.add(s);
                            }
                            if(i==2){
                                elemento.add(s);
                            }
                            if(i==3){
                                arvore.add(s);
                            }
                            if(i==4){
                                lixo.add(s);
                            }
                            //System.out.println(s);

                        }
                        i++;
                    }


                    textBackgroud = Integer.toString(background.size()) + " Imagem(s) de Fundo\n";
                    for (String s : background){
                        ListCBackground.addElement(background);
                        textBackgroud += "\t" + s + "\t\n";
                    }

                    textElemento = Integer.toString(elemento.size()) + " Elemento(s)\n";
                    for (String s : elemento){
                        ListCElemento.addElement(elemento);
                        textElemento += "\t" + s + "\t\n";
                    }

                    textArvore = Integer.toString(arvore.size()) + " Arvore(s)\n";
                    for (String s : arvore){
                        ListCArvore.addElement(arvore);
                        textArvore += "\t" + s + "\t\n";
                    }

                    textLixo = Integer.toString(lixo.size()) + " Lixo(s)\n";
                    for (String s : lixo){
                        ListCLixo.addElement(lixo);
                        textLixo += "\t" + s + "\t\n";
                    }

                    textResumo = textBackgroud + "\n\n" + textElemento + "\n\n" + textArvore + "\n\n" + textLixo;
                    labelResumo2.setText(textResumo);
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_AbrirJogoActionPerformed

    private void FecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FecharActionPerformed
        dispose();
    }//GEN-LAST:event_FecharActionPerformed

    private void btnVoltar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltar3ActionPerformed
        jTabbedPane1.setSelectedIndex(1);        // TODO add your handling code here
    }//GEN-LAST:event_btnVoltar3ActionPerformed

    private void btnVoltar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltar2ActionPerformed
        jTabbedPane1.setSelectedIndex(0);        // TODO add your handling code here
    }//GEN-LAST:event_btnVoltar2ActionPerformed

    private void btnProx2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProx2ActionPerformed
        jTabbedPane1.setSelectedIndex(2);        // TODO add your handling code here
    }//GEN-LAST:event_btnProx2ActionPerformed

    private void btnProx3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProx3ActionPerformed
        jTabbedPane1.setSelectedIndex(3);        // TODO add your handling code here
    }//GEN-LAST:event_btnProx3ActionPerformed

    private void labelSaveAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labelSaveAddressActionPerformed
        // TODO add your handling code here
    }//GEN-LAST:event_labelSaveAddressActionPerformed

    private void SalvarJogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvarJogoActionPerformed
        boolean test;
        
        if (savepath == ""){
            JFrame parentFrame = new JFrame();
            JFileChooser fileChooser = new JFileChooser();
            int status = fileChooser.showOpenDialog(null);
        
            if(status == JFileChooser.APPROVE_OPTION){
                int userSelection = fileChooser.showSaveDialog(parentFrame);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    savepath = fileToSave.getAbsolutePath() + ".txt";
                }
            }
        }
        
        if (savepath != ""){
            try {
                test = general.SaveGame(savepath, background, elemento, arvore, lixo, labelSaveAddress.getText(), textNomeJogo.getText());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_SalvarJogoActionPerformed

    private void textNomeJogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNomeJogoActionPerformed
        // TODO add your handling code here
    }//GEN-LAST:event_textNomeJogoActionPerformed

    private void SalvarComoJogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvarComoJogoActionPerformed
        boolean changed = false;
        boolean test;
        
        JFrame parentFrame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar Como");   

        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            savepath = fileToSave.getAbsolutePath() + ".txt";
            changed= true;
        }
        
        if(changed){
            try {
                test = general.SaveGame(savepath, background, elemento, arvore, lixo, labelSaveAddress.getText(), textNomeJogo.getText());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_SalvarComoJogoActionPerformed

    private void btnBuildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuildActionPerformed
        // Ask to Save
        int yesno = JOptionPane.showConfirmDialog(null, "Deseja salvar estado atual?", "Salvar antes de construir", JOptionPane.YES_NO_OPTION);
        progressBuilding.setVisible(true);
        labelCommandLines.setVisible(true);
        labelCommandLines.setText("Inicializando Construtor ...");
        super.update(this.getGraphics());
        //TimeUnit.SECONDS.sleep(2);
        if(yesno == 0){
            boolean test;
            
            if (savepath == ""){
                JFrame parentFrame = new JFrame();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Salvar Como");
                
                int userSelection = fileChooser.showSaveDialog(parentFrame);
                
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    savepath = fileToSave.getAbsolutePath() + ".txt";
                }
            }
            
            if (savepath != ""){
                try {
                    test = general.SaveGame(savepath, background, elemento, arvore, lixo, labelSaveAddress.getText(), textNomeJogo.getText());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (plataform.contains("windows")){
            try {
                // Execute Build
                progressBuilding.setValue(16);
                labelCommandLines.setText("Criando pasta principal em \"" + labelSaveAddress.getText() + "\" ...");
                super.update(this.getGraphics());
                String com = "cmd /c mkdir \"" + labelSaveAddress.getText() + "\\" + textNomeJogo.getText() + "\"";
                String res = general.ExecuteBuild(background, elemento, arvore, lixo, labelSaveAddress.getText(), textNomeJogo.getText(), com);
                System.out.println(res);
                //TimeUnit.SECONDS.sleep(2);

                // Execute Build
                progressBuilding.setValue(32);
                labelCommandLines.setText("Criando pastas de imagens ...");
                super.update(this.getGraphics());
                com = "cmd /c mkdir \"" + labelSaveAddress.getText() + "\\" + textNomeJogo.getText() + "\\Imagens\\ComEdicao\"";
                res = general.ExecuteBuild(background, elemento, arvore, lixo, labelSaveAddress.getText(), textNomeJogo.getText(), com);
                System.out.println(res);
                com = "cmd /c mkdir \"" + labelSaveAddress.getText() + "\\" + textNomeJogo.getText() + "\\Imagens\\Elemento\"";
                res = general.ExecuteBuild(background, elemento, arvore, lixo, labelSaveAddress.getText(), textNomeJogo.getText(), com);
                System.out.println(res);
                com = "cmd /c mkdir \"" + labelSaveAddress.getText() + "\\" + textNomeJogo.getText() + "\\Imagens\\Arvore\"";
                res = general.ExecuteBuild(background, elemento, arvore, lixo, labelSaveAddress.getText(), textNomeJogo.getText(), com);
                System.out.println(res);
                com = "cmd /c mkdir \"" + labelSaveAddress.getText() + "\\" + textNomeJogo.getText() + "\\Imagens\\Lixo\"";
                res = general.ExecuteBuild(background, elemento, arvore, lixo, labelSaveAddress.getText(), textNomeJogo.getText(), com);
                System.out.println(res);
                //TimeUnit.SECONDS.sleep(2);

                // Execute Build
                progressBuilding.setValue(48);
                for (String s : background){
                    com = "cmd /c xcopy \"" + s + "\" \"" + labelSaveAddress.getText() + "\\" + textNomeJogo.getText() + "\\Imagens\\ComEdicao\"";
                    res = general.ExecuteBuild(background, elemento, arvore, lixo, labelSaveAddress.getText(), textNomeJogo.getText(), com);
                    System.out.println(res);
                }
                for (String s : elemento){
                    com = "cmd /c xcopy \"" + s + "\" \"" + labelSaveAddress.getText() + "\\" + textNomeJogo.getText() + "\\Imagens\\Elemento\"";
                    res = general.ExecuteBuild(background, elemento, arvore, lixo, labelSaveAddress.getText(), textNomeJogo.getText(), com);
                    System.out.println(res);
                }
                for (String s : arvore){
                    com = "cmd /c xcopy \"" + s + "\" \"" + labelSaveAddress.getText() + "\\" + textNomeJogo.getText() + "\\Imagens\\Arvore\"";
                    res = general.ExecuteBuild(background, elemento, arvore, lixo, labelSaveAddress.getText(), textNomeJogo.getText(), com);
                    System.out.println(res);
                }
                for (String s : lixo){
                    com = "cmd /c xcopy \"" + s + "\" \"" + labelSaveAddress.getText() + "\\" + textNomeJogo.getText() + "\\Imagens\\Lixo\"";
                    res = general.ExecuteBuild(background, elemento, arvore, lixo, labelSaveAddress.getText(), textNomeJogo.getText(), com);
                    System.out.println(res);
                }
                //TimeUnit.SECONDS.sleep(2);

                progressBuilding.setValue(65);
                labelCommandLines.setText("Referenciando arquivos ao Unity3D ...");
                super.update(this.getGraphics());
                Path currentRelativePath = Paths.get("");
                String refpath = currentRelativePath.toAbsolutePath().toString() + "\\";
                String refText = textNomeJogo.getText() + "\n" + labelSaveAddress.getText() + "\\" +textNomeJogo.getText() + "\\" ;
                DefaultListModel auxListUsed = ListComponentsUsed;
                DefaultListModel auxListPrio = ListComponentsPrio;
                ArrayList <String> RefList = general.GetRef();

                while (!(ListComponentsUsed.isEmpty())){
                    refText += "\n" + ListComponentsUsed.lastElement() + " | " + RefList.get(RefList.size()-1) + " | " + ListComponentsPrio.lastElement() + " | ";
                    if(arvore.contains(ListComponentsUsed.lastElement()))
                        refText += "arvore";
                    else if(elemento.contains(ListComponentsUsed.lastElement()))
                        refText += "elemento";
                    else if(lixo.contains(ListComponentsUsed.lastElement()))
                        refText += "lixo";
                    System.out.println(refText);
                    ListComponentsPrio.remove(ListComponentsPrio.indexOf(ListComponentsPrio.lastElement()));
                    ListComponentsUsed.remove(ListComponentsUsed.indexOf(ListComponentsUsed.lastElement()));
                    RefList.remove(RefList.size()-1);
                }
                ListComponentsUsed = auxListUsed;
                ListComponentsPrio = auxListPrio;
                //refText += "\n" + ;
                try {
                    FileWriter ref = new FileWriter(refpath + "ref.txt", false);
                    ref.write(refText);
                    ref.close();
                } catch (IOException e) {
                    System.out.println("Ocorreu um erro no arquivo referencia.");
                    e.printStackTrace();
                }
                //TimeUnit.SECONDS.sleep(2);

                // Execute Build
                progressBuilding.setValue(75);
                labelCommandLines.setText("Criando arquivo executavel e finalizando ...");
                super.update(this.getGraphics());
                //TimeUnit.SECONDS.sleep(2);

                String projFile = currentRelativePath.toAbsolutePath().toString() + "\\..\\..";
                com = unitypath + " -quit -batchmode -projectPath \"" + projFile + "\" -executeMethod BuildScript.ForWindows";
                res = general.ExecuteBuild(background, elemento, arvore, lixo, labelSaveAddress.getText(), textNomeJogo.getText(), com);
                System.out.println(res);
                progressBuilding.setValue(100);
                labelCommandLines.setText("\"" + textNomeJogo.getText() + "\" criado com sucesso!");

                JOptionPane.showMessageDialog(null, "Jogo Criado Com Sucesso!", "Construindo Jogo", JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Ocorreu um erro na construção! Por favor, tente novamente!", "Construindo Jogo", JOptionPane.INFORMATION_MESSAGE);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Ocorreu um erro na construção! Por favor, tente novamente!", "Construindo Jogo", JOptionPane.INFORMATION_MESSAGE);
            }
            progressBuilding.setVisible(false);
            labelCommandLines.setVisible(false);
        }
        
        if (plataform.contains("mac")){
            try {
                // Execute Build
                progressBuilding.setValue(16);
                labelCommandLines.setText("Criando pasta principal em \"" + labelSaveAddress.getText() + "/ ...");
                super.update(this.getGraphics());
                String com = "mkdir " + labelSaveAddress.getText() + "/" + textNomeJogo.getText() + "/";
                String res = general.ExecuteBuild(background, elemento, arvore, lixo, labelSaveAddress.getText(), textNomeJogo.getText(), com);
                System.out.println(res);
                TimeUnit.SECONDS.sleep(2);

                // Execute Build
                progressBuilding.setValue(32);
                labelCommandLines.setText("Criando pastas de imagens ...");
                super.update(this.getGraphics());
                com = "mkdir " + labelSaveAddress.getText() + "/" + textNomeJogo.getText() + "/Imagens/";
                res = general.ExecuteBuild(background, elemento, arvore, lixo, labelSaveAddress.getText(), textNomeJogo.getText(), com);
                com = "mkdir " + labelSaveAddress.getText() + "/" + textNomeJogo.getText() + "/Imagens/ComEdicao/";
                res = general.ExecuteBuild(background, elemento, arvore, lixo, labelSaveAddress.getText(), textNomeJogo.getText(), com);
                System.out.println(res);
                com = "mkdir " + labelSaveAddress.getText() + "/" + textNomeJogo.getText() + "/Imagens/Elemento/";
                res = general.ExecuteBuild(background, elemento, arvore, lixo, labelSaveAddress.getText(), textNomeJogo.getText(), com);
                System.out.println(res);
                com = "mkdir " + labelSaveAddress.getText() + "/" + textNomeJogo.getText() + "/Imagens/Arvore/";
                res = general.ExecuteBuild(background, elemento, arvore, lixo, labelSaveAddress.getText(), textNomeJogo.getText(), com);
                System.out.println(res);
                com = "mkdir " + labelSaveAddress.getText() + "/" + textNomeJogo.getText() + "/Imagens/Lixo/";
                res = general.ExecuteBuild(background, elemento, arvore, lixo, labelSaveAddress.getText(), textNomeJogo.getText(), com);
                System.out.println(res);
                TimeUnit.SECONDS.sleep(2);

                // Execute Build
                progressBuilding.setValue(48);
                //int i=0;
                for (String s : background){
                    com = "cp " + s + " " + labelSaveAddress.getText() + "/" + textNomeJogo.getText() + "/Imagens/ComEdicao/";
                    System.out.println(com);
                    Process p = Runtime.getRuntime().exec(com);
                    p.waitFor();
                }
                for (String s : elemento){
                    com = "cp " + s + " " + labelSaveAddress.getText() + "/" + textNomeJogo.getText() + "/Imagens/Elemento/";
                    System.out.println(com);
                    Process p = Runtime.getRuntime().exec(com);
                    p.waitFor();
                }
                for (String s : arvore){
                    com = "cp " + s + " " + labelSaveAddress.getText() + "/" + textNomeJogo.getText() + "/Imagens/Arvore/";
                    System.out.println(com);
                    Process p = Runtime.getRuntime().exec(com);
                    p.waitFor();
                }
                for (String s : lixo){
                    com = "cp " + s + " " + labelSaveAddress.getText() + "/" + textNomeJogo.getText() + "/Imagens/Lixo/";
                    System.out.println(com);
                    Process p = Runtime.getRuntime().exec(com);
                    p.waitFor();
                }
                TimeUnit.SECONDS.sleep(2);

                progressBuilding.setValue(65);
                labelCommandLines.setText("Referenciando arquivos ao Unity3D ...");
                Path currentRelativePath = Paths.get("");
                String refpath = currentRelativePath.toAbsolutePath().toString() + "/";
                String refText = textNomeJogo.getText() + "\n" + labelSaveAddress.getText() + "/" +textNomeJogo.getText() + "/" ;
                DefaultListModel auxListUsed = ListComponentsUsed;
                DefaultListModel auxListPrio = ListComponentsPrio;
                ArrayList <String> RefList = general.GetRef();

                while (!(ListComponentsUsed.isEmpty())){
                    refText += "\n" + ListComponentsUsed.lastElement() + " | " + RefList.get(RefList.size()-1) + " | " + ListComponentsPrio.lastElement() + " | ";
                    if(arvore.contains(ListComponentsUsed.lastElement()))
                        refText += "arvore";
                    else if(elemento.contains(ListComponentsUsed.lastElement()))
                        refText += "elemento";
                    else if(lixo.contains(ListComponentsUsed.lastElement()))
                        refText += "lixo";
                    System.out.println(refText);
                    ListComponentsPrio.remove(ListComponentsPrio.indexOf(ListComponentsPrio.lastElement()));
                    ListComponentsUsed.remove(ListComponentsUsed.indexOf(ListComponentsUsed.lastElement()));
                    RefList.remove(RefList.size()-1);
                }
                ListComponentsUsed = auxListUsed;
                ListComponentsPrio = auxListPrio;
                //refText += "\n" + ;
                try {
                    FileWriter ref = new FileWriter(refpath + "ref.txt", false);
                    ref.write(refText);
                    ref.close();
                } catch (IOException e) {
                    System.out.println("Ocorreu um erro no arquivo referencia.");
                    e.printStackTrace();
                }
                TimeUnit.SECONDS.sleep(2);

                // Execute Build
                progressBuilding.setValue(75);
                labelCommandLines.setText("Criando arquivo executavel e finalizando ...");
                super.update(this.getGraphics());
                TimeUnit.SECONDS.sleep(2);

                String projFile = currentRelativePath.toAbsolutePath().toString() + "/../..";
                
                com = unitypath + " -quit -batchmode -projectPath " + projFile + " -executeMethod BuildScript.ForMac";
                res = general.ExecuteBuild(background, elemento, arvore, lixo, labelSaveAddress.getText(), textNomeJogo.getText(), com);
                System.out.println(res);
                progressBuilding.setValue(100);
                labelCommandLines.setText("\"" + textNomeJogo.getText() + "\" criado com sucesso!");

                JOptionPane.showMessageDialog(null, "Jogo Criado Com Sucesso!", "Construindo Jogo", JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Ocorreu um erro na construção! Por favor, tente novamente!", "Construindo Jogo", JOptionPane.INFORMATION_MESSAGE);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Ocorreu um erro na construção! Por favor, tente novamente!", "Construindo Jogo", JOptionPane.INFORMATION_MESSAGE);
            }
            progressBuilding.setVisible(false);
            labelCommandLines.setVisible(false);
        }
    }//GEN-LAST:event_btnBuildActionPerformed

    private void BtnPos_n2_n4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_n4ActionPerformed
        BtnPos_n2_n4 = general.SetCell(BtnPos_n2_n4, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_n4ActionPerformed

    private void BtnPos_n2_n3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_n3ActionPerformed
        BtnPos_n2_n3 = general.SetCell(BtnPos_n2_n3, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_n3ActionPerformed

    private void BtnPos_n2_n2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_n2ActionPerformed
        BtnPos_n2_n2 = general.SetCell(BtnPos_n2_n2, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_n2ActionPerformed

    private void BtnPos_n2_n1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_n1ActionPerformed
        BtnPos_n2_n1 = general.SetCell(BtnPos_n2_n1, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_n1ActionPerformed

    private void BtnPos_n2_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_0ActionPerformed
        BtnPos_n2_0 = general.SetCell(BtnPos_n2_0, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_0ActionPerformed

    private void BtnPos_n2_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_1ActionPerformed
        BtnPos_n2_1 = general.SetCell(BtnPos_n2_1, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_1ActionPerformed

    private void BtnPos_n2_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_2ActionPerformed
        BtnPos_n2_2 = general.SetCell(BtnPos_n2_2, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_2ActionPerformed

    private void BtnPos_n2_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_3ActionPerformed
        BtnPos_n2_3 = general.SetCell(BtnPos_n2_3, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_3ActionPerformed

    private void BtnPos_n2_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_4ActionPerformed
        BtnPos_n2_4 = general.SetCell(BtnPos_n2_4, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_4ActionPerformed

    private void BtnPos_n1_n4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_n4ActionPerformed
        BtnPos_n1_n4 = general.SetCell(BtnPos_n1_n4, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_n4ActionPerformed

    private void BtnPos_n1_n3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_n3ActionPerformed
        BtnPos_n1_n3 = general.SetCell(BtnPos_n1_n3, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_n3ActionPerformed

    private void BtnPos_n1_n2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_n2ActionPerformed
        BtnPos_n1_n2 = general.SetCell(BtnPos_n1_n2, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_n2ActionPerformed

    private void BtnPos_n1_n1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_n1ActionPerformed
        BtnPos_n1_n1 = general.SetCell(BtnPos_n1_n1, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_n1ActionPerformed

    private void BtnPos_n1_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_0ActionPerformed
        BtnPos_n1_0 = general.SetCell(BtnPos_n1_0, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_0ActionPerformed

    private void BtnPos_n1_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_1ActionPerformed
        BtnPos_n1_1 = general.SetCell(BtnPos_n1_1, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_1ActionPerformed

    private void BtnPos_n1_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_2ActionPerformed
        BtnPos_n1_2 = general.SetCell(BtnPos_n1_2, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_2ActionPerformed

    private void BtnPos_n1_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_3ActionPerformed
        BtnPos_n1_3 = general.SetCell(BtnPos_n1_3, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_3ActionPerformed

    private void BtnPos_n1_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_4ActionPerformed
        BtnPos_n1_4 = general.SetCell(BtnPos_n1_4, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_4ActionPerformed

    private void BtnPos_0_n4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_n4ActionPerformed
        BtnPos_0_n4 = general.SetCell(BtnPos_0_n4, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_n4ActionPerformed

    private void BtnPos_0_n3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_n3ActionPerformed
        BtnPos_0_n3 = general.SetCell(BtnPos_0_n3, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_n3ActionPerformed

    private void BtnPos_0_n2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_n2ActionPerformed
        BtnPos_0_n2 = general.SetCell(BtnPos_0_n2, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_n2ActionPerformed

    private void BtnPos_0_n1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_n1ActionPerformed
        BtnPos_0_n1 = general.SetCell(BtnPos_0_n1, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_n1ActionPerformed

    private void BtnPos_0_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_0ActionPerformed
        BtnPos_0_0 = general.SetCell(BtnPos_0_0, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_0ActionPerformed

    private void BtnPos_0_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_1ActionPerformed
        BtnPos_0_1 = general.SetCell(BtnPos_0_1, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_1ActionPerformed

    private void BtnPos_0_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_2ActionPerformed
        BtnPos_0_2 = general.SetCell(BtnPos_0_2, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_2ActionPerformed

    private void BtnPos_0_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_3ActionPerformed
        BtnPos_0_3 = general.SetCell(BtnPos_0_3, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_3ActionPerformed

    private void BtnPos_0_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_4ActionPerformed
        BtnPos_0_4 = general.SetCell(BtnPos_0_4, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_4ActionPerformed

    private void BtnPos_1_n4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_n4ActionPerformed
        BtnPos_1_n4 = general.SetCell(BtnPos_1_n4, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_n4ActionPerformed

    private void BtnPos_1_n3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_n3ActionPerformed
        BtnPos_1_n3 = general.SetCell(BtnPos_1_n3, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_n3ActionPerformed

    private void BtnPos_1_n2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_n2ActionPerformed
        BtnPos_1_n2 = general.SetCell(BtnPos_1_n2, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_n2ActionPerformed

    private void BtnPos_1_n1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_n1ActionPerformed
        BtnPos_1_n1 = general.SetCell(BtnPos_1_n1, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_n1ActionPerformed

    private void BtnPos_1_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_0ActionPerformed
        BtnPos_1_0 = general.SetCell(BtnPos_1_0, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_0ActionPerformed

    private void BtnPos_1_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_1ActionPerformed
        BtnPos_1_1 = general.SetCell(BtnPos_1_1, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_1ActionPerformed

    private void BtnPos_1_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_2ActionPerformed
        BtnPos_1_2 = general.SetCell(BtnPos_1_2, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_2ActionPerformed

    private void BtnPos_1_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_3ActionPerformed
        BtnPos_1_3 = general.SetCell(BtnPos_1_3, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_3ActionPerformed

    private void BtnPos_1_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_4ActionPerformed
        BtnPos_1_4 = general.SetCell(BtnPos_1_4, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_4ActionPerformed

    private void BtnPos_2_n4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_n4ActionPerformed
        BtnPos_2_n4 = general.SetCell(BtnPos_2_n4, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_n4ActionPerformed

    private void BtnPos_2_n3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_n3ActionPerformed
        BtnPos_2_n3 = general.SetCell(BtnPos_2_n3, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_n3ActionPerformed

    private void BtnPos_2_n2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_n2ActionPerformed
        BtnPos_2_n2 = general.SetCell(BtnPos_2_n2, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_n2ActionPerformed

    private void BtnPos_2_n1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_n1ActionPerformed
        BtnPos_2_n1 = general.SetCell(BtnPos_2_n1, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_n1ActionPerformed

    private void BtnPos_2_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_0ActionPerformed
        BtnPos_2_0 = general.SetCell(BtnPos_2_0, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_0ActionPerformed

    private void BtnPos_2_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_1ActionPerformed
        BtnPos_2_1 = general.SetCell(BtnPos_2_1, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_1ActionPerformed

    private void BtnPos_2_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_2ActionPerformed
        BtnPos_2_2 = general.SetCell(BtnPos_2_2, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_2ActionPerformed

    private void BtnPos_2_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_3ActionPerformed
        BtnPos_2_3 = general.SetCell(BtnPos_2_3, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_3ActionPerformed

    private void BtnPos_2_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_4ActionPerformed
        BtnPos_2_4 = general.SetCell(BtnPos_2_4, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_4ActionPerformed

    private void BtnPos_n2_n5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_n5ActionPerformed
        BtnPos_n2_n5 = general.SetCell(BtnPos_n2_n5, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_n5ActionPerformed

    private void BtnPos_n2_n6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_n6ActionPerformed
        BtnPos_n2_n6 = general.SetCell(BtnPos_n2_n6, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_n6ActionPerformed

    private void BtnPos_n2_n7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_n7ActionPerformed
        BtnPos_n2_n7 = general.SetCell(BtnPos_n2_n7, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_n7ActionPerformed

    private void BtnPos_n2_n8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_n8ActionPerformed
        BtnPos_n2_n8 = general.SetCell(BtnPos_n2_n8, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_n8ActionPerformed

    private void BtnPos_n2_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_5ActionPerformed
        BtnPos_n2_5 = general.SetCell(BtnPos_n2_5, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_5ActionPerformed

    private void BtnPos_n2_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_6ActionPerformed
        BtnPos_n2_6 = general.SetCell(BtnPos_n2_6, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_6ActionPerformed

    private void BtnPos_n2_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_7ActionPerformed
        BtnPos_n2_7 = general.SetCell(BtnPos_n2_7, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_7ActionPerformed

    private void BtnPos_n2_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_8ActionPerformed
        BtnPos_n2_8 = general.SetCell(BtnPos_n2_8, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_8ActionPerformed

    private void BtnPos_n2_9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_9ActionPerformed
        BtnPos_n2_9 = general.SetCell(BtnPos_n2_9, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_9ActionPerformed

    private void BtnPos_n1_n5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_n5ActionPerformed
        BtnPos_n1_n5 = general.SetCell(BtnPos_n1_n5, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_n5ActionPerformed

    private void BtnPos_n1_n6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_n6ActionPerformed
        BtnPos_n1_n6 = general.SetCell(BtnPos_n1_n6, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_n6ActionPerformed

    private void BtnPos_n1_n7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_n7ActionPerformed
        BtnPos_n1_n7 = general.SetCell(BtnPos_n1_n7, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_n7ActionPerformed

    private void BtnPos_n1_n8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_n8ActionPerformed
        BtnPos_n1_n8 = general.SetCell(BtnPos_n1_n8, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_n8ActionPerformed

    private void BtnPos_n1_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_5ActionPerformed
        BtnPos_n1_5 = general.SetCell(BtnPos_n1_5, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_5ActionPerformed

    private void BtnPos_n1_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_6ActionPerformed
        BtnPos_n1_6 = general.SetCell(BtnPos_n1_6, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_6ActionPerformed

    private void BtnPos_n1_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_7ActionPerformed
        BtnPos_n1_7 = general.SetCell(BtnPos_n1_7, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_7ActionPerformed

    private void BtnPos_n1_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_8ActionPerformed
        BtnPos_n1_8 = general.SetCell(BtnPos_n1_8, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_8ActionPerformed

    private void BtnPos_n1_9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_9ActionPerformed
        BtnPos_n1_9 = general.SetCell(BtnPos_n1_9, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_9ActionPerformed

    private void BtnPos_0_n5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_n5ActionPerformed
        BtnPos_0_n5 = general.SetCell(BtnPos_0_n5, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_n5ActionPerformed

    private void BtnPos_0_n6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_n6ActionPerformed
        BtnPos_0_n6 = general.SetCell(BtnPos_0_n6, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_n6ActionPerformed

    private void BtnPos_0_n7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_n7ActionPerformed
        BtnPos_0_n7 = general.SetCell(BtnPos_0_n7, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_n7ActionPerformed

    private void BtnPos_0_n8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_n8ActionPerformed
        BtnPos_0_n8 = general.SetCell(BtnPos_0_n8, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_n8ActionPerformed

    private void BtnPos_0_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_5ActionPerformed
        BtnPos_0_5 = general.SetCell(BtnPos_0_5, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_5ActionPerformed

    private void BtnPos_0_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_6ActionPerformed
        BtnPos_0_6 = general.SetCell(BtnPos_0_6, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_6ActionPerformed

    private void BtnPos_0_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_7ActionPerformed
        BtnPos_0_7 = general.SetCell(BtnPos_0_7, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_7ActionPerformed

    private void BtnPos_0_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_8ActionPerformed
        BtnPos_0_8 = general.SetCell(BtnPos_0_8, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_8ActionPerformed

    private void BtnPos_0_9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_9ActionPerformed
        BtnPos_0_9 = general.SetCell(BtnPos_0_9, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_9ActionPerformed

    private void BtnPos_1_n5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_n5ActionPerformed
        BtnPos_1_n5 = general.SetCell(BtnPos_1_n5, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_n5ActionPerformed

    private void BtnPos_1_n6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_n6ActionPerformed
        BtnPos_1_n6 = general.SetCell(BtnPos_1_n6, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_n6ActionPerformed

    private void BtnPos_1_n7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_n7ActionPerformed
        BtnPos_1_n7 = general.SetCell(BtnPos_1_n7, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_n7ActionPerformed

    private void BtnPos_1_n8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_n8ActionPerformed
        BtnPos_1_n8 = general.SetCell(BtnPos_1_n8, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_n8ActionPerformed

    private void BtnPos_1_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_5ActionPerformed
        BtnPos_1_5 = general.SetCell(BtnPos_1_5, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_5ActionPerformed

    private void BtnPos_1_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_6ActionPerformed
        BtnPos_1_6 = general.SetCell(BtnPos_1_6, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_6ActionPerformed

    private void BtnPos_1_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_7ActionPerformed
        BtnPos_1_7 = general.SetCell(BtnPos_1_7, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_7ActionPerformed

    private void BtnPos_1_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_8ActionPerformed
        BtnPos_1_8 = general.SetCell(BtnPos_1_8, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_8ActionPerformed

    private void BtnPos_1_9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_9ActionPerformed
        BtnPos_1_9 = general.SetCell(BtnPos_1_9, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_9ActionPerformed

    private void BtnPos_2_n5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_n5ActionPerformed
        BtnPos_2_n5 = general.SetCell(BtnPos_2_n5, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_n5ActionPerformed

    private void BtnPos_2_n6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_n6ActionPerformed
        BtnPos_2_n6 = general.SetCell(BtnPos_2_n6, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_n6ActionPerformed

    private void BtnPos_2_n7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_n7ActionPerformed
        BtnPos_2_n7 = general.SetCell(BtnPos_2_n7, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_n7ActionPerformed

    private void BtnPos_2_n8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_n8ActionPerformed
        BtnPos_2_n8 = general.SetCell(BtnPos_2_n8, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_n8ActionPerformed

    private void BtnPos_2_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_5ActionPerformed
        BtnPos_2_5 = general.SetCell(BtnPos_2_5, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_5ActionPerformed

    private void BtnPos_2_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_6ActionPerformed
        BtnPos_2_6 = general.SetCell(BtnPos_2_6, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_6ActionPerformed

    private void BtnPos_2_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_7ActionPerformed
        BtnPos_2_7 = general.SetCell(BtnPos_2_7, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_7ActionPerformed

    private void BtnPos_2_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_8ActionPerformed
        BtnPos_2_8 = general.SetCell(BtnPos_2_8, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_8ActionPerformed

    private void BtnPos_2_9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_9ActionPerformed
        BtnPos_2_9 = general.SetCell(BtnPos_2_9, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_9ActionPerformed

    private void BtnPos_n2_n9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_n9ActionPerformed
        BtnPos_n2_n9 = general.SetCell(BtnPos_n2_n9, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_n9ActionPerformed

    private void BtnPos_n2_n10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_n10ActionPerformed
        BtnPos_n2_n10 = general.SetCell(BtnPos_n2_n10, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_n10ActionPerformed

    private void BtnPos_n2_n11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_n11ActionPerformed
        BtnPos_n2_n11 = general.SetCell(BtnPos_n2_n11, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_n11ActionPerformed

    private void BtnPos_n2_n12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_n12ActionPerformed
        BtnPos_n2_n12 = general.SetCell(BtnPos_n2_n12, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_n12ActionPerformed

    private void BtnPos_n2_n13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_n13ActionPerformed
        BtnPos_n2_n13 = general.SetCell(BtnPos_n2_n13, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_n13ActionPerformed

    private void BtnPos_n2_10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_10ActionPerformed
        BtnPos_n2_10 = general.SetCell(BtnPos_n2_10, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_10ActionPerformed

    private void BtnPos_n2_11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_11ActionPerformed
        BtnPos_n2_11 = general.SetCell(BtnPos_n2_11, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_11ActionPerformed

    private void BtnPos_n2_12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_12ActionPerformed
        BtnPos_n2_12 = general.SetCell(BtnPos_n2_12, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_12ActionPerformed

    private void BtnPos_n2_13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_13ActionPerformed
        BtnPos_n2_13 = general.SetCell(BtnPos_n2_13, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_13ActionPerformed

    private void BtnPos_n2_14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n2_14ActionPerformed
        BtnPos_n2_14 = general.SetCell(BtnPos_n2_14, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n2_14ActionPerformed

    private void BtnPos_n1_n9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_n9ActionPerformed
        BtnPos_n1_n9 = general.SetCell(BtnPos_n1_n9, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_n9ActionPerformed

    private void BtnPos_n1_n10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_n10ActionPerformed
        BtnPos_n1_n10 = general.SetCell(BtnPos_n1_n10, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_n10ActionPerformed

    private void BtnPos_n1_n11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_n11ActionPerformed
        BtnPos_n1_n11 = general.SetCell(BtnPos_n1_n11, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_n11ActionPerformed

    private void BtnPos_n1_n12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_n12ActionPerformed
        BtnPos_n1_n12 = general.SetCell(BtnPos_n1_n12, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_n12ActionPerformed

    private void BtnPos_n1_n13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_n13ActionPerformed
        BtnPos_n1_n13 = general.SetCell(BtnPos_n1_n13, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_n13ActionPerformed

    private void BtnPos_n1_10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_10ActionPerformed
        BtnPos_n1_10 = general.SetCell(BtnPos_n1_10, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_10ActionPerformed

    private void BtnPos_n1_11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_11ActionPerformed
        BtnPos_n1_11 = general.SetCell(BtnPos_n1_11, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_11ActionPerformed

    private void BtnPos_n1_12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_12ActionPerformed
        BtnPos_n1_12 = general.SetCell(BtnPos_n1_12, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_12ActionPerformed

    private void BtnPos_n1_13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_13ActionPerformed
        BtnPos_n1_13 = general.SetCell(BtnPos_n1_13, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_13ActionPerformed

    private void BtnPos_n1_14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n1_14ActionPerformed
        BtnPos_n1_14 = general.SetCell(BtnPos_n1_14, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n1_14ActionPerformed

    private void BtnPos_0_n9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_n9ActionPerformed
        BtnPos_0_n9 = general.SetCell(BtnPos_0_n9, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_n9ActionPerformed

    private void BtnPos_0_n10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_n10ActionPerformed
        BtnPos_0_n10 = general.SetCell(BtnPos_0_n10, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_n10ActionPerformed

    private void BtnPos_0_n11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_n11ActionPerformed
        BtnPos_0_n11 = general.SetCell(BtnPos_0_n11, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_n11ActionPerformed

    private void BtnPos_0_n12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_n12ActionPerformed
        BtnPos_0_n12 = general.SetCell(BtnPos_0_n12, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_n12ActionPerformed

    private void BtnPos_0_n13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_n13ActionPerformed
        BtnPos_0_n13 = general.SetCell(BtnPos_0_n13, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_n13ActionPerformed

    private void BtnPos_0_10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_10ActionPerformed
        BtnPos_0_10 = general.SetCell(BtnPos_0_10, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_10ActionPerformed

    private void BtnPos_0_11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_11ActionPerformed
        BtnPos_0_11 = general.SetCell(BtnPos_0_11, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_11ActionPerformed

    private void BtnPos_0_12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_12ActionPerformed
        BtnPos_0_12 = general.SetCell(BtnPos_0_12, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_12ActionPerformed

    private void BtnPos_0_13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_13ActionPerformed
        BtnPos_0_13 = general.SetCell(BtnPos_0_13, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_13ActionPerformed

    private void BtnPos_0_14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_0_14ActionPerformed
        BtnPos_0_14 = general.SetCell(BtnPos_0_14, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_0_14ActionPerformed

    private void BtnPos_1_n9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_n9ActionPerformed
        BtnPos_1_n9 = general.SetCell(BtnPos_1_n9, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_n9ActionPerformed

    private void BtnPos_1_n10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_n10ActionPerformed
        BtnPos_1_n10 = general.SetCell(BtnPos_1_n10, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_n10ActionPerformed

    private void BtnPos_1_n11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_n11ActionPerformed
        BtnPos_1_n11 = general.SetCell(BtnPos_1_n11, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_n11ActionPerformed

    private void BtnPos_1_n12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_n12ActionPerformed
        BtnPos_1_n12 = general.SetCell(BtnPos_1_n12, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_n12ActionPerformed

    private void BtnPos_1_n13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_n13ActionPerformed
        BtnPos_1_n13 = general.SetCell(BtnPos_1_n13, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_n13ActionPerformed

    private void BtnPos_1_10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_10ActionPerformed
        BtnPos_1_10 = general.SetCell(BtnPos_1_10, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_10ActionPerformed

    private void BtnPos_1_11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_11ActionPerformed
        BtnPos_1_11 = general.SetCell(BtnPos_1_11, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_11ActionPerformed

    private void BtnPos_1_12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_12ActionPerformed
        BtnPos_1_12 = general.SetCell(BtnPos_1_12, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_12ActionPerformed

    private void BtnPos_1_13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_13ActionPerformed
        BtnPos_1_13 = general.SetCell(BtnPos_1_13, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_13ActionPerformed

    private void BtnPos_1_14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_1_14ActionPerformed
        BtnPos_1_14 = general.SetCell(BtnPos_1_14, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_1_14ActionPerformed

    private void BtnPos_2_n9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_n9ActionPerformed
        BtnPos_2_n9 = general.SetCell(BtnPos_2_n9, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_n9ActionPerformed

    private void BtnPos_2_n10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_n10ActionPerformed
        BtnPos_2_n10 = general.SetCell(BtnPos_2_n10, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_n10ActionPerformed

    private void BtnPos_2_n11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_n11ActionPerformed
        BtnPos_2_n11 = general.SetCell(BtnPos_2_n11, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_n11ActionPerformed

    private void BtnPos_2_n12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_n12ActionPerformed
        BtnPos_2_n12 = general.SetCell(BtnPos_2_n12, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_n12ActionPerformed

    private void BtnPos_2_n13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_n13ActionPerformed
        BtnPos_2_n13 = general.SetCell(BtnPos_2_n13, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_n13ActionPerformed

    private void BtnPos_2_10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_10ActionPerformed
        BtnPos_2_10 = general.SetCell(BtnPos_2_10, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_10ActionPerformed

    private void BtnPos_2_11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_11ActionPerformed
        BtnPos_2_11 = general.SetCell(BtnPos_2_11, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_11ActionPerformed

    private void BtnPos_2_12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_12ActionPerformed
        BtnPos_2_12 = general.SetCell(BtnPos_2_12, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_12ActionPerformed

    private void BtnPos_2_13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_13ActionPerformed
        BtnPos_2_13 = general.SetCell(BtnPos_2_13, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_13ActionPerformed

    private void BtnPos_2_14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_2_14ActionPerformed
        BtnPos_2_14 = general.SetCell(BtnPos_2_14, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_2_14ActionPerformed

    private void BtnPos_n3_n13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n13ActionPerformed
        BtnPos_n3_n13 = general.SetCell(BtnPos_n3_n13, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n13ActionPerformed

    private void BtnPos_n3_n12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n12ActionPerformed
        BtnPos_n3_n12 = general.SetCell(BtnPos_n3_n12, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n12ActionPerformed

    private void BtnPos_n3_n11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n11ActionPerformed
        BtnPos_n3_n11 = general.SetCell(BtnPos_n3_n11, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n11ActionPerformed

    private void BtnPos_n3_n10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n10ActionPerformed
        BtnPos_n3_n10 = general.SetCell(BtnPos_n3_n10, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n10ActionPerformed

    private void BtnPos_n3_n9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n9ActionPerformed
        BtnPos_n3_n9 = general.SetCell(BtnPos_n3_n9, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n9ActionPerformed

    private void BtnPos_n3_n4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n4ActionPerformed
        BtnPos_n3_n4 = general.SetCell(BtnPos_n3_n4, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n4ActionPerformed

    private void BtnPos_n3_n3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n3ActionPerformed
        BtnPos_n3_n3 = general.SetCell(BtnPos_n3_n3, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n3ActionPerformed

    private void BtnPos_n3_n2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n2ActionPerformed
        BtnPos_n3_n2 = general.SetCell(BtnPos_n3_n2, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n2ActionPerformed

    private void BtnPos_n3_n1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n1ActionPerformed
        BtnPos_n3_n1 = general.SetCell(BtnPos_n3_n1, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n1ActionPerformed

    private void BtnPos_n3_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_0ActionPerformed
        BtnPos_n3_0 = general.SetCell(BtnPos_n3_0, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_0ActionPerformed

    private void BtnPos_n3_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_1ActionPerformed
        BtnPos_n3_1 = general.SetCell(BtnPos_n3_1, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_1ActionPerformed

    private void BtnPos_n3_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_2ActionPerformed
        BtnPos_n3_2 = general.SetCell(BtnPos_n3_2, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_2ActionPerformed

    private void BtnPos_n3_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_3ActionPerformed
        BtnPos_n3_3 = general.SetCell(BtnPos_n3_3, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_3ActionPerformed

    private void BtnPos_n3_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_4ActionPerformed
        BtnPos_n3_4 = general.SetCell(BtnPos_n3_4, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_4ActionPerformed

    private void BtnPos_n3_10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_10ActionPerformed
        BtnPos_n3_10 = general.SetCell(BtnPos_n3_10, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_10ActionPerformed

    private void BtnPos_n3_11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_11ActionPerformed
        BtnPos_n3_11 = general.SetCell(BtnPos_n3_11, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_11ActionPerformed

    private void BtnPos_n3_12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_12ActionPerformed
        BtnPos_n3_12 = general.SetCell(BtnPos_n3_12, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_12ActionPerformed

    private void BtnPos_n3_13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_13ActionPerformed
        BtnPos_n3_13 = general.SetCell(BtnPos_n3_13, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_13ActionPerformed

    private void BtnPos_n3_14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_14ActionPerformed
        BtnPos_n3_14 = general.SetCell(BtnPos_n3_14, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_14ActionPerformed

    private void BtnPos_3_n13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n13ActionPerformed
        BtnPos_3_n13 = general.SetCell(BtnPos_3_n13, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n13ActionPerformed

    private void BtnPos_3_n12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n12ActionPerformed
        BtnPos_3_n12 = general.SetCell(BtnPos_3_n12, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n12ActionPerformed

    private void BtnPos_3_n11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n11ActionPerformed
        BtnPos_3_n11 = general.SetCell(BtnPos_3_n11, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n11ActionPerformed

    private void BtnPos_3_n10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n10ActionPerformed
        BtnPos_3_n10 = general.SetCell(BtnPos_3_n10, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n10ActionPerformed

    private void BtnPos_3_n9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n9ActionPerformed
        BtnPos_3_n9 = general.SetCell(BtnPos_3_n9, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n9ActionPerformed

    private void BtnPos_3_n4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n4ActionPerformed
        BtnPos_3_n4 = general.SetCell(BtnPos_3_n4, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n4ActionPerformed

    private void BtnPos_3_n3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n3ActionPerformed
        BtnPos_3_n3 = general.SetCell(BtnPos_3_n3, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n3ActionPerformed

    private void BtnPos_3_n2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n2ActionPerformed
        BtnPos_3_n2 = general.SetCell(BtnPos_3_n2, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n2ActionPerformed

    private void BtnPos_3_n1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n1ActionPerformed
        BtnPos_3_n1 = general.SetCell(BtnPos_3_n1, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n1ActionPerformed

    private void BtnPos_3_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_0ActionPerformed
        BtnPos_3_0 = general.SetCell(BtnPos_3_0, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_0ActionPerformed

    private void BtnPos_3_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_1ActionPerformed
        BtnPos_3_1 = general.SetCell(BtnPos_3_1, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_1ActionPerformed

    private void BtnPos_3_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_2ActionPerformed
        BtnPos_3_2 = general.SetCell(BtnPos_3_2, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_2ActionPerformed

    private void BtnPos_3_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_3ActionPerformed
        BtnPos_3_3 = general.SetCell(BtnPos_3_3, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_3ActionPerformed

    private void BtnPos_3_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_4ActionPerformed
        BtnPos_3_4 = general.SetCell(BtnPos_3_4, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_4ActionPerformed

    private void BtnPos_3_10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_10ActionPerformed
        BtnPos_3_10 = general.SetCell(BtnPos_3_10, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_10ActionPerformed

    private void BtnPos_3_11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_11ActionPerformed
        BtnPos_3_11 = general.SetCell(BtnPos_3_11, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_11ActionPerformed

    private void BtnPos_3_12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_12ActionPerformed
        BtnPos_3_12 = general.SetCell(BtnPos_3_12, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_12ActionPerformed

    private void BtnPos_3_13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_13ActionPerformed
        BtnPos_3_13 = general.SetCell(BtnPos_3_13, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_13ActionPerformed

    private void BtnPos_3_14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_14ActionPerformed
        BtnPos_3_14 = general.SetCell(BtnPos_3_14, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
ArrayList<JToggleButton> aux = general.GetMatrix();
matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_14ActionPerformed

    private void BtnPos_3_n14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n14ActionPerformed
        BtnPos_3_n14 = general.SetCell(BtnPos_3_n14, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n14ActionPerformed

    private void BtnPos_3_n15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n15ActionPerformed
        BtnPos_3_n15 = general.SetCell(BtnPos_3_n15, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n15ActionPerformed

    private void BtnPos_3_n16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n16ActionPerformed
        BtnPos_3_n16 = general.SetCell(BtnPos_3_n16, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n16ActionPerformed

    private void BtnPos_3_n17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n17ActionPerformed
        BtnPos_3_n17 = general.SetCell(BtnPos_3_n17, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n17ActionPerformed

    private void BtnPos_3_n18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n18ActionPerformed
        BtnPos_3_n18 = general.SetCell(BtnPos_3_n18, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n18ActionPerformed

    private void BtnPos_3_n5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n5ActionPerformed
        BtnPos_3_n5 = general.SetCell(BtnPos_3_n5, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n5ActionPerformed

    private void BtnPos_3_n6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n6ActionPerformed
        BtnPos_3_n6 = general.SetCell(BtnPos_3_n6, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n6ActionPerformed

    private void BtnPos_3_n7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n7ActionPerformed
        BtnPos_3_n7 = general.SetCell(BtnPos_3_n7, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n7ActionPerformed

    private void BtnPos_3_n8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n8ActionPerformed
        BtnPos_3_n8 = general.SetCell(BtnPos_3_n8, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n8ActionPerformed

    private void BtnPos_3_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_5ActionPerformed
        BtnPos_3_5 = general.SetCell(BtnPos_3_5, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_5ActionPerformed

    private void BtnPos_3_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_6ActionPerformed
        BtnPos_3_6 = general.SetCell(BtnPos_3_6, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_6ActionPerformed

    private void BtnPos_3_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_7ActionPerformed
        BtnPos_3_7 = general.SetCell(BtnPos_3_7, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_7ActionPerformed

    private void BtnPos_3_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_8ActionPerformed
        BtnPos_3_8 = general.SetCell(BtnPos_3_8, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_8ActionPerformed

    private void BtnPos_3_9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_9ActionPerformed
        BtnPos_3_9 = general.SetCell(BtnPos_3_9, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_9ActionPerformed

    private void BtnPos_3_15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_15ActionPerformed
        BtnPos_3_15 = general.SetCell(BtnPos_3_15, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_15ActionPerformed

    private void BtnPos_3_16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_16ActionPerformed
        BtnPos_3_16 = general.SetCell(BtnPos_3_16, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_16ActionPerformed

    private void BtnPos_3_17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_17ActionPerformed
        BtnPos_3_17 = general.SetCell(BtnPos_3_17, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_17ActionPerformed

    private void BtnPos_3_18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_18ActionPerformed
        BtnPos_3_18 = general.SetCell(BtnPos_3_18, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_18ActionPerformed

    private void BtnPos_3_19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_19ActionPerformed
        BtnPos_3_19 = general.SetCell(BtnPos_3_19, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_19ActionPerformed

    private void BtnPos_3_n19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n19ActionPerformed
        BtnPos_3_n19 = general.SetCell(BtnPos_3_n19, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n19ActionPerformed

    private void BtnPos_3_n20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n20ActionPerformed
        BtnPos_3_n20 = general.SetCell(BtnPos_3_n20, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n20ActionPerformed

    private void BtnPos_3_n21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n21ActionPerformed
        BtnPos_3_n21 = general.SetCell(BtnPos_3_n21, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n21ActionPerformed

    private void BtnPos_3_n22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n22ActionPerformed
        BtnPos_3_n22 = general.SetCell(BtnPos_3_n22, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n22ActionPerformed

    private void BtnPos_3_n23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n23ActionPerformed
        BtnPos_3_n23 = general.SetCell(BtnPos_3_n23, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n23ActionPerformed

    private void BtnPos_3_n24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n24ActionPerformed
        BtnPos_3_n24 = general.SetCell(BtnPos_3_n24, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n24ActionPerformed

    private void BtnPos_3_n25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n25ActionPerformed
        BtnPos_3_n25 = general.SetCell(BtnPos_3_n25, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n25ActionPerformed

    private void BtnPos_3_n26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n26ActionPerformed
        BtnPos_3_n26 = general.SetCell(BtnPos_3_n26, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n26ActionPerformed

    private void BtnPos_3_n27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n27ActionPerformed
        BtnPos_3_n27 = general.SetCell(BtnPos_3_n27, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n27ActionPerformed

    private void BtnPos_3_20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_20ActionPerformed
        BtnPos_3_20 = general.SetCell(BtnPos_3_20, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_20ActionPerformed

    private void BtnPos_3_21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_21ActionPerformed
        BtnPos_3_21 = general.SetCell(BtnPos_3_21, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_21ActionPerformed

    private void BtnPos_3_22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_22ActionPerformed
        BtnPos_3_22 = general.SetCell(BtnPos_3_22, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_22ActionPerformed

    private void BtnPos_3_23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_23ActionPerformed
        BtnPos_3_23 = general.SetCell(BtnPos_3_23, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_23ActionPerformed

    private void BtnPos_3_24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_24ActionPerformed
        BtnPos_3_24 = general.SetCell(BtnPos_3_24, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_24ActionPerformed

    private void BtnPos_3_25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_25ActionPerformed
        BtnPos_3_25 = general.SetCell(BtnPos_3_25, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_25ActionPerformed

    private void BtnPos_3_26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_26ActionPerformed
        BtnPos_3_26 = general.SetCell(BtnPos_3_26, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_26ActionPerformed

    private void BtnPos_3_27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_27ActionPerformed
        BtnPos_3_27 = general.SetCell(BtnPos_3_27, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_27ActionPerformed

    private void BtnPos_3_28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_28ActionPerformed
        BtnPos_3_28 = general.SetCell(BtnPos_3_28, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_28ActionPerformed

    private void BtnPos_3_29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_29ActionPerformed
        BtnPos_3_29 = general.SetCell(BtnPos_3_29, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_29ActionPerformed

    private void BtnPos_3_n28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n28ActionPerformed
        BtnPos_3_n28 = general.SetCell(BtnPos_3_n28, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n28ActionPerformed

    private void BtnPos_3_n29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n29ActionPerformed
        BtnPos_3_n29 = general.SetCell(BtnPos_3_n29, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n29ActionPerformed

    private void BtnPos_3_n30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n30ActionPerformed
        BtnPos_3_n30 = general.SetCell(BtnPos_3_n30, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n30ActionPerformed

    private void BtnPos_3_n31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n31ActionPerformed
        BtnPos_3_n31 = general.SetCell(BtnPos_3_n31, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n31ActionPerformed

    private void BtnPos_3_n32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n32ActionPerformed
        BtnPos_3_n32 = general.SetCell(BtnPos_3_n32, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n32ActionPerformed

    private void BtnPos_3_n33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n33ActionPerformed
        BtnPos_3_n33 = general.SetCell(BtnPos_3_n33, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n33ActionPerformed

    private void BtnPos_3_n34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n34ActionPerformed
        BtnPos_3_n34 = general.SetCell(BtnPos_3_n34, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n34ActionPerformed

    private void BtnPos_3_n35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n35ActionPerformed
        BtnPos_3_n35 = general.SetCell(BtnPos_3_n35, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n35ActionPerformed

    private void BtnPos_3_n36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_n36ActionPerformed
        BtnPos_3_n36 = general.SetCell(BtnPos_3_n36, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_n36ActionPerformed

    private void BtnPos_3_30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_30ActionPerformed
        BtnPos_3_30 = general.SetCell(BtnPos_3_30, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_30ActionPerformed

    private void BtnPos_3_31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_31ActionPerformed
        BtnPos_3_31 = general.SetCell(BtnPos_3_31, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_31ActionPerformed

    private void BtnPos_3_32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_32ActionPerformed
        BtnPos_3_32 = general.SetCell(BtnPos_3_32, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_32ActionPerformed

    private void BtnPos_3_33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_33ActionPerformed
        BtnPos_3_33 = general.SetCell(BtnPos_3_33, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_33ActionPerformed

    private void BtnPos_3_34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_34ActionPerformed
        BtnPos_3_34 = general.SetCell(BtnPos_3_34, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_34ActionPerformed

    private void BtnPos_3_35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_35ActionPerformed
        BtnPos_3_35 = general.SetCell(BtnPos_3_35, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_35ActionPerformed

    private void BtnPos_3_36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_36ActionPerformed
        BtnPos_3_36 = general.SetCell(BtnPos_3_36, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_36ActionPerformed

    private void BtnPos_3_37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_37ActionPerformed
        BtnPos_3_37 = general.SetCell(BtnPos_3_37, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_37ActionPerformed

    private void BtnPos_3_38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_38ActionPerformed
        BtnPos_3_38 = general.SetCell(BtnPos_3_38, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_38ActionPerformed

    private void BtnPos_3_39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_3_39ActionPerformed
        BtnPos_3_39 = general.SetCell(BtnPos_3_39, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_3_39ActionPerformed

    private void BtnPos_n3_n14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n14ActionPerformed
        BtnPos_n3_n14 = general.SetCell(BtnPos_n3_n14, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n14ActionPerformed

    private void BtnPos_n3_n15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n15ActionPerformed
        BtnPos_n3_n15 = general.SetCell(BtnPos_n3_n15, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n15ActionPerformed

    private void BtnPos_n3_n16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n16ActionPerformed
        BtnPos_n3_n16 = general.SetCell(BtnPos_n3_n16, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n16ActionPerformed

    private void BtnPos_n3_n17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n17ActionPerformed
        BtnPos_n3_n17 = general.SetCell(BtnPos_n3_n17, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n17ActionPerformed

    private void BtnPos_n3_n18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n18ActionPerformed
        BtnPos_n3_n18 = general.SetCell(BtnPos_n3_n18, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n18ActionPerformed

    private void BtnPos_n3_n5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n5ActionPerformed
        BtnPos_n3_n5 = general.SetCell(BtnPos_n3_n5, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n5ActionPerformed

    private void BtnPos_n3_n6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n6ActionPerformed
        BtnPos_n3_n6 = general.SetCell(BtnPos_n3_n6, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n6ActionPerformed

    private void BtnPos_n3_n7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n7ActionPerformed
        BtnPos_n3_n7 = general.SetCell(BtnPos_n3_n7, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n7ActionPerformed

    private void BtnPos_n3_n8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n8ActionPerformed
        BtnPos_n3_n8 = general.SetCell(BtnPos_n3_n8, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n8ActionPerformed

    private void BtnPos_n3_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_5ActionPerformed
        BtnPos_n3_5 = general.SetCell(BtnPos_n3_5, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_5ActionPerformed

    private void BtnPos_n3_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_6ActionPerformed
        BtnPos_n3_6 = general.SetCell(BtnPos_n3_6, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_6ActionPerformed

    private void BtnPos_n3_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_7ActionPerformed
        BtnPos_n3_7 = general.SetCell(BtnPos_n3_7, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_7ActionPerformed

    private void BtnPos_n3_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_8ActionPerformed
        BtnPos_n3_8 = general.SetCell(BtnPos_n3_8, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_8ActionPerformed

    private void BtnPos_n3_9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_9ActionPerformed
        BtnPos_n3_9 = general.SetCell(BtnPos_n3_9, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_9ActionPerformed

    private void BtnPos_n3_15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_15ActionPerformed
        BtnPos_n3_15 = general.SetCell(BtnPos_n3_15, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_15ActionPerformed

    private void BtnPos_n3_16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_16ActionPerformed
        BtnPos_n3_16 = general.SetCell(BtnPos_n3_16, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_16ActionPerformed

    private void BtnPos_n3_17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_17ActionPerformed
        BtnPos_n3_17 = general.SetCell(BtnPos_n3_17, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_17ActionPerformed

    private void BtnPos_n3_18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_18ActionPerformed
        BtnPos_n3_18 = general.SetCell(BtnPos_n3_18, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_18ActionPerformed

    private void BtnPos_n3_19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_19ActionPerformed
        BtnPos_n3_19 = general.SetCell(BtnPos_n3_19, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_19ActionPerformed

    private void BtnPos_n3_n19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n19ActionPerformed
        BtnPos_n3_n19 = general.SetCell(BtnPos_n3_n19, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n19ActionPerformed

    private void BtnPos_n3_n20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n20ActionPerformed
        BtnPos_n3_n20 = general.SetCell(BtnPos_n3_n20, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n20ActionPerformed

    private void BtnPos_n3_n21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n21ActionPerformed
        BtnPos_n3_n21 = general.SetCell(BtnPos_n3_n21, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n21ActionPerformed

    private void BtnPos_n3_n22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n22ActionPerformed
        BtnPos_n3_n22 = general.SetCell(BtnPos_n3_n22, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n22ActionPerformed

    private void BtnPos_n3_n23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n23ActionPerformed
        BtnPos_n3_n23 = general.SetCell(BtnPos_n3_n23, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n23ActionPerformed

    private void BtnPos_n3_n24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n24ActionPerformed
        BtnPos_n3_n24 = general.SetCell(BtnPos_n3_n24, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n24ActionPerformed

    private void BtnPos_n3_n25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n25ActionPerformed
        BtnPos_n3_n25 = general.SetCell(BtnPos_n3_n25, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n25ActionPerformed

    private void BtnPos_n3_n26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n26ActionPerformed
        BtnPos_n3_n26 = general.SetCell(BtnPos_n3_n26, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n26ActionPerformed

    private void BtnPos_n3_n27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_n27ActionPerformed
        BtnPos_n3_n27 = general.SetCell(BtnPos_n3_n27, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_n27ActionPerformed

    private void BtnPos_n3_20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_20ActionPerformed
        BtnPos_n3_20 = general.SetCell(BtnPos_n3_20, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_20ActionPerformed

    private void BtnPos_n3_21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_21ActionPerformed
        BtnPos_n3_21 = general.SetCell(BtnPos_n3_21, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_21ActionPerformed

    private void BtnPos_n3_22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_22ActionPerformed
        BtnPos_n3_22 = general.SetCell(BtnPos_n3_22, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_22ActionPerformed

    private void BtnPos_n3_23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_23ActionPerformed
        BtnPos_n3_23 = general.SetCell(BtnPos_n3_23, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_23ActionPerformed

    private void BtnPos_n3_24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_24ActionPerformed
        BtnPos_n3_24 = general.SetCell(BtnPos_n3_24, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_24ActionPerformed

    private void BtnPos_n3_25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_25ActionPerformed
        BtnPos_n3_25 = general.SetCell(BtnPos_n3_25, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_25ActionPerformed

    private void BtnPos_n3_26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_26ActionPerformed
        BtnPos_n3_26 = general.SetCell(BtnPos_n3_26, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_26ActionPerformed

    private void BtnPos_n3_27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_27ActionPerformed
        BtnPos_n3_27 = general.SetCell(BtnPos_n3_27, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_27ActionPerformed

    private void BtnPos_n3_28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_28ActionPerformed
        BtnPos_n3_28 = general.SetCell(BtnPos_n3_28, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_28ActionPerformed

    private void BtnPos_n3_29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPos_n3_29ActionPerformed
        BtnPos_n3_29 = general.SetCell(BtnPos_n3_29, ListComponentsUsed, ListComponentsTemp, compList, matrix.get(ListCBackground.indexOf(backgroundList.getSelectedValue().toString())), ref);
        ListComponentsTemp = general.GetTemp();
        ListComponentsUsed = general.GetUsed();
        ListComponentsPrio = general.GetPrio();
        ArrayList<JToggleButton> aux = general.GetMatrix();
        matrix.set(ListCBackground.indexOf(backgroundList.getSelectedValue().toString()), aux);
    }//GEN-LAST:event_BtnPos_n3_29ActionPerformed

    private void checkboxAtalho1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxAtalho1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkboxAtalho1ActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainFrame().setVisible(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AbrirJogo;
    private javax.swing.JToggleButton BtnPos_0_0;
    private javax.swing.JToggleButton BtnPos_0_1;
    private javax.swing.JToggleButton BtnPos_0_10;
    private javax.swing.JToggleButton BtnPos_0_11;
    private javax.swing.JToggleButton BtnPos_0_12;
    private javax.swing.JToggleButton BtnPos_0_13;
    private javax.swing.JToggleButton BtnPos_0_14;
    private javax.swing.JToggleButton BtnPos_0_2;
    private javax.swing.JToggleButton BtnPos_0_3;
    private javax.swing.JToggleButton BtnPos_0_4;
    private javax.swing.JToggleButton BtnPos_0_5;
    private javax.swing.JToggleButton BtnPos_0_6;
    private javax.swing.JToggleButton BtnPos_0_7;
    private javax.swing.JToggleButton BtnPos_0_8;
    private javax.swing.JToggleButton BtnPos_0_9;
    private javax.swing.JToggleButton BtnPos_0_n1;
    private javax.swing.JToggleButton BtnPos_0_n10;
    private javax.swing.JToggleButton BtnPos_0_n11;
    private javax.swing.JToggleButton BtnPos_0_n12;
    private javax.swing.JToggleButton BtnPos_0_n13;
    private javax.swing.JToggleButton BtnPos_0_n2;
    private javax.swing.JToggleButton BtnPos_0_n3;
    private javax.swing.JToggleButton BtnPos_0_n4;
    private javax.swing.JToggleButton BtnPos_0_n5;
    private javax.swing.JToggleButton BtnPos_0_n6;
    private javax.swing.JToggleButton BtnPos_0_n7;
    private javax.swing.JToggleButton BtnPos_0_n8;
    private javax.swing.JToggleButton BtnPos_0_n9;
    private javax.swing.JToggleButton BtnPos_1_0;
    private javax.swing.JToggleButton BtnPos_1_1;
    private javax.swing.JToggleButton BtnPos_1_10;
    private javax.swing.JToggleButton BtnPos_1_11;
    private javax.swing.JToggleButton BtnPos_1_12;
    private javax.swing.JToggleButton BtnPos_1_13;
    private javax.swing.JToggleButton BtnPos_1_14;
    private javax.swing.JToggleButton BtnPos_1_2;
    private javax.swing.JToggleButton BtnPos_1_3;
    private javax.swing.JToggleButton BtnPos_1_4;
    private javax.swing.JToggleButton BtnPos_1_5;
    private javax.swing.JToggleButton BtnPos_1_6;
    private javax.swing.JToggleButton BtnPos_1_7;
    private javax.swing.JToggleButton BtnPos_1_8;
    private javax.swing.JToggleButton BtnPos_1_9;
    private javax.swing.JToggleButton BtnPos_1_n1;
    private javax.swing.JToggleButton BtnPos_1_n10;
    private javax.swing.JToggleButton BtnPos_1_n11;
    private javax.swing.JToggleButton BtnPos_1_n12;
    private javax.swing.JToggleButton BtnPos_1_n13;
    private javax.swing.JToggleButton BtnPos_1_n2;
    private javax.swing.JToggleButton BtnPos_1_n3;
    private javax.swing.JToggleButton BtnPos_1_n4;
    private javax.swing.JToggleButton BtnPos_1_n5;
    private javax.swing.JToggleButton BtnPos_1_n6;
    private javax.swing.JToggleButton BtnPos_1_n7;
    private javax.swing.JToggleButton BtnPos_1_n8;
    private javax.swing.JToggleButton BtnPos_1_n9;
    private javax.swing.JToggleButton BtnPos_2_0;
    private javax.swing.JToggleButton BtnPos_2_1;
    private javax.swing.JToggleButton BtnPos_2_10;
    private javax.swing.JToggleButton BtnPos_2_11;
    private javax.swing.JToggleButton BtnPos_2_12;
    private javax.swing.JToggleButton BtnPos_2_13;
    private javax.swing.JToggleButton BtnPos_2_14;
    private javax.swing.JToggleButton BtnPos_2_2;
    private javax.swing.JToggleButton BtnPos_2_3;
    private javax.swing.JToggleButton BtnPos_2_4;
    private javax.swing.JToggleButton BtnPos_2_5;
    private javax.swing.JToggleButton BtnPos_2_6;
    private javax.swing.JToggleButton BtnPos_2_7;
    private javax.swing.JToggleButton BtnPos_2_8;
    private javax.swing.JToggleButton BtnPos_2_9;
    private javax.swing.JToggleButton BtnPos_2_n1;
    private javax.swing.JToggleButton BtnPos_2_n10;
    private javax.swing.JToggleButton BtnPos_2_n11;
    private javax.swing.JToggleButton BtnPos_2_n12;
    private javax.swing.JToggleButton BtnPos_2_n13;
    private javax.swing.JToggleButton BtnPos_2_n2;
    private javax.swing.JToggleButton BtnPos_2_n3;
    private javax.swing.JToggleButton BtnPos_2_n4;
    private javax.swing.JToggleButton BtnPos_2_n5;
    private javax.swing.JToggleButton BtnPos_2_n6;
    private javax.swing.JToggleButton BtnPos_2_n7;
    private javax.swing.JToggleButton BtnPos_2_n8;
    private javax.swing.JToggleButton BtnPos_2_n9;
    private javax.swing.JToggleButton BtnPos_3_0;
    private javax.swing.JToggleButton BtnPos_3_1;
    private javax.swing.JToggleButton BtnPos_3_10;
    private javax.swing.JToggleButton BtnPos_3_11;
    private javax.swing.JToggleButton BtnPos_3_12;
    private javax.swing.JToggleButton BtnPos_3_13;
    private javax.swing.JToggleButton BtnPos_3_14;
    private javax.swing.JToggleButton BtnPos_3_15;
    private javax.swing.JToggleButton BtnPos_3_16;
    private javax.swing.JToggleButton BtnPos_3_17;
    private javax.swing.JToggleButton BtnPos_3_18;
    private javax.swing.JToggleButton BtnPos_3_19;
    private javax.swing.JToggleButton BtnPos_3_2;
    private javax.swing.JToggleButton BtnPos_3_20;
    private javax.swing.JToggleButton BtnPos_3_21;
    private javax.swing.JToggleButton BtnPos_3_22;
    private javax.swing.JToggleButton BtnPos_3_23;
    private javax.swing.JToggleButton BtnPos_3_24;
    private javax.swing.JToggleButton BtnPos_3_25;
    private javax.swing.JToggleButton BtnPos_3_26;
    private javax.swing.JToggleButton BtnPos_3_27;
    private javax.swing.JToggleButton BtnPos_3_28;
    private javax.swing.JToggleButton BtnPos_3_29;
    private javax.swing.JToggleButton BtnPos_3_3;
    private javax.swing.JToggleButton BtnPos_3_30;
    private javax.swing.JToggleButton BtnPos_3_31;
    private javax.swing.JToggleButton BtnPos_3_32;
    private javax.swing.JToggleButton BtnPos_3_33;
    private javax.swing.JToggleButton BtnPos_3_34;
    private javax.swing.JToggleButton BtnPos_3_35;
    private javax.swing.JToggleButton BtnPos_3_36;
    private javax.swing.JToggleButton BtnPos_3_37;
    private javax.swing.JToggleButton BtnPos_3_38;
    private javax.swing.JToggleButton BtnPos_3_39;
    private javax.swing.JToggleButton BtnPos_3_4;
    private javax.swing.JToggleButton BtnPos_3_5;
    private javax.swing.JToggleButton BtnPos_3_6;
    private javax.swing.JToggleButton BtnPos_3_7;
    private javax.swing.JToggleButton BtnPos_3_8;
    private javax.swing.JToggleButton BtnPos_3_9;
    private javax.swing.JToggleButton BtnPos_3_n1;
    private javax.swing.JToggleButton BtnPos_3_n10;
    private javax.swing.JToggleButton BtnPos_3_n11;
    private javax.swing.JToggleButton BtnPos_3_n12;
    private javax.swing.JToggleButton BtnPos_3_n13;
    private javax.swing.JToggleButton BtnPos_3_n14;
    private javax.swing.JToggleButton BtnPos_3_n15;
    private javax.swing.JToggleButton BtnPos_3_n16;
    private javax.swing.JToggleButton BtnPos_3_n17;
    private javax.swing.JToggleButton BtnPos_3_n18;
    private javax.swing.JToggleButton BtnPos_3_n19;
    private javax.swing.JToggleButton BtnPos_3_n2;
    private javax.swing.JToggleButton BtnPos_3_n20;
    private javax.swing.JToggleButton BtnPos_3_n21;
    private javax.swing.JToggleButton BtnPos_3_n22;
    private javax.swing.JToggleButton BtnPos_3_n23;
    private javax.swing.JToggleButton BtnPos_3_n24;
    private javax.swing.JToggleButton BtnPos_3_n25;
    private javax.swing.JToggleButton BtnPos_3_n26;
    private javax.swing.JToggleButton BtnPos_3_n27;
    private javax.swing.JToggleButton BtnPos_3_n28;
    private javax.swing.JToggleButton BtnPos_3_n29;
    private javax.swing.JToggleButton BtnPos_3_n3;
    private javax.swing.JToggleButton BtnPos_3_n30;
    private javax.swing.JToggleButton BtnPos_3_n31;
    private javax.swing.JToggleButton BtnPos_3_n32;
    private javax.swing.JToggleButton BtnPos_3_n33;
    private javax.swing.JToggleButton BtnPos_3_n34;
    private javax.swing.JToggleButton BtnPos_3_n35;
    private javax.swing.JToggleButton BtnPos_3_n36;
    private javax.swing.JToggleButton BtnPos_3_n4;
    private javax.swing.JToggleButton BtnPos_3_n5;
    private javax.swing.JToggleButton BtnPos_3_n6;
    private javax.swing.JToggleButton BtnPos_3_n7;
    private javax.swing.JToggleButton BtnPos_3_n8;
    private javax.swing.JToggleButton BtnPos_3_n9;
    private javax.swing.JToggleButton BtnPos_n1_0;
    private javax.swing.JToggleButton BtnPos_n1_1;
    private javax.swing.JToggleButton BtnPos_n1_10;
    private javax.swing.JToggleButton BtnPos_n1_11;
    private javax.swing.JToggleButton BtnPos_n1_12;
    private javax.swing.JToggleButton BtnPos_n1_13;
    private javax.swing.JToggleButton BtnPos_n1_14;
    private javax.swing.JToggleButton BtnPos_n1_2;
    private javax.swing.JToggleButton BtnPos_n1_3;
    private javax.swing.JToggleButton BtnPos_n1_4;
    private javax.swing.JToggleButton BtnPos_n1_5;
    private javax.swing.JToggleButton BtnPos_n1_6;
    private javax.swing.JToggleButton BtnPos_n1_7;
    private javax.swing.JToggleButton BtnPos_n1_8;
    private javax.swing.JToggleButton BtnPos_n1_9;
    private javax.swing.JToggleButton BtnPos_n1_n1;
    private javax.swing.JToggleButton BtnPos_n1_n10;
    private javax.swing.JToggleButton BtnPos_n1_n11;
    private javax.swing.JToggleButton BtnPos_n1_n12;
    private javax.swing.JToggleButton BtnPos_n1_n13;
    private javax.swing.JToggleButton BtnPos_n1_n2;
    private javax.swing.JToggleButton BtnPos_n1_n3;
    private javax.swing.JToggleButton BtnPos_n1_n4;
    private javax.swing.JToggleButton BtnPos_n1_n5;
    private javax.swing.JToggleButton BtnPos_n1_n6;
    private javax.swing.JToggleButton BtnPos_n1_n7;
    private javax.swing.JToggleButton BtnPos_n1_n8;
    private javax.swing.JToggleButton BtnPos_n1_n9;
    private javax.swing.JToggleButton BtnPos_n2_0;
    private javax.swing.JToggleButton BtnPos_n2_1;
    private javax.swing.JToggleButton BtnPos_n2_10;
    private javax.swing.JToggleButton BtnPos_n2_11;
    private javax.swing.JToggleButton BtnPos_n2_12;
    private javax.swing.JToggleButton BtnPos_n2_13;
    private javax.swing.JToggleButton BtnPos_n2_14;
    private javax.swing.JToggleButton BtnPos_n2_2;
    private javax.swing.JToggleButton BtnPos_n2_3;
    private javax.swing.JToggleButton BtnPos_n2_4;
    private javax.swing.JToggleButton BtnPos_n2_5;
    private javax.swing.JToggleButton BtnPos_n2_6;
    private javax.swing.JToggleButton BtnPos_n2_7;
    private javax.swing.JToggleButton BtnPos_n2_8;
    private javax.swing.JToggleButton BtnPos_n2_9;
    private javax.swing.JToggleButton BtnPos_n2_n1;
    private javax.swing.JToggleButton BtnPos_n2_n10;
    private javax.swing.JToggleButton BtnPos_n2_n11;
    private javax.swing.JToggleButton BtnPos_n2_n12;
    private javax.swing.JToggleButton BtnPos_n2_n13;
    private javax.swing.JToggleButton BtnPos_n2_n2;
    private javax.swing.JToggleButton BtnPos_n2_n3;
    private javax.swing.JToggleButton BtnPos_n2_n4;
    private javax.swing.JToggleButton BtnPos_n2_n5;
    private javax.swing.JToggleButton BtnPos_n2_n6;
    private javax.swing.JToggleButton BtnPos_n2_n7;
    private javax.swing.JToggleButton BtnPos_n2_n8;
    private javax.swing.JToggleButton BtnPos_n2_n9;
    private javax.swing.JToggleButton BtnPos_n3_0;
    private javax.swing.JToggleButton BtnPos_n3_1;
    private javax.swing.JToggleButton BtnPos_n3_10;
    private javax.swing.JToggleButton BtnPos_n3_11;
    private javax.swing.JToggleButton BtnPos_n3_12;
    private javax.swing.JToggleButton BtnPos_n3_13;
    private javax.swing.JToggleButton BtnPos_n3_14;
    private javax.swing.JToggleButton BtnPos_n3_15;
    private javax.swing.JToggleButton BtnPos_n3_16;
    private javax.swing.JToggleButton BtnPos_n3_17;
    private javax.swing.JToggleButton BtnPos_n3_18;
    private javax.swing.JToggleButton BtnPos_n3_19;
    private javax.swing.JToggleButton BtnPos_n3_2;
    private javax.swing.JToggleButton BtnPos_n3_20;
    private javax.swing.JToggleButton BtnPos_n3_21;
    private javax.swing.JToggleButton BtnPos_n3_22;
    private javax.swing.JToggleButton BtnPos_n3_23;
    private javax.swing.JToggleButton BtnPos_n3_24;
    private javax.swing.JToggleButton BtnPos_n3_25;
    private javax.swing.JToggleButton BtnPos_n3_26;
    private javax.swing.JToggleButton BtnPos_n3_27;
    private javax.swing.JToggleButton BtnPos_n3_28;
    private javax.swing.JToggleButton BtnPos_n3_29;
    private javax.swing.JToggleButton BtnPos_n3_3;
    private javax.swing.JToggleButton BtnPos_n3_4;
    private javax.swing.JToggleButton BtnPos_n3_5;
    private javax.swing.JToggleButton BtnPos_n3_6;
    private javax.swing.JToggleButton BtnPos_n3_7;
    private javax.swing.JToggleButton BtnPos_n3_8;
    private javax.swing.JToggleButton BtnPos_n3_9;
    private javax.swing.JToggleButton BtnPos_n3_n1;
    private javax.swing.JToggleButton BtnPos_n3_n10;
    private javax.swing.JToggleButton BtnPos_n3_n11;
    private javax.swing.JToggleButton BtnPos_n3_n12;
    private javax.swing.JToggleButton BtnPos_n3_n13;
    private javax.swing.JToggleButton BtnPos_n3_n14;
    private javax.swing.JToggleButton BtnPos_n3_n15;
    private javax.swing.JToggleButton BtnPos_n3_n16;
    private javax.swing.JToggleButton BtnPos_n3_n17;
    private javax.swing.JToggleButton BtnPos_n3_n18;
    private javax.swing.JToggleButton BtnPos_n3_n19;
    private javax.swing.JToggleButton BtnPos_n3_n2;
    private javax.swing.JToggleButton BtnPos_n3_n20;
    private javax.swing.JToggleButton BtnPos_n3_n21;
    private javax.swing.JToggleButton BtnPos_n3_n22;
    private javax.swing.JToggleButton BtnPos_n3_n23;
    private javax.swing.JToggleButton BtnPos_n3_n24;
    private javax.swing.JToggleButton BtnPos_n3_n25;
    private javax.swing.JToggleButton BtnPos_n3_n26;
    private javax.swing.JToggleButton BtnPos_n3_n27;
    private javax.swing.JToggleButton BtnPos_n3_n3;
    private javax.swing.JToggleButton BtnPos_n3_n4;
    private javax.swing.JToggleButton BtnPos_n3_n5;
    private javax.swing.JToggleButton BtnPos_n3_n6;
    private javax.swing.JToggleButton BtnPos_n3_n7;
    private javax.swing.JToggleButton BtnPos_n3_n8;
    private javax.swing.JToggleButton BtnPos_n3_n9;
    private javax.swing.JMenuItem ComoUsar;
    private javax.swing.JMenuItem Fechar;
    private javax.swing.JScrollPane JScrollPane10;
    private javax.swing.JMenuItem NovoJogo;
    private javax.swing.JMenuItem SalvarComoJogo;
    private javax.swing.JMenuItem SalvarJogo;
    private javax.swing.JMenuItem Sobre;
    private javax.swing.JList<String> backgroundList;
    private javax.swing.JScrollPane backgroundViewerPane;
    private javax.swing.JButton btnAddArvores;
    private javax.swing.JButton btnAddBackground;
    private javax.swing.JButton btnAddElementos;
    private javax.swing.JButton btnAddLixo;
    private javax.swing.JButton btnBuild;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnOpenFileChooser;
    private javax.swing.JButton btnProx2;
    private javax.swing.JButton btnProx3;
    private javax.swing.JButton btnVoltar2;
    private javax.swing.JButton btnVoltar3;
    private javax.swing.JButton btnVoltar4;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JCheckBox checkboxAtalho;
    private javax.swing.JCheckBox checkboxAtalho1;
    private javax.swing.JList<String> compList;
    private javax.swing.JList<String> compList1;
    private javax.swing.JLabel compPreview;
    private javax.swing.JLabel compPreview1;
    private javax.swing.JPanel compPreviewPanel;
    private javax.swing.JPanel compPreviewPanel1;
    private javax.swing.JPanel compViewer;
    private javax.swing.JPanel compViewer1;
    private javax.swing.JScrollPane componentViewerPane;
    private javax.swing.JLabel imgAux;
    private javax.swing.JLabel imgAux1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelTab;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JList<String> labelArvores;
    private javax.swing.JList<String> labelBackground;
    private javax.swing.JLabel labelCommandLines;
    private javax.swing.JList<String> labelElementos;
    private javax.swing.JList<String> labelLixo;
    private javax.swing.JTextPane labelResumo2;
    private javax.swing.JTextField labelSaveAddress;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane panelBackground;
    private javax.swing.JPanel posTab;
    private javax.swing.JTabbedPane posTabGroup;
    private javax.swing.JProgressBar progressBuilding;
    private javax.swing.JTextField textNomeJogo;
    // End of variables declaration//GEN-END:variables
    
    private ArrayList<String> background = new ArrayList<String>();
    private ArrayList<String> elemento = new ArrayList<String>();
    private ArrayList<String> arvore = new ArrayList<String>();
    private ArrayList<String> lixo = new ArrayList<String>();
    private DefaultListModel ListCBackground = new DefaultListModel();
    private DefaultListModel ListCArvore = new DefaultListModel();
    private DefaultListModel ListCElemento = new DefaultListModel();
    private DefaultListModel ListCLixo = new DefaultListModel();
    
    private DefaultListModel ListComponents = new DefaultListModel();
    private DefaultListModel ListComponentsTemp = new DefaultListModel();
    private DefaultListModel ListComponentsUsed = new DefaultListModel();
    private DefaultListModel ListComponentsPrio = new DefaultListModel();
    
    private String textElemento;
    private String textArvore;
    private String textLixo;
    private String textBackgroud;
    private String textResumo;
    private String savepath = "";
    private String unitypath = "";
    private String plataform = "";
    
    private MotivacaoAutoBuilder general = new MotivacaoAutoBuilder();
    
    private ArrayList<ArrayList<JToggleButton>> matrix = new ArrayList<ArrayList<JToggleButton>>();
    private ArrayList<String> ref = new ArrayList<String>();
    
    private javax.swing.JPanel auxForPos;
    
    /*private String[] ref = {"0_0", "0_1", "0_2", "0_3", "0_4",
                        "0_-1", "0_-2", "0_-3", "0_-5",
                        "1_0", "1_1", "1_2", "1_3", "1_4",
                        "1_-1", "1_-2", "1_-3", "1_-5",
                        "2_0", "2_1", "2_2", "2_3", "2_4",
                        "2_-1", "2_-2", "2_-3", "2_-5",
                        "-1_0", "-1_1", "-1_2", "-1_3", "-1_4",
                        "-1_-1", "-1_-2", "-1_-3", "-1_-5",
                        "-2_0", "-2_1", "-2_2", "-2_3", "-2_4",
                        "-2_-1", "-2_-2", "-2_-3", "-2_-5"
                        };*/
    

    private ArrayList<JToggleButton> SetBtnMatrix (){
        /*({ BtnPos_0_0, BtnPos_0_1, BtnPos_0_2, BtnPos_0_3, BtnPos_0_4,
            BtnPos_0_n1, BtnPos_0_n2, BtnPos_0_n3, BtnPos_0_n4,
            BtnPos_1_0, BtnPos_1_1, BtnPos_1_2, BtnPos_1_3, BtnPos_1_4,
            BtnPos_1_n1, BtnPos_1_n2, BtnPos_1_n3, BtnPos_1_n4,
            BtnPos_2_0, BtnPos_2_1, BtnPos_2_2, BtnPos_2_3, BtnPos_2_4,
            BtnPos_2_n1, BtnPos_2_n2, BtnPos_2_n3, BtnPos_2_n4,
            BtnPos_n1_0, BtnPos_n1_1, BtnPos_n1_2, BtnPos_n1_3, BtnPos_n1_4,
            BtnPos_n1_n1, BtnPos_n1_n2, BtnPos_n1_n3, BtnPos_n1_n4,
            BtnPos_n2_0, BtnPos_n2_1, BtnPos_n2_2, BtnPos_n2_3, BtnPos_n2_4,
            BtnPos_n2_n1, BtnPos_n2_n2, BtnPos_n2_n3, BtnPos_n2_n4
        });*/
        ArrayList<JToggleButton> matrix = new ArrayList<JToggleButton>();
        
        matrix.add(BtnPos_0_0); matrix.add(BtnPos_0_1); matrix.add(BtnPos_0_2); matrix.add(BtnPos_0_3); matrix.add(BtnPos_0_4); matrix.add(BtnPos_0_10);  matrix.add(BtnPos_0_11);  matrix.add(BtnPos_0_12);  matrix.add(BtnPos_0_13);  matrix.add(BtnPos_0_14);
        matrix.add(BtnPos_0_n1); matrix.add(BtnPos_0_n2); matrix.add(BtnPos_0_n3); matrix.add(BtnPos_0_n4); matrix.add(BtnPos_0_n9); matrix.add(BtnPos_0_n10); matrix.add(BtnPos_0_n11); matrix.add(BtnPos_0_n12); matrix.add(BtnPos_0_n13);
        matrix.add(BtnPos_1_0); matrix.add(BtnPos_1_1); matrix.add(BtnPos_1_2); matrix.add(BtnPos_1_3); matrix.add(BtnPos_1_4); matrix.add(BtnPos_1_10);  matrix.add(BtnPos_1_11);  matrix.add(BtnPos_1_12);  matrix.add(BtnPos_1_13);  matrix.add(BtnPos_1_14);
        matrix.add(BtnPos_1_n1); matrix.add(BtnPos_1_n2); matrix.add(BtnPos_1_n3); matrix.add(BtnPos_1_n4); matrix.add(BtnPos_1_n9); matrix.add(BtnPos_1_n10); matrix.add(BtnPos_1_n11); matrix.add(BtnPos_1_n12); matrix.add(BtnPos_1_n13);
        matrix.add(BtnPos_2_0); matrix.add(BtnPos_2_1); matrix.add(BtnPos_2_2); matrix.add(BtnPos_2_3); matrix.add(BtnPos_2_4); matrix.add(BtnPos_2_10);  matrix.add(BtnPos_2_11);  matrix.add(BtnPos_2_12);  matrix.add(BtnPos_2_13);  matrix.add(BtnPos_2_14);
        matrix.add(BtnPos_2_n1); matrix.add(BtnPos_2_n2); matrix.add(BtnPos_2_n3); matrix.add(BtnPos_2_n4); matrix.add(BtnPos_2_n9); matrix.add(BtnPos_2_n10); matrix.add(BtnPos_2_n11); matrix.add(BtnPos_2_n12); matrix.add(BtnPos_2_n13);
        matrix.add(BtnPos_3_0); matrix.add(BtnPos_3_1); matrix.add(BtnPos_3_2); matrix.add(BtnPos_3_3); matrix.add(BtnPos_3_4); matrix.add(BtnPos_3_10);  matrix.add(BtnPos_3_11);  matrix.add(BtnPos_3_12);  matrix.add(BtnPos_3_13);  matrix.add(BtnPos_3_14);
        matrix.add(BtnPos_3_n1); matrix.add(BtnPos_3_n2); matrix.add(BtnPos_3_n3); matrix.add(BtnPos_3_n4); matrix.add(BtnPos_3_n9); matrix.add(BtnPos_3_n10); matrix.add(BtnPos_3_n11); matrix.add(BtnPos_3_n12); matrix.add(BtnPos_3_n13);
        matrix.add(BtnPos_3_5); matrix.add(BtnPos_3_6); matrix.add(BtnPos_3_7); matrix.add(BtnPos_3_8); matrix.add(BtnPos_3_9); matrix.add(BtnPos_3_15); matrix.add(BtnPos_3_16); matrix.add(BtnPos_3_17); matrix.add(BtnPos_3_18); matrix.add(BtnPos_3_19);
        matrix.add(BtnPos_3_n8); matrix.add(BtnPos_3_n7); matrix.add(BtnPos_3_n6); matrix.add(BtnPos_3_n5); matrix.add(BtnPos_3_n18); matrix.add(BtnPos_3_n17); matrix.add(BtnPos_3_n16); matrix.add(BtnPos_3_n15); matrix.add(BtnPos_3_n14);
        matrix.add(BtnPos_3_20); matrix.add(BtnPos_3_21); matrix.add(BtnPos_3_22); matrix.add(BtnPos_3_23); matrix.add(BtnPos_3_24); matrix.add(BtnPos_3_25); matrix.add(BtnPos_3_26); matrix.add(BtnPos_3_27); matrix.add(BtnPos_3_28); matrix.add(BtnPos_3_29); 
        matrix.add(BtnPos_3_n27); matrix.add(BtnPos_3_n26); matrix.add(BtnPos_3_n25); matrix.add(BtnPos_3_n24); matrix.add(BtnPos_3_n23); matrix.add(BtnPos_3_n22); matrix.add(BtnPos_3_n21); matrix.add(BtnPos_3_n20); matrix.add(BtnPos_3_n19);
        matrix.add(BtnPos_3_30); matrix.add(BtnPos_3_31); matrix.add(BtnPos_3_32); matrix.add(BtnPos_3_33); matrix.add(BtnPos_3_34); matrix.add(BtnPos_3_35); matrix.add(BtnPos_3_36); matrix.add(BtnPos_3_37); matrix.add(BtnPos_3_38); matrix.add(BtnPos_3_39); 
        matrix.add(BtnPos_3_n36); matrix.add(BtnPos_3_n35); matrix.add(BtnPos_3_n34); matrix.add(BtnPos_3_n33); matrix.add(BtnPos_3_n32); matrix.add(BtnPos_3_n31); matrix.add(BtnPos_3_n30); matrix.add(BtnPos_3_n29); matrix.add(BtnPos_3_n28);
        matrix.add(BtnPos_n1_0); matrix.add(BtnPos_n1_1); matrix.add(BtnPos_n1_2); matrix.add(BtnPos_n1_3); matrix.add(BtnPos_n1_4); matrix.add(BtnPos_n1_10);  matrix.add(BtnPos_n1_11);  matrix.add(BtnPos_n1_12);  matrix.add(BtnPos_n1_13);  matrix.add(BtnPos_n1_14);
        matrix.add(BtnPos_n1_n1); matrix.add(BtnPos_n1_n2); matrix.add(BtnPos_n1_n3); matrix.add(BtnPos_n1_n4); matrix.add(BtnPos_n1_n9); matrix.add(BtnPos_n1_n10); matrix.add(BtnPos_n1_n11); matrix.add(BtnPos_n1_n12); matrix.add(BtnPos_n1_n13);
        matrix.add(BtnPos_n2_0); matrix.add(BtnPos_n2_1); matrix.add(BtnPos_n2_2); matrix.add(BtnPos_n2_3); matrix.add(BtnPos_n2_4); matrix.add(BtnPos_n2_10);  matrix.add(BtnPos_n2_11);  matrix.add(BtnPos_n2_12);  matrix.add(BtnPos_n2_13);  matrix.add(BtnPos_n2_14);
        matrix.add(BtnPos_n2_n1); matrix.add(BtnPos_n2_n2); matrix.add(BtnPos_n2_n3); matrix.add(BtnPos_n2_n4); matrix.add(BtnPos_n2_n9); matrix.add(BtnPos_n2_n10); matrix.add(BtnPos_n2_n11); matrix.add(BtnPos_n2_n12); matrix.add(BtnPos_n2_n13); 
        matrix.add(BtnPos_n3_0); matrix.add(BtnPos_n3_1); matrix.add(BtnPos_n3_2); matrix.add(BtnPos_n3_3); matrix.add(BtnPos_n3_4); matrix.add(BtnPos_n3_10);  matrix.add(BtnPos_n3_11);  matrix.add(BtnPos_n3_12);  matrix.add(BtnPos_n3_13);  matrix.add(BtnPos_n3_14);
        matrix.add(BtnPos_n3_n1); matrix.add(BtnPos_n3_n2); matrix.add(BtnPos_n3_n3); matrix.add(BtnPos_n3_n4); matrix.add(BtnPos_n3_n9); matrix.add(BtnPos_n3_n10); matrix.add(BtnPos_n3_n11); matrix.add(BtnPos_n3_n12); matrix.add(BtnPos_n3_n13);
        matrix.add(BtnPos_n3_5); matrix.add(BtnPos_n3_6); matrix.add(BtnPos_n3_7); matrix.add(BtnPos_n3_8); matrix.add(BtnPos_n3_9); matrix.add(BtnPos_n3_15); matrix.add(BtnPos_n3_16); matrix.add(BtnPos_n3_17); matrix.add(BtnPos_n3_18); matrix.add(BtnPos_n3_19);
        matrix.add(BtnPos_n3_n8); matrix.add(BtnPos_n3_n7); matrix.add(BtnPos_n3_n6); matrix.add(BtnPos_n3_n5); matrix.add(BtnPos_n3_n18); matrix.add(BtnPos_n3_n17); matrix.add(BtnPos_n3_n16); matrix.add(BtnPos_n3_n15); matrix.add(BtnPos_n3_n14);
        matrix.add(BtnPos_n3_20); matrix.add(BtnPos_n3_21); matrix.add(BtnPos_n3_22); matrix.add(BtnPos_n3_23); matrix.add(BtnPos_n3_24); matrix.add(BtnPos_n3_25); matrix.add(BtnPos_n3_26); matrix.add(BtnPos_n3_27); matrix.add(BtnPos_n3_28); matrix.add(BtnPos_n3_29); 
        matrix.add(BtnPos_n3_n27); matrix.add(BtnPos_n3_n26); matrix.add(BtnPos_n3_n25); matrix.add(BtnPos_n3_n24); matrix.add(BtnPos_n3_n23); matrix.add(BtnPos_n3_n22); matrix.add(BtnPos_n3_n21); matrix.add(BtnPos_n3_n20); matrix.add(BtnPos_n3_n19);
        
        return matrix;
    }
    
    private void SetRefMatrix (){
        ref.add("0_0"); ref.add("0_1"); ref.add("0_2"); ref.add("0_3"); ref.add("0_4"); ref.add("0_5"); ref.add("0_6"); ref.add("0_7"); ref.add("0_8"); ref.add("0_9");
        ref.add("0_-1"); ref.add("0_-2"); ref.add("0_-3"); ref.add("0_-4"); ref.add("0_-5"); ref.add("0_-6"); ref.add("0_-7"); ref.add("0_-8"); ref.add("0_-9");
        ref.add("1_0"); ref.add("1_1"); ref.add("1_2"); ref.add("1_3"); ref.add("1_4"); ref.add("1_5"); ref.add("1_6"); ref.add("1_7"); ref.add("1_8"); ref.add("1_9");
        ref.add("1_-1"); ref.add("1_-2"); ref.add("1_-3"); ref.add("1_-4"); ref.add("1_-5"); ref.add("1_-6"); ref.add("1_-7"); ref.add("1_-8"); ref.add("1_-9");
        ref.add("2_0"); ref.add("2_1"); ref.add("2_2"); ref.add("2_3"); ref.add("2_4"); ref.add("2_5"); ref.add("2_6"); ref.add("2_7"); ref.add("2_8"); ref.add("2_9");
        ref.add("2_-1"); ref.add("2_-2"); ref.add("2_-3"); ref.add("2_-4"); ref.add("2_-5"); ref.add("2_-6"); ref.add("2_-7"); ref.add("2_-8"); ref.add("2_-9");
        ref.add("3_0"); ref.add("3_1"); ref.add("3_2"); ref.add("3_3"); ref.add("3_4"); ref.add("3_5"); ref.add("3_6"); ref.add("3_7"); ref.add("3_8"); ref.add("3_9");
        ref.add("3_-1"); ref.add("3_-2"); ref.add("3_-3"); ref.add("3_-4"); ref.add("3_-5"); ref.add("3_-6"); ref.add("3_-7"); ref.add("3_-8"); ref.add("3_-9");
        ref.add("4_0"); ref.add("4_1"); ref.add("4_2"); ref.add("4_3"); ref.add("4_4"); ref.add("4_5"); ref.add("4_6"); ref.add("4_7"); ref.add("4_8"); ref.add("4_9");
        ref.add("4_-1"); ref.add("4_-2"); ref.add("4_-3"); ref.add("4_-4"); ref.add("4_-5"); ref.add("4_-6"); ref.add("4_-7"); ref.add("4_-8"); ref.add("4_-9");
        ref.add("5_0"); ref.add("5_1"); ref.add("5_2"); ref.add("5_3"); ref.add("5_4"); ref.add("5_5"); ref.add("5_6"); ref.add("5_7"); ref.add("5_8"); ref.add("5_9");
        ref.add("5_-1"); ref.add("5_-2"); ref.add("5_-3"); ref.add("5_-4"); ref.add("5_-5"); ref.add("5_-6"); ref.add("5_-7"); ref.add("5_-8"); ref.add("5_-9");
        ref.add("6_0"); ref.add("6_1"); ref.add("6_2"); ref.add("6_3"); ref.add("6_4"); ref.add("6_5"); ref.add("6_6"); ref.add("6_7"); ref.add("6_8"); ref.add("6_9");
        ref.add("6_-1"); ref.add("6_-2"); ref.add("6_-3"); ref.add("6_-4"); ref.add("6_-5"); ref.add("6_-6"); ref.add("6_-7"); ref.add("6_-8"); ref.add("6_-9");
        ref.add("-1_0"); ref.add("-1_1"); ref.add("-1_2"); ref.add("-1_3"); ref.add("-1_4"); ref.add("-1_5"); ref.add("-1_6"); ref.add("-1_7"); ref.add("-1_8"); ref.add("-1_9");
        ref.add("-1_-1"); ref.add("-1_-2"); ref.add("-1_-3"); ref.add("-1_-4"); ref.add("-1_-5"); ref.add("-1_-6"); ref.add("-1_-7"); ref.add("-1_-8"); ref.add("-1_-9");
        ref.add("-2_0"); ref.add("-2_1"); ref.add("-2_2"); ref.add("-2_3"); ref.add("-2_4"); ref.add("-2_5"); ref.add("-2_6"); ref.add("-2_7"); ref.add("-2_8"); ref.add("-2_9");
        ref.add("-2_-1"); ref.add("-2_-2"); ref.add("-2_-3"); ref.add("-2_-4"); ref.add("-2_-5"); ref.add("-2_-6"); ref.add("-2_-7"); ref.add("-2_-8"); ref.add("-2_-9");
        ref.add("-3_0"); ref.add("-3_1"); ref.add("-3_2"); ref.add("-3_3"); ref.add("-3_4"); ref.add("-3_5"); ref.add("-3_6"); ref.add("-3_7"); ref.add("-3_8"); ref.add("-3_9");
        ref.add("-3_-1"); ref.add("-3_-2"); ref.add("-3_-3"); ref.add("-3_-4"); ref.add("-3_-5"); ref.add("-3_-6"); ref.add("-3_-7"); ref.add("-3_-8"); ref.add("-3_-9");
        ref.add("-4_0"); ref.add("-4_1"); ref.add("-4_2"); ref.add("-4_3"); ref.add("-4_4"); ref.add("-4_5"); ref.add("-4_6"); ref.add("-4_7"); ref.add("-4_8"); ref.add("-4_9");
        ref.add("-4_-1"); ref.add("-4_-2"); ref.add("-4_-3"); ref.add("-4_-4"); ref.add("-4_-5"); ref.add("-4_-6"); ref.add("-4_-7"); ref.add("-4_-8"); ref.add("-4_-9");
        ref.add("-5_0"); ref.add("-5_1"); ref.add("-5_2"); ref.add("-5_3"); ref.add("-5_4"); ref.add("-5_5"); ref.add("-5_6"); ref.add("-5_7"); ref.add("-5_8"); ref.add("-5_9");
        ref.add("-5_-1"); ref.add("-5_-2"); ref.add("-5_-3"); ref.add("-5_-4"); ref.add("-5_-5"); ref.add("-5_-6"); ref.add("-5_-7"); ref.add("-5_-8"); ref.add("-5_-9");
    }
    
    private void SetBoardMatrix (ArrayList<JToggleButton> cell){
        BtnPos_0_0=cell.get(0); BtnPos_0_1=cell.get(1); BtnPos_0_2=cell.get(2); BtnPos_0_3=cell.get(3); BtnPos_0_4=cell.get(4); BtnPos_0_10=cell.get(5); BtnPos_0_11=cell.get(6); BtnPos_0_12=cell.get(7); BtnPos_0_13=cell.get(8); BtnPos_0_14=cell.get(9);  
        BtnPos_0_n1=cell.get(10); BtnPos_0_n2=cell.get(11); BtnPos_0_n3=cell.get(12); BtnPos_0_n4=cell.get(13); BtnPos_0_n9=cell.get(14); BtnPos_0_n10=cell.get(15); BtnPos_0_n11=cell.get(16); BtnPos_0_n12=cell.get(17); BtnPos_0_n13=cell.get(18);
        BtnPos_1_0=cell.get(19); BtnPos_1_1=cell.get(20); BtnPos_1_2=cell.get(21); BtnPos_1_3=cell.get(22); BtnPos_1_4=cell.get(23); BtnPos_1_10=cell.get(24); BtnPos_1_11=cell.get(25); BtnPos_1_12=cell.get(26); BtnPos_1_13=cell.get(27); BtnPos_1_14=cell.get(28);
        BtnPos_1_n1=cell.get(29); BtnPos_1_n2=cell.get(30); BtnPos_1_n3=cell.get(31); BtnPos_1_n4=cell.get(32); BtnPos_1_n9=cell.get(33); BtnPos_1_n10=cell.get(34); BtnPos_1_n11=cell.get(35); BtnPos_1_n12=cell.get(36); BtnPos_1_n13=cell.get(37);
        BtnPos_2_0=cell.get(38); BtnPos_2_1=cell.get(39); BtnPos_2_2=cell.get(40); BtnPos_2_3=cell.get(41); BtnPos_2_4=cell.get(42); BtnPos_2_10=cell.get(43); BtnPos_2_11=cell.get(44); BtnPos_2_12=cell.get(45); BtnPos_2_13=cell.get(46); BtnPos_2_14=cell.get(47);
        BtnPos_2_n1=cell.get(48); BtnPos_2_n2=cell.get(49); BtnPos_2_n3=cell.get(50); BtnPos_2_n4=cell.get(51); BtnPos_2_n9=cell.get(52); BtnPos_2_n10=cell.get(53); BtnPos_2_n11=cell.get(54); BtnPos_2_n12=cell.get(55); BtnPos_2_n13=cell.get(56);
        BtnPos_3_0=cell.get(57); BtnPos_3_1=cell.get(58); BtnPos_3_2=cell.get(59); BtnPos_3_3=cell.get(60); BtnPos_3_4=cell.get(61); BtnPos_3_10=cell.get(62); BtnPos_3_11=cell.get(63); BtnPos_3_12=cell.get(64); BtnPos_3_13=cell.get(65); BtnPos_3_14=cell.get(66);
        BtnPos_3_n1=cell.get(67); BtnPos_3_n2=cell.get(68); BtnPos_3_n3=cell.get(69); BtnPos_3_n4=cell.get(70); BtnPos_3_n9=cell.get(71); BtnPos_3_n10=cell.get(72); BtnPos_3_n11=cell.get(73); BtnPos_3_n12=cell.get(74); BtnPos_3_n13=cell.get(75);
        BtnPos_3_5=cell.get(76); BtnPos_3_6=cell.get(77); BtnPos_3_7=cell.get(78); BtnPos_3_8=cell.get(79); BtnPos_3_9=cell.get(80); BtnPos_3_15=cell.get(81); BtnPos_3_16=cell.get(82); BtnPos_3_17=cell.get(83); BtnPos_3_18=cell.get(84); BtnPos_3_19=cell.get(85);
        BtnPos_3_n8=cell.get(86); BtnPos_3_n7=cell.get(87); BtnPos_3_n6=cell.get(88); BtnPos_3_n5=cell.get(89); BtnPos_3_n18=cell.get(90); BtnPos_3_n17=cell.get(91); BtnPos_3_n16=cell.get(92); BtnPos_3_n15=cell.get(93); BtnPos_3_n14=cell.get(94);
        BtnPos_3_20=cell.get(95); BtnPos_3_21=cell.get(96); BtnPos_3_22=cell.get(97); BtnPos_3_23=cell.get(98); BtnPos_3_24=cell.get(99); BtnPos_3_25=cell.get(100); BtnPos_3_26=cell.get(101); BtnPos_3_27=cell.get(102); BtnPos_3_28=cell.get(103); BtnPos_3_29=cell.get(104);
        BtnPos_3_n27=cell.get(105); BtnPos_3_n26=cell.get(106); BtnPos_3_n25=cell.get(107); BtnPos_3_n24=cell.get(108); BtnPos_3_n23=cell.get(109); BtnPos_3_n22=cell.get(110); BtnPos_3_n21=cell.get(111); BtnPos_3_n20=cell.get(112); BtnPos_3_n19=cell.get(113);
        BtnPos_3_30=cell.get(114); BtnPos_3_31=cell.get(115); BtnPos_3_32=cell.get(116); BtnPos_3_33=cell.get(117); BtnPos_3_34=cell.get(118); BtnPos_3_35=cell.get(119); BtnPos_3_36=cell.get(120); BtnPos_3_37=cell.get(121); BtnPos_3_38=cell.get(122); BtnPos_3_39=cell.get(123);
        BtnPos_3_n36=cell.get(124); BtnPos_3_n35=cell.get(125); BtnPos_3_n34=cell.get(126); BtnPos_3_n33=cell.get(127); BtnPos_3_n32=cell.get(128); BtnPos_3_n31=cell.get(129); BtnPos_3_n30=cell.get(130); BtnPos_3_n29=cell.get(131); BtnPos_3_n28=cell.get(132); 
        BtnPos_n1_0=cell.get(133); BtnPos_n1_1=cell.get(134); BtnPos_n1_2=cell.get(135); BtnPos_n1_3=cell.get(136); BtnPos_n1_4=cell.get(137); BtnPos_n1_10=cell.get(138); BtnPos_n1_11=cell.get(139); BtnPos_n1_12=cell.get(140); BtnPos_n1_13=cell.get(141); BtnPos_n1_14=cell.get(142);
        BtnPos_n1_n1=cell.get(143); BtnPos_n1_n2=cell.get(144); BtnPos_n1_n3=cell.get(145); BtnPos_n1_n4=cell.get(146); BtnPos_n1_n9=cell.get(147); BtnPos_n1_n10=cell.get(148); BtnPos_n1_n11=cell.get(149); BtnPos_n1_n12=cell.get(150); BtnPos_n1_n13=cell.get(151);
        BtnPos_n2_0=cell.get(152); BtnPos_n2_1=cell.get(153); BtnPos_n2_2=cell.get(154); BtnPos_n2_3=cell.get(155); BtnPos_n2_4=cell.get(156); BtnPos_n2_10=cell.get(157); BtnPos_n2_11=cell.get(158); BtnPos_n2_12=cell.get(159); BtnPos_n2_13=cell.get(160); BtnPos_n2_14=cell.get(161);
        BtnPos_n2_n1=cell.get(162); BtnPos_n2_n2=cell.get(163); BtnPos_n2_n3=cell.get(164); BtnPos_n2_n4=cell.get(165); BtnPos_n2_n9=cell.get(166); BtnPos_n2_n10=cell.get(167); BtnPos_n2_n11=cell.get(168); BtnPos_n2_n12=cell.get(169); BtnPos_n2_n13=cell.get(170);
        BtnPos_n3_0=cell.get(171); BtnPos_n3_1=cell.get(172); BtnPos_n3_2=cell.get(173); BtnPos_n3_3=cell.get(174); BtnPos_n3_4=cell.get(175); BtnPos_n3_10=cell.get(176); BtnPos_n3_11=cell.get(177); BtnPos_n3_12=cell.get(178); BtnPos_n3_13=cell.get(179); BtnPos_n3_14=cell.get(180);
        BtnPos_n3_n1=cell.get(181); BtnPos_n3_n2=cell.get(182); BtnPos_n3_n3=cell.get(183); BtnPos_n3_n4=cell.get(184); BtnPos_n3_n9=cell.get(185); BtnPos_n3_n10=cell.get(186); BtnPos_n3_n11=cell.get(187); BtnPos_n3_n12=cell.get(188); BtnPos_n3_n13=cell.get(189);
        BtnPos_n3_5=cell.get(190); BtnPos_n3_6=cell.get(191); BtnPos_n3_7=cell.get(192); BtnPos_n3_8=cell.get(193); BtnPos_n3_9=cell.get(194); BtnPos_n3_15=cell.get(195); BtnPos_n3_16=cell.get(196); BtnPos_n3_17=cell.get(197); BtnPos_n3_18=cell.get(198); BtnPos_n3_19=cell.get(199);
        BtnPos_n3_n8=cell.get(200); BtnPos_n3_n7=cell.get(201); BtnPos_n3_n6=cell.get(202); BtnPos_n3_n5=cell.get(203); BtnPos_n3_n18=cell.get(204); BtnPos_n3_n17=cell.get(205); BtnPos_n3_n16=cell.get(206); BtnPos_n3_n15=cell.get(207); BtnPos_n3_n14=cell.get(208);
        BtnPos_n3_20=cell.get(209); BtnPos_n3_21=cell.get(210); BtnPos_n3_22=cell.get(211); BtnPos_n3_23=cell.get(212); BtnPos_n3_24=cell.get(213); BtnPos_n3_25=cell.get(214); BtnPos_n3_26=cell.get(215); BtnPos_n3_27=cell.get(216); BtnPos_n3_28=cell.get(217); BtnPos_n3_29=cell.get(218);
        BtnPos_n3_n27=cell.get(219); BtnPos_n3_n26=cell.get(220); BtnPos_n3_n25=cell.get(221); BtnPos_n3_n24=cell.get(222); BtnPos_n3_n23=cell.get(223); BtnPos_n3_n22=cell.get(224); BtnPos_n3_n21=cell.get(225); BtnPos_n3_n20=cell.get(226); BtnPos_n3_n19=cell.get(227);
    
        System.out.println(matrix.size());
    }
    
    private String toString(int size) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
