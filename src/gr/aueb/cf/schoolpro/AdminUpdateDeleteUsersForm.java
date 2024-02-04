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
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminUpdateDeleteUsersForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idTxt;
	private JTextField usernameTxt;
	private JTextField passwordTxt;
	private JComboBox<RoleType> roleComboBox = new JComboBox<>();
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs = null;

	/**
	 * Create the frame.
	 */
	public AdminUpdateDeleteUsersForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				passwordTxt.setText("");
				String sql = "SELECT * FROM USERS WHERE USERNAME LIKE ?";
				try {
					conn = DBUtil.getConnection();
					ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					ps.setString(1, Main.getUsersMenu().getUsername() + "%");
					rs = ps.executeQuery();
					if (rs.next()) {
						idTxt.setText(rs.getString("ID"));
						usernameTxt.setText(rs.getString("USERNAME"));
						roleComboBox.setSelectedItem(RoleType.valueOf(rs.getString("ROLE")));
					} else {
						JOptionPane.showMessageDialog(null, "No users found", "Users", JOptionPane.WARNING_MESSAGE);
						Main.getUsersMenu().setEnabled(true);
						Main.getAdminUpdateDeleteUsersForm().setVisible(false);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		setTitle("Ενημέρωση / Διαγραφή Χρήστη");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminUpdateDeleteUsersForm.class.getResource("/resources/eduv2.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 405, 392);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel idLbl = new JLabel("ID");
		idLbl.setForeground(new Color(128, 0, 0));
		idLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		idLbl.setBounds(88, 38, 18, 17);
		contentPane.add(idLbl);
		
		idTxt = new JTextField();
		idTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		idTxt.setEditable(false);
		idTxt.setColumns(10);
		idTxt.setBackground(new Color(252, 252, 205));
		idTxt.setBounds(125, 34, 59, 24);
		contentPane.add(idTxt);
		
		JLabel usernameLbl = new JLabel("Username");
		usernameLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		usernameLbl.setForeground(new Color(128, 0, 0));
		usernameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		usernameLbl.setBounds(23, 77, 83, 17);
		contentPane.add(usernameLbl);
		
		usernameTxt = new JTextField();
		usernameTxt.setBackground(new Color(252, 252, 205));
		usernameTxt.setEditable(false);
		usernameTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		usernameTxt.setColumns(10);
		usernameTxt.setBounds(125, 73, 203, 24);
		contentPane.add(usernameTxt);
		
		JLabel passwordLbl = new JLabel("New Password");
		passwordLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		passwordLbl.setForeground(new Color(128, 0, 0));
		passwordLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordLbl.setBounds(0, 118, 106, 17);
		contentPane.add(passwordLbl);
		
		passwordTxt = new JTextField();
		passwordTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordTxt.setColumns(10);
		passwordTxt.setBounds(125, 114, 203, 24);
		contentPane.add(passwordTxt);
		
		JLabel roleLbl = new JLabel("Role");
		roleLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		roleLbl.setForeground(new Color(128, 0, 0));
		roleLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		roleLbl.setBounds(40, 160, 66, 17);
		contentPane.add(roleLbl);
		
		
		roleComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		roleComboBox.setModel(new DefaultComboBoxModel<RoleType>(RoleType.values()));
		roleComboBox.setBounds(125, 156, 117, 24);
		contentPane.add(roleComboBox);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "UPDATE USERS SET PASSWORD = ?, ROLE = ? WHERE ID = ?";
				String password = passwordTxt.getText();
				String role = roleComboBox.getSelectedItem().toString();
				String id = idTxt.getText();
				if (password.equals("")) {
					JOptionPane.showMessageDialog(null, "Empty input password", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try (Connection conn = DBUtil.getConnection();
						PreparedStatement ps = conn.prepareStatement(sql)) {
					ps.setString(1, SecUtil.hashPassword(password));
					ps.setString(2,  role);
					ps.setInt(3,  Integer.parseInt(id));
					int n = ps.executeUpdate();
					
					if (n > 0) {
						JOptionPane.showMessageDialog(null, "Successful Update", "Update", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Update Error", "Update", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		updateBtn.setForeground(new Color(0, 0, 128));
		updateBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		updateBtn.setBounds(16, 288, 108, 43);
		contentPane.add(updateBtn);
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "DELETE FROM USERS WHERE ID = ?";
				try (Connection conn = DBUtil.getConnection();
						PreparedStatement ps = conn.prepareStatement(sql)) {
					ps.setInt(1, Integer.parseInt(idTxt.getText()));
					
					int response = JOptionPane.showConfirmDialog(null, "Είστε σίγουρος;", "Warning", JOptionPane.YES_NO_CANCEL_OPTION);
					if (response == JOptionPane.YES_OPTION) {
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "User: " + usernameTxt.getText() + " was deleted successfully", "Deleted", JOptionPane.INFORMATION_MESSAGE);
					} else {
						return;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		deleteBtn.setForeground(new Color(0, 0, 128));
		deleteBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		deleteBtn.setBounds(140, 288, 108, 43);
		contentPane.add(deleteBtn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(40, 204, 303, 2);
		contentPane.add(separator);
		
		JButton firstBtn = new JButton("");
		firstBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.first()) {
						idTxt.setText(rs.getString("ID"));
						usernameTxt.setText(rs.getString("USERNAME"));
						roleComboBox.setSelectedItem(RoleType.valueOf(rs.getString("ROLE")));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		firstBtn.setIcon(new ImageIcon(AdminUpdateDeleteUsersForm.class.getResource("/resources/First record.png")));
		firstBtn.setBounds(40, 229, 49, 35);
		contentPane.add(firstBtn);
		
		JButton prevBtn = new JButton("");
		prevBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.previous()) {
						idTxt.setText(rs.getString("ID"));
						usernameTxt.setText(rs.getString("USERNAME"));
						roleComboBox.setSelectedItem(RoleType.valueOf(rs.getString("ROLE")));
					} else {
						rs.first();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		prevBtn.setIcon(new ImageIcon(AdminUpdateDeleteUsersForm.class.getResource("/resources/Previous_record.png")));
		prevBtn.setBounds(125, 229, 49, 35);
		contentPane.add(prevBtn);
		
		JButton nextBtn = new JButton("");
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.next()) {
						idTxt.setText(rs.getString("ID"));
						usernameTxt.setText(rs.getString("USERNAME"));
						roleComboBox.setSelectedItem(RoleType.valueOf(rs.getString("ROLE")));
					} else {
						rs.last();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		nextBtn.setIcon(new ImageIcon(AdminUpdateDeleteUsersForm.class.getResource("/resources/Next_track.png")));
		nextBtn.setBounds(212, 229, 49, 35);
		contentPane.add(nextBtn);
		
		JButton lastBtn = new JButton("");
		lastBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.last()) {
						idTxt.setText(rs.getString("ID"));
						usernameTxt.setText(rs.getString("USERNAME"));
						roleComboBox.setSelectedItem(RoleType.valueOf(rs.getString("ROLE")));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		lastBtn.setIcon(new ImageIcon(AdminUpdateDeleteUsersForm.class.getResource("/resources/Last_Record.png")));
		lastBtn.setBounds(299, 229, 49, 35);
		contentPane.add(lastBtn);
		
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rs.close();
					ps.close();
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				Main.getUsersMenu().setEnabled(true);
				Main.getAdminUpdateDeleteUsersForm().setVisible(false);
			}
		});
		closeBtn.setForeground(new Color(0, 0, 128));
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		closeBtn.setBounds(264, 288, 108, 43);
		contentPane.add(closeBtn);
	}
}
