package BusinessLogic;
import DataAccess.DBTxtManager;
import DataAccess.DatabaseSQL;
import UI.GUI;
import java.util.ArrayList;


public class Main
{
    public static void main(String[] args) throws Exception
    {
//-----------------------------------GUI Object------------------------------------------//
        GUI G = new GUI();
        G.createLoadingFrame();
//-----------------------------------Location Object-------------------------------------//
        location location = new location();
        location.getCurrentLocation();
//-----------------------------------Cache Manager Object--------------------------------//
        CacheManager manager = new CacheManager(location);
        manager.getData(location.getCity());
//-----------------------------------Variables for Data Passing--------------------------//
        ArrayList<String> data = manager.readCacheFile();
        ArrayList<String> db = manager.readCacheDB();
//-----------------------------------Database Object-------------------------------------//
        DBTxtManager hello = new DBTxtManager();
        hello.writeToDBTxt(db.get(0), db.get(1), db.get(2));
        DatabaseSQL.main(args);
//-----------------------------------GUI called------------------------------------------//
        G.add(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), data.get(5), data.get(6), data.get(7), data.get(8), data.get(9), data.get(10), data.get(11), data.get(12), data.get(13), data.get(14), data.get(15),
                data.get(16), data.get(17), data.get(18), data.get(19), data.get(20), data.get(21), data.get(22), data.get(23), data.get(24), data.get(25));
    }
}