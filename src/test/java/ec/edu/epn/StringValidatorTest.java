package ec.edu.epn;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    // Clase interna para definir los datos de prueba
    private static class TestData {
        String input;
        boolean expected;

        TestData(String input, boolean expected) {
            this.input = input;
            this.expected = expected;
        }
    }

    // --- Pruebas de Pruebas Dinámicas para isPalindrome (@TestFactory) ---
    @TestFactory
    Collection<DynamicTest> dynamicPalindromeTests() {
        StringValidator stringValidator = new StringValidator(); // Instancia local para el factory

        // Lista de casos de prueba (input, expected)
        List<TestData> testDataList = Arrays.asList(
            new TestData("reconocer", true), // Palíndromo
            new TestData("oso", true),       // Palíndromo
            new TestData("La ruta natural", true), // Con espacios y mayúsculas
            new TestData("A luna ese anula", true), // Con espacios y mayúsculas
            new TestData("hola", false),     // No palíndromo
            new TestData("test", false),     // No palíndromo
            new TestData(null, false)       // Caso nulo
        );

        // Generación dinámica de tests a partir de la lista de datos
        return testDataList.stream()
            .map(data -> DynamicTest.dynamicTest(
                // Nombre del test basado en los datos para mejor reporte
                "isPalindrome('" + (data.input == null ? "null" : data.input) + "') debe ser " + data.expected,
                () -> {
                    // Act
                    boolean result = stringValidator.isPalindrome(data.input);
                    // Assert
                    assertEquals(data.expected, result); // Usar assertEquals para mejor mensaje de error
                }
            ))
            .collect(Collectors.toList());
    }

    // --- Pruebas de validateNotEmpty (Existentes del Lab 2/1) ---
    
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