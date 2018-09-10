package Vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Modelos.Usuario;

public class Login {
	int cont =0;
	Scanner sc = new Scanner(System.in);
	Usuario[] usuarioList = new Usuario[50];
	
	public void crearUsuario(String nombre, String rol, String identificador, String password, Date fechaInicio) {
		boolean dos = false;
		for(int i=0;i<cont;i++){
			if(usuarioList[i].getNombre().equals(nombre)&&usuarioList[i].getPassword().equals(password)){
				dos=true;
			}else{
				dos=false;
			}
		}
		if(dos == true){
			JOptionPane.showMessageDialog(null, "Este usuario ya existe");
		}else if(dos == false){
			usuarioList[cont]=new Usuario(nombre,rol,identificador,password,fechaInicio);
			cont++;
		}
	}
	public void formulario() {
		JPanel login;
		JDialog ventanaLogin;
		JLabel usuario,contraseña;
		JTextField user;
		JPasswordField contra;
		JButton aceptar,cancelar;
		Usuario users = new Usuario();
		login = new JPanel();
		ventanaLogin = new JDialog();
		login.setLayout(null);
		usuario = new JLabel("Usuario");
		usuario.setBounds(10,20,80,20);
		contraseña = new JLabel("Password");
		contraseña.setBounds(10,50,80,20);
		user = new JTextField();
	
		user.setBounds(100, 20, 80, 20);
		contra = new JPasswordField ();
		
		contra.setBounds(100, 50, 80, 20);
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			ventanaLogin.dispose();	
			}
			
		});
		aceptar = new JButton("Aceptar");
		aceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre,password;
				nombre = user.getText();
				password = contra.getText();
				boolean uno = login(nombre,password);
				if(uno==true){
					JOptionPane.showMessageDialog(null, "Bienvendo al sistema");
					ventanaLogin.dispose();
					menuAdministrador();
				}else{
					JOptionPane.showMessageDialog(null, "Verifique sus credenciales");
				}
				
			}
			
		});
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
		
	}
	public void menuAdministrador(){
		JButton controlUsuarios,configuracion,reportes,salir;
		JDialog dialog = new JDialog();
		JPanel panelIzquierdo = new JPanel();
		panelIzquierdo.setLayout(new GridLayout(2,2,3,3));
		JPanel panelDerecho = new JPanel();
		panelDerecho.setLayout(new GridLayout(2,2,3,3));
		JPanel panelAbajo = new JPanel();
		JPanel arriba = new JPanel();
		JLabel label2 = new JLabel("Panel de administrador");
		label2.setFont(new Font("Tahoma",0,35));
		arriba.add(label2);
		controlUsuarios = new JButton("Control de Usuarios");
		controlUsuarios.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				formularioCrearUsuario();
			}
			
		});
		configuracion = new JButton("Configuración");
		configuracion.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		reportes = new JButton("Reportes");
		reportes.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		salir = new JButton("Logaut");
		salir.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
			}
			
		});
		panelAbajo.setPreferredSize(new Dimension(55,0));
		panelIzquierdo.add(controlUsuarios);
		panelDerecho.add(salir);
		panelIzquierdo.setPreferredSize(new Dimension(300,0));
		panelDerecho.setPreferredSize(new Dimension(300,0));
		panelIzquierdo.add(configuracion);
		panelDerecho.add(reportes);
		arriba.setPreferredSize(new Dimension(0,100));
		dialog.add(arriba, BorderLayout.NORTH);
		dialog.add(panelIzquierdo,BorderLayout.WEST);
		dialog.add(panelDerecho,BorderLayout.EAST);
		dialog.add(panelAbajo, BorderLayout.SOUTH);
		dialog.setLocationRelativeTo(null);
		panelDerecho.setBackground(Color.WHITE);
		panelIzquierdo.setBackground(Color.WHITE);
		arriba.setBackground(Color.WHITE);
		dialog.setSize(620,620);;
		dialog.setVisible(true);
		dialog.setLocationRelativeTo(null);
	}
	public void formularioCrearUsuario(){
		JPasswordField  contraseña,verificas;
		JTextField ids,nombres,apellidos,userss,rols,passwordsa;
		JComboBox roles,rolse2;
		JPanel arriba = new JPanel();
		JLabel verificars;
		JPasswordField verificar;
		JDialog dialogUsuario = new JDialog();
		JButton agregar,cancelar;
		JPanel panelUsuario = new JPanel();
		panelUsuario.setLayout(null);
		JLabel idss,nombress,apellidoss,users,rolss,contraseñas;
		JPasswordField passwords;
		idss = new JLabel("CUI: ");
		idss.setBounds(10,20,80,20);
		nombress = new JLabel("Nombre:");
		nombress.setBounds(10,50,95,20);
		apellidoss = new JLabel("Apellido: ");
		apellidoss.setBounds(10,80,95,20);
		users = new JLabel("Usuario: ");
		users.setBounds(10,110,95,20);
		rolss = new JLabel("Rol: ");
		rolss.setBounds(10,140,95,20);
		contraseñas = new JLabel("Contraseña");
		contraseñas.setBounds(10,170,110,20);
		verificars = new JLabel("Verificar");
		verificars.setBounds(10,200,110,20);
		ids = new JTextField();
		ids.setBounds(105,20,80,20);
		nombres = new JTextField();
		nombres.setBounds(105, 50, 80, 20);
		apellidos = new JTextField();
		apellidos.setBounds(105,80,80,20);
		userss = new JTextField();
		userss.setBounds(105,110,80,20);
		roles = new JComboBox();
		roles.addItem("usuario");
		roles.addItem("administrador");
		roles.setBounds(105,140,80,20);
		contraseña = new JPasswordField();
		contraseña.setBounds(105,170,80,20);
		verificar = new JPasswordField();
		verificar.setBounds(105,200,80,20);
		agregar = new JButton("Aceptar");
		agregar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String id,nombre,apellido,rol,user,password;
				id = ids.getText();
				nombre = nombres.getText();
				apellido = apellidos.getText();
				rol = String.valueOf(roles.getSelectedItem());
				user = userss.getText();
			}
			
		});
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dialogUsuario.dispose();
				
			}
			
		});
		JLabel label2 = new JLabel("Formulario de Usuarios");
	    label2.setFont(new Font("Tahoma",0,35));
	    arriba.add(label2);
	    dialogUsuario.add(arriba, BorderLayout.NORTH);
		agregar.setBounds(105, 230, 80, 20);
		cancelar.setBounds(205, 230, 80, 20);
		panelUsuario.add(idss);
		panelUsuario.add(cancelar);
		panelUsuario.add(nombres);
		panelUsuario.add(apellidoss);
		panelUsuario.add(userss);
		panelUsuario.add(rolss);
		panelUsuario.add(verificars);
		panelUsuario.add(agregar);
		panelUsuario.add(contraseña);
		panelUsuario.add(contraseñas);
		panelUsuario.add(nombress);
		panelUsuario.add(verificar);
		panelUsuario.add(apellidos);
		panelUsuario.add(users);
		panelUsuario.add(roles);
		panelUsuario.add(ids);
		dialogUsuario.add(panelUsuario);
		dialogUsuario.setLocationRelativeTo(null);
		dialogUsuario.setSize(400,400);
		dialogUsuario.setVisible(true);
		dialogUsuario.setLocationRelativeTo(null);
	
	}
	public void recorrerLista(){
		for(int i=0;i<cont;i++){
				System.out.println(usuarioList[i].getNombre());
		}
	}
	public boolean login(String nombre, String password){
		boolean acceso=false;
		for(int i=0;i<cont;i++){
			if(usuarioList[i].getNombre().equals(nombre)&&usuarioList[i].getPassword().equals(password)){
				acceso=true;
			}
		}
		return acceso;
	}
	
}
