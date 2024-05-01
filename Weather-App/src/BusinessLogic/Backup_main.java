package BusinessLogic;
import DataAccess.CacheManager;
import DataAccess.DBTxtManager;
import DataAccess.DatabaseSQL;
import UI.GUI;
import UI.UITerminalBased;
import java.util.Scanner;
import java.util.ArrayList;


public class Backup_main {

        public static void main(String[] args) throws Exception
        {
            location location = new location();
            location.getCurrentLocation();

            CacheManager manager = new CacheManager(location);
            manager.getData(location.getCity());

            Scanner scan = new Scanner(System.in);
            System.out.println("Press 1 to use TxtFiles and 2 to use DataBase");
            int input = scan.nextInt();

            if(input==1)
            {
                ArrayList<String> data = manager.readCacheFile();
                ArrayList<String> db = manager.readCacheDB();

                Scanner scanner = new Scanner(System.in);
                System.out.println("Press 1 to use GUI and 2 to use Terminal-UI");
                int choice = scanner.nextInt();

                if (choice == 1)
                {
                    GUI G = new GUI();
                    G.createLoadingFrame();
                    G.add(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), data.get(5), data.get(6), data.get(7), data.get(8), data.get(9), data.get(10), data.get(11), data.get(12), data.get(13), data.get(14), data.get(15),
                            data.get(16), data.get(17), data.get(18), data.get(19), data.get(20), data.get(21), data.get(22), data.get(23), data.get(24), data.get(25),input);
                }
                else if (choice==2)
                {
                    DBTxtManager hello = new DBTxtManager();
                    UITerminalBased ui = new UITerminalBased();
                    ui.run(args,manager,hello,input);
                }
            }
            else if (input == 2)
            {
                ArrayList<String> data = manager.readCacheFile();
                ArrayList<String> db = manager.readCacheDB();

                DBTxtManager hello = new DBTxtManager();
                hello.writeToDBTxt(db.get(0), db.get(1), db.get(2));
                DatabaseSQL.main(args);

                Scanner scanner = new Scanner(System.in);
                System.out.println("Press 1 to use GUI and 2 to use Terminal-UI");
                int choice = scanner.nextInt();

                if (choice == 1)
                {
                    GUI G = new GUI();
                    G.createLoadingFrame();
                    G.add(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), data.get(5), data.get(6), data.get(7), data.get(8), data.get(9), data.get(10), data.get(11), data.get(12), data.get(13), data.get(14), data.get(15),
                            data.get(16), data.get(17), data.get(18), data.get(19), data.get(20), data.get(21), data.get(22), data.get(23), data.get(24), data.get(25),input);
                }
                else if (choice==2)
                {
                    UITerminalBased ui=new UITerminalBased();
                    ui.run(args,manager,hello,input);
                }
            }
        }
    }

