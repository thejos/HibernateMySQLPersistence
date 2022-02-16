package hibernatemysqlpersistency;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

/**
 *
 * @author: Dejan Smiljić; e-mail: dej4n.s@gmail.com
 *
 */
public class EntryPanel extends JPanel {

    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel ageLabel;
    private JLabel addressLabel;
    private JLabel salaryLabel;
    private JTextField idTxtField;
    private JTextField nameTxtField;
    private JTextField ageTxtField;
    private JTextField addressTxtField;
    private JTextField salaryTxtField;
    private Border border;
    protected static ArrayList<JTextField> txtFieldList;

    public EntryPanel() {

        initComponents();

    }

    private void initComponents() {

        this.setPreferredSize(new Dimension(220, 250));
        this.setLayout(null);

        border = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.WHITE, Color.LIGHT_GRAY);
        this.setBorder(border);

        idLabel = new JLabel("staff_id");
        idLabel.setBounds(20, 30, 100, 26);
        this.add(idLabel);
        idTxtField = new JTextField();
        idTxtField.setBounds(80, 30, 120, 26);
        this.add(idTxtField);

        nameLabel = new JLabel("name");
        nameLabel.setBounds(20, 70, 100, 26);
        this.add(nameLabel);
        nameTxtField = new JTextField();
        nameTxtField.setBounds(80, 70, 120, 26);
        this.add(nameTxtField);

        ageLabel = new JLabel("age");
        ageLabel.setBounds(20, 110, 100, 26);
        this.add(ageLabel);
        ageTxtField = new JTextField();
        ageTxtField.setBounds(80, 110, 120, 26);
        this.add(ageTxtField);

        addressLabel = new JLabel("address");
        addressLabel.setBounds(20, 150, 100, 26);
        this.add(addressLabel);
        addressTxtField = new JTextField();
        addressTxtField.setBounds(80, 150, 120, 26);
        this.add(addressTxtField);

        salaryLabel = new JLabel("salary");
        salaryLabel.setBounds(20, 190, 100, 26);
        this.add(salaryLabel);
        salaryTxtField = new JTextField();
        salaryTxtField.setBounds(80, 190, 120, 26);
        this.add(salaryTxtField);

        Component[] components = this.getComponents();
        txtFieldList = new ArrayList<>();
        for (Component component : components) {
            if (component instanceof JTextField) {
                txtFieldList.add((JTextField) component);
            }
        }
    }// initComponents END

}
