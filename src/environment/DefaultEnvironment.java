package environment;

import java.awt.Dimension;

public class DefaultEnvironment extends Environment {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5389350490641773302L;
	public static final Dimension DefaultDimension = new Dimension(1008, 709);

	public DefaultEnvironment() {
		super(DefaultDimension);
	}

	public DefaultEnvironment(Dimension d) {
		super(d);
	}

	

}
