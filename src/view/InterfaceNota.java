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
    for (Aluno aluno : alunos) {
      System.out.println(aluno.getIdAluno() + " | " + aluno.getNome());
    }
  }

  private static void displayNotas(ArrayList<Nota> notas) {
    for (Nota nota : notas) {
      System.out.println(nota.getIdNota() + " | Nota: " + nota.getNota());
    }
  }

  private static Aluno findAluno(ArrayList<Aluno> alunos) {
    while (true) {
      while (!scanner.hasNextInt()) {
        System.out.println("Entrada inválida! Por favor, digite um número inteiro.");
        scanner.next();
      }

      int selecao = scanner.nextInt();

      for (Aluno aluno : alunos) {
        if (aluno.getIdAluno().equals(selecao)) {
          return aluno;
        }
      }
      System.out.println("Entrada inválida! Por favor, digite um id válido.");
    }
  }

  private static Nota findNota(ArrayList<Nota> notas) {
    while (true) {
      while (!scanner.hasNextInt()) {
        System.out.println("Entrada inválida! Por favor, digite um número inteiro.");
        scanner.next();
      }

      int selecao = scanner.nextInt();

      for (Nota nota : notas) {
        if (nota.getIdNota().equals(selecao)) {
          return nota;
        }
      }
      System.out.println("Entrada inválida! Por favor, digite um id válido.");
    }
  }

  private static int getValidSelection(int max, int min) {
    int selecao = -1;
    while (selecao < min || selecao > max) {
      try {
        selecao = scanner.nextInt();
        if (selecao < min || selecao > max) {
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
    ArrayList<Aluno> alunos = AlunoDao.get();

    if (alunos.isEmpty()) {
      System.out.println("Sem alunos cadastrados.");
      System.out.println("PRESSIONE ENTER PARA SAIR");
      scanner.nextLine();
      scanner.nextLine();
      return;
    }

    System.out.println("Digite o número do aluno para ver suas notas:");
    displayAlunos(alunos);

    Aluno aluno = InterfaceNota.findAluno(alunos);

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
    Aluno aluno = InterfaceNota.findAluno(alunos);
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
    ArrayList<Aluno> alunos = AlunoDao.get();

    if (alunos.isEmpty()) {
      System.out.println("Sem alunos cadastrados.");
      System.out.println("PRESSIONE ENTER PARA SAIR");
      scanner.nextLine();
      scanner.nextLine();

      return;
    }

    System.out.println("Digite o número do aluno para alterar uma nota:");
    displayAlunos(alunos);

    Aluno aluno = InterfaceNota.findAluno(alunos);
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

    Nota notaSelecionada = InterfaceNota.findNota(notas);

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
    ArrayList<Aluno> alunos = AlunoDao.get();

    if (alunos.isEmpty()) {
      System.out.println("Sem alunos cadastrados.");
      System.out.println("PRESSIONE ENTER PARA SAIR");
      scanner.nextLine();
      scanner.nextLine();

      return;
    }

    System.out.println("Digite o número do aluno para remover uma nota:");
    displayAlunos(alunos);

    Aluno aluno = InterfaceNota.findAluno(alunos);
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

    Nota notaSelecionada = InterfaceNota.findNota(notas);

    NotaDao.delete(notaSelecionada);
    System.out.println("Nota removida com sucesso!");
    System.out.println("PRESSIONE ENTER PARA SAIR");
    scanner.nextLine();
    scanner.nextLine();

  }

  public static void deleteAllFromAluno() {
    clearScreen();
    ArrayList<Aluno> alunos = AlunoDao.get();

    if (alunos.isEmpty()) {
      System.out.println("Sem alunos cadastrados.");
      System.out.println("PRESSIONE ENTER PARA SAIR");
      scanner.nextLine();
      scanner.nextLine();

      return;
    }

    System.out.println("Digite o número do aluno para remover TODAS as notas:");
    displayAlunos(alunos);

    Aluno aluno = InterfaceNota.findAluno(alunos);

    NotaDao.deleteAllFromAluno(aluno);
    System.out.println("Notas removidas com sucesso!");
    System.out.println("PRESSIONE ENTER PARA SAIR");
    scanner.nextLine();
    scanner.nextLine();

  }
}
