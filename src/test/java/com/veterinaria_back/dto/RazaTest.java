package com.veterinaria_back.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RazaTest {

    private Raza expectedRaza, actualRaza, differentRaza, nullRaza;

    @BeforeEach
    void setUp() {
        expectedRaza = new Raza();
        expectedRaza.setId(1);
        expectedRaza.setNombre("Tetra neón");
        expectedRaza.setId_especie(3);

        actualRaza = new Raza();
        actualRaza.setId(1);
        actualRaza.setNombre("Tetra neón");
        actualRaza.setId_especie(3);

        differentRaza = new Raza();
        differentRaza.setId(5);
        differentRaza.setNombre("Guacamayo Bandera");
        differentRaza.setId_especie(7);

        nullRaza = null;
    }

    @Test
    void testRaza() {
        assertEquals(expectedRaza.getId(), actualRaza.getId());
        assertEquals(expectedRaza.getNombre(), actualRaza.getNombre());
        assertEquals(expectedRaza.getId_especie(), actualRaza.getId_especie());
    }

    @Test
    void testEquals() {
        assertEquals(expectedRaza, actualRaza);
        assertNotEquals(expectedRaza, differentRaza);
        assertNull(nullRaza);
    }

    @Test
    void testHashCode() {
        assertNotEquals(expectedRaza.hashCode(), differentRaza.hashCode());
        assertEquals(expectedRaza.hashCode(), actualRaza.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("Raza(id=1, nombre=Tetra neón, id_especie=3)", actualRaza.toString());
    }

}