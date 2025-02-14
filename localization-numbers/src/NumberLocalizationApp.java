import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class App {
    public static void main(String[] args) throws Exception {
        double num = 26326.23423476d;
        NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));

        nf.setMaximumFractionDigits(2);
        String value = nf.format(num);
        System.out.println("Formatted number: " + value);

        // decimal format

        DecimalFormat df = new DecimalFormat("#0.00 Money");
        String valueDf = df.format(num);
        System.out.println("Custom Formatted number: " + valueDf);

    }
}
