package hibernatemysqlpersistency;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author: Dejan Smiljić; e-mail: dej4n.s@gmail.com
 *
 */
public class MainFrame extends JFrame {

    public MainFrame() {

        initComponents();
    }

    private void initComponents() {

        this.setTitle("JPA Hibernate ORM MySQL Demo");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TablePanel tablePanel = new TablePanel();
        this.add(tablePanel, BorderLayout.CENTER);

        EntryPanel entryPanel = new EntryPanel();
        this.add(entryPanel, BorderLayout.WEST);

        ControlPanel controlPanel = new ControlPanel();
        this.add(controlPanel, BorderLayout.SOUTH);

        this.pack();//Da bi se prozor pravilno centrirao metod pack() treba pozvati prije setLocationRelativeTo() metode 
        this.setLocationRelativeTo(null);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                HibernateUtil.close();

            }
        }
        );//addWindowListener() END

    }// initComponents() END

}
