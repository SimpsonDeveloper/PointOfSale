package HI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import problemDomain.Sale;
import problemDomain.Session;
import problemDomain.Store;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.math.BigDecimal;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EndSession extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public EndSession(Store store, JFrame frame, Session session) {
		setLayout(null);
		
		JLabel label = new JLabel("Cashier: " + session.getCashier().toString());
		label.setBounds(10, 51, 147, 14);
		add(label);
		
		JLabel label_1 = new JLabel("Register: " + session.getRegister().toString());
		label_1.setBounds(10, 76, 147, 14);
		add(label_1);
		
		JLabel lblNumberOfSales = new JLabel("Number of Sales:");
		lblNumberOfSales.setBounds(10, 133, 115, 14);
		add(lblNumberOfSales);
		
		textField = new JTextField();
		textField.setBounds(135, 130, 86, 20);
		textField.setText(String.valueOf(session.getSales().size()));
		add(textField);
		textField.setColumns(10);
		
		
		
		textField_1 = new JTextField();
		textField_1.setBounds(135, 155, 86, 20);
		BigDecimal total = new BigDecimal("0");
		for (Sale s : session.getSales())
		{
			total = total.add(s.calcTotal());
		}
		textField_1.setText(total.toString());
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEnterCash = new JLabel("Enter Cash:");
		lblEnterCash.setBounds(10, 183, 115, 14);
		add(lblEnterCash);
		
		textField_2 = new JTextField();
		textField_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_3.setText(session.calcCashCountDiff(new BigDecimal(textField_2.getText())).toString());
			}
		});
		textField_2.setBounds(135, 180, 86, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblCashCountDiff = new JLabel("Cash Count Diff:");
		lblCashCountDiff.setBounds(10, 208, 115, 14);
		add(lblCashCountDiff);
		
		textField_3 = new JTextField();
		textField_3.setBounds(135, 205, 86, 20);
		add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblSessionSummary = new JLabel("Session Summary");
		lblSessionSummary.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSessionSummary.setBounds(174, 11, 115, 20);
		add(lblSessionSummary);
		
		JButton btnEndSession = new JButton("End Session");
		btnEndSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				session.getRegister().getCashDrawer().setCashAmount(textField_2.getText());
				store.addSession(session);
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new MainMenu(store, frame));
				frame.getContentPane().revalidate();
			}
		});
		btnEndSession.setBounds(166, 266, 123, 23);
		add(btnEndSession);
		
		JLabel lblTotalsales = new JLabel("TotalSales:");
		lblTotalsales.setBounds(10, 158, 115, 14);
		add(lblTotalsales);

	}
}
