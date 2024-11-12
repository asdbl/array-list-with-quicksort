package src;

/**
 * Интерфейс для реализации списка с быстрой сортировкой
 *
 * @param <T>
 */
public interface CustomList<T extends Comparable<T>> {
    /**
     * Метод добавление элемента в конец списка
     *
     * @param element
     */
    void add(T element);

    /**
     * Метод добавление элемента в список по индексу
     *
     * @param index
     * @param element
     */
    void add(int index, T element);

    /**
     * Метод проверки списка на наличие элемента
     *
     * @param element
     * @return Возвращает true, когда элемент есть в списке, false, когда элемента нет в списке
     */
    boolean contains(T element);

    /**
     * Метод удаление элемента из списка
     *
     * @param element
     * @return Возвращает удаленный элемент
     */
    T remove(T element);

    /**
     * Метод получение элемента из списка
     *
     * @param index
     * @return Возвращает найденный элемент списка
     */
    T get(int index);

    /**
     * Метод удаления элемента из списка по индексу
     *
     * @param index
     * @return Возвращает удаленный элемент
     */
    T remove(int index);

    /**
     * Метод удаление всех элемнтов из списка
     */
    void clear();

    /**
     * Метод получения размера списка
     *
     * @return Возвращает количество элементов в списке
     */
    int size();

    /**
     * Метод уменьшает вместимость списка до количества его элементов
     */
    void trimToSize();

    /**
     * Метод получения индекса элемента
     *
     * @param element
     * @return Возвращает индекс найденного элемента
     */
    int indexOf(T element);

    /**
     * Методсортировки списка
     *
     * @param left
     * @param right
     */
    <E extends Comparable<T>> void quickSort(int left, int right);
}
