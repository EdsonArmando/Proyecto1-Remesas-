package Vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Modelos.Remesa;
import Modelos.Usuario;

public class RemesasVista {
	boolean cobrada=false;
	static boolean cobrada2=false;
	static Login lg = new Login();
	static Usuario[] listaUsuario=lg.getUsuarioList();
	static int contReme=0;
	static int id=1;
	static int cont =0;
	static Remesa[] listaRemesa = new Remesa[50];
	JTextField idRemesa=null;
	JTextField identificadorRemitente=null,identificadorBeneficiario=null,montoEnviar;
	public void ventasOperador(){
		cont = lg.getCont();
		JPanel arriba = new JPanel();
		JDialog dialogUsuario = new JDialog();
		JButton agregar,cancelar;
		JPanel panelUsuario = new JPanel();
		panelUsuario.setLayout(null);
		JLabel identificadorRemitentes,identificadorBeneficiarios,montoEnviars;
		identificadorRemitentes = new JLabel("Identificador Remitente: ");
		identificadorRemitentes.setBounds(10,20,150,20);
		identificadorBeneficiarios = new JLabel("Indentificadro beneficiario: ");
		identificadorBeneficiarios.setBounds(10,50,150,20);
		montoEnviars = new JLabel("Monto a Enviar: ");
		montoEnviars.setBounds(10,80,95,20);
		identificadorRemitente = new JTextField();
		identificadorRemitente.setBounds(150,20,80,20);
		identificadorBeneficiario = new JTextField();
		identificadorBeneficiario.setBounds(150, 50, 80, 20);
		montoEnviar = new JTextField();
		montoEnviar.setBounds(150,80,80,20);
		agregar = new JButton("Aceptar");
		agregar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String idRemi=null,idBene=null;
				double monto=0;
				double costo=0;
				boolean remi=false,bene=false;
				Date fechaPago=null;
				Date fechaVenta = new Date();
				idRemi = identificadorRemitente.getText();
				idBene = identificadorBeneficiario.getText();
				monto= Double.parseDouble(montoEnviar.getText());
				costo=(monto*0.1)+monto;
				remi = verificarRemitente(idRemi);
				bene = verificarBeneficiario(idBene);
				if(idRemi.equals(idBene)){
					JOptionPane.showMessageDialog(null,"No puede introducir dos pasaportes iguales");
				}else if(idRemi!=idBene){
					if(remi&&bene==true){
						 if (JOptionPane.showConfirmDialog(null, "Se realizara la venta, ¿desea continuar?",
							        "Realizar Venta", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
								ventaRemesa(idRemi,idBene,monto,fechaVenta,costo,cobrada,fechaPago);
								contReme++;
								id++;
								recorrerListaRemesas(idBene);
						 }
							        
					}else if(remi&&bene==false){
						JOptionPane.showMessageDialog(null,"Los pasaportes son inválidos");
					}
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
		JLabel label2 = new JLabel("Venta de remesas");
	    label2.setFont(new Font("Tahoma",0,35));
	    arriba.add(label2);
	    dialogUsuario.add(arriba, BorderLayout.NORTH);
		agregar.setBounds(10,110,95,20);
		cancelar.setBounds(105,110,80,20);
		panelUsuario.add(identificadorRemitente);
		panelUsuario.add(cancelar);
		panelUsuario.add(identificadorBeneficiario);
		panelUsuario.add(montoEnviar);
		panelUsuario.add(identificadorRemitentes);
		panelUsuario.add(identificadorBeneficiarios);
		panelUsuario.add(montoEnviars);
		panelUsuario.add(agregar);
		dialogUsuario.add(panelUsuario);
		dialogUsuario.setLocationRelativeTo(null);
		dialogUsuario.setSize(400,400);
		dialogUsuario.setVisible(true);
	
	}
	public boolean verificarRemitente(String idRemitente){
		boolean verificar = false;
		for(int i=0;i<cont;i++){
			if(listaUsuario[i].getIdentificador().equals(idRemitente)){
					verificar = true;
			}
		}
		return verificar;
	}
	public static void ventaRemesa(String remitente, String beneficiario, double montoEnviar, Date fechaVenta,double costo,boolean cobrada,Date fechaPago){
		listaRemesa[contReme]=new Remesa(id,remitente,beneficiario,montoEnviar,fechaVenta,costo,cobrada,fechaPago);
	}
	public boolean verificarBeneficiario(String idBeneficiario){
		boolean verificar = false;
		for(int i=0;i<cont;i++){
				if(listaUsuario[i].getIdentificador().equals(idBeneficiario)){
					verificar = true;
				}
		}
		return verificar;
	}
	public static boolean verificarRemesa(int idRemesa){
		boolean ambas = false;
		for(int i=0;i<contReme;i++){
			if(listaRemesa[i].getId()==idRemesa){
				ambas = true;
			}
	}
		return ambas;
	}
	public static void consultaRemesa(){
		JPanel arriba = new JPanel();
		JFrame uno=new JFrame();
		JButton agregar;
		JLabel usuario;
		JComboBox id;
		  String[] columns = {"Id", "Emisario", "Beneficiario","Estado"};
		    Object [][]data = new Object[contReme][4];
		        for(int i=0;i<contReme;i++){
					try{
						data[i][0]=listaRemesa[i].getId();
						data[i][1]=listaRemesa[i].getRemitente();
						data[i][2]=listaRemesa[i].getBeneficiario();
						if(listaRemesa[i].isCobrada()==false){
							data[i][3]="Sin cancelar";
						}else if(listaRemesa[i].isCobrada()==true){
							data[i][3]="pagada";
						}
						
					}catch(NullPointerException e){
						System.out.print("NullPointerException caught");
					}
				}
		
	    JTable table = new JTable(data, columns);
	    JScrollPane scrollPane = new JScrollPane(table);
	    uno.add(scrollPane, BorderLayout.CENTER);
	    JLabel label2 = new JLabel("Consulta de Remesas");
	    label2.setFont(new Font("Tahoma",0,35));
	    arriba.add(label2);
	    table.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				int row=table.rowAtPoint(e.getPoint());

				int col= table.columnAtPoint(e.getPoint());
				String identificador = table.getValueAt(row,col).toString();
				
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
	    uno.add(arriba, BorderLayout.NORTH);
	    uno.setSize(600, 400);
	    uno.setLocationRelativeTo(null);
	    uno.setVisible(true);
	}
	public void recorrerListaRemesas(String idBene){
		for(int j=0;j<cont;j++){
			for(int i=0;i<contReme;i++){
				if(listaUsuario[j].getIdentificador().equals(idBene)){
					if(listaRemesa[i].getBeneficiario().equals(idBene)){
						System.out.println("El id de la remesa es de : "+listaRemesa[i].getId());
						System.out.println("El beneficiario es: "+listaUsuario[j].getNombre());
						System.out.println("La cantidad que puede cobrar es de : "+listaRemesa[i].getMontoEnviar());
					}
				}	
			}
		}
		
	}
	public void cobrarRemesa(String nombre){
		JPanel arriba = new JPanel();
		JDialog dialogUsuario = new JDialog();
		JButton agregar,cancelar;
		JPanel panelUsuario = new JPanel();
		panelUsuario.setLayout(null);
		JLabel idRemesas,identificadorBeneficiarios;
		idRemesas = new JLabel("Id Remesa: ");
		idRemesas.setBounds(10,20,150,20);
		identificadorBeneficiarios = new JLabel("Indentificadro beneficiario: ");
		identificadorBeneficiarios.setBounds(10,50,150,20);
		idRemesa = new JTextField();
		idRemesa.setBounds(150,20,80,20);
		identificadorBeneficiario = new JTextField();
		identificadorBeneficiario.setBounds(150, 50, 80, 20);
		agregar = new JButton("Aceptar");
		agregar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean id=false,beneficiario=false,disponible=false;
				String idBene=null;
				int idReme=0;
				Date fechaPago=null;
				Date fechaVenta = new Date();
				idReme = Integer.parseInt(idRemesa.getText());
				idBene = identificadorBeneficiario.getText();
				beneficiario= verificarBeneficiario(idBene);
				id= verificarRemesa(idReme);
				disponible=verificarDisponibilidad(idReme);
				System.out.println(beneficiario);
				System.out.println(id);
				System.out.println(disponible);
				if(disponible==true){
					JOptionPane.showMessageDialog(null,"Esta remesa ya fue cobrada");
				}else if(disponible==false){
					if(beneficiario==true&&id==true&&disponible==false){
						cobrarRemesa(idReme);
						JOptionPane.showMessageDialog(null,"Pago de remesa exitosa");
					}
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
		JLabel label2 = new JLabel("Venta de remesas");
	    label2.setFont(new Font("Tahoma",0,35));
	    arriba.add(label2);
	    dialogUsuario.add(arriba, BorderLayout.NORTH);
		agregar.setBounds(10,80,95,20);
		cancelar.setBounds(150,80,80,20);
		panelUsuario.add(idRemesa);
		panelUsuario.add(cancelar);
		panelUsuario.add(identificadorBeneficiario);
		panelUsuario.add(identificadorBeneficiarios);
		panelUsuario.add(idRemesas);
		panelUsuario.add(agregar);
		dialogUsuario.add(panelUsuario);
		dialogUsuario.setLocationRelativeTo(null);
		dialogUsuario.setSize(400,400);
		dialogUsuario.setVisible(true);
	}
	public void cobrarRemesa(int idRemesa){
		for(int i=0;i<contReme;i++){
			if(listaRemesa[i].getId()==idRemesa){
				listaRemesa[i].setCobrada(true);;
			}
		}
	}
	public void recorrerLista(){
		for(int i=0;i<cont;i++){
			System.out.println(listaUsuario[i].getNombre());
			System.out.println(listaUsuario[i].getIdentificador());
	}
	}
	public static boolean verificarDisponibilidad(int idRemesa){
		boolean disponibilidad=false;
		for(int i=0;i<contReme;i++){
		if(listaRemesa[i].getId()==idRemesa){
			disponibilidad = listaRemesa[i].isCobrada();
		}
	}
		return disponibilidad;
	}
}
