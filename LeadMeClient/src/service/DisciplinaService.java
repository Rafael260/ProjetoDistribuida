/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import consumidor_api.ConsumidorAPILeadMe;
import excecoes.ConnectionException;
import fabricas.Fabrica;
import java.util.Collections;
import java.util.List;
import modelo.Disciplina;
import modelo.MatrizDisciplina;
import modelo.Turma;

/**
 *
 * @author rafao
 */
public class DisciplinaService {

//    LeadMeWSClient wsClient;
    private ConsumidorAPILeadMe consumidorLeadMe;
    
    public DisciplinaService() {
        consumidorLeadMe = ConsumidorAPILeadMe.getInstance();
    }

    /**
     * Retorna a média de aprovações das turmas da disciplina. Serve para
     * carregar o gráfico pizza com as aprovações.
     *
     * @param disciplina disciplina que o usuário escolheu para ver as
     * estatísticas
     * @return média de aprovações dessa disciplina
     */
    public Double coletarMediaAprovacao(Disciplina disciplina) {
        try {
			return consumidorLeadMe.getMediaAprovacao(disciplina);
		} catch (ConnectionException e) {
			System.err.println("Erro ao coletar a media de aprova��o da disciplina de id " + disciplina.getId());
			System.exit(-1);
			return null;
		}
    }
    
    public Double coletarMediaReprovacao(Disciplina disciplina){
        return 100.0 - coletarMediaAprovacao(disciplina);
    }
    
    public void ordenarDisciplinas(List<MatrizDisciplina> disciplinas){
        ComparadorMatrizDisciplina comparador = Fabrica.getInstance().getFactory().createComparadorMatrizDisciplina();
        Collections.sort(disciplinas, comparador);
    }
    
    public List<Turma> carregarTurmasDaDisciplina(Disciplina disciplina){
        try {
			return consumidorLeadMe.getTurmas(disciplina);
		} catch (ConnectionException e) {
			System.err.println("Erro ao carregar as turmas da disciplina");
			System.exit(-1);
			return null;
		}
    }
}
