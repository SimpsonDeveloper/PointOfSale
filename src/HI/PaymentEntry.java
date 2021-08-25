package HI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import problemDomain.Sale;
import problemDomain.Session;
import problemDomain.Store;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PaymentEntry extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public PaymentEntry(JFrame frame, JPanel currentPanel, Sale sale, Session session) {
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
		
	}
}
