package View;

import Controller.Controller;

import com.company.MyException;

import java.io.IOException;
import java.util.ArrayList;

public class RunExample extends Command {
    private Controller ctrl;

    public RunExample(String key, String desc, Controller ctr) {
        super(key, desc);

        this.ctrl = ctr;
    }

    @Override
    public void execute() {
        try {
            ctrl.allStep();
        } catch (MyException | IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
