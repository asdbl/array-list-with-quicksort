package src;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Реализация ArrayList с быстрой сортировкой
 *
 * @author Клёнин Андрей
 * @version 1.0
 */
public class CustomArrayList<T extends Comparable<T>> implements CustomList<T> {

    /**
     * Поле вместимости массива по умолчанию
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Поле размера массива
     */
    private int listSize;
    /**
     * Поле массива объектов
     */
    private Object[] array;

    /**
     * Конструктор - создание нового массива с заданной вместимостью
     *
     * @param initialCapacity
     */
    public CustomArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.array = new Object[initialCapacity];
        } else {
            this.array = new Object[DEFAULT_CAPACITY];
        }
    }

    /**
     * Конструктор - создание нового массива с вместимостью по умолчанию
     */
    public CustomArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Метод добавления элемента в конец списка
     *
     * @param element
     */
    @Override
    public void add(T element) {
        if (listSize == array.length - 1) {
            resize((int) (array.length * 1.5));
        }
        array[listSize++] = element;
    }

    /**
     * Метод добавление элемента по заданному индексу
     *
     * @param index
     * @param element
     */
    @Override
    public void add(int index, T element) {
        if (listSize == array.length - 1) {
            resize((int) (array.length * 1.5));
        }
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = element;
    }

    /**
     * Метод проверки списка на наличие элемента
     *
     * @param element
     * @return Возвращает true, если элемент найден, false, если не найден
     */
    @Override
    public boolean contains(T element) {
        for (int i = 0; i < listSize; i++) {
            if (array[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Метод удаления элемента из списка
     *
     * @param element
     * @return Возвращает удаленный элемент, либо NoSuchElementException(), если элемент не найден
     */
    @Override
    public T remove(T element) {
        if (listSize != 0) {
            if (contains(element)) {
                return remove(indexOf(element));
            }
        }
        throw new NoSuchElementException();
    }

    /**
     * Метод получения элемента по индексу
     *
     * @param index
     * @return Возвращает полученный элемент,
     * либо выбрасывается исключение IndexOutOfBoundsException, если не верно задан индекс
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= listSize) {
            throw new IndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    /**
     * Метод удаления элемента по индексу
     *
     * @param index
     * @return Возвращает удаленный элемент
     */
    @Override
    public T remove(int index) {
        Object o = null;
        if ((index < listSize) && (index >= 0)) {
            o = get(index);
            shiftToLeft(index);
        }
        return (T) o;
    }

    /**
     * Метод удаления всех элементов из списка
     */
    @Override
    public void clear() {
        for (int i = 0; i < listSize; i++) {
            array[i] = null;
        }
        listSize = 0;
    }

    /**
     * Метод получения размера списка
     *
     * @return Возвращает количество элементов в списке
     */
    @Override
    public int size() {
        return listSize;
    }

    /**
     * Метод уменьшает вместимость списка до размера списка
     */
    @Override
    public void trimToSize() {
        if (listSize < array.length) {
            array = Arrays.copyOf(array, listSize);
        }
    }

    /**
     * Метод получения индекса элемента списка
     *
     * @param element
     * @return Возвращает индекс элемента, если он есть в списке, иначе возвращает -1
     */
    @Override
    public int indexOf(T element) {
        for (int i = 0; i < listSize; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Метод сортирует список от элемента с индексом left до элемента с индексом right
     *
     * @param left
     * @param right
     */
    @Override
    public <E extends Comparable<T>> void quickSort(int left, int right) {
        if (listSize == 0) {
            return;
        }
        if (left >= right) {
            return;
        }
        int middle = left + (right - left) / 2;
        T pivot = get(middle);
        int i = left;
        int j = right;
        while (i <= j) {
            if (get(i).compareTo(pivot) < 0) {
                i++;
            }
            while (get(j).compareTo(pivot) > 0) {
                j--;
            }
            if (i <= j) {
                T temp = get(i);
                add(i, get(j));
                add(j, temp);
                i++;
                j--;
            }
        }
        if (left < j) {
            quickSort(left, j);
        }
        if (right > i) {
            quickSort(i, right);
        }
    }

    private void resize(int newCapacity) {
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0, listSize);
        array = newArray;
    }

    private void shiftToLeft(int start) {
        listSize--;
        if (listSize <= 0) {
            return;
        }
        if (listSize != start) {
            System.arraycopy(array, start + 1, array, start, listSize - start);
        }
        array[listSize] = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomArrayList<?> that = (CustomArrayList<?>) o;
        return listSize == that.listSize && Objects.deepEquals(array, that.array);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listSize, Arrays.hashCode(array));
    }

    @Override
    public String toString() {
        trimToSize();
        return Arrays.toString(array);
    }
}
