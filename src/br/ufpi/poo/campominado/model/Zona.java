package br.ufpi.poo.campominado.model;

import br.ufpi.poo.campominado.enums.EstadoZona;

/**
 * @author alcemirsantos
 *
 */
public class Zona {

	private EstadoZona estado;
	private Integer valor;
	private boolean bomba;

	public Zona() {
		this.valor = null;
		this.estado = EstadoZona.VAZIO;
		this.bomba = false;
	}

	public EstadoZona getEstadoZona() {
		return estado;
	}

	public String valorToString() {
		switch (this.estado) {
		case ABERTO:
			if (this.valor == Integer.MAX_VALUE)
				return "[!]";
			else if (this.valor > 0 && this.valor < Integer.MAX_VALUE)
				return "[" + this.valor + "]";
			else
				return "[ ]";
		case MARCADO:
			return "[B]";
		case VAZIO:
		default:
			return "[#]";
		}
	}

	@Override
	public String toString() {
		return "[" + this.estado + ", " + this.valor + "]";
	}

	public void setValor(int umValor) {
		this.valor = umValor;
	}

	public void setEstadoZona(EstadoZona umEstado) {
		this.estado = umEstado;
	}

	public void setBomba(boolean b) {
		this.bomba = b;
	}

	public boolean temBomba() {
		return this.bomba;
	}

}
