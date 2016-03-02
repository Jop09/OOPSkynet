package de.hammes.skynet;

import skynet.SkynetSubnet;
import skynet.SubnetBackdoor;

public class RandomSkynetFail {

	
	public static void main(String[] args) {

		int nodes = 1;
		for (int i = 0; i < 20; i++) {
			boolean test = true;
			SubnetBackdoor sb = SkynetSubnet.createRandomSubnet(nodes);
			int links[][] = sb.getNodeLinks();
			for (int[] link : links) {
				System.out.println("" + link[0] + " " + link[1]);
				if (link[0] >= nodes || link[1] >= nodes)
					test = false;
			}
			if (test == false)
				System.out.println("Fail ");
		}
	}

}
