import model.ExchangeRateData;
import service.CurrencyService;

import java.util.*;

public class Main {
    private static final List<String> conversionHistory = new ArrayList<>();
    private static final Map<String, List<String>> continentCurrencies = new HashMap<>();

    public static void main(String[] args) {
        CurrencyService currencyService = new CurrencyService();
        ExchangeRateData exchangeRateData = currencyService.fetchExchangeRates();

        if (exchangeRateData == null) {
            System.out.println("No se pudieron obtener las tasas de cambio.");
            return;
        }

        Map<String, Double> rates = exchangeRateData.getConversionRates();
        Scanner scanner = new Scanner(System.in);

        // Inicializar las monedas por continente
        loadCurrencyList();

        while (true) {
            displayMenu();
            System.out.print("Elija una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (option) {
                case 1 -> convertCurrency(rates, scanner);
                case 2 -> displayCurrencies(scanner);  // Mostrar monedas organizadas por continente
                case 3 -> showConversionHistory(scanner);
                case 4 -> displayInstructions(scanner);
                case 5 -> {
                    System.out.println("\n¡Gracias por usar el conversor de monedas JPC!");
                    return;
                }
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n+----------------------------+");
        System.out.println("| MI CONVERSOR DE MONEDAS JPC |");
        System.out.println("+-----------------------------+");
        System.out.println("| 1. Conversión de monedas    |");
        System.out.println("| 2. Ver lista de monedas     |");
        System.out.println("| 3. Historial de conversiones|");
        System.out.println("| 4. Instrucciones            |");
        System.out.println("| 5. Salir                    |");
        System.out.println("+-----------------------------+");
    }

    private static void convertCurrency(Map<String, Double> rates, Scanner scanner) {
        while (true) {
            System.out.println("\n+-------------------------------+");
            System.out.println("|   === Conversión de Monedas === |");
            System.out.println("+-------------------------------+");
            System.out.println("| Pulse 'M' para regresar al menú principal.");
            System.out.print("|  Introduzca la moneda de origen (por ejemplo, USD): ");
            String fromCurrency = scanner.nextLine().toUpperCase();
            if (fromCurrency.equals("M")) return;

            System.out.print("| Introduzca la moneda de destino (por ejemplo, EUR) ");
            String toCurrency = scanner.nextLine().toUpperCase();
            if (toCurrency.equals("M")) return;

            System.out.print("| Introduzca la cantidad a convertir: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("M")) return;

            double amount;
            try {
                amount = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("| Cantidad no válida. Intente de nuevo.");
                continue;
            }

            if (!rates.containsKey(fromCurrency) || !rates.containsKey(toCurrency)) {
                System.out.println("| Una o ambas monedas no son válidas. Intente de nuevo.");
                continue;
            }

            double fromRate = rates.get(fromCurrency);
            double toRate = rates.get(toCurrency);

            double convertedAmount = (amount / fromRate) * toRate;

            System.out.printf("| %.2f %s equivalen a %.2f %s%n", amount, fromCurrency, convertedAmount, toCurrency);

            String record = String.format("%.2f %s -> %.2f %s", amount, fromCurrency, convertedAmount, toCurrency);
            conversionHistory.add(record);

            System.out.println("\n| ¿Desea hacer otra conversión? (S(si)/N(no))");
            String userChoice = scanner.nextLine().toUpperCase();
            if (userChoice.equals("N")) {
                return; // Regresar al menú principal
            }
        }
    }

    private static void showConversionHistory(Scanner scanner) {
        System.out.println("\n+------------------------------------+");
        System.out.println("|   === Historial de Conversiones === |");
        System.out.println("+------------------------------------+");
        if (conversionHistory.isEmpty()) {
            System.out.println("| No se han realizado conversiones.");
        } else {
            conversionHistory.forEach(record -> System.out.println("| " + record));
        }
        System.out.println("+------------------------------------+");
        System.out.println("\n| Pulse 'M' para regresar al menú.");
        scanner.nextLine(); // Esperar que el usuario presione enter
    }

    private static void displayInstructions(Scanner scanner) {
        System.out.println("\n+-------------------------------+");
        System.out.println("|        === Instrucciones ===    |");
        System.out.println("+-------------------------------+");
        System.out.println("| 1. Selecciona 'Conversión de monedas' para realizar una conversión.");
        System.out.println("| 2. Ingresa las monedas de origen y destino, así como la cantidad que deseas convertir.");
        System.out.println("| 3. Si deseas ver el historial de conversiones, selecciona 'Historial de conversiones'.");
        System.out.println("| 4. Para consultar las monedas disponibles, selecciona 'Ver lista de monedas disponibles'.");
        System.out.println("| 5. Puedes salir en cualquier momento seleccionando 'Salir'.");
        System.out.println("+-------------------------------+");
        System.out.println("\n| Pulse 'M' para regresar al menú.");
        scanner.nextLine(); // Esperar que el usuario presione enter
    }

    private static void loadCurrencyList() {
        // Monedas por continente: América
        continentCurrencies.put("América", Arrays.asList(
                "USD - Estados Unidos, El Salvador, Panamá, Ecuador, Islas Turcas y Caicos, Islas Vírgenes de EE.UU., Puerto Rico, Otros países del Caribe y América Central",
                "ARS - Argentina",
                "BBD - Barbados",
                "BOB - Bolivia",
                "BRL - Brasil",
                "BSD - Bahamas",
                "BZD - Belice",
                "CAD - Canadá",
                "COP - Colombia",
                "CRC - Costa Rica",
                "CUP - Cuba",
                "CVE - Cabo Verde",
                "DOP - República Dominicana",
                "EGP - Egipto",
                "GTQ - Guatemala",
                "GYD - Guyana",
                "HNL - Honduras",
                "MXN - México",
                "NIO - Nicaragua",
                "PAB - Panamá",
                "PEN - Perú",
                "PHP - Filipinas",
                "PYG - Paraguay",
                "SRD - Surinam",
                "UYU - Uruguay"
        ));

        // Monedas por continente: Europa
        continentCurrencies.put("Europa", Arrays.asList(
                "ALL - Albania",
                "AMD - Armenia",
                "BAM - Bosnia y Herzegovina",
                "BGN - Bulgaria",
                "BHD - Baréin",
                "CHF - Suiza",
                "CLP - Chile",
                "CNY - China",
                "DKK - Dinamarca",
                "EUR - Francia, Alemania, Italia, España, Portugal, Irlanda, Grecia, Países Bajos, Bélgica, Luxemburgo, Austria, Finlandia, Eslovaquia, Estonia, Letonia, Lituania, Malta, Chipre, Eslovenia",
                "GBP - Reino Unido, Gibraltar",
                "GEL - Georgia",
                "HRK - Croacia",
                "HUF - Hungría",
                "ISK - Islandia",
                "RON - Rumanía",
                "RSD - Serbia",
                "RUB - Rusia",
                "SEK - Suecia",
                "TRY - Turquía",
                "UAH - Ucrania"
        ));

        // Monedas por continente: África
        continentCurrencies.put("África", Arrays.asList(
                "AOA - Angola",
                "BIF - Burundi",
                "CDF - República Democrática del Congo",
                "DJF - Yibuti",
                "DZD - Argelia",
                "ERN - Eritrea",
                "ETB - Etiopía",
                "GHS - Ghana",
                "GNF - Guinea",
                "KMF - Comoras",
                "KES - Kenia",
                "KGS - Kirguistán",
                "KWD - Kuwait",
                "LSL - Lesoto",
                "LYD - Libia",
                "MGA - Madagascar",
                "MWK - Malaui",
                "MZN - Mozambique",
                "NAD - Namibia",
                "NGN - Nigeria",
                "SLL - Sierra Leona",
                "SOS - Somalia",
                "TND - Túnez",
                "UGX - Uganda",
                "ZAR - Sudáfrica",
                "ZMW - Zambia",
                "ZWL - Zimbabue"
        ));

        // Monedas por continente: Asia
        continentCurrencies.put("Asia", Arrays.asList(
                "AFN - Afganistán",
                "BDT - Bangladés",
                "INR - India",
                "IQD - Irak",
                "IRR - Irán",
                "JOD - Jordania",
                "JPY - Japón",
                "KWD - Kuwait",
                "LKR - Sri Lanka",
                "MYR - Malasia",
                "NPR - Nepal",
                "PKR - Pakistán",
                "PHP - Filipinas",
                "QAR - Catar",
                "RUB - Rusia",
                "SAR - Arabia Saudita",
                "SGD - Singapur",
                "THB - Tailandia",
                "TJS - Tayikistán",
                "TRY - Turquía",
                "TWD - Taiwán",
                "UZS - Uzbekistán",
                "VND - Vietnam",
                "YER - Yemen"
        ));
    }

    private static void displayCurrencies(Scanner scanner) {
        System.out.println("\n+-----------------------------+");
        System.out.println("| === Monedas por Continente === |");
        System.out.println("+-----------------------------+");
        continentCurrencies.forEach((continent, currencies) -> {
            System.out.println("\n" + continent + ":");
            currencies.forEach(currency -> System.out.println("| " + currency));
        });
        System.out.println("\n+-----------------------------+");
        System.out.println("\nPulse 'M' para regresar al menú.");
        scanner.nextLine(); // Esperar que el usuario presione enter
    }
}

