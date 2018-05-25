package br.ufpi.poo.campominado;

import java.util.Scanner;

import br.ufpi.poo.campominado.enums.Acao;
import br.ufpi.poo.campominado.exceptions.BombaExplodiuEssipitiu;
import br.ufpi.poo.campominado.model.CampoMinado;
import br.ufpi.poo.campominado.model.Coordenada;
import br.ufpi.poo.campominado.model.Jogada;

public class Main {

	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		CampoMinado campo = new CampoMinado();

		while (!campo.temVencedor()) {
			Jogada umaJogada = null;
			umaJogada = coletaJogada();
			try {
				campo.executa(umaJogada);
			} catch (BombaExplodiuEssipitiu b) {
				System.out.println(b + "Você perdeu!\n\n"
						+ campo.getTabuleiro());
				return;
			}
		}

		System.out.println("Você ganhou!\n\n" + campo.getTabuleiro());
		sc.close();
	}

	private static Jogada coletaJogada() {
		System.out.println("\n::Escolha a ação \n\t(1= Investigar; 2=Marcar Bomba):");
		Acao acao;
		switch(Integer.parseInt(sc.nextLine())){
		case 1:
			acao = Acao.INVESTIGAR;
			break;
		case 2:
			acao= Acao.MARCAR;
			break;
		default:
			acao = Acao.INVESTIGAR;
		}
		
		System.out.println("\n::Digite posição da macação!");
		int linha = 99, coluna = 99;
		
		while (linha == 99) {
			System.out.print("Linha,Coluna:");
			try {
				String[] entrada = sc.nextLine().split(",");
				linha = Integer.parseInt(entrada[0]);
				coluna = Integer.parseInt(entrada[1]);
				break;
			} catch (NumberFormatException e) {
				System.err
						.println("\nLinha inválida! Digite somente (um) número(s) separados por uma vírgula (,)!");
			}
		}

		return new Jogada(acao, new Coordenada(linha, coluna));
	}

}
