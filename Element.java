/**
 * 
 * filename: Element.java
 * 
 * version: 1.0 04/26/2017
 *
 * revisions: Initial version
 */
/**
 * 
 * @author vpb8262 Vishal Bulchandani
 * @author pan7447 Parvathi Nair
 *
 */
public class Element {
	int r;
	int c;
	int value;

	/**
	 * 
	 * @param r
	 *            - row number
	 * @param c
	 *            - column number
	 * @param value
	 *            - the element in the row r and column c
	 */
	public Element(int r, int c, int value) {
		this.r = r;
		this.c = c;
		this.value = value;
	}

	/**
	 * This method is overriden for object comparison
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Element)) {
			return false;
		}
		Element elem = (Element) obj;

		if (r == elem.r && c == elem.c && value == elem.value) {
			return true;
		}
		return false;
	}

	/**
	 * This method is overriden as we are dealing with objects and since we
	 * override equals
	 */
	@Override
	public int hashCode() {
		int hash = 1;
		hash *= 31 + Integer.hashCode(r);
		hash *= 31 + Integer.hashCode(c);
		hash *= 31 + Integer.hashCode(value);
		return hash;
	}
}
