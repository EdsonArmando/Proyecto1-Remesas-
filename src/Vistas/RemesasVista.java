package Vistas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Modelos.Remesa;
import Modelos.Usuario;

public class RemesasVista {
	boolean cobrada=false;
	static Login lg = new Login();
	static Usuario[] listaUsuario=lg.getUsuarioList();
	static int contReme=0;
	static int id=1;
	static int cont =0;
	static Remesa[] listaRemesa = new Remesa[50];
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
		identificadorRemitentes.setBounds(10,20,80,20);
		identificadorBeneficiarios = new JLabel("Indentificadro beneficiario: ");
		identificadorBeneficiarios.setBounds(10,50,95,20);
		montoEnviars = new JLabel("Monto a Enviar: ");
		montoEnviars.setBounds(10,80,95,20);
		identificadorRemitente = new JTextField();
		identificadorRemitente.setBounds(105,20,80,20);
		identificadorBeneficiario = new JTextField();
		identificadorBeneficiario.setBounds(105, 50, 80, 20);
		montoEnviar = new JTextField();
		montoEnviar.setBounds(105,80,80,20);
		agregar = new JButton("Aceptar");
		agregar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String idRemi=null,idBene=null;
				double monto=0;
				double costo=0;
				boolean remi=false,bene=false;
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
								ventaRemesa(idRemi,idBene,monto,fechaVenta,costo,cobrada);
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
		JLabel label2 = new JLabel("Formulario de Usuarios");
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
	
	}public boolean verificarRemitente(String idRemitente){
		boolean verificar = false;
		for(int i=0;i<cont;i++){
			if(listaUsuario[i].getIdentificador().equals(idRemitente)){
					verificar = true;
			}
		}
		return verificar;
	}
	public static void ventaRemesa(String remitente, String beneficiario, double montoEnviar, Date fechaVenta,double costo,boolean cobrada){
		listaRemesa[contReme]=new Remesa(id,remitente,beneficiario,montoEnviar,fechaVenta,costo,cobrada);
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
	public void recorrerLista(){
		for(int i=0;i<cont;i++){
			System.out.println(listaUsuario[i].getNombre());
			System.out.println(listaUsuario[i].getIdentificador());
	}
	}
}
