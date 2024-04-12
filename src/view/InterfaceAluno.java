package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.AlunoDao;
import dao.NotaDao;
import models.Aluno;
import models.Estatistica;

public class InterfaceAluno {

  private static final Scanner scanner = new Scanner(System.in);

  private static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void list() {
    clearScreen();
    System.out.println("PRESSIONE ENTER PARA SAIR");
    System.out.println("");
    System.out.println(" id | nome | média | menor nota | maior nota | variância");

    AlunoDao.get().forEach(aluno -> {
      Estatistica estatistica = NotaDao.getEstatistica(aluno);

      System.out.print(aluno.getIdAluno() + " | " + aluno.getNome() + " | ");
      if (estatistica != null)
        System.out.print(estatistica.getMedia() + " | " + estatistica.getMin() + " | " + estatistica.getMax() + " | "
            + estatistica.getVar() + "\n");
      else
        System.out.println("");
    });
    scanner.nextLine();
  }

  public static void create() {
    clearScreen();
    System.out.println("Digite o nome do novo aluno:");

    String nome = scanner.nextLine();

    Aluno aluno = new Aluno();
    aluno.setNome(nome);
    AlunoDao.create(aluno);

    System.out.println("Aluno criado com sucesso!");
    System.out.println("PRESSIONE ENTER PARA SAIR");
    scanner.nextLine();
  }

  public static void update() {
    clearScreen();
    System.out.println("Digite número do aluno para alterá-lo:");
    ArrayList<Aluno> alunos = AlunoDao.get();

    int contador = 0;
    for (Aluno aluno : alunos) {
      contador += 1;
      System.out.println(contador + " | " + aluno.getNome());
    }

    int selecao = scanner.nextInt();

    while (selecao < 1 || selecao > alunos.size()) {
      System.out.println("Entrada inválida");
      selecao = scanner.nextInt();
    }

    Aluno novoAluno = alunos.get(selecao - 1);
    System.out.println("Digite o novo nome:");
    scanner.nextLine();
    String nome = scanner.nextLine();

    novoAluno.setNome(nome);
    AlunoDao.update(novoAluno);

    System.out.println("Aluno alterado com sucesso!");
    System.out.println("PRESSIONE ENTER PARA SAIR");
    scanner.nextLine();
  }

  public static void delete() {
    clearScreen();
    System.out.println("Digite número do aluno para deletá-lo:");
    ArrayList<Aluno> alunos = AlunoDao.get();

    int contador = 0;
    for (Aluno aluno : alunos) {
      contador += 1;
      System.out.println(contador + " | " + aluno.getNome());
    }

    int selecao = scanner.nextInt();

    while (selecao < 1 || selecao > alunos.size()) {
      System.out.println("Entrada inválida");
      selecao = scanner.nextInt();
    }

    AlunoDao.delete(alunos.get(selecao - 1));
    System.out.println("Aluno removido com sucesso!");
    System.out.println("PRESSIONE ENTER PARA SAIR");
    scanner.nextLine();
  }
}
