package br.ufpi.poo.campominado.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import br.ufpi.poo.campominado.enums.EstadoZona;
/**
 * @author alcemirsantos
 *
 */
public class Tabuleiro {

	int comprimento;
	int largura;
	private Map<Coordenada, Zona> mapa;

	public Tabuleiro() {
		this.comprimento = 5;
		this.largura = 5;
		this.mapa = new HashMap<Coordenada, Zona>();
		inicia();
	}

	private void inicia() {
		for (int x = 0; x < this.comprimento; x++) {
			for (int y = 0; y < this.largura; y++) {

				this.mapa.put(new Coordenada(x, y), new Zona());
			}
		}
	}

	public int getComprimento() {
		return comprimento;
	}

	public void setComprimento(int comprimento) {
		this.comprimento = comprimento;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public boolean estaPronto() {
		return getComprimento() >= 5 && getLargura() >= 5;
	}

	public EstadoZona estadoPosicao(int x, int y) {
		Set<Entry<Coordenada, Zona>> pares = this.mapa.entrySet();
		for (Entry<Coordenada, Zona> entrada : pares) {
			Coordenada c = entrada.getKey();
			if (c.getX() == x && c.getY() == y) {
				Zona z = entrada.getValue();
				return z.getEstadoZona();
			}
		}
		return null;
	}

}
