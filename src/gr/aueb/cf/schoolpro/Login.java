package gr.aueb.cf.schoolpro;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolpro.security.SecUtil;
import gr.aueb.cf.schoolpro.util.DBUtil;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

public class Login extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameTxt;
	private JPasswordField passwordTxt;
	private JTextField usernameRegTxt;
	private JPasswordField passwordRegTxt;
	private JPasswordField confirmPasswordTxt;
	
	/**
	 * Create the frame.
	 */
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/resources/eduv2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 289);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabbedPane.setBounds(0, 0, 436, 263);
		contentPane.add(tabbedPane);
		
		JPanel login = new JPanel();
		tabbedPane.addTab("Login", null, login, null);
		login.setLayout(null);
		
		JLabel usernameLbl = new JLabel("Username");
		usernameLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		usernameLbl.setBounds(81, 35, 88, 13);
		login.add(usernameLbl);
		
		usernameTxt = new JTextField();
		usernameTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		usernameTxt.setBounds(200, 28, 180, 26);
		login.add(usernameTxt);
		usernameTxt.setColumns(10);
		
		JLabel passwordLbl = new JLabel("Password");
		passwordLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		passwordLbl.setBounds(84, 79, 78, 13);
		login.add(passwordLbl);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.setForeground(new Color(0, 0, 128));
		loginBtn.setBackground(UIManager.getColor("Button.background"));
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputPassword = String.valueOf(passwordTxt.getPassword()).trim();
				String inputUsername = usernameTxt.getText();
				if (inputUsername.matches("[aA]dmin")) {
				
					if (inputPassword.equals(System.getenv("SCHOOL_APP_ADMIN_PASSWD"))) {
						Main.getAdminMenu().setVisible(true);
						Main.getLoginForm().setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(null, "Password Error" , "Error" , JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else {
					String role = getUserRole(inputUsername, inputPassword);
					if (role == null) {
						JOptionPane.showMessageDialog(null, "Username or Password Error" , "Error" , JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					if (role.equals("Student")) {
//						Main.getStudentsMenu().setVisible(true);
//						Main.getLoginForm().setEnabled(false);
					} else if (role.equals("Teacher")) {
//						Main.getTeachersMenu().setVisible(true);
//						Main.getLoginForm().setEnabled(false);
					}
				}	
			}
		});
		loginBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		loginBtn.setBounds(272, 147, 108, 43);
		login.add(loginBtn);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordTxt.setBounds(200, 72, 180, 26);
		login.add(passwordTxt);
		
		JPanel register = new JPanel();
		tabbedPane.addTab("Register", null, register, null);
		register.setLayout(null);
		
		JComboBox<RoleType> roleComboBox = new JComboBox<>();
		roleComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		roleComboBox.setModel(new DefaultComboBoxModel<RoleType>(RoleType.values()));
		roleComboBox.setBounds(206, 16, 173, 25);
		register.add(roleComboBox);
		
		JLabel roleLbl = new JLabel("Role");
		roleLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		roleLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		roleLbl.setBounds(135, 22, 48, 13);
		register.add(roleLbl);
		
		JLabel usernameRegLbl = new JLabel("Username");
		usernameRegLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		usernameRegLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		usernameRegLbl.setBounds(101, 57, 82, 13);
		register.add(usernameRegLbl);
		
		JLabel passwordRegLbl = new JLabel("Password");
		passwordRegLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		passwordRegLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		passwordRegLbl.setBounds(101, 92, 82, 13);
		register.add(passwordRegLbl);
		
		JLabel confirmPasswordLbl = new JLabel("Confirm Password");
		confirmPasswordLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		confirmPasswordLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		confirmPasswordLbl.setBounds(23, 127, 160, 15);
		register.add(confirmPasswordLbl);
		
		usernameRegTxt = new JTextField();
		usernameRegTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		usernameRegTxt.setBounds(206, 51, 173, 25);
		register.add(usernameRegTxt);
		usernameRegTxt.setColumns(10);
		
		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameRegTxt.getText().trim();
				
				//char[] password1chars = passwordRegTxt.getPassword();
				String password1 = String.valueOf(passwordRegTxt.getPassword()).trim();
				
				//char[] password2chars = passwordReg2Txt.getPassword();
				String password2 = String.valueOf(confirmPasswordTxt.getPassword()).trim();
				String role = roleComboBox.getSelectedItem().toString();
				
				if (username == "" || password1 == "" || password2 == "") {
					JOptionPane.showMessageDialog(null, "Please fill username / password", "Basic info", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
//				if (username.length() <= 2 || password2.length() <= 8) {
//					JOptionPane.showMessageDialog(null, "Please fill username / password", "Basic info", JOptionPane.ERROR_MESSAGE);
//					return;
//				}
				
				if (!password1.equals(password2)) {
					JOptionPane.showMessageDialog(null, "Confirmation password not the same", "Basic info", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				
				String sql = "INSERT INTO USERS (USERNAME, PASSWORD, ROLE) VALUES(?, ?, ? )";
				try (Connection connection = DBUtil.getConnection();
						PreparedStatement ps = connection.prepareStatement(sql)) {		 
					ps.setString(1, username);
					ps.setString(2, SecUtil.hashPassword(password1));
					ps.setString(3, role);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "User registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		registerBtn.setForeground(new Color(0, 64, 128));
		registerBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		registerBtn.setBounds(271, 162, 108, 43);
		register.add(registerBtn);
		
		passwordRegTxt = new JPasswordField();
		passwordRegTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordRegTxt.setBounds(206, 86, 173, 25);
		register.add(passwordRegTxt);
		
		confirmPasswordTxt = new JPasswordField();
		confirmPasswordTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		confirmPasswordTxt.setBounds(206, 121, 173, 25);
		register.add(confirmPasswordTxt);
	}
	
	private String getUserRole(String inputUsername, String inputPassword) {
		PreparedStatement ps = null;
		String role = null;
		
		try (Connection conn = DBUtil.getConnection()) {
			String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
			
				ps = conn.prepareStatement(sql);
				ps.setString(1,  inputUsername);
				ResultSet rs = ps.executeQuery();
				if (!rs.next()) return null;
				String password = rs.getString("PASSWORD");
				role = rs.getString("ROLE");
				if (!SecUtil.checkPassword(inputPassword, password)) {
					return null;
				}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		return role;
	}
}
