package br.ufpi.poo.campominado.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.ufpi.poo.campominado.enums.EstadoZona;
import br.ufpi.poo.campominado.exceptions.PosicaoInvalidaException;
import br.ufpi.poo.campominado.model.CampoMinado;
import br.ufpi.poo.campominado.model.Coordenada;
import br.ufpi.poo.campominado.model.Tabuleiro;

public class TabuleiroTests {


	@Test
	public void testTabuleiroLimpo() throws PosicaoInvalidaException {
		CampoMinado cm = new CampoMinado();
		cm.reseta();
		Tabuleiro t = cm.getTabuleiro();

		EstadoZona estado;
		for (int x = 0; x < t.getComprimento(); x++) {
			for (int y = 0; y < t.getLargura(); y++) {
				estado = t.getEstado(new Coordenada(x,y));
				assertEquals(EstadoZona.VAZIO, estado);
			}
		}
	}

}
