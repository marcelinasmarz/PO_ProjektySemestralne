import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class DeathCauseStatistic {
    private String icd;
    private int[] zgony;

    public String getIcd() {
        return icd;
    }

    public int[] getZgony() {
        return zgony;
    }

    public DeathCauseStatistic (String icd, int[] zgony){
        this.icd = icd;
        this.zgony = zgony;
    }
    public static String fromCsv(String filename) throws IOException {
        String result = null;
        try {
            String line;
            BufferedReader br = new BufferedReader((new FileReader(filename)));
            br.readLine();
            while ((line = br.readLine())!= null){
                result = line;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Plik" + filename + "nie istnieje");
        }
        return result;
    }
    public static DeathCauseStatistic fromCsvLine(String line) throws illegalArgumentException {
        String[] parts =line.split("\t");
        if(parts.length < 2) {
            throw new illegalArgumentException("Nieprawidłowa linie csv:" + line);
        }
        String code = parts[0];
        int[] deaths = new int[parts.length -1];
        for(int i = 0; i < parts.length-1;
        i++){
            deaths[i] = Integer.parseInt(parts[i+1]);

        }
        return new DeathCauseStatistic(code, deaths);
    }



    public class  AgeBracketDeaths {
        public final int young, old, deathCount;
        public AgeBracketDeaths(int young, int old, int deathCount) {
            this.young = young;
            this.old = old;
            this.deathCount = deathCount;
        }
        public String toString() {
            return "Wiek" + young + "-" + old+ "Zgony:" + deathCount;
        }

    }
    public AgeBracketDeaths Age(int wiek) {
    int index = wiek / 5;
    if(index < 0 || index >= this.zgony.length) {
        throw new IllegalArgumentException("Brak danych dla wieku" + wiek);
    }
    int young = index *5;
    int old = young + 4;
    int count = this.zgony[index];

        return new AgeBracketDeaths(young, old, count);
    }
    public String toString () {
        return "ICD" + icd + ",zgony:" + Arrays.toString(zgony);
    }
    public int getDeathsForAgeIndex(int index) {
        if (index >= 0 && index < zgony.length) {
            return zgony[index];
        }
        return 0;
    }
}
