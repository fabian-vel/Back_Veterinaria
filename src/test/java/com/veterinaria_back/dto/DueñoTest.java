package com.veterinaria_back.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DueñoTest {

    private Dueño expectedDueño, actualDueño, differentDueño, nullDueño;

    @BeforeEach
    void setUp() {

        expectedDueño = new Dueño();
        expectedDueño.setId(1);
        expectedDueño.setNombre("Pedro Paternina");
        expectedDueño.setTipo_identificacion("CC");
        expectedDueño.setIdentificacion("678435908");
        expectedDueño.setCiudad("Monteria");
        expectedDueño.setDireccion("Cr. 12 Cl. 4A");
        expectedDueño.setTelefono("3156743400");

        actualDueño = new Dueño();
        actualDueño.setId(1);
        actualDueño.setNombre("Pedro Paternina");
        actualDueño.setTipo_identificacion("CC");
        actualDueño.setIdentificacion("678435908");
        actualDueño.setCiudad("Monteria");
        actualDueño.setDireccion("Cr. 12 Cl. 4A");
        actualDueño.setTelefono("3156743400");

        differentDueño = new Dueño();
        differentDueño.setId(2);
        differentDueño.setNombre("Sara Solano");
        differentDueño.setTipo_identificacion("CC");
        differentDueño.setIdentificacion("10678903400");
        differentDueño.setCiudad("Medellin");
        differentDueño.setDireccion("Cr. 21A Cl. 10");
        differentDueño.setTelefono("3214569087");

        nullDueño = null;
    }

    @Test
    void testDueño() {
        assertEquals(expectedDueño.getId(), actualDueño.getId());
        assertEquals(expectedDueño.getNombre(), actualDueño.getNombre());
        assertEquals(expectedDueño.getTipo_identificacion(), actualDueño.getTipo_identificacion());
        assertEquals(expectedDueño.getIdentificacion(), actualDueño.getIdentificacion());
        assertEquals(expectedDueño.getCiudad(), actualDueño.getCiudad());
        assertEquals(expectedDueño.getDireccion(), actualDueño.getDireccion());
        assertEquals(expectedDueño.getTelefono(), actualDueño.getTelefono());
    }

    @Test
    void testEquals() {
        assertEquals(expectedDueño, actualDueño);
        assertNotEquals(expectedDueño, differentDueño);
        assertNull(nullDueño);
    }

    @Test
    void testHashCode() {
        assertNotEquals(expectedDueño.hashCode(), differentDueño.hashCode());
        assertEquals(expectedDueño.hashCode(), actualDueño.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("Dueño(id=1, nombre=Pedro Paternina, tipo_identificacion=CC, " +
                        "identificacion=678435908, ciudad=Monteria, direccion=Cr. 12 Cl. 4A, telefono=3156743400)",
                actualDueño.toString());
    }

}