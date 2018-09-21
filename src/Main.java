import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
		JButton boton1 = new JButton("About");
		JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel2 = new JPanel();
		ImageIcon logo = new ImageIcon("Image.jpg");
		ImageIcon logo2 = new ImageIcon("Image01.jpg");
		JLabel image = new JLabel(logo);
		JLabel image2 = new JLabel(logo2);
		image.setSize(350,350);
		image2.setSize(75,75);
		Icon icono = new ImageIcon(logo.getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_DEFAULT));
		Icon icono2 = new ImageIcon(logo2.getImage().getScaledInstance(image2.getWidth(), image2.getHeight(), Image.SCALE_DEFAULT));
		image.setIcon(icono);
		image2.setIcon(icono2);
		JFrame inicio;
		JButton login;
		JPanel panel1;
		JLabel titulo;
		panel2.add(image);
		panel2.setBackground(Color.WHITE);
		panel2.setLayout(new GridBagLayout());
		panel2.add(image);
		panel2.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		panel2.setPreferredSize(new Dimension(350,200));
		panel1 = new JPanel();
		titulo = new JLabel("Envio de remesas");
		titulo.setFont(new Font("Tahoma",0,35));
		panel1.setBackground(Color.WHITE);
		inicio = new JFrame("Remesas");
		login = new JButton("Login");
		panel1.setPreferredSize(new Dimension(0,100));
		panel1.setLayout(new FlowLayout());
		titulo.setHorizontalAlignment(JLabel.CENTER);
		titulo.setVerticalAlignment(JLabel.CENTER);
		inicio.setLayout(new BorderLayout());
		inicio.setSize(550, 600);
		panel1.add(titulo);
		panel1.add(login);
		panel1.add(image2);
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Login log = new Login();
				log.formulario();
				log.recorrerLista();
			}
			
		});
		panel4.add(boton1);
		panel4.setBackground(Color.black);
		inicio.add(panel4, BorderLayout.SOUTH);
		inicio.add(panel2, BorderLayout.CENTER);
		inicio.add(panel1, BorderLayout.NORTH);
		inicio.setVisible(true);
		inicio.setLocationRelativeTo(null);
	}
}