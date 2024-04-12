package view;

import java.util.Scanner;

public class Interface {
  private static String bemVindo = "--------------------------------------------\n" +
      "--------Gerenciador de Alunos e Notas-------\n" +
      "--------------------------------------------\n" +
      "|                                          |\n" +
      "|------------------------------------------|\n" +
      "|                                          |\n" +
      "|            Selecione uma opção:          |\n" +
      "|                                          |\n" +
      "|1 - Listar Alunos                         |\n" +
      "|2 - Adicionar um Aluno                    |\n" +
      "|3 - Atualizar um Aluno                    |\n" +
      "|4 - Remover um Aluno                      |\n" +
      "|5 - Ver Notas de um Aluno                 |\n" +
      "|6 - Adicionar uma Nota a um Aluno         |\n" +
      "|7 - Alterar uma Nota de um Aluno          |\n" +
      "|8 - Remover uma Nota de um Aluno          |\n" +
      "|9 - Remover TODAS Notas de um Aluno       |\n" +
      "|                                          |\n" +
      "|0 - Sair                                  |\n" +
      "|------------------------------------------|\n";

  public static void main(String[] args) {

    int selecao = -1;

    Scanner entrada = new Scanner(System.in);
    while (true) {
      System.out.print("\033[H\033[2J");
      System.out.print(bemVindo);

      while (!entrada.hasNextInt()) {
        System.out.println("Entrada inválida! Por favor, digite um número inteiro.");
        entrada.next();
      }

      selecao = entrada.nextInt();

      switch (selecao) {
        case 0:
          entrada.close();
          System.exit(0);
          break;
        case 1:
          InterfaceAluno.list();
          break;
        case 2:
          InterfaceAluno.create();
          break;
        case 3:
          InterfaceAluno.update();
          break;
        case 4:
          InterfaceAluno.delete();
          break;
        case 5:
          InterfaceNota.listForAluno();
          break;
        case 6:
          InterfaceNota.create();
          break;
        case 7:
          InterfaceNota.update();
          break;
        case 8:
          InterfaceNota.delete();
          break;
        case 9:
          InterfaceNota.deleteAllFromAluno();
          break;
        default:
          break;
      }
      entrada.reset();
    }
    // entrada.close();
  }
}
