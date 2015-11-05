package Packet;





public abstract class Packets{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7339312898785050048L;
	/**
	 * 
	 */
	

/*	public static enum PacketTypes {
		INVALID, LOGIN, DISCONNECT, MOVE;
	}*/

	private Type type;
	
	public void setType(Type type) {
		this.type = type;
	}
	public Type getType()
	{
		return type;
	}
}
