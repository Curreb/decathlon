import com.example.decathlon.common.CalcTrackAndField;
import com.example.decathlon.deca.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalcTrackAndFieldDecathlonTest {

    @Test //testa matten i klassen TrackAndField för track
    void calculateTrack_100m_1000Points() {
        CalcTrackAndField calc = new CalcTrackAndField();
        double A = 25.4347;
        double B = 18.0;
        double C = 1.81;
        double time = 10.395; // 1000p enligt tabellerna
        int points = calc.calculateTrack(A, B, C, time);
        assertEquals(1000, points);
    }

    @Test //testa matten i klassen TrackAndField för Field
    void calculateField_LongJump_1000Points() {
        CalcTrackAndField calc = new CalcTrackAndField();
        double A = 0.14354;
        double B = 220.0;   // cm
        double C = 1.40;
        double distance = 776.0; // cm (7.76 m) => 1000p
        int points = calc.calculateField(A, B, C, distance);
        assertEquals(1000, points);
    }

    @Nested
    class Deca100MTest {
        @Test void calc_100m_1000p() { assertEquals(1000, new Deca100M().calculateResult(10.395)); }
        @Test void calc_100m_900p()  { assertEquals(900,  new Deca100M().calculateResult(10.827)); }
        @Test void calc_100m_800p()  { assertEquals(800,  new Deca100M().calculateResult(11.278)); }
        @Test void calc_100m_0p()    { assertEquals(0,    new Deca100M().calculateResult(18.0));   }
    }

    @Nested
    class Deca400MTest {
        @Test void calc_400m_1000p(){ assertEquals(1000, new Deca400M().calculateResult(46.17)); }
        @Test void calc_400m_933p() { assertEquals(933,  new Deca400M().calculateResult(47.50)); }
        @Test void calc_400m_815p() { assertEquals(815,  new Deca400M().calculateResult(50.00)); }
        @Test void calc_400m_0p()   { assertEquals(0,    new Deca400M().calculateResult(82.00)); }
    }

    @Nested
    class Deca110MHurdlesTest {
        @Test void calc_110h_1000p(){ assertEquals(1000, new Deca110MHurdles().calculateResult(13.80)); }
        @Test void calc_110h_911p() { assertEquals(911,  new Deca110MHurdles().calculateResult(14.50)); }
        @Test void calc_110h_850p() { assertEquals(850,  new Deca110MHurdles().calculateResult(15.00)); }
        @Test void calc_110h_0p()   { assertEquals(0,    new Deca110MHurdles().calculateResult(28.50)); }
    }

    @Nested
    class Deca1500MTest {
        @Test void calc_1500m_1000p(){ assertEquals(1000, new Deca1500M().calculateResult(233.79)); } // 3:53.79
        @Test void calc_1500m_953p() { assertEquals(953,  new Deca1500M().calculateResult(240.00)); } // 4:00.00
        @Test void calc_1500m_745p() { assertEquals(745,  new Deca1500M().calculateResult(270.00)); } // 4:30.00
        @Test void calc_1500m_0p()   { assertEquals(124,    new Deca1500M().calculateResult(400.00)); } // 8:00.00
    }

    @Nested
    class DecaLongJumpTest {
        @Test void calc_LJ_1000p(){ assertEquals(1000, new DecaLongJump().calculateResult(776.0)); } // 7.76 m
        @Test void calc_LJ_935p() { assertEquals(935,  new DecaLongJump().calculateResult(750.0)); }
        @Test void calc_LJ_814p() { assertEquals(814,  new DecaLongJump().calculateResult(700.0)); }
        @Test void calc_LJ_0p()   { assertEquals(0,    new DecaLongJump().calculateResult(220.0)); } // B-höjd => 0
    }

    @Nested
    class DecaHighJumpTest {
        @Test void calc_HJ_1000p(){
            assertEquals(1000, new DecaHighJump().calculateResult(220.761552126905)); // ~2.2076 m
        }
        @Test void calc_HJ_992p(){ assertEquals(992, new DecaHighJump().calculateResult(220.0)); }
        @Test void calc_HJ_896p(){ assertEquals(896, new DecaHighJump().calculateResult(210.0)); }
        @Test void calc_HJ_0p()  { assertEquals(0,   new DecaHighJump().calculateResult(75.0));  } // B-höjd
    }

    @Nested
    class DecaPoleVaultTest {
        @Test void calc_PV_1000p(){
            assertEquals(1000, new DecaPoleVault().calculateResult(528.6271282710284)); // ~5.286 m
        }
        @Test void calc_PV_998p(){ assertEquals(998, new DecaPoleVault().calculateResult(528.0)); }
        @Test void calc_PV_972p(){ assertEquals(972, new DecaPoleVault().calculateResult(520.0)); }
        @Test void calc_PV_0p()  { assertEquals(0,   new DecaPoleVault().calculateResult(100.0)); } // B-höjd
    }

    @Nested
    class DecaShotPutTest {
        @Test void calc_SP_1000p(){ assertEquals(1000, new DecaShotPut().calculateResult(18.40)); }
        @Test void calc_SP_851p() { assertEquals(851,  new DecaShotPut().calculateResult(16.00)); }
        @Test void calc_SP_728p() { assertEquals(728,  new DecaShotPut().calculateResult(14.00)); }
        @Test void calc_SP_0p()   { assertEquals(0,    new DecaShotPut().calculateResult(1.50));  } // B-distans
    }

    @Nested
    class DecaDiscusTest {
        @Test void calc_DIS_1000p(){ assertEquals(1000, new DecaDiscusThrow().calculateResult(56.17)); }
        @Test void calc_DIS_870p() { assertEquals(870,  new DecaDiscusThrow().calculateResult(50.00)); }
        @Test void calc_DIS_665p() { assertEquals(665,  new DecaDiscusThrow().calculateResult(40.00)); }
        @Test void calc_DIS_0p()   { assertEquals(0,    new DecaDiscusThrow().calculateResult(4.00));  } // B-distans
    }

    @Nested
    class DecaJavelinTest {
        @Test void calc_JAV_1000p(){ assertEquals(1000, new DecaJavelinThrow().calculateResult(77.19)); }
        @Test void calc_JAV_889p() { assertEquals(889,  new DecaJavelinThrow().calculateResult(70.00)); }
        @Test void calc_JAV_738p() { assertEquals(738,  new DecaJavelinThrow().calculateResult(60.00)); }
        @Test void calc_JAV_0p()   { assertEquals(0,    new DecaJavelinThrow().calculateResult(7.00));  } // B-distans
    }

    // Enkla “gränsfall” – när (B - time) eller (distance - B) inte är positivt.
    // Med nuvarande implementation blir det Double.NaN -> cast till int => 0.
    @Test
    void calculateTrack_TimeWorseThanB_ReturnsZero() {
        CalcTrackAndField calc = new CalcTrackAndField();
        double A = 25.4347;
        double B = 18.0;
        double C = 1.81;
        double time = 20.0; // B - time < 0
        int points = calc.calculateTrack(A, B, C, time);
        assertEquals(0, points);
    }

    @Test
    void calculateField_DistanceBelowB_ReturnsZero() {
        CalcTrackAndField calc = new CalcTrackAndField();
        double A = 0.14354;
        double B = 220.0;
        double C = 1.40;
        double distance = 200.0; // distance - B < 0
        int points = calc.calculateField(A, B, C, distance);
        assertEquals(0, points);
    }
}



