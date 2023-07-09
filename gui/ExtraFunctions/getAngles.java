package ExtraFunctions;

public class getAngles extends getLength {
	private double angles[];

	public getAngles() {
		super();
		this.angles = new double[] { 90, 90, 90, 90 };
	
	}

	private void calculateAngles() {

		double a0, a1, a2, a3;
		

		a0 = Math.acos(((lengths[0] * lengths[0]) + (lengths[3] * lengths[3]) - (diagonal1 * diagonal1))
				/ (2 * lengths[0] * lengths[3]));
		a0 = (a0 / Math.PI) * 180;
		a0 = ((double) Math.round(a0 * 100)) / 100.0;

		a1 = Math.acos(((lengths[0] * lengths[0]) + (lengths[1] * lengths[1]) - (diagonal2 * diagonal2))
				/ (2 * lengths[0] * lengths[1]));
		a1 = (a1 / Math.PI) * 180;
		a1 = ((double) Math.round(a1 * 100)) / 100.0;

		a2 = Math.acos(((lengths[1] * lengths[1]) + (lengths[2] * lengths[2]) - (diagonal1 * diagonal1))
				/ (2 * lengths[2] * lengths[1]));
		a2 = (a2 / Math.PI) * 180;
		a2 = ((double) Math.round(a2 * 100)) / 100.0;

		a3 = Math.acos(((lengths[3] * lengths[3]) + (lengths[2] * lengths[2]) - (diagonal2 * diagonal2))
				/ (2 * lengths[3] * lengths[2]));
		a3 = (a3 / Math.PI) * 180;
		a3 = ((double) Math.round(a3 * 100)) / 100.0;

		angles[0] = a0;
		angles[1] = a1;
		angles[2] = a2;
		angles[3] = a3;
	}

	public double getAngle(int i) {
		return angles[i];
	}

	public double[] getangles() {
		return angles;
	}

	public void setPoint(int x, int y, int i) {
		xPoints[i] = x;
		yPoints[i] = y;
		calculateArea();
		calculateLengths();
		calculateAngles();
	}
	
	public void setPoint() {
		calculateArea();
		calculateLengths();
		calculateAngles();
	}
}