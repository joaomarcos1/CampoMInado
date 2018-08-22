package br.ufpi.poo.campominado.model;

import java.util.ArrayList;
import java.util.List;

import br.ufpi.poo.campominado.enums.Acao;
import br.ufpi.poo.campominado.enums.EstadoZona;
import br.ufpi.poo.campominado.exceptions.AcaoInvalidaException;
import br.ufpi.poo.campominado.exceptions.BombaExplodiuException;
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

	public void executa(Jogada umaJogada) throws BombaExplodiuException,
			PosicaoInvalidaException, AcaoInvalidaException {
		if (validar(umaJogada)) {
			this.jogadas.add(umaJogada);
			resolve(umaJogada);
		}
	}

	private void resolve(Jogada umaJogada) throws BombaExplodiuException {
		switch (umaJogada.getAcao()) {
		case MARCAR:
			this.tabuleiro.atualizaZona(umaJogada.getCoordenada(),
					EstadoZona.MARCADO);
			break;
		case INVESTIGAR:
		default:
			this.tabuleiro.atualizaZona(umaJogada.getCoordenada(),
					EstadoZona.ABERTO);
			break;
		}
	}

	public List<Jogada> getJogadas() {
		return this.jogadas;
	}

	public boolean validar(Jogada nova) throws PosicaoInvalidaException, AcaoInvalidaException {
		return eCoordenadaValida(nova.getCoordenada())
				&& eAcaoValida(nova);
	}

	private boolean eCoordenadaValida(Coordenada umaCoordenada)
			throws PosicaoInvalidaException {
		return checarUsada(umaCoordenada) && checarLimites(umaCoordenada);
	}

	private boolean eAcaoValida(Jogada j) throws AcaoInvalidaException, PosicaoInvalidaException {
		EstadoZona atual = this.tabuleiro.getEstado(j.getCoordenada());
		switch (atual) {
		case ABERTO:
			throw new AcaoInvalidaException("Você já INVESTIGOU essa zona.");
		case MARCADO:
			if(j.getAcao() == Acao.MARCAR)
				throw new AcaoInvalidaException("Você já MARCOU essa zona, mas pode INVESTIGAR caso se arrependeu.");
		case VAZIO:
		default:
			return true;
		}
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

	/**
	 * Sim, sob duas condições:
	 * - qtde de bombas = marcações de bombas;
	 * - não tem posições vazias.
	 * 
	 * @return
	 */
	public boolean temVencedor() {
		if (this.tabuleiro.getQtdeZonas(EstadoZona.MARCADO) == this.tabuleiro
				.getQtdeBombas() && this.tabuleiro.getQtdeZonasLivres() == 0)
			return true;
		return false;
	}

}
