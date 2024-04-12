package view;

import dao.AlunoDao;
import dao.NotaDao;
import models.Aluno;
import models.Nota;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InterfaceNota {
  private static final Scanner scanner = new Scanner(System.in);

  private static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  private static void displayAlunos(ArrayList<Aluno> alunos) {
    int contador = 0;
    for (Aluno aluno : alunos) {
      contador++;
      System.out.println(contador + " | " + aluno.getNome());
    }
  }

  private static void displayNotas(ArrayList<Nota> notas) {
    int contador = 0;
    for (Nota nota : notas) {
      contador++;
      System.out.println(contador + " | Nota: " + nota.getNota());
    }
  }

  private static int getValidSelection(int max) {
    int selecao = -1;
    while (selecao < 1 || selecao > max) {
      try {
        selecao = scanner.nextInt();
        if (selecao < 1 || selecao > max) {
          System.out.println("Entrada inválida. Tente novamente:");
        }
      } catch (InputMismatchException e) {
        System.out.println("Entrada inválida. Tente novamente:");
        scanner.next();
      }
    }
    return selecao;
  }

  private static int getValidSelection(int max, int min) {
    int selecao = -1;
    while (selecao < min || selecao > max) {
      try {
        selecao = scanner.nextInt();
        if (selecao < min|| selecao > max) {
          System.out.println("Entrada inválida. Tente novamente:");
        }
      } catch (InputMismatchException e) {
        System.out.println("Entrada inválida. Tente novamente:");
        scanner.next();
      }
    }
    return selecao;
  }

  public static void listForAluno() {
    clearScreen();

    System.out.println("Digite o número do aluno para ver suas notas:");
    ArrayList<Aluno> alunos = AlunoDao.get();

    if (alunos.isEmpty()) {
      System.out.println("Sem alunos cadastrados.");
      System.out.println("PRESSIONE ENTER PARA SAIR");
      scanner.nextLine();
      scanner.nextLine();
      return;
    }

    displayAlunos(alunos);

    int selecao = getValidSelection(alunos.size());
    Aluno aluno = alunos.get(selecao - 1);

    System.out.println("Notas do(a) " + aluno.getNome() + ":");
    ArrayList<Nota> notas = NotaDao.get(aluno);

    if (notas.isEmpty()) {
      System.out.println("Sem notas cadastradas para este aluno.");
    } else {
      displayNotas(notas);
    }

    System.out.println("PRESSIONE ENTER PARA SAIR");
    scanner.nextLine();
    scanner.nextLine();
  }

  public static void create() {
    clearScreen();

    System.out.println("Digite o número do aluno para adicionar uma nota:");
    ArrayList<Aluno> alunos = AlunoDao.get();

    if (alunos.isEmpty()) {
      System.out.println("Sem alunos cadastrados.");
      System.out.println("PRESSIONE ENTER PARA SAIR");
      scanner.nextLine();
      scanner.nextLine();
      return;
    }

    displayAlunos(alunos);

    int selecao = getValidSelection(alunos.size());
    Aluno aluno = alunos.get(selecao - 1);
    System.out.println("Digite a nota:");

    int nota = getValidSelection(10, 0);
    Nota novaNota = new Nota();
    novaNota.setAluno(aluno);
    novaNota.setNota(nota);

    NotaDao.create(novaNota);
    System.out.println("Nota criada com sucesso!");
    System.out.println("PRESSIONE ENTER PARA SAIR");
    scanner.nextLine();
    scanner.nextLine();
  }

  public static void update() {
    clearScreen();

    System.out.println("Digite o número do aluno para alterar uma nota:");
    ArrayList<Aluno> alunos = AlunoDao.get();

    if (alunos.isEmpty()) {
      System.out.println("Sem alunos cadastrados.");
      System.out.println("PRESSIONE ENTER PARA SAIR");
      scanner.nextLine();
      scanner.nextLine();

      return;
    }

    displayAlunos(alunos);

    int selecao = getValidSelection(alunos.size());
    Aluno aluno = alunos.get(selecao - 1);
    ArrayList<Nota> notas = NotaDao.get(aluno);

    if (notas.isEmpty()) {
      System.out.println("Aluno sem notas cadastradas.");
      System.out.println("PRESSIONE ENTER PARA SAIR");
      scanner.nextLine();
      scanner.nextLine();

      return;
    }

    System.out.println("Digite o número da nota que deseja alterar:");

    displayNotas(notas);

    selecao = getValidSelection(notas.size());
    Nota notaSelecionada = notas.get(selecao - 1);

    System.out.println("Digite a nova nota:");
    int novaNota = getValidSelection(10, 0);
    notaSelecionada.setNota(novaNota);

    NotaDao.update(notaSelecionada);
    System.out.println("Nota alterada com sucesso!");
    System.out.println("PRESSIONE ENTER PARA SAIR");
    scanner.nextLine();
    scanner.nextLine();

  }

  public static void delete() {
    clearScreen();

    System.out.println("Digite o número do aluno para remover uma nota:");
    ArrayList<Aluno> alunos = AlunoDao.get();

    if (alunos.isEmpty()) {
      System.out.println("Sem alunos cadastrados.");
      System.out.println("PRESSIONE ENTER PARA SAIR");
      scanner.nextLine();
      scanner.nextLine();

      return;
    }

    displayAlunos(alunos);

    int selecao = getValidSelection(alunos.size());
    Aluno aluno = alunos.get(selecao - 1);
    ArrayList<Nota> notas = NotaDao.get(aluno);

    if (notas.isEmpty()) {
      System.out.println("Aluno sem notas cadastradas.");
      System.out.println("PRESSIONE ENTER PARA SAIR");
      scanner.nextLine();
      scanner.nextLine();

      return;
    }

    System.out.println("Digite o número da nota que deseja remover:");

    displayNotas(notas);

    selecao = getValidSelection(notas.size());
    Nota notaSelecionada = notas.get(selecao - 1);

    NotaDao.delete(notaSelecionada);
    System.out.println("Nota removida com sucesso!");
    System.out.println("PRESSIONE ENTER PARA SAIR");
    scanner.nextLine();
    scanner.nextLine();

  }

  public static void deleteAllFromAluno() {
    clearScreen();

    System.out.println("Digite o número do aluno para remover TODAS as notas:");
    ArrayList<Aluno> alunos = AlunoDao.get();

    if (alunos.isEmpty()) {
      System.out.println("Sem alunos cadastrados.");
      System.out.println("PRESSIONE ENTER PARA SAIR");
      scanner.nextLine();
      scanner.nextLine();

      return;
    }

    displayAlunos(alunos);

    int selecao = getValidSelection(alunos.size());
    Aluno aluno = alunos.get(selecao - 1);

    NotaDao.deleteAllFromAluno(aluno);
    System.out.println("Notas removidas com sucesso!");
    System.out.println("PRESSIONE ENTER PARA SAIR");
    scanner.nextLine();
    scanner.nextLine();

  }
}
