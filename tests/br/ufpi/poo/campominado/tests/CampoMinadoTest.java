package br.ufpi.poo.campominado.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import br.ufpi.poo.campominado.enums.Acao;
import br.ufpi.poo.campominado.exceptions.PosicaoInvalidaException;
import br.ufpi.poo.campominado.model.CampoMinado;
import br.ufpi.poo.campominado.model.Coordenada;
import br.ufpi.poo.campominado.model.Jogada;

/**
 * @author alcemirsantos
 *
 */

public class CampoMinadoTest {

	@Test
	public void testJogadaValida() throws PosicaoInvalidaException{
		CampoMinado cm = new CampoMinado();
		cm.reseta();
		
		Jogada nova = new Jogada(Acao.INVESTIGAR, new Coordenada(0, 0));
		assertTrue("deveria ser valida!",cm.validar(nova));
		
	}
}
