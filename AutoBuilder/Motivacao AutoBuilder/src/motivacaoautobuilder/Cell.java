/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motivacaoautobuilder;

import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author leonardocoelho
 */
public class Cell {
    private final String id;
    private String component;
    
    public Cell(String id){
        this.id = id;
        this.component = "";
    }
    
    public String getId(){
        return id;
    }
    public String getComp(){
        return component;
    }
    public String[] getData(){
        String[] data = {id, component};
        return data;
    }
    
    public boolean isEmpty(){
        return "".equals(component);
    }
    
    public void AddComp(JList list, DefaultListModel Used){
        component = list.getSelectedValue().toString();
        Used.addElement(list.getSelectedValue().toString());
    }
    
    public void RemoveComp(JList list, DefaultListModel Used){
        Used.remove(Used.indexOf(component));
        component = "";
    }
    
}
