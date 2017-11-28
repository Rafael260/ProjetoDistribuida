package consumidor_api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import modelo.Curso;
import modelo.Disciplina;
import modelo.MatrizCurricular;
import modelo.Turma;

public interface LeadMe extends Remote{
	public Curso getCurso(String id) throws RemoteException;
	public Double getMediaAprovacao(Disciplina disciplina) throws RemoteException;
	public Turma getTurma(String id) throws RemoteException;
	public List<MatrizCurricular> getMatrizesCurriculares(Curso curso) throws RemoteException;
	public List<Disciplina> getDisciplinasDoCurso(Curso curso) throws RemoteException;
	public List<Turma> getTurmas(Disciplina disciplina) throws RemoteException;
	public Disciplina coletarDisciplinaDaTurma(Turma turma) throws RemoteException;
}
