import java.util.Scanner;

public class Сalculator {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите римское или арабское целое число от 1 до 10 с оператором +-*/");
        String input = scanner.nextLine();

        String calc = calc(input);// вызывает метод calc с аргументом input.
        System.out.println("Результат вычисления " + calc);
    }

    public static String calc(String input) throws Exception {// метод
        int num1;
        int num2;
        String result;
        boolean isRoman;
        String line = input.replace(" ", "");// заменяет все найденные пробелы. Убрали пробелы.
        //Преобразуем строчку в массив
        String[] strings = line.split("[+\\-*/]");// разбили строку по символу

        if (strings.length == 1)
            throw new Exception("строка не является математической операцией");

        if (strings.length != 2) //если длинна строки не равняется 2.
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");// выбросили ошибку

        if (Roman.isRoman(strings[0]) && Roman.isRoman(strings[1])) {// если ввели обе римские
            num1 = Roman.convertToArabian(strings[0]);
            num2 = Roman.convertToArabian(strings[1]);
            isRoman = true;
        } else if (!Roman.isRoman(strings[0]) && !Roman.isRoman(strings[1])) {// если ввели обе арабских
            num1 = Integer.parseInt(strings[0]);// преобразование строки в число
            num2 = Integer.parseInt(strings[1]);// преобразование строки в число
            isRoman = false;
        } else {
            throw new Exception("используются одновременно разные системы счисления");
        }

        if (num1 >= 1 && num1 <= 10) ;// число "num1" может принимать числа от 1 до 10
        else {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        if (num2 >= 1 && num2 <= 10) ;// число "num2" может принимать числа от 1 до 10
        else {
            throw new Exception("Числа должны быть от 1 до 10");
        }

        int arabian = ter(num1, num2, line);
        if (isRoman) {// если true выполняем
            if (arabian <= 0) { // если результат римских меньше нуля то выполняем
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            result = Roman.convertToRoman(arabian);// конвертируем римские в арабские
        } else {
            result = String.valueOf(arabian);// переводим в строку
        }
        return result;// возвращаем
    }

    static int ter(int num1, int num2, String line) {// метод вычисления
        int result = 0;
        char[] operation = new char[5]; // массив символов из пяти ячеек
        for (int i = 0; i < line.length(); i++) {
            operation[i] = line.charAt(i);// возвращает символ, расположенный по указанному индексу строки

            if (operation[i] == '+') {// если operation[i] =+ то производится сложение
                result = num1 + num2;
                break;
            } else if (operation[i] == '-') {
                result = num1 - num2;
                break;
            } else if (operation[i] == '*') {
                result = num1 * num2;
                break;
            } else if (operation[i] == '/') {
                result = num1 / num2;
                break;
            }
        }
        return result;
    }


    class Roman {
        static String[] romanArray = new String[]
                {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV",
                        "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII",
                        "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII",
                        "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
                        "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI",
                        "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII",
                        "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII",
                        "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII",
                        "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

        public static boolean isRoman(String bol) {// метод поиска по массиву Римских чисел
            for (int i = 0; i < romanArray.length; i++) {// цикл по массиву, перебор всех элементов массива
                if (bol.equals(romanArray[i])) {// сравнение равен ли объект bol, одному из элементов массива romanArray[i]
                    return true;// возвращаем правду, метод завершился успешно
                }
            }
            return false;// возвращаем лож, условия не выполнились
        }

        public static int convertToArabian(String roman) {// метод конвертирования римских в арабские по индексу
            for (int i = 0; i < romanArray.length; i++) {
                if (roman.equals(romanArray[i])) {
                    return i;// метод завершился успешно, возвращаем индекс римского числа
                }
            }
            return -1;// если условия не выполняются, не удачный результат
        }

        public static String convertToRoman(int arabian) {// метод конвертирования арабских в римские
            return romanArray[arabian];// возвращает элемент массива romanArray, индекс которого соответсвует arabian
        }
    }
}

