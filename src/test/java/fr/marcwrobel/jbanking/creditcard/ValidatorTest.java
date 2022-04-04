package fr.marcwrobel.jbanking.creditcard;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorTest {

  private static Stream<Arguments> isValidArgs() {
    return Stream.of(
      Arguments.of("4695106544498469", true),
      Arguments.of("5474716219913354", true),
      Arguments.of("6011227151245712", true),
      Arguments.of("19238479120874", false),
      Arguments.of("string", false)
    );
  }

  @ParameterizedTest
  @CsvSource({
    "4695106544498469,Visa",
    "5474716219913354,MasterCard",
    "6011227151245712,Discover",
    "372387408115435,Amex",
    "3559693849900718,JCB",
    "30036592321275,Diners",
    "19238479120874,Not Valid"
  })
  void cardTypeTest(String input, String expected) {
    Validator validator = new Validator();
    String result = validator.cardType(input);
    assertEquals(result, expected);
  }

  @ParameterizedTest
  @MethodSource("isValidArgs")
  void isValidCardTest(String input, boolean expected) {
    Validator validator = new Validator();
    boolean result = validator.isValidCard(input);
    assertEquals(result, expected);
  }
}
