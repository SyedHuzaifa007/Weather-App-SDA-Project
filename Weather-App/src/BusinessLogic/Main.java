package BusinessLogic;

import DataAccess.CacheManager;
import DataAccess.DBTxtManager;
import DataAccess.DatabaseSQL;
import UI.GUI;
import UI.UITerminalBased;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: java Main <data_source>");
            System.exit(1);
        }

        String dataSource = args[0];

        location location = new location();
        location.getCurrentLocation();

        CacheManager manager = new CacheManager(location);
        manager.getData(location.getCity());

        ArrayList<String> data = manager.readCacheFile();
        ArrayList<String> db = manager.readCacheDB();

        if (dataSource.equals("txtfiles")) {
            if (args.length != 2) {
                System.out.println("Usage: java Main TxtFiles <UI_type>");
                System.exit(1);
            }
            String uiType = args[1];

            if (uiType.equals("gui")) {
                GUI G = new GUI();
                G.createLoadingFrame();
                G.add(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), data.get(5), data.get(6), data.get(7), data.get(8), data.get(9), data.get(10), data.get(11), data.get(12), data.get(13), data.get(14), data.get(15),
                        data.get(16), data.get(17), data.get(18), data.get(19), data.get(20), data.get(21), data.get(22), data.get(23), data.get(24), data.get(25), 1);
            } else if (uiType.equals("terminal")) {
                DBTxtManager hello = new DBTxtManager();
                UITerminalBased ui = new UITerminalBased();
                ui.run(args, manager, hello, 1);
            } else {
                System.out.println("Invalid UI type.");
                System.exit(1);
            }
        } else if (dataSource.equals("sql")) {
            DBTxtManager hello = new DBTxtManager();
            hello.writeToDBTxt(db.get(0), db.get(1), db.get(2));
            DatabaseSQL.main(args);

            if (args.length != 2) {
                System.out.println("Usage: java Main DataBase <UI_type>");
                System.exit(1);
            }
            String uiType = args[1];

            if (uiType.equals("gui")) {
                GUI G = new GUI();
                G.createLoadingFrame();
                G.add(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), data.get(5), data.get(6), data.get(7), data.get(8), data.get(9), data.get(10), data.get(11), data.get(12), data.get(13), data.get(14), data.get(15),
                        data.get(16), data.get(17), data.get(18), data.get(19), data.get(20), data.get(21), data.get(22), data.get(23), data.get(24), data.get(25), 1);
            } else if (uiType.equals("terminal")) {
                UITerminalBased ui = new UITerminalBased();
                ui.run(args, manager, hello, 1);
            } else {
                System.out.println("Invalid UI type.");
                System.exit(1);
            }
        } else {
            System.out.println("Invalid data source.");
            System.exit(1);
        }
    }
}
