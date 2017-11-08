public class NBody {
	private static int N;
	private static double r;

    public static double readRadius(String filePath) {
    	In in = new In(filePath);
    	N = in.readInt();
    	r = in.readDouble();
    	return r;
    }

    public static Planet[] readPlanets(String filePath) {
    	In in = new In(filePath);
    	N = in.readInt();
    	r = in.readDouble();
    	Planet[] result = new Planet[N];
    	for (int i = 0; i < N; i++) {
    		double xP = in.readDouble();
    		double yP = in.readDouble();
    		double xV = in.readDouble();
    		double yV = in.readDouble();
    		double m = in.readDouble();
    		String img = in.readString();
    		result[i] = new Planet(xP, yP, xV, yV, m, img);
    	}
    	return result;
    }

    public static void main(String[] args) {
    	StdAudio.play("audio/2001.mid");
    	double T = Double.parseDouble(args[0]);
    	double dt = Double.parseDouble(args[1]);
    	String filename = args[2];
    	double radius = readRadius(filename);
    	Planet[] planets = readPlanets(filename);

		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");
		for (Planet p : planets) p.draw();

		double[] xForces = new double[planets.length];
		double[] yForces = new double[planets.length];
		for (double time = 0; time < T; time += dt) {
			for (int i = 0; i < xForces.length; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for (int i = 0; i < planets.length; i++) planets[i].update(dt, xForces[i], yForces[i]);
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Planet p : planets) p.draw();
			StdDraw.show(10);
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
					planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
		}
	}
}
