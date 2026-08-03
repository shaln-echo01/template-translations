package com.echo.translator.validation;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.echo.translator.exception.GeminiTranslationException;



class MjmlTranslationValidatorTest {


    private MjmlTranslationValidator validator;



    @BeforeEach
    void setUp() {

        validator =
                new MjmlTranslationValidator();
    }



    @Test
    void shouldAcceptValidMjml() {


        String validMjml =
                """
                <mjml>
                    <mj-body>
                        <mj-section>
                            <mj-text>
                                Hello
                            </mj-text>
                        </mj-section>
                    </mj-body>
                </mjml>
                """;


        assertDoesNotThrow(
                () ->
                    validator.validate(validMjml)
        );
    }



    @Test
    void shouldRejectEmptyContent() {


        assertThrows(
                GeminiTranslationException.class,
                () ->
                    validator.validate("")
        );
    }



    @Test
    void shouldRejectMissingOpeningMjmlTag() {


        String invalidMjml =
                """
                <mj-body>
                    <mj-section>
                        <mj-text>
                            Hello
                        </mj-text>
                    </mj-section>
                </mj-body>
                </mjml>
                """;


        assertThrows(
                GeminiTranslationException.class,
                () ->
                    validator.validate(invalidMjml)
        );
    }



    @Test
    void shouldRejectMissingClosingMjmlTag() {


        String invalidMjml =
                """
                <mjml>
                    <mj-body>
                        <mj-section>
                            <mj-text>
                                Hello
                            </mj-text>
                        </mj-section>
                    </mj-body>
                """;


        assertThrows(
                GeminiTranslationException.class,
                () ->
                    validator.validate(invalidMjml)
        );
    }
}