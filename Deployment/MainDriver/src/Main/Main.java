package Main;

import BusinessLogic.CacheManager;
import DataAccess.DBTxtManager;
import DataAccess.DatabaseSQL;
import UI.GUI;
import UI.UITerminalBased;
import BusinessLogic.location;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception
    {
        location location = new location();
        location.getCurrentLocation();

        CacheManager manager = new CacheManager(location);
        manager.getData(location.getCity());
//      String DataBase=args[0];
//     String UI=args[1];
//        AbstractFactory creation;
 //       if(DataBase.toLowerCase()=="txtfile")
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press 1 to use GUI and 2 to use Terminal-UI");
        int choice = scanner.nextInt();

        if(choice==1)     {
            ArrayList<String> data = manager.readCacheFile();
            ArrayList<String> db = manager.readCacheDB();
            Scanner scanner2 = new Scanner(System.in);
            System.out.println("Press 1 to use GUI and 2 to use Terminal-UI");
            int choice2 = scanner.nextInt();

          //  if (UI.toLowerCase() == "gui")
            if(choice2==1){
                GUI G = new GUI();
                G.createLoadingFrame();
                G.add(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), data.get(5), data.get(6), data.get(7), data.get(8), data.get(9), data.get(10), data.get(11), data.get(12), data.get(13), data.get(14), data.get(15),
                        data.get(16), data.get(17), data.get(18), data.get(19), data.get(20), data.get(21), data.get(22), data.get(23), data.get(24), data.get(25),1);
            }
           // else if (UI.toLowerCase() == "terminal")
            else
            {
                DBTxtManager hello = new DBTxtManager();
                UITerminalBased ui = new UITerminalBased();
                ui.run(args,manager,hello,1);
            }
        }
  //      else if(DataBase.toLowerCase()=="sql")
else if(choice==2)
        {
            ArrayList<String> data = manager.readCacheFile();
            ArrayList<String> db = manager.readCacheDB();

            DBTxtManager hello = new DBTxtManager();
            hello.writeToDBTxt(db.get(0), db.get(1), db.get(2));
            DatabaseSQL.main(args);

            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Press 1 to use GUI and 2 to use Terminal-UI");
            int choice1 = scanner.nextInt();

    //        if (UI.toLowerCase() == "gui")
      if(choice1==1)      {
                GUI G = new GUI();
                G.createLoadingFrame();
                G.add(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), data.get(5), data.get(6), data.get(7), data.get(8), data.get(9), data.get(10), data.get(11), data.get(12), data.get(13), data.get(14), data.get(15),
                        data.get(16), data.get(17), data.get(18), data.get(19), data.get(20), data.get(21), data.get(22), data.get(23), data.get(24), data.get(25),2);
            }
          //  else if (UI.toLowerCase() == "terminal")
            else{
                UITerminalBased ui=new UITerminalBased();
                ui.run(args,manager,hello,2);
            }
        }
    }
}
