package consumidor_api;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import modelo.Curso;
import modelo.Disciplina;
import modelo.MatrizCurricular;
import modelo.Turma;

public class ConsumidorRMILeadMe {

	private static ConsumidorRMILeadMe instance = new ConsumidorRMILeadMe();
	private LeadMe stub;
	
	private ConsumidorRMILeadMe() {
        stub = null;
		try {
        	Registry registry = LocateRegistry.getRegistry(null);
			stub = (LeadMe) registry.lookup("leadme");
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ConsumidorRMILeadMe getInstance(){
		return instance;
	}
	
	public Curso getCurso(String id) throws ConnectionException{
		try {
			return stub.getCurso(id);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConnectionException(e.getLocalizedMessage());
		}
	}
	
	public Double getMediaAprovacao(Disciplina disciplina) throws ConnectionException{
		try {
			return stub.getMediaAprovacao(disciplina);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConnectionException(e.getLocalizedMessage());
		}
	}
	
	public Turma getTurma(String id) throws ConnectionException{
		try {
			return stub.getTurma(id);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConnectionException(e.getLocalizedMessage());
		}
	}
	
	public List<MatrizCurricular> getMatrizesCurriculares(Curso curso) throws ConnectionException{
		try {
			return stub.getMatrizesCurriculares(curso);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConnectionException(e.getLocalizedMessage());
		}
	}
	
	public List<Disciplina> getDisciplinasDoCurso(Curso curso) throws ConnectionException{
		try {
			return stub.getDisciplinasDoCurso(curso);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConnectionException(e.getLocalizedMessage());
		}
	}
	
	public List<Turma> getTurmas(Disciplina disciplina) throws ConnectionException{
		try {
			return stub.getTurmas(disciplina);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConnectionException(e.getLocalizedMessage());
		}
	}
	
	public Disciplina coletarDisciplinaDaTurma(Turma turma) throws ConnectionException{
		try {
			return stub.coletarDisciplinaDaTurma(turma);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new ConnectionException(e.getLocalizedMessage());
		}
	}
	
	
}
