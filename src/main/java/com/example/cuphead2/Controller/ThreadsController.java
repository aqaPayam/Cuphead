package com.example.cuphead2.Controller;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;

import java.util.ArrayList;
import java.util.Timer;

public class ThreadsController {
    public static boolean ended = false;
    public static int point = 0;
    public static long time = 0;
    public static ArrayList<Timer> timers = new ArrayList<>();
    public static ArrayList<Timeline> timelines = new ArrayList<>();
    public static ArrayList<AnimationTimer> animationTimers = new ArrayList<>();
}
