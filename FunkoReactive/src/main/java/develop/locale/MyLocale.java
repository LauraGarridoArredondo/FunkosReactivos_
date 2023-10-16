package develop.locale;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
/**
 * Clase de utilidad para trabajar con configuraciones de localizacion personalizadas.
 * Esta clase proporciona metodos estaticos para formatear fechas y valores monetarios en un formato especifico
 * definido por la configuracion regional de Espana.
 *
 * @version 1.0
 */
public class MyLocale {
    // Configuracion regional para el espanol de Espana
    private static final Locale locale = new Locale("es","ES");

    /**
     * Formatea una fecha local en un formato especifico de acuerdo con la configuracion regional.
     *
     * @param date La fecha local que se formateara.
     * @return La fecha formateada en el formato deseado.
     */
    public static String toLocalDate(LocalDate date) {
        return date.format(
                DateTimeFormatter
                        .ofLocalizedDate(FormatStyle.MEDIUM).withLocale(locale)
        );
    }
    /**
     * Formatea un valor monetario en el formato de moneda especifico de acuerdo con la configuracion regional.
     *
     * @param money El valor monetario que se formateara.
     * @return El valor monetario formateado en el formato de moneda deseado.
     */
    public static String toLocalMoney(double money) {
        return NumberFormat.getCurrencyInstance(locale).format(money);
    }

}