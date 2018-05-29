package br.ufpi.poo.campominado;

import java.util.Scanner;

import br.ufpi.poo.campominado.enums.Acao;
import br.ufpi.poo.campominado.exceptions.BombaExplodiuException;
import br.ufpi.poo.campominado.exceptions.PosicaoInvalidaException;
import br.ufpi.poo.campominado.model.CampoMinado;
import br.ufpi.poo.campominado.model.Coordenada;
import br.ufpi.poo.campominado.model.Jogada;

public class Main {

	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);

		while (true) {

			CampoMinado campo = new CampoMinado();
			campo.reseta();

			while (!campo.temVencedor()) {
				System.out.println(campo.getTabuleiro());

				Jogada umaJogada = null;
				umaJogada = coletaJogada();
				try {
					campo.executa(umaJogada);
				} catch (BombaExplodiuException b) {
					System.out.println("Você perdeu!" + b);
					System.out.println(campo.getTabuleiro());
					return;
				} catch (PosicaoInvalidaException e) {
					System.err
							.println("ERROR: Jogada em posição inválida. Tente novamente!");
				}
			}

			System.out.println("Você ganhou!\n\n" + campo.getTabuleiro());
			if (!jogarNovamente()) {
				sc.close();
				return;
			}
		}
	}

	private static boolean jogarNovamente() {
		System.out.println("Deseja jogar novamente?(s/n) ");
		String str = sc.next();
		if (str.toLowerCase().equals("s"))
			return true;

		return false;
	}

	private static Jogada coletaJogada() {
		System.out.println("\n::: Nova Jogada :::");
		int opcao = 0;
		while (opcao == 0) {
			try {
				System.out
						.println("::Escolha a ação (1= Investigar; 2=Marcar Bomba):");
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
			System.out.print("\n::Digite a posição assim: linha,coluna:");
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
}
