package net.theexceptionist.coherentvillages.math;

import java.util.Random;

public class PolarCoord {
	public static final int QUAD_1 = 1;
	public static final int QUAD_2 = 2;
	public static final int QUAD_3 = 3;
	public static final int QUAD_4 = 4;
	
	protected double radius;
	protected double rad;
	protected double x, pointX;
	protected double z, pointZ;
	protected double originX;
	protected double originZ;
	protected int quad;
	private Random rand;
	
	public PolarCoord(final Random random, final double originX, final double originZ, final double coordX, final double coordZ)
	{
		this.x = coordX - originX;
		this.z = coordZ - originZ;
		this.originX = originX;
		this.originZ = originZ;
		this.rand = random;
	
		initQuad();
		initRad();
		initRadius();
	}

	public PolarCoord(final Random random, int originX, int originZ, int radius) {
		this.originX = originX;
		this.originZ = originZ;	
		this.radius = radius;
		this.rad = 0;
		this.rand = random;
		
		updateCoord();
	}
	
	public void setRadius(double radius)
	{
		this.radius = radius;
		
		updateCoord();
	}

	private void initRadius() {
		this.radius = Math.sqrt(x * x + z * z);
	}

	private void initRad() {
		double localRad = Math.atan2(this.z, this.x);
		double offset = 0;
		
		if(quad == QUAD_1 | quad == QUAD_3)
		{
			offset = (Math.PI / 2) * (quad - 1);
			this.rad = localRad + offset;
		}
		else
		{
			offset = (Math.PI / 2) * (quad);
			this.rad = offset;
		}
	}

	private void initQuad() {
		if(this.x < 0 && this.z < 0)
		{
			quad = QUAD_3;
		}
		else if(this.x > 0 && this.z > 0)
		{
			quad = QUAD_1;
		}
		else if(this.x < 0 && this.z > 0)
		{
			quad = QUAD_2;
		}
		else
		{
			quad = QUAD_4;
		}
	}

	public double getRadius() {
		return radius;
	}

	public double getDegrees() {
		return Math.toDegrees(rad);
	}

	public double getRad() {
		return rad;
	}

	public void addDegree(int degree) {
		this.rad += Math.toRadians(degree);
		updateCoord();
	}
	
	public void updateCoord()
	{
		setX();
		setZ();
		initQuad();
	}
	
	public void setDegree(int degree)
	{
		this.rad = Math.toRadians(degree);
		updateCoord();
	}

	public double setX() {
		this.x = this.radius * Math.sin(rad);
		return x;
	}

	public double setZ() {
		this.z = this.radius * Math.cos(rad);
		return z;
	}

	public double getWorldX() {
		return this.originX + x;
	}

	public double getWorldZ() {
		return this.originZ + z;
	}

	public void printStats() {
		System.out.println("X: "+this.x);
		System.out.println("Z: "+this.z);
		System.out.println("World X: "+this.getWorldX());
		System.out.println("World Z: "+this.getWorldZ());
		System.out.println("Rad: "+Math.toDegrees(rad));
		System.out.println("Radius: "+this.radius);
		System.out.println("Quad: "+this.quad);
	}
	
	public void mirror()
	{
		this.addDegree(180);
	}
	
	public void setRandomAdjacentCoord()
	{
		if(rand.nextInt(100) < 50)
		{
			this.setToRandomArcQuad(quad - 1);
		}
		else
		{
			this.setToRandomArcQuad(quad + 1);
		}
	}
	
	public void setToRandomArcQuad(int baseQuad)
	{
		int quad = baseQuad; 
		if(quad < QUAD_1) quad = QUAD_4;
		if(quad > QUAD_4) quad = QUAD_1;
		
		switch(quad)
		{
			case QUAD_1:
			{
				this.setDegree(0);
				this.addDegree(rand.nextInt(90));
			}
			break;
			case QUAD_2:
			{
				this.setDegree(90);
				this.addDegree(rand.nextInt(90));
			}
			break;
			case QUAD_3:
			{
				this.setDegree(180);
				this.addDegree(rand.nextInt(90));
			}
			break;
			case QUAD_4:
			{
				this.setDegree(270);
				this.addDegree(rand.nextInt(90));
			}
			break;
		}
	}
	
}
