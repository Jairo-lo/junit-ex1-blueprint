package ec.edu.epn;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setup() {
        calculator = new Calculator();
    }

    @AfterEach
    void tearDown() {
        calculator = null;
    }

    // --- Pruebas de Adición (Parametrizadas) ---
    @ParameterizedTest
    @CsvSource({
        "1, 2, 3",
        "5, 7, 12",
        "-1, 1, 0",
        "0, 0, 0",
        "-5, -3, -8",
        "100, 200, 300"
    })
    void add_MultipleValues_ReturnsCorrectSum(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b));
    }

    // --- Pruebas de Sustracción (Parametrizadas) ---
    @ParameterizedTest
    @CsvSource({
        "10, 4, 6",
        "5, 5, 0",
        "0, 5, -5",
        "-5, -3, -2",
        "100, 50, 50",
        "-10, 5, -15"
    })
    void subtract_MultipleValues_ReturnsCorrectDifference(int a, int b, int expected) {
        assertEquals(expected, calculator.subtract(a, b));
    }

    // --- Pruebas de Multiplicación (Parametrizadas) ---
    @ParameterizedTest
    @CsvSource({
        "6, 7, 42",
        "5, 5, 25",
        "0, 10, 0",
        "-5, 3, -15",
        "-4, -5, 20",
        "10, 0, 0"
    })
    void multiply_MultipleValues_ReturnsCorrectProduct(int a, int b, int expected) {
        assertEquals(expected, calculator.multiply(a, b));
    }

    // --- NUEVA PRUEBA DE DIVISIÓN CON assertAll ---
    @Test
    void divide_DosNumerosPositivos_retornaCocienteCorrecto() {
        // Arrange
        int a = 10;
        int b = 2;

        // Act
        double result = calculator.divide(a, b);

        // Assert: Uso de assertAll para múltiples verificaciones simultáneas
        assertAll(
            // El cociente debería ser 5.0
            () -> assertEquals(5.0, result, "El cociente debería ser 5.0"),
            // El resultado debe ser positivo
            () -> assertTrue(result > 0, "El resultado debe ser positivo")
        );
    }
    
    // --- Pruebas de División (Parametrizadas) ---
    @ParameterizedTest
    @CsvSource({
        "7, 2, 3.5",
        "10, 2, 5.0",
        "9, 3, 3.0",
        "-10, 2, -5.0",
        "15, 4, 3.75",
        "0, 5, 0.0" 
    })
    void divide_MultipleValues_ReturnsCorrectQuotient(int a, int b, double expected) {
        assertEquals(expected, calculator.divide(a, b), 0.0001); 
    }

    // --- Prueba de Excepción de División por Cero ---
    @Test
    void divide_DivideByZero_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(5, 0);
        });
        assertEquals("The divisor cannot be zero.", exception.getMessage());
    }

    // --- Pruebas de Paridad (Parametrizadas) ---
    @ParameterizedTest
    @ValueSource(ints = {4, 0, -2, 100, 8})
    void isEven_EvenNumbers_ReturnsTrue(int number) {
        assertTrue(calculator.isEven(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, -1, 7, 101, 9})
    void isEven_OddNumbers_ReturnsFalse(int number) {
        assertFalse(calculator.isEven(number));
    }
}