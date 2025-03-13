package tema1c.ejemplosInterfaces;

public enum Color {
	AZUL, AMARILLO, ROJO, VERDE;
	
	// atributos y métodos
	
	/** Devuelve el color de java.awt.Color correspondiente al enum
	 * @return	Color convertido
	 */
	public java.awt.Color getColorAWT() {
		switch (this.ordinal()) {
			case 0:
				return java.awt.Color.BLUE;
			case 1:
				return java.awt.Color.YELLOW;
			case 2:
				return java.awt.Color.RED;
			case 3:
				return java.awt.Color.GREEN;
			default:   // En este caso nunca ocurrirá
				return null;
		}
	}
	
}
