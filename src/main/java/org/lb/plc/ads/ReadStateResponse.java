package org.lb.plc.ads;

import org.lb.plc.Toolbox;

public class ReadStateResponse extends Payload {
	
	/**
	 * The ID of the command
	 */
	private final static int COMMAND_ID			=		0x0004;
	
	/**
	 * The result code
	 */
	private final long errorCode;
	
	/**
	 * The ADS status
	 */
	private final short adsState;
	
	/**
	 * The device status
	 */
	private final short devState;

	/**
	 * Create new {@link WriteControlResponse}
	 * 
	 * @param errorCode ADS error number
	 */
	public ReadStateResponse(final long errorCode, final short adsState, final short devState) {
		this.errorCode = errorCode;
		this.adsState = adsState;
		this.devState = devState;
	}

	/**
	 * 
	 */
	@Override
	public org.lb.plc.ams.ErrorCode getErrorCode() {
		return org.lb.plc.ams.ErrorCode.valueOf(errorCode);
	}
	
	/**
	 * Returns the {@link #adsState}
	 * 
	 * @return the {@link #adsState}
	 */
	public short getAdsState() {
		return adsState;
	}
	
	/**
	 * Returns the {@link #devState}
	 * 
	 * @return the {@link #devState}
	 */
	public short getDevState() {
		return devState;
	}

	/**
	 * 
	 */
	@Override
	public int getCommandId() {
		return COMMAND_ID;
	}

	@Override
	public byte[] toBinary() {

		byte[] ret = new byte[8];
		
		Toolbox.copyIntoByteArray(ret, 0, Toolbox.uint32ToBytes(errorCode));
		Toolbox.copyIntoByteArray(ret, 4, Toolbox.uint16ToBytes(adsState));
		Toolbox.copyIntoByteArray(ret, 6, Toolbox.uint16ToBytes(devState));
		
		return ret;
	}

	public static Payload valueOf(final byte[] data) {
		final long errorCode = Toolbox.bytesToUint32(data, 0);
		final short adsState = (short)Toolbox.bytesToUint16(data, 4);
		final short devState = (short)Toolbox.bytesToUint16(data, 6);
		
		return new ReadStateResponse(errorCode, adsState, devState);
	}

	@Override
	public String toString() {
		return String.format("AdsStateRead: Error code: %d", errorCode);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + (int) (errorCode ^ (errorCode >>> 32));
		result = prime * result + (int) (adsState ^ (adsState >>> 16));
		result = prime * result + (int) (devState ^ (devState >>> 16));

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof WriteControlResponse))
			return false;
		
		ReadStateResponse other = (ReadStateResponse) obj;
		
		if (errorCode != other.errorCode)
			return false;
		if (adsState != other.adsState)
			return false;
		if (devState != other.devState)
			return false;

		return true;
	}
}
