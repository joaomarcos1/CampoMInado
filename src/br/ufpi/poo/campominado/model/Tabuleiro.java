package br.ufpi.poo.campominado.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import br.ufpi.poo.campominado.enums.EstadoZona;
import br.ufpi.poo.campominado.exceptions.BombaExplodiuException;

/**
 * @author alcemirsantos
 *
 */
public class Tabuleiro {

	int linhas;
	int colunas;
	private Map<Coordenada, Zona> mapa;
	private Collection<Coordenada> bombas;

	public Tabuleiro() {
		this.linhas = 4;
		this.colunas = 4;
		this.mapa = new HashMap<Coordenada, Zona>(getNumZonas());
		this.bombas = new ArrayList<Coordenada>();
		inicia();
	}

	private void inicia() {
		for (int x = 0; x < this.linhas; x++) {
			for (int y = 0; y < this.colunas; y++) {
				Zona zona = new Zona();
				this.mapa.put(new Coordenada(x, y), zona);
			}
		}
		marcaBombas();
	}

	private void marcaBombas() {
		int qtdeBombas = 0;
		while (qtdeBombas < (int) Math.round(0.2 * getQtdeZonasLivres())) {
			Random r = new Random();
			int x = r.nextInt(linhas);
			int y = r.nextInt(colunas);
			Coordenada possivelNovaBomba = new Coordenada(x, y);
			if (this.bombas.contains(possivelNovaBomba))
				continue;
			this.mapa.get(possivelNovaBomba).setBomba(true);
			this.mapa.get(possivelNovaBomba).setValor(Integer.MAX_VALUE);
			this.bombas.add(new Coordenada(x, y));
			qtdeBombas++;
		}
	}

	public int getComprimento() {
		return linhas;
	}

	public void setComprimento(int comprimento) {
		this.linhas = comprimento;
	}

	public int getLargura() {
		return colunas;
	}

	public void setLargura(int largura) {
		this.colunas = largura;
	}

	public boolean estaPronto() {
		return getComprimento() >= 5 && getLargura() >= 5;
	}

	/**
	 * Usar o método {@link #getEstado(Coordenada)} ao invés desse.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	@Deprecated
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

	public EstadoZona getEstado(Coordenada umaCoordenada) {
		return mapa.get(umaCoordenada).getEstadoZona();
	}

	public int getNumZonas() {
		return this.linhas * this.colunas;
	}

	public int getQtdeZonas(EstadoZona vazio) {
		int count = 0;
		for (Zona z : this.mapa.values())
			if (z.getEstadoZona().equals(vazio))
				count++;
		return count;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		String indicesColuna = "  ";
		for (int y = 0; y < this.colunas; y++) {
			indicesColuna += " " + y + " ";
		}
		sb.append(indicesColuna + "\n");
		for (int x = 0; x < this.linhas; x++) {
			String linha = x + " ";
			for (int y = 0; y < this.colunas; y++)
				linha += getZona(x, y).valorToString();
			sb.append(linha + "\n");
		}

		return sb.toString();
	}

	public Zona getZona(int x, int y) {
		return this.mapa.get(new Coordenada(x, y));
	}

	public Zona getZona(Coordenada c) {
		return this.mapa.get(c);
	}

	public int getQtdeBombas() {
		return this.bombas.size();
	}

	public int getQtdeZonasLivres() {
		return getQtdeZonas(EstadoZona.VAZIO);
	}

	public void atualizaZona(Coordenada umaCoordenada, EstadoZona novoEstado) throws BombaExplodiuException {
		if (novoEstado == EstadoZona.ABERTO) {
			if(this.mapa.get(umaCoordenada).temBomba()){
				throw new BombaExplodiuException("Oh não! Aqui havia uma bomba!");
			}else{
				this.mapa.get(umaCoordenada).setValor(contaBombasAoRedor(umaCoordenada));
			}
		}
		this.mapa.get(umaCoordenada).setEstadoZona(novoEstado);
	}

	private int contaBombasAoRedor(Coordenada umaCoordenada) {
		// TODO Auto-generated method stub
		return 0;
	}

}
