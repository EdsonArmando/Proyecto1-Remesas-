package Modelos;

import java.util.Date;

import javax.swing.JOptionPane;


public class Usuario {
	String nombre;
	String rol;
	String identificador;
	String password;
	Date fechaInicio;
	String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Usuario(String nombre, String rol, String identificador, String password, Date fechaInicio,String username) {
		super();
		this.nombre = nombre;
		this.rol = rol;
		this.identificador = identificador;
		this.password = password;
		this.fechaInicio = fechaInicio;
		this.username = username;
	}
	public Usuario() {
		super();
	}
	public void cargarUsuario(){
	
	}
	
	
}
