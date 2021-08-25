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

import problemDomain.Cash;
import problemDomain.Check;
import problemDomain.Credit;
import problemDomain.Item;
import problemDomain.Payment;
import problemDomain.Sale;
import problemDomain.SaleLineItem;
import problemDomain.Session;
import problemDomain.Store;
import javax.swing.JScrollPane;

public class ReportSale extends JPanel {
	private LocalDate reportDate;
	DatePicker datePicker;
	/**
	 * Create the panel.
	 */
	public ReportSale(Store store, JFrame frame) {
		setLayout(null);
		
		JLabel lblCashierReport = new JLabel("Sales Report");
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
				int numItems;
				BigDecimal cash;
				BigDecimal check;
				BigDecimal credit;
				
				BigDecimal totalSales = new BigDecimal("0");
				int totalNumItems = 0;
				BigDecimal totalCash = new BigDecimal("0");
				BigDecimal totalCheck = new BigDecimal("0");
				BigDecimal totalCredit = new BigDecimal("0");
				
				reportDate = datePicker.getDate();
				String output = "Sales Report For: " + reportDate.toString() + "\n" + "\n";
				output += leftJustify("Time", 15) + leftJustify("Total Sales", 15) + leftJustify("Items Sold", 15) + leftJustify("Cash", 10) + leftJustify("Check", 10) + "Credit\n";
				for (Session session : store.getSessions())
				{
					if (session.getStartDate().equals(reportDate))
					{
						for (Sale sale : session.getSales())
						{
							numItems = 0;
							cash = new BigDecimal("0");
							check = new BigDecimal("0");
							credit = new BigDecimal("0");
							
							totalSales = totalSales.add(sale.calcTotal());
							for (SaleLineItem sli : sale.getSaleLineItems())
							{
								numItems += sli.getQuantity();
								totalNumItems += sli.getQuantity();
							}
							for (Payment payment : sale.getPayments())
							{
								if (payment instanceof Cash)
								{
									cash = cash.add(payment.getAmount());
									totalCash = totalCash.add(payment.getAmount());
								}
								if (payment instanceof Check)
								{
									check = check.add(payment.getAmount());
									totalCheck = totalCheck.add(payment.getAmount());
								}
								if (payment instanceof Credit)
								{
									credit = credit.add(payment.getAmount());
									totalCredit = totalCredit.add(payment.getAmount());
								}
							}
							output += leftJustify(sale.getDateTime().toLocalTime().toString(), 15) + leftJustify(sale.calcTotal().toString(), 15);
							output += leftJustify(Integer.toString(numItems), 15) + leftJustify(cash.toString(), 10) + leftJustify(check.toString(), 10) + credit.toString() + "\n";
						}
					}
				}
				
				output += "\n";
				output += leftJustify("Total:", 15) + leftJustify(totalSales.toString(), 15) + leftJustify(Integer.toString(totalNumItems), 15);
				output += leftJustify(totalCash.toString(), 10) + leftJustify(totalCheck.toString(), 10) + totalCredit.toString() + "\n";
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
