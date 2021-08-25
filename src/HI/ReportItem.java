package HI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.github.lgooddatepicker.components.DatePicker;

import problemDomain.Item;
import problemDomain.Sale;
import problemDomain.SaleLineItem;
import problemDomain.Session;
import problemDomain.Store;
import javax.swing.JScrollPane;

public class ReportItem extends JPanel {
	private LocalDate reportDate;
	DatePicker datePicker;
	/**
	 * Create the panel.
	 */
	public ReportItem(Store store, JFrame frame)
	{
		setLayout(null);
		
		JLabel lblCashierReport = new JLabel("Item Report");
		lblCashierReport.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCashierReport.setForeground(new Color(0, 0, 0));
		lblCashierReport.setBounds(168, 11, 96, 20);
		add(lblCashierReport);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(49, 70, 46, 14);
		add(lblDate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 113, 413, 137);
		add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		scrollPane.setViewportView(textArea);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int count;
				int totalCount = 0;
				
				boolean itemUsed;
				reportDate = datePicker.getDate();
				String output = "Item Report For: " + reportDate.toString() + "\n" + "\n";
				for (Item item : store.getItems())
				{
					count = 0;
					itemUsed = false;
					for (Session session : store.getSessions())
					{
						if (session.getStartDate().equals(reportDate))
						{
							for (Sale sale : session.getSales())
							{
								for (SaleLineItem sli : sale.getSaleLineItems())
								{
									if (item.getNumber().equals(sli.getItem().getNumber()))
									{
										itemUsed = true;
										count += sli.getQuantity();
										totalCount += sli.getQuantity();
									}
								}
							}
						}
					}
					if (itemUsed)
					{
						output += leftJustify(item.getNumber() + " " + item.getDescription(), 30) + count + "\n";
					}
				}
				output += "\n";
				output += leftJustify("Total:", 30) + totalCount;
				textArea.setText(output);
			}
		});
		btnGenerate.setBounds(116, 261, 89, 23);
		add(btnGenerate);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new MainMenu(store, frame));
				frame.getContentPane().revalidate();
			}
		});
		btnClose.setBounds(241, 261, 89, 23);
		add(btnClose);
		
		datePicker = new DatePicker();
		datePicker.setBounds(113, 68, 160, 30);
		datePicker.setDate(LocalDate.now());
		add(datePicker);
	}
	
	public String leftJustify(String text, int length)
	{
		return String.format("%-" + length + "s", text);
	}

}
