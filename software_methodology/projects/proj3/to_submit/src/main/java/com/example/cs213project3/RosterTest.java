package com.example.cs213project3;

import org.junit.Test;

import static org.junit.Assert.*;

public class RosterTest {

    @Test
    public void addInternational1() {
        Roster myRoster = new Roster();
        Profile profile1 = new Profile("Duper", "Super", new Date("7/24/2001"));
        International international1 = new International(profile1, Major.CS, 67, true);
        assertTrue(myRoster.add(international1));
    }

    @Test
    public void addInternational2() {
        Roster myRoster2 = new Roster();
        Profile profile2 = new Profile("Simpson", "Bart", new Date("9/17/2002"));
        International international2 = new International(profile2, Major.CS, 14, false);
        assertTrue(myRoster2.add(international2));
    }

    @Test
    public void addTriState1() {
        Roster myRoster3 = new Roster();
        Profile profile3 = new Profile("Johnson", "Squash", new Date("1/10/2000"));
        TriState triState1 = new TriState(profile3, Major.ITI, 31, "NY");
        assertTrue(myRoster3.add(triState1));
    }

    @Test
    public void addTriState2() {
        Roster myRoster4 = new Roster();
        Profile profile4 = new Profile("Pan", "Peter", new Date("3/3/1997"));
        TriState triState2 = new TriState(profile4, Major.MATH, 100, "CT");
        assertTrue(myRoster4.add(triState2));
    }
}