package com.branden;

import javax.swing.*;
import java.awt.event.*;

public class Movies extends JFrame{
    private JLabel enterMovieNameLabel;
    private JTextField movieNameTextField;
    private JLabel resultsLabel;
    private JPanel rootPanel;
    private JButton searchButton;
    private Omdb omdb;
    Movies(){
        // set display
        super("Movie Ratings");
        setContentPane(rootPanel);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        // set up retrofit class
        omdb = new Omdb();
        // event listeners
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = movieNameTextField.getText();
                if ( text.length() > 2){


                   omdb.getMovieByName(text);

                } else {
                    resultsLabel.setText("Must enter a movie name longer than 2 characters");
                    pack();
                }
            }
        });
    }
}
