# PO_ProjektySemestralne
Podsumowanie:
Dziedziczenie:
-dziedziczenie po klasie (extends)
class Animal {
    public void speak() {
        System.out.println("Animal speaks");
    }
}

class Dog extends Animal {
    // Dog automatycznie ma metodę speak()
}
-implementowanie interfejsu:
interface Walkable {
    default void walk() {
        System.out.println("Walking");
    }
}

class Human implements Walkable {
    // walk() już jest odziedziczone
}
-dziedziczenie metod abstrakcyjnych:
tj dziedziczenie po klasie
-przesyłanie metod za pomocą override (nadpisanie)
class Animal {
    public void speak() {
        System.out.println("Generic animal");
    }
}

class Cat extends Animal {
    @Override
    public void speak() {
        System.out.println("Meow");
    }
}
-metoda nadrzędna super 
class Dog extends Animal {
    @Override
    public void speak() {
        super.speak(); // wywołuje "Animal speaks"
        System.out.println("Bark");
    }
}
Metoda nadrzędna super()
-wywołuje metody z nadklasy 
-daje dostęp do pól z nadklas
-wywołanie konstruktorów z nadklas
class Animal {
    Animal(String name) {
        System.out.println("Animal: " + name);
    }
}

class Dog extends Animal {
    Dog() {
        super("Reksio");  // wywołuje konstruktor Animal z argumentem "Reksio"
        System.out.println("Dog created");
    }
}
WAŻNE: 
musi być pierwszą instrukcją w konstruktorze
musi być używana w klasie, która dziedziczy po innej klasie 
KONTENERY 
https://www.geeksforgeeks.org/collections-in-java-2/

Arraylist/ lista 

List<RaceCar> raceCars = new ArrayList<>();
raceCars.add(new RaceCar("RWD", 700, 350.0f, 80.0f));
raceCars.add(new RaceCar("AWD", 850, 400.0f, 90.0f));

SET<>
nieuporządkowana lista zawierająca tylko unikaty

Set<String> raceCarBrands = new HashSet<>();
raceCarBrands.add("Ferrari");
raceCarBrands.add("Aston Martin");
raceCarBrands.add("Ford");


Wyjątki 
korzystamy ze słów try i catch

zwykły try-catch
public class Main {
    public static void main(String[] args) {
        try {
            int x = 10 / 0; // dzielenie przez zero
        } catch (ArithmeticException e) {
            System.out.println("Błąd: " + e.getMessage()); // Błąd: / by zero
        }
    }
}
z finally 
public class Main {
    public static void main(String[] args) {
        try {
            String text = null;
            text.length(); // NullPointerException
        } catch (NullPointerException e) {
            System.out.println("Błąd: null nie ma długości");
        } finally {
            System.out.println("Zawsze się wykonuje");
        }
    }
}
z throw 
public class Main {
    public static void validateAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Musisz mieć co najmniej 18 lat");
        }
    }

    public static void main(String[] args) {
        validateAge(16); // rzuci wyjątek
    }
}
własny wyjątek czyli extends exception 

class TooColdException extends Exception {
    public TooColdException(String message) {
        super(message);
    }
}

public class Main {
    public static void checkTemperature(int temp) throws TooColdException {
        if (temp < 0) {
            throw new TooColdException("Za zimno! Temperatura: " + temp);
        }
    }

    public static void main(String[] args) {
        try {
            checkTemperature(-5);
        } catch (TooColdException e) {
            System.out.println("Uwaga: " + e.getMessage());
        }
    }
}
Własny wyjątek niekontrolowany 
class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(String message) {
        super(message);
    }
}

public class Main {
    public static void login(String password) {
        if (!password.equals("tajne")) {
            throw new WrongPasswordException("Złe hasło!");
        }
    }


    public static void main(String[] args) {
        login("1234"); // rzuci wyjątek
    }
}

Metoda Statyczna:
-należy do klasy, nie do obiektu

-nie potrzebujesz new, żeby jej użyć

-nie ma dostępu do this ani do pól instancyjnych (nie może używać zmiennych obiektowych)
-
public class MathUtils {
    public static int square(int x) {
        return x * x;
    }
}

 wywołanie :
 int result = MathUtils.square(5); 


 Dynamiczna metoda:

-należy do obiektu klasy

-musisz utworzyć obiekt (new) żeby jej użyć

-może używać this, pól i innych metod obiektowych;


public class Dog {
    String name;

    public Dog(String name) {
        this.name = name;
    }

    public void bark() {
        System.out.println(name + " szczeka!");
    }
}
 wywołanie :
 Dog dog = new Dog("Reksio");
dog.bark(); //  działa na obiekcie
Kiedy używać?
-Użyj static, gdy:
Metoda nie zależy od stanu obiektu

Ma uniwersalne zastosowanie (np. Math.max())

Chcesz stworzyć fabrykę, narzędzie, pomocnika (np. Utils.calculate())

- Użyj dynamicznej, gdy:
Metoda potrzebuje danych obiektu

Używasz this, np. do modyfikacji pól

Tworzysz zwykłe klasy i modele (np. Car.startEngine())
