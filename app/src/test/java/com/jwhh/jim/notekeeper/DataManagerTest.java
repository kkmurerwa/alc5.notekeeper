package com.jwhh.jim.notekeeper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataManagerTest {
    static DataManager sDataManager;

    @BeforeClass
    public static void classSetup() {
        sDataManager = DataManager.getInstance();
    }


    @Before
    public void setUp() {
        sDataManager.getNotes().clear();
    }

    @Test
    public void testCreateNewNote() {
        final CourseInfo course = sDataManager.getCourse("android_async");
        final String noteTitle = "Test Note Title";
        final String noteText = "This is the body of my test note";

        int noteIndex = sDataManager.createNewNote();

        NoteInfo newNote = sDataManager.getNotes().get(noteIndex);
        newNote.setCourse(course);
        newNote.setTitle(noteTitle);
        newNote.setText(noteText);

        NoteInfo compareNote = sDataManager.getNotes().get(noteIndex);

        Assert.assertEquals(newNote, compareNote);
        Assert.assertEquals(course, compareNote.getCourse());
        Assert.assertEquals(noteTitle, compareNote.getTitle());
        Assert.assertEquals(noteText, compareNote.getText());
    }

    @Test
    public void findSimilarNotes() {
        final CourseInfo course = sDataManager.getCourse("android_async");
        final String noteTitle = "Test Note Title";
        final String noteText1 = "This is the body of my first test note";
        final String noteText2 = "This is the body of my second test note";

        int noteIndex1 = sDataManager.createNewNote();
        NoteInfo newNote1 = sDataManager.getNotes().get(noteIndex1);
        newNote1.setCourse(course);
        newNote1.setTitle(noteTitle);
        newNote1.setText(noteText1);

        int noteIndex2 = sDataManager.createNewNote();
        NoteInfo newNote2 = sDataManager.getNotes().get(noteIndex2);
        newNote2.setCourse(course);
        newNote2.setTitle(noteTitle);
        newNote2.setText(noteText2);

        int foundIndex1 = sDataManager.findNote(newNote1);
        Assert.assertEquals(noteIndex1, foundIndex1);
    }
}