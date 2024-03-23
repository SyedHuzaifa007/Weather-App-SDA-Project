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
    JFrame frame1 = new JFrame();
        frame1.setSize(1080, 800); // set frame size
        frame1.setTitle("Weather-App"); // set frame title
        frame1.getContentPane().setBackground(new Color(2, 0, 22)); // set frame color
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // on cross close
        frame1.setResizable(false); // disable frame resizing


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

    // Text_Location
    JLabel location= new JLabel();
        location.setText("Lahore");
        location.setForeground(new Color(255,255,255)); // set font color
        location.setFont(new Font("Roboto",Font.BOLD,40)); // set font style
        location.setBounds(375, 100, 200, 100);

    // Text_Temp
    JLabel temp = new JLabel();
        temp.setText("31");
        temp.setForeground(new Color(255,255,255)); // set font color
        temp.setFont(new Font("Roboto",Font.BOLD,40)); // set font style
        temp.setBounds(375, 150, 200, 100);

    // Text_Longitude
    JLabel longi = new JLabel();
        longi.setText("Longitude: " + "31.5204° N");
        longi.setForeground(new Color(255,255,255)); // set font color
        longi.setFont(new Font("Roboto",Font.BOLD,20)); // set font style
        longi.setBounds(600, 130, 300, 100);

    // Text_Latitude
    JLabel lati = new JLabel();
        lati.setText("Latitude: " + "   74.3587° E");
        lati.setForeground(new Color(255,255,255)); // set font color
        lati.setFont(new Font("Roboto",Font.BOLD,20)); // set font style
        lati.setBounds(600, 160, 300, 100);

    //Text_Highlights
    JLabel highlights = new JLabel();
        highlights.setText("HIGHLIGHT'S");
        highlights.setForeground(new Color(170,170,170)); // set font color
        highlights.setFont(new Font("Roboto",Font.BOLD,15)); // set font style
        highlights.setBounds(180, 265, 300, 100);

    // Text_FeelsLike
    JLabel feel = new JLabel();
        feel.setText("Feels Like");
        feel.setForeground(new Color(170,170,170)); // set font color
        feel.setFont(new Font("Roboto",Font.BOLD,20)); // set font style
        feel.setBounds(220, 300, 300, 100);

    // Text_Sunrise
    JLabel sunrise = new JLabel();
        sunrise.setText("Sunrise");
        sunrise.setForeground(new Color(170,170,170)); // set font color
        sunrise.setFont(new Font("Roboto",Font.BOLD,20)); // set font style
        sunrise.setBounds(220, 450, 300, 100);

    // Text_MaxTemp
    JLabel maxtemp = new JLabel();
        maxtemp.setText("Maximum Temperature");
        maxtemp.setForeground(new Color(170,170,170)); // set font color
        maxtemp.setFont(new Font("Roboto",Font.BOLD,20)); // set font style
        maxtemp.setBounds(220, 590, 300, 100);

    // Text_Sunset
    JLabel sunset = new JLabel();
        sunset.setText("Sunset");
        sunset.setForeground(new Color(170,170,170)); // set font color
        sunset.setFont(new Font("Roboto",Font.BOLD,20)); // set font style
        sunset.setBounds(620, 450, 300, 100);

    // Text_MinTemp
    JLabel mintemp = new JLabel();
         mintemp.setText("Minimum Temperature");
         mintemp.setForeground(new Color(170,170,170)); // set font color
         mintemp.setFont(new Font("Roboto",Font.BOLD,20)); // set font style
         mintemp.setBounds(620, 590, 300, 100);


    //Text 5Day_Forcast
    JLabel five = new JLabel();
         five.setText("5-DAY FORCAST");
         five.setForeground(new Color(170,170,170)); // set font color
         five.setFont(new Font("Roboto",Font.BOLD,15)); // set font style
         five.setBounds(892, 235, 300, 100);

    //Text Day-1
    JLabel d_one = new JLabel();
         d_one.setText("Today");
         d_one.setForeground(new Color(170,170,170)); // set font color
         d_one.setFont(new Font("Roboto",Font.BOLD,15)); // set font style
         d_one.setBounds(892, 290, 300, 100);

    //Text Day-2
    JLabel d_two = new JLabel();
         d_two.setText("Tue");
         d_two.setForeground(new Color(170,170,170)); // set font color
         d_two.setFont(new Font("Roboto",Font.BOLD,15)); // set font style
         d_two.setBounds(892, 380, 300, 100);

    //Text Day-3
    JLabel d_three = new JLabel();
         d_three.setText("Wed");
         d_three.setForeground(new Color(170,170,170)); // set font color
         d_three.setFont(new Font("Roboto",Font.BOLD,15)); // set font style
         d_three.setBounds(892, 470, 300, 100);

    //Text Day-4
    JLabel d_four = new JLabel();
         d_four.setText("Thu");
         d_four.setForeground(new Color(170,170,170)); // set font color
         d_four.setFont(new Font("Roboto",Font.BOLD,15)); // set font style
         d_four.setBounds(892, 560, 300, 100);

    //Text Day-5
    JLabel d_five = new JLabel();
         d_five.setText("Fri");
         d_five.setForeground(new Color(170,170,170)); // set font color
         d_five.setFont(new Font("Roboto",Font.BOLD,15)); // set font style
         d_five.setBounds(892, 650, 300, 100);

    //Text Notification
    JLabel notification = new JLabel();
         notification.setText("NOTIFICATION");
         notification.setForeground(new Color(170,170,170)); // set font color
         notification.setFont(new Font("Roboto",Font.BOLD,15)); // set font style
         notification.setBounds(895, 40, 300, 100);

    //Text warning_badWeather
    JLabel weather = new JLabel();
         weather.setText("Warning! Bad Weather");
         weather.setForeground(new Color(255,255,255)); // set font color
         weather.setFont(new Font("Roboto",Font.BOLD,15)); // set font style
         weather.setBounds(895, 80, 300, 100);

    //Text warning_badquality
    JLabel quality = new JLabel();
         quality.setText("Warning! Bad Air");
         quality.setForeground(new Color(255,255,255)); // set font color
         quality.setFont(new Font("Roboto",Font.BOLD,15)); // set font style
         quality.setBounds(895, 150, 300, 100);


    //Text AQI
    JLabel aqi = new JLabel();
         aqi.setText("AQI: ");
         aqi.setForeground(new Color(170,170,170)); // set font color
         aqi.setFont(new Font("Roboto",Font.BOLD,30)); // set font style
         aqi.setBounds(400, 360, 300, 100);

    //Text O3(ug/m3)
    JLabel o3 = new JLabel();
         o3.setText("O3 (ug/m3)");
         o3.setForeground(new Color(170,170,170)); // set font color
         o3.setFont(new Font("Roboto",Font.BOLD,20)); // set font style
         o3.setBounds(220, 530, 300, 100);

    //Text PM10(ug/m3)
    JLabel PM10 = new JLabel();
         PM10.setText("PM10 (ug/m3)");
         PM10.setForeground(new Color(170,170,170)); // set font color
         PM10.setFont(new Font("Roboto",Font.BOLD,20)); // set font style
         PM10.setBounds(375, 530, 300, 100);

    //Text NO(ug/m3)
    JLabel NO = new JLabel();
         NO.setText("NO (ug/m3)");
         NO.setForeground(new Color(170,170,170)); // set font color
         NO.setFont(new Font("Roboto",Font.BOLD,20)); // set font style
         NO.setBounds(560, 530, 300, 100);

    //Text NO2(ug/m3)
    JLabel NO2 = new JLabel();
         NO2.setText("NO2 (ug/m3)");
         NO2.setForeground(new Color(170,170,170)); // set font color
         NO2.setFont(new Font("Roboto",Font.BOLD,20)); // set font style
         NO2.setBounds(725, 530, 300, 100);
}
