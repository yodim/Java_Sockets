import java.io.Serializable;

public class Planet implements Serializable {
	String name;
	String nbr_inhab;
	double surface_gravity;
	
	Planet(String n , double nbr_inhab2, double g) {
		name = n;
		nbr_inhab = nbr_inhab2 +" Billions";
		surface_gravity = g;
	}
}
