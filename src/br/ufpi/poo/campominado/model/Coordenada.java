package br.ufpi.poo.campominado.model;

import java.util.Objects;

public class Coordenada {

	private int x;
	private int y;

	public Coordenada(int x, int y) {
		setX(x);
		setY(y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		Coordenada other = null;
		if (o instanceof Coordenada)
			other = (Coordenada) o;
		if (this.x == other.getX() && this.y == other.getY())
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.x, this.y);
	}

	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
}
