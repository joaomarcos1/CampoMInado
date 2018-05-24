package br.ufpi.poo.campominado;

import br.ufpi.poo.campominado.model.Tabuleiro;

public class CampoMinado {

	private Tabuleiro tabuleiro;

	public void inicia() {
		this.tabuleiro = new Tabuleiro();
	}

	public Tabuleiro getTabuleiro() {
		return this.tabuleiro;
	}

	public void setTabuleiro(Tabuleiro umTabuleiro) {
		this.tabuleiro = umTabuleiro;
	}

}
