package br.ufpi.poo.campominado;

import java.util.Scanner;

import br.ufpi.poo.campominado.enums.Acao;
import br.ufpi.poo.campominado.exceptions.BombaExplodiuException;
import br.ufpi.poo.campominado.exceptions.PosicaoInvalidaException;
import br.ufpi.poo.campominado.model.CampoMinado;
import br.ufpi.poo.campominado.model.Coordenada;
import br.ufpi.poo.campominado.model.Jogada;
import br.ufpi.poo.campominado.model.Tabuleiro;

public class Main {

	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);

		while (true) {
			CampoMinado campo = new CampoMinado();
			campo.reseta();

			System.out.println("========================");
			System.out.println("::: Campo Minado POO :::");
			System.out.println("========================\n");
			
			System.out.println("CUIDADO! Existem "+campo.getTabuleiro().getQtdeBombas()+ " bombas.");
			boolean bombaExplodiu = false;
			long tempoInicial = System.currentTimeMillis();
			while (!campo.temVencedor()) {
				System.out.println(campo.getTabuleiro());

				Jogada umaJogada = null;
				umaJogada = coletaJogada();
				try {
					campo.executa(umaJogada);
				} catch (PosicaoInvalidaException e) {
					System.err.println("ERROR: Jogada em posição inválida.");
					System.out.println("Tente novamente!");
				} catch (BombaExplodiuException b) {
					long tempoFinal = System.currentTimeMillis();
					System.out.println(b);
					encerraPartida(Resultado.PERDEU, campo.getTabuleiro(), tempoFinal-tempoInicial);
					bombaExplodiu = true;
					break;
				}
			}
			if(!bombaExplodiu){
				long tempoFinal = System.currentTimeMillis();
				System.out.println();
				encerraPartida(Resultado.GANHOU, campo.getTabuleiro(), tempoFinal-tempoInicial);
			}
		}
	}

	private static void encerraPartida(Resultado r, Tabuleiro t, long tempoEmMilisegundos) {
		System.out.println("=======================");
		switch (r) {
		case GANHOU:
			System.out.println("Você ganhou!");
			break;
		case PERDEU:
			System.out.println("Você perdeu!");
			break;
		}
		t.finaliza();
		System.out.println(t);
		long second = (tempoEmMilisegundos / 1000) % 60;
		long minute = (tempoEmMilisegundos / (1000 * 60)) % 60;
//		long hour = (tempoEmMilisegundos / (1000 * 60 * 60)) % 24;
		String tempoFormatado = String.format("%02dminutos e %02dsegundos", minute, second);
		System.out.println("Tempo de Jogo: "+tempoFormatado);
		
		System.out.println("=======================\n");
		System.out.print("Deseja jogar novamente?(s/n) ");
		String str = sc.nextLine();
		if (!str.toLowerCase().equals("s")) {
			System.out.println("Fim de Jogo!");
			sc.close();
			System.exit(0);
		}
	}

	private static Jogada coletaJogada() {
		System.out.println("::: Nova Jogada :::");
		int opcao = 0;
		while (opcao == 0) {
			try {
				System.out
						.print("::Escolha a ação (1= Investigar; 2=Marcar Bomba):");
				opcao = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.err.println("Opção inválida!" + e);
			}
		}
		Acao acao;
		switch (opcao) {
		case 2:
			acao = Acao.MARCAR;
			break;
		case 1:
		default:
			acao = Acao.INVESTIGAR;
			break;
		}

		int linha = 99, coluna = 99;
		while (linha == 99 || coluna == 99) {
			System.out.print("::Digite a posição assim: linha,coluna:");
			try {
				String[] entrada = sc.nextLine().split(",");
				linha = Integer.parseInt(entrada[0]);
				coluna = Integer.parseInt(entrada[1]);
				break;
			} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
				System.err
						.println("\nLinha inválida! Digite somente (um) número(s) separados por uma vírgula (,)!");
			}
		}

		return new Jogada(acao, new Coordenada(linha, coluna));
	}

	enum Resultado {
		PERDEU, GANHOU;
	}
}
