//(C) Matthew Krawczyk
package com.Mattk4355;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGUICalculator implements ActionListener {
    JButton a, b, c, d, e;
    JTextField field1 = new JTextField("");
    JTextField field2 = new JTextField("");
    JTextField field3 = new JTextField("");

    public void display(){
        field1.setEditable(false);
        a = new JButton("Add");
        b = new JButton("Subtract");
        c = new JButton("Multiply");
        d = new JButton("Divide");
        e = new JButton("Remainder");
        a.addActionListener(this);
        b.addActionListener(this);
        c.addActionListener(this);
        d.addActionListener(this);
        e.addActionListener(this);
        String[] option = {"Credits", "Close"};

        JPanel pane = new JPanel(new GridLayout(0, 1));
        pane.add(new JLabel("Number 1"));
        pane.add(field2);
        pane.add(new JLabel("Number 2"));
        pane.add(field3);
        pane.add(new JLabel(""));
        pane.add(a);
        pane.add(b);
        pane.add(c);
        pane.add(d);
        pane.add(e);
        pane.add(new JLabel("Answer"));
        pane.add(field1);

        int result = JOptionPane.showOptionDialog(null, pane, "Calculator", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, option, null);

        if (result == JOptionPane.YES_OPTION){
            credits();
        }
    }
    public void credits(){
        JPanel pane = new JPanel(new GridLayout(0, 1));
        String[] option = {"Calculator", "Close"};
        pane.add(new JLabel("Simple GUI Calculator v1.1"));
        pane.add(new JLabel("by Matthew Krawczyk"));

        int result = JOptionPane.showOptionDialog(null, pane, "Credits", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, option, null);

        if (result == JOptionPane.YES_OPTION){
            display();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        double f = Double.parseDouble(field2.getText());
        double g = Double.parseDouble(field3.getText());

        if(ae.getSource() == a) {
            field1.setText(Double.toString((f + g)));
        }
        if(ae.getSource() == b) {
            field1.setText(Double.toString((f - g)));
        }
        if(ae.getSource() == c) {
            field1.setText(Double.toString((f * g)));
        }
        if(ae.getSource() == d) {
            field1.setText(Double.toString((f / g)));
        }
        if(ae.getSource() == e) {
            field1.setText(Double.toString((f % g)));
        }
    }

    public static void main(String[] args) {
        SimpleGUICalculator s = new SimpleGUICalculator();
        s.display();
    }
}