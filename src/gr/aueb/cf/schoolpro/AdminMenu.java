package gr.aueb.cf.schoolpro;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminMenu extends JFrame {
	private static final long serialVersionUID = 123456;
	private JPanel contentPane;

	
	public AdminMenu() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminMenu.class.getResource("/resources/eduv2.png")));
		setTitle("Admin Menu");
		setBounds(100, 100, 352, 491);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton teachersBtn = new JButton("");
		teachersBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getAdminTeachersMenu().setVisible(true);
				Main.getAdminMenu().setEnabled(false);
			}
		});
		teachersBtn.setBounds(32, 80, 40, 40);
		contentPane.add(teachersBtn);
		
		JLabel lblNewLabel = new JLabel("Εκπαιδευτές");
		lblNewLabel.setForeground(new Color(128, 64, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(82, 86, 86, 29);
		contentPane.add(lblNewLabel);
		
		JButton studentsBtn = new JButton("");
		studentsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getAdminStudentsMenu().setVisible(true);
				Main.getAdminMenu().setEnabled(false);
			}
		});
		studentsBtn.setBounds(32, 140, 40, 40);
		contentPane.add(studentsBtn);
		
		JLabel lblNewLabel_1 = new JLabel("Εκπαιδευόμενοι");
		lblNewLabel_1.setForeground(new Color(128, 64, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(82, 146, 117, 29);
		contentPane.add(lblNewLabel_1);
		
		JButton usersBtn = new JButton("");
		usersBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getAdminMenu().setEnabled(false);
				Main.getUsersMenu().setVisible(true);
			}
		});
		usersBtn.setBounds(32, 200, 40, 40);
		contentPane.add(usersBtn);
		
		JLabel lblNewLabel_1_1 = new JLabel("Χρήστες");
		lblNewLabel_1_1.setForeground(new Color(128, 64, 0));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(82, 206, 70, 29);
		contentPane.add(lblNewLabel_1_1);
		
		JButton citiesBtn = new JButton("");
		citiesBtn.setBounds(32, 260, 40, 40);
		contentPane.add(citiesBtn);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Πόλεις");
		lblNewLabel_1_1_1.setForeground(new Color(128, 64, 0));
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(82, 266, 51, 29);
		contentPane.add(lblNewLabel_1_1_1);
		
		JButton specialitiesBtn = new JButton("");
		specialitiesBtn.setBounds(32, 320, 40, 40);
		contentPane.add(specialitiesBtn);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Ειδικότητες");
		lblNewLabel_1_1_1_1.setForeground(new Color(128, 64, 0));
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_1.setBounds(82, 326, 86, 29);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(32, 380, 270, 1);
		contentPane.add(separator);
		
		JButton closeBtn = new JButton("Close App");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		closeBtn.setForeground(new Color(0, 0, 255));
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		closeBtn.setBounds(204, 395, 108, 43);
		contentPane.add(closeBtn);
		
		JButton meetingsBtn = new JButton("");
		meetingsBtn.setBounds(32, 20, 40, 40);
		contentPane.add(meetingsBtn);
		
		JLabel lblNewLabel_2 = new JLabel("Συναντήσεις");
		lblNewLabel_2.setForeground(new Color(128, 64, 0));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(82, 26, 95, 29);
		contentPane.add(lblNewLabel_2);
	}
}