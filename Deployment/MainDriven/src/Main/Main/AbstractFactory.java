package Main;
import UI.*;
import DataAccess.*;
public interface AbstractFactory {
    InterfaceUI createUI(String choice);

}
