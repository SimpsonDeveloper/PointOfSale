package HI;

import javax.swing.JPanel;

import problemDomain.Store;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class EditStore extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public EditStore(Store store, JFrame frame) {
		setLayout(null);
		
		
		textField = new JTextField();
		textField.setBounds(176, 110, 86, 20);
		textField.setText(store.getName());
		add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterStoreName = new JLabel("Name");
		lblEnterStoreName.setBounds(110, 113, 41, 14);
		add(lblEnterStoreName);
		
		JLabel lblEditStore = new JLabel("Edit Store");
		lblEditStore.setBounds(190, 39, 59, 14);
		add(lblEditStore);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				store.setName(textField.getText());
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new MainMenu(store, frame));
				frame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(98, 197, 89, 23);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new MainMenu(store, frame));
				frame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(252, 197, 89, 23);
		add(btnCancel);

	}
}
