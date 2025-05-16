import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DeathCauseStatisticList {
    private List<DeathCauseStatistic> statistics;

    public DeathCauseStatisticList() {
        this.statistics = new ArrayList<>();
    }

    public void repopulate(String filePath) throws IOException {
        statistics.clear(); // Usuwa poprzednie dane

        List<String> lines = Files.readAllLines(Paths.get(filePath));

        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                try {
                    DeathCauseStatistic stat = DeathCauseStatistic.fromCsvLine(line);
                    statistics.add(stat);
                } catch (Exception e) {
                    System.err.println("Pominięto linię: " + line);
                } catch (illegalArgumentException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public List<DeathCauseStatistic> getAll() {
        return statistics;
    }
    public List<DeathCauseStatistic> mostDeadlyDiseases(int age, int n) {
        int index = getAgeGroupIndex(age);

        return statistics.stream()
                .sorted((a, b) -> Integer.compare(
                        b.getDeathsForAgeIndex(index),
                        a.getDeathsForAgeIndex(index)))
                .limit(n)
                .toList();
    }

    private int getAgeGroupIndex(int age) {
        return Math.min(age / 10, 10);  }

}


