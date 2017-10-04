

public class CollisionState {
	public double[] dM1x, dM2x, dCMx; // position
	public double dM1v, dM2v, dCMv; // velocity
	public double dM1a, dM2a, dCMa; // angle

	public CollisionState(double[] m1, double[] m2, double[] m3)
	{
		dM1x = m1;
		dM2x = m2;
		dCMx = m3;
	}

    public CollisionState(CollisionState x)
    {
        dM1x = new double[] {x.dM1x[0],x.dM1x[1]};
        dM2x = new double[] {x.dM2x[0],x.dM2x[1]};
        dCMx = new double[] {x.dCMx[0],x.dCMx[1]};
        dM1v = x.dM1v;
        dM2v = x.dM2v;
        dCMv = x.dCMv;
        dM1a = x.dM1a;
        dM2a = x.dM2a;
        dCMa = x.dCMa;
    }
    
	public CollisionState()
	{
		;
	}
}
