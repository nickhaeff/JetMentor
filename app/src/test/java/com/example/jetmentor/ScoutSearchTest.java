package com.example.jetmentor;

import com.example.jetmentor.ui.scout.ScoutFragment;

import org.junit.Test;
import static org.junit.Assert.*;

public class ScoutSearchTest {

    @Test
    public void find_all_results(){
        String list1[] = {"pen", "pencil", "pennsylvania"};
        String list2[] = {"piz", "pizza", "pizzaria"};
        String list3[] = {"junk", "june", "junker"};
        String searchText = "pen";

        String actual[][] = ScoutFragment.ContentSearch(searchText, list1, list2, list3);
        String expected[][] = {list1, list2, list3};

        assertEquals(actual[0].length, expected[0].length);

        for(int index = 0; index < actual[0].length; index++)
        {
            assertEquals(expected[0][index], actual[0][index]);
            assertEquals(expected[1][index], actual[1][index]);
            assertEquals(expected[2][index], actual[2][index]);
        }
    }

    @Test
    public void find_single_results(){
        String list1[] = {"pen", "pond", "pioneer"};
        String list2[] = {"piz", "pizza", "pizzaria"};
        String list3[] = {"junk", "june", "junker"};
        String elist1[] = {"pen"};
        String elist2[] = {"piz"};
        String elist3[] = {"junk"};
        String searchText = "pen";

        String actual[][] = ScoutFragment.ContentSearch(searchText, list1, list2, list3);
        String expected[][] = {elist1, elist2, elist3};

        assertEquals(actual[0].length, expected[0].length);

        for(int index = 0; index < actual[0].length; index++)
        {
            assertEquals(expected[0][index], actual[0][index]);
            assertEquals(expected[1][index], actual[1][index]);
            assertEquals(expected[2][index], actual[2][index]);
        }
    }

    @Test
    public void find_half_results(){
        String list1[] = {"pineapple", "radio","pen", "pencil", "penssylvani","pen1", "pigpen", "apple", "banana", "chocolate"};
        String list2[] = {"piz", "pizza", "pizzaria","ionef", "iewfud", "awddawd", "qwadaefaf", "edadwddwa", "wadawdw", "wdadas"};
        String list3[] = {"junk", "june", "junker", "awdwad", "psenas", "qwweq", "qwewqc", "wcsad", "wsacx", "ervdsa"};
        String elist1[] = {"pen", "pencil", "penssylvani","pen1", "pigpen"};
        String elist2[] = {"pizzaria","ionef", "iewfud", "awddawd", "qwadaefaf"};
        String elist3[] = {"junker", "awdwad", "psenas", "qwweq", "qwewqc"};
        String searchText = "pen";

        String actual[][] = ScoutFragment.ContentSearch(searchText, list1, list2, list3);
        String expected[][] = {elist1, elist2, elist3};

        assertEquals(actual[0].length, expected[0].length);


        for(int index = 0; index < actual[0].length; index++)
        {
            assertEquals(expected[0][index], actual[0][index]);
            assertEquals(expected[1][index], actual[1][index]);
            assertEquals(expected[2][index], actual[2][index]);
        }
    }

    @Test
    public void find_no_results(){
        String list1[] = {"pineapple", "radio","pen", "pencil", "penssylvani","pen1", "pigpen", "apple", "banana", "chocolate"};
        String list2[] = {"piz", "pizza", "pizzaria","ionef", "iewfud", "awddawd", "qwadaefaf", "edadwddwa", "wadawdw", "wdadas"};
        String list3[] = {"junk", "june", "junker", "awdwad", "psenas", "qwweq", "qwewqc", "wcsad", "wsacx", "ervdsa"};
        String elist1[] = {};
        String elist2[] = {};
        String elist3[] = {};
        String searchText = "georgiapig";

        String actual[][] = ScoutFragment.ContentSearch(searchText, list1, list2, list3);
        String expected[][] = {elist1, elist2, elist3};

        assertEquals(actual[0].length, expected[0].length);


        for(int index = 0; index < actual[0].length; index++)
        {
            assertEquals(expected[0][index], actual[0][index]);
            assertEquals(expected[1][index], actual[1][index]);
            assertEquals(expected[2][index], actual[2][index]);
        }
    }

    @Test
    public void find_based_on_user(){
        String list1[] = {"pineapple", "radio","pen", "pencil", "penssylvani","pen1", "pigpen", "apple", "banana", "chocolate"};
        String list2[] = {"piz", "pizza", "pizzaria","ionef", "iewfud", "awddawd", "qwadaefaf", "edadwddwa", "wadawdw", "wdadas"};
        String list3[] = {"junk", "june", "junker", "awdwad", "psenas", "qwweq", "qwewqc", "wcsad", "wsacx", "ervdsa"};
        String elist1[] = {"pen", "pencil", "penssylvani","pen1", "pigpen"};
        String elist2[] = {"pizzaria","ionef", "iewfud", "awddawd", "qwadaefaf"};
        String elist3[] = {"junker", "awdwad", "psenas", "qwweq", "qwewqc"};
        String searchText = "pen";

        String actual[][] = ScoutFragment.ContentSearch(searchText, list1, list2, list3);
        String expected[][] = {elist1, elist2, elist3};

        assertEquals(actual[0].length, expected[0].length);


        for(int index = 0; index < actual[0].length; index++)
        {
            assertEquals(expected[0][index], actual[0][index]);
            assertEquals(expected[1][index], actual[1][index]);
            assertEquals(expected[2][index], actual[2][index]);
        }
    }

    @Test
    public void find_based_on_company(){
        String list1[] = {"pineapple", "radio","pen", "pencil", "penssylvani","pen1", "pigpen", "apple", "banana", "chocolate"};
        String list2[] = {"piz", "pizza", "pizzaria","ionef", "iewfud", "awddawd", "qwadaefaf", "edadwddwa", "wadawdw", "wdadas"};
        String list3[] = {"junk", "june", "junker", "awdwad", "psenas", "qwweq", "qwewqc", "wcsad", "wsacx", "ervdsa"};
        String elist1[] = {"pineapple", "radio","pen"};
        String elist2[] = {"piz", "pizza", "pizzaria"};
        String elist3[] = {"junk", "june", "junker"};
        String searchText = "piz";

        String actual[][] = ScoutFragment.ContentSearch(searchText, list1, list2, list3);
        String expected[][] = {elist1, elist2, elist3};

        assertEquals(actual[0].length, expected[0].length);


        for(int index = 0; index < actual[0].length; index++)
        {
            assertEquals(expected[0][index], actual[0][index]);
            assertEquals(expected[1][index], actual[1][index]);
            assertEquals(expected[2][index], actual[2][index]);
        }
    }

    @Test
    public void find_based_on_position(){
        String list1[] = {"pineapple", "radio","pen", "pencil", "penssylvani","pen1", "pigpen", "apple", "banana", "chocolate"};
        String list2[] = {"piz", "pizza", "pizzaria","ionef", "iewfud", "awddawd", "qwadaefaf", "edadwddwa", "wadawdw", "wdadas"};
        String list3[] = {"junk", "june", "junker", "awdwad", "psenas", "qwweq", "qwewqc", "wcsad", "wsacx", "ervdsa"};
        String elist1[] = {"pineapple", "radio","pen"};
        String elist2[] = {"piz", "pizza", "pizzaria"};
        String elist3[] = {"junk", "june", "junker"};
        String searchText = "jun";

        String actual[][] = ScoutFragment.ContentSearch(searchText, list1, list2, list3);
        String expected[][] = {elist1, elist2, elist3};

        assertEquals(actual[0].length, expected[0].length);


        for(int index = 0; index < actual[0].length; index++)
        {
            assertEquals(expected[0][index], actual[0][index]);
            assertEquals(expected[1][index], actual[1][index]);
            assertEquals(expected[2][index], actual[2][index]);
        }
    }




}
