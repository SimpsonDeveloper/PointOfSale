package HI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import problemDomain.Store;
import problemDomain.TaxCategory;
import problemDomain.TaxRate;

public class EditTaxRate extends JPanel {
	JTextField textField;
	private JTextField textField_1;
	/**
	 * Create the panel.
	 */
	public EditTaxRate(Store store, JFrame frame, JPanel currentPanel, Boolean isAdd, TaxRate taxRate, TaxCategory taxCategory) {
		setLayout(null);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(150, 82, 86, 20);
		if (!isAdd)
		{
			textField.setText(taxRate.getRate().toString());
		}
		add(textField);
		
		JLabel label = new JLabel("Rate:");
		label.setBounds(86, 85, 54, 14);
		add(label);
		
		JLabel label_1 = new JLabel("Edit Tax Rate");
		label_1.setBounds(178, 11, 96, 14);
		add(label_1);
		
		JButton button = new JButton("Save");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taxRate.setRate(textField.getText());
				taxRate.setDate(textField_1.getText());
				if (isAdd)
				{
					taxCategory.addTaxRate(taxRate);
				}
				frame.getContentPane().removeAll();
				frame.getContentPane().add(currentPanel);
				frame.getContentPane().repaint();
			}
		});
		button.setBounds(86, 233, 89, 23);
		add(button);
		
		JButton button_1 = new JButton("Cancel");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(currentPanel);
				frame.getContentPane().repaint();
			}
		});
		button_1.setBounds(235, 233, 89, 23);
		add(button_1);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(86, 115, 46, 14);
		add(lblDate);
		
		textField_1 = new JTextField();
		textField_1.setBounds(150, 112, 86, 20);
		if (!isAdd)
		{
			textField_1.setText(taxRate.getDateString());
		}
		add(textField_1);
		textField_1.setColumns(10);
	}
}
