package com.formacionbdi.springboot.app.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilMathTest {

    @Test
    void suma() {
        assertEquals(3, UtilMath.suma(1, 2));
    }

    @Test
    void capitalizar() {
        assertEquals("HELLO", UtilMath.capitalizar("hola"));}

    @Test
    void capitalizar2() {
        assertEquals("Hola", UtilMath.capitalizar2("hola"));
    }
}