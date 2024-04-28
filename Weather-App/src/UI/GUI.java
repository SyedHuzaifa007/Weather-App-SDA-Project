package UI;

import java.time.DayOfWeek;
import java.time.LocalDate;
import BusinessLogic.Main;
import javax.swing.border.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;

import javax.swing.*;

public class GUI {

    JFrame frame1 = new JFrame();
    JFrame frame2 = new JFrame();
    JFrame loading = new JFrame();
    String typed = " ";

    public GUI()
    {
    }
    public void add(String Location_got,String longi_got, String lati_got, String temp_got, String feel_got, String min_got, String max_got, String sunrise_got, String sunset_got, String stamp_got, String day1_got, String day2_got, String day3_got, String day4_got, String day5_got, String aqi_got, String CO_got, String NO_got, String NO2_got, String O3_got, String SO2_got, String NH3_got, String PM25_got, String PM10_got, String n_weather, String n_air)
    {
        loading.dispose();
        //Application Frame
        frame1.setSize(1080, 800); // set frame size
        frame1.setTitle("Weather-App"); // set frame title
        frame1.getContentPane().setBackground(new Color(2, 0, 22)); // set frame color
        frame1.setResizable(false); // disable frame resizing
        frame1.setLocationRelativeTo(null);


        //Text Field
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(250,40));
        field.setFont(new Font("Roboto",Font.BOLD,20));
        field.setForeground(new Color(255,255,255));
        field.setBackground(new Color(13,31,51));
        field.setBounds(220, 35, 250, 40); // Adjusted position
        field.setBorder(null); // Remove border

        // storing typed data into data
        field.addActionListener (e->
        {
            typed = field.getText();
            frame1.dispose();
            frame2.dispose();
            try {
                Main.processData(typed);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // Text_Location
        JLabel location= new JLabel();
        location.setText(Location_got);
        location.setForeground(new Color(255,255,255)); // set font color
        location.setFont(new Font("Roboto",Font.BOLD,40)); // set font style
        location.setBounds(375, 100, 600, 100);

        // Text_Temp
        JLabel temp = new JLabel();
        temp.setText(temp_got+"°C");
        temp.setForeground(new Color(255,255,255)); // set font color
        temp.setFont(new Font("Roboto",Font.BOLD,40)); // set font style
        temp.setBounds(375, 150, 200, 100);

        // Text_Longitude
        JLabel longi = new JLabel();
        longi.setText("Longitude: " + longi_got.substring(0, Math.min(14, longi_got.length())) + "°");
        longi.setForeground(new Color(255,255,255)); // set font color
        longi.setFont(new Font("Roboto",Font.BOLD,20)); // set font style
        longi.setBounds(600, 130, 300, 100);

        // Text_Latitude
        JLabel lati = new JLabel();
        lati.setText("Latitude:    " + lati_got.substring(0, Math.min(14, lati_got.length())) + "°");
        lati.setForeground(new Color(255,255,255)); // set font color
        lati.setFont(new Font("Roboto",Font.BOLD,20)); // set font style
        lati.setBounds(600, 160, 300, 100);

        //Text_Highlights
        JLabel highlights = new JLabel();
        highlights.setText("HIGHLIGHT'S");
        highlights.setForeground(new Color(170,170,170)); // set font color
        highlights.setFont(new Font("Roboto",Font.BOLD,15)); // set font style
        highlights.setBounds(183, 268, 300, 100);

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
        five.setBounds(894, 237, 300, 100);

        //Text Day-1
        LocalDate currentDate = LocalDate.now();
        String current = String.valueOf(currentDate.getDayOfWeek());

        JLabel d_one = new JLabel();
        d_one.setText(current.substring(0,3));
        d_one.setForeground(new Color(170,170,170)); // set font color
        d_one.setFont(new Font("Roboto",Font.BOLD,15)); // set font style
        d_one.setBounds(892, 290, 300, 100);

        //Text Day-2
        LocalDate nextDay = currentDate.plusDays(1);
        String day2 = String.valueOf(nextDay.getDayOfWeek());

        JLabel d_two = new JLabel();
        d_two.setText(day2.substring(0,3));
        d_two.setForeground(new Color(170,170,170)); // set font color
        d_two.setFont(new Font("Roboto",Font.BOLD,15)); // set font style
        d_two.setBounds(892, 380, 300, 100);

        //Text Day-3
        nextDay = currentDate.plusDays(2);
        String day3 = String.valueOf(nextDay.getDayOfWeek());

        JLabel d_three = new JLabel();
        d_three.setText(day3.substring(0,3));
        d_three.setForeground(new Color(170,170,170)); // set font color
        d_three.setFont(new Font("Roboto",Font.BOLD,15)); // set font style
        d_three.setBounds(892, 470, 300, 100);

        //Text Day-4
        nextDay = currentDate.plusDays(3);
        String day4 = String.valueOf(nextDay.getDayOfWeek());

        JLabel d_four = new JLabel();
        d_four.setText(day4.substring(0,3));
        d_four.setForeground(new Color(170,170,170)); // set font color
        d_four.setFont(new Font("Roboto",Font.BOLD,15)); // set font style
        d_four.setBounds(892, 560, 300, 100);

        //Text Day-5
        nextDay = currentDate.plusDays(4);
        String day5 = String.valueOf(nextDay.getDayOfWeek());

        JLabel d_five = new JLabel();
        d_five.setText(day5.substring(0,3));
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
        weather.setText(n_weather);
        weather.setForeground(new Color(255,255,255)); // set font color
        weather.setFont(new Font("Roboto",Font.BOLD,13)); // set font style
        weather.setBounds(895, 80, 300, 100);

        //Text warning_badquality
        JLabel quality = new JLabel();
        quality.setText(n_air);
        quality.setForeground(new Color(255,255,255)); // set font color
        quality.setFont(new Font("Roboto",Font.BOLD,13)); // set font style
        quality.setBounds(895, 150, 300, 100);


        //Text AQI
        JLabel aqi = new JLabel();
        aqi.setText("AQI: ");
        aqi.setForeground(new Color(170,170,170)); // set font color
        aqi.setFont(new Font("Roboto",Font.BOLD,30)); // set font style
        aqi.setBounds(400, 360, 300, 100);

        //Text CO(ug/m3)
        JLabel CO = new JLabel();
        CO.setText("CO (ug/m3)");
        CO.setForeground(new Color(170,170,170)); // set font color
        CO.setFont(new Font("Roboto",Font.BOLD,20)); // set font style
        CO.setBounds(220, 500, 300, 100);

        //Text NO(ug/m3)
        JLabel NO = new JLabel();
        NO.setText("NO (ug/m3)");
        NO.setForeground(new Color(170,170,170)); // set font color
        NO.setFont(new Font("Roboto",Font.BOLD,20)); // set font style
        NO.setBounds(390, 500, 300, 100);

        //Text NO2(ug/m3)
        JLabel NO2 = new JLabel();
        NO2.setText("NO2 (ug/m3)");
        NO2.setForeground(new Color(170,170,170)); // set font color
        NO2.setFont(new Font("Roboto",Font.BOLD,20)); // set font style
        NO2.setBounds(560, 500, 300, 100);

        //Text O3(ug/m3)
        JLabel o3 = new JLabel();
        o3.setText("O3 (ug/m3)");
        o3.setForeground(new Color(170,170,170)); // set font color
        o3.setFont(new Font("Roboto",Font.BOLD,20)); // set font style
        o3.setBounds(730, 500, 300, 100);

        //Text SO2(ug/m3)
        JLabel SO2 = new JLabel();
        SO2.setText("SO2 (ug/m3)");
        SO2.setForeground(new Color(170,170,170)); // set font color
        SO2.setFont(new Font("Roboto",Font.BOLD,20)); // set font style
        SO2.setBounds(220, 600, 300, 100);

        //Text NH3(ug/m3)
        JLabel NH3 = new JLabel();
        NH3.setText("NH3 (ug/m3)");
        NH3.setForeground(new Color(170,170,170)); // set font color
        NH3.setFont(new Font("Roboto",Font.BOLD,20)); // set font style
        NH3.setBounds(390, 600, 300, 100);

        //Text PM25(ug/m3)
        JLabel PM25 = new JLabel();
        PM25.setText("PM25 (ug/m3)");
        PM25.setForeground(new Color(170,170,170)); // set font color
        PM25.setFont(new Font("Roboto",Font.BOLD,20)); // set font style
        PM25.setBounds(550, 600, 300, 100);

        //Text PM10(ug/m3)
        JLabel PM10 = new JLabel();
        PM10.setText("PM10 (ug/m3)");
        PM10.setForeground(new Color(170,170,170)); // set font color
        PM10.setFont(new Font("Roboto",Font.BOLD,20)); // set font style
        PM10.setBounds(720, 600, 300, 100);


        //Variable FeelsLike
        JLabel fe = new JLabel();
        fe.setText(feel_got+"°C");
        fe.setForeground(new Color(255,255,255)); // set font color
        fe.setFont(new Font("Roboto",Font.BOLD,50)); // set font style
        fe.setBounds(265, 350, 200, 100);

        //Variable SunRise
        JLabel sr = new JLabel();
        sr.setText(sunrise_got);
        sr.setForeground(new Color(255,255,255)); // set font color
        sr.setFont(new Font("Roboto",Font.BOLD,40)); // set font style
        sr.setBounds(255, 505, 200, 100);

        //Variable MaxTemp
        JLabel mt = new JLabel();
        mt.setText(max_got+"°C");
        mt.setForeground(new Color(255,255,255)); // set font color
        mt.setFont(new Font("Roboto",Font.BOLD,50)); // set font style
        mt.setBounds(265, 640, 200, 100);

        //Variable SunSet
        JLabel ss = new JLabel();
        ss.setText(sunset_got);
        ss.setForeground(new Color(255,255,255)); // set font color
        ss.setFont(new Font("Roboto",Font.BOLD,40)); // set font style
        ss.setBounds(655, 505, 200, 100);

        //Variable MinTemp
        JLabel mint = new JLabel();
        mint.setText(min_got+"°C");
        mint.setForeground(new Color(255,255,255)); // set font color
        mint.setFont(new Font("Roboto",Font.BOLD,50)); // set font style
        mint.setBounds(665, 640, 200, 100);

        //Variable Day-1
        JLabel t_one = new JLabel();
        t_one.setText(day1_got+"°C");
        t_one.setForeground(new Color(255,255,255)); // set font color
        t_one.setFont(new Font("Roboto",Font.BOLD,30)); // set font style
        t_one.setBounds(950, 290, 200, 100);

        //Variable Day-2
        JLabel t_two = new JLabel();
        t_two.setText(day2_got+"°C");
        t_two.setForeground(new Color(255,255,255)); // set font color
        t_two.setFont(new Font("Roboto",Font.BOLD,30)); // set font style
        t_two.setBounds(950, 380, 200, 100);

        //Variable Day-3
        JLabel t_three = new JLabel();
        t_three.setText(day3_got+"°C");
        t_three.setForeground(new Color(255,255,255)); // set font color
        t_three.setFont(new Font("Roboto",Font.BOLD,30)); // set font style
        t_three.setBounds(950, 470, 200, 100);

        //Variable Day-4
        JLabel t_four = new JLabel();
        t_four.setText(day4_got+"°C");
        t_four.setForeground(new Color(255,255,255)); // set font color
        t_four.setFont(new Font("Roboto",Font.BOLD,30)); // set font style
        t_four.setBounds(950, 560, 200, 100);

        //Variable Day-5
        JLabel t_five = new JLabel();
        t_five.setText(day5_got+"°C");
        t_five.setForeground(new Color(255,255,255)); // set font color
        t_five.setFont(new Font("Roboto",Font.BOLD,30)); // set font style
        t_five.setBounds(950, 650, 200, 100);


        //Variable aqi
        JLabel aq = new JLabel();
        aq.setText(aqi_got);
        aq.setForeground(new Color(255,255,255)); // set font color
        aq.setFont(new Font("Roboto",Font.BOLD,50)); // set font style
        aq.setBounds(500, 360, 200, 100);

        //Variable CO(ug/m3)
        JLabel vCO = new JLabel();
        vCO.setText(CO_got);
        vCO.setForeground(new Color(255,255,255)); // set font color
        vCO.setFont(new Font("Roboto",Font.BOLD,30)); // set font style
        vCO.setBounds(220, 545, 200, 100);

        //Variable NO(ug/m3)
        JLabel vno = new JLabel();
        vno.setText(NO_got);
        vno.setForeground(new Color(255,255,255)); // set font color
        vno.setFont(new Font("Roboto",Font.BOLD,30)); // set font style
        vno.setBounds(390, 545, 200, 100);

        //Variable NO2(ug/m3)
        JLabel no2 = new JLabel();
        no2.setText(NO2_got);
        no2.setForeground(new Color(255,255,255)); // set font color
        no2.setFont(new Font("Roboto",Font.BOLD,30)); // set font style
        no2.setBounds(560, 545, 200, 100);

        //Variable O3(ug/m3)
        JLabel vo3 = new JLabel();
        vo3.setText(O3_got);
        vo3.setForeground(new Color(255,255,255)); // set font color
        vo3.setFont(new Font("Roboto",Font.BOLD,30)); // set font style
        vo3.setBounds(730, 545, 200, 100);

        //Variable SO2(ug/m3)
        JLabel vSO2 = new JLabel();
        vSO2.setText(SO2_got);
        vSO2.setForeground(new Color(255,255,255)); // set font color
        vSO2.setFont(new Font("Roboto",Font.BOLD,30)); // set font style
        vSO2.setBounds(220, 645, 200, 100);

        //Variable SO2(ug/m3)
        JLabel vNH3 = new JLabel();
        vNH3.setText(NH3_got);
        vNH3.setForeground(new Color(255,255,255)); // set font color
        vNH3.setFont(new Font("Roboto",Font.BOLD,30)); // set font style
        vNH3.setBounds(390, 645, 200, 100);

        //Variable PM25(ug/m3)
        JLabel pm25 = new JLabel();
        pm25.setText(PM25_got);
        pm25.setForeground(new Color(255,255,255)); // set font color
        pm25.setFont(new Font("Roboto",Font.BOLD,30)); // set font style
        pm25.setBounds(550, 645, 200, 100);

        //Variable PM25(ug/m3)
        JLabel pm10 = new JLabel();
        pm10.setText(PM10_got);
        pm10.setForeground(new Color(255,255,255)); // set font color
        pm10.setFont(new Font("Roboto",Font.BOLD,30)); // set font style
        pm10.setBounds(720, 645, 200, 100);


        // Create rounded border
        int borderRadius = 30; // You can adjust this value to change the roundness
        Border roundedBorder = new Border() {
            private int borderWidth = 1;

            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2d = (Graphics2D) g.create();

                // Draw the background
                g2d.setColor(new Color(13, 31, 51));
                g2d.fillRoundRect(x, y, width - 1, height - 1, borderRadius, borderRadius);

                // Draw the border
                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(borderWidth));
                g2d.drawRoundRect(x, y, width - 1, height - 1, borderRadius, borderRadius);

                g2d.dispose();
            }

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(borderWidth, borderWidth, borderWidth, borderWidth);
            }

            @Override
            public boolean isBorderOpaque() {
                return true;
            }
        };

        //backbox1
        JLabel backbox1 = new JLabel();
        backbox1.setOpaque(true);
        backbox1.setBackground(new Color(2, 0, 22));
        backbox1.setBounds(170, 300, 700, 450);
        backbox1.setBorder(roundedBorder);

        //backbox2
        JLabel backbox2 = new JLabel();
        backbox2.setOpaque(true);
        backbox2.setBackground(new Color(2, 0, 22));
        backbox2.setBounds(30, 50, 75, 650);
        backbox2.setBorder(roundedBorder);

        //backbox3
        JLabel backbox3 = new JLabel();
        backbox3.setOpaque(true);
        backbox3.setBackground(new Color(2, 0, 22));
        backbox3.setBounds(160, 30, 700, 50);
        backbox3.setBorder(roundedBorder);

        //backbox4
        JLabel backbox4 = new JLabel();
        backbox4.setOpaque(true);
        backbox4.setBackground(new Color(2, 0, 22));
        backbox4.setBounds(885, 270, 170, 480);
        backbox4.setBorder(roundedBorder);

        //backbox5
        JLabel backbox5 = new JLabel();
        backbox5.setOpaque(true);
        backbox5.setBackground(new Color(2, 0, 22));
        backbox5.setBounds(885, 70, 170, 175);
        backbox5.setBorder(roundedBorder);

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


        // Label Search
        JLabel searchLabel = new JLabel();
        ImageIcon searchimg = new ImageIcon("Media\\Search.png");
        searchLabel.setIcon(searchimg);

        int searchWidth = 30;
        int searchHeight = 30;
        ImageIcon scaledsearch = new ImageIcon(searchimg.getImage().getScaledInstance(searchWidth, searchHeight, java.awt.Image.SCALE_SMOOTH));
        searchLabel.setIcon(scaledsearch);
        searchLabel.setBounds(180, 40, searchWidth, searchHeight);

        // Label_Sun
        JLabel label2 = new JLabel();
        ImageIcon Sun = new ImageIcon("Media\\Sun.png");
        label2.setIcon(Sun);
        // Adjust the size of the sun icon based on label size
        int labelWidth = 150; // Example width
        int labelHeight = 150; // Example height
        ImageIcon scaledSun = new ImageIcon(Sun.getImage().getScaledInstance(labelWidth, labelHeight, java.awt.Image.SCALE_SMOOTH));
        label2.setIcon(scaledSun);
        label2.setBounds(200, 100, labelWidth, labelHeight); // Set bounds for label2


        // Label_FeLike
        JLabel feeLabel = new JLabel();
        ImageIcon feelimg = new ImageIcon("Media\\Temperature.png");
        feeLabel.setIcon(feelimg);
        // Adjust the size of the sun icon based on label size
        int feelWidth = 30;
        int feelHeight = 50;
        ImageIcon scaledfeel = new ImageIcon(feelimg.getImage().getScaledInstance(feelWidth, feelHeight, java.awt.Image.SCALE_SMOOTH));
        feeLabel.setIcon(scaledfeel);
        feeLabel.setBounds(205, 375, feelWidth, feelHeight);

        // Label_Sunrise
        JLabel rise = new JLabel();
        ImageIcon riseimg = new ImageIcon("Media\\SunRise.png");
        rise.setIcon(riseimg);
        // Adjust the size of the sun icon based on label size
        int riseWidth = 50;
        int riseHeight = 50;
        ImageIcon scaledrise = new ImageIcon(riseimg.getImage().getScaledInstance(riseWidth, riseHeight, java.awt.Image.SCALE_SMOOTH));
        rise.setIcon(scaledrise);
        rise.setBounds(195, 525, riseWidth, riseHeight);

        // LabelMaxTemp
        JLabel max = new JLabel();
        ImageIcon maximg = new ImageIcon("Media\\Warm.png");
        max.setIcon(maximg);
        // Adjust the size of the sun icon based on label size
        int maxWidth = 40;
        int maxHeight = 40;
        ImageIcon scaledmax = new ImageIcon(maximg.getImage().getScaledInstance(maxWidth, maxHeight, java.awt.Image.SCALE_SMOOTH));
        max.setIcon(scaledmax);
        max.setBounds(195, 670, maxWidth, maxHeight);

        // Label_Sunset
        JLabel set = new JLabel();
        ImageIcon setimg = new ImageIcon("Media\\SunSet.png");
        set.setIcon(setimg);
        // Adjust the size of the sun icon based on label size
        int setWidth = 50;
        int setHeight = 50;
        ImageIcon scaledset = new ImageIcon(setimg.getImage().getScaledInstance(setWidth, setHeight, java.awt.Image.SCALE_SMOOTH));
        set.setIcon(scaledset);
        set.setBounds(595, 525, setWidth, setHeight);

        // LabelminTemp
        JLabel min = new JLabel();
        ImageIcon minimg = new ImageIcon("Media\\Cold.png");
        min.setIcon(minimg);
        // Adjust the size of the sun icon based on label size
        int minWidth = 40;
        int minHeight = 40;
        ImageIcon scaledmin = new ImageIcon(minimg.getImage().getScaledInstance(minWidth, minHeight, java.awt.Image.SCALE_SMOOTH));
        min.setIcon(scaledmin);
        min.setBounds(595, 670, minWidth, minHeight);

        // LabelNotification
        JLabel notify = new JLabel();
        ImageIcon notifyimg = new ImageIcon("Media\\Notification Bell.png");
        notify.setIcon(notifyimg);
        // Adjust the size of the sun icon based on label size
        int notifyWidth = 20;
        int notifyHeight = 25;
        ImageIcon scalednotify = new ImageIcon(notifyimg.getImage().getScaledInstance(notifyWidth, notifyHeight, java.awt.Image.SCALE_SMOOTH));
        notify.setIcon(scalednotify);
        notify.setBounds(1025, 80, notifyWidth, notifyHeight);




        // Button-Exit
        JButton exit = new JButton();
        ImageIcon exitimg = new ImageIcon("Media\\Vector.png");
        exit.setIcon(exitimg);

        // Adjust the size of the sun icon based on label size
        int exitWidth = 50; // Example width
        int exitHeight = 50; // Example height
        ImageIcon scaledexit = new ImageIcon(exitimg.getImage().getScaledInstance(exitWidth, exitHeight, java.awt.Image.SCALE_SMOOTH));
        exit.setIcon(scaledexit);

        exit.setBounds(43, 625, exitWidth, exitHeight);
        exit.setBorderPainted(false);
        exit.setBackground(new Color(13,31,51));
        exit.addActionListener(e -> {
            frame1.dispose();
            disposeLoadingFrame();
        }); // closes the frame

        //View more Button
        JButton more = new JButton();
        more.setText("View More");
        more.setBounds(650,360,100,50);;
        more.setBackground(new Color(7,159,244));
        more.setBorderPainted(false);
        more.setFocusable(false);

        //Back Button
        JButton back = new JButton();
        back.setText("Back");
        back.setBounds(200,320,100,50);;
        back.setBackground(new Color(7,159,244));
        back.setBorderPainted(false);
        back.setFocusable(false);

        //when more buttton presses
        more.addActionListener(e->{
            frame1.dispose();
            frame2.setSize(1080, 800); // set frame size
            frame2.setTitle("Weather-App"); // set frame title
            frame2.getContentPane().setBackground(new Color(2, 0, 22)); // set frame color
            frame2.setResizable(false); // disable frame resizing
            frame2.setLocationRelativeTo(null);

            JLayeredPane Panel2 = new JLayeredPane();
            Panel2.setBounds(0, 0, 1080, 800);

            Panel2.add(field);
            Panel2.add(searchLabel);

            Panel2.add(exit);
            Panel2.add(back);

            Panel2.add(label2);
            Panel2.add(location);
            Panel2.add(temp);
            Panel2.add(lati);
            Panel2.add(longi);

            Panel2.add(aqi);
            Panel2.add(CO);
            Panel2.add(NO);
            Panel2.add(NO2);
            Panel2.add(o3);
            Panel2.add(SO2);
            Panel2.add(NH3);
            Panel2.add(PM25);
            Panel2.add(PM10);


            Panel2.add(line1);
            Panel2.add(line5);
            Panel2.add(line6);
            Panel2.add(line7);
            Panel2.add(line8);
            Panel2.add(line9);
            Panel2.add(line10);
            Panel2.add(line11);
            Panel2.add(line12);
            Panel2.add(line13);


            Panel2.add(notify);

            Panel2.add(five);
            Panel2.add(d_one);
            Panel2.add(d_two);
            Panel2.add(d_three);
            Panel2.add(d_four);
            Panel2.add(d_five);

            Panel2.add(notification);
            Panel2.add(weather);
            Panel2.add(quality);

            Panel2.add(t_one);
            Panel2.add(t_two);
            Panel2.add(t_three);
            Panel2.add(t_four);
            Panel2.add(t_five);

            Panel2.add(aq);
            Panel2.add(vCO);
            Panel2.add(vno);
            Panel2.add(no2);
            Panel2.add(vo3);
            Panel2.add(vSO2);
            Panel2.add(vNH3);
            Panel2.add(pm25);
            Panel2.add(pm10);

            Panel2.add(backbox1);
            Panel2.add(backbox2);
            Panel2.add(backbox3);
            Panel2.add(backbox4);
            Panel2.add(backbox5);

            frame2.add(Panel2);
            frame2.setLayout(null);
            frame2.setVisible(true); // see frame

            exit.addActionListener(g->{
                frame2.dispose();
                disposeLoadingFrame();
            });

            back.addActionListener(f->{
                frame2.dispose();


                frame1.setSize(1080, 800); // set frame size
                frame1.setTitle("Weather-App"); // set frame title
                frame1.getContentPane().setBackground(new Color(2, 0, 22)); // set frame color
                frame1.setResizable(false); // disable frame resizing
                frame1.setLocationRelativeTo(null);

                JLayeredPane Panel = new JLayeredPane();
                Panel.setBounds(0, 0, 1080, 800);

                Panel.add(field);
                Panel.add(searchLabel);

                Panel.add(exit);
                Panel.add(more);

                Panel.add(label2);
                Panel.add(location);
                Panel.add(temp);
                Panel.add(lati);
                Panel.add(longi);

                Panel.add(line1);
                Panel.add(line2);
                Panel.add(line3);
                Panel.add(line4);
                Panel.add(line5);
                Panel.add(line6);
                Panel.add(line7);
                Panel.add(line8);
                Panel.add(line9);

                Panel.add(highlights);
                Panel.add(feel);
                Panel.add(feeLabel);
                Panel.add(sunrise);
                Panel.add(rise);
                Panel.add(maxtemp);
                Panel.add(max);
                Panel.add(sunset);
                Panel.add(set);
                Panel.add(mintemp);
                Panel.add(min);
                Panel.add(notify);

                Panel.add(five);
                Panel.add(d_one);
                Panel.add(d_two);
                Panel.add(d_three);
                Panel.add(d_four);
                Panel.add(d_five);

                Panel.add(notification);
                Panel.add(weather);
                Panel.add(quality);

                Panel.add(fe);
                Panel.add(sr);
                Panel.add(mt);
                Panel.add(ss);
                Panel.add(mint);

                Panel.add(t_one);
                Panel.add(t_two);
                Panel.add(t_three);
                Panel.add(t_four);
                Panel.add(t_five);

                Panel.add(backbox1);
                Panel.add(backbox2);
                Panel.add(backbox3);
                Panel.add(backbox4);
                Panel.add(backbox5);

                frame1.add(Panel);
                frame1.setLayout(null);
                frame1.setVisible(true); // see frame
            });
        });

        JLayeredPane Panel = new JLayeredPane();
        Panel.setBounds(0, 0, 1080, 800);

        Panel.add(field);
        Panel.add(searchLabel);

        Panel.add(exit);
        Panel.add(more);

        Panel.add(label2);
        Panel.add(location);
        Panel.add(temp);
        Panel.add(lati);
        Panel.add(longi);


        Panel.add(line1);
        Panel.add(line2);
        Panel.add(line3);
        Panel.add(line4);
        Panel.add(line5);
        Panel.add(line6);
        Panel.add(line7);
        Panel.add(line8);
        Panel.add(line9);

        Panel.add(highlights);
        Panel.add(feel);
        Panel.add(feeLabel);
        Panel.add(sunrise);
        Panel.add(rise);
        Panel.add(maxtemp);
        Panel.add(max);
        Panel.add(sunset);
        Panel.add(set);
        Panel.add(mintemp);
        Panel.add(min);
        Panel.add(notify);

        Panel.add(five);
        Panel.add(d_one);
        Panel.add(d_two);
        Panel.add(d_three);
        Panel.add(d_four);
        Panel.add(d_five);

        Panel.add(notification);
        Panel.add(weather);
        Panel.add(quality);

        Panel.add(fe);
        Panel.add(sr);
        Panel.add(mt);
        Panel.add(ss);
        Panel.add(mint);

        Panel.add(t_one);
        Panel.add(t_two);
        Panel.add(t_three);
        Panel.add(t_four);
        Panel.add(t_five);

        Panel.add(backbox1);
        Panel.add(backbox2);
        Panel.add(backbox3);
        Panel.add(backbox4);
        Panel.add(backbox5);

        frame1.add(Panel);
        frame1.setLayout(null);
        frame1.setVisible(true); // see frame
    }

    public void createLoadingFrame()
    {
        loading.setSize(1080, 800); // set frame size
        loading.getContentPane().setBackground(new Color(2, 0, 22)); // set frame color
        loading.setResizable(false); // disable frame resizing
        loading.setLocationRelativeTo(null);
        loading.getContentPane().setLayout(new BorderLayout());

        // Label Search
        JLabel img = new JLabel();
        ImageIcon loadingimg = new ImageIcon("Media\\api.png");
        img.setIcon(loadingimg);

        int loadingWidth = 250;
        int loadingHeight = 250;
        ImageIcon scaledloading = new ImageIcon(loadingimg.getImage().getScaledInstance(loadingWidth, loadingHeight, java.awt.Image.SCALE_SMOOTH));
        img.setIcon(scaledloading);

        // Create a panel to hold the label and center it horizontally
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(2, 0, 22));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0); // Add some space below the image
        panel.add(img, gbc);

        // Add text label under the image
        JLabel textLabel = new JLabel("Loading...");
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.BOLD, 30));
        gbc.gridy = 1; // Set the y position to 1 to place it under the image
        panel.add(textLabel, gbc);

        // Add the panel to the center of the frame
        loading.getContentPane().add(panel, BorderLayout.CENTER);

        loading.setVisible(true); // see frame
    }

    public void disposeLoadingFrame() {
        if (loading != null) {
            loading.dispose();
        }
    }
};