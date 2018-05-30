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
					System.out.println(b);
					encerraPartida(Resultado.PERDEU, campo.getTabuleiro());
					bombaExplodiu = true;
					break;
				}
			}
			if(!bombaExplodiu)
				encerraPartida(Resultado.GANHOU, campo.getTabuleiro());

		}
	}

	private static void encerraPartida(Resultado r, Tabuleiro t) {
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
		System.out.println("=======================\n");
		System.out.print("Deseja jogar novamente?(s/n) ");
		String str = sc.nextLine();
		if (!str.toLowerCase().equals("s")) {
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
