package HI;

import javax.swing.JPanel;

import problemDomain.Register;
import problemDomain.Store;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JScrollPane;

public class SelectRegister extends JPanel {

	JButton btnDelete;
	JButton btnEdit;
	private JScrollPane scrollPane;
	
	/**
	 * Create the panel.
	 */
	public SelectRegister(Store store, JFrame frame) {
		setLayout(null);
		
		DefaultListModel listModel = new DefaultListModel();
		for (Register r: store.getRegisters())
		{
			listModel.addElement(r);
		}
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(149, 63, 128, 123);
		add(scrollPane);
		JList<Register> list = new JList<Register>(listModel);
		scrollPane.setViewportView(list);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (!list.isSelectionEmpty())
				{
					btnDelete.setEnabled(true);
					btnEdit.setEnabled(true);
				}
				else
				{
					btnDelete.setEnabled(false);
					btnEdit.setEnabled(false);
				}
			}
		});
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new EditRegister(store, frame, true, new Register()));
				frame.getContentPane().revalidate();
			}
		});
		btnAdd.setBounds(170, 197, 89, 23);
		add(btnAdd);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new EditRegister(store, frame, false, list.getSelectedValue()));
				frame.getContentPane().revalidate();
			}
		});
		btnEdit.setBounds(57, 197, 89, 23);
		btnEdit.setEnabled(false);
		add(btnEdit);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeRegister(list.getSelectedValue());
				listModel.removeElementAt(list.getSelectedIndex());
			}
		});
		btnDelete.setBounds(279, 197, 89, 23);
		btnDelete.setEnabled(false);
		add(btnDelete);
		
	}
}
