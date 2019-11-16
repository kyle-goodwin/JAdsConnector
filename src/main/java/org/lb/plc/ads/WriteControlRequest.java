package org.lb.plc.ads;

import org.lb.plc.Toolbox;

public class WriteControlRequest extends Payload {
	
	/**
	 * The ID of the command
	 */
	private final static int COMMAND_ID			=		0x0005;
	
	/**
	 * The ADS state
	 */
	private short adsState;
	
	/**
	 * The deivce state
	 */
	private short devState;
	
	/**
	 * Additional data for the ADS device
	 */
	private byte [] data;
	
	/**
	 * Create new {@link WriteControlRequest}
	 * 
	 * @param adsState The ADS state
	 * @param devState The device state
	 * @param data Additional data
	 */
	public WriteControlRequest(final short adsState, final short devState, final byte[] data) {
		this.adsState = adsState;
		this.devState = devState;
		this.data = data;
	}
	
	/**
	 * Returns the {@link #adsState}
	 * 
	 * @return the {@link #adsState}
	 */
	public short getAdsState() {
		return this.adsState;
	}
	
	/**
	 * Returns the {@link #devState}
	 * 
	 * @return the {@link #devState}
	 */
	public short getDevState() {
		return this.devState;
	}
	
	/**
	 * Returns the {@link #data}
	 * 
	 * @return the {@link #data}
	 */
	public byte [] getData() {
		return this.data;
	}
	
	/**
	 * Returns length of the data array
	 * 
	 * @return Length of the data array
	 */
	public int getDataLength() {
		if (data == null) {
			return 0;
		} else {
			return data.length;
		}
	}

	@Override
	public int getCommandId() {
		return COMMAND_ID;
	}

	@Override
	public byte[] toBinary() {
		
		byte[] ret = new byte[8 + getDataLength()];
		
		Toolbox.copyIntoByteArray(ret, 0, Toolbox.uint16ToBytes(adsState));
		Toolbox.copyIntoByteArray(ret, 2, Toolbox.uint16ToBytes(devState));
		if (data == null) {
			Toolbox.copyIntoByteArray(ret, 4, Toolbox.uint32ToBytes(0));			
		} else {
			Toolbox.copyIntoByteArray(ret, 4, Toolbox.uint32ToBytes(data.length));
			Toolbox.copyIntoByteArray(ret, 8, data);
		}
		
		return ret;
	}

	public static Payload valueOf(final byte[] data) {
		
		final short adsState = (short)Toolbox.bytesToUint16(data, 0);
		final short devState = (short)Toolbox.bytesToUint16(data, 2);
		final int dataLength = (int)Toolbox.bytesToUint32(data, 4);
		
		byte [] dataArray = null;
		
		if (dataLength > 0) {
			dataArray = new byte [dataLength];
			System.arraycopy(data, 8, dataArray, 0, dataLength);
		}
		
		return new WriteControlRequest(adsState, devState, dataArray);
	}

	@Override
	public String toString() {
		return String.format(
				"AdsWriteControlRequest: AdsState %d, DevState %d, Data length %d bytes", adsState,
				devState, getDataLength());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (adsState ^ (adsState >>> 32));
		result = prime * result + (int) (devState ^ (devState >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof WriteControlRequest))
			return false;
		
		WriteControlRequest other = (WriteControlRequest) obj;
		
		if (adsState != other.adsState)
			return false;
		if (devState != other.devState)
			return false;
		if (data != other.data)
			return false;
		
		return true;
	}
}
