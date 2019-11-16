package org.lb.plc.ads;

import org.lb.plc.Toolbox;

public class WriteControlResponse extends Payload {
	
	/**
	 * The ID of the command
	 */
	private final static int COMMAND_ID			=		0x0005;
	
	/**
	 * The result
	 */
	private final long errorCode;

	/**
	 * Create new {@link WriteControlResponse}
	 * 
	 * @param errorCode ADS error number
	 */
	public WriteControlResponse(final long errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * 
	 */
	@Override
	public org.lb.plc.ams.ErrorCode getErrorCode() {
		return org.lb.plc.ams.ErrorCode.valueOf(errorCode);
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

		byte[] ret = new byte[4];
		
		Toolbox.copyIntoByteArray(ret, 0, Toolbox.uint32ToBytes(errorCode));
		
		return ret;
	}

	public static Payload valueOf(final byte[] data) {
		final long errorCode = Toolbox.bytesToUint32(data, 0);
		
		return new WriteControlResponse(errorCode);
	}

	@Override
	public String toString() {
		return String.format("AdsWriteControlResponse: Error code: %d", errorCode);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + (int) (errorCode ^ (errorCode >>> 32));

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
		
		WriteControlResponse other = (WriteControlResponse) obj;
		
		if (errorCode != other.errorCode)
			return false;

		return true;
	}
}
