package com.example.decathlon.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalcTrackAndFieldDecathlonTest {

    @Test
    void calculateTrack_100m() {
        CalcTrackAndField calc = new CalcTrackAndField();
        double A = 25.4347;
        double B = 18.0;
        double C = 1.81;

        // Med assertAll körs alla asserts oavsett om någon failar
        assertAll(
                () -> assertEquals(1000, calc.calculateTrack(A, B, C, 10.395)),
                () -> assertEquals(900, calc.calculateTrack(A, B, C, 10.827)),
                () -> assertEquals(800, calc.calculateTrack(A, B, C, 11.278)),
                () -> assertEquals(800, calc.calculateTrack(A, B, C, 11.278)),
                () -> assertEquals(0, calc.calculateTrack(A, B, C, 20.0)) // ogiltigt fall
        );
    }
//    void calculateTrack_100m_1000Points() {
//        CalcTrackAndField calc = new CalcTrackAndField();
//        double A = 25.4347;
//        double B = 18.0;
//        double C = 1.81;
//        double time = 10.395; // 1000p enligt tabellerna
//        int points = calc.calculateTrack(A, B, C, time);
//        assertEquals(1000, points);
//    }

    @Test
    void calculateTrack_400m() {
        CalcTrackAndField calc = new CalcTrackAndField();
        double A = 1.53775;
        double B = 82.0;
        double C = 1.81;
        double time = 46.17; // 1000p
        int points = calc.calculateTrack(A, B, C, time);
        assertEquals(1000, points);
    }

}
