/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabricas;

import service.ComparadorMatrizDisciplina;
import service.MatriculaService;
import service.RequisitosService;

/**
 *
 * @author rafao
 */
public interface AbstractFactory {
    public RequisitosService createRequisitosService();
    public MatriculaService createMatriculaService();
    public ComparadorMatrizDisciplina createComparadorMatrizDisciplina();
}
