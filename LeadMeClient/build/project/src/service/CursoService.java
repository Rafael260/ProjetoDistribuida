/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import consumidor_api.ConsumidorAPILeadMe;
import excecoes.ConnectionException;
import excecoes.DataException;
import fabricas.Fabrica;
import java.util.ArrayList;
import java.util.List;
import minerador.BibliotecaMineracao;
import minerador.BibliotecaMineracaoImpl;
import modelo.Aluno;
import modelo.Curso;
import modelo.Disciplina;
import modelo.MatrizCurricular;
import modelo.MatrizDisciplina;

public class CursoService {
    
    public static int NUMERO_DISCIPLINAS_DIFICEIS = 10;
    
    private RequisitosService requisitosService;
    private BibliotecaMineracao bibliotecaMineracao;
    private DisciplinaService disciplinaService;
//    private LeadMeWSClient wsClient;
    private ConsumidorAPILeadMe consumidorLeadMe;
    
    public CursoService(){
        requisitosService = Fabrica.getInstance().getFactory().createRequisitosService();
        bibliotecaMineracao = new BibliotecaMineracaoImpl();
        disciplinaService = new DisciplinaService();
        consumidorLeadMe = ConsumidorAPILeadMe.getInstance();
    }
    
    
    public Curso carregarCurso(Integer id) throws DataException{
        Curso curso;
		try {
			curso = consumidorLeadMe.getCurso(id.toString());
			System.out.println("ENCONTROU O CURSO");
	        return curso;
		} catch (ConnectionException e) {
			System.err.println("Erro ao tentar procurar o curso. Saindo do programa...");
			System.exit(-1);
		}
//        bibliotecaMineracao.gerarArquivoParaAssociarDisciplinas(curso);
        return null;
    }
    
    public List<Disciplina> coletarDisciplinasDoCurso(Curso curso){
//        return curso.coletarDisciplinas();
        try {
			return consumidorLeadMe.getDisciplinasDoCurso(curso);
		} catch (ConnectionException e) {
			System.err.println("Erro ao coletar as disciplinas do curso");
			System.exit(-1);
			return null;
		}
    }
    
    public List<MatrizCurricular> carregarMatrizesCurricularesDoCurso(Curso curso){
        try {
			return consumidorLeadMe.getMatrizesCurriculares(curso);
		} catch (ConnectionException e) {
			System.err.println("Erro ao carregar as matrizes do curso");
			System.exit(-1);
			return null;
		}
    }
    
    /**
     * Retorna uma lista de disciplinas que podem ser pagas pelo aluno.
     * Leva em consideração os pré requisitos, co requisitos e equivalencias para dizer se o aluno pode ou não pagar a disciplina.
     * A lista é do tipo MatrizDisciplina por ter informações de natureza (obrigatória ou optativa) e semestre ideal (qual período pagar,  caso seja obrigatória)
     * @param aluno aluno interessado em receber as sugestões de disciplinas
     * @return Lista de disciplinas que pode pagar, ordenada pela relevância (obrigatório e dos primeiros períodos)
     */
    public List<MatrizDisciplina> coletarDisciplinasDisponiveis(Aluno aluno, MatrizCurricular matriz){
        //Crio a lista com as disciplinas disponiveis
        List<MatrizDisciplina> disciplinasDisponiveis = new ArrayList<>();
        //TODO fornecer a lista de matrizes para aluno escolher qual fazer as simulacoes
        //E para cada disciplina da matriz, eu verifico se o aluno pode pagar
        List<MatrizDisciplina> disciplinasNaMatriz = matriz.getDisciplinasNaMatriz();
        for (MatrizDisciplina disciplinaNaMatriz: disciplinasNaMatriz){
            if (requisitosService.podePagar(aluno, disciplinaNaMatriz.getDisciplina())){
                disciplinasDisponiveis.add(disciplinaNaMatriz);
            }
        }
        bibliotecaMineracao.associarDisciplinasComunsAPeriodoLetivo(disciplinasDisponiveis);
        //Ordena pela ordem de prioridade das disciplinas a serem pagas
        disciplinaService.ordenarDisciplinas(disciplinasDisponiveis);
        return disciplinasDisponiveis;
    }
}
