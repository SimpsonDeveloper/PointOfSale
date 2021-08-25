package HI;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import problemDomain.Cash;
import problemDomain.Payment;
import problemDomain.Sale;
import problemDomain.Session;
import problemDomain.Store;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class CashPaymentEntry extends JPanel {

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	/**
	 * Create the panel.
	 */
	public CashPaymentEntry(JFrame frame, JPanel currentPanel, Sale sale, Session session) {
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
		
		JLabel lblEnterCashPayment = new JLabel("Enter Cash Payment");
		lblEnterCashPayment.setBounds(233, 37, 128, 14);
		add(lblEnterCashPayment);
		
		JLabel lblAmountTendered_1 = new JLabel("Amount Tendered:");
		lblAmountTendered_1.setBounds(196, 65, 107, 14);
		add(lblAmountTendered_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(313, 62, 89, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment payment = new Cash(sale.calcAmount(new BigDecimal(textField_2.getText())).toString(), textField_2.getText());
				sale.addPayment(payment);
				session.getRegister().getCashDrawer().addCash(payment.getAmtTendered());
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new PaymentEntry(frame, currentPanel, sale, session));
				frame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(196, 89, 89, 23);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new PaymentEntry(frame, currentPanel, sale, session));
				frame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(295, 89, 89, 23);
		add(btnCancel);
	}
}
