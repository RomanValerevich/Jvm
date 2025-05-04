/**
 * Класс JvmComprehension загружается ClassLoader'ом и размещается в Metaspace.
 */
public class JvmComprehension {

    public static void main(String[] args) {
        int i = 1;                      // 1
        Object o = new Object();        // 2
        Integer ii = 2;                 // 3
        printAll(o, i, ii);             // 4
        System.out.println("finished"); // 7
    }

    private static void printAll(Object o, int i, Integer ii) {
        Integer uselessVar = 700;                   // 5
        System.out.println(o.toString() + i + ii);  // 6
    }
}

/**
 * В момент вызова создается фрейм Main в Stack'е.
 * 1. Во фреме Main создается локальная переменная i и присваивается значение 1.
 *
 * 2. В куче (heap) выделяется место для создания объекта Object. Во фрейме Main в Stack'е создается
 * переменная o и присваивается ссылка на этот объект в куче.
 *
 * 3.В куче выделяется место для создания объекта Integer со значением 2, в Stack'е создается переменная ii
 * и присваивается ссылка на этот объект в куче.
 *
 * 4.В Stack'е создается новый фрейм printAll в нем произходит копирование объется i и копирование сылок на объекты o и ii.
 *
 * 5.В куче создается объект Integer со значением 700, во фрейме printAll в Stack'е создается переменная uselessVar
 * и присваивается ссылка на этот объект в куче.
 *
 * 6. В Stack'е создается новый фрейм print в нем произходит копирование объется i и копирование сылки на объект ii.
 * Также создается новый фрейм toString в нем произходит копирование сылки на объект o.
 * После того как отработает метод printAll, фрейм printAll будет "удален" из Stack'а.
 *
 * 7.В Stack'е создается фрейм print, в куче создается объект String со значением "finished" и присваивается ссылка на этот.
 * В ходе выполнения программы отрабатывает Garbage Collection.
 */