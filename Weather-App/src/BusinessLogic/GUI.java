package BusinessLogic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

public class GUI {
    //Text Field
    JTextField field = new JTextField();
        field.setPreferredSize(new void Dimension(250,40));
        field.setFont(new void Font("Roboto",Font.BOLD,20));
        field.setForeground(new Color(255,255,255));
        field.setBackground(new Color(13,31,51));
        field.setBounds(220, 35, 250, 40); // Adjusted position
        field.setBorder(null); // Remove border

    // storing typed data into data
        field.addActionListener (e->
    {
        String data = field.getText();

        System.out.println("Input: " + data);
    });
}
