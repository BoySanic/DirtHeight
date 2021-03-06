package Reduced;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Random;

public class Main {
  // like 100000 for decent results in like 2 minutes with 8 data points
  // like 1000000 for an undersampled 30-60 min run with almost all data points
  // using all data points, total sample size across hosts should be > 100000000
  public static final int samplesize = 1000000 * 1;
  public static final int seed = 0;

  public static final Random rand = new Random(seed);
  public static final int minwfx = -235;
  public static final int maxwfx = 135; // inclusive
  public static final int minwfz = -32;
  public static final int maxwfz = -25; // inclusive

  public static void main(String[] args) {

    // LocNoise.locnoise.printAll();

    // new CPG().printAll();

    // System.out.println("# tests: " + PntTest.tests.length);

    int[][] counts = new int[maxwfx - minwfx + 1][maxwfz - minwfz + 1];

    for (int sample = 0; sample < samplesize; sample++) {
      CPG cpg = new CPG();
      for (int wfx = minwfx; wfx <= maxwfx; wfx++) {
        for (int wfz = minwfz; wfz <= maxwfz; wfz++) {
          if (PntTest.checkAll(wfx, wfz, cpg)) {
            counts[wfx - minwfx][wfz - minwfz]++;
          }
        }
      }
    }
    // for (int wfx = minwfx; wfx <= maxwfx; wfx++) {
    // for (int wfz = minwfz; wfz <= maxwfz; wfz++) {
    // if (counts[wfx - minwfx][wfz - minwfz] > 0) {
    // System.out.println(wfx + " " + wfz + " " + counts[wfx - minwfx][wfz -
    // minwfz]);
    // }
    // }
    // }

    try {
      PrintWriter out = new PrintWriter("out.txt");
      out.println(seed);
      out.println(samplesize);
      for (int wfx = minwfx; wfx <= maxwfx; wfx++) {
        for (int wfz = minwfz; wfz <= maxwfz; wfz++) {
          if (counts[wfx - minwfx][wfz - minwfz] > 0) {
            out.println(wfx + " " + wfz + " " + counts[wfx - minwfx][wfz - minwfz]);
          }
        }
      }
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
