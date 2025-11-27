package ec.edu.epn;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class StringValidatorTest {

    private StringValidator validator;

    @BeforeEach
    void setup() {
        validator = new StringValidator();
    }

    @AfterEach
    void tearDown() {
        validator = null;
    }

    // --- Pruebas de Éxito (Parametrizadas) ---
    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World", "Test123", "Valid Input", "a", "123", " text with spaces "})
    void testValidateNotEmpty_ValidInputs_NoException(String input) {
        // Verifica que no se lance ninguna excepción para entradas válidas
        assertDoesNotThrow(() -> validator.validateNotEmpty(input));
    }

    // --- Prueba para Input Nulo (Parametrizada) ---
    @ParameterizedTest
    @NullSource
    void testValidateNotEmpty_NullInput_ThrowsException(String input) {
        // Verifica la excepción y el mensaje para input nulo
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.validateNotEmpty(input);
        });
        assertEquals("Input cannot be null.", exception.getMessage());
    }

    // --- Pruebas para Strings Vacíos/Whitespace (Parametrizadas) ---
    @ParameterizedTest
    // "", "\t", "\n" y " \t\n " son considerados vacíos después de trim()
    @ValueSource(strings = {"", " ", "\t", "\n", " \t\n "})
    void testValidateNotEmpty_WhitespaceStrings_ThrowsException(String input) {
        // Verifica la excepción y el mensaje para strings vacíos o con solo espacios
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.validateNotEmpty(input);
        });
        assertEquals("Input cannot be empty.", exception.getMessage());
    }
}