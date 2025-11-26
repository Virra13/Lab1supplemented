package lab.countrySupp;

/**
 * Модель страны с площадью, численностью населения и необязательной столицей.
 * Обеспечивает валидацию всех параметров и инкапсуляцию данных.
 */

public class Country {

    private String name;
    private double area;
    private long population;
    private String capitalName;
    private long capitalPopulation;

    /**
     * Создаёт объект Country без информации о столице.
     * Обязательные параметры проходят валидацию.
     *
     * @param name название страны
     * @param area площадь страны в км²
     * @param population численность населения
     */

    public Country(String name, double area, long population) {
        setName(name);
        setArea(area);
        setPopulation(population);
    }

    /**
     * Создаёт объект Country с указанными параметрами и столицей.
     * Все значения проходят валидацию.
     *
     * @param name название страны
     * @param area площадь страны в км²
     * @param population численность населения
     * @param capitalName название столицы
     * @param capitalPopulation численность населения столицы
     */

    public Country(String name, double area, long population,
                   String capitalName, long capitalPopulation) {

        setName(name);
        setArea(area);
        setPopulation(population);
        setCapital(capitalName, capitalPopulation);
    }

    public String getName() {

        return name;
    }

    public double getArea() {

        return area;
    }

    public long getPopulation() {

        return population;
    }

    public String getCapitalName() {

        return capitalName;
    }

    public long getCapitalPopulation() {

        return capitalPopulation;
    }

    /**
     * Методы установки значений полей модели Country.
     * Каждый сеттер выполняет собственную проверку корректности данных.
     */

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public void setArea(double area) {
        validateArea(area);
        this.area = area;
    }

    public void setPopulation(long population) {
        validatePopulation(population);
        this.population = population;
    }

    /**
     * Устанавливает сведения о столице страны.
     * Переданные значения проходят обязательную проверку.
     * Если название столицы null, столица считается отсутствующей.
     *
     * @param capitalName название столицы или null
     * @param capitalPopulation численность населения столицы
     */

    public void setCapital(String capitalName, long capitalPopulation) {
        if (capitalName == null) {
            this.capitalName = null;
            this.capitalPopulation = 0;
            return;
        }

        validateCapitalName(capitalName);
        validateCapitalPopulation(capitalPopulation);

        this.capitalName = capitalName;
        this.capitalPopulation = capitalPopulation;
    }

    /**
     * Возвращает плотность населения (население / площадь).
     *
     * @return плотность населения как double
     * @throws IllegalStateException если значение площади или населения не положительное число
     */

    public double getPopulationDensity() {

        if (area <= 0 || population <= 0) {
            throw new IllegalStateException("Невозможно вычислить плотность: некорректные данные.");

        }
        return population / area;
    }

    /**
     * Возвращает строковое представление страны с площадью, населением
     * и информацией о столице (если она задана).
     *
     * @return описание объекта Country
     */

    @Override
    public String toString() {

        String p = "";
        if (population > 0) {
            p = ", население: " + population;
        }

        String c = "";
        if (capitalName != null && capitalPopulation > 0) {
            c = "\nСтолица: " + capitalName + ", население: " + capitalPopulation;
        }

        return name + ": " + String.format("%.0f", area) + " км²" + p + c;
    }

    /**
     * Набор статических методов валидации входных данных.
     * Все валидаторы выбрасывают IllegalArgumentException при ошибках.
     */

    public static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название страны не может быть пустым");
        }
    }

    public static void validateArea(double area) {
        if (area <= 0) {
            throw new IllegalArgumentException("Площадь должна быть строго больше нуля.");
        }
    }

    public static void validatePopulation(long population) {
        if (population < 0) {
            throw new IllegalArgumentException("Население не может быть отрицательным.");
        }
    }

    public static void validateCapitalName(String capitalName) {
        if (capitalName == null || capitalName.trim().isEmpty()) {
            throw new IllegalArgumentException("Название столицы не может быть пустым");
        }
    }

    public static void validateCapitalPopulation(long capitalPopulation) {
        if (capitalPopulation < 0) {
            throw new IllegalArgumentException("Население столицы не может быть отрицательным.");
        }
    }
}