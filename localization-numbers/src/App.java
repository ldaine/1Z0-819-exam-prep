import java.text.NumberFormat;
import java.util.Locale;

public class App {
    public static void main(String[] args) throws Exception {
        double num = 26326.23423476d;
        NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));

        nf.setMaximumFractionDigits(2);
        String value = nf.format(num);
        System.out.println("Formatted number: " + value);
    }
}
