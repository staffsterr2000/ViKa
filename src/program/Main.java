package program;

import javax.sound.midi.MidiChannel;

public class Main {

    /*
    I did muting notes in main() and in play():
        1. I want different release between chords and solo notes
        2. Maybe I'll figure out my problem (How to use different instruments or the same instrument parallel (with able to
        do different latency for all parts of a symphony))
        3. In ViKa.main() we're muting chords (with a big number of if - else statements)
        4. In Player.play() we're muting solo notes
     */

    public static void main(String[] args) {
        int[][] notesArray = {      // an array for notes (with info about single note, and chord notes (if -1 in notesArray[x][2] -> no chord))
                // here we have 4 types of notes (SPACES {with no notes (-1)}, SOLO_NOTES {with no chord notes},
                // CHORDS {with no single notes}, and BOTH {with both type of notes})

                // single notes and chords
                {65, -1}, {-1, -1}, {65, -1}, {65, -1},
                {65, -1}, {-1, -1}, {65, -1}, {65, -1},
                {65, -1}, {-1, -1}, {65, -1}, {-1, -1},
                {65, -1}, {65, -1}, {65, 53}, {-1, 53},

                {65, 60, 51}, {-1, -1}, {65, -1}, {65, -1},
                {65, -1}, {-1, -1}, {65, -1}, {65, -1},
                {65, 60, 51}, {-1, -1}, {65, -1}, {-1, -1},
                {65, -1}, {65, -1}, {65, -1}, {-1, -1},

                {65, 60, 48}, {-1, -1}, {65, -1}, {65, -1},
                {65, -1}, {-1, -1}, {65, -1}, {65, -1},
                {65, 60, 48}, {-1, -1}, {65, -1}, {-1, -1},
                {65, -1}, {65, -1}, {65, -1}, {-1, -1},

                {65, 46, 58}, {-1, -1}, {65, -1}, {65, -1},
                {65, -1}, {-1, -1}, {65, -1}, {65, -1},
                {65, 46, 58}, {-1, -1}, {65, -1}, {-1, -1},
                {65, -1}, {65, -1}, {65, -1}, {-1, -1},

                // solo octaves (without solo notes)
                {-1, 41, 53}, {-1, -1}, {-1, -1}, {-1, -1},
                {-1, 44, 56}, {-1, -1}, {-1, -1}, {-1, -1},
                {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1},
                {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1},

                {-1, 44, 56}, {-1, -1}, {-1, -1}, {-1, -1},
                {-1, 46, 58}, {-1, -1}, {-1, -1}, {-1, -1},
                {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1},
                {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1},

                {-1, 49, 61}, {-1, -1}, {-1, -1}, {-1, -1},
                {-1, 48, 60}, {-1, -1}, {-1, -1}, {-1, -1},
                {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1},
                {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1},

                {-1, 48, 60}, {-1, -1}, {-1, -1}, {-1, -1},
                {-1, 46, 58}, {-1, -1}, {-1, -1}, {-1, -1},
                {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1},
                {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1},

                // solo double octaves (without single notes)
                {-1, 41, 53, 65}, {-1, -1}, {-1, -1}, {-1, -1},
                {-1, 44, 56, 68}, {-1, -1}, {-1, -1}, {-1, -1},
                {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1},
                {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1},

                {-1, 44, 56, 68}, {-1, -1}, {-1, -1}, {-1, -1},
                {-1, 46, 58, 70}, {-1, -1}, {-1, -1}, {-1, -1},
                {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1},
                {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1},

                {-1, 49, 61, 73}, {-1, -1}, {-1, -1}, {-1, -1},
                {-1, 48, 60, 72}, {-1, -1}, {-1, -1}, {-1, -1},
                {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1},
                {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1},

                {-1, 48, 60, 72}, {-1, -1}, {-1, -1}, {-1, -1},
                {-1, 46, 58, 70}, {-1, -1}, {-1, -1}, {-1, -1},
                {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1},
                {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1},
        };

        Player player = new Player();   // add new Player()
        MidiChannel piano = player.getChannel(0);     // get the first channel for the piano for off the chords it in main method
        MidiChannel drums = player.getChannel(9);     // get the ninth channel for the drums
        System.out.print("Hello workers!");

        while (true) {      // an endless song
            for (int i = 0; i < notesArray.length; i++) {   // for every note array
                int[] note = notesArray[i];
                if (i >= 16 && i < 64 && i % 16 == 8) {     // for the first part from the 16th iteration
                    // (when the chords are getting in the game)
                    for (int j = 1; j < notesArray[i - 8].length; j++) {    // for every chord note
                        piano.noteOff(notesArray[i - 8][j]);  // muting it (any chord note) (solo notes we're muting
                        // in the play function)
                    }
                }
                if (i >= 64) {      // for the second part (one octave chords and two octaves chords)
                    if (i % 16 == 0) {  // for every sixteenth iteration by 0 we:
                        System.out.print("\nVI");   // print "VI"
                        drums.noteOn(49, 100);    // crash
                        for (int j = 1; j < notesArray[i - 12].length; j++) {   // muting last chord (it was 12 notes ago)
                            piano.noteOff(notesArray[i - 12][j]);     // (every note of last chord)
                        }
                    }
                    if (i % 16 == 4) {  // for every sixteenth iteration by 4 we:
                        System.out.print("KA");     // add to the previous letters "KA"
                        for (int j = 1; j < notesArray[i - 4].length; j++) {    // and also muting last chord
                            // (it was 4 notes ago)
                            piano.noteOff(notesArray[i - 4][j]);      // (also every note)
                        }
                    }
                    if (i % 16 == 0 || i % 8 == 2) {    // percussion part
                        drums.noteOn(35, 100);    // kick
                    } else if (i % 8 == 4) {
                        drums.noteOn(40, 100);    // snare
                    }
                }
                player.play(0,80, note);  // and the most important command -
                // play from player this (current) note
            }
        }
    }
}
