package HI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import problemDomain.Item;
import problemDomain.Store;
import problemDomain.UPC;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditUPC extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public EditUPC(Store store, JFrame frame, JPanel currentPanel, Boolean isAdd, UPC upc, Item item) {
		setLayout(null);
		
		JLabel lblEditUpc = new JLabel("Edit UPC");
		lblEditUpc.setBounds(204, 11, 61, 14);
		add(lblEditUpc);
		
		JLabel lblUpcCode = new JLabel("UPC Code:");
		lblUpcCode.setBounds(45, 89, 66, 14);
		add(lblUpcCode);
		
		textField = new JTextField();
		textField.setBounds(121, 86, 155, 20);
		if (!isAdd)
		{
			textField.setText(upc.getUPC());
		}
		add(textField);
		textField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upc.setUPC(textField.getText());
				if (isAdd)
				{
					item.addUPC(upc);
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
		
	}

}
