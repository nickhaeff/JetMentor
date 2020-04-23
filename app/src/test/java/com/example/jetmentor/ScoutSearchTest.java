package com.example.jetmentor;

import com.example.jetmentor.ui.mentorInfo;
import com.example.jetmentor.ui.scout.ScoutFragment;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ScoutSearchTest {


//  Pass in mentorInfo list
//  get back list of filtered mentorinfos
//

    public mentorInfo genSearchMentInfo(String iname, String icomp, String ipos){
        return new mentorInfo("12345", iname, icomp, ipos, 0, true, "", "gen@test");
    }

    @Test
    public void find_all_results(){
        String list1[] = {"pen", "pencil", "pennsylvania"};
        String list2[] = {"piz", "pizza", "pizzaria"};
        String list3[] = {"junk", "june", "junker"};
        String searchText = "pen";

        List<mentorInfo> actualMentorInfo = new ArrayList<>();
        for(int i = 0; i < list1.length; i++)
        {
            actualMentorInfo.add((genSearchMentInfo(list1[i], list2[i], list3[i])));
        }

        ScoutFragment.ContentSearch(searchText, actualMentorInfo, "54321");
        String expected[][] = {list1, list2, list3};

        List<mentorInfo> expectedMentorInfo = new ArrayList<>();
        for(int i = 0; i < expected[0].length; i++)
        {
            expectedMentorInfo.add((genSearchMentInfo(expected[0][i], expected[1][i], expected[2][i])));
        }

        assertEquals(expectedMentorInfo.size(), actualMentorInfo.size());

        for(int index = 0; index < actualMentorInfo.size(); index++)
        {
            assertEquals(expectedMentorInfo.get(index).getName(), actualMentorInfo.get(index).getName());
            assertEquals(expectedMentorInfo.get(index).getCompany(), actualMentorInfo.get(index).getCompany());
            assertEquals(expectedMentorInfo.get(index).getPosition(), actualMentorInfo.get(index).getPosition());
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


        List<mentorInfo> actualMentorInfo = new ArrayList<>();
        for(int i = 0; i < list1.length; i++)
        {
            actualMentorInfo.add((genSearchMentInfo(list1[i], list2[i], list3[i])));
        }
        assertEquals(3, actualMentorInfo.size());
        actualMentorInfo = ScoutFragment.ContentSearch(searchText, actualMentorInfo, "54321");
        assertEquals(1, actualMentorInfo.size());
        String expected[][] = {elist1, elist2, elist3};

        List<mentorInfo> expectedMentorInfo = new ArrayList<>();
        for(int i = 0; i < expected[0].length; i++)
        {
            expectedMentorInfo.add((genSearchMentInfo(expected[0][i], expected[1][i], expected[2][i])));
        }

        assertEquals(expectedMentorInfo.size(), actualMentorInfo.size());

        for(int index = 0; index < actualMentorInfo.size(); index++)
        {
            assertEquals(expectedMentorInfo.get(index).getName(), actualMentorInfo.get(index).getName());
            assertEquals(expectedMentorInfo.get(index).getCompany(), actualMentorInfo.get(index).getCompany());
            assertEquals(expectedMentorInfo.get(index).getPosition(), actualMentorInfo.get(index).getPosition());
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

        List<mentorInfo> actualMentorInfo = new ArrayList<>();
        for(int i = 0; i < list1.length; i++)
        {
            actualMentorInfo.add((genSearchMentInfo(list1[i], list2[i], list3[i])));
        }


        actualMentorInfo = ScoutFragment.ContentSearch(searchText, actualMentorInfo, "54321");
        String expected[][] = {elist1, elist2, elist3};
        List<mentorInfo> expectedMentorInfo = new ArrayList<>();
        for(int i = 0; i < expected[0].length; i++)
        {
            expectedMentorInfo.add((genSearchMentInfo(expected[0][i], expected[1][i], expected[2][i])));
        }

        assertEquals(expectedMentorInfo.size(), actualMentorInfo.size());



        for(int index = 0; index < actualMentorInfo.size(); index++)
        {
            assertEquals(expectedMentorInfo.get(index).getName(), actualMentorInfo.get(index).getName());
            assertEquals(expectedMentorInfo.get(index).getCompany(), actualMentorInfo.get(index).getCompany());
            assertEquals(expectedMentorInfo.get(index).getPosition(), actualMentorInfo.get(index).getPosition());
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

        List<mentorInfo> actualMentorInfo = new ArrayList<>();
        for(int i = 0; i < list1.length; i++)
        {
            actualMentorInfo.add((genSearchMentInfo(list1[i], list2[i], list3[i])));
        }


        actualMentorInfo = ScoutFragment.ContentSearch(searchText, actualMentorInfo, "54321");
        String expected[][] = {elist1, elist2, elist3};
        List<mentorInfo> expectedMentorInfo = new ArrayList<>();
        for(int i = 0; i < expected[0].length; i++)
        {
            expectedMentorInfo.add((genSearchMentInfo(expected[0][i], expected[1][i], expected[2][i])));
        }

        assertEquals(expectedMentorInfo.size(), actualMentorInfo.size());



        for(int index = 0; index < actualMentorInfo.size(); index++)
        {
            assertEquals(expectedMentorInfo.get(index).getName(), actualMentorInfo.get(index).getName());
            assertEquals(expectedMentorInfo.get(index).getCompany(), actualMentorInfo.get(index).getCompany());
            assertEquals(expectedMentorInfo.get(index).getPosition(), actualMentorInfo.get(index).getPosition());
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

        List<mentorInfo> actualMentorInfo = new ArrayList<>();
        for(int i = 0; i < list1.length; i++)
        {
            actualMentorInfo.add((genSearchMentInfo(list1[i], list2[i], list3[i])));
        }


        actualMentorInfo = ScoutFragment.ContentSearch(searchText, actualMentorInfo, "54321");
        String expected[][] = {elist1, elist2, elist3};
        List<mentorInfo> expectedMentorInfo = new ArrayList<>();

        for(int i = 0; i < expected[0].length; i++)
        {
            expectedMentorInfo.add((genSearchMentInfo(expected[0][i], expected[1][i], expected[2][i])));
        }

        assertEquals(expectedMentorInfo.size(), actualMentorInfo.size());



        for(int index = 0; index < actualMentorInfo.size(); index++)
        {
            assertEquals(expectedMentorInfo.get(index).getName(), actualMentorInfo.get(index).getName());
            assertEquals(expectedMentorInfo.get(index).getCompany(), actualMentorInfo.get(index).getCompany());
            assertEquals(expectedMentorInfo.get(index).getPosition(), actualMentorInfo.get(index).getPosition());
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

        List<mentorInfo> actualMentorInfo = new ArrayList<>();
        for(int i = 0; i < list1.length; i++)
        {
            actualMentorInfo.add((genSearchMentInfo(list1[i], list2[i], list3[i])));
        }


        actualMentorInfo = ScoutFragment.ContentSearch(searchText, actualMentorInfo, "54321");
        String expected[][] = {elist1, elist2, elist3};
        List<mentorInfo> expectedMentorInfo = new ArrayList<>();

        for(int i = 0; i < expected[0].length; i++)
        {
            expectedMentorInfo.add((genSearchMentInfo(expected[0][i], expected[1][i], expected[2][i])));
        }

        assertEquals(expectedMentorInfo.size(), actualMentorInfo.size());



        for(int index = 0; index < actualMentorInfo.size(); index++)
        {
            assertEquals(expectedMentorInfo.get(index).getName(), actualMentorInfo.get(index).getName());
            assertEquals(expectedMentorInfo.get(index).getCompany(), actualMentorInfo.get(index).getCompany());
            assertEquals(expectedMentorInfo.get(index).getPosition(), actualMentorInfo.get(index).getPosition());
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

        List<mentorInfo> actualMentorInfo = new ArrayList<>();
        for(int i = 0; i < list1.length; i++)
        {
            actualMentorInfo.add((genSearchMentInfo(list1[i], list2[i], list3[i])));
        }

        actualMentorInfo = ScoutFragment.ContentSearch(searchText, actualMentorInfo, "54321");
        String expected[][] = {elist1, elist2, elist3};
        List<mentorInfo> expectedMentorInfo = new ArrayList<>();

        for(int i = 0; i < expected[0].length; i++)
        {
            expectedMentorInfo.add((genSearchMentInfo(expected[0][i], expected[1][i], expected[2][i])));
        }

        assertEquals(expectedMentorInfo.size(), actualMentorInfo.size());



        for(int index = 0; index < actualMentorInfo.size(); index++)
        {
            assertEquals(expectedMentorInfo.get(index).getName(), actualMentorInfo.get(index).getName());
            assertEquals(expectedMentorInfo.get(index).getCompany(), actualMentorInfo.get(index).getCompany());
            assertEquals(expectedMentorInfo.get(index).getPosition(), actualMentorInfo.get(index).getPosition());
        }
    }




}
