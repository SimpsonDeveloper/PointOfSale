package HI;

import javax.swing.JPanel;
import javax.swing.JTextField;

import problemDomain.Register;
import problemDomain.Store;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditRegister extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public EditRegister(Store store, JFrame frame, Boolean isAdd, Register register) {
		setLayout(null);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(164, 82, 86, 20);
		if (!isAdd)
		{
			textField.setText(register.getNumber());
		}
		add(textField);
		
		JLabel label = new JLabel("Number");
		label.setBounds(86, 85, 68, 14);
		add(label);
		
		JLabel label_1 = new JLabel("Edit Register");
		label_1.setBounds(178, 11, 72, 14);
		add(label_1);
		
		JButton button = new JButton("Save");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register.setNumber(textField.getText());
				if (isAdd)
				{
					store.addRegister(register);
				}
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new SelectRegister(store, frame));
				frame.getContentPane().revalidate();
			}
		});
		button.setBounds(86, 169, 89, 23);
		add(button);
		
		JButton button_1 = new JButton("Cancel");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new SelectRegister(store, frame));
				frame.getContentPane().revalidate();
			}
		});
		button_1.setBounds(240, 169, 89, 23);
		add(button_1);
		
	}

}
