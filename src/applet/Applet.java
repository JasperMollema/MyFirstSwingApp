package applet;

import javax.swing.*;

public class Applet extends JApplet {
    @Override
    public void init() {
        System.out.println("init");
    }

    @Override
    public void start() {
        System.out.println("init");
    }

    @Override
    public void stop() {
        System.out.println("start");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
