package models;

import lejos.hardware.Sound;

public class Music {

	private final static int[] PIANO = new int[] { 4, 25, 500, 7000, 5 };

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
	private int noteLength = 60000 / tempo;
	private int whole = noteLength * 4;
	private int half = noteLength * 2;
	private int quarter = noteLength * 1;
	private int eighth = (int) (noteLength * 0.5);
	private int sixteenth = (int) (noteLength * 0.25);
	private double dotted = 1.5;
	private double triplet = 0.33333;

	public Music() {
		super();

	}

	public void playStarWars(int length) {
		Sound.playNote(PIANO, noteA1, quarter);
		Sound.playNote(PIANO, noteA1, quarter);
		Sound.playNote(PIANO, noteA1, quarter);
		Sound.playNote(PIANO, noteF1, (int) (eighth * dotted));
		Sound.playNote(PIANO, noteC2, sixteenth);
		Sound.playNote(PIANO, noteA1, quarter);
		Sound.playNote(PIANO, noteF1, (int) (eighth * dotted));
		Sound.playNote(PIANO, noteC2, sixteenth);
		Sound.playNote(PIANO, noteA1, half);

		if (length == 1) return;
		
		Sound.playNote(PIANO, noteE2, quarter);
		Sound.playNote(PIANO, noteE2, quarter);
		Sound.playNote(PIANO, noteE2, quarter);
		Sound.playNote(PIANO, noteF2, (int) (eighth * dotted));
		Sound.playNote(PIANO, noteC2, sixteenth);
		Sound.playNote(PIANO, noteAb1, quarter);
		Sound.playNote(PIANO, noteF1, (int) (eighth * dotted));
		Sound.playNote(PIANO, noteC2, sixteenth);
		Sound.playNote(PIANO, noteA1, half);

		playStarWarsRepeat();
		Sound.playNote(PIANO, noteAb1, sixteenth);
		Sound.playNote(PIANO, noteC2, quarter);
		Sound.playNote(PIANO, noteA1, (int) (eighth * dotted));
		Sound.playNote(PIANO, noteC2, sixteenth);
		Sound.playNote(PIANO, noteE2, half);
		
		playStarWarsRepeat();
		Sound.playNote(PIANO, noteC2, sixteenth);
		Sound.playNote(PIANO, noteA1, quarter);
		Sound.playNote(PIANO, noteF1, (int) (eighth * dotted));
		Sound.playNote(PIANO, noteC2, sixteenth);
		Sound.playNote(PIANO, noteA1, half);
	}

	public void playStarWarsRepeat() {
		Sound.playNote(PIANO, noteA2, quarter);
		Sound.playNote(PIANO, noteA1, (int) (eighth * dotted));
		Sound.playNote(PIANO, noteA1, sixteenth);
		Sound.playNote(PIANO, noteA2, quarter);
		Sound.playNote(PIANO, noteAb2, eighth);
		Sound.playNote(PIANO, noteG2, eighth);
		Sound.playNote(PIANO, noteGb2, sixteenth);
		Sound.playNote(PIANO, noteF2, sixteenth);
		Sound.playNote(PIANO, noteGb2, quarter);
		Sound.playNote(PIANO, noteBb1, eighth);
		Sound.playNote(PIANO, noteEb2, quarter);
		Sound.playNote(PIANO, noteD2, eighth);
		Sound.playNote(PIANO, noteDb2, eighth);
		Sound.playNote(PIANO, noteC2, sixteenth);
		Sound.playNote(PIANO, noteB1, sixteenth);
		Sound.playNote(PIANO, noteC2, quarter);
		Sound.playNote(PIANO, noteF1, eighth);
		Sound.playNote(PIANO, noteAb1, quarter);
		Sound.playNote(PIANO, noteF1, (int) (eighth * dotted));

	}
}
