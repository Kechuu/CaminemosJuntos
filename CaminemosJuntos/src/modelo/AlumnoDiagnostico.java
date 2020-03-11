/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author javier
 */
public class AlumnoDiagnostico {
    private int idAlumnoDiagnostico;
    private Alumno alumno;
    private Diagnostico diagnostico;

    public int getIdAlumnoDiagnostico() {
        return idAlumnoDiagnostico;
    }

    public void setIdAlumnoDiagnostico(int idAlumnoDiagnostico) {
        this.idAlumnoDiagnostico = idAlumnoDiagnostico;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }
    
}
