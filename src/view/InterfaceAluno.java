package view;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.AlunoDao;
import dao.NotaDao;
import models.Aluno;
import models.Estatistica;

public class InterfaceAluno {

  private static final Scanner scanner = new Scanner(System.in);

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

  private static boolean isValidName(String nome) {
    String regex = "^[a-zA-ZÀ-ÿ]+(?:\\s[a-zA-ZÀ-ÿ]+)*$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(nome);
    return matcher.matches();
  }

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
      if (estatistica != null) {
        System.out.print(estatistica.getMedia() + " | " + estatistica.getMin() + " | " + estatistica.getMax() + " | "
            + estatistica.getVar() + "\n");
      } else {
        System.out.println("");
      }
    });
    scanner.nextLine();
  }

  public static void create() {
    clearScreen();
    System.out.println("Digite o nome do novo aluno:");
    String nome = scanner.nextLine();

    while (!isValidName(nome)) {
      System.out.println("Nome inválido! Por favor, insira um nome válido.");
      nome = scanner.nextLine();
    }

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

    for (Aluno aluno : alunos) {
      System.out.println(aluno.getIdAluno() + " | " + aluno.getNome());
    }

    Aluno alunoModificado = InterfaceAluno.findAluno(alunos);

    System.out.println("Digite o novo nome:");
    scanner.nextLine();
    String nome = scanner.nextLine();

    while (!isValidName(nome)) {
      System.out.println("Nome inválido! Por favor, insira um nome válido.");
      nome = scanner.nextLine();
    }

    alunoModificado.setNome(nome);
    AlunoDao.update(alunoModificado);

    System.out.println("Aluno alterado com sucesso!");
    System.out.println("PRESSIONE ENTER PARA SAIR");
    scanner.nextLine();
  }

  public static void delete() {
    clearScreen();
    System.out.println("Digite número do aluno para deletá-lo:");
    ArrayList<Aluno> alunos = AlunoDao.get();

    for (Aluno aluno : alunos) {
      System.out.println(aluno.getIdAluno() + " | " + aluno.getNome());
    }

    Aluno alunoDelete = InterfaceAluno.findAluno(alunos);

    AlunoDao.delete(alunoDelete);
    System.out.println("Aluno removido com sucesso!");
    System.out.println("PRESSIONE ENTER PARA SAIR");
    scanner.nextLine();
  }
}
