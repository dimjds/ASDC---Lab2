import java.io.IOException; //Исключение ввода/вывода
import java.nio.file.Files; //Предоставляет методы для работы с файлами
import java.nio.file.Paths; //Доступ к файловой системе
import java.util.ArrayList; //Реализация списка на основе динамического массива
import java.util.List; //Интерфейс опред методы для работы со списками

//Создаю класс Product с полями id, name, price, brand, quantity
class Product {
    int id;
    String name;
    double price;
    String brand;
    int quantity;

    //Определяю конструктор класса
    public Product(int id, String name, double price, String brand, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.quantity = quantity;
    }

    //Предопределяю метод "tostring"
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}


public class Main {
    public static void main(String[] args) throws IOException {
        List<Product> products = new ArrayList<>(); //Создаю список товаров
        List<String> lines = Files.readAllLines(Paths.get("D:\\Soft\\IntelliJ IDEA Community Edition 2022.3.3\\projects\\testing\\src\\products.txt"));
        for (String line : lines) { //При помощи split разбиваю строки на части
            String[] parts = line.split(","); //Наш разделитель это запятая
            int id = Integer.parseInt(parts[0]); //Здесь я достаю ID (Например 1)
            String name = parts[1]; //Здесь я достаю название (Например Hoodie)
            double price = Double.parseDouble(parts[2]); //Цена
            String brand = parts[3]; //Название бренда
            int quantity = Integer.parseInt(parts[4]); //Количество товара
            products.add(new Product(id, name, price, brand, quantity)); //Разделённые части нашего файла в список
        }

        //Вывод информации о пузырьковом методе
        System.out.println("Пузырьковый метод:");
        bubbleSort(new ArrayList<>(products)); //Создание нового списка с теми же данными, что и до этого, но он проходит сортировку заданную в начале строки
        System.out.println();

        //Вывод информации о сортировке вставками
        System.out.println("Сортировка вставками:");
        insertionSort(new ArrayList<>(products));
        System.out.println();

        //Вывод информации о сортировке вставками
        System.out.println("Сортировка выбором:");
        selectionSort(new ArrayList<>(products));
    }

    //Здесь я определяю пузырьковый метод сортировки. n - размерность нашего списка,
    //в данном случае он динамический
    public static void bubbleSort(List<Product> products) {
        int n = products.size();
        int comparisons = 0;
        int swaps = 0;

        //Во внешнем цикле у нас переменная i увеличивается от 0 до n -2 включительно
        //т.е и предпоследнего элемента списка, он сравнивает соседние элементы списка между собой

        for (int i = 0; i < n - 1; i++) {

            //Во внутреннем цикле у нас происходит сравнение текущего элемента списка со следующим
            //и наоборот и так до тех пор пока не дойдёт до предпоследнего элемента
            for (int j = 0; j < n - i - 1; j++) { ///(Для себя i - номер прохода, j - данные из products, n - размерность массива)
                comparisons++; //Увеличивается каждый раз, когда происходит сравнение
                if (products.get(j).price > products.get(j + 1).price) { //Меняю местами данные в зависимости от их цены
                    swaps++; //Увеличивается каждый раз, когда происходит перестановка
                    Product temp = products.get(j); //Создание временной переменной и присваивание  ей значение элемента j
                    products.set(j, products.get(j + 1)); //Замена значения элемента на j+1
                    products.set(j + 1, temp); //j+1 заменяется на temp чтобы мы могли понять какую цену имеет элемент
                }
            }
        }

        System.out.println("Теор.оценка сложности: O(n^2)");
        System.out.println("Сравнения: " + comparisons);
        System.out.println("Смена местами: " + swaps);
    }

    public static void insertionSort(List<Product> products) {
        int n = products.size();
        int comparisons = 0;
        int swaps = 0;

        //i это элемент списка, который записывается в переменную key
        for (int i = 1; i < n; i++) {
            Product key = products.get(i); //Создание перменной key которая хранит в себе текущий элемент
            int j = i - 1; //Сюда записывается эелемент, который стоит перед key

            //Здесь происходит сравнение элементов по цене, если оно выше, то элемент сдвигается вправо (линейый список)
            while (j >= 0 && products.get(j).price > key.price) {
                comparisons++;
                swaps++;
                products.set(j + 1, products.get(j)); //Эта строка позволяет нам вставить элемент на нужную позицию в списке
                j--; //Это отвечает за уменьшение значение переменной для того, чтобы сдвигаться по элементам списка так, чтобы могли правильно вставлять key
            }
            products.set(j + 1, key);
        }

        System.out.println("Теор.оценка сложности: O(n^2)");
        System.out.println("Сравнения: " + comparisons);
        System.out.println("Смена местами: " + swaps);
    }

    //Здесь я определяю п
    public static void selectionSort(List<Product> products) {
        int n = products.size();
        int comparisons = 0;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i; //Мы задаём переменной i найменьшую позицию
            for (int j = i + 1; j < n; j++) { //Здесь сравниваются остальные значения списка и увеличение сравнений далее
                comparisons++;
                if (products.get(j).price < products.get(minIndex).price) { //Здесь мы значения из переменной j сравниваем со значениями из minindex
                    minIndex = j; //Теперь минимальные значения записываются в j
                }
            }

            if (minIndex != i) { //Проверка найденного минимально элемента с текующим,если он больше, то меньше, то меняет их местами
                swaps++;
                Product temp = products.get(minIndex); //Создание временной переменной и назначение ей минимального элемента
                products.set(minIndex, products.get(i)); //Значения элементов списка меняются местами для перемещения минимального элемента списка в начало
                products.set(i, temp); //в i у нас хранится новый элемент, а в minindex старый, они меняются местами, чтобы выводиться в порядке возрастания
            }
        }

        System.out.println("Теор.оценка сложности: O(n^2)");
        System.out.println("Сравнения: " + comparisons);
        System.out.println("Смена местами: " + swaps);
    }
}