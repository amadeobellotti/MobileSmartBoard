package environment;

import java.awt.Dimension;

public class DefaultEnvironment extends Environment {
	public static final Dimension DefaultDimension = new Dimension(1008, 709);

	public DefaultEnvironment() {
		super(DefaultDimension);
	}

	public DefaultEnvironment(Dimension d) {
		super(d);
	}

	

}
