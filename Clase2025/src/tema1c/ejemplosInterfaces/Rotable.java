package tema1c.ejemplosInterfaces;

public interface Rotable {
	/*public abstract*/ 
	
	
	/** Rota el objeto un ángulo dado
	 * @param angulo	Ángulo en radianes. Incrementa la rotación que ya tuviera el objeto
	 * 					Puede ser negativo o positivo. En sentido horario.
	 */
	void rotar( double angulo );
}
