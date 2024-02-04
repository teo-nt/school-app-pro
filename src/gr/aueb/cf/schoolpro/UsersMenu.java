package gr.aueb.cf.schoolpro;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UsersMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameTxt;
	private String username = "";

	/**
	 * Create the frame.
	 */
	public UsersMenu() {
		setTitle("Μενού Χρηστών");
		setIconImage(Toolkit.getDefaultToolkit().getImage(UsersMenu.class.getResource("/resources/eduv2.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 424);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		searchPanel.setBounds(40, 23, 355, 156);
		contentPane.add(searchPanel);
		searchPanel.setLayout(null);
		
		JLabel usernameLbl = new JLabel("Username");
		usernameLbl.setForeground(new Color(128, 0, 0));
		usernameLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		usernameLbl.setBounds(140, 10, 75, 35);
		searchPanel.add(usernameLbl);
		
		usernameTxt = new JTextField();
		usernameTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		usernameTxt.setBounds(69, 55, 216, 35);
		searchPanel.add(usernameTxt);
		usernameTxt.setColumns(10);
		
		JButton searchBtn = new JButton("Αναζήτηση");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = usernameTxt.getText();
				usernameTxt.setText("");
				Main.getAdminUpdateDeleteUsersForm().setVisible(true);
				Main.getUsersMenu().setEnabled(false);	
			}
		});
		searchBtn.setForeground(new Color(0, 0, 255));
		searchBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		searchBtn.setBounds(116, 100, 122, 43);
		searchPanel.add(searchBtn);
		
		JPanel insertPanel = new JPanel();
		insertPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		insertPanel.setBounds(40, 204, 355, 97);
		contentPane.add(insertPanel);
		insertPanel.setLayout(null);
		
		JButton insertBtn = new JButton("Εισαγωγή");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getAdminInsertUserForm().setVisible(true);
				Main.getUsersMenu().setEnabled(false);
			}
		});
		insertBtn.setForeground(new Color(0, 0, 255));
		insertBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		insertBtn.setBounds(116, 27, 122, 43);
		insertPanel.add(insertBtn);
		
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getAdminMenu().setEnabled(true);
				Main.getUsersMenu().setVisible(false);
			}
		});
		closeBtn.setForeground(new Color(0, 0, 255));
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		closeBtn.setBounds(287, 328, 108, 43);
		contentPane.add(closeBtn);
	}
	
	public String getUsername() {
		return username;
	}
}
