
package zw.co.tonytech.ticketingsystem.business;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author tndangana
 * @param <T>
 */
/**
 Java provides a mechanism, 
 * called object serialization where an object can be represented as a sequence 
 * of bytes that includes the object's data as well as information about the object's
 * type and the types of data stored in the object.

After a serialized object has been written into a file, it can be read from the file and 
* deserialized that is, the type information and bytes that represent the object and its
* data can be used to recreate the object in memory.
 * @param <T>
 */
//In computer science, in the context of data storage, 
//serialization is the process of translating data structures or object state into a format that can be stored 
//Serialization is persisting an object from memory to a sequence of bits, for instance for saving onto the disk.
//Deserialization is the opposite - reading data from the disk to hydrate/create an object.
public interface IServiceComponents<T> extends Serializable {
    
    public T Save (T t);
    public List<T> findAll();
    public Long findOne();
    public T Delete(T t); 
    
}