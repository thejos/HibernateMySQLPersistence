package hibernatemysqlpersistency;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author: Dejan Smiljić; e-mail: dej4n.s@gmail.com
 *
 */
public class DataAccess {

    private Session session;
    private Transaction transaction;
    private Query query;
    private Staff employee;
    private Vector<Object> vectorTableData;
    private Vector<Object> vectorColumnNames;
    private final String HQL_SELECT_ALL = "FROM Staff";

    protected DefaultTableModel retrieveData(String hql) {

        DefaultTableModel tableModel;

        try {

            session = HibernateUtil.createSessionFactory().openSession();
            System.out.println("session opened: " + session.isOpen());

            transaction = session.beginTransaction();
            query = session.createQuery(hql);
            query.setFirstResult(0);
            query.setMaxResults(100);
            transaction.commit();

            List resultList = query.list();
            vectorColumnNames = new Vector<>();
            vectorColumnNames.addElement("staff_Id");
            vectorColumnNames.addElement("name");
            vectorColumnNames.addElement("age");
            vectorColumnNames.addElement("address");
            vectorColumnNames.addElement("salary");

            vectorTableData = new Vector<>(resultList.size());
            for (Object object : resultList) {
                employee = (Staff) object;
                Vector<Object> vectorRow = new Vector<>();
                vectorRow.addElement(employee.getStaffId());
                vectorRow.addElement(employee.getName());
                vectorRow.addElement(employee.getAge());
                vectorRow.addElement(employee.getAddress());
                vectorRow.addElement(employee.getSalary());
                vectorTableData.add(vectorRow);
            }

        } catch (HibernateException hExc) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, hExc.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        } finally {
            try {
                session.close();
                System.out.println("session closed: " + !session.isOpen());
            } catch (HibernateException hExc) {
                System.out.println("Cannot close session...\n" + hExc);
            }
        }// finally END

        tableModel = new DefaultTableModel(vectorTableData, vectorColumnNames) {

            @Override
            public Class<?> getColumnClass(int columnIndex) {

                for (int i = 0; i < getRowCount(); i++) {
                    Object object = getValueAt(i, columnIndex);

                    if (object != null) {
                        return object.getClass();
                    }
                }
                return Object.class;
            }
        };

        return tableModel;
    }//retrieveData() END

    protected void saveData() {

        String inputName;
        Integer ageInteger;
        String inputAddress;
        Double salaryDouble;
        BigDecimal salaryDecimal;

        try {
            inputName = EntryPanel.txtFieldList.get(1).getText().trim();
            if (inputName.equals("")) {
                throw new NumberFormatException("For input string: \"" + EntryPanel.txtFieldList.get(1).getText() + "\"");
            }

            ageInteger = Integer.parseInt(EntryPanel.txtFieldList.get(2).getText());

            inputAddress = EntryPanel.txtFieldList.get(3).getText().trim();
            if (inputAddress.equals("")) {
                throw new NumberFormatException("For input string: \"" + EntryPanel.txtFieldList.get(3).getText() + "\"");
            }

            salaryDouble = Double.parseDouble(EntryPanel.txtFieldList.get(4).getText());
            salaryDecimal = BigDecimal.valueOf(salaryDouble);
        } catch (NumberFormatException nfExc) {
            JOptionPane.showMessageDialog(null, nfExc.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            //Exits this method, prevents execution of a Hibernate try-catch and
            //prevents invocation of refreshDataDisplay() and textFieldsReset() methods
            return;
        }

        try {
            session = HibernateUtil.createSessionFactory().openSession();
            System.out.println("session opened: " + session.isOpen());

            transaction = session.beginTransaction();

            employee = new Staff();
            employee.setName(inputName);
            employee.setAge(ageInteger);
            employee.setAddress(inputAddress);
            employee.setSalary(salaryDecimal);

            session.persist(employee);

            transaction.commit();
        } catch (HibernateException hExc) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, hExc.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            //Exits this method, prevents invocation of refreshDataDisplay() and textFieldsReset() methods
            //finally block will be executed
            return;
        } finally {
            try {
                session.close();
                System.out.println("session closed: " + !session.isOpen());
            } catch (HibernateException hExc) {
                System.out.println("Cannot close session...\n" + hExc);
            }
        }// finally END

        refreshDataDisplay();
        textFieldsReset();

    }//saveData() END

    protected void updateData() {

        Integer idNumber;
        String inputName;
        Integer ageInteger;
        String inputAddress;
        Double salaryDouble;
        BigDecimal salaryDecimal;

        try {
            idNumber = Integer.parseInt(EntryPanel.txtFieldList.get(0).getText());

            inputName = EntryPanel.txtFieldList.get(1).getText().trim();
            if (inputName.equals("")) {
                throw new NumberFormatException("For input string: \"" + EntryPanel.txtFieldList.get(1).getText() + "\"");
            }

            ageInteger = Integer.parseInt(EntryPanel.txtFieldList.get(2).getText());

            inputAddress = EntryPanel.txtFieldList.get(3).getText().trim();
            if (inputAddress.equals("")) {
                throw new NumberFormatException("For input string: \"" + EntryPanel.txtFieldList.get(3).getText() + "\"");
            }

            salaryDouble = Double.parseDouble(EntryPanel.txtFieldList.get(4).getText());
            salaryDecimal = BigDecimal.valueOf(salaryDouble);
        } catch (NumberFormatException nfExc) {
            JOptionPane.showMessageDialog(null, nfExc.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            session = HibernateUtil.createSessionFactory().openSession();
            System.out.println("session opened: " + session.isOpen());

            transaction = session.beginTransaction();

            employee = (Staff) session.load(Staff.class, idNumber);
            employee.setName(inputName);
            employee.setAge(ageInteger);
            employee.setAddress(inputAddress);
            employee.setSalary(salaryDecimal);

            session.update(employee);//Invocation of the update() method is not necessary

            transaction.commit();
        } catch (HibernateException hExc) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, hExc.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            return;
        } finally {
            try {
                session.close();
                System.out.println("session closed: " + !session.isOpen());
            } catch (HibernateException hExc) {
                System.out.println("Cannot close session...\n" + hExc);
            }
        }// finally END

        refreshDataDisplay();
        textFieldsReset();

    }//updateData() END

    protected void deleteData() {

        Integer idNumber;

        try {
            idNumber = Integer.parseInt(EntryPanel.txtFieldList.get(0).getText());
        } catch (NumberFormatException nfExc) {
            JOptionPane.showMessageDialog(null, nfExc.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            session = HibernateUtil.createSessionFactory().openSession();
            System.out.println("session opened: " + session.isOpen());

            employee = (Staff) session.load(Staff.class, idNumber);

            //whether data should be deleted
            if (cancelDataDeletion(employee)) {
                employee = null;
                return;// Exits this method; finally block will be executed
            }

            transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        } catch (HibernateException hExc) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, hExc.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            return;
        } finally {
            try {
                session.close();
                System.out.println("session closed: " + !session.isOpen());
            } catch (HibernateException hExc) {
                System.out.println("Cannot close session...\n" + hExc);
            }
        }// finally END

        refreshDataDisplay();
        textFieldsReset();

    }// deleteData() END

    private boolean cancelDataDeletion(Staff person) {

        int chosenOption = JOptionPane.showConfirmDialog(null, "Delete\n" + person + "\n\nData will be lost forever!", "Data Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
        /*ConfirmDialog returns:
            value 0 when button YES is clicked;
            value 1 when button NO is clicked, same when ConfirmDialog is closed via the GUI X close button;
        condition: chosenOption != 0; will be TRUE when ConfirmDialog return value is !=0*/
        return chosenOption != 0;

    }//cancelDataDeletion() END

    protected void searchData() {

        String hqlSelectByCriteria = null;

        //Getting input values
        String searchCriteria = (String) ControlPanel.searchComboBox.getSelectedItem();
        String searchParameter = ControlPanel.searchTxtField.getText().trim() + "%";

        switch (searchCriteria) {

            case "name":
                hqlSelectByCriteria = "FROM Staff as staff WHERE staff.name LIKE '" + searchParameter + "'";
                break;
            case "age":
                hqlSelectByCriteria = "FROM Staff as staff WHERE staff.age LIKE '" + searchParameter + "'";
                break;
            case "address":
                hqlSelectByCriteria = "FROM Staff as staff WHERE staff.address LIKE '" + searchParameter + "'";
                break;
            case "salary":
                hqlSelectByCriteria = "FROM Staff as staff WHERE staff.salary LIKE '" + searchParameter + "'";
                break;
            default:
                System.out.println("ERROR Unknown");
                break;

        }// switch END

        //Getting DefaultTableModel
        DefaultTableModel dataModelSearch = retrieveData(hqlSelectByCriteria);
        //Setting DefaultTableModel for jtable
        TablePanel.table.setModel(dataModelSearch);
        ControlPanel.buttonRetrieveData.setVisible(true);

    }//searchData() END

    //Sets up a TableModel so data is shown immediately upon save, update, delete actions
    protected void refreshDataDisplay() {

        /*//Identifies a caller method of this method
        System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
         */
        DefaultTableModel freshDataModel = retrieveData(HQL_SELECT_ALL);
        TablePanel.table.setModel(freshDataModel);

    }// refreshDataDisplay() END

    protected void searchControlsReset() {

        ControlPanel.searchTxtField.setText("");
        ControlPanel.buttonSearch.setEnabled(false);
        ControlPanel.buttonRetrieveData.setVisible(false);
    }//searchControlsReset() END

    private void textFieldsReset() {

        for (int i = 0; i < EntryPanel.txtFieldList.size(); i++) {
            EntryPanel.txtFieldList.get(i).setText("");
        }

    }//txtFieldsReset() END

}
