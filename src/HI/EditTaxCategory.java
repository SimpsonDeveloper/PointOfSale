package HI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import problemDomain.Store;
import problemDomain.TaxCategory;
import problemDomain.TaxRate;

import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class EditTaxCategory extends JPanel {
	private JTextField textField;
	
	JButton btnDelete;
	JButton btnEdit;
	DefaultListModel listModel;
	JList<TaxRate> list;
	/**
	 * Create the panel.
	 */
	public EditTaxCategory(Store store, JFrame frame, Boolean isAdd, TaxCategory taxCategory) {
		addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
				//load list
				listModel = new DefaultListModel();
				for (TaxRate r : taxCategory.getTaxRates())
				{
					listModel.addElement(r);
				}
				list.setModel(listModel);
			}
			public void ancestorMoved(AncestorEvent arg0) {
			}
			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		setLayout(null);
		JPanel currentPanel = this;
		
		//store the original tax rates
		ArrayList<TaxRate> originalRates = new ArrayList<TaxRate>();
		for (TaxRate r : taxCategory.getTaxRates())
		{
			TaxRate temp = new TaxRate();
			temp.setRate(r.getRate());
			temp.setDate(r.getDate());
			originalRates.add(temp);
		}
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(123, 61, 86, 20);
		if (!isAdd)
		{
			textField.setText(taxCategory.getCategory());
		}
		add(textField);
		
		JLabel label = new JLabel("Category:");
		label.setBounds(45, 64, 72, 14);
		add(label);
		
		JLabel label_1 = new JLabel("Edit Tax Category");
		label_1.setBounds(178, 11, 100, 14);
		add(label_1);
		
		JButton button = new JButton("Save");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taxCategory.setCategory(textField.getText());
				if (isAdd)
				{
					store.addTaxCategory(taxCategory);
				}
				
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new SelectTaxCategory(store, frame));
				frame.getContentPane().revalidate();
			}
		});
		button.setBounds(45, 236, 89, 23);
		add(button);
		
		JButton button_1 = new JButton("Cancel");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isAdd)
				{
					//restore tax rates
					//clear all current rates
					taxCategory.clearTaxRates();
					//add all original rates
					for (TaxRate r : originalRates)
					{
						taxCategory.addTaxRate(r);
					}
				}
				
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new SelectTaxCategory(store, frame));
				frame.getContentPane().revalidate();
			}
		});
		button_1.setBounds(189, 236, 89, 23);
		add(button_1);
		
		listModel = new DefaultListModel();
		for (TaxRate r : taxCategory.getTaxRates())
		{
			listModel.addElement(r);
		}
		
		list = new JList<TaxRate>(listModel);
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
		list.setBounds(269, 63, 130, 83);
		add(list);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new EditTaxRate(store, frame, currentPanel, false, list.getSelectedValue(), taxCategory));
				frame.getContentPane().revalidate();
			}
		});
		btnEdit.setBounds(207, 170, 71, 20);
		btnEdit.setEnabled(false);
		add(btnEdit);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new EditTaxRate(store, frame, currentPanel, true, new TaxRate(), taxCategory));
				frame.getContentPane().revalidate();
			}
		});
		btnAdd.setBounds(288, 170, 69, 20);
		add(btnAdd);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taxCategory.removeTaxRate(list.getSelectedValue());
				listModel.removeElementAt(list.getSelectedIndex());
			}
		});
		btnDelete.setBounds(367, 170, 73, 20);
		btnDelete.setEnabled(false);
		add(btnDelete);
	}
}
