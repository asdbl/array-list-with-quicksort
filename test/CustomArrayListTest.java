package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.CustomArrayList;
import src.CustomList;

public class CustomArrayListTest {
    CustomArrayList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new CustomArrayList<>();
    }

    @Test
    public void shouldAddNewElement() {
        list.add(1);
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(1, list.get(0));
    }

    @Test
    public void shouldAddNewElementByIndex() {
        list.add(1);
        list.add(2);
        list.add(1,3);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(3, list.get(1));
    }

    @Test
    public void shouldRemoveElement() {
        CustomList<String> st = new CustomArrayList<>();
        st.add("A");
        st.add("B");
        st.remove("A");
        Assertions.assertEquals(1, st.size());
        Assertions.assertEquals("B", st.get(0));
    }

    @Test
    public void shouldRemoveElementByIndex() {
        list.add(1);
        list.add(2);
        list.remove(0);
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(2, list.get(0));
    }

    @Test
    public void shouldRemoveAllElements() {
        list.add(1);
        list.add(2);
        list.clear();
        Assertions.assertEquals(0, list.size());
    }

    @Test
    public void shouldReturnTrueWhenListContainsElement() {
        list.add(1);
        Assertions.assertTrue(list.contains(1));
    }

    @Test
    public void shouldReturnFalseWhenListDoesNotContainElement() {
        list.add(1);
        Assertions.assertFalse(list.contains(2));
    }

    @Test
    public void shouldReturnElementByIndex() {
        list.add(1);
        Assertions.assertEquals(1, list.get(0));
    }

    @Test
    public void shouldReturn1WhenAddedOneElement() {
        list.add(1);
        Assertions.assertEquals(1, list.size());
    }

    @Test
    public void shouldReturn0WhenAddedZeroElements() {
        Assertions.assertEquals(0, list.size());
    }

    @Test
    public void shouldReturn0WhenIndexOfFirstElement(){
        list.add(1);
        list.add(2);
        Assertions.assertEquals(0, list.indexOf(1));
    }

    @Test
    public void shouldReturnMinus1WhenIndexOfElementIsNotFound(){
        list.add(1);
        list.add(2);
        Assertions.assertEquals(-1, list.indexOf(3));
    }

    @Test
    public void shouldResizeAndAddElement() {
        CustomArrayList<Integer> list = new CustomArrayList<>(5);
        for (int i = 0; i < 8; i++) {
            list.add(i);
        }
        Assertions.assertEquals(8, list.size());
    }

    @Test
    void shouldReturnSortedList() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(4);
        list.add(2);
        list.add(7);
        list.add(1);
        list.quickSort(0, list.size() - 1);
        CustomArrayList<Integer> expected = new CustomArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(4);
        expected.add(7);
        Assertions.assertEquals(expected, list);
    }
}
