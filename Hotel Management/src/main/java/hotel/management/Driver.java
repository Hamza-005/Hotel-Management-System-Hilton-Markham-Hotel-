package hotel.management;

import javax.swing.SwingUtilities;

public class Driver {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Resources.initializeResources();

                MainMenu frame = new MainMenu();
                frame.setVisible(true);

            }
        });
    }
}