package HI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import problemDomain.Store;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class POSFrame extends javax.swing.JFrame {

	private JPanel contentPane;
	JFrame me = this;

	/**
	 * Launch the application.
	 */
	public static void run(Store store) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					POSFrame frame = new POSFrame(store);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	
	/**
	 * Create the frame.
	 */
	public POSFrame(Store store) {
		setDefaultCloseOperation(POSFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 400);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMaintain = new JMenu("Maintain");
		menuBar.add(mnMaintain);
		
		JMenuItem mntmStore = new JMenuItem("Store");
		mntmStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getContentPane().removeAll();
				getContentPane().add(new EditStore(store, me));
				getContentPane().revalidate();
			}
		});
		mnMaintain.add(mntmStore);
		
		JMenuItem mntmRegister = new JMenuItem("Register");
		mntmRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getContentPane().removeAll();
				getContentPane().add(new SelectRegister(store, me));
				getContentPane().revalidate();
			}
		});
		mnMaintain.add(mntmRegister);
		
		JMenuItem mntmCashier = new JMenuItem("Cashier");
		mntmCashier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new SelectCashier(store, me));
				getContentPane().revalidate();
			}
		});
		mnMaintain.add(mntmCashier);
		
		JMenuItem mntmTaxCategories = new JMenuItem("Tax Categories");
		mntmTaxCategories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getContentPane().removeAll();
				getContentPane().add(new SelectTaxCategory(store, me));
				getContentPane().revalidate();
			}
		});
		mnMaintain.add(mntmTaxCategories);
		
		JMenuItem mntmItems = new JMenuItem("Items");
		mntmItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getContentPane().removeAll();
				getContentPane().add(new SelectItem(store, me));
				getContentPane().revalidate();
			}
		});
		mnMaintain.add(mntmItems);
		
		JMenu mnPos = new JMenu("POS");
		menuBar.add(mnPos);
		
		JMenuItem mntmLogin = new JMenuItem("Login");
		mntmLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getContentPane().removeAll();
				getContentPane().add(new Login(store, me));
				getContentPane().revalidate();
			}
		});
		mnPos.add(mntmLogin);
		
		JMenu mnReports = new JMenu("Reports");
		menuBar.add(mnReports);
		
		JMenuItem mntmSession = new JMenuItem("Cashier");
		mntmSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new ReportCashier(store, me));
				getContentPane().revalidate();
			}
		});
		mnReports.add(mntmSession);
		
		JMenuItem mntmItem = new JMenuItem("Item");
		mntmItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new ReportItem(store, me));
				getContentPane().revalidate();
			}
		});
		mnReports.add(mntmItem);
		
		JMenuItem mntmSale = new JMenuItem("Sale");
		mntmSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new ReportSale(store, me));
				getContentPane().revalidate();
			}
		});
		mnReports.add(mntmSale);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		getContentPane().removeAll();
		getContentPane().add(new MainMenu(store, this));
		getContentPane().revalidate();
		
		

	}
}
