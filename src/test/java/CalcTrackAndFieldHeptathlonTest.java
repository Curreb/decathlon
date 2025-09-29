import com.example.decathlon.common.CalcTrackAndField;
import com.example.decathlon.deca.*;
import com.example.decathlon.heptathlon.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalcTrackAndFieldHeptathlonTest {

    @Test //testa matten i klassen TrackAndField för track
    void calculateTrack_Hep200m_1000Points() {
        CalcTrackAndField calc = new CalcTrackAndField();
        double A = 4.99087;
        double B = 42.5;
        double C = 1.81;
        double time = 23.80; // 1000p enligt tabellerna
        int points = calc.calculateTrack(A, B, C, time);
        assertEquals(1000, points);
    }

    @Test //testa matten i klassen TrackAndField för Field
    void calculateField_HepLongJump_1001Points() {
        CalcTrackAndField calc = new CalcTrackAndField();
        double A = 0.188807;
        double B = 210.0;   // cm
        double C = 1.41;
        double distance = 648.0; // cm (6.48 m) => 1001p
        int points = calc.calculateField(A, B, C, distance);
        assertEquals(1001, points);
    }

    @Nested
    class Hep100MHurdlesTest{
        @Test void calc_100mHurdles() {assertEquals(1172, new Hep100MHurdles().calculateResult(12.69));}
    }

    @Nested
    class HepHighJumpTest{
        @Test void calc_highJump() {assertEquals(1054, new HeptHightJump().calculateResult(186)); }
    }

    @Nested
    class HepShotPutTest{
        @Test void calc_shotPut()   {assertEquals(915, new HeptShotPut().calculateResult(15.80));}
    }

    @Nested
    class Hep200MTest{
        @Test void calc_200M()  {assertEquals(1123, new Hep200M().calculateResult(22.56));}
    }

    @Nested
    class HepLongJumpTest{
        @Test void calc_longJump() {assertEquals(1264, new HeptLongJump().calculateResult(727));}
    }

    @Nested
    class HepJavelinThrow{
        @Test void calc_javelinThrow() {assertEquals(776,new HeptJavelinThrow().calculateResult(45.66));}
    }

    @Nested
    class Hep800MTest{
        @Test void calc_800m() {assertEquals(987, new Hep800M().calculateResult(128.51));}
    }

    @Test
    void calculateTrack_TimeWorseThanB_ReturnsZero() {
        CalcTrackAndField calc = new CalcTrackAndField();
        double A = 4.99087;
        double B = 42.5;
        double C = 1.81;
        double time = 50.0; // B - time < 0
        int points = calc.calculateTrack(A, B, C, time);
        assertEquals(0, points);
    }

    @Test
    void calculateField_DistanceBelowB_ReturnsZero() {
        CalcTrackAndField calc = new CalcTrackAndField();
        double A = 0.188807;
        double B = 210.0;
        double C = 1.41;
        double distance = 200.0; // distance - B < 0
        int points = calc.calculateField(A, B, C, distance);
        assertEquals(0, points);
    }

//
//
//
//
//    @Nested
//    class Deca400MTest {
//        @Test void calc_400m_1000p(){ assertEquals(1000, new Deca400M().calculateResult(46.17)); }
//        @Test void calc_400m_933p() { assertEquals(933,  new Deca400M().calculateResult(47.50)); }
//        @Test void calc_400m_815p() { assertEquals(815,  new Deca400M().calculateResult(50.00)); }
//        @Test void calc_400m_0p()   { assertEquals(0,    new Deca400M().calculateResult(82.00)); }
//    }
//


}



