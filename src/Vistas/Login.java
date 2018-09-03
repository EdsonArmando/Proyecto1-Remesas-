package Vistas;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {
	String nombre,password;
	JPanel login;
	JDialog ventanaLogin;
	JLabel usuario,contraseña;
	JTextField user;
	JPasswordField contra;
	JButton aceptar,cancelar;
	public void formulario() {
		login = new JPanel();
		ventanaLogin = new JDialog();
		login.setLayout(null);
		usuario = new JLabel("Usuario");
		usuario.setBounds(10,20,80,20);
		contraseña = new JLabel("Password");
		contraseña.setBounds(10,50,80,20);
		user = new JTextField();
		nombre = user.getText();
		user.setBounds(100, 20, 80, 20);
		contra = new JPasswordField ();
		password = contra.getText();
		contra.setBounds(100, 50, 80, 20);
		aceptar = new JButton("Aceptar");
		cancelar = new JButton("Cancelar");
		aceptar.setBounds(200,20,80,20);
		cancelar.setBounds(200,50,80,20);
		aceptar.setBounds(200,20,80,20);
		cancelar.setBounds(200,50,80,20);
		login.add(usuario);
		login.add(cancelar);
		login.add(contra);
		login.add(user);
		login.add(contraseña);
		login.add(aceptar);
		ventanaLogin.add(login);
		ventanaLogin.setVisible(true);
		ventanaLogin.setSize(300,150);
		ventanaLogin.setLocationRelativeTo(null);
		
	};
	public void cerrarCesion(String eleccion) {
		
		
	}
}
