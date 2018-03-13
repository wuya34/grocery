// Person.aidl
package com.example.amyas.grocery;
// Declare any non-default types here with import statements
import com.example.amyas.grocery.Person;
//
//parcelable Person;
interface PersonAidl {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    String getInfo(String s);
    String sendPerson(in Person person);
}
