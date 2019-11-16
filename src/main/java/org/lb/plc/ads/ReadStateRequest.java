package org.lb.plc.ads;

public class ReadStateRequest extends Payload {
	
	/**
	 * The ID of the command
	 */
	private final static int COMMAND_ID			=		0x0004;
	
	/**
	 * Create new {@link ReadStateRequest}
	 */
	public ReadStateRequest() {
	}
	
	@Override
	public int getCommandId() {
		return COMMAND_ID;
	}

	@Override
	public byte[] toBinary() {
		return null;
	}

	public static Payload valueOf(final byte[] data) {
		return new ReadStateRequest();
	}

	@Override
	public String toString() {
		return "AdsReadStateRequest";
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else {
			return false;
		}
	}
}
