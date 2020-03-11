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
public class TituloProfesional {
    private int idTituloProfesional;
    private Titulo titulo;
    private Profesional profesional;
    private String matricula;

    public int getIdTituloProfesional() {
        return idTituloProfesional;
    }

    public void setIdTituloProfesional(int idTituloProfesional) {
        this.idTituloProfesional = idTituloProfesional;
    }

    public Titulo getTitulo() {
        return titulo;
    }

    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    
}
