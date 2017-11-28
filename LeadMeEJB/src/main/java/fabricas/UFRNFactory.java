/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabricas;

import service.ComparadorMatrizDisciplina;
import service.ComparadorMatrizDisciplinaUFRN;
import service.MatriculaService;
import service.MatriculaServiceUFRN;
import service.RequisitosService;
import service.RequisitosServiceUFRN;

/**
 *
 * @author rafao
 */
public class UFRNFactory implements AbstractFactory {


    @Override
    public RequisitosService createRequisitosService() {
        return new RequisitosServiceUFRN();
    }

    @Override
    public MatriculaService createMatriculaService() {
        return new MatriculaServiceUFRN();
    }

    @Override
    public ComparadorMatrizDisciplina createComparadorMatrizDisciplina() {
        return new ComparadorMatrizDisciplinaUFRN();
    }

}
