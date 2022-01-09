package de.hsw.iban;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import de.hsw.iban.utils.IbanUtil;

public class IbanUtilTests {

    /**
     * Einfacher Test für erfolgreiche Erstellung
     */
    @Test
    public void createTest() {
        assertEquals("DE11123456781234567890", IbanUtil.create("DE", "1234567890", "12345678"));
    }

    /**
     * Weitere Tests für erfolgreiche Kombinationen - in der Regel einzelne Tests aber hier in einen Test zusammengeführt
     */
    @Test
    public void createFurtherTests() {
        assertEquals("DE15111111110000000001", IbanUtil.create("DE", "1", "11111111"));
        assertEquals("DE38222222220000000001", IbanUtil.create("DE", "01", "22222222"));
        assertEquals("DE45333333330000000101", IbanUtil.create("DE", "101", "33333333"));
        assertEquals("DE68444444440000000101", IbanUtil.create("DE", "0101", "44444444"));
        assertEquals("DE43555555550000010101", IbanUtil.create("DE", "10101", "55555555"));
        assertEquals("DE66666666660000010101", IbanUtil.create("DE", "010101", "66666666"));
        assertEquals("DE42777777770001010101", IbanUtil.create("DE", "1010101", "77777777"));
        assertEquals("DE65888888880001010101", IbanUtil.create("DE", "01010101", "88888888"));
        assertEquals("DE21888888880101010101", IbanUtil.create("DE", "101010101", "88888888"));
        assertEquals("DE21888888880101010101", IbanUtil.create("DE", "0101010101", "88888888"));
        assertEquals("DE33888888881010101010", IbanUtil.create("DE", "1010101010", "88888888"));
    }

    /**
     * Einfacher Test für einen ungültigen Parameter
     */
    @Test
    public void createFailTest() {
        assertThrows(IllegalArgumentException.class, () -> IbanUtil.create("CH", "1234567890", "12345678"));
    }

    /**
     * Weitere Tests für ungültige Parameter - in der Regel einzelne Tests aber hier in einen Test zusammengeführt
     */
    @Test
    public void createFailFurtherTest() {
        assertThrows(IllegalArgumentException.class, () -> IbanUtil.create(null, "1234567890", "12345678"));
        assertThrows(IllegalArgumentException.class, () -> IbanUtil.create("DE", null, "12345678"));
        assertThrows(IllegalArgumentException.class, () -> IbanUtil.create("DE", "1234567890", null));

        assertThrows(IllegalArgumentException.class, () -> IbanUtil.create("", "1234567890", "12345678"));
        assertThrows(IllegalArgumentException.class, () -> IbanUtil.create("DE", "", "12345678"));
        assertThrows(IllegalArgumentException.class, () -> IbanUtil.create("DE", "1234567890", ""));

        assertThrows(IllegalArgumentException.class, () -> IbanUtil.create("DEX", "1234567890", "12345678"));
        assertThrows(IllegalArgumentException.class, () -> IbanUtil.create("DE", "123456789012", "12345678"));
        assertThrows(IllegalArgumentException.class, () -> IbanUtil.create("DE", "1234567890", "1234567812"));

        assertThrows(IllegalArgumentException.class, () -> IbanUtil.create("00", "1234567890", "12345678"));
        assertThrows(IllegalArgumentException.class, () -> IbanUtil.create("DE", "XXXXX", "12345678"));
        assertThrows(IllegalArgumentException.class, () -> IbanUtil.create("DE", "1234567890", "XXX"));

        assertThrows(IllegalArgumentException.class, () -> IbanUtil.create("DE", "1234567890", "00000000"));
        assertThrows(IllegalArgumentException.class, () -> IbanUtil.create("DE", "1234567890", "99999999"));
    }

    @Test
    public void validateTest() {
        assertTrue(IbanUtil.validate("DE11123456781234567890"));
    }

    @Test
    public void validateFailTest() {
        assertFalse(IbanUtil.validate("DE22123456781234567890"));
    }

}
