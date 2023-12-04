package oy.interact.tira.factories;

import oy.interact.tira.util.TIRAKeyedContainer;
import oy.interact.tira.student.HashTable;

public class HashTableFactory {
	private HashTableFactory() {
		// empty
	}

	public static <K extends Comparable<K>, V> TIRAKeyedContainer<K,V> createHashTable() {
		return new HashTable<>();
	}

}
