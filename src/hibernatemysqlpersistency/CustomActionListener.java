package hibernatemysqlpersistency;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author: Dejan Smiljić; e-mail: dej4n.s@gmail.com
 *
 */
public class CustomActionListener implements ActionListener {

    private DataAccess dao;

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case "save":
                dao = new DataAccess();
                dao.saveData();
                break;
            case "update":
                dao = new DataAccess();
                dao.updateData();
                break;
            case "delete":
                dao = new DataAccess();
                dao.deleteData();
                break;
            case "search":
                dao = new DataAccess();
                dao.searchData();
                break;
            case "retrieve":
                dao = new DataAccess();
                dao.refreshDataDisplay();
                dao.searchControlsReset();
                break;
            default:
                System.err.println("ERROR Unknown - Imaginary button click");
                break;
        }//switch{} END

    }//actionPerformed() END

}
