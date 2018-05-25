package br.ufpi.poo.campominado.model;

import br.ufpi.poo.campominado.enums.EstadoZona;
/**
 * @author alcemirsantos
 *
 */
public class Zona {

	private EstadoZona estado;
	private Integer valor;

	public Zona() {
		this.valor = null;
		this.estado = EstadoZona.VAZIO;
	}

	public EstadoZona getEstadoZona() {
		return estado;
	}
	
	@Override
	public String toString(){
		return "["+this.estado+", "+this.valor+"]";
	}

}
