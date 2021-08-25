package HI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import problemDomain.Cashier;
import problemDomain.Person;
import problemDomain.Store;

public class EditCashier extends JPanel {
	private JTextField textField_8;
	private JTextField textField_7;
	private JTextField textField_4;
	private JTextField textField_3;
	private JTextField textField_2;
	private JTextField textField_1;
	private JTextField textField_9;
	private JTextField textField_5;
	private JTextField textField_6;
	/**
	 * Create the panel.
	 */
	public EditCashier(Store store, JFrame frame, Boolean isAdd, Cashier cashier) {
		setLayout(null);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(88, 176, 86, 20);
		if (!isAdd)
		{
			textField_8.setText(cashier.getPassword());
		}
		add(textField_8);
		
		JLabel label = new JLabel("Number:");
		label.setBounds(10, 51, 68, 14);
		add(label);
		
		JLabel label_1 = new JLabel("Edit Cashier");
		label_1.setBounds(178, 11, 72, 14);
		add(label_1);
		
		JButton button = new JButton("Save");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cashier.setNumber(textField_1.getText());
				cashier.getPerson().setName(textField_2.getText());
				cashier.getPerson().setAddress(textField_3.getText());
				cashier.getPerson().setCity(textField_4.getText());
				cashier.getPerson().setState(textField_5.getText());
				cashier.getPerson().setZip(textField_6.getText());
				cashier.getPerson().setPhone(textField_7.getText());
				cashier.setPassword(textField_8.getText());
				cashier.getPerson().setSSN(textField_9.getText());
				if (isAdd)
				{
					store.addCashier(cashier);
				}
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new SelectCashier(store, frame));
				frame.getContentPane().revalidate();
			}
		});
		button.setBounds(51, 247, 89, 23);
		add(button);
		
		JButton button_1 = new JButton("Cancel");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new SelectCashier(store, frame));
				frame.getContentPane().revalidate();
			}
		});
		button_1.setBounds(213, 247, 89, 23);
		add(button_1);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(10, 76, 46, 14);
		add(lblNewLabel);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(10, 104, 68, 14);
		add(lblAddress);
		
		JLabel lblNewLabel_1 = new JLabel("City:");
		lblNewLabel_1.setBounds(10, 129, 46, 14);
		add(lblNewLabel_1);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(10, 154, 46, 14);
		add(lblPhone);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 179, 68, 14);
		add(lblPassword);
		
		textField_7 = new JTextField();
		textField_7.setBounds(88, 151, 129, 20);
		if (!isAdd)
		{
			textField_7.setText(cashier.getPerson().getPhone());
		}
		add(textField_7);
		textField_7.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(88, 126, 129, 20);
		if (!isAdd)
		{
			textField_4.setText(cashier.getPerson().getCity());
		}
		add(textField_4);
		textField_4.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(88, 101, 129, 20);
		if (!isAdd)
		{
			textField_3.setText(cashier.getPerson().getAddress());
		}
		add(textField_3);
		textField_3.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(88, 73, 129, 20);
		if (!isAdd)
		{
			textField_2.setText(cashier.getName());
		}
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(88, 48, 86, 20);
		if (!isAdd)
		{
			textField_1.setText(cashier.getNumber());
		}
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblSsn = new JLabel("SSN:");
		lblSsn.setBounds(256, 51, 46, 14);
		add(lblSsn);
		
		textField_9 = new JTextField();
		textField_9.setBounds(294, 48, 117, 20);
		if (!isAdd)
		{
			textField_9.setText(cashier.getPerson().getSSN());
		}
		add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblState = new JLabel("State:");
		lblState.setBounds(238, 129, 46, 14);
		add(lblState);
		
		textField_5 = new JTextField();
		textField_5.setBounds(282, 126, 46, 20);
		if (!isAdd)
		{
			textField_5.setText(cashier.getPerson().getState());
		}
		add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblZip = new JLabel("Zip:");
		lblZip.setBounds(350, 129, 46, 14);
		add(lblZip);
		
		textField_6 = new JTextField();
		textField_6.setBounds(383, 126, 46, 20);
		if (!isAdd)
		{
			textField_6.setText(cashier.getPerson().getZip());
		}
		add(textField_6);
		textField_6.setColumns(10);
	}
}
