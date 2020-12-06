package com.tabs.tabs.plants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Profile {

    private String subtitle;
    private String personName;
    private List<String> personNotes;

    public Profile() {
        setName("Tyler");
        setSubtitle("Nothing he can do about it");
        setNotes(new ArrayList<String>());
    }

    public Profile(String name, String subtitle, List<String> notes) {
        setName(name);
        setSubtitle(subtitle);
        setNotes(notes);
    }

    public void setName(String name) {
        personName = name;
    }

    public String getName() {
        return personName;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void addNote(String note) {
        personNotes.add(note);
    }

    public void setNotes(List<String> notes) {
        personNotes = notes;
    }

    public List<String> getNotes() {
        return personNotes;
    }

    private static String delim = "___";

    public String writeProfile() {
        String ret = personName + delim + subtitle;
        for (String s: personNotes) {
            ret += delim + s;
        }
        return ret;
    }

    public static Profile readProfile(String in) {
        String[] splitted = in.split(delim);
        String name = splitted[0];
        String sub = splitted[1];
        String[] notes = Arrays.copyOfRange(splitted, 2, splitted.length);
        List<String> notesList = Arrays.asList(notes);
        return new Profile(name, sub, notesList);
    }

}
