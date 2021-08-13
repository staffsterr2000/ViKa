package program;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class Player {
    private Synthesizer synth;
    private MidiChannel[] channels;     // we can work with the channels from the method play(), and from anywhere just with
    // the address of a channel that we can get from the method below

    public MidiChannel getChannel(int channel) {    // get a channel for work with it
        return channels[channel];
    }

    public Player() {       // constructor
        try {
            synth = MidiSystem.getSynthesizer();        // get a synthesizer
            synth.open();
            channels = synth.getChannels();             // get channels
        }
        catch (MidiUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    public void close() {
        synth.close();
    }   // we don't need it cause the song is endless (as we want)

    public void play(int channelNum, int volume, int...note) {  // the heart of this class
        // here we can play notes with it channel and velocity

        // standart values that we can change (channel, instrument, duration)
        MidiChannel channel = channels[channelNum];     // getting into channel
        channel.programChange(2);       // changing instrument
        int duration = 140;

        boolean isSpace = false;        // boolean variable that changes if we have no notes in array (both: -1)
        int startPosition = 0;          // start position of playing notes
        int endPosition = note.length;  // end position of playing notes
        if (note[0] != -1 && note[1] != -1)     // if we have solo note and chord note/s in array then we starting from the first one
            startPosition = 0;                  // and going to the last one

        else if (note[0] == -1 && note[1] == -1)    // *boolean variable that changes if we have no notes in array (both: -1)*
            isSpace = true;

        else if (note[0] == -1) {       // if we have only chord note/s -> playing them
            // (starting from index 1)
            startPosition = 1;
        }

        else {          // and finally if we have only solo notes -> playing just the first note
            endPosition = startPosition + 1;    // start - 0, finish - <1
        }

        if (!isSpace) {     // if we have any note
            for (int i = startPosition; i < endPosition; i++)   // play all notes (with volume as an argument from signature
                channel.noteOn(note[i], volume);                // of this method

            try {           // time for release the note/s
                Thread.sleep(duration);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            if (note[0] != -1)              // if we have a solo note
                channel.noteOff(note[0]);       // mute the solo note
        }
        else {              // if we haven't any note
            try {
                Thread.sleep(duration);     // also wait
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

