package Vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Modelos.Usuario;

public class Login {
	int cont =2;
	int cont2=0;
	Scanner sc = new Scanner(System.in);
	Usuario[] usuarioList = new Usuario[50];
	Usuario[] listaSolicitudes = new Usuario[50];
	String nombreUsuario=null;
	JLabel idss=null;
	JPasswordField  contraseña=null;
	public void formulario() {
		usuarioList[0] = new Usuario("Edson","Administrador","201701029","123",null);
		usuarioList[1] = new Usuario("Emiliana","Operador","254158","125",null);
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
				nombreUsuario=tipoUsuario(nombre,password);
				boolean uno = login(nombre,password);
				if(uno==true&&rol.equals("Administrador")){
					JOptionPane.showMessageDialog(null, "Bienvenido al sistema");
					menuAdministrador();
					user.setText("");
					contra.setText("");
					recorrerLista();
				}else if(uno==true&&rol.equals("Operador")){
					JOptionPane.showMessageDialog(null, "Bienvenido al sistema");
					menuOperador();
					user.setText("");
					contra.setText("");
				}else if(uno==true&&rol.equals("Cliente")){
					JOptionPane.showMessageDialog(null, "Bienvenido al sistema");
					menuCliente();
					user.setText("");
					contra.setText("");
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
				tablaSolicitudUsuarios();
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
	public void tablaSolicitudUsuarios(){
		JPanel arriba = new JPanel();
		JFrame uno=new JFrame();
		JPanel pane= new JPanel();
		JButton agregar;
		JLabel usuario;
		JComboBox id;
		  String[] columns = {"Pasaporte", "Nombre", "Rol"};
		    Object [][]data = new Object[cont][3];
		        for(int i=0;i<cont2;i++){
					try{
						data[i][0]=listaSolicitudes[i].getIdentificador();
						data[i][1]=listaSolicitudes[i].getNombre();
						data[i][2]=listaSolicitudes[i].getRol();
						
					}catch(NullPointerException e){
						System.out.print("NullPointerException caught");
					}
				}
	    JTable table = new JTable(data, columns);

	    JScrollPane scrollPane = new JScrollPane(table);
	    uno.add(scrollPane, BorderLayout.CENTER);
	    JLabel label2 = new JLabel("Solicitud de Usuarios");
	    label2.setFont(new Font("Tahoma",0,35));
	    arriba.add(label2);
	    pane = new JPanel(new FlowLayout());
	    pane.add(usuario=new JLabel("Pasaporte: "));
	    pane.add(id=new JComboBox());
	    pane.add(agregar =new JButton("Aceptar"));
	    table.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				int row=table.rowAtPoint(e.getPoint());

				int col= table.columnAtPoint(e.getPoint());
				String identificador = table.getValueAt(row,col).toString();
				id.addItem(identificador);
			}

			@Override
			public void mousePressed(MouseEvent e) {
			
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	   
	       });
	    agregar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean dos=false;
				String nombre=null,rol=null,identificador=null,password=null;
				Date fechaIncio = null;
				for(int i=0;i<cont2;i++){
					if(id.getSelectedItem().equals(listaSolicitudes[i].getIdentificador())){
						identificador=listaSolicitudes[i].getIdentificador();
						nombre=listaSolicitudes[i].getNombre();
						password=listaSolicitudes[i].getPassword();
						fechaIncio= listaSolicitudes[i].getFechaInicio();
						rol =listaSolicitudes[i].getRol();						
					}
				}
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
					usuarioList[cont]=new Usuario(nombre,rol,identificador,password,null);
					JOptionPane.showMessageDialog(null, "El usuario fue ingresado exitosamente");
					cont++;
				}
				recorrerLista();
				
			}
	    	
	    });
	    pane.setPreferredSize(new Dimension(200,0));
	    uno.add(pane,BorderLayout.WEST);
	    uno.add(arriba, BorderLayout.NORTH);
	    uno.setSize(600, 400);
	    uno.setLocationRelativeTo(null);
	    uno.setVisible(true);
	}
	/*public void tablaSolicitudUsuarios(){
		final JFrame frame = new JFrame("JTable Demo");
	    String[] columns = {"Pasaporte", "Nombre", "Rol"};
	    Object [][]data = new Object[cont][3];
	        for(int i=0;i<cont;i++){
				try{
					data[i][0]=listaSolicitudes[i].getIdentificador();
					data[i][1]=listaSolicitudes[i].getNombre();
					data[i][2]=listaSolicitudes[i].getRol();
					
				}catch(NullPointerException e){
					System.out.print("NullPointerException caught");
				}
			}
	        JTable table = new JTable(data, columns);
	        JScrollPane scrollPane = new JScrollPane(table);
	        JLabel lblHeading = new JLabel("Solicitud de registro");
	        lblHeading.setFont(new Font("Arial",Font.TRUETYPE_FONT,24));
	       table.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				int row=table.rowAtPoint(e.getPoint());

				int col= table.columnAtPoint(e.getPoint());

				System.out.println(" Value in the cell clicked :"+ " " +table.getValueAt(row,col).toString());
			}

			@Override
			public void mousePressed(MouseEvent e) {
			
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	   
	       });
	        frame.getContentPane().setLayout(new BorderLayout());
	        frame.getContentPane().add(lblHeading,BorderLayout.PAGE_START);
	        frame.getContentPane().add(scrollPane,BorderLayout.CENTER);
	        frame.setSize(550, 200);
	        frame.setVisible(true);
	        frame.setLocationRelativeTo(null);

	}*/
	public void formularioCrearUsuario(){
		JPasswordField verificas=null;
		JTextField ids,nombres,fecha,userss,rols,passwordsa=null;
		JComboBox roles,rolse2;
		JPanel arriba = new JPanel();
		JLabel verificars=null;
		JPasswordField verificar=null;
		JDialog dialogUsuario = new JDialog();
		JButton agregar,cancelar,cargaMasiva;
		JPanel panelUsuario = new JPanel();
		panelUsuario.setLayout(null);
		JLabel nombress,fechas,users,rolss,contraseñas=null,label2=null;
		JPasswordField passwords=null;
		if(nombreUsuario.equals("Administrador")){
			idss = new JLabel("CUI: ");
			idss.setBounds(10,20,80,20);
		}else if(nombreUsuario.equals("Operador")){
			idss = new JLabel("Pasaporte: ");
			idss.setBounds(10,20,80,20);
		}
		nombress = new JLabel("Nombre:");
		nombress.setBounds(10,50,95,20);
		fechas = new JLabel("Fecha: ");
		fechas.setBounds(10,80,95,20);
		users = new JLabel("Usuario: ");
		users.setBounds(10,110,95,20);
		rolss = new JLabel("Rol: ");
		rolss.setBounds(10,140,95,20);
		if(nombreUsuario.equals("Administrador")){
			contraseñas = new JLabel("Contraseña");
			contraseñas.setBounds(10,170,110,20);
			contraseña = new JPasswordField();
			contraseña.setBounds(105,170,80,20);
		}else if(nombreUsuario.equals("Operador")){
			contraseñas = new JLabel("Contraseña");
			contraseñas.setBounds(10,80,95,20);
			contraseña = new JPasswordField();
			contraseña.setBounds(105,80,80,20);
		}
		if(nombreUsuario.equals("Administrador")){
			verificars = new JLabel("Verificar");
			verificars.setBounds(10,200,110,20);
			verificar = new JPasswordField();
			verificar.setBounds(105,200,80,20);
		}else if(nombreUsuario.equals("Operador")){
			verificars = new JLabel("Verificar");
			verificars.setBounds(10,110,95,20);
			verificar = new JPasswordField();
			verificar.setBounds(105,110,80,20);
		}
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
		agregar = new JButton("Aceptar");
		agregar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String id,nombre,rol,user,password;
				Date fech;
				id = ids.getText();
				nombre = nombres.getText();
				fech = convertirFecha(fecha.getText());
				password = contraseña.getText();
				rol = String.valueOf(roles.getSelectedItem());
				user = userss.getText();
				boolean dos = false;
				if(nombreUsuario.equals("Administrador")){
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
						usuarioList[cont]=new Usuario(nombre,rol,id,password,fech);
						JOptionPane.showMessageDialog(null, "El usuario fue ingresado exitosamente");
						cont++;
					}
					System.out.println(user+password);
					recorrerLista();
				}
				if(nombreUsuario.equals("Operador")){
					for(int i=0;i<cont2;i++){
						if(listaSolicitudes[i].getNombre().equals(nombre)&&listaSolicitudes[i].getPassword().equals(password)){
							dos=true;
						}else{
							dos=false;
						}
					}
					if(dos == true){
						JOptionPane.showMessageDialog(null, "Este usuario ya existe");
					}else if(dos == false){
						listaSolicitudes[cont2]=new Usuario(nombre,"Cliente",id,password,null);
						JOptionPane.showMessageDialog(null, "La solicitud fue enviada exitosamente");
						cont2++;
					}
					recorrerListaDos();
				}
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
					idss.setText("Pasaporte: ");
					fecha.setEnabled(false);
					fecha.setText("");
					userss.setEnabled(false);
					userss.setText("");
				}
				if(roles.getSelectedItem().equals("Operador")){
					idss.setText("CUI: ");
					fecha.setEnabled(true);
					fecha.setText("");
					userss.setEnabled(true);
					userss.setText("");
				}
			}
			
		});
		if(nombreUsuario.equals("Administrador")){
			label2 = new JLabel("Formulario de Usuarios");
	    }else if(nombreUsuario.equals("Operador")){
	    	label2 = new JLabel("Solicitud de registro");
	    }
	    label2.setFont(new Font("Tahoma",0,35));
	    arriba.add(label2);
	    dialogUsuario.add(arriba, BorderLayout.NORTH);
	    if(nombreUsuario.equals("Administrador")){
	    	agregar.setBounds(105, 230, 80, 20);
			cancelar.setBounds(205, 230, 80, 20);
			cargaMasiva.setBounds(305, 230, 80, 20);
	    }else if(nombreUsuario.equals("Operador")){
	    	agregar.setBounds(10,140,95,20);
			cancelar.setBounds(105,140,80,20);
	    }
		panelUsuario.add(idss);
		panelUsuario.add(cancelar);
		panelUsuario.add(nombres);
		if(nombreUsuario.equals("Administrador")){
			panelUsuario.add(fecha);
			panelUsuario.add(fechas);
		}
		if(nombreUsuario.equals("Administrador")){
			panelUsuario.add(userss);
			panelUsuario.add(users);
		}
		if(nombreUsuario.equals("Administrador")){
			panelUsuario.add(rolss);
			panelUsuario.add(roles);
		}
		panelUsuario.add(verificars);
		panelUsuario.add(agregar);
		if(nombreUsuario.equals("Administrador")){
			panelUsuario.add(cargaMasiva);
		}
		panelUsuario.add(contraseña);
		panelUsuario.add(contraseñas);
		panelUsuario.add(nombress);
		panelUsuario.add(verificar);
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
				formularioCrearUsuario();
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
	public void menuCliente(){
		JButton remesasCobradas,remesasCompradas,salir;
		JDialog dialog = new JDialog();
		JPanel panelIzquierdo = new JPanel();
		panelIzquierdo.setLayout(new GridLayout(2,2,3,3));
		JPanel panelDerecho = new JPanel();
		panelDerecho.setLayout(new GridLayout(2,2,3,3));
		JPanel panelAbajo = new JPanel();
		JPanel arriba = new JPanel();
		JLabel label2 = new JLabel("Panel de Cliente");
		label2.setFont(new Font("Tahoma",0,35));
		arriba.add(label2);
		remesasCobradas = new JButton("Remesas Cobradas");
		remesasCobradas.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
			}
			
		});
		remesasCompradas = new JButton("Remesas Compradas");
		remesasCompradas.addActionListener(new ActionListener(){

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
		panelIzquierdo.add(remesasCobradas);
		panelDerecho.add(salir);
		panelIzquierdo.setPreferredSize(new Dimension(300,0));
		panelDerecho.setPreferredSize(new Dimension(300,0));
		panelIzquierdo.add(remesasCompradas);
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
			text.setText("nombre;"+",dpi;"+",fecha;"+",username;"+",contraseña;");
			panel.add(text,BorderLayout.CENTER);
			panel.add(aceptar = new JButton("Aceptar"),BorderLayout.WEST);
			aceptar.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					String nombre,dpi,username,contraseña,fecha1;
					Date fecha;
						String uno =text.getText();
						String[] parts = uno.split(";");
						System.out.println(parts.length);
						nombre = parts[0];
						dpi = parts[1];
						fecha1 = parts[2];
						username = parts[3];
						contraseña = parts[4];	
						System.out.println(nombre+dpi+fecha1+username+contraseña);
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
	 public static Date convertirFecha(String fecha)
	    {
	        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	        Date fechaDate = null;
	        try {
	            fechaDate = formato.parse(fecha);
	        } 
	        catch (ParseException ex) 
	        {
	            System.out.println(ex);
	        }
	        return fechaDate;
	    }
	public void cargarUsuarios(){
		usuarioList[cont]=new Usuario("Edson","Administrador","201701029","123",null);
		cont++;
		/*usuarioList[1]=new Usuario("Arma","Operador","201701029","125",null);
		usuarioList[2]=new Usuario("Lucia","Cliente","201701029","126",null);*/
	}
	public void recorrerLista(){
		for(int i=0;i<cont;i++){
				System.out.println(usuarioList[i].getNombre());
		}
	}
	public void recorrerListaDos(){
		for(int i=0;i<cont2;i++){
				System.out.println(listaSolicitudes[i].getNombre());
		}
	}
}
