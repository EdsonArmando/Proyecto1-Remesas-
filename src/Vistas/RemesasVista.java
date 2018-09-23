package Vistas;

import java.awt.BorderLayout;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
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
	boolean cobrada = false;
	static boolean cobrada2 = false;
	static Login lg = new Login();
	static Usuario[] listaUsuario = lg.getUsuarioList();
	static int contReme = 0;
	static int id = 1;
	static int cont = 0;
	static Remesa[] listaRemesa = new Remesa[50];
	JTextField idRemesa = null;
	double limiteCobro = 0.0;
	double tipoCambio = 0.0;
	JTextField identificadorRemitente = null, identificadorBeneficiario = null, montoEnviar;

	public void ventasOperador() {
		cont = lg.getCont();
		JPanel arriba = new JPanel();
		JDialog dialogUsuario = new JDialog();
		JButton agregar, cancelar;
		JPanel panelUsuario = new JPanel();
		panelUsuario.setLayout(null);
		JLabel identificadorRemitentes, identificadorBeneficiarios, montoEnviars;
		identificadorRemitentes = new JLabel("Identificador Remitente: ");
		identificadorRemitentes.setBounds(10, 20, 150, 20);
		identificadorBeneficiarios = new JLabel("Indentificadro beneficiario: ");
		identificadorBeneficiarios.setBounds(10, 50, 150, 20);
		montoEnviars = new JLabel("Monto a Enviar: ");
		montoEnviars.setBounds(10, 80, 95, 20);
		identificadorRemitente = new JTextField();
		identificadorRemitente.setBounds(150, 20, 80, 20);
		identificadorBeneficiario = new JTextField();
		identificadorBeneficiario.setBounds(150, 50, 80, 20);
		montoEnviar = new JTextField();
		montoEnviar.setBounds(150, 80, 80, 20);
		agregar = new JButton("Aceptar");
		agregar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double porcentajess = lg.getPorcentajeCambio();
				double limiteVentas = lg.getLimiteCompra();
				String idRemi = null, idBene = null;
				double monto = 0;
				double costo = 0;
				boolean remi = false, bene = false;
				Date fechaPago = null;
				Date fechaVenta = new Date();
				idRemi = identificadorRemitente.getText();
				idBene = identificadorBeneficiario.getText();
				monto = Double.parseDouble(montoEnviar.getText());
				costo = (monto * porcentajess) + monto;
				remi = verificarRemitente(idRemi);
				bene = verificarBeneficiario(idBene);
				double suma = retornarSumaPago(idRemi);
				if ((suma + monto) <= limiteVentas) {
					if (idRemi.equals(idBene)) {
						JOptionPane.showMessageDialog(null, "No puede introducir dos pasaportes iguales");
					} else if (idRemi != idBene) {
						if (remi && bene == true) {
							if (JOptionPane.showConfirmDialog(null,
									"El costo de la remesa en $ es: " + costo + ", ¿desea continuar?", "Realizar Venta",
									JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
								ventaRemesa(idRemi, idBene, monto, fechaVenta, costo, cobrada, fechaPago);
								contReme++;
								id++;
								recorrerListaRemesas(idBene);
							}

						} else {
							JOptionPane.showMessageDialog(null, "Los pasaportes son inválidos");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "No puede enviar más de: " + limiteVentas);
				}

			}

		});
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dialogUsuario.dispose();
			}

		});
		JLabel label2 = new JLabel("Venta de remesas");
		label2.setFont(new Font("Tahoma", 0, 35));
		arriba.add(label2);
		dialogUsuario.add(arriba, BorderLayout.NORTH);
		agregar.setBounds(10, 110, 95, 20);
		cancelar.setBounds(105, 110, 80, 20);
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
		dialogUsuario.setSize(400, 400);
		dialogUsuario.setVisible(true);

	}

	public boolean verificarRemitente(String idRemitente) {
		boolean verificar = false;
		for (int i = 0; i < cont; i++) {
			if (listaUsuario[i].getRol().equals("Cliente")) {
				if (listaUsuario[i].getIdentificador().equals(idRemitente)) {
					verificar = true;
				}
			}
		}
		return verificar;
	}

	public static void ventaRemesa(String remitente, String beneficiario, double montoEnviar, Date fechaVenta,
			double costo, boolean cobrada, Date fechaPago) {
		listaRemesa[contReme] = new Remesa(id, remitente, beneficiario, montoEnviar, fechaVenta, costo, cobrada,
				fechaPago);
	}

	public static void consultaRemesa() {
		DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		JPanel arriba = new JPanel();
		JFrame uno = new JFrame();
		String[] columns = { "Id", "Emisario", "Beneficiario", "Fecha Venta", "Hora Venta", "Estado", "Fecha Cobro",
				"Hora Cobro", "Operador Aprobo" };
		Object[][] data = new Object[contReme][9];
		for (int i = 0; i < contReme; i++) {

			try {
				data[i][0] = listaRemesa[i].getId();
				data[i][1] = listaRemesa[i].getRemitente();
				data[i][2] = listaRemesa[i].getBeneficiario();
				data[i][3] = dateFormat.format(listaRemesa[i].getFechaVenta());
				data[i][4] = hourFormat.format(listaRemesa[i].getFechaVenta());
				if (listaRemesa[i].isCobrada() == false) {
					data[i][5] = "Sin cancelar";
				} else if (listaRemesa[i].isCobrada() == true) {
					data[i][5] = "pagada";
				}
				data[i][6] = dateFormat.format(listaRemesa[i].getFechaPago());
				data[i][7] = hourFormat.format(listaRemesa[i].getFechaPago());
				data[i][8] = listaRemesa[i].getUserAprobo();
			} catch (NullPointerException e) {
				System.out.print("NullPointerException caught");
			}
		}
		JTable table = new JTable(data, columns);
		JScrollPane scrollPane = new JScrollPane(table);
		uno.add(scrollPane, BorderLayout.CENTER);
		JLabel label2 = new JLabel("Consulta de Remesas");
		label2.setFont(new Font("Tahoma", 0, 35));
		arriba.add(label2);
		uno.add(arriba, BorderLayout.NORTH);
		uno.setSize(600, 400);
		uno.setLocationRelativeTo(null);
		uno.setVisible(true);
	}

	public void recorrerListaRemesas(String idBene) {
		for (int j = 0; j < cont; j++) {
			for (int i = 0; i < contReme; i++) {
				if (listaUsuario[j].getIdentificador().equals(idBene)) {
					if (listaRemesa[i].getBeneficiario().equals(idBene)) {
						System.out.println("El id de la remesa es de : " + listaRemesa[i].getId());
						System.out.println("El beneficiario es: " + listaUsuario[j].getNombre());
						System.out.println("La cantidad que puede cobrar es de : " + listaRemesa[i].getMontoEnviar());
					}
				}
			}
		}

	}

	public void consultaRemesaClienteComprada(String nombre) {
		DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		JPanel arriba = new JPanel();
		String idRemitent = idRemitente(nombre);
		JFrame uno = new JFrame();
		String[] columns = { "Id", "Emisario", "Beneficiario", "Fecha Venta", "Hora Venta", "Estado" };
		Object[][] data = new Object[contReme][6];
		for (int i = 0; i < contReme; i++) {
			if (listaRemesa[i].getRemitente().equals(idRemitent)) {
				try {
					data[i][0] = listaRemesa[i].getId();
					data[i][1] = listaRemesa[i].getRemitente();
					data[i][2] = listaRemesa[i].getBeneficiario();
					data[i][3] = dateFormat.format(listaRemesa[i].getFechaVenta());
					data[i][4] = hourFormat.format(listaRemesa[i].getFechaVenta());
					if (listaRemesa[i].isCobrada() == false) {
						data[i][5] = "Sin cancelar";
					} else if (listaRemesa[i].isCobrada() == true) {
						data[i][5] = "pagada";
					}

				} catch (NullPointerException e) {
					System.out.print("NullPointerException caught");
				}
			}
		}

		JTable table = new JTable(data, columns);
		JScrollPane scrollPane = new JScrollPane(table);
		uno.add(scrollPane, BorderLayout.CENTER);
		JLabel label2 = new JLabel("Consulta de Remesas");
		label2.setFont(new Font("Tahoma", 0, 35));
		arriba.add(label2);
		uno.add(arriba, BorderLayout.NORTH);
		uno.setSize(600, 400);
		uno.setLocationRelativeTo(null);
		uno.setVisible(true);
	}

	public void consultaRemesaClienteCobradas(String nombre) {
		DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		JPanel arriba = new JPanel();
		String idRemitent = idRemitente(nombre);
		JFrame uno = new JFrame();
		String[] columns = { "Id", "Emisario", "Beneficiario", "Fecha Venta", "Hora Venta", "Estado", "Fecha Cobro",
				"Hora Cobro" };
		Object[][] data = new Object[contReme][8];
		for (int i = 0; i < contReme; i++) {
			if (listaRemesa[i].getBeneficiario().equals(idRemitent)) {
				try {
					data[i][0] = listaRemesa[i].getId();
					data[i][1] = listaRemesa[i].getRemitente();
					data[i][2] = listaRemesa[i].getBeneficiario();
					data[i][3] = dateFormat.format(listaRemesa[i].getFechaVenta());
					data[i][4] = hourFormat.format(listaRemesa[i].getFechaVenta());
					if (listaRemesa[i].isCobrada() == false) {
						data[i][5] = "Sin cancelar";
					} else if (listaRemesa[i].isCobrada() == true) {
						data[i][5] = "pagada";
					}
					data[i][6] = dateFormat.format(listaRemesa[i].getFechaPago());
					data[i][7] = hourFormat.format(listaRemesa[i].getFechaPago());
				} catch (NullPointerException e) {
					System.out.print("NullPointerException caught");
				}
			}
		}

		JTable table = new JTable(data, columns);
		JScrollPane scrollPane = new JScrollPane(table);
		uno.add(scrollPane, BorderLayout.CENTER);
		JLabel label2 = new JLabel("Consulta de Remesas");
		label2.setFont(new Font("Tahoma", 0, 35));
		arriba.add(label2);
		uno.add(arriba, BorderLayout.NORTH);
		uno.setSize(600, 400);
		uno.setLocationRelativeTo(null);
		uno.setVisible(true);
	}

	public void cobrarRemesa(String nombre) {
		JPanel arriba = new JPanel();
		JDialog dialogUsuario = new JDialog();
		JButton agregar, cancelar;
		JPanel panelUsuario = new JPanel();
		panelUsuario.setLayout(null);
		JLabel idRemesas, identificadorBeneficiarios;
		idRemesas = new JLabel("Id Remesa: ");
		idRemesas.setBounds(10, 20, 150, 20);
		identificadorBeneficiarios = new JLabel("Indentificadro beneficiario: ");
		identificadorBeneficiarios.setBounds(10, 50, 150, 20);
		idRemesa = new JTextField();
		idRemesa.setBounds(150, 20, 80, 20);
		identificadorBeneficiario = new JTextField();
		identificadorBeneficiario.setBounds(150, 50, 80, 20);
		agregar = new JButton("Aceptar");
		agregar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				limiteCobro = lg.getLimitePago();
				tipoCambio = lg.getTipoCambio();
				boolean id = false, beneficiario = false, disponible = false;
				String idBene = null;
				int idReme = 0;
				Date fechaVenta = new Date();
				idReme = Integer.parseInt(idRemesa.getText());
				idBene = identificadorBeneficiario.getText();
				beneficiario = verificarBeneficiarioRemesa(idBene);
				id = verificarRemesa(idReme);
				disponible = verificarDisponibilidad(idReme);
				double monto = obtenerMonto(idReme);
				double sumar = retornarSumaCobro(idBene);
				if ((monto + sumar) <= limiteCobro) {
					if (id && beneficiario == true) {
						if (disponible == true) {
							JOptionPane.showMessageDialog(null, "Esta remesa ya fue cobrada");
						} else if (disponible == false) {
							if (beneficiario == true && id == true && disponible == false) {
								if (JOptionPane.showConfirmDialog(null,
										"El Monto a pagar en Q es: " + (monto * tipoCambio)
												+ "\nEl monto a pagar en $ es: " + monto + "\n¿Desea continuar?",
										"Realizar Venta", JOptionPane.WARNING_MESSAGE,
										JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
									cobrarRemesa(idReme, fechaVenta, nombre);
									JOptionPane.showMessageDialog(null, "Pago de remesa exitosa");
								}
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Datos Incorrectos");
					}

				} else {
					JOptionPane.showMessageDialog(null, "No puede cobrar mas de: " + limiteCobro);
				}
			}
		});
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dialogUsuario.dispose();
			}

		});
		JLabel label2 = new JLabel("Pago de remesas");
		label2.setFont(new Font("Tahoma", 0, 35));
		arriba.add(label2);
		dialogUsuario.add(arriba, BorderLayout.NORTH);
		agregar.setBounds(10, 80, 95, 20);
		cancelar.setBounds(150, 80, 80, 20);
		panelUsuario.add(idRemesa);
		panelUsuario.add(cancelar);
		panelUsuario.add(identificadorBeneficiario);
		panelUsuario.add(identificadorBeneficiarios);
		panelUsuario.add(idRemesas);
		panelUsuario.add(agregar);
		dialogUsuario.add(panelUsuario);
		dialogUsuario.setLocationRelativeTo(null);
		dialogUsuario.setSize(400, 400);
		dialogUsuario.setVisible(true);
	}

	public static void Reportes() {
		DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		JPanel arriba = new JPanel();
		JFrame uno = new JFrame();
		String[] columns = { "Total remesas vendidas($)", "Total remesas Pagadas($)","Remesas por pagar", "Ganancia total($)"};
		Object[][] data = {
			    {obtenerSumaRemesasVendidas(), obtenerRemesasPagadas(),
			    	pendientesPorPagar(), gananciaTotal()},
			};
		JTable table = new JTable(data, columns);
		JScrollPane scrollPane = new JScrollPane(table);
		uno.add(scrollPane, BorderLayout.CENTER);
		JLabel label2 = new JLabel("Consulta de Remesas");
		label2.setFont(new Font("Tahoma", 0, 35));
		arriba.add(label2);
		uno.add(arriba, BorderLayout.NORTH);
		uno.setSize(600, 400);
		uno.setLocationRelativeTo(null);
		uno.setVisible(true);
	}
	public  void reportesCliente(String nombre2) {
		DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		JPanel arriba = new JPanel();
		JFrame uno = new JFrame();
		String[] columns = { "Id", "Emisario", "Beneficiario", "Fecha Venta", "Hora Venta", "Estado", "Fecha Cobro",
				"Hora Cobro" };
		Object[][] data = new Object[contReme][8];
		for (int i = 0; i < contReme; i++) {
			if (listaRemesa[i].getBeneficiario().equals(nombre2)||listaRemesa[i].getRemitente().equals(nombre2)) {
					try {
						data[i][0] = listaRemesa[i].getId();
						data[i][1] = listaRemesa[i].getRemitente();
						data[i][2] = listaRemesa[i].getBeneficiario();
						data[i][3] = dateFormat.format(listaRemesa[i].getFechaVenta());
						data[i][4] = hourFormat.format(listaRemesa[i].getFechaVenta());
						if (listaRemesa[i].isCobrada() == false) {
							data[i][5] = "Sin cancelar";
						} else if (listaRemesa[i].isCobrada() == true) {
							data[i][5] = "pagada";
						}
						data[i][6] = dateFormat.format(listaRemesa[i].getFechaPago());
						data[i][7] = hourFormat.format(listaRemesa[i].getFechaPago());
					} catch (NullPointerException e) {
						System.out.print("NullPointerException caught");
					}
				}
			}

		JTable table = new JTable(data, columns);
		JScrollPane scrollPane = new JScrollPane(table);
		uno.add(scrollPane, BorderLayout.CENTER);
		JLabel label2 = new JLabel("Consulta de Remesas");
		label2.setFont(new Font("Tahoma", 0, 35));
		arriba.add(label2);
		uno.add(arriba, BorderLayout.NORTH);
		uno.setSize(600, 400);
		uno.setLocationRelativeTo(null);
		uno.setVisible(true);
	}
	static double obtenerSumaRemesasVendidas(){
		double suma =0;
		for(int i=0;i<contReme;i++){
			suma +=listaRemesa[i].getMontoEnviar();
		}
		return suma;
	}
	static double obtenerRemesasPagadas(){
		double suma =0;
		for(int i=0;i<contReme;i++){
			if(listaRemesa[i].isCobrada()==true){
				suma +=listaRemesa[i].getMontoEnviar();
			}
		}
		return suma;
	}
	static double gananciaTotal(){
		double suma =0;
		for(int i=0;i<contReme;i++){
				suma +=listaRemesa[i].getCosto();
		}
		return suma;
	}
	static int pendientesPorPagar(){
		int suma =0;
		for(int i=0;i<contReme;i++){
			if(listaRemesa[i].isCobrada()==false){
				int myInt = listaRemesa[i].isCobrada() ? 0 : 1;
				suma +=myInt;
			}
		}
		return suma;
	}
	public void cobrarRemesa(int idRemesa, Date fechaPagoss, String userAprobo) {
		for (int i = 0; i < contReme; i++) {
			if (listaRemesa[i].getId() == idRemesa) {
				listaRemesa[i].setCobrada(true);
				listaRemesa[i].setFechaPago(fechaPagoss);
				listaRemesa[i].setUserAprobo(userAprobo);
			}
		}
	}

	public void recorrerLista() {
		for (int i = 0; i < cont; i++) {
			System.out.println(listaUsuario[i].getNombre());
			System.out.println(listaUsuario[i].getIdentificador());
		}
	}

	public String idRemitente(String nombre) {
		String idRemitent = null;
		for (int i = 0; i < cont; i++) {
			if (listaUsuario[i].getNombre().equals(nombre)) {
				idRemitent = listaUsuario[i].getIdentificador();
			}
		}
		return idRemitent;
	}

	public static int retornarSumaCobro(String idBeneficiarioss) {
		int suma = 0;
		for (int i = 0; i < contReme; i++) {
			if (listaRemesa[i].isCobrada() == true) {
				if (listaRemesa[i].getBeneficiario().equals(idBeneficiarioss)) {
					suma += listaRemesa[i].getMontoEnviar();
				}
			}
		}
		return suma;
	}

	public static int retornarSumaPago(String idRemitentess) {
		int suma = 0;
		for (int i = 0; i < contReme; i++) {
			if (listaRemesa[i].getRemitente().equals(idRemitentess)) {
				suma += listaRemesa[i].getMontoEnviar();
			}
		}
		return suma;
	}

	public static boolean verificarDisponibilidad(int idRemesa) {
		boolean disponibilidad = false;
		for (int i = 0; i < contReme; i++) {
			if (listaRemesa[i].getId() == idRemesa) {
				disponibilidad = listaRemesa[i].isCobrada();
			}
		}
		return disponibilidad;
	}

	public boolean verificarBeneficiario(String idBeneficiario) {
		boolean verificar = false;
		for (int i = 0; i < cont; i++) {
			if (listaUsuario[i].getRol().equals("Cliente")) {
				if (listaUsuario[i].getIdentificador().equals(idBeneficiario)) {
					verificar = true;
				}
			}
		}
		return verificar;
	}

	public static boolean verificarRemesa(int idRemesa) {
		boolean ambas = false;
		for (int i = 0; i < contReme; i++) {
			if (listaRemesa[i].getId() == idRemesa) {
				ambas = true;
			}
		}
		return ambas;
	}

	public static double obtenerMonto(int idRemesas) {
		double suma = 0;
		for (int i = 0; i < contReme; i++) {
			if (listaRemesa[i].getId() == idRemesas) {
				suma = listaRemesa[i].getMontoEnviar();
			}
		}
		return suma;
	}

	public boolean verificarBeneficiarioRemesa(String idBeneficiario) {
		boolean verificar = false;
		for (int i = 0; i < contReme; i++) {
			if (listaRemesa[i].getBeneficiario().equals(idBeneficiario)) {
				verificar = true;
			}
		}
		return verificar;
	}
}
