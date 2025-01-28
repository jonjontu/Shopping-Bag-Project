package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShoppingItemTests {
    ShoppingItem s1;
    ShoppingItem s2;
    ShoppingItem s3;
    ShoppingItem s4;
    ShoppingItem s5;
    ShoppingItem s6;
    ShoppingItem s7;
    ShoppingItem s8;

    @BeforeEach
    void runBefore() {
        s1 = new ShoppingItem("Apple",3,10,21,21);
        s2 = new ShoppingItem("Chocolate Bar",1,11,31,21);
        s3 = new ShoppingItem("Jeans",1,11,30,21);
        s4 = new ShoppingItem("McChickens",3,13,40,21);
        s5 = new ShoppingItem("Flags",3,14,1,21);
        s6 = new ShoppingItem("Keyboard",3,1,21,21);
        s7 = new ShoppingItem("T-Shirt",3,12,21,21);
        s8 = new ShoppingItem("Heart",1,2,29,21);
    }

    @Test
    void validMonth() {
        assertTrue(s1.validMonth());
        assertTrue(s2.validMonth());
        assertTrue(s3.validMonth());
        assertFalse(s4.validMonth());
        assertFalse(s5.validMonth());
        assertTrue(s6.validMonth());
        assertTrue(s7.validMonth());
        assertTrue(s8.validMonth());
    }

    @Test
    void validDay() {
        assertTrue(s1.validDay());
        assertFalse(s2.validDay());
        assertTrue(s3.validDay());
        assertFalse(s4.validDay());
        assertTrue(s5.validDay());
        assertTrue(s6.validDay());
        assertTrue(s7.validDay());
        assertFalse(s8.validDay());
    }

    @Test
    void validDate() {
        assertTrue(s1.validDate());
        assertFalse(s2.validDate());
        assertTrue(s3.validDate());
        assertFalse(s4.validDate());
        assertFalse(s5.validDate());
        assertTrue(s6.validDate());
        assertTrue(s7.validDate());
        assertFalse(s8.validDate());
    }

    @Test
    void getName() {
        assertEquals("Apple",s1.getName());
        assertEquals("Chocolate Bar",s2.getName());
        assertEquals("Jeans",s3.getName());
        assertEquals("McChickens",s4.getName());
        assertEquals("Flags",s5.getName());
        assertEquals("Keyboard",s6.getName());
        assertEquals("T-Shirt",s7.getName());
        assertEquals("Heart",s8.getName());
    }

    @Test
    void getQuantity() {
        assertEquals(3, s1.getQuantity());
        assertEquals(1, s2.getQuantity());
        assertEquals(1, s3.getQuantity());
        assertEquals(3, s4.getQuantity());
        assertEquals(3, s5.getQuantity());
        assertEquals(3, s6.getQuantity());
        assertEquals(3, s7.getQuantity());
        assertEquals(1, s8.getQuantity());
    }

    @Test
    void getMonth() {
        assertEquals(10, s1.getMonth());
        assertEquals(11, s2.getMonth());
        assertEquals(11, s3.getMonth());
        assertEquals(13, s4.getMonth());
        assertEquals(14, s5.getMonth());
        assertEquals(1, s6.getMonth());
        assertEquals(12, s7.getMonth());
        assertEquals(2, s8.getMonth());
    }


    @Test
    void getDay() {
        assertEquals(21, s1.getDay());
        assertEquals(31, s2.getDay());
        assertEquals(30, s3.getDay());
        assertEquals(40, s4.getDay());
        assertEquals(1, s5.getDay());
        assertEquals(21, s6.getDay());
        assertEquals(21, s7.getDay());
        assertEquals(29, s8.getDay());
    }

    @Test
    void getYear() {
        assertEquals(21, s1.getYear());
        assertEquals(21, s2.getYear());
        assertEquals(21, s3.getYear());
        assertEquals(21, s4.getYear());
        assertEquals(21, s5.getYear());
        assertEquals(21, s6.getYear());
        assertEquals(21, s7.getYear());
        assertEquals(21, s8.getYear());
    }
}