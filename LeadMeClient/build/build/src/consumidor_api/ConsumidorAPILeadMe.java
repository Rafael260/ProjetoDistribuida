/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumidor_api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import excecoes.ConnectionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Curso;
import modelo.Disciplina;
import modelo.MatrizCurricular;
import modelo.Turma;

/**
 *
 * @author rafao
 */
public class ConsumidorAPILeadMe {

    private static ConsumidorAPILeadMe instance = new ConsumidorAPILeadMe();

    public static final String BASE_URL = "http://10.51.105.106:8080/LeadMeWSMaven/api/leadme/";
    public static String CONSULTA_CURSO = BASE_URL + "cursos/";
    public static String CONSULTA_MEDIA = BASE_URL + "disciplina/mediaAprovacao?idComponente=";
    public static String CONSULTA_TURMA = BASE_URL + "turmas/";
    public static String CONSULTA_MATRIZES = BASE_URL + "matrizescurriculares?idCurso=";
    public static String CONSULTA_DISCIPLINAS = BASE_URL + "disciplinas?curso=";
    public static String CONSULTA_TURMAS = BASE_URL + "turmas?idComponente=";
    public static String CONSULTA_DISCIPLINA_DA_TURMA = BASE_URL + "disciplina?idTurma=";

    private Gson gson;

    private ConsumidorAPILeadMe() {
        gson = new Gson();
    }

    public static ConsumidorAPILeadMe getInstance() {
        return instance;
    }

    public String fazerRequisicao(String location, String metodo) throws ProtocolException, MalformedURLException, IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL(location);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(metodo.toUpperCase());
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    public Curso getCurso(String id) throws ConnectionException {
        String url = CONSULTA_CURSO + id;
        System.out.println(url);
        try {
            String response = fazerRequisicao(url, "GET");
            System.out.println("Response getCurso: " + response);
            return gson.fromJson(response, Curso.class);
        } catch (MalformedURLException ex) {
        	throw new ConnectionException(ex.getLocalizedMessage());
        } catch (IOException ex) {
        	throw new ConnectionException(ex.getLocalizedMessage());
        }
    }

    public Double getMediaAprovacao(Disciplina disciplina) throws ConnectionException{
        String idComponente = disciplina.getId().toString();
        String url = CONSULTA_MEDIA + idComponente;
        System.out.println(url);
        try {
            String response = fazerRequisicao(url, "GET");
            System.out.println("Response media aprovacao: " + response);
            return Double.parseDouble(response);
        } catch (MalformedURLException ex) {
        	throw new ConnectionException(ex.getLocalizedMessage());
        } catch (IOException ex) {
        	throw new ConnectionException(ex.getLocalizedMessage());
        }
    }

    public Turma getTurma(String id) throws ConnectionException{
        String url = CONSULTA_TURMA + id;
        System.out.println(url);
        try {
            String response = fazerRequisicao(url, "GET");
            System.out.println("Response getTurma " + response);
            return gson.fromJson(response, Turma.class);
        } catch (MalformedURLException ex) {
        	throw new ConnectionException(ex.getLocalizedMessage());
        } catch (IOException ex) {
        	throw new ConnectionException(ex.getLocalizedMessage());
        }
    }

    public List<MatrizCurricular> getMatrizesCurriculares(Curso curso) throws ConnectionException {
        String url = CONSULTA_MATRIZES + curso.getId().toString();
        System.out.println(url);
        if (curso.getMatrizesCurricular() != null) {
            return curso.getMatrizesCurricular();
        }
        try {
            String response = fazerRequisicao(url, "GET");
            System.out.println("Response matrizes: "+ response);
            Type listType = new TypeToken<ArrayList<MatrizCurricular>>() {
            }.getType();
            List<MatrizCurricular> matrizesCurriculares = new Gson().fromJson(response, listType);
            curso.setMatrizesCurricular(matrizesCurriculares);
            for (MatrizCurricular matriz: matrizesCurriculares) {
            	matriz.setCurso(curso);
            }
            return matrizesCurriculares;
        } catch (MalformedURLException ex) {
        	throw new ConnectionException(ex.getLocalizedMessage());
        } catch (IOException ex) {
        	throw new ConnectionException(ex.getLocalizedMessage());
        }
    }

    public List<Disciplina> getDisciplinasDoCurso(Curso curso) throws ConnectionException{
        String url = CONSULTA_DISCIPLINAS + curso.getId().toString();
        System.out.println(url);
        try {
            String response = fazerRequisicao(url, "GET");
            System.out.println("Response getDisciplinas: "+ response);
            Type listType = new TypeToken<ArrayList<Disciplina>>() {
            }.getType();
            List<Disciplina> disciplinas = new Gson().fromJson(response, listType);
            return disciplinas;
        } catch (MalformedURLException ex) {
        	throw new ConnectionException(ex.getLocalizedMessage());
        } catch (IOException ex) {
        	throw new ConnectionException(ex.getLocalizedMessage());
        }
    }

    public List<Turma> getTurmas(Disciplina disciplina) throws ConnectionException {
        String url = CONSULTA_TURMAS + disciplina.getId().toString();
        System.out.println(url);
        if(disciplina.getTurmas() != null){
            return disciplina.getTurmas();
        }
        try {
            String response = fazerRequisicao(url, "GET");
            System.out.println("Response getTurmas: " + response);
            Type listType = new TypeToken<ArrayList<Turma>>() {
            }.getType();
            List<Turma> turmas = new Gson().fromJson(response, listType);
            for (Turma turma: turmas) {
            	turma.setDisciplina(disciplina);
            }
            disciplina.setTurmas(turmas);
            return turmas;
        } catch (MalformedURLException ex) {
        	throw new ConnectionException(ex.getLocalizedMessage());
        } catch (IOException ex) {
        	throw new ConnectionException(ex.getLocalizedMessage());
        }
    }
    
    public Disciplina coletarDisciplinaDaTurma(Turma turma) throws ConnectionException{
    	String url = CONSULTA_DISCIPLINA_DA_TURMA + turma.getId().toString();
    	System.out.println(url);
    	try {
			String response = fazerRequisicao(url, "GET");
			System.out.println("Response disciplinaDaTurma: " + response);
			return gson.fromJson(response, Disciplina.class);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ConnectionException(e.getLocalizedMessage());
		}
    	
    }
}
