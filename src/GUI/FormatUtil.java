package GUI;
import java.text.NumberFormat;
import java.util.Locale;
// hàm này để định dạng giá bán để hiển thị
public class FormatUtil {
    public static String formatCurrency(double amount) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        return formatter.format(amount) + " VND";
    }

    // hàm này để trả định dạng giá lại thành Double
public static double parseCurrencyToDouble(String formatted) {
    String cleaned = formatted.replace(".", "").replace(" VND", "").trim();
    return Double.parseDouble(cleaned);
}
}
