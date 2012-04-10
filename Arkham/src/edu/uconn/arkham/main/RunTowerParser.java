/**
 * This class pulls in xml data from the openBmap project and loads it into
 * the tower_locations table for later processing.
 */
package edu.uconn.arkham.main;

import edu.uconn.arkham.parser.Parser;

public class RunTowerParser {

	public RunTowerParser() {
		new Parser();
	}
	
	public static void main(String[] args) {
		new RunTowerParser();
	}

}
