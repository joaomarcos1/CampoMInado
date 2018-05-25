package br.ufpi.poo.campominado.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Position;

import br.ufpi.poo.campominado.enums.Acao;
import br.ufpi.poo.campominado.enums.EstadoZona;
import br.ufpi.poo.campominado.exceptions.BombaExplodiuEssipitiu;
import br.ufpi.poo.campominado.exceptions.PosicaoInvalidaException;

/**
 * @author alcemirsantos
 *
 */
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

	public void executa(Jogada umaJogada) throws BombaExplodiuEssipitiu {
		jogadas.add(umaJogada);
		// TODO implementar a execução da jogada
	}

	public List<Jogada> getJogadas() {
		return this.jogadas;
	}

	public boolean validar(Jogada nova) throws PosicaoInvalidaException {
		return eCoordenadaValida(nova.getCoordenada())
				&& eAcaoValida(nova.getAcao());
	}

	private boolean eCoordenadaValida(Coordenada umaCoordenada)
			throws PosicaoInvalidaException {
		return checarUsada(umaCoordenada) && checarLimites(umaCoordenada);
	}

	private boolean eAcaoValida(Acao acao) {
		// TODO investigar depois de marcar pode.
		return false;
	}

	private boolean checarUsada(Coordenada umaCoordenada)
			throws PosicaoInvalidaException {
		if (this.tabuleiro.getEstado(umaCoordenada) == EstadoZona.VAZIO
				|| this.tabuleiro.getEstado(umaCoordenada) == EstadoZona.MARCADO)
			return true;
		else
			throw new PosicaoInvalidaException("A posição " + umaCoordenada
					+ " já foi usada!");
	}

	private boolean checarLimites(Coordenada umaCoordenada)
			throws PosicaoInvalidaException {
		if (umaCoordenada.getX() >= 0
				&& umaCoordenada.getX() <= this.tabuleiro.getComprimento()
				&& umaCoordenada.getY() >= 0
				&& umaCoordenada.getY() <= this.tabuleiro.getLargura())
			return true;

		else
			throw new PosicaoInvalidaException(
					"A coordenada está fora dos limites!\n "
							+ "-> Escolha um número entre 0 e"
							+ this.tabuleiro.getComprimento() + ", inclusos.");

	}

	public boolean possivelJogar() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean temVencedor() {
		if(getNumZonasLivres()>getNumBombas())
			return false;
		return true;
	}

	private int getNumBombas() {
		// TODO Auto-generated method stub
		return 0;
	}

	private int getNumZonasLivres() {
		// TODO Auto-generated method stub
		return 0;
	}
}
