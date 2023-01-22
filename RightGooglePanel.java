import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.net.*;
import java.io.*;


public class RightGooglePanel{
    private static JButton searchButton;
    private static JTextField searchBox;
    
    static javax.swing.JPanel drawRightGooglePanel(){
        JPanel rightPanel = new JPanel();
        Listener listener = new Listener();

        // Search Box (Entry).
        searchBox = new JTextField("Search");
        searchBox.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        searchBox.setBounds(200,50,450,50);

        // Search Button.
        searchButton = new JButton("Google");
        searchButton.setFocusable(false);
        searchButton.setBackground(new Color(0xff0026FC));
        searchButton.setForeground(new Color(0xffE4E7F2));
        searchButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        searchButton.addActionListener(listener);
        searchButton.setBounds(700,50,100,50);

        // Adding the components to the panel.
        rightPanel.setLayout(null);
        rightPanel.add(searchBox);
        rightPanel.add(searchButton);
        

        return rightPanel;
    }


    private static class Listener implements java.awt.event.ActionListener{
        private String query;

        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == searchButton){
                this.query = searchBox.getText();
                System.out.println(this.query);
                searchBox.setText("");
                // seand request and display the response.


                
            }
        }
    }
}
