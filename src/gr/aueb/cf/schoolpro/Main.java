package gr.aueb.cf.schoolpro;

import java.awt.EventQueue;

public class Main {
	private static Login login;
	private static AdminMenu adminMenu;
	private static AdminTeachersMenu adminTeachersMenu;
	private static AdminInsertStudentsForm adminInsertStudentsForm;
	private static AdminInsertTeachersForm adminInsertTeachersForm;
	private static AdminUpdateDeleteStudentsForm adminUpdateDeleteStudentsForm;
	private static AdminStudentsMenu adminStudentsMenu;
	private static UsersMenu usersMenu;
	private static AdminInsertUserForm adminInsertUserForm;
	private static AdminUpdateDeleteUsersForm adminUpdateDeleteUsersForm;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login = new Login();
					login.setVisible(true);
					
					adminMenu = new AdminMenu();
					adminMenu.setVisible(false);
					
					adminTeachersMenu = new AdminTeachersMenu();
					adminTeachersMenu.setVisible(false);
					
					adminStudentsMenu = new AdminStudentsMenu();
					adminStudentsMenu.setVisible(false);
					
					usersMenu = new UsersMenu();
					usersMenu.setVisible(false);
					
					adminInsertUserForm = new AdminInsertUserForm();
					adminInsertUserForm.setVisible(false);
					
					adminUpdateDeleteUsersForm = new AdminUpdateDeleteUsersForm();
					adminUpdateDeleteUsersForm.setVisible(false);
					
					adminInsertTeachersForm = new AdminInsertTeachersForm();
					adminInsertTeachersForm.setVisible(false);
					
					adminInsertStudentsForm = new AdminInsertStudentsForm();
					adminInsertStudentsForm.setVisible(false);
					
					adminUpdateDeleteStudentsForm = new AdminUpdateDeleteStudentsForm();
					adminUpdateDeleteStudentsForm.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static Login getLoginForm() {
		return login;
	}

	public static AdminMenu getAdminMenu() {
		return adminMenu;
	}

	public static AdminTeachersMenu getAdminTeachersMenu() {
		return adminTeachersMenu;
	}

	public static AdminInsertStudentsForm getAdminInsertStudentsForm() {
		return adminInsertStudentsForm;
	}

	public static AdminStudentsMenu getAdminStudentsMenu() {
		return adminStudentsMenu;
	}

	public static AdminInsertTeachersForm getAdminInsertTeachersForm() {
		return adminInsertTeachersForm;
	}

	public static AdminUpdateDeleteStudentsForm getAdminUpdateDeleteStudentsForm() {
		return adminUpdateDeleteStudentsForm;
	}
	
	public static UsersMenu getUsersMenu() {
		return usersMenu;
	}
	
	public static AdminInsertUserForm getAdminInsertUserForm() {
		return adminInsertUserForm;
	}
	
	public static AdminUpdateDeleteUsersForm getAdminUpdateDeleteUsersForm() {
		return adminUpdateDeleteUsersForm;
	}
}
