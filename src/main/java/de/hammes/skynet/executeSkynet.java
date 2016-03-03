package de.hammes.skynet;

import skynet.SkynetSubnet;
import skynet.SubnetBackdoor;

public class executeSkynet {

	public static void main(String[] args) {

		SubnetBackdoor sb = SkynetSubnet.createBackdoorToExistingSubnet(2);
		SkynetProblem.killAgent(sb);
	}
}
