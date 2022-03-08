package com.techelevator.tenmo.model;

import java.util.Objects;

/**
 * This DTO contains details necessary for interfacing with the database's Account table.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */

public class Authority {

   private String name;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Authority(String name) {
      this.name = name;
   }


   /**
    * This method overrides the hashCode() method,
    * inputs the name property and returns a hashed name.
    *
    * @return returns a hashed name.
    *
    */
   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Authority authority = (Authority) o;
      return name == authority.name;
   }

   /**
    * This method overrides the hashCode() method,
    * inputs the name property and returns a hashed name.
    *
    * @return returns a hashed name.
    *
    */
   @Override
   public int hashCode() {
      return Objects.hash(name);
   }

   /**
    * This method overrides the toString() method when called from the
    * Authority DTO.
    *
    * @return returns User authority as a string.
    *
    */
   @Override
   public String toString() {
      return "Authority{" +
         "name=" + name +
         '}';
   }
}
