package HI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import problemDomain.Payment;
import problemDomain.Sale;
import problemDomain.SaleLineItem;
import problemDomain.Session;
import problemDomain.Store;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JCheckBox;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.Color;

public class SaleEntry extends JPanel {
	JPanel me = this;
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	JList list;
	DefaultListModel<SaleLineItem> listModel;
	JLabel lblUpcNotFound;
	JButton btnCompleteSale;
	JButton btnEndSession;
	
	Sale currentSale = new Sale();

	/**
	 * Create the panel.
	 */
	public SaleEntry(Store store, JFrame frame, Session session) {
		addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
				textField.requestFocusInWindow();
				textField.setText("");
				textField_1.setText("1");
				textField_5.setText(currentSale.calcAmtTendered().toString());
				textField_6.setText(currentSale.calcChange().toString());
				//see if the sale is valid, and set the "complete sale" button accordingly
				if (currentSale.getTotalPayments().compareTo(currentSale.calcTotal()) < 0 || currentSale.getTotalPayments().doubleValue() == 0)
				{
					btnCompleteSale.setEnabled(false);
				}
				else
				{
					btnCompleteSale.setEnabled(true);
				}
				
			}
			public void ancestorMoved(AncestorEvent arg0) {
			}
			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		setLayout(null);
		
		JLabel lblCashier = new JLabel("Cashier: " + session.getCashier().toString());
		lblCashier.setBounds(10, 25, 147, 14);
		add(lblCashier);
		
		JLabel lblRegister = new JLabel("Register: " + session.getRegister().toString());
		lblRegister.setBounds(10, 50, 147, 14);
		add(lblRegister);
		
		JLabel lblSale = new JLabel("Sale");
		lblSale.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSale.setBounds(204, 0, 46, 28);
		add(lblSale);
		
		JLabel lblItem = new JLabel("Item:");
		lblItem.setBounds(28, 87, 35, 14);
		add(lblItem);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//add an SLI to the DefaultListModel and the sale, or update an SLI if it already exists
				boolean exists = false;
				int idx = 0;
				if (store.findItemForUPC(textField.getText()) != null)
				{
					SaleLineItem sli = new SaleLineItem(store.findItemForUPC(textField.getText()), textField_1.getText());
					for (int i = 0; i < listModel.size(); i++)
					{
						if (sli.equals(listModel.getElementAt(i)))
						{
							exists = true;
							idx = i;
						}
					}
					if (exists)
					{
						sli = listModel.getElementAt(idx);
						sli.setQuantity(sli.getQuantity() + Integer.parseInt(textField_1.getText()));
						listModel.setElementAt(sli, idx);
					}
					else
					{
						listModel.addElement(sli);
						currentSale.addSaleLineItem(sli);
					}
					lblUpcNotFound.setVisible(false);
					//set all the calculations
					textField_2.setText(currentSale.calcSubTotal().toString());
					textField_3.setText(currentSale.calcTax().toString());
					textField_4.setText(currentSale.calcTotal().toString());
					//reset all the amounts of the payments and recalculate them
					for (Payment p : currentSale.getPayments()) p.setAmount(new BigDecimal("0"));
					for (Payment p : currentSale.getPayments()) p.setAmount(currentSale.calcAmount(p.getAmtTendered()));
					textField_6.setText(currentSale.calcChange().toString());
					//see if the sale is valid, and set the "complete sale" button accordingly
					if (currentSale.getTotalPayments().compareTo(currentSale.calcTotal()) < 0 || currentSale.getTotalPayments().doubleValue() == 0)
					{
						btnCompleteSale.setEnabled(false);
					}
					else
					{
						btnCompleteSale.setEnabled(true);
					}
				}
				else
				{
					lblUpcNotFound.setVisible(true);
				}
				//clear the field
				textField.setText("");
			}
		});
		textField.setBounds(58, 84, 128, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(196, 87, 54, 14);
		add(lblQuantity);
		
		textField_1 = new JTextField();
		textField_1.setBounds(248, 84, 46, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		listModel = new DefaultListModel<SaleLineItem>();
		
		list = new JList(listModel);
		list.setBounds(10, 111, 255, 108);
		add(list);
		
		JLabel lblSubtotal = new JLabel("SubTotal:");
		lblSubtotal.setBounds(275, 126, 57, 14);
		add(lblSubtotal);
		
		textField_2 = new JTextField();
		textField_2.setBounds(342, 123, 86, 20);
		textField_2.setText(currentSale.calcSubTotal().toString());
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblTax = new JLabel("Tax:");
		lblTax.setBounds(275, 151, 57, 14);
		add(lblTax);
		
		textField_3 = new JTextField();
		textField_3.setBounds(342, 148, 86, 20);
		textField_3.setText(currentSale.calcTax().toString());
		add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(275, 176, 57, 14);
		add(lblTotal);
		
		textField_4 = new JTextField();
		textField_4.setBounds(342, 173, 86, 20);
		textField_4.setText(currentSale.calcTotal().toString());
		add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblAmttendered = new JLabel("AmtTendered:");
		lblAmttendered.setBounds(248, 237, 84, 14);
		add(lblAmttendered);
		
		textField_5 = new JTextField();
		textField_5.setBounds(342, 234, 86, 20);
		textField_5.setText(currentSale.calcAmtTendered().toString());
		add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblChange = new JLabel("Change:");
		lblChange.setBounds(248, 262, 46, 14);
		add(lblChange);
		
		textField_6 = new JTextField();
		textField_6.setBounds(342, 259, 86, 20);
		textField_6.setText(currentSale.calcChange().toString());
		add(textField_6);
		textField_6.setColumns(10);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Tax Free");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentSale.setTaxFree(chckbxNewCheckBox.isSelected());
				textField_3.setText(currentSale.calcTax().toString());
				textField_4.setText(currentSale.calcTotal().toString());
			}
		});
		chckbxNewCheckBox.setBounds(342, 46, 97, 23);
		add(chckbxNewCheckBox);
		
		JButton btnPayment = new JButton("Payment");
		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new PaymentEntry(frame, me, currentSale, session));
				frame.getContentPane().revalidate();
			}
		});
		btnPayment.setBounds(0, 233, 117, 23);
		add(btnPayment);
		
		btnCompleteSale = new JButton("Complete Sale");
		btnCompleteSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//give the customer the change
				session.getRegister().getCashDrawer().subtractCash(currentSale.calcChange());
				//add the sale to the session
				session.addSale(currentSale);
				currentSale = new Sale();
				//clear the current sale and the JList
				currentSale.clearSaleLineItems();
				currentSale.clearPayments();
				listModel.clear();
				//reset the sale calculations and payment text
				textField_2.setText(currentSale.calcSubTotal().toString());
				textField_3.setText(currentSale.calcTax().toString());
				textField_4.setText(currentSale.calcTotal().toString());
				textField_5.setText(currentSale.getTotalPayments().toString());
				textField_6.setText(currentSale.calcChange().toString());
				
				//reset complete sale button
				btnCompleteSale.setEnabled(false);
				
				//enable the complete session button
				btnEndSession.setEnabled(true);
				
			}
		});
		btnCompleteSale.setBounds(121, 233, 117, 23);
		btnCompleteSale.setEnabled(false);
		add(btnCompleteSale);
		
		JButton btnCancelSale = new JButton("Cancel Sale");
		btnCancelSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//clear the current sale and the JList
				currentSale.clearSaleLineItems();
				currentSale.clearPayments();
				listModel.clear();
				//reset the sale calculations and payment text
				textField_2.setText(currentSale.calcSubTotal().toString());
				textField_3.setText(currentSale.calcTax().toString());
				textField_4.setText(currentSale.calcTotal().toString());
				textField_5.setText(currentSale.getTotalPayments().toString());
				textField_6.setText(currentSale.calcChange().toString());
				
				//reset complete sale button
				btnCompleteSale.setEnabled(false);
			}
		});
		btnCancelSale.setBounds(0, 266, 117, 23);
		add(btnCancelSale);
		
		btnEndSession = new JButton("End Session");
		btnEndSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new EndSession(store, frame, session));
				frame.getContentPane().revalidate();
			}
		});
		btnEndSession.setBounds(121, 266, 117, 23);
		btnEndSession.setEnabled(false);
		add(btnEndSession);
		
		lblUpcNotFound = new JLabel("UPC not found");
		lblUpcNotFound.setForeground(Color.RED);
		lblUpcNotFound.setBounds(58, 68, 128, 14);
		lblUpcNotFound.setVisible(false);
		add(lblUpcNotFound);
		
	}
}
