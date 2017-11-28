/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import modelo.Aluno;
import minerador.BibliotecaMineracao;
import minerador.BibliotecaMineracaoImpl;

/**
 *
 * @author rafao
 */
public class SessionService {

    private Aluno aluno;
    private static SessionService loginService = new SessionService();
    private CursoService cursoService;
    private BibliotecaMineracao bibliotecaMineracao;
    
    private SessionService() {
        aluno = null;
        cursoService = new CursoService();
        bibliotecaMineracao = new BibliotecaMineracaoImpl();
    }

    public static SessionService getInstance() {
        return loginService;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
