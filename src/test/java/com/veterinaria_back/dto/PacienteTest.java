package com.veterinaria_back.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PacienteTest {

    private Paciente expectedPaciente, actualPaciente, differentPaciente, nullPaciente;

    @BeforeEach
    void setUp() {
        expectedPaciente = new Paciente();
        expectedPaciente.setId(1);
        expectedPaciente.setNombre("Max");
        expectedPaciente.setFecha_nacimiento(new Date(2023, 01, 23));
        expectedPaciente.setFecha_registro(new Date(2023, 05, 9));
        expectedPaciente.setId_especie(2);
        expectedPaciente.setId_raza(45);
        expectedPaciente.setId_dueño(4);

        actualPaciente = new Paciente();
        actualPaciente.setId(1);
        actualPaciente.setNombre("Max");
        actualPaciente.setFecha_nacimiento(new Date(2023, 01, 23));
        actualPaciente.setFecha_registro(new Date(2023, 05, 9));
        actualPaciente.setId_especie(2);
        actualPaciente.setId_raza(45);
        actualPaciente.setId_dueño(4);

        differentPaciente = new Paciente();
        differentPaciente.setId(3);
        differentPaciente.setNombre("Luna");
        differentPaciente.setFecha_nacimiento(new Date(2021, 12, 04));
        differentPaciente.setFecha_registro(new Date(2023, 02, 22));
        differentPaciente.setId_especie(5);
        differentPaciente.setId_raza(99);
        differentPaciente.setId_dueño(8);

        nullPaciente = null;
    }

    @Test
    void testPaciente() {
        assertEquals(expectedPaciente.getId(), actualPaciente.getId());
        assertEquals(expectedPaciente.getNombre(), actualPaciente.getNombre());
        assertEquals(expectedPaciente.getFecha_nacimiento(), actualPaciente.getFecha_nacimiento());
        assertEquals(expectedPaciente.getFecha_registro(), actualPaciente.getFecha_registro());
        assertEquals(expectedPaciente.getId_especie(), actualPaciente.getId_especie());
        assertEquals(expectedPaciente.getId_raza(), actualPaciente.getId_raza());
        assertEquals(expectedPaciente.getId_dueño(), actualPaciente.getId_dueño());
    }

    @Test
    void testEquals() {
        assertEquals(expectedPaciente, actualPaciente);
        assertNotEquals(expectedPaciente, differentPaciente);
        assertNull(nullPaciente);
    }

    @Test
    void testHashCode() {
        assertNotEquals(expectedPaciente.hashCode(), differentPaciente.hashCode());
        assertEquals(expectedPaciente.hashCode(), actualPaciente.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("Paciente(id=1, nombre=Max, " +
                        "fecha_nacimiento=Fri Feb 23 00:00:00 COT 3923, " +
                        "fecha_registro=Sat Jun 09 00:00:00 COT 3923, id_raza=45, " +
                        "nombre_raza=null, id_especie=2, nombre_especie=null, " +
                        "id_dueño=4, nombre_duenio=null, ciudad=null, " +
                        "tipo_identificacion=null, identificacion=null, " +
                        "direccion=null, telefono=null)",
                actualPaciente.toString());
    }

}