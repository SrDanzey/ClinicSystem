package br.com.clinicsystem.agendaconsultoria.core.entity;

import java.util.Objects;

public class AgendaEntity {

    private Long id;
    private String horario;
    private Long fk_idClinicaMedico;
    private Long fk_idPaciente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Long getFk_idClinicaMedico() {
        return fk_idClinicaMedico;
    }

    public void setFk_idClinicaMedico(Long fk_idClinicaMedico) {
        this.fk_idClinicaMedico = fk_idClinicaMedico;
    }

    public Long getFk_idPaciente() {
        return fk_idPaciente;
    }

    public void setFk_idPaciente(Long fk_idPaciente) {
        this.fk_idPaciente = fk_idPaciente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgendaEntity that = (AgendaEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
