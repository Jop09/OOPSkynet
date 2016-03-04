package de.hammes.skynet;

import skynet.SubnetBackdoor;

public interface IStrategy {

	public int[] chooseDisconnectingNodes(SubnetBackdoor sb);
}
// test