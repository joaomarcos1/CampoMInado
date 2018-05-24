package br.ufpi.poo.campominado.tests;
import static org.junit.Assert.*;

import org.junit.Test;

import br.ufpi.poo.campominado.CampoMinado;
import br.ufpi.poo.campominado.enums.EstadoZona;
import br.ufpi.poo.campominado.model.Tabuleiro;

public class TabuleiroTests {

	@Test
	public void testTabuleiroTemDimensoes() {
		// Dado...
		CampoMinado cm = new CampoMinado();
		cm.inicia();
		Tabuleiro t = cm.getTabuleiro();

		// Quando...
		boolean estado = t.estaPronto();

		// Então...
		assertTrue(estado);
	}

	@Test
	public void testTabuleiroLimpo() {
		CampoMinado cm = new CampoMinado();
		cm.inicia();
		Tabuleiro t = cm.getTabuleiro();

		EstadoZona estado;
		for (int x = 0; x < t.getComprimento(); x++) {
			for (int y = 0; y < t.getLargura(); y++) {
				estado = t.estadoPosicao(x, y);
				assertEquals(EstadoZona.VAZIO, estado);
			}
		}
	}

	@Test
	public void test3() {
	}

	@Test
	public void test4() {
	}

}
