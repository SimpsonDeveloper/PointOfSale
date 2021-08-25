package HI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import problemDomain.Item;
import problemDomain.Price;
import problemDomain.PromoPrice;
import problemDomain.Store;
import problemDomain.TaxCategory;
import problemDomain.TaxRate;
import problemDomain.UPC;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListModel;

public class EditItem extends JPanel {
	
	DefaultListModel listModel;
	DefaultListModel listModel_1;
	private JTextField textField;
	private JTextField textField_1;
	JComboBox comboBox;
	JList<UPC> list;
	JList<Price> list_1;
	
	JButton btnEdit;
	JButton btnDelete;
	JButton button;
	JButton button_2;
	/**
	 * Create the panel.
	 */
	public EditItem(Store store, JFrame frame, boolean isAdd, Item item) {
		addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
				listModel = new DefaultListModel();
				for (UPC u : item.getUPCs())
				{
					listModel.addElement(u);
				}
				list.setModel(listModel);
				
				listModel_1 = new DefaultListModel();
				for (Price p : item.getPrices())
				{
					listModel_1.addElement(p);
				}
				list_1.setModel(listModel_1);
			}
			public void ancestorMoved(AncestorEvent arg0) {
			}
			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		setLayout(null);
		JPanel currentPanel = this;
		
		//store the original tax rates
			ArrayList<UPC> originalUPCs = new ArrayList<UPC>();
			for (UPC u : item.getUPCs())
			{
				UPC uTemp = new UPC();
				uTemp.setUPC(u.getUPC());
				originalUPCs.add(uTemp);
			}
		//store the original prices
			ArrayList<Price> originalPrices = new ArrayList<Price>();
			for (Price p : item.getPrices())
			{
				Price pTemp;
				if (p instanceof PromoPrice)
				{
					pTemp = new PromoPrice();
					((PromoPrice) pTemp).setEndDate(((PromoPrice) p).getEndDate());
				}
				else
				{
					pTemp = new Price();
				}
				pTemp.setPrice(p.getPrice());
				pTemp.setEffectiveDate(p.getEffectiveDate());
				originalPrices.add(pTemp);
			}
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//change the item
				item.setNumber(textField.getText());
				item.setDescription(textField_1.getText());
				item.setTaxCategory((TaxCategory)comboBox.getSelectedItem());
				if (isAdd)
				{
					System.out.println("Got here");
					store.addItem(item);
				}
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new SelectItem(store, frame));
				frame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(26, 242, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isAdd)
				{
					//restore UPCs and prices
					//clear all current UPCs
					item.clearUPCs();
					//clear all current prices
					item.clearPrices();
					//add all original UPCs
					for (UPC u : originalUPCs)
					{
						item.addUPC(u);
					}
					//add all original prices
					for (Price p : originalPrices)
					{
						item.addPrice(p);
					}
				}
				
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new SelectItem(store, frame));
				frame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(151, 242, 89, 23);
		add(btnNewButton_1);
		
		JLabel lblEditItem = new JLabel("Edit Item");
		lblEditItem.setBounds(187, 11, 66, 14);
		add(lblEditItem);
		
		JLabel lblNewLabel = new JLabel("Item Number");
		lblNewLabel.setBounds(10, 52, 83, 14);
		add(lblNewLabel);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(10, 77, 83, 14);
		add(lblDescription);
		
		JLabel lblTaxCategory = new JLabel("Tax Category");
		lblTaxCategory.setBounds(10, 102, 83, 14);
		add(lblTaxCategory);
		
		textField = new JTextField();
		textField.setBounds(103, 49, 86, 20);
		if (!isAdd)
		{
			textField.setText(item.getNumber());
		}
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(103, 74, 129, 20);
		if (!isAdd)
		{
			textField_1.setText(item.getDescription());
		}
		add(textField_1);
		textField_1.setColumns(10);
		
		DefaultComboBoxModel comboListModel = new DefaultComboBoxModel();
		for (TaxCategory t : store.getTaxCategories())
		{
			comboListModel.addElement(t);
		}
		
		comboBox = new JComboBox(comboListModel);
		comboBox.setSelectedItem(item.getTaxCategory());
		comboBox.setBounds(103, 99, 86, 20);
		add(comboBox);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new EditUPC(store, frame, currentPanel, false, list.getSelectedValue(), item));
				frame.getContentPane().revalidate();
			}
		});
		btnEdit.setBounds(297, 93, 60, 23);
		btnEdit.setEnabled(false);
		add(btnEdit);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new EditUPC(store, frame, currentPanel, true, new UPC(), item));
				frame.getContentPane().revalidate();
			}
		});
		btnAdd.setBounds(380, 93, 60, 23);
		add(btnAdd);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				item.removeUPC(list.getSelectedValue());
				listModel.removeElementAt(list.getSelectedIndex());
			}
		});
		btnDelete.setBounds(334, 121, 72, 23);
		btnDelete.setEnabled(false);
		add(btnDelete);
		
		listModel = new DefaultListModel();
		for (UPC u : item.getUPCs())
		{
			listModel.addElement(u);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(297, 11, 143, 68);
		add(scrollPane);
		
		list = new JList<UPC>(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (!list.isSelectionEmpty())
				{
					btnEdit.setEnabled(true);
					btnDelete.setEnabled(true);
				}
				else
				{
					btnEdit.setEnabled(false);
					btnDelete.setEnabled(false);
				}
			}
		});
		scrollPane.setViewportView(list);
		
		listModel_1 = new DefaultListModel();
		for (Price p : item.getPrices())
		{
			listModel_1.addElement(p);
		}
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(261, 155, 179, 68);
		add(scrollPane_1);
		
		list_1 = new JList<Price>(listModel_1);
		list_1.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (!list_1.isSelectionEmpty())
				{
					button.setEnabled(true);
					button_2.setEnabled(true);
				}
				else
				{
					button.setEnabled(false);
					button_2.setEnabled(false);
				}
			}
		});
		scrollPane_1.setColumnHeaderView(list_1);
		
		button = new JButton("Edit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new EditPrice(store, frame, currentPanel, false, list_1.getSelectedValue(), item));
				frame.getContentPane().revalidate();
			}
		});
		button.setEnabled(false);
		button.setBounds(271, 234, 60, 23);
		add(button);
		
		JButton button_1 = new JButton("Add");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new EditPrice(store, frame, currentPanel, true, new Price(), item));
				frame.getContentPane().revalidate();
			}
		});
		button_1.setBounds(369, 234, 60, 23);
		add(button_1);
		
		button_2 = new JButton("Delete");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				item.removePrice(list_1.getSelectedValue());
				listModel_1.removeElementAt(list_1.getSelectedIndex());
			}
		});
		button_2.setEnabled(false);
		button_2.setBounds(315, 266, 72, 23);
		add(button_2);
		
	}
}
