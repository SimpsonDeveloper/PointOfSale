package HI;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import problemDomain.Credit;
import problemDomain.Payment;
import problemDomain.Sale;
import problemDomain.Session;
import problemDomain.Store;
import problemDomain.TaxCategory;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class CreditPaymentEntry extends JPanel {

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	/**
	 * Create the panel.
	 */
	public CreditPaymentEntry(JFrame frame, JPanel currentPanel, Sale sale, Session session) {
		setLayout(null);
		
		JLabel lblPayment = new JLabel("Payment");
		lblPayment.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPayment.setBounds(181, 0, 98, 19);
		add(lblPayment);
		
		JLabel lblPaymentDue = new JLabel("Payment Due:");
		lblPaymentDue.setBounds(10, 37, 81, 14);
		add(lblPaymentDue);
		
		textField = new JTextField();
		textField.setBounds(10, 62, 98, 20);
		textField.setText(sale.calcTotal().toString());
		add(textField);
		textField.setColumns(10);
		
		JLabel lblAmountTendered = new JLabel("Amount Tendered:");
		lblAmountTendered.setBounds(10, 93, 109, 14);
		add(lblAmountTendered);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 118, 98, 20);
		textField_1.setText(sale.calcAmtTendered().toString());
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnCash = new JButton("Cash");
		btnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new CashPaymentEntry(frame, currentPanel, sale, session));
				frame.getContentPane().revalidate();
			}
		});
		btnCash.setBounds(10, 169, 89, 23);
		add(btnCash);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new CheckPaymentEntry(frame, currentPanel, sale, session));
				frame.getContentPane().revalidate();
			}
		});
		btnCheck.setBounds(10, 203, 89, 23);
		add(btnCheck);
		
		JButton btnCredit = new JButton("Credit");
		btnCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new CreditPaymentEntry(frame, currentPanel, sale, session));
				frame.getContentPane().revalidate();
			}
		});
		btnCredit.setBounds(10, 237, 89, 23);
		add(btnCredit);
		
		JButton btnPaymentComplete = new JButton("Payment Complete");
		btnPaymentComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(currentPanel);
				frame.getContentPane().repaint();
			}
		});
		if (sale.getTotalPayments().compareTo(sale.calcTotal()) < 0)
		{
			btnPaymentComplete.setEnabled(false);
		}
		else
		{
			btnPaymentComplete.setEnabled(true);
		}
		btnPaymentComplete.setBounds(149, 266, 151, 23);
		add(btnPaymentComplete);
		
		JLabel lblCredit = new JLabel("Enter Credit Payment");
		lblCredit.setBounds(233, 37, 151, 14);
		add(lblCredit);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(181, 65, 68, 14);
		add(lblAmount);
		
		textField_2 = new JTextField();
		textField_2.setBounds(277, 62, 86, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblCardType = new JLabel("Card Type:");
		lblCardType.setBounds(181, 93, 68, 14);
		add(lblCardType);
		
		DefaultComboBoxModel comboListModel = new DefaultComboBoxModel();
		comboListModel.addElement("Visa");
		comboListModel.addElement("Mastercard");
		
		JComboBox comboBox = new JComboBox(comboListModel);
		comboBox.setBounds(277, 90, 86, 20);
		add(comboBox);
		
		JLabel lblAmount_1 = new JLabel("Account Num:");
		lblAmount_1.setBounds(181, 121, 80, 14);
		add(lblAmount_1);
		
		textField_3 = new JTextField();
		textField_3.setBounds(277, 118, 86, 20);
		add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblExpireDate = new JLabel("Expire Date:");
		lblExpireDate.setBounds(181, 146, 80, 14);
		add(lblExpireDate);
		
		textField_4 = new JTextField();
		textField_4.setBounds(277, 143, 86, 20);
		add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment payment = new Credit((String)comboBox.getSelectedItem(), textField_3.getText(), textField_4.getText());
				payment.setAmtTendered(new BigDecimal(textField_2.getText()));
				payment.setAmount(sale.calcAmount(new BigDecimal(textField_2.getText())));
				
				sale.addPayment(payment);
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new PaymentEntry(frame, currentPanel, sale, session));
				frame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(181, 189, 89, 23);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new PaymentEntry(frame, currentPanel, sale, session));
				frame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(295, 189, 89, 23);
		add(btnCancel);
	}

}
