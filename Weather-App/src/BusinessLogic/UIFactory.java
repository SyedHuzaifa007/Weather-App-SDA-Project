package BusinessLogic;
import UI.*;
import DataAccess.*;

// Concrete factory implementations
class UIFactory implements AbstractFactory {

    public InterfaceUI createConsole() {
        return new UITerminalBased();
    }

    public InterfaceUI createGUI() {
        return (InterfaceUI) new GUI();
    }
    public InterfaceUI createUI(String choice){
        switch (choice.toLowerCase()) {
            case "console":
                return createConsole();
            case "gui":
                return createGUI();
            default:
                System.out.println("Invalid UI type");
                return null;
        }
    }



}
