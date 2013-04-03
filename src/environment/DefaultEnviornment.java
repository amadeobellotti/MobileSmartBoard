package environment;

import java.awt.Dimension;

public class DefaultEnviornment extends Enviornment {
	public static final Dimension DefaultDimension = new Dimension(1008, 709);

	public DefaultEnviornment() {
		super(DefaultDimension);
	}

	public DefaultEnviornment(Dimension d) {
		super(d);
	}

	

}
