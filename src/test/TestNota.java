package test;

import dao.NotaDao;
import models.Nota;

public class TestNota {
  public static void main(String[] args) {
    NotaDao alunoDao = new NotaDao();

    Nota aluno = new Nota();
    Nota aluno2 = new Nota();
    aluno.setNota(10);

    alunoDao.create(aluno);
    for (Nota a : alunoDao.read()) {
      aluno2 = a;
      System.out.println("Nota " + a.getNota());
    }

    aluno2.setNota(4);

    alunoDao.update(aluno2);
    for (Nota a : alunoDao.read()) {
      aluno2 = a;
      System.out.println("Nota " + a.getNota());
    }

    alunoDao.delete(aluno2);
    for (Nota a : alunoDao.read()) {
      aluno2 = a;
      System.out.println("Nota " + a.getNota());
    }
  }
}
