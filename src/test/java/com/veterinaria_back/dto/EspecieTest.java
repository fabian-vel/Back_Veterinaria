package com.veterinaria_back.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EspecieTest {

    private Especie expectedEspecie, actualEspecie, differentEspecie, nullEspecie;

    @BeforeEach
    void setUp() {
        expectedEspecie = new Especie();
        expectedEspecie.setId(1);
        expectedEspecie.setNombre("Perro");
        expectedEspecie.setNombre_cientifico("Canis lupus familiaris");

        actualEspecie = new Especie();
        actualEspecie.setId(1);
        actualEspecie.setNombre("Perro");
        actualEspecie.setNombre_cientifico("Canis lupus familiaris");

        differentEspecie = new Especie();
        differentEspecie.setId(2);
        differentEspecie.setNombre("Gato");
        differentEspecie.setNombre_cientifico("Felis catus");

        nullEspecie = null;
    }

    @Test
    void testEspecie() {
        assertEquals(expectedEspecie.getId(),actualEspecie.getId());
        assertEquals(expectedEspecie.getNombre(), actualEspecie.getNombre());
        assertEquals(expectedEspecie.getNombre_cientifico(), actualEspecie.getNombre_cientifico());
    }

    @Test
    void testEquals() {
        assertEquals(expectedEspecie, actualEspecie);
        assertNotEquals(expectedEspecie, differentEspecie);
        assertNull(nullEspecie);
    }

    @Test
    void testHashCode() {
        assertNotEquals(expectedEspecie.hashCode(), differentEspecie.hashCode());
        assertEquals(expectedEspecie.hashCode(), actualEspecie.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("Especie(id=1, nombre=Perro, nombre_cientifico=Canis lupus familiaris)", actualEspecie.toString());
    }

}