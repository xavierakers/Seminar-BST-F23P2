/**
 * @author Xavier Akers
 * 
 * @version Last Updated 2023-09-29
 * @param <K>
 * 
 * @since 2023-09-22
 * 
 *        A Generic Key Value Pair
 *        Implementing Comparable
 * 
 * @param <K>
 *            Primitive Type for the Key
 * @param <V>
 *            Primitive Type for the Value
 */
public class KVPair<K extends Comparable<K>, V>
    implements Comparable<KVPair<K, V>> {

    private K key;
    private V value;

    /**
     * KV Constructor
     * 
     * @param key
     *            The KeyID
     * @param value
     *            The Data Value
     */
    KVPair(K key, V value) {
        this.key = key;
        this.value = value;
    }


    /**
     * Getter for Key
     * 
     * @return The KeyID
     */
    public K getKey() {
        return key;
    }


    /**
     * Getter for DataValue
     * 
     * @return The Data
     */
    public V getValue() {
        return value;
    }


    /**
     * Implemented CompareTo method
     * 
     * @param o
     *            The KVPair to Compare
     */
    @Override
    public int compareTo(KVPair<K, V> o) {

        return this.key.compareTo((K)o.getKey());
    }


    /**
     * Another CompareTo method
     * Comparing the KeyID
     * 
     * @param o
     *            ID to compare
     * @return Comparison
     */
    public int compareTo(K o) {
        return this.getKey().compareTo(o);
    }

}
