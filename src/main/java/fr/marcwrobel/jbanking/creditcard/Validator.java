package fr.marcwrobel.jbanking.creditcard;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

  public static void main(String[] args) {
    List<String> cards = new ArrayList<String>();

    //Valid Credit Cards
    cards.add("5553457706656941");  //Masked to avoid any inconvenience unknowingly

    //Invalid Credit Card
    cards.add("xxxx-xxxx-xxxx-xxxx"); //Masked to avoid any inconvenience unknowingly

    String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
      "(?<mastercard>5[1-5][0-9]{14})|" +
      "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" +
      "(?<amex>3[47][0-9]{13})|" +
      "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" +
      "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$";

    Pattern pattern = Pattern.compile(regex);

    for (String card : cards) {
      //Strip all hyphens
      card = card.replaceAll("-", "");

      //Match the card
      Matcher matcher = pattern.matcher(card);

      System.out.println(matcher.matches());

      if (matcher.matches()) {
        //If card is valid then verify which group it belong
        if(matcher.group("visa") != null) {
          System.out.println("card Type: Visa");
        } else if (matcher.group("mastercard") != null) {
          System.out.println("card Type: MasterCard");
        } else if (matcher.group("discover") != null) {
          System.out.println("card Type: Discover");
        } else if (matcher.group("amex") != null) {
          System.out.println("card Type: AmericanExpress");
        } else if (matcher.group("diners") != null) {
          System.out.println("card Type: Diners");
        } else if (matcher.group("jcb") != null) {
          System.out.println("card Type: JCB");
        }
      }


    }
  }
}
