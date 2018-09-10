package Vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JTextArea;
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
				String nombre,password,rol;
				nombre = user.getText();
				password = contra.getText();
				rol=tipoUsuario(nombre,password);
				boolean uno = login(nombre,password);
				if(uno==true&&rol.equals("Administrador")){
					JOptionPane.showMessageDialog(null, "Bienvenido al sistema");
					ventanaLogin.dispose();
					menuAdministrador();
				}else if(uno==true&&rol.equals("Operador")){
					JOptionPane.showMessageDialog(null, "Bienvenido al sistema");
					ventanaLogin.dispose();
					menuOperador();
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
				menuControlUsuarios();
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
				logaut(dialog);
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
	public void menuControlUsuarios(){
		JButton altas,bajas,aprobar;
		JDialog dialog = new JDialog();
		JPanel panelIzquierdo = new JPanel();
		panelIzquierdo.setLayout(new GridLayout(2,2,3,3));
		JPanel panelDerecho = new JPanel();
		panelDerecho.setLayout(new GridLayout(2,2,3,3));
		JPanel panelAbajo = new JPanel();
		JPanel arriba = new JPanel();
		JLabel label2 = new JLabel("ControlUsuario");
		label2.setFont(new Font("Tahoma",0,35));
		arriba.add(label2);
		altas = new JButton("Crear Usuario");
		altas.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				formularioCrearUsuario();
			}
			
		});
		bajas = new JButton("EliminarUsuario");
		bajas.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		aprobar = new JButton("Aprobar Usuario");
		aprobar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		panelAbajo.setPreferredSize(new Dimension(55,0));
		panelIzquierdo.add(altas);
		panelIzquierdo.setPreferredSize(new Dimension(250,0));
		panelDerecho.setPreferredSize(new Dimension(250,0));
		panelIzquierdo.add(bajas);
		panelDerecho.add(aprobar);
		arriba.setPreferredSize(new Dimension(0,100));
		dialog.add(arriba, BorderLayout.NORTH);
		dialog.add(panelIzquierdo,BorderLayout.WEST);
		dialog.add(panelDerecho,BorderLayout.EAST);
		dialog.add(panelAbajo, BorderLayout.SOUTH);
		dialog.setLocationRelativeTo(null);
		panelDerecho.setBackground(Color.WHITE);
		panelIzquierdo.setBackground(Color.WHITE);
		arriba.setBackground(Color.WHITE);
		dialog.setSize(520,520);;
		dialog.setVisible(true);
		dialog.setLocationRelativeTo(null);
	}
	public void formularioCrearUsuario(){
		JPasswordField  contraseña,verificas;
		JTextField ids,nombres,fecha,userss,rols,passwordsa;
		JComboBox roles,rolse2;
		JPanel arriba = new JPanel();
		JLabel verificars;
		JPasswordField verificar;
		JDialog dialogUsuario = new JDialog();
		JButton agregar,cancelar,cargaMasiva;
		JPanel panelUsuario = new JPanel();
		panelUsuario.setLayout(null);
		JLabel idss,nombress,fechas,users,rolss,contraseñas;
		JPasswordField passwords;
		idss = new JLabel("CUI: ");
		idss.setBounds(10,20,80,20);
		nombress = new JLabel("Nombre:");
		nombress.setBounds(10,50,95,20);
		fechas = new JLabel("Fecha: ");
		fechas.setBounds(10,80,95,20);
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
		fecha = new JTextField();
		fecha.setBounds(105,80,80,20);
		userss = new JTextField();
		userss.setBounds(105,110,80,20);
		roles = new JComboBox();
		roles.addItem("Operador");
		roles.addItem("Cliente");
		roles.setBounds(105,140,80,20);
		contraseña = new JPasswordField();
		contraseña.setBounds(105,170,80,20);
		verificar = new JPasswordField();
		verificar.setBounds(105,200,80,20);
		agregar = new JButton("Aceptar");
		agregar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String id,nombre,fech,rol,user,password;
				id = ids.getText();
				nombre = nombres.getText();
				fech = fecha.getText();
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
		cargaMasiva = new JButton("Carga Masiva");
		cargaMasiva.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				cargaMasiva();
			}
			
		});
		roles.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(roles.getSelectedItem().equals("Cliente")){
					fecha.setEnabled(false);
					fecha.setText("");
					userss.setEnabled(false);
					userss.setText("");
				}
				if(roles.getSelectedItem().equals("Operador")){
					fecha.setEnabled(true);
					fecha.setText("");
					userss.setEnabled(true);
					userss.setText("");
				}
			}
			
		});
		JLabel label2 = new JLabel("Formulario de Usuarios");
	    label2.setFont(new Font("Tahoma",0,35));
	    arriba.add(label2);
	    dialogUsuario.add(arriba, BorderLayout.NORTH);
		agregar.setBounds(105, 230, 80, 20);
		cancelar.setBounds(205, 230, 80, 20);
		cargaMasiva.setBounds(305, 230, 80, 20);
		panelUsuario.add(idss);
		panelUsuario.add(cancelar);
		panelUsuario.add(nombres);
		panelUsuario.add(fecha);
		panelUsuario.add(userss);
		panelUsuario.add(rolss);
		panelUsuario.add(verificars);
		panelUsuario.add(agregar);
		panelUsuario.add(cargaMasiva);
		panelUsuario.add(contraseña);
		panelUsuario.add(contraseñas);
		panelUsuario.add(nombress);
		panelUsuario.add(verificar);
		panelUsuario.add(fechas);
		panelUsuario.add(users);
		panelUsuario.add(roles);
		panelUsuario.add(ids);
		dialogUsuario.add(panelUsuario);
		dialogUsuario.setLocationRelativeTo(null);
		dialogUsuario.setSize(400,400);
		dialogUsuario.setVisible(true);
		dialogUsuario.setLocationRelativeTo(null);
	
	}
	public void menuOperador(){
		JButton registro,ventas,pago,consulta,salir;
		JDialog dialog = new JDialog();
		JPanel panelIzquierdo = new JPanel();
		panelIzquierdo.setLayout(new GridLayout(2,2,3,3));
		JPanel panelDerecho = new JPanel();
		panelDerecho.setLayout(new GridLayout(2,2,3,3));
		JPanel panelAbajo = new JPanel();
		JPanel arriba = new JPanel();
		JLabel label2 = new JLabel("Panel de Operador");
		label2.setFont(new Font("Tahoma",0,35));
		arriba.add(label2);
		registro = new JButton("Registro de Usuarios");
		registro.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				menuControlUsuarios();
			}
			
		});
		ventas = new JButton("Ventas");
		ventas.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		pago = new JButton("Pagos");
		pago.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		consulta = new JButton("Consultas");
		consulta.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				logaut(dialog);
			}
			
		});
		salir = new JButton("Salir");
		salir.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				logaut(dialog);
			}
			
		});
		panelAbajo.setPreferredSize(new Dimension(55,0));
		panelIzquierdo.add(registro);
		panelDerecho.add(ventas);
		panelIzquierdo.setPreferredSize(new Dimension(300,0));
		panelDerecho.setPreferredSize(new Dimension(300,0));
		panelIzquierdo.add(pago);
		panelDerecho.add(consulta);
		arriba.add(salir);
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
	public void cargaMasiva(){
		JPanel arriba = new JPanel();
		try{
			JButton aceptar;
			JDialog dg = new JDialog();
			JPanel panel = new JPanel(new FlowLayout());
			JLabel tipos;
			JTextArea text=new JTextArea(15,40);
			text.setText("Nombre:"+"Edson Guix"+"Contraseña:"+"123"+"Pasaporte:"+"123456789");
			panel.add(text,BorderLayout.CENTER);
			panel.add(aceptar = new JButton("Aceptar"),BorderLayout.WEST);
			aceptar.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					String tipos,autors,titulos,edicions,
					palabrasClaves,descripcions,temass,noCopiass,noDisponibles,frecuenciaActuals,ejemplaress,areass;
						String uno =text.getText();
						String[] parts = uno.split(";");
						System.out.println(parts.length);
						if(parts[0].equals("0")){
							parts[0]="Libro";
							System.out.println(parts[0]);
						}
						if(parts[0].equals("1")){
							parts[0]="Revista";
						}
						if(parts[0].equals("2")){
							parts[0]="Tesis";
						}
						tipos = parts[0];
						autors = parts[1];
						titulos = parts[2];
						edicions = parts[3];
						palabrasClaves = parts[4];
						descripcions = parts[5];
						temass = parts[6];
						noCopiass = parts[7];
						noDisponibles = parts[8];
						frecuenciaActuals = parts[9];
						ejemplaress = parts[10];
						areass = parts[11];		
				}
				
			});
			dg.add(panel);
			JLabel label2 = new JLabel("Carga Masiva de Usuarios");
		    label2.setFont(new Font("Tahoma",0,35));
		    arriba.add(label2);
		    dg.add(arriba, BorderLayout.NORTH);
			dg.setVisible(true);
			dg.setSize(800,400);
			dg.setLocationRelativeTo(null);
		}catch(NumberFormatException gf){
			JOptionPane.showMessageDialog(null, "Debe ingresar una contraseña");
		}
	}
	public void logaut(JDialog ventana){
		ventana.dispose();
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
	public String tipoUsuario(String nombre, String password){
		String rol=null;
		for(int i=0;i<cont;i++){
			if(usuarioList[i].getNombre().equals(nombre)&&usuarioList[i].getPassword().equals(password)){
				rol = usuarioList[i].getRol();
			}
		}
		return rol;
	}
	
}
