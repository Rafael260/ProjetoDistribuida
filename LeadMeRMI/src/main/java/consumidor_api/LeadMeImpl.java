package consumidor_api;

import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import modelo.Curso;
import modelo.Disciplina;
import modelo.MatrizCurricular;
import modelo.Turma;

public class LeadMeImpl implements LeadMe {

	private ConsumidorAPILeadMe apiLeadMe;
	
	public LeadMeImpl(){
		apiLeadMe = ConsumidorAPILeadMe.getInstance();
	}
	
	public static void main(String[] args) {
		try {
			LeadMeImpl obj = new LeadMeImpl();
			LeadMe stub = (LeadMe) UnicastRemoteObject.exportObject(obj, 0);
			Registry registry = LocateRegistry.getRegistry();
            registry.bind("leadme", stub);
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */

	public Curso getCurso(String id) throws RemoteException {
		try {
			return apiLeadMe.getCurso(id);
		} catch (ConnectionException e) {
			e.printStackTrace();
			throw new RemoteException(e.getLocalizedMessage());
		}
	}

	public Double getMediaAprovacao(Disciplina disciplina) throws RemoteException {
		try {
			return apiLeadMe.getMediaAprovacao(disciplina);
		} catch (ConnectionException e) {
			e.printStackTrace();
			throw new RemoteException(e.getLocalizedMessage());
		}
	}

	public Turma getTurma(String id) throws RemoteException {
		try {
			return apiLeadMe.getTurma(id);
		} catch (ConnectionException e) {
			e.printStackTrace();
			throw new RemoteException(e.getLocalizedMessage());
		}
	}

	public List<MatrizCurricular> getMatrizesCurriculares(Curso curso) throws RemoteException {
		try {
			return apiLeadMe.getMatrizesCurriculares(curso);
		} catch (ConnectionException e) {
			e.printStackTrace();
			throw new RemoteException(e.getLocalizedMessage());
		}
	}

	public List<Disciplina> getDisciplinasDoCurso(Curso curso) throws RemoteException {
		try {
			return apiLeadMe.getDisciplinasDoCurso(curso);
		} catch (ConnectionException e) {
			e.printStackTrace();
			throw new RemoteException(e.getLocalizedMessage());
		}
	}

	public List<Turma> getTurmas(Disciplina disciplina) throws RemoteException {
		try {
			return apiLeadMe.getTurmas(disciplina);
		} catch (ConnectionException e) {
			e.printStackTrace();
			throw new RemoteException(e.getLocalizedMessage());
		}
	}

	public Disciplina coletarDisciplinaDaTurma(Turma turma) throws RemoteException {
		try {
			return apiLeadMe.coletarDisciplinaDaTurma(turma);
		} catch (ConnectionException e) {
			e.printStackTrace();
			throw new RemoteException(e.getLocalizedMessage());
		}
	}

}
