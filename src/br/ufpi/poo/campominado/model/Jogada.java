package br.ufpi.poo.campominado.model;

import java.util.Objects;

import br.ufpi.poo.campominado.enums.Acao;

public class Jogada {

	private Coordenada coordenada;
	private Acao acao;

	public Jogada(Acao umaAcao, Coordenada umaCoordenada) {
		setCoordenada(umaCoordenada);
		setAcao(umaAcao);
	}

	private Coordenada getCoordenada() {
		return this.coordenada;
	}

	private void setCoordenada(Coordenada umaCoordenada) {
		this.coordenada = umaCoordenada;
	}

	private Acao getAcao() {
		return this.acao;
	}

	private void setAcao(Acao umaAcao) {
		this.acao = umaAcao;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		Jogada other = null;
		if (o instanceof Jogada)
			other = (Jogada) o;
		if (this.coordenada.equals(other.getCoordenada())
				&& this.acao == other.getAcao())
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.acao, this.coordenada);
	}

	@Override
	public String toString() {
		return this.acao + " " + this.coordenada;
	}
}
