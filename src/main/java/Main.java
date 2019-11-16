// Copyright (c) 2011-2012, Leif Bruder <leifbruder@googlemail.com>
// 
// Permission to use, copy, modify, and/or distribute this software for any
// purpose with or without fee is hereby granted, provided that the above
// copyright notice and this permission notice appear in all copies.
// 
// THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
// WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
// ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
// WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
// ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
// OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.

import org.lb.plc.*;
import org.lb.plc.tpy.*;
//import java.util.*;

public class Main implements VariableObserver {
	//@Override
	
	public void variableChanged(String variable, Object newValue) {
		System.out.println("Variable " + variable + " has a new value: " + newValue);
	}

	public static void main(String args[]) throws Exception {
		
		/*
		System.out.println(" ");
		TpyFile tpyFile = new TpyFile("tpy files/testPLC1-pretty.tpy");
		Map<String, Type> types = tpyFile.getTypes();
		for (String key : types.keySet()){
			Type type = types.get(key);
			System.out.println(type.getClass() + ": " + key);
		}
		System.out.println(" ");
		for (Variable item : tpyFile.getVariables()){
			System.out.println(item.type + " : " + item.name);
		}
		*/
		final TpyFile file = new TpyFile("tpy files/testPLC1-pretty.tpy");
		//Expander errors out if datatype is not defined in the .tpy (system types). The only place to add in system types is in VariableExpander.getByteSizeFromName().
		//I do not like it. Need to refactor... something.
		final VariableExpander expander = new VariableExpander(file);
		@SuppressWarnings("unused")
		final VariableLocator locator = new VariableLocator(expander.getVariables());

		//final VariableLocator locator = new VariableLocator(new VariableExpander(new TpyFile("tpy files/testPLC1-pretty.tpy")).getVariables());

		// final InetAddress destIp = InetAddress.getByName("192.168.1.2");
		// final String destNetId = "1.2.3.4.1.1";
		// final String sourceNetId = "192.168.1.1.1.1";
		//
		// final SimplePlcInterface conn = new SimplePlcInterface(destIp,
		// sourceNetId, 32768, destNetId, 801, 1000, locator, new Main());
		// System.out.println("Connection established");
		//
		// System.out.println("PLC device version: " + conn.getDeviceVersion());
		// System.out.println("Reading a variable: " +
		// conn.getVariableAsInteger(".variable.name"));
		//
		// conn.close();
	}
}
