package br.com.clinicsystem.agendaconsultoria.core.agenda;

import java.util.Date;
import java.util.Objects;

public class AgendaEntity {

    private Long id;
    private Date horario;
    private String fk_idClinica;
    private String fk_idPaciente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public String getFk_idClinica() {
        return fk_idClinica;
    }

    public void setFk_idClinica(String fk_idClinica) {
        this.fk_idClinica = fk_idClinica;
    }

    public String getFk_idPaciente() {
        return fk_idPaciente;
    }

    public void setFk_idPaciente(String fk_idPaciente) {
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
