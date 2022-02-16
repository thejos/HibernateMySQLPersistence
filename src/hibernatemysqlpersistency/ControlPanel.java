package hibernatemysqlpersistency;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

/**
 *
 * @author: Dejan Smiljić; e-mail: dej4n.s@gmail.com
 *
 */
public class ControlPanel extends JPanel {

    private Border border;
    private JButton buttonSave;
    private JButton buttonUpdate;
    private JButton buttonDelete;
    protected static JComboBox<String> searchComboBox;
    protected static JTextField searchTxtField;
    protected static JButton buttonSearch;
    protected static JButton buttonRetrieveData;

    public ControlPanel() {

        initComponents();
    }

    private void initComponents() {

        this.setPreferredSize(new Dimension(0, 120));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        border = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.WHITE, Color.LIGHT_GRAY);
        this.setBorder(border);

        buttonSave = new JButton("Save Data");
        buttonSave.setActionCommand("save");
        buttonSave.addActionListener(new CustomActionListener());
        this.add(buttonSave);

        buttonUpdate = new JButton("Update Data");
        buttonUpdate.setActionCommand("update");
        buttonUpdate.addActionListener(new CustomActionListener());
        this.add(buttonUpdate);

        buttonDelete = new JButton("Delete Data");
        buttonDelete.setActionCommand("delete");
        buttonDelete.addActionListener(new CustomActionListener());
        this.add(buttonDelete);

        searchComboBox = new JComboBox<>();
        searchComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"name", "age", "address", "salary"}));
        searchComboBox.setSelectedIndex(0);
        searchComboBox.setToolTipText("Pick a database search criteria");
        this.add(searchComboBox);

        searchTxtField = new JTextField(10);
        this.add(searchTxtField);
        searchTxtField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                if (!buttonSearch.isEnabled()) {
                    buttonSearch.setEnabled(true);
                }

                if (searchTxtField.getText().isEmpty() || searchTxtField.getText().trim().equals("")) {
                    buttonSearch.setEnabled(false);
                }
            }
        });

        buttonSearch = new JButton("Search Database");
        buttonSearch.setActionCommand("search");
        buttonSearch.addActionListener(new CustomActionListener());
        this.add(buttonSearch);
        buttonSearch.setEnabled(false);

        buttonRetrieveData = new JButton("Retrieve Data");
        buttonRetrieveData.setActionCommand("retrieve");
        buttonRetrieveData.addActionListener(new CustomActionListener());
        this.add(buttonRetrieveData);
        buttonRetrieveData.setVisible(false);

    }// initComponents() END

}
