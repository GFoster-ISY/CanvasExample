package com.example.canvasdrawing;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javax.sound.midi.*;
import java.util.ArrayList;

public class AnimatedNotes extends AnimatedCanvas{

    int frameCount = 0;
    ArrayList<NoteRepresentation> notes;
    int[] melody = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            61,-1,-1,-1,61,-1,61,-1,-1,-1,62,-1,64,-1,-1,-1,
            61,-1,-1,-1,61,-1,61,-1,-1,-1,62,-1,64,-1,-1,-1,
            64,-1,-1,-1,64,-1,64,-1,-1,-1,66,-1,68,-1,-1,-1,
            69,-1,-1,-1,69,-1,-1,-1,66,-1,-1,64,-1,-1,62,-1,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                   };
    int[] chord1 ={45,-1,-1,-1,45,-1,-1,-1,45,-1,-1,-1,45,-1,-1,-1,
            46,-1,-1,-1,46,-1,-1,-1,46,-1,-1,-1,46,-1,-1,-1,
            49,-1,-1,-1,49,-1,-1,-1,49,-1,-1,-1,49,-1,-1,-1,
            50,-1,-1,-1,50,-1,-1,-1,50,-1,-1,49,-1,-1,46,-1,
            45,-1,-1,-1,45,-1,-1,-1,45,-1,-1,-1,45,-1,-1,-1,
            46,-1,-1,-1,46,-1,-1,-1,46,-1,-1,-1,46,-1,-1,-1,
            49,-1,-1,-1,49,-1,-1,-1,49,-1,-1,-1,49,-1,-1,-1,
            50,-1,-1,-1,50,-1,-1,-1,50,-1,-1,49,-1,-1,46,-1,
            45,-1,-1,-1,45,-1,-1,-1,45,-1,-1,-1,45,-1,-1,-1,
    };
    int[] chord2 ={61,-1,-1,-1,61,-1,-1,-1,61,-1,-1,-1,61,-1,-1,-1,
            58,-1,-1,-1,58,-1,-1,-1,58,-1,-1,-1,58,-1,-1,-1,
            61,-1,-1,-1,61,-1,-1,-1,61,-1,-1,-1,61,-1,-1,-1,
            57,-1,-1,-1,57,-1,-1,-1,57,-1,-1,61,-1,-1,58,-1,
            61,-1,-1,-1,61,-1,-1,-1,61,-1,-1,-1,61,-1,-1,-1,
            58,-1,-1,-1,58,-1,-1,-1,58,-1,-1,-1,58,-1,-1,-1,
            61,-1,-1,-1,61,-1,-1,-1,61,-1,-1,-1,61,-1,-1,-1,
            57,-1,-1,-1,57,-1,-1,-1,57,-1,-1,61,-1,-1,58,-1,
            61,-1,-1,-1,61,-1,-1,-1,61,-1,-1,-1,61,-1,-1,-1,
    };
    int[] chord3 ={64,-1,-1,-1,64,-1,-1,-1,64,-1,-1,-1,64,-1,-1,-1,
            62,-1,-1,-1,62,-1,-1,-1,62,-1,-1,-1,62,-1,-1,-1,
            64,-1,-1,-1,64,-1,-1,-1,64,-1,-1,-1,64,-1,-1,-1,
            62,-1,-1,-1,62,-1,-1,-1,62,-1,-1,61,-1,-1,62,-1,
            64,-1,-1,-1,64,-1,-1,-1,64,-1,-1,-1,64,-1,-1,-1,
            62,-1,-1,-1,62,-1,-1,-1,62,-1,-1,-1,62,-1,-1,-1,
            64,-1,-1,-1,64,-1,-1,-1,64,-1,-1,-1,64,-1,-1,-1,
            62,-1,-1,-1,62,-1,-1,-1,62,-1,-1,61,-1,-1,62,-1,
            64,-1,-1,-1,64,-1,-1,-1,64,-1,-1,-1,64,-1,-1,-1,
    };
    int[] chord4 ={69,-1,-1,-1,69,-1,-1,-1,69,-1,-1,-1,69,-1,-1,-1,
            66,-1,-1,-1,66,-1,-1,-1,66,-1,-1,-1,66,-1,-1,-1,
            68,-1,-1,-1,68,-1,-1,-1,68,-1,-1,-1,68,-1,-1,-1,
            66,-1,-1,-1,66,-1,-1,-1,66,-1,-1,68,-1,-1,66,-1,
            69,-1,-1,-1,69,-1,-1,-1,69,-1,-1,-1,69,-1,-1,-1,
            66,-1,-1,-1,66,-1,-1,-1,66,-1,-1,-1,66,-1,-1,-1,
            68,-1,-1,-1,68,-1,-1,-1,68,-1,-1,-1,68,-1,-1,-1,
            66,-1,-1,-1,66,-1,-1,-1,66,-1,-1,68,-1,-1,66,-1,
            69,-1,-1,-1,69,-1,-1,-1,69,-1,-1,-1,69,-1,-1,-1,
    };
    int index = 0;
    int lastNote0 = 0;
    int lastNote1 = 0;
    int lastNote2 = 0;
    int lastNote3 = 0;
    int lastNote4 = 0;
    int melodyVolume = 50;
    int chordVolume = 40;
    int notesPerFrame = 4;
    MidiChannel[] channels;
    AnimatedNotes(Canvas cnvDrawingArea) {
        super(cnvDrawingArea);
        notes = new ArrayList<>();
        createNotes();
        setFrameRate(40);
        frameLimit = (chord1.length+1)*notesPerFrame+10;
    }

    private void createNotes(){
        Synthesizer midiSynth;
        try {
            midiSynth = MidiSystem.getSynthesizer();
            midiSynth.open();
        } catch (MidiUnavailableException e) {
            throw new RuntimeException(e);
        }
        Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
        channels = midiSynth.getChannels();
        int instrument1 = 1;
        int instrument2 = 1;
        channels[0].programChange(instr[instrument1].getPatch().getBank(),instr[instrument1].getPatch().getProgram());
        channels[1].programChange(instr[instrument2].getPatch().getBank(),instr[instrument2].getPatch().getProgram());
    }
    @Override
    protected void paint() {
        if (frameCount % notesPerFrame == 0) {
            playNote();
        }
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (NoteRepresentation note : notes){
            note.draw(gc);
        }

        frameCount++;
    }

    private void playNote(){
        if (index < chord1.length) {
            if (melody[index]==0) channels[1].noteOff(lastNote0);
            if (chord1[index]==0) channels[0].noteOff(lastNote1);
            if (chord2[index]==0) channels[0].noteOff(lastNote2);
            if (chord3[index]==0) channels[0].noteOff(lastNote3);
            if (chord4[index]==0) channels[0].noteOff(lastNote4);
            if (melody[index]>0) {
                lastNote0 = melody[index];
                channels[1].noteOn(melody[index],melodyVolume);
                notes.add(new NoteBubble(canvas, melody[index], melodyVolume, index, chord1.length));
            }
            if (chord1[index]>0) {
                lastNote1 = chord1[index];
                channels[0].noteOn(chord1[index],chordVolume);
                notes.add(new NoteBubble(canvas, chord1[index], chordVolume, index, chord1.length));
            }
            if (chord2[index]>0){
                lastNote2 = chord2[index];
                channels[0].noteOn(chord2[index],chordVolume);
                notes.add(new NoteBubble(canvas,chord2[index], chordVolume, index, chord1.length));
            }
            if (chord3[index]>0) {
                lastNote3 = chord3[index];
                channels[0].noteOn(chord3[index],chordVolume);
                notes.add(new NoteBubble(canvas, chord3[index], chordVolume, index, chord1.length));
            }
            if (chord4[index]>0){
                lastNote4 = chord4[index];
                channels[0].noteOn(chord4[index],chordVolume);
                notes.add(new NoteBubble(canvas,chord4[index], chordVolume, index, chord1.length));
            }
            index++;
        }
    }

    private abstract class NoteRepresentation{
        Canvas canvas;
        int note;
        int volume;
        int index;
        int noteCount;
        int age;
        Color colour;
        private final static Color[] colours = {Color.DARKRED, Color.RED, Color.ORANGERED, Color.ORANGE, Color.LIGHTSALMON
                         ,Color.LIGHTYELLOW, Color.YELLOW, Color.YELLOWGREEN, Color.GREEN, Color.LIGHTSEAGREEN
                         ,Color.DARKSEAGREEN, Color.BLUE, Color.DARKBLUE, Color.PURPLE, Color.INDIGO};
        NoteRepresentation(Canvas canvas, int note, int volume, int index, int noteCount){
            this.noteCount = noteCount;
            this.canvas = canvas;
            this.note = note;
            this.volume = volume;
            this.index = index;
            age = 10;
            colour = colours[note%12];
        }
        public abstract void draw(GraphicsContext gc);
    }

    private class NoteBubble extends NoteRepresentation{

        int x;
        int y;
        int r;
        int thickness;

        NoteBubble(Canvas canvas, int note, int volume, int index, int noteCount) {
            super(canvas, note, volume, index, noteCount);
            x = 50 + index*(((int)canvas.getWidth()-60)/noteCount);
            y = (int)canvas.getHeight()/2 - (note-50)*5;
            r = volume/10;
            thickness = volume/5;
        }

        @Override
        public void draw(GraphicsContext gc) {
            if (age>0){
                r++;
                y++;
            } else {
                r--;
                y--;
            }
            x-=2;
            age--;
            gc.setLineWidth(thickness);
            gc.setStroke(colour);
            gc.strokeOval(x,y,r,r);
        }
    }
}
