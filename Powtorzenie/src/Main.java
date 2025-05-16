
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    String filename = "zgony(1).csv";
    int age = 23;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Tworzymy obiekt DeathCauseStatistic z jednej linii
                DeathCauseStatistic stat = DeathCauseStatistic.fromCsvLine(line);

                // Pobieramy dane dla danego wieku
                DeathCauseStatistic.AgeBracketDeaths ageData = stat.Age(23);
                System.out.println(ageData.young + "-" + ageData.old + ": " + ageData.deathCount);


                // Wyświetlamy wynik
                System.out.println("Kod choroby: " + stat.getIcd());
                System.out.println("Dla wieku " + age + ": " + ageData);
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("Błąd przy czytaniu pliku: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Błąd danych: " + e.getMessage());
        } catch (illegalArgumentException e) {
            throw new RuntimeException(e);
        }
        DeathCauseStatisticList list = new DeathCauseStatisticList();
        try {
            list.repopulate("zgony.csv"); // ścieżka względna
            System.out.println("Wczytano: " + list.getAll().size() + " statystyk.");
        } catch (IOException e) {
            System.err.println("Błąd odczytu pliku: " + e.getMessage());
        }
    }
}