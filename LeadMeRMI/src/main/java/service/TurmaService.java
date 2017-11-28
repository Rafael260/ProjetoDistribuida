package service;

import consumidor_api.ConnectionException;
import consumidor_api.ConsumidorAPILeadMe;
import modelo.Disciplina;
import modelo.Turma;

public class TurmaService {

	private ConsumidorAPILeadMe consumidorLeadMe;
	
	public TurmaService() {
		consumidorLeadMe = ConsumidorAPILeadMe.getInstance();
	}
	
	public Disciplina coletarDisciplinaDaTurma(Turma turma) {
		if(turma.getDisciplina() != null) {
			return turma.getDisciplina();
		}
		Disciplina disciplina;
		try {
			disciplina = consumidorLeadMe.coletarDisciplinaDaTurma(turma);
			turma.setDisciplina(disciplina);
			return disciplina;
		} catch (ConnectionException e) {
			System.err.println("Erro ao carregar a disciplina da turma " + turma.getId());
			System.exit(-1);
			return null;
		}
		
	}
}
