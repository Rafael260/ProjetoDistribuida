/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import consumidor_api.ConnectionException;
import consumidor_api.ConsumidorAPILeadMe;
import consumidor_api.ConsumidorAPISinfo;
import consumidor_api.ConsumidorRMILeadMe;
import dto.MatriculaComponenteUfrnDTO;

import java.util.ArrayList;
import java.util.List;
import modelo.Aluno;
import modelo.Matricula;
import modelo.Turma;

/**
 *
 * @author rafao
 */
public class AlunoServiceUFRN extends AlunoService{
    
    private ConsumidorAPISinfo consumidor;
//    private LeadMeWSClient wsClient;
    private ConsumidorRMILeadMe consumidorLeadMe;
    
    public AlunoServiceUFRN(){
        consumidor = ConsumidorAPISinfo.getInstance();
        consumidorLeadMe = ConsumidorRMILeadMe.getInstance();
    }
    
    @Override
    public void carregarMatriculasDoAluno(Aluno aluno){
        List<MatriculaComponenteUfrnDTO> matriculasDTO = consumidor.coletarMatriculas(Integer.parseInt(aluno.getId()));
        List<Matricula> matriculas = new ArrayList<>();
        Matricula matricula;
        Turma turma;
        for(MatriculaComponenteUfrnDTO matriculaDTO: matriculasDTO){
            System.out.println("Carregando a matricula");
            matricula = new Matricula();
            matricula.setAluno(aluno);
            //TODO criar fun√ß√£o para consumir do WebService
//            turma = turmaDAO.encontrar(matriculaDTO.getIdTurma());
            try {
				turma = consumidorLeadMe.getTurma(matriculaDTO.getIdTurma().toString());
				if(turma == null) {
					System.err.println("Turma da API n„o encontrada na API do Lead Me...");
					continue;
				}
				matricula.setTurma(turma);
	            if(matriculaDTO.getConceito()){
	                System.err.println("Conceito true, entao a nota n eh double");
	                continue;
	            }
	            String situacao = consumidor.coletarSituacaoMatricula(matriculaDTO.getIdSituacaoMatricula());
	            matricula.setSituacao(situacao);
	            matricula.setNumeroFaltas(matriculaDTO.getFaltas().doubleValue());
	            matriculas.add(matricula);
			} catch (ConnectionException e) {
				// TODO Auto-generated catch block
				System.err.println("Erro de conex„o com API do Lead Me");
				System.exit(-1);
			}
            
        }
        aluno.setMatriculas(matriculas);
    }
}
