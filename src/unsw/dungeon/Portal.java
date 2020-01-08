package unsw.dungeon;

public class Portal extends Entity {

	private Portal otherPortal;
	private int id;

	public Portal(int x, int y, int id) {
		super(x, y);
		this.id = id;
	}
	/**
	 * Setter functior the other portal
	 * @param p
	 */
	public void setOtherPortal(Portal p) {
		this.otherPortal = p;
	} 
	/**
	 * Getter function for the other portal
	 * @return
	 */
	public Portal getOtherPortal() {
		return otherPortal;
	}
	/**
	 * Getter function for portal id
	 * @return
	 */
	public int getId() {
		return id;
	}
	/**
	 * Teleports the player to the other portal
	 */
	public void interactWith(Player p, String moveCmd) {
		System.out.println("Teleporting to " + otherPortal);
		int destX = otherPortal.getX();
		int destY = otherPortal.getY();
		p.teleportPlayer(destX, destY);		
	}

	@Override
	public String toString() {
		return "Portal at (" + getX() + ", " + getY() + ") ";
		//		+ "and other portal at (" + otherPortal.getX() + ", " + otherPortal.getY() + ")" ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((otherPortal == null) ? 0 : otherPortal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Portal other = (Portal) obj;
		if (otherPortal == null) {
			if (other.otherPortal != null)
				return false;
		} else if (!otherPortal.equals(other.otherPortal))
			return false;
		return true;
	}
	
	
	

}
