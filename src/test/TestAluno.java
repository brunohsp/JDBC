package test;

import dao.AlunoDao;
import models.Aluno;

public class Test {
  public static void main(String[] args) {
    AlunoDao alunoDao = new AlunoDao();

    Aluno aluno = new Aluno();
    Aluno aluno2 = new Aluno();
    aluno.setNome("Bruno");

    alunoDao.create(aluno);
    for (Aluno a : alunoDao.read()) {
      aluno2 = a;
      System.out.println("Aluno " + a.getNome());
    }

    aluno2.setNome("Henrique");

    alunoDao.update(aluno2);
    for (Aluno a : alunoDao.read()) {
      aluno2 = a;
      System.out.println("Aluno " + a.getNome());
    }

    alunoDao.delete(aluno2);
    for (Aluno a : alunoDao.read()) {
      aluno2 = a;
      System.out.println("Aluno " + a.getNome());
    }
  }
}
