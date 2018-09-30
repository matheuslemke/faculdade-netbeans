/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

/**
 *
 * @author matheus
 */
public class BeatModel implements BeatModelInterface, MetaEventListener
{

    // objetos que vão lidar com o som de verdade
    Sequencer sequencer;
    Sequence sequence;
    Track track;

    // batidas por minuto
    int bpm = 90;

    // observadores
    List<BeatObserver> beatObservers = new ArrayList<BeatObserver>();
    List<BPMObserver> bpmObservers = new ArrayList<BPMObserver>();

    @Override
    public void initialize()
    {
        setUpMidi();
        buildTrackAndStart();
    }

    @Override
    public void on()
    {
        sequencer.start();
        setBPM(90);
    }

    @Override
    public void off()
    {
        setBPM(0);
        sequencer.stop();
    }

    @Override
    public void setBPM(int bpm)
    {
        this.bpm = bpm;
        sequencer.setTempoInBPM(getBPM());
        notifyBPMObservers();
    }

    @Override
    public int getBPM()
    {
        return bpm;
    }

    // métodos de notificação de observadores
    void beatEvent()
    {
        notifyBeatObservers();
    }

    @Override
    public void registerObserver(BeatObserver o)
    {
        beatObservers.add(o);
    }

    private void notifyBeatObservers()
    {
        for (BeatObserver bo : beatObservers)
        {
            bo.updateBeat();
        }
    }

    @Override
    public void removeObserver(BeatObserver o)
    {
        beatObservers.remove(o);
    }

    @Override
    public void registerObserver(BPMObserver o)
    {
        bpmObservers.add(o);
    }

    private void notifyBPMObservers()
    {
        for (BPMObserver bo : bpmObservers)
        {
            bo.updateBPM();
        }
    }

    @Override
    public void removeObserver(BPMObserver o)
    {
        bpmObservers.remove(o);
    }

    // Os métodos a seguir implementam a geração do som real
    @Override
    public void meta(MetaMessage meta)
    {
        if (meta.getType() == 47)
        {
            beatEvent();
            sequencer.setMicrosecondPosition(0);
            sequencer.start();
            sequencer.setTempoInBPM(getBPM());
// setBPM(getBPM());
        }
    }

    public void setUpMidi()
    {
        try
        {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.addMetaEventListener(this);
            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(getBPM());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void buildTrackAndStart()
    {
        int[] trackList =
        {
            35, 0, 46, 0
        };
        sequence.deleteTrack(null);
        track = sequence.createTrack();
        makeTracks(trackList);
        track.add(makeEvent(192, 9, 1, 0, 4));
        try
        {
            sequencer.setSequence(sequence);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void makeTracks(int[] list)
    {
        for (int i = 0; i < list.length; i++)
        {
            int key = list[i];
            if (key != 0)
            {
                track.add(makeEvent(144, 9, key, 100, i));
                track.add(makeEvent(128, 9, key, 100, i + 1));
            }
        }
    }

    public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick)
    {
        MidiEvent event = null;
        try
        {
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            event = new MidiEvent(a, tick);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return event;
    }
}
