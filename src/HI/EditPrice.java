package HI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import problemDomain.Item;
import problemDomain.Price;
import problemDomain.PromoPrice;
import problemDomain.Store;
import problemDomain.UPC;
import javax.swing.JCheckBox;

public class EditPrice extends JPanel {
	JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	boolean isPromo;
	
	/**
	 * Create the panel.
	 */
	public EditPrice(Store store, JFrame frame, JPanel currentPanel, Boolean isAdd, Price price, Item item) {
		setLayout(null);
		
		isPromo = false;
		if (price instanceof PromoPrice)
		{
			isPromo = true;
		}
		
		JLabel lblEditPrice = new JLabel("Edit Price");
		lblEditPrice.setBounds(204, 11, 61, 14);
		add(lblEditPrice);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(45, 61, 89, 14);
		add(lblPrice);
		
		textField = new JTextField();
		textField.setBounds(144, 58, 121, 20);
		if (!isAdd)
		{
			textField.setText(price.getPrice().toString());
		}
		add(textField);
		textField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isPromo)
				{
					((PromoPrice) price).setEndDate(textField_2.getText());
				}
				price.setPrice(new BigDecimal(textField.getText()));
				price.setEffectiveDate(textField_1.getText());
				if (isAdd)
				{
					item.addPrice(price);
				}
				frame.getContentPane().removeAll();
				frame.getContentPane().add(currentPanel);
				frame.getContentPane().repaint();
			}
		});
		btnSave.setBounds(70, 188, 89, 23);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(currentPanel);
				frame.getContentPane().repaint();
			}
		});
		btnCancel.setBounds(236, 188, 89, 23);
		add(btnCancel);
		
		JLabel lblEffectiveDate = new JLabel("Effective Date:");
		lblEffectiveDate.setBounds(45, 86, 89, 14);
		add(lblEffectiveDate);
		
		textField_1 = new JTextField();
		textField_1.setBounds(144, 83, 121, 20);
		if (!isAdd)
		{
			textField_1.setText(price.getDateString());
		}
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setBounds(45, 111, 89, 14);
		add(lblEndDate);
		
		textField_2 = new JTextField();
		textField_2.setBounds(144, 108, 121, 20);
		if (!isAdd)
		{
			if (isPromo)
			{
				textField_2.setText(((PromoPrice) price).getEndDateString());
			}
		}
		add(textField_2);
		textField_2.setColumns(10);
		
		JCheckBox chckbxPromoPrice = new JCheckBox("Promo Price");
		chckbxPromoPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (isPromo)
				{
					isPromo = false;
					lblEndDate.setVisible(false);
					textField_2.setVisible(false);
				}
				else
				{
					isPromo = true;
					lblEndDate.setVisible(true);
					textField_2.setVisible(true);
				}
			}
		});
		chckbxPromoPrice.setBounds(144, 28, 97, 23);
		if (isPromo)
		{
			chckbxPromoPrice.setSelected(true);
		}
		else
		{
			lblEndDate.setVisible(false);
			textField_2.setVisible(false);
		}
		if (!isAdd)
		{
			chckbxPromoPrice.setEnabled(false);
		}
		add(chckbxPromoPrice);
	}
}
