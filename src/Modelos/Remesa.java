package Modelos;

import java.util.Date;

public class Remesa {
	int id;
	String remitente;
	String beneficiario;
	double montoEnviar=0.0;
	Date fechaVenta;
	double costo=0.0;
	boolean cobrada=false;
	Date fechaPago;
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public String getBeneficiario() {
		return beneficiario;
	}
	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}
	public double getMontoEnviar() {
		return montoEnviar;
	}
	public void setMontoEnviar(double montoEnviar) {
		this.montoEnviar = montoEnviar;
	}
	public Date getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
	
	public boolean isCobrada() {
		return cobrada;
	}
	public void setCobrada(boolean cobrada) {
		this.cobrada = cobrada;
	}
	public Remesa(int id, String remitente, String beneficiario, double montoEnviar, Date fechaVenta, double costo,boolean cobrada,Date fechaPago) {
		super();
		this.id = id;
		this.remitente = remitente;
		this.beneficiario = beneficiario;
		this.montoEnviar = montoEnviar;
		this.fechaVenta = fechaVenta;
		this.costo = costo;
		this.cobrada=cobrada;
		this.fechaPago = fechaPago;
	}
	public Remesa() {
		super();
	}
	
}
