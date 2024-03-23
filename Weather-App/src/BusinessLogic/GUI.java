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


    //Variable FeelsLike
    JLabel fe = new JLabel();
         fe.setText("33");
         fe.setForeground(new Color(255,255,255)); // set font color
         fe.setFont(new Font("Roboto",Font.BOLD,50)); // set font style
         fe.setBounds(265, 350, 200, 100);

    //Variable SunRise
    JLabel sr = new JLabel();
         sr.setText("06:35");
         sr.setForeground(new Color(255,255,255)); // set font color
         sr.setFont(new Font("Roboto",Font.BOLD,50)); // set font style
         sr.setBounds(265, 505, 200, 100);

    //Variable MaxTemp
    JLabel mt = new JLabel();
         mt.setText("31");
         mt.setForeground(new Color(255,255,255)); // set font color
         mt.setFont(new Font("Roboto",Font.BOLD,50)); // set font style
         mt.setBounds(265, 640, 200, 100);

    //Variable SunSet
    JLabel ss = new JLabel();
         ss.setText("20:58");
         ss.setForeground(new Color(255,255,255)); // set font color
         ss.setFont(new Font("Roboto",Font.BOLD,50)); // set font style
         ss.setBounds(665, 505, 200, 100);

    //Variable MinTemp
    JLabel mint = new JLabel();
         mint.setText("23");
         mint.setForeground(new Color(255,255,255)); // set font color
         mint.setFont(new Font("Roboto",Font.BOLD,50)); // set font style
         mint.setBounds(665, 640, 200, 100);

    //Variable Day-1
    JLabel t_one = new JLabel();
         t_one.setText("36");
         t_one.setForeground(new Color(255,255,255)); // set font color
         t_one.setFont(new Font("Roboto",Font.BOLD,40)); // set font style
         t_one.setBounds(960, 290, 200, 100);

    //Variable Day-2
    JLabel t_two = new JLabel();
         t_two.setText("27");
         t_two.setForeground(new Color(255,255,255)); // set font color
         t_two.setFont(new Font("Roboto",Font.BOLD,40)); // set font style
         t_two.setBounds(960, 380, 200, 100);

    //Variable Day-3
    JLabel t_three = new JLabel();
         t_three.setText("25");
         t_three.setForeground(new Color(255,255,255)); // set font color
         t_three.setFont(new Font("Roboto",Font.BOLD,40)); // set font style
         t_three.setBounds(960, 470, 200, 100);

    //Variable Day-4
    JLabel t_four = new JLabel();
         t_four.setText("28");
         t_four.setForeground(new Color(255,255,255)); // set font color
         t_four.setFont(new Font("Roboto",Font.BOLD,40)); // set font style
         t_four.setBounds(960, 560, 200, 100);

    //Variable Day-5
    JLabel t_five = new JLabel();
         t_five.setText("23");
         t_five.setForeground(new Color(255,255,255)); // set font color
         t_five.setFont(new Font("Roboto",Font.BOLD,40)); // set font style
         t_five.setBounds(960, 650, 200, 100);


    //Variable aqi
    JLabel aq = new JLabel();
         aq.setText("3");
         aq.setForeground(new Color(255,255,255)); // set font color
         aq.setFont(new Font("Roboto",Font.BOLD,50)); // set font style
         aq.setBounds(500, 360, 200, 100);

    //Variable O3(ug/m3)
    JLabel vo3 = new JLabel();
         vo3.setText("52");
         vo3.setForeground(new Color(255,255,255)); // set font color
         vo3.setFont(new Font("Roboto",Font.BOLD,50)); // set font style
         vo3.setBounds(230, 580, 200, 100);

    //Variable PM(ug/m3)
    JLabel pm = new JLabel();
         pm.setText("25");
         pm.setForeground(new Color(255,255,255)); // set font color
         pm.setFont(new Font("Roboto",Font.BOLD,50)); // set font style
         pm.setBounds(400, 580, 200, 100);

    //Variable NO(ug/m3)
    JLabel vno = new JLabel();
         vno.setText("22");
         vno.setForeground(new Color(255,255,255)); // set font color
         vno.setFont(new Font("Roboto",Font.BOLD,50)); // set font style
         vno.setBounds(570, 580, 200, 100);

    //Variable NO(ug/m3)
    JLabel no2 = new JLabel();
         no2.setText("12");
         no2.setForeground(new Color(255,255,255)); // set font color
         no2.setFont(new Font("Roboto",Font.BOLD,50)); // set font style
         no2.setBounds(740, 580, 200, 100);

    //backbox1
    JLabel backbox1 = new JLabel();
         backbox1.setOpaque(true);
         backbox1.setBackground(new Color(13,31,51));
         backbox1.setBounds(170, 300, 700, 450);

    //backbox2
    JLabel backbox2 = new JLabel();
         backbox2.setOpaque(true);
         backbox2.setBackground(new Color(13,31,51));
         backbox2.setBounds(30, 50, 75, 650);

    //backbox3
    JLabel backbox3 = new JLabel();
         backbox3.setOpaque(true);
         backbox3.setBackground(new Color(13,31,51));
         backbox3.setBounds(160, 30, 700, 50);

    //backbox4
    JLabel backbox4 = new JLabel();
          backbox4.setOpaque(true);
          backbox4.setBackground(new Color(13,31,51));
          backbox4.setBounds(885, 270, 170, 480);

    //backbox5
    JLabel backbox5 = new JLabel();
        backbox5.setOpaque(true);
        backbox5.setBackground(new Color(13,31,51));
        backbox5.setBounds(885, 70, 170, 175);

    //Line 1
    JLabel line1 = new JLabel();
         line1.setOpaque(true);
         line1.setBackground(new Color(4,98,239));
         line1.setBounds(170, 280, 700, 1);

    //Line 2
    JLabel line2 = new JLabel();
         line2.setOpaque(true);
         line2.setBackground(new Color(4,98,239));
         line2.setBounds(510, 340, 2, 390);

    //Line 3
    JLabel line3 = new JLabel();
          line3.setOpaque(true);
          line3.setBackground(new Color(4,98,239));
          line3.setBounds(200, 460, 640, 1);

    //Line 4
    JLabel line4 = new JLabel();
          line4.setOpaque(true);
          line4.setBackground(new Color(4,98,239));
          line4.setBounds(200, 610, 640, 1);

    //Line 5
    JLabel line5 = new JLabel();
           line5.setOpaque(true);
           line5.setBackground(new Color(4,98,239));
           line5.setBounds(895, 383, 150, 2);

    //Line 6
    JLabel line6 = new JLabel();
           line6.setOpaque(true);
           line6.setBackground(new Color(4,98,239));
           line6.setBounds(895, 470, 150, 2);

    //Line 7
    JLabel line7 = new JLabel();
        line7.setOpaque(true);
        line7.setBackground(new Color(4,98,239));
        line7.setBounds(895, 560, 150, 2);

    //Line 8
    JLabel line8 = new JLabel();
        line8.setOpaque(true);
        line8.setBackground(new Color(4,98,239));
        line8.setBounds(895, 650, 150, 2);

    //Line 9
    JLabel line9 = new JLabel();
        line9.setOpaque(true);
        line9.setBackground(new Color(4,98,239));
        line9.setBounds(895, 170, 150, 2);

    //Line 10
    JLabel line10 = new JLabel();
        line10.setOpaque(true);
        line10.setBackground(new Color(4,98,239));
        line10.setBounds(200, 500, 640, 1);

    //Line 11
    JLabel line11 = new JLabel();
        line11.setOpaque(true);
        line11.setBackground(new Color(4,98,239));
        line11.setBounds(355, 530, 2, 200);

    //Line 12
    JLabel line12 = new JLabel();
        line12.setOpaque(true);
        line12.setBackground(new Color(4,98,239));
        line12.setBounds(535, 530, 2, 200);

    //Line 13
    JLabel line13 = new JLabel();
         line13.setOpaque(true);
         line13.setBackground(new Color(4,98,239));
         line13.setBounds(700, 530, 2, 200);

}
