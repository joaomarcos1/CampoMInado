package br.ufpi.poo.campominado.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import br.ufpi.poo.campominado.enums.Acao;
import br.ufpi.poo.campominado.exceptions.BombaExplodiuException;
import br.ufpi.poo.campominado.model.CampoMinado;
import br.ufpi.poo.campominado.model.Coordenada;
import br.ufpi.poo.campominado.model.Jogada;

public class JogadasTest {

	@Test
	public void testSobrescitaEqualsJogadasIguais() {
		Jogada um = new Jogada(Acao.INVESTIGAR, new Coordenada(0, 0));
		Jogada dois = new Jogada(Acao.INVESTIGAR, new Coordenada(0, 0));
		assertTrue("deveriam ser iguais!", um.equals(dois));
	}

	@Test
	public void testSobrescitaEqualsJogadasAcaoDiferente() {
		Jogada um = new Jogada(Acao.INVESTIGAR, new Coordenada(0, 0));
		Jogada dois = new Jogada(Acao.MARCAR, new Coordenada(0, 0));
		assertFalse("deveriam ser iguais!", um.equals(dois));
	}

	@Test
	public void testSobrescitaEqualsJogadasCoordenadaXDiferente() {
		Jogada um = new Jogada(Acao.INVESTIGAR, new Coordenada(0, 0));
		Jogada dois = new Jogada(Acao.INVESTIGAR, new Coordenada(1, 0));
		assertFalse("deveriam ser iguais!", um.equals(dois));
	}

	@Test
	public void testSobrescitaEqualsJogadasCoordenadaYDiferente() {
		Jogada um = new Jogada(Acao.INVESTIGAR, new Coordenada(0, 0));
		Jogada dois = new Jogada(Acao.INVESTIGAR, new Coordenada(0, 1));
		assertFalse("deveriam ser iguais!", um.equals(dois));
	}

	@Test
	public void testJogadaArmazenada() throws BombaExplodiuException {
		CampoMinado cm = new CampoMinado();
		cm.reseta();

		assertTrue("deveria estar vazia!", cm.getJogadas().isEmpty());
		Jogada nova = new Jogada(Acao.MARCAR, new Coordenada(0, 0));
		cm.executa(nova);
		List<Jogada> log = cm.getJogadas();
		assertEquals("deveria ter uma única jogada!", 1, log.size());
		assertTrue("deveria ser a jogada feita!", log.get(0).equals(nova));
	}

	
}
