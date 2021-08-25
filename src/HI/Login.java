package HI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import problemDomain.CashDrawer;
import problemDomain.Cashier;
import problemDomain.Register;
import problemDomain.Session;
import problemDomain.Store;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;

public class Login extends JPanel {
	private JTextField textField;
	private JTextField pwdField;
	private JPasswordField pwdTest;

	/**
	 * Create the panel.
	 */
	public Login(Store store, JFrame frame) {
		setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(198, 11, 58, 14);
		add(lblLogin);
		
		JLabel lblCashierNumber = new JLabel("Cashier Number:");
		lblCashierNumber.setBounds(65, 86, 104, 14);
		add(lblCashierNumber);
		
		JLabel lblRegisterNumber = new JLabel("Register Number:");
		lblRegisterNumber.setBounds(65, 111, 104, 14);
		add(lblRegisterNumber);
		
		JLabel lblStartingCash = new JLabel("Starting Cash:");
		lblStartingCash.setBounds(65, 136, 104, 14);
		add(lblStartingCash);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(65, 161, 104, 14);
		add(lblPassword);
		
		DefaultComboBoxModel comboListModel = new DefaultComboBoxModel();
		for (Cashier c : store.getCashiers())
		{
			comboListModel.addElement(c);
		}
		
		JComboBox comboBox = new JComboBox(comboListModel);
		comboBox.setSelectedItem(comboListModel.getElementAt(0));
		comboBox.setBounds(179, 83, 86, 20);
		add(comboBox);
		
		DefaultComboBoxModel comboListModel_1 = new DefaultComboBoxModel();
		for (Register r : store.getRegisters())
		{
			comboListModel_1.addElement(r);
		}
		
		JComboBox comboBox_1 = new JComboBox(comboListModel_1);
		comboBox_1.setSelectedItem(comboListModel_1.getElementAt(0));
		comboBox_1.setBounds(179, 108, 86, 20);
		add(comboBox_1);
		
		textField = new JTextField();
		textField.setBounds(179, 133, 86, 20);
		textField.setText("0");
		add(textField);
		textField.setColumns(10);
		
		pwdField = new JPasswordField();
		pwdField.setBounds(179, 158, 86, 20);
		add(pwdField);
		pwdField.setColumns(10);
		
		JLabel lblErrorSpecifiedCashier = new JLabel("Error: Specified Cashier Number and Password Fields Do Not Match");
		lblErrorSpecifiedCashier.setForeground(Color.RED);
		lblErrorSpecifiedCashier.setBounds(31, 46, 385, 14);
		lblErrorSpecifiedCashier.setVisible(false);
		add(lblErrorSpecifiedCashier);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//authenticate the login attempt
				if (((Cashier) comboBox.getSelectedItem()).getPassword().equals(pwdField.getText()))
				{
					//make a new session with the selected cashier and register
					CashDrawer tempCashDrawer = new CashDrawer(textField.getText());
					((Register) comboBox_1.getSelectedItem()).setCashDrawer(tempCashDrawer);
					Session session = new Session((Cashier) comboBox.getSelectedItem(), (Register) comboBox_1.getSelectedItem());
					frame.getContentPane().removeAll();
					frame.getContentPane().add(new SaleEntry(store, frame, session));
					frame.getContentPane().revalidate();
				}
				else
				{
					//show an error message
					lblErrorSpecifiedCashier.setVisible(true);
				}
			}
		});
		btnLogin.setBounds(80, 238, 89, 23);
		add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new MainMenu(store, frame));
				frame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(226, 238, 89, 23);
		add(btnCancel);

	}
}
