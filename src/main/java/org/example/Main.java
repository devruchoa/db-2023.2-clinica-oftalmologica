package org.example;

import org.example.dao.ConsultaMedicaDAO;
import org.example.dao.EspecificacaoLenteDAO;
import org.example.dao.MedicoDAO;
import org.example.dao.PacienteDAO;
import org.example.model.ConsultaMedica;
import org.example.model.EspecificacaoLente;
import org.example.model.Medico;
import org.example.model.Paciente;

import java.util.List;

public class Main {

    static PacienteDAO pacienteDAO =  new PacienteDAO();
    static MedicoDAO medicoDAO = new MedicoDAO();
    static ConsultaMedicaDAO consultaMedicaDAO = new ConsultaMedicaDAO();

    public static void main(String[] args) {

        Paciente pacienteMaisVelho = pacienteDAO.selectPacienteMaisVelhoComConsulta();
        System.out.println("Paciente mais velho com consulta: " + pacienteMaisVelho);

        Paciente pacienteMaisNovo = pacienteDAO.selectPacienteMaisNovoComConsulta();
        System.out.println("Paciente mais novo com consulta: " + pacienteMaisNovo);

        Medico medicoComMaisPacientes = medicoDAO.medicoComMaisPacientes();
        System.out.println("Médico com mais pacientes: " + medicoComMaisPacientes);

        Medico medicoComMenosPacientes = medicoDAO.medicoComMenosPacientes();
        System.out.println("Médico com menos pacientes: " + medicoComMenosPacientes);

        Medico medicoComConsultaMaisRecente = medicoDAO.medicoComConsultasMaisRecente();
        System.out.println("Médico com consulta mais recente: " + medicoComConsultaMaisRecente);

        ConsultaMedica consultaMedicaMaisAntiga = consultaMedicaDAO.consultaMedicaMaisAntiga();
        System.out.println("Consulta médica mais antiga: " + consultaMedicaMaisAntiga);

        ConsultaMedica consultaMedicaMaisRecente = consultaMedicaDAO.consultaMedicaMaisRecente();
        System.out.println("Consulta médica mais recente: " + consultaMedicaMaisRecente);

        ConsultaMedica consultaMedicaMaisAntigaPorPaciente = consultaMedicaDAO.consultaMedicaMaisAntigaPorPaciente(110);
        System.out.println("Consulta médica mais antiga por paciente: " + consultaMedicaMaisAntigaPorPaciente);
    }
}
