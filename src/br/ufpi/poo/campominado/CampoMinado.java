package br.ufpi.poo.campominado;

import java.util.ArrayList;
import java.util.List;

import br.ufpi.poo.campominado.model.Jogada;
import br.ufpi.poo.campominado.model.Tabuleiro;

public class CampoMinado {

	private Tabuleiro tabuleiro;
	private List<Jogada> jogadas;

	public void reseta() {
		this.tabuleiro = new Tabuleiro();
		this.jogadas = new ArrayList<Jogada>();
	}

	public Tabuleiro getTabuleiro() {
		return this.tabuleiro;
	}

	public void setTabuleiro(Tabuleiro umTabuleiro) {
		this.tabuleiro = umTabuleiro;
	}

	public void executa(Jogada umaJogada) {
		jogadas.add(umaJogada);
	}

	public List<Jogada> getJogadas() {
		return this.jogadas;
	}

}
