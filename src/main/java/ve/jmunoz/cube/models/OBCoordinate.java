package ve.jmunoz.cube.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <h1>Class OBCoordinate</h1><br>
 * Object business model to manage coordinates values to populate cube<br>
 * 
 * @author jmunoz
 * @since 2018-01-21
 * @version 0.0.1
 */
public class OBCoordinate {

	private int x;
	private int y;
	private int z;
	private float value;
	
	public OBCoordinate() {
		super();
	}
	
	@JsonProperty("x")
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	@JsonProperty("y")
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	@JsonProperty("z")
	public int getZ() {
		return z;
	}
	
	public void setZ(int z) {
		this.z = z;
	}
	
	@JsonProperty("value")
	public float getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "OBCoordinate [x=" + x + ", y=" + y + ", z=" + z + ", value=" + value + "]";
	}
}
