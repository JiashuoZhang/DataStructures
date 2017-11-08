public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	/** Name of an image in the images directory that depicts the planet. */
	public String imgFileName;

	/** Gravitational constant. */
	private static final double G = 6.67 * Math.pow(10, -11);

	public Planet(double xP, double yP, double xV,
				  double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	/** Returns the distance between two planets. */
	public double calcDistance(Planet other) {
		if (!(other instanceof Planet)) return -1;
		double dx = other.xxPos - this.xxPos;
		double dy = other.yyPos - this.yyPos;
		return Math.sqrt(dx*dx + dy * dy);
	}

	public double calcForceExertedBy(Planet other) {
		if (!(other instanceof Planet)) return -1;
		double d = calcDistance(other);
		return this.mass * other.mass * G / (d * d);
	}

	public double calcForceExertedByX(Planet other) {
		if (!(other instanceof Planet)) return -1;
		return calcForceExertedBy(other) * (other.xxPos - this.xxPos) / calcDistance(other);
	}

	public double calcForceExertedByY(Planet other) {
		if (!(other instanceof Planet)) return -1;
		return calcForceExertedBy(other) * (other.yyPos - this.yyPos) / calcDistance(other);
	}

	public double calcNetForceExertedByX(Planet[] planets) {
		double netForceX = 0;
		for (Planet planet : planets) {
			if (!this.equals(planet)) {
				netForceX += calcForceExertedByX(planet);
			}
		}
		return netForceX;
	}

	public double calcNetForceExertedByY(Planet[] planets) {
		double netForceY = 0;
		for (Planet planet : planets) {
			if (!this.equals(planet)) {
				netForceY += calcForceExertedByY(planet);
			}
		}
		return netForceY;
	}

	public void update(double time, double forceX, double forceY) {
		double aX = forceX / mass;
		double aY = forceY / mass;
		xxVel = xxVel + aX * time;
		yyVel = yyVel + aY * time;
		xxPos = xxPos + time * xxVel;
		yyPos = yyPos + time * yyVel;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
}
