package hibernatemysqlpersistency;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author: Dejan Smiljić; e-mail: dej4n.s@gmail.com
 *
 */
public class HibernateMySQLPersistencyApp {

    public static void main(String[] args) {

        NimbusLookAndFeel nimbusLaF = new NimbusLookAndFeel();
        try {
            UIManager.setLookAndFeel(nimbusLaF);
        } catch (UnsupportedLookAndFeelException ulafExc) {
            ulafExc.getStackTrace();
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException multiExc) {
                System.out.println(multiExc);
            }
        }
        try {
            /*Koristenje klasa EventQueue i SwingUTilities ne pravi razliku. SwingUtilitites interno poziva
            EventQueue.invokeAndWait() metod.
            https://stackoverflow.com/questions/8847083/swingutilities-invokelater-vs-eventqueue-invokelater
            Linija koda ispod komentara se moze napisati i ovako:
            EventQueue.invokeAndWait(...)*/
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    MainFrame mainWindow = new MainFrame();
                    mainWindow.setVisible(true);
                }
            });
        } catch (InterruptedException | InvocationTargetException multiExc) {
            System.out.println(multiExc);
        }/*Za razliku izmedju .invokeAndWait() i .invokeLater() metoda pogledaj:
        https://javarevisited.blogspot.com/2011/09/invokeandwait-invokelater-swing-example.html
        https://stackoverflow.com/questions/5499921/invokeandwait-method-in-swingutilities*/

    }// main() END
}
