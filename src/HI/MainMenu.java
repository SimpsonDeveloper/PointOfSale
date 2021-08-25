package HI;

import javax.swing.JPanel;

import problemDomain.Store;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class MainMenu extends JPanel {

	/**
	 * Create the panel.
	 */
	public MainMenu(Store store, JFrame frame) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel(store.getName());
		lblNewLabel.setBounds(84, 87, 315, 14);
		add(lblNewLabel);
		
	}

}
