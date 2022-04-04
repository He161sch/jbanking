package fr.marcwrobel.jbanking.creditcard;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
  
private final String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
    "(?<mastercard>5[1-5][0-9]{14})|" +
    "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" +
    "(?<amex>3[47][0-9]{13})|" +
    "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" +
    "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$";

private final Pattern pattern = Pattern.compile(regex);

  public Validator() {}



  public String cardType(String cards) {
    
   // if (isValidCard(card)) {
    String card = cards.replaceAll("-", "");
    Matcher matcher = pattern.matcher(card);
      //If card is valid then verify which group it belong
      if(matcher.group("visa") != null) {
        return "Visa";
      } else if (matcher.group("mastercard") != null) {
      return "MasterCard";
      } else if (matcher.group("discover") != null) {
        return "Discover";
      } else if (matcher.group("amex") != null) {
        return "Amex";
      } else if (matcher.group("diners") != null) {
        return "Diners";
      } else if (matcher.group("jcb") != null) {
        return "JCB";
      }
    //}
    return "Not Valid";
  } 

  public boolean isValidCard(String cards) {
    //Strip all hyphens
    String card = cards.replaceAll("-", "");

    //Match the card
    Matcher matcher = pattern.matcher(card);

    return matcher.matches() && checkSum(card);
  }

  private static boolean checkSum(String card) {
    int sum = 0;
    boolean alternate = false;
    for (int i = card.length() - 1; i >= 0; i--)
    {
      int n = Integer.parseInt(card.substring(i, i + 1));
      if (alternate)
      {
        n *= 2;
        if (n > 9)
        {
          n = (n % 10) + 1;
        }
      }
      sum += n;
      alternate = !alternate;
    }
    return (sum % 10 == 0);

  }
  public static void main(String[] args) {
    Validator validator = new Validator();

    System.out.println(validator.cardType("5328327812382837"));
  }

}
