import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Modelos.Usuario;
import Vistas.Login;
public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);		
		Main ma = new Main();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ma.inicio();
	}
	public void inicio() {
		Login log = new Login();
		JFrame inicio;
		JButton login;
		JPanel panel1;
		JLabel titulo;
		panel1 = new JPanel();
		titulo = new JLabel("Envio de remesas");
		titulo.setFont(new Font("Tahoma",0,35));
		inicio = new JFrame("Remesas");
		login = new JButton("Login");
		panel1.setPreferredSize(new Dimension(0,100));
		panel1.setLayout(new FlowLayout());
		titulo.setHorizontalAlignment(JLabel.CENTER);
		titulo.setVerticalAlignment(JLabel.CENTER);
		inicio.setLayout(new BorderLayout());
		inicio.setSize(500, 500);
		panel1.add(titulo);
		panel1.add(login);
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Login log = new Login();
				log.formulario();
				log.crearUsuario("Edson","Administrador","201701029","123",null);
			}
			
		});
		inicio.add(panel1, BorderLayout.NORTH);
		inicio.setVisible(true);
		inicio.setLocationRelativeTo(null);
	}
}