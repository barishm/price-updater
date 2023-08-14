package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


import static org.example.Start.start;

public class MyFrame extends JFrame implements ActionListener {
    JButton button;
    JTextField excelPath;
    JTextField linkCell;
    JTextField priceCell;
    JLabel pathLabel;
    JLabel linkLabel;
    JLabel priceLabel;
    MyFrame(){
        button = new JButton();
        button.setBounds(200,250,100,50);
        button.setLabel("Start");
        button.addActionListener(this);
        excelPath = new JTextField();
        excelPath.setBounds(50,100,400,30);
        linkCell = new JTextField();
        linkCell.setBounds(70,150,50,30);
        priceCell = new JTextField();
        priceCell.setBounds(370,150,50,30);
        pathLabel = new JLabel("Excel Path");
        pathLabel.setBounds(220,70,110,30);
        linkLabel = new JLabel("Link Cell");
        linkLabel.setBounds(130,150,50,30);
        priceLabel = new JLabel("Price Cell");
        priceLabel.setBounds(300,150,70,30);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500,400);
        this.setVisible(true);
        this.add(button);
        this.add(excelPath);
        this.add(linkCell);
        this.add(priceCell);
        this.add(pathLabel);
        this.add(linkLabel);
        this.add(priceLabel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button) {
            String path = excelPath.getText();
            int linkCol = Integer.parseInt(linkCell.getText());
            int priceCol = Integer.parseInt(priceCell.getText());
            try {
                start(path,priceCol,linkCol);
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
