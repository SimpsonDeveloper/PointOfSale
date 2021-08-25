package HI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import problemDomain.Cashier;
import problemDomain.Sale;
import problemDomain.Session;
import problemDomain.Store;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.JScrollPane;

public class ReportCashier extends JPanel {
	private LocalDate reportDate;
	DatePicker datePicker;

	/**
	 * Create the panel.
	 */
	public ReportCashier(Store store, JFrame frame) {
		setLayout(null);
		
		JLabel lblCashierReport = new JLabel("Cashier Report");
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
				BigDecimal amount;
				int count;
				boolean cashierUsed;
				
				BigDecimal totalAmount = new BigDecimal("0");
				
				reportDate = datePicker.getDate();
				String output = "Cashier Report For: " + reportDate.toString() + "\n" + "\n";
				output += leftJustify("Number", 10) + leftJustify("Name", 20) + leftJustify("Count", 10) + "Amount\n";
				for (Cashier cashier : store.getCashiers())
				{
					cashierUsed = false;
					amount = new BigDecimal("0");
					count = 0;
					for (Session session : store.getSessions())
					{
						if (session.getStartDate().equals(reportDate) && session.getCashier().getNumber().equals(cashier.getNumber()))
						{
							cashierUsed = true;
							for (Sale sale : session.getSales())
							{
								count++;
								amount = amount.add(sale.calcTotal());
								totalAmount = totalAmount.add(sale.calcTotal());
							}
						}
					}
					if (cashierUsed)
					{
						output += leftJustify(cashier.getNumber(), 10) + leftJustify(cashier.getName(), 20);
						output += leftJustify(Integer.toString(count), 10) + amount + "\n";
					}
				}
				output += "\n";
				output += leftJustify("Total:", 40) + totalAmount;
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
