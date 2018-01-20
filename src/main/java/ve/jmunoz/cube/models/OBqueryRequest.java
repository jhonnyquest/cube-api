package ve.jmunoz.cube.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OBqueryRequest {
	
	private int x1;
	private int y1;
	private int z1;
	private int x2;
	private int y2;
	private int z2;
	
	public OBqueryRequest(){
		super();
	}

	@JsonProperty("x1")
	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	@JsonProperty("y1")
	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	@JsonProperty("z1")
	public int getZ1() {
		return z1;
	}

	public void setZ1(int z1) {
		this.z1 = z1;
	}

	@JsonProperty("x2")
	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	@JsonProperty("y2")
	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	@JsonProperty("z2")
	public int getZ2() {
		return z2;
	}

	public void setZ2(int z2) {
		this.z2 = z2;
	}

	@Override
	public String toString() {
		return "OBqueryRequest [x1=" + x1 + ", y1=" + y1 + ", z1=" + z1 + ", x2=" + x2 + ", y2=" + y2 + ", z2=" + z2
				+ "]";
	}
}
