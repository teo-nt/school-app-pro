package gr.aueb.cf.schoolpro;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolpro.security.SecUtil;
import gr.aueb.cf.schoolpro.util.DBUtil;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AdminInsertUserForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameTxt;
	private JTextField passwordTxt;

	/**
	 * Create the frame.
	 */
	public AdminInsertUserForm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminInsertUserForm.class.getResource("/resources/eduv2.png")));
		setTitle("Εισαγωγή Χρήστη");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 406, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel usernameLbl = new JLabel("Username");
		usernameLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		usernameLbl.setForeground(new Color(128, 0, 0));
		usernameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		usernameLbl.setBounds(30, 35, 77, 14);
		contentPane.add(usernameLbl);
		
		JLabel passwordLbl = new JLabel("Password");
		passwordLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		passwordLbl.setForeground(new Color(128, 0, 0));
		passwordLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordLbl.setBounds(30, 84, 77, 14);
		contentPane.add(passwordLbl);
		
		JLabel roleLbl = new JLabel("Role");
		roleLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		roleLbl.setForeground(new Color(128, 0, 0));
		roleLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		roleLbl.setBounds(43, 133, 64, 14);
		contentPane.add(roleLbl);
		
		usernameTxt = new JTextField();
		usernameTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		usernameTxt.setBounds(141, 30, 183, 24);
		contentPane.add(usernameTxt);
		usernameTxt.setColumns(10);
		
		passwordTxt = new JTextField();
		passwordTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordTxt.setBounds(141, 79, 183, 24);
		contentPane.add(passwordTxt);
		passwordTxt.setColumns(10);
		
		JComboBox<RoleType> roleComboBox = new JComboBox<>();
		roleComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		roleComboBox.setModel(new DefaultComboBoxModel<RoleType>(RoleType.values()));
		roleComboBox.setBounds(141, 128, 116, 24);
		contentPane.add(roleComboBox);
		
		JButton insertBtn = new JButton("Εισαγωγή");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "INSERT INTO USERS (USERNAME, PASSWORD, ROLE) VALUES(?, ?, ?)";
				String username = usernameTxt.getText();
				String password = passwordTxt.getText();
				String role = roleComboBox.getSelectedItem().toString();
				if (username.equals("") || password.equals("")) {
					JOptionPane.showMessageDialog(null, "Ελλειπή στοιχεία username/password", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (usernameExists(username)) {
					JOptionPane.showMessageDialog(null, "Ο χρήστης " + username + " υπάρχει ήδη", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try (Connection conn = DBUtil.getConnection();
						PreparedStatement ps = conn.prepareStatement(sql)) {
					ps.setString(1, username);
					ps.setString(2, SecUtil.hashPassword(password));
					ps.setString(3,  role);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Επιτυχής εισαγωγή χρήστη!", "Success", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		insertBtn.setForeground(Color.BLUE);
		insertBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		insertBtn.setBounds(103, 194, 108, 43);
		contentPane.add(insertBtn);
		
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getUsersMenu().setEnabled(true);
				Main.getAdminInsertUserForm().setVisible(false);
			}
		});
		closeBtn.setForeground(Color.BLUE);
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		closeBtn.setBounds(216, 194, 108, 43);
		contentPane.add(closeBtn);
	}
	
	private boolean usernameExists(String username) {
		String sql = "SELECT USERNAME FROM USERS WHERE USERNAME = ?";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1,  username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) return true;	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}
}
