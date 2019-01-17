package models;

import java.util.ArrayList;

import lejos.hardware.Button;
import lejos.hardware.Sound;

public class Music {

	private final static int[] PIANO = new int[] { 4, 25, 500, 7000, 5 };
	private ArrayList<int[]> song = new ArrayList<int[]>();
	private int songEnd;

	private double tempered = 1.0595;
	private int noteA00 = 110;
	private int noteAi00, noteBb00 = (int) (noteA00 * tempered);
	private int noteB00 = (int) (noteBb00 * tempered);
	private int noteC0 = (int) (noteB00 * tempered);
	private int noteCi0, noteDb0 = (int) (noteC0 * tempered);
	private int noteD0 = (int) (noteDb0 * tempered);
	private int noteDi0, noteEb0 = (int) (noteD0 * tempered);
	private int noteE0 = (int) (noteEb0 * tempered);
	private int noteF0 = (int) (noteE0 * tempered);
	private int noteFi0, noteGb0 = (int) (noteF0 * tempered);
	private int noteG0 = (int) (noteGb0 * tempered);
	private int noteGi0, noteAb0 = (int) (noteG0 * tempered);
	private int noteA0 = 220;
	private int noteAi0, noteBb0 = (int) (noteA0 * tempered);
	private int noteB0 = (int) (noteBb0 * tempered);
	private int noteC1 = (int) (noteB0 * tempered);
	private int noteCi1, noteDb1 = (int) (noteC1 * tempered);
	private int noteD1 = (int) (noteDb1 * tempered);
	private int noteDi1, noteEb1 = (int) (noteD1 * tempered);
	private int noteE1 = (int) (noteEb1 * tempered);
	private int noteF1 = (int) (noteE1 * tempered);
	private int noteFi1, noteGb1 = (int) (noteF1 * tempered);
	private int noteG1 = (int) (noteGb1 * tempered);
	private int noteGi1, noteAb1 = (int) (noteG1 * tempered);
	private int noteA1 = 440;
	private int noteAi1, noteBb1 = (int) (noteA1 * tempered);
	private int noteB1 = (int) (noteBb1 * tempered);
	private int noteC2 = (int) (noteB1 * tempered);
	private int noteCi2, noteDb2 = (int) (noteC2 * tempered);
	private int noteD2 = (int) (noteDb2 * tempered);
	private int noteDi2, noteEb2 = (int) (noteD2 * tempered);
	private int noteE2 = (int) (noteEb2 * tempered);
	private int noteF2 = (int) (noteE2 * tempered);
	private int noteFi2, noteGb2 = (int) (noteF2 * tempered);
	private int noteG2 = (int) (noteGb2 * tempered);
	private int noteGi2, noteAb2 = (int) (noteG2 * tempered);
	private int noteA2 = 880;
	private int noteAi2, noteBb2 = (int) (noteA2 * tempered);
	private int noteB2 = (int) (noteBb2 * tempered);
	private int noteC3 = (int) (noteB2 * tempered);

	private int tempo = 103;
	private int quarterNoteLength = 60000 / tempo;
	private int whole = quarterNoteLength * 4;
	private int half = quarterNoteLength * 2;
	private int quarter = quarterNoteLength * 1;
	private int eighth = (int) (quarterNoteLength * 0.5);
	private int sixteenth = (int) (quarterNoteLength * 0.25);
	private double dotted = 1.5;
	private double triplet = 0.33333;

	public void playStarWars(int length) {

		addNote(noteA1, quarter);
		// length 1
		addNote(noteA1, quarter);
		addNote(noteA1, quarter);
		addNote(noteF1, (int) (eighth * dotted));
		addNote(noteC2, sixteenth);
		addNote(noteA1, quarter);
		addNote(noteF1, (int) (eighth * dotted));
		addNote(noteC2, sixteenth);
		addNote(noteA1, half);

		// length 2
		addNote(noteE2, quarter);
		addNote(noteE2, quarter);
		addNote(noteE2, quarter);
		addNote(noteF2, (int) (eighth * dotted));
		addNote(noteC2, sixteenth);
		addNote(noteAb1, quarter);
		addNote(noteF1, (int) (eighth * dotted));
		addNote(noteC2, sixteenth);
		addNote(noteA1, half);

		playStarWarsRepeat();
		addNote(noteAb1, sixteenth);
		addNote(noteC2, quarter);
		addNote(noteA1, (int) (eighth * dotted));
		addNote(noteC2, sixteenth);
		addNote(noteE2, half);

		playStarWarsRepeat();
		addNote(noteC2, sixteenth);
		addNote(noteA1, quarter);
		addNote(noteF1, (int) (eighth * dotted));
		addNote(noteC2, sixteenth);
		addNote(noteA1, half);

		if (length == 1) {
			songEnd = 1;
		} else if (length == 2) {
			songEnd = 9;
		} else
			songEnd = song.size();

		for (int i = 0; i < songEnd; i++) {
			int[] notes = new int[2];
			notes = song.get(i);
			Sound.playNote(PIANO, notes[0], notes[1]);
			if (Button.ESCAPE.isDown())
				i = song.size();
		}
	}

	public void playStarWarsRepeat() {
		addNote(noteA2, quarter);
		addNote(noteA1, (int) (eighth * dotted));
		addNote(noteA1, sixteenth);
		addNote(noteA2, quarter);
		addNote(noteAb2, eighth);
		addNote(noteG2, eighth);
		addNote(noteGb2, sixteenth);
		addNote(noteF2, sixteenth);
		addNote(noteGb2, quarter);
		addNote(noteBb1, eighth);
		addNote(noteEb2, quarter);
		addNote(noteD2, eighth);
		addNote(noteDb2, eighth);
		addNote(noteC2, sixteenth);
		addNote(noteB1, sixteenth);
		addNote(noteC2, quarter);
		addNote(noteF1, eighth);
		addNote(noteAb1, quarter);
		addNote(noteF1, (int) (eighth * dotted));
	}

	public void addNote(int noteHight, int noteLength) {
		int[] notes = { noteHight, noteLength };
		song.add(notes);
	}
}
