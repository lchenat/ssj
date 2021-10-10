package markovchainrqmc;

import umontreal.ssj.markovchainrqmc.*;

import umontreal.ssj.hups.*;
import umontreal.ssj.rng.*;
import umontreal.ssj.stat.Tally;
import umontreal.ssj.util.*;
import umontreal.ssj.util.sort.*;

import java.io.BufferedWriter;
import java.lang.Math;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import py4j.GatewayServer;

//https://www.py4j.org/getting_started.html#writing-the-java-program
public class SobolEntryPoint {
	
   RandomStream stream = new MRG32k3a();
   PointSetRandomization rand = new RandomShift(stream);
   PointSet p;
	
   public SobolEntryPoint () {}
   
   public double[][] sample(int pow, int dim) {
	    int n_samples = (int)Math.pow(2, pow);
	    double[][] points = new double[n_samples][dim];


        // p = new DigitalNetBase2();
        // DigitalNetBase2 p = (new SobolSequence(pow, 31, dim)).toNetShiftCj();
        // LMScrambleShift randLMS = new LMScrambleShift(new MRG32k3a());
        DigitalNetBase2 p = new SobolSequence(pow, 31, dim);
        p.leftMatrixScramble(stream);
        p.addRandomShift(stream);


		// p = new SobolSequence(pow, 31, dim);
		// p.randomize(rand);
        PointSetIterator point_stream = p.iterator ();
        //stream.resetCurPointIndex ();
        for (int i = 0; i < n_samples; ++i) {
            point_stream.nextPoint(points[i], dim);
        }
        return points;
   }

   public void display(int pow, int dim) {
	   double[][] points = sample(pow, dim);
	   for (double[] row: points) {
		   for (double x: row) {
			   System.out.printf("%f ", x);
		   }
		   System.out.println();
	   }
   }
   
   public static void main (String[] args) {
	   SobolEntryPoint sobol_entry = new SobolEntryPoint();
       GatewayServer gatewayServer = new GatewayServer(sobol_entry);
       gatewayServer.start();
       System.out.println("SobolEntryPoint: Gateway Server Started");
	   //sobol_entry.display(3, 5);
   }
}
