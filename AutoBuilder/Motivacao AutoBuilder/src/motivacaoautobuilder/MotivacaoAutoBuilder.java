/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motivacaoautobuilder;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

/**
 *
 * @author leo_c
 */
public class MotivacaoAutoBuilder {

    /**
     * @param args the command line arguments
     */
    private DefaultListModel ListComponentsUsed = new DefaultListModel();
    private DefaultListModel ListComponentsTemp = new DefaultListModel();
    private DefaultListModel ListComponentsPrio = new DefaultListModel();
    
    private ArrayList<String> ref = new ArrayList<String>();
    private ArrayList<JToggleButton> matrix = new ArrayList<JToggleButton>();
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    ArrayList <String> StartSettings(String filename) throws FileNotFoundException{
        ArrayList <String> data = new ArrayList <String>();
        FileInputStream fstream = new FileInputStream(filename);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String line;
        
        try {
            while((line = br.readLine()) != null){
                data.add(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(MotivacaoAutoBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data;
    }
    
    boolean SaveGame(String path, ArrayList <String> backgroud, ArrayList <String> elements, ArrayList <String> trees, ArrayList <String> lixos, String pathInstall, String name ) throws FileNotFoundException{
        try (PrintWriter out = new PrintWriter(new FileOutputStream(path))){

            String text = "==Info==\n";
            text += name + "\n";
            text += pathInstall + "\n";
            text += "==EndInfo==\n";
            
            text += "==Backgroud==\n";
            for (String s : backgroud){
                text += s + "\n";
            }
            text += "==EndBackground==\n";
            
            text += "==Elements==\n";
            for (String s : elements){
                text += s + "\n";
            }
            text += "==EndElements==\n";
            
            text += "==Trees==\n";
            for (String s : trees){
                text += s + "\n";
            }
            text += "==EndTrees==\n";
            
            text += "==Lixos==\n";
            for (String s : lixos){
                text += s + "\n";
            }
            text += "==EndLixos==\n";
            
            out.println(text);
            
        }   catch (IOException e) {
            return false;
	}
        
        return true;
    }
    
    Image GetImg(String filepath, int width, int height){
        BufferedImage img = null;
        Image dimg = null;
        try {
            img = ImageIO.read(new File(filepath));
            dimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dimg;
    }
    
    ArrayList <ArrayList <String>> AbrirGame(String filename) throws FileNotFoundException{
        ArrayList <ArrayList <String>> data = new ArrayList <ArrayList <String>>();
        ArrayList <String> aux = new ArrayList <String>(); 
        
        FileInputStream fstream = new FileInputStream(filename);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String line;
        
        try {
            while((line = br.readLine()) != null){
                if (("==Info==".equals(line)) || ("==Backgroud==".equals(line)) || ("==Elements==".equals(line)) || ("==Trees==".equals(line)) || ("==Lixos==".equals(line))){
                    aux = new ArrayList <String>();
                }
                else{ if(("==EndInfo==".equals(line)) || ("==EndBackground==".equals(line)) || ("==EndElements==".equals(line)) || ("==EndTrees==".equals(line)) || ("==EndLixos==".equals(line))){
                    data.add(aux);
                }
                
                else{
                    aux.add(line);
                }}
                //System.out.println(line);
            }
            for (ArrayList <String> i : data){
            for (String s : i){
                System.out.println(s);
            }
            }
        } catch (IOException ex) {
            Logger.getLogger(MotivacaoAutoBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return data;
    }

    String ExecuteBuild(ArrayList<String> background, ArrayList<String> elemento, ArrayList<String> arvore, ArrayList<String> lixo, String path, String name, String com) throws IOException, InterruptedException {
        String cmd = com;
        System.out.println(cmd);
        
        String res = "";
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd, null, new File(path));
        
        BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line = "";
        while ((line = buf.readLine()) != null){
            res += line + "\n";
        }
        return res;
    }
    
    JToggleButton SetCell(JToggleButton btn, DefaultListModel used, DefaultListModel temp, JList list, ArrayList<JToggleButton> matrix, ArrayList<String> refM){
        ListComponentsUsed = used;
        ListComponentsTemp = temp;
        this.matrix = matrix;
        
        if(btn.isSelected()){
            if(!(ListComponentsUsed.contains(list.getSelectedValue().toString())))
                ListComponentsUsed.addElement(list.getSelectedValue().toString());
            int prio_in = Integer.parseInt(JOptionPane.showInputDialog("Qual a camada da imagem?\n\nEx.: Se a imagem_1 tem camada 0\n e a imagem_2 tem camada 1,\n a imagem_2 vai aparecer na frente da imagem_1"));
            System.out.println("prio: " + prio_in);
            System.out.println("ListComponentTemp_index: " + ListComponentsTemp.indexOf(list.getSelectedValue().toString()));
            System.out.println("Value: " + ListComponentsUsed.lastElement());
            ListComponentsPrio.addElement(prio_in);
            btn.setText(list.getSelectedValue().toString());
            ListComponentsTemp.remove(ListComponentsTemp.indexOf(list.getSelectedValue().toString()));
            ref.add(refM.get(matrix.indexOf(btn)));
            btn.setBackground(java.awt.Color.yellow);
        }
        else{
            ListComponentsPrio.remove(ListComponentsUsed.indexOf(btn.getText()));
            ListComponentsUsed.remove(ListComponentsUsed.indexOf(btn.getText()));
            ListComponentsTemp.addElement(btn.getText());
            ref.remove(refM.get(matrix.indexOf(btn)));
            btn.setText("");
            btn.setBackground(java.awt.Color.white);
        }
        
        this.matrix.set(matrix.indexOf(btn), btn);
        return btn;
    }
    
    DefaultListModel GetUsed(){
        return ListComponentsUsed;
    }
    
    DefaultListModel GetTemp(){
        return ListComponentsTemp;
    }
    
    DefaultListModel GetPrio(){
        return ListComponentsPrio;
    }
    
    ArrayList<JToggleButton> GetMatrix(){
        return matrix;
    }
    
    ArrayList<String> GetRef(){
        return ref;
    }

}
