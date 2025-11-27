package ec.edu.epn;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxCalculatorTest {

    private TaxCalculator taxCalculator;

    @BeforeEach
    void setup() {
        taxCalculator = new TaxCalculator();
    }

    @AfterEach
    void tearDown() {
        taxCalculator = null;
    }

    /**
     * Fuente de datos para las pruebas de cálculo de impuestos.
     * Define (valor base, tasa de impuesto, resultado esperado).
     */
    static Stream<Arguments> taxCalculatorData() {
        return Stream.of(
            // Base Value, Tax Rate (decimal), Expected Total
            Arguments.of(100.0, 0.15, 115.0),    // 100 + (100 * 0.15) = 115
            Arguments.of(200.0, 0.10, 220.0),    // 200 + (200 * 0.10) = 220
            Arguments.of(58.5, 0.03, 60.255),    // Ejemplo con decimales
            Arguments.of(1000.0, 0.12, 1120.0),  // Impuesto común (12%)
            Arguments.of(75.0, 0.00, 75.0),      // Impuesto cero
            Arguments.of(8.0, 0.15, 9.2),        // Valor base pequeño
            Arguments.of(100.0, 0.0, 100.0),     // Otro caso de impuesto cero
            Arguments.of(258.5, 0.15, 297.275)   // Ejemplo de informe (258.5 * 1.15)
        );
    }

    @ParameterizedTest
    @MethodSource("taxCalculatorData")
    void calculateTax_MultipleTaxScenarios_ReturnsCorrectValue(double value, double tax, double expected) {
        // Arrange
        // (Datos proporcionados por @MethodSource)

        // Act
        double result = taxCalculator.calculateTax(value, tax);

        // Assert
        // Se usa 0.001 como delta para asegurar precisión en cálculos decimales
        assertEquals(expected, result, 0.001); 
    }
}